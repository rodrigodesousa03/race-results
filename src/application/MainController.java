package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import static java.util.stream.Collectors.toList;

import br.com.rsousa.pojo.ams.Driver;
import br.com.rsousa.pojo.ams.RFactorXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {

	private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

	private List<String> xmlTypes;

	@FXML
	private TextArea raceTextArea;

	@FXML
	private TextArea qualifyTextArea;
	
	@FXML
	private Text textDrivers;
	
	private Map<String, String> driverTeams = new HashMap<String, String>();
	
	@FXML
	void readCsvFileChooser(ActionEvent event) {
        BufferedReader br = null;
        String line = "";
        driverTeams = new HashMap<String, String>();
        
       	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("CSV File", "*.csv"));

    	File file = fc.showOpenDialog(null);
    		
    	if (file != null) {
			try {
	            br = new BufferedReader(new FileReader(file));
	            while ((line = br.readLine()) != null) {
	                String[] driver = line.split(";");
	                
	                if (!driver[0].contains("Piloto")) {
	                	driverTeams.put(driver[0], driver[1]);
	                }
	            }
	            
	            textDrivers.setText(driverTeams.size() + " Drivers");
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		}
	}
	
	@FXML
	void clearDrivers(ActionEvent event) {
		driverTeams = new HashMap<String, String>();
		
		textDrivers.setText(driverTeams.size() + " Drivers");
	}

	@FXML
	void raceFileChooser(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("XML Files", xmlTypes()));

		File file = fc.showOpenDialog(null);
		if (file != null) {
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(RFactorXML.class);

				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

				RFactorXML raceResult = (RFactorXML) jaxbUnmarshaller.unmarshal(file);

				List<Driver> drivers = Stream.of(raceResult.getRaceResults().getRace().getDriver())
						.sorted(Comparator.comparing(Driver::getPosition))
						.collect(toList());

				int position = 1;
				String resultStr = "";
				Driver driverBestLap = null;
				Integer totalLaps = 0;
				Double leaderFinishTime = null;

				for (Driver driver : drivers) {
					if (isDriver(driver)) {
						String raceTimeFormatted;

						if (position == 1) {
							driverBestLap = driver;
							raceTimeFormatted = driver.getLaps() + " voltas";
							totalLaps = driver.getLaps();
							leaderFinishTime = Double.parseDouble(driver.getFinishTime());
						} else {
							if (driver.getBestLapTime() != null && driverBestLap.getBestLapTime().compareTo(driver.getBestLapTime()) > 0) {
								driverBestLap = driver;
							}
							
							if (totalLaps == driver.getLaps()) {
								Double secondsBehindTheLeader = Double.parseDouble(driver.getFinishTime()) - leaderFinishTime;
								
								raceTimeFormatted = formatSeconds(secondsBehindTheLeader.toString());
								
							} else {
								int lapsBehindTheLeader = totalLaps - driver.getLaps();
								
								raceTimeFormatted = "+ " + lapsBehindTheLeader + " laps";
							}
						}
						
						if (!"Finished Normally".equals(driver.getFinishStatus()) && !"None".equals(driver.getFinishStatus())) {
							raceTimeFormatted += " (" + driver.getFinishStatus() + ")";
						}

						resultStr += resultLine(position, driver, raceTimeFormatted) + "\n";
						position++;
					}
				}

				resultStr += "Volta mais rápida: " + driverBestLap.getName() + ", "
						+ formatSeconds(driverBestLap.getBestLapTime());

				raceTextArea.setText(resultStr);
			} catch (Exception e) {
				raceTextArea.setText("Error transforming the XML file.");
				e.printStackTrace();
			}
		}
	}

	@FXML
	void qualifyFileChooser(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("XML Files", xmlTypes()));

		File file = fc.showOpenDialog(null);
		if (file != null) {
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(RFactorXML.class);

				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

				RFactorXML raceResult = (RFactorXML) jaxbUnmarshaller.unmarshal(file);

				Driver[] drivers = raceResult.getRaceResults().getQualify().getDriver();

				int position = 1;

				String resultStr = "";

				for (Driver driver : drivers) {
					if (isDriver(driver)) {
						String bestLapTimeFormatted = formatSeconds(driver.getBestLapTime(), "sem tempo");

						resultStr += resultLine(position, driver, bestLapTimeFormatted) + "\n";

						position++;
					}
				}

				qualifyTextArea.setText(resultStr);
			} catch (Exception e) {
				qualifyTextArea.setText("Error transforming the XML file.");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
	}

	private List<String> xmlTypes() {
		if (xmlTypes == null) {
			xmlTypes = new ArrayList<String>();
			xmlTypes.add("*.xml");
			xmlTypes.add("*.XML");
		}

		return xmlTypes;
	}
	
	private boolean isDriver(Driver driver) {
		return !driver.getName().contains("Diretor") && !driver.getName().contains("Comentarista") && !driver.getName().contains("Narrador");
	}

	private String resultLine(int position, Driver driver, String time) {
		String driverTeamName = driverTeams.containsKey(driver.getName()) ? driverTeams.get(driver.getName()) : driver.getTeamName().trim();
		
		return position + " " + driver.getName() + " (" + driverTeamName + "), " + time;
	}
	
	private String formatSeconds(String time, String textIfNull) {
		String formattedSeconds = formatSeconds(time);
		
		if (formattedSeconds == null) {
			return textIfNull;
		}
		
		return formattedSeconds;
	}

	private String formatSeconds(String time) {
		if (time == null) {
			return null;
		}
		
		String[] bestLapTime = time.split("\\.");

		Integer totalSeconds = Integer.parseInt(bestLapTime[0]);
		String milliseconds = bestLapTime[1].substring(0, 3);

		String bestLapTimeFormatted = LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
				+ milliseconds;
		return bestLapTimeFormatted;
	}
}
