package application;

import static br.com.rsousa.enums.FileType.RACE;
import static br.com.rsousa.transformers.RFactorTransformer.processQualify;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import br.com.rsousa.formatter.SessionFormatter;
import br.com.rsousa.pojo.Event;
import org.controlsfx.control.PopOver;

import br.com.rsousa.enums.FileType;
import br.com.rsousa.pojo.Driver;
import br.com.rsousa.transformers.AssettoTransformer;
import br.com.rsousa.transformers.IRacingTransformer;
import br.com.rsousa.transformers.RFactorTransformer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {

	private List<String> fileTypes;

	@FXML
	private TextArea raceTextArea;

	@FXML
	private TextArea qualifyTextArea;
	
	private PopOver popOver;
	
	@FXML
	private Text textDrivers;
	
	private List<Driver> driverTeams = new ArrayList<>();
	
	private Event raceEvent = new Event();
	
	@FXML
	void readCsvFileChooser(ActionEvent event) {
        driverTeams.clear();
        
       	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("CSV File", "*.csv"));

    	File file = fc.showOpenDialog(null);
    		
    	processDrivers(file);
	}

	@FXML
	void showDrivers() {
		StringBuilder drivers = new StringBuilder();
		
		if (driverTeams.isEmpty()) {
			drivers.append("No Drivers");
		} else {
			driverTeams.stream().sorted(Comparator.comparing(Driver::getName))
								.forEach(d -> drivers.append(d.toString() + "\n"));
		}
		
		Label label = new Label(drivers.toString());
		
		VBox vBox = new VBox(label);
		
		popOver = new PopOver(vBox);
		
		popOver.show(textDrivers);
	}
	
	@FXML
	void hideDrivers() {
		popOver.hide();
	}
	
	@FXML
	void clearDrivers(ActionEvent event) {
		driverTeams.clear();
		
		textDrivers.setText(driverTeams.size() + " Drivers");
	}

	@FXML
	void processLogFileChooser(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("XML, CSV Files", fileTypes()));

		File file = fc.showOpenDialog(null);
		
		if (file.getName().contains("xml") || file.getName().contains("XML")) {
			raceEvent.addSession(RFactorTransformer.processQualify(file, driverTeams));
		} else if (file.getName().contains("csv") || file.getName().contains("CSV")) {
			raceEvent.addSession(IRacingTransformer.processQualify(file, driverTeams));
		} else {
			raceEvent.addSession(AssettoTransformer.processQualify(file, driverTeams));
		}
		
		showResults();
	}
	
	@FXML
	void dragOver(DragEvent event) {
		if (event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
		}
	}
	
	@FXML
	void logFileDrop(DragEvent event) {
		raceEvent.clear();
		
		List<File> files = event.getDragboard().getFiles();
		
		files.stream().filter(f -> f.getName().contains("pilotos")).findFirst().ifPresent(f -> {
			driverTeams.clear();
			processDrivers(f);
			files.remove(f);
		});
		
		for (File file : files) {
			if (file.getName().contains("xml") || file.getName().contains("XML")) {
				raceEvent.addSession(RFactorTransformer.processQualify(file, driverTeams));
			} else if (file.getName().contains("csv") || file.getName().contains("CSV")) {
				raceEvent.addSession(IRacingTransformer.processQualify(file, driverTeams));
			} else if (file.getName().contains("json") || file.getName().contains("JSON")) {
				raceEvent.addSession(AssettoTransformer.processQualify(file, driverTeams));
			}
		}
		
		showResults();
	}



	private void showResults() {
		if (raceEvent.getQualifySession() != null) {
			qualifyTextArea.setText(SessionFormatter.format(raceEvent.getQualifySession()));
		}

		if (raceEvent.getRaceSession() != null) {
			raceTextArea.setText(SessionFormatter.format(raceEvent.getRaceSession()));
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
	}

	private List<String> fileTypes() {
		if (fileTypes == null) {
			fileTypes = new ArrayList<String>();
			fileTypes.add("*.xml");
			fileTypes.add("*.XML");
			fileTypes.add("*.json");
			fileTypes.add("*.JSON");
			fileTypes.add("*.csv");
			fileTypes.add("*.CSV");
		}

		return fileTypes;
	}
	
	
	
	private void processDrivers(File file) {
		BufferedReader br = null;
        String line = "";
        
        if (file != null) {
			try {
	            br = new BufferedReader(new FileReader(file));
	            while ((line = br.readLine()) != null) {
	                String[] driver = line.split(";");
	                
	                if (!driver[0].contains("Piloto")) {
	                	String id = driver.length > 2 ? driver[2] : null;
	                			
	                	driverTeams.add(new Driver(driver[0], driver[1], id));
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
}
