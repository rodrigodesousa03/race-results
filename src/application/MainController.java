package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import br.com.rsousa.formatter.SessionFormatter;
import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.transformers.*;
import br.com.rsousa.utils.SessionUtils;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.PopOver;

import br.com.rsousa.pojo.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TextArea sheetsTextArea;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField circuitTextField;

    @FXML
    private TextArea licenseTextArea;

    @FXML
    private TextArea qualifyTextArea;

    @FXML
    private CheckBox hardDnfCheckBox;

    @FXML
    private CheckBox selectiveCheckBox;

    private PopOver popOver;

    @FXML
    private Text textDrivers;

    @FXML
    private TableView<Driver> raceTableView;

    @FXML
    private TableColumn<Driver, Integer> positionColumn;

    @FXML
    private TableColumn<Driver, Integer> driverColumn;

    @FXML
    private TableColumn<Driver, Integer> textColumn;

    @FXML
    private Text versaoLabel;

    private List<Driver> driverTeams = new ArrayList<>();

    private Driver driverSelected;

    private Event raceEvent = new Event();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        positionColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().positionText()));
        driverColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getName()));
        textColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().text()));

        raceTableView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> selectDriver(newValue));

        versaoLabel.setText("5.2");
    }

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
                    .forEach(d -> drivers.append(d).append("\n"));
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

        textDrivers.setText(0 + " Drivers");
    }

    @FXML
    void clear(ActionEvent event) {
        raceEvent.clear(false);

        raceTextArea.setText(null);
        qualifyTextArea.setText(null);
    }

    @FXML
    void processLogFileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("XML, CSV, JSON Files", fileTypes()));

        File file = fc.showOpenDialog(null);

        processLog(file);

        showResults();
    }

    @FXML
    void moveUpButton(ActionEvent event) {
        if (isDriverNotSelected()) {
            return;
        }

        SessionUtils.moveUpPosition(raceEvent.getRaceSessions().get(0), driverSelected);

        showResults();
    }

    @FXML
    void moveDownButton(ActionEvent event) {
        if (isDriverNotSelected()) {
            return;
        }

        SessionUtils.moveDownPosition(raceEvent.getRaceSessions().get(0), driverSelected);

        showResults();
    }

    @FXML
    void lastPositionButton(ActionEvent event) {
        if (isDriverNotSelected()) {
            return;
        }

        SessionUtils.moveLastPosition(raceEvent.getRaceSessions().get(0), driverSelected);

        showResults();
    }

    @FXML
    void didNotFinishedButton(ActionEvent event) {
        if (isDriverNotSelected()) {
            return;
        }

        SessionUtils.didNotFinished(driverSelected);

        showResults();
    }

    @FXML
    void disqualifyButton(ActionEvent event) {
        if (isDriverNotSelected()) {
            return;
        }

        SessionUtils.disqualify(raceEvent.getRaceSessions().get(0), driverSelected);

        showResults();
    }

    private boolean isDriverNotSelected() {
        return raceEvent.getRaceSessions().isEmpty() || driverSelected == null;
    }

    @FXML
    void resetRace(ActionEvent event) {
        raceEvent.resetRace();

        showResults();
    }

    @FXML
    void updateLicensePoints(KeyEvent event) {
        if (raceEvent.getRaceSessions().isEmpty()) {
            return;
        }

        raceEvent.getRaceSessions().get(0).drivers().forEach(d -> d.setLicensePoints(0));

        String[] licenseTextRows = licenseTextArea.getText().split("\n");

        for (String licenseRow : licenseTextRows) {
            if (licenseRow.contains("+")) {
                String[] row = licenseRow.split("\\+");

                String driverName = row[0].trim();
                Integer licensePoints = Integer.parseInt(row[1]);

                if (!raceEvent.getRaceSessions().isEmpty()) {
                    raceEvent.getRaceSessions().get(0).drivers().stream().filter(d -> d.getName().equals(driverName))
                            .findFirst()
                            .ifPresent(d -> d.setLicensePoints(licensePoints));
                }
            }
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
        raceEvent.clear(selectiveCheckBox.isSelected());

        List<File> files = event.getDragboard().getFiles();

        files.stream().filter(f -> f.getName().contains("Cadastros")).findFirst().ifPresent(f -> {
            driverTeams.clear();
            processDrivers(f);
            files.remove(f);
        });

        for (File file : files) {
            processLog(file);
        }

        showResults();
    }

    private void processLog(File file) {
        SimulatorTransformer simulatorTransformer = new EmptyTransformer();

        if (file.getName().contains("xml") || file.getName().contains("XML")) {
            simulatorTransformer = new RFactorTransformer();
        } else if (file.getName().contains("csv") || file.getName().contains("CSV")) {
            simulatorTransformer = new IRacingCsvTransformer();
        } else if (file.getName().contains("json") || file.getName().contains("JSON")) {
            if (isIRacingLog(file)) {
                simulatorTransformer = new IRacingJsonTransformer();
            } else if (isAssettoCorsaLog(file)) {
                simulatorTransformer = new AssettoTransformer();
            } else if (isAutomobilista2Log(file)) {
                simulatorTransformer = new Automobilista2Transformer();
            } else {
                simulatorTransformer = new AssettoCorsaCompetizioneTransformer();
            }
        }

        try {
            boolean hardDnf = hardDnfCheckBox.isSelected();
            boolean isSelective = selectiveCheckBox.isSelected();

            if (simulatorTransformer.processEvent()) {
                raceEvent = simulatorTransformer.processEvent(file, driverTeams, hardDnf, isSelective);
            } else {
                raceEvent.addSession(simulatorTransformer.processQualify(file, driverTeams, hardDnf, isSelective), isSelective);
            }
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erro ao importar o log");
            a.setContentText(e.getMessage());
            a.show();

            e.printStackTrace();
        }
    }

    private static boolean isIRacingLog(File file) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString().contains("i_rating");
    }

    private static boolean isAssettoCorsaLog(File file) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString().contains("TrackName");
    }

    private static boolean isAutomobilista2Log(File file) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString().contains("participants");
    }

    private void selectDriver(Driver driver) {
        if (driver != null) {
            driverSelected = driver;
        }
    }

    private void showResults() {
        if (raceEvent.getQualifySession() != null) {
            qualifyTextArea.setText(SessionFormatter.format(raceEvent.getQualifySession()));
        }

        if (!raceEvent.getRaceSessions().isEmpty()) {
            raceTableView.getItems().clear();
            raceEvent.getRaceSessions().forEach(Session::sortDrivers);
            raceTableView.getItems().addAll(raceEvent.getRaceSessions().get(0).drivers());
            raceTextArea.setText(SessionFormatter.format(raceEvent.getRaceSessions()));
            sheetsTextArea.setText(SessionFormatter.toSheets(raceEvent.getRaceSessions(), categoryTextField.getText(), circuitTextField.getText()));
        }
    }

    private List<String> fileTypes() {
        if (fileTypes == null) {
            fileTypes = new ArrayList<>();
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
        String line;

        if (file != null) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    String[] driver = line.split(",");

                    if (!driver[0].contains("Piloto")) {
                        String id = driver.length > 2 ? driver[2] : null;

                        driverTeams.add(new Driver(driver[0], driver[1], id, driver[3]));
                    }
                }

                textDrivers.setText(driverTeams.size() + " Drivers");
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
