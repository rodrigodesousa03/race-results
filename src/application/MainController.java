package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import br.com.rsousa.formatter.SessionFormatter;
import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.transformers.*;
import br.com.rsousa.utils.SessionUtils;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
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
    private TextArea sheetsResultsTextArea;

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
    private ComboBox<Integer> batteryComboBox;

    @FXML
    private Text versaoLabel;

    @FXML
    private VBox welcomeScreen;

    @FXML
    private HBox resultsPanel;

    private List<Driver> driverTeams = new ArrayList<>();

    private Driver driverSelected;

    private Event event = new Event();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        positionColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().positionText()));
        driverColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getName()));
        textColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().text()));

        // Set column resize policy programmatically for JavaFX 11 compatibility
        raceTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        raceTableView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> selectDriver(newValue));

        batteryComboBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showResults());

        versaoLabel.setText("7.0");
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
    void readEventFileChooser(ActionEvent event) {
        this.event.clear(selectiveCheckBox.isSelected());

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("XML, CSV, JSON Files", fileTypes()));

        File file = fc.showOpenDialog(null);

        if (file.getName().contains("Cadastros")) {
            driverTeams.clear();
            processDrivers(file);
        } else {
            processLog(file);
        }

        showResults();
    }

    @FXML
    void showDrivers() {
        VBox vBox = new VBox(10);
        vBox.setStyle("-fx-padding: 15; -fx-background-color: white; -fx-background-radius: 8;");

        // Header
        Label header = new Label("Pilotos Carregados");
        header.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #2C3E50; -fx-padding: 0 0 10 0;");

        if (driverTeams.isEmpty()) {
            Label emptyLabel = new Label("Nenhum piloto carregado");
            emptyLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #95A5A6; -fx-padding: 20 0;");
            vBox.getChildren().addAll(header, emptyLabel);
        } else {
            // Separator
            javafx.scene.control.Separator separator = new javafx.scene.control.Separator();
            separator.setStyle("-fx-background-color: #E0E0E0;");

            // Lista de pilotos com scroll
            javafx.scene.control.ListView<String> listView = new javafx.scene.control.ListView<>();
            listView.setPrefWidth(280);
            listView.setPrefHeight(Math.min(driverTeams.size() * 28 + 10, 400));
            listView.setStyle("-fx-background-color: #FAFAFA; -fx-border-color: transparent; -fx-font-size: 12px;");

            // Adiciona pilotos ordenados
            driverTeams.stream()
                    .sorted(Comparator.comparing(Driver::getName))
                    .forEach(d -> listView.getItems().add(d.getName()));

            // Footer com contagem
            Label footer = new Label(driverTeams.size() + " piloto(s) no total");
            footer.setStyle("-fx-font-size: 11px; -fx-text-fill: #7F8C8D; -fx-padding: 10 0 0 0;");

            vBox.getChildren().addAll(header, separator, listView, footer);
        }

        popOver = new PopOver(vBox);
        popOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        popOver.setCornerRadius(8);
        popOver.setDetachable(false);

        popOver.show(textDrivers);
    }

    @FXML
    void hideDrivers() {
        popOver.hide();
    }

    @FXML
    void clearDrivers(ActionEvent event) {
        driverTeams.clear();

        textDrivers.setText(0 + " Pilotos");
    }

    @FXML
    void clear(ActionEvent event) {
        this.event.clear(false);

        raceTextArea.setText(null);
        qualifyTextArea.setText(null);

        // Show welcome screen again
        showWelcomeScreen();
    }

    private void showWelcomeScreen() {
        welcomeScreen.setVisible(true);
        welcomeScreen.setManaged(true);
        resultsPanel.setVisible(false);
        resultsPanel.setManaged(false);
    }

    private void showResultsPanel() {
        welcomeScreen.setVisible(false);
        welcomeScreen.setManaged(false);
        resultsPanel.setVisible(true);
        resultsPanel.setManaged(true);
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

        SessionUtils.moveUpPosition(this.event.getRaceSessions().get(batteryComboBox.getValue()-1), driverSelected);

        showResults();
    }

    @FXML
    void moveDownButton(ActionEvent event) {
        if (isDriverNotSelected()) {
            return;
        }

        SessionUtils.moveDownPosition(this.event.getRaceSessions().get(batteryComboBox.getValue()-1), driverSelected);

        showResults();
    }

    @FXML
    void lastPositionButton(ActionEvent event) {
        if (isDriverNotSelected()) {
            return;
        }

        SessionUtils.moveLastPosition(this.event.getRaceSessions().get(batteryComboBox.getValue()-1), driverSelected);

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

        SessionUtils.disqualify(this.event.getRaceSessions().get(batteryComboBox.getValue()-1), driverSelected);

        showResults();
    }

    private boolean isDriverNotSelected() {
        return event.getRaceSessions().isEmpty() || driverSelected == null;
    }

    @FXML
    void resetRace(ActionEvent event) {
        this.event.resetRace();

        showResults();
    }

    @FXML
    void updateLicensePoints(KeyEvent event) {
        if (this.event.getRaceSessions().isEmpty()) {
            return;
        }

        this.event.getRaceSessions().stream().map(Session::drivers).forEach(drivers -> drivers.forEach(d -> d.setLicensePoints(0)));

        String[] licenseTextRows = licenseTextArea.getText().split("\n");

        for (String licenseRow : licenseTextRows) {
            if (licenseRow.contains("+")) {
                String[] row = licenseRow.split("\\+");

                String driverName = row[0].trim();
                Integer licensePoints = Integer.parseInt(row[1]);

                if (!this.event.getRaceSessions().isEmpty()) {
                    this.event.getRaceSessions().forEach(s -> s.drivers().stream().filter(d -> d.getName().equals(driverName))
                            .findFirst()
                            .ifPresent(d -> d.setLicensePoints(licensePoints)));
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
        this.event.clear(selectiveCheckBox.isSelected());

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
        SimulatorTransformer simulatorTransformer = switch (getFileExtension(file)) {
            case "xml", "XML" -> new RFactorTransformer();
            case "csv", "CSV" -> new IRacingCsvTransformer();
            case "json", "JSON" -> {
                // Verifica ACC primeiro para evitar confusão com Assetto Corsa normal
                if (isAssettoCorsaCompetizioneLog(file)) {
                    yield new AssettoCorsaCompetizioneTransformer();
                } else if (isIRacingLog(file)) {
                    yield new IRacingJsonTransformer();
                } else if (isAssettoCorsaLog(file)) {
                    yield new AssettoTransformer();
                } else if (isAutomobilista2Log(file)) {
                    yield new Automobilista2Transformer();
                } else {
                    // Se nenhum tipo foi detectado, retorna EmptyTransformer
                    yield new EmptyTransformer();
                }
            }
            default -> new EmptyTransformer();
        };

        // Verifica se o formato foi reconhecido
        if (simulatorTransformer instanceof EmptyTransformer) {
            String fileName = file != null ? file.getName() : "arquivo desconhecido";
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Formato não reconhecido");
                alert.setHeaderText("Não foi possível identificar o formato do arquivo: " + fileName);
                alert.setContentText("O arquivo não corresponde a nenhum formato conhecido:\n" +
                        "- iRacing (JSON/CSV)\n" +
                        "- Assetto Corsa (JSON)\n" +
                        "- Automobilista 2 (JSON)\n" +
                        "- Assetto Corsa Competizione (JSON)\n" +
                        "- rFactor (XML)\n\n" +
                        "Verifique se o arquivo é um log válido de corrida.");
                alert.showAndWait();
            });
            return;
        }

        try {
            boolean hardDnf = hardDnfCheckBox.isSelected();
            boolean isSelective = selectiveCheckBox.isSelected();

            if (simulatorTransformer.processEvent()) {
                event = simulatorTransformer.processEvent(file, driverTeams, hardDnf, isSelective);
            } else {
                event.addSession(simulatorTransformer.processQualify(file, driverTeams, hardDnf, isSelective), isSelective);
            }

            batteryComboBox.getItems().clear();
            for (int i = 1; i <= event.getRaceSessions().size(); i++) {
                batteryComboBox.getItems().add(i);
            }

            batteryComboBox.getSelectionModel().selectFirst();

            // Show results panel after loading data
            showResultsPanel();
        } catch (Exception e) {
            String fileName = file != null ? file.getName() : "arquivo desconhecido";
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro ao importar o log");
                alert.setHeaderText("Ocorreu um erro ao processar o arquivo: " + fileName);

                String errorMessage = e.getMessage() != null ? e.getMessage() : "Erro desconhecido";
                alert.setContentText(errorMessage);

                // Adiciona detalhes expandíveis com o stack trace
                TextArea textArea = new TextArea(getStackTraceAsString(e));
                textArea.setEditable(false);
                textArea.setWrapText(true);
                textArea.setMaxWidth(Double.MAX_VALUE);
                textArea.setMaxHeight(Double.MAX_VALUE);

                alert.getDialogPane().setExpandableContent(textArea);
                alert.showAndWait();
            });

            e.printStackTrace();
        }
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return ""; // empty extension
        }
        return fileName.substring(lastIndexOfDot + 1);
    }

    private String getStackTraceAsString(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.toString()).append("\n");
        for (StackTraceElement element : e.getStackTrace()) {
            sb.append("\tat ").append(element.toString()).append("\n");
        }
        if (e.getCause() != null) {
            sb.append("\nCaused by: ").append(e.getCause().toString()).append("\n");
            for (StackTraceElement element : e.getCause().getStackTrace()) {
                sb.append("\tat ").append(element.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    private static boolean isIRacingLog(File file) {
        try {
            // Tenta primeiro com UTF-8
            String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardCharsets.UTF_8);
            return content.contains("i_rating");
        } catch (Exception e) {
            try {
                // Se falhar, tenta com ISO-8859-1 (Latin1)
                String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardCharsets.ISO_8859_1);
                return content.contains("i_rating");
            } catch (IOException ex) {
                // Se ainda assim falhar, retorna false
                return false;
            }
        }
    }

    private static boolean isAssettoCorsaLog(File file) {
        try {
            // Tenta primeiro com UTF-8
            String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardCharsets.UTF_8);
            // Assetto Corsa (não Competizione) tem "TrackName" com T maiúsculo
            // E não tem os campos específicos do ACC
            return content.contains("\"TrackName\"") && !content.contains("\"sessionType\"");
        } catch (Exception e) {
            try {
                // Se falhar, tenta com ISO-8859-1 (Latin1)
                String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardCharsets.ISO_8859_1);
                return content.contains("\"TrackName\"") && !content.contains("\"sessionType\"");
            } catch (IOException ex) {
                // Se ainda assim falhar, retorna false
                return false;
            }
        }
    }

    private static boolean isAutomobilista2Log(File file) {
        String[] encodings = {"UTF-8", "UTF-16LE", "UTF-16BE", "UTF-16", "ISO-8859-1"};

        for (String encoding : encodings) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), encoding);
                if (content.trim().contains("participants")) {
                    return true;
                }
            } catch (Exception e) {
                // Continua tentando outros encodings
            }
        }
        return false;
    }

    private static boolean isAssettoCorsaCompetizioneLog(File file) {
        // Tenta múltiplos encodings
        String[] encodings = {"UTF-8", "UTF-16LE", "UTF-16BE", "UTF-16", "ISO-8859-1"};

        for (String encoding : encodings) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), encoding);
                String cleanContent = content.trim();

                // Se começar com BOM ou caracteres inválidos, pula
                if (cleanContent.isEmpty() || cleanContent.charAt(0) == '\uFFFD') {
                    continue;
                }

                // ACC logs contêm "sessionType", "trackName" (minúsculo) e "leaderBoardLines"
                boolean hasSessionType = cleanContent.contains("\"sessionType\"");
                boolean hasTrackName = cleanContent.contains("\"trackName\"");
                boolean hasLeaderBoardLines = cleanContent.contains("\"leaderBoardLines\"");

                if (hasSessionType && hasTrackName && hasLeaderBoardLines) {
                    return true;
                }
            } catch (Exception e) {
                // Continua tentando outros encodings
            }
        }

        return false;
    }

    private void selectDriver(Driver driver) {
        if (driver != null) {
            driverSelected = driver;
        }
    }

    private void showResults() {
        if (event.getQualifySession() != null) {
            qualifyTextArea.setText(SessionFormatter.format(event.getQualifySession()));
        }

        if (!event.getRaceSessions().isEmpty()) {
            // Salva o índice do driver selecionado antes de limpar
            int selectedIndex = raceTableView.getSelectionModel().getSelectedIndex();

            raceTableView.getItems().clear();
            event.getRaceSessions().forEach(Session::sortDrivers);
            raceTableView.getItems().addAll(event.getRaceSessions().get(batteryComboBox.getValue()-1).drivers());

            // Restaura a seleção se havia um driver selecionado
            if (driverSelected != null && selectedIndex >= 0 && selectedIndex < raceTableView.getItems().size()) {
                // Procura o driver na nova lista (a posição pode ter mudado)
                for (int i = 0; i < raceTableView.getItems().size(); i++) {
                    Driver driver = raceTableView.getItems().get(i);
                    if (driver.getName().equals(driverSelected.getName())) {
                        raceTableView.getSelectionModel().select(i);
                        driverSelected = driver; // Atualiza a referência
                        break;
                    }
                }
            }

            raceTextArea.setText(SessionFormatter.format(event.getRaceSessions()));
            sheetsTextArea.setText(SessionFormatter.toSheets(event.getRaceSessions(), categoryTextField.getText(), circuitTextField.getText()));
        }

        sheetsResultsTextArea.setText(SessionFormatter.toSheetsResults(event));
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

        if (file == null) {
            return;
        }

        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] driver = line.split(",");

                if (!driver[0].contains("Piloto")) {
                    String id = driver.length > 2 ? driver[2] : null;

                    Integer carNumber = driver.length > 4 ? Integer.parseInt(driver[4]) : null;

                    driverTeams.add(new Driver(driver[0], driver[1], id, driver[3], carNumber));
                }
            }

            textDrivers.setText(driverTeams.size() + " Pilotos");
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
