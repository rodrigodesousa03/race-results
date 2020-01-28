package application;

import static application.RFactorTransformer.proccessXMLQualify;
import static application.RFactorTransformer.proccessXMLRace;
import static br.com.rsousa.enums.FileType.RACE;

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

import org.controlsfx.control.PopOver;

import static java.util.stream.Collectors.toList;

import br.com.rsousa.enums.FileType;
import br.com.rsousa.pojo.ams.Driver;
import br.com.rsousa.pojo.ams.RFactorXML;
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
	
	private Map<String, String> driverTeams = new HashMap<String, String>();
	
	private Map<FileType, String> results = new HashMap<FileType, String>();
	
	@FXML
	void readCsvFileChooser(ActionEvent event) {
        driverTeams = new HashMap<String, String>();
        
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
			driverTeams.keySet().stream().sorted((k1, k2) -> k1.compareTo(k2)).forEach(k -> drivers.append("Driver: " + k + " - Team: " + driverTeams.get(k) + "\n"));
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
		driverTeams = new HashMap<String, String>();
		
		textDrivers.setText(driverTeams.size() + " Drivers");
	}

	@FXML
	void processLogFileChooser(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("XML Files", fileTypes()));

		File file = fc.showOpenDialog(null);
		proccessXMLQualify(file, driverTeams, results);
		
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
		results.clear();
		
		List<File> files = event.getDragboard().getFiles();
		
		files.stream().filter(f -> f.getName().contains("csv")).findFirst().ifPresent(f -> processDrivers(f));
		
		for (File file : files) {
			if (file.getName().contains("xml") || file.getName().contains("XML")) {
				proccessXMLQualify(file, driverTeams, results);
			}
		}
		
		showResults();
	}



	private void showResults() {
		for (FileType type : results.keySet()) {
			if (type == RACE) {
				raceTextArea.setText(results.get(type));
			} else {
				qualifyTextArea.setText(results.get(type));
			}
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
}
