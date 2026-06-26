package application;

import br.com.rsousa.iracing.IRacingCredentials;

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
import javafx.concurrent.Task;
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

    @FXML
    private VBox loadingOverlay;

    @FXML
    private Label loadingFileLabel;

    @FXML
    private Label loadingProgressLabel;

    @FXML
    private Label loadingDetailLabel;

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
        if (file == null) return;

        if (file.getName().contains("Cadastros")) {
            driverTeams.clear();
            processDrivers(file);
        } else {
            processLogsAsync(List.of(file));
        }
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
        if (file == null) return;

        this.event.clear(selectiveCheckBox.isSelected());
        processLogsAsync(List.of(file));
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
    void openIRacingSettings(ActionEvent event) {
        IRacingCredentials creds = IRacingCredentials.load();

        javafx.scene.control.TextField emailField = new javafx.scene.control.TextField(creds.getEmail());
        emailField.setPromptText("seu@email.com");

        javafx.scene.control.PasswordField passwordField = new javafx.scene.control.PasswordField();
        passwordField.setText(creds.getPassword());
        passwordField.setPromptText("Senha iRacing");

        javafx.scene.control.TextField clientIdField = new javafx.scene.control.TextField(creds.getClientId());
        clientIdField.setPromptText("Ex: 67090-pwlimited");

        javafx.scene.control.PasswordField clientSecretField = new javafx.scene.control.PasswordField();
        clientSecretField.setText(creds.getClientSecret());
        clientSecretField.setPromptText("Client Secret");

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        grid.add(new javafx.scene.control.Label("Email iRacing:"),   0, 0);
        grid.add(emailField,        1, 0);
        grid.add(new javafx.scene.control.Label("Senha:"),            0, 1);
        grid.add(passwordField,     1, 1);
        grid.add(new javafx.scene.control.Label("Client ID:"),        0, 2);
        grid.add(clientIdField,     1, 2);
        grid.add(new javafx.scene.control.Label("Client Secret:"),    0, 3);
        grid.add(clientSecretField, 1, 3);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Configurações iRacing API");
        dialog.setHeaderText("Credenciais para validação de voltas off-track na seletiva.\nSalvas em ~/.race-results/iracing.properties");
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.OK) {
                creds.setEmail(emailField.getText().trim());
                creds.setPassword(passwordField.getText());
                creds.setClientId(clientIdField.getText().trim());
                creds.setClientSecret(clientSecretField.getText());
                try {
                    creds.save();
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setContentText("Não foi possível salvar as credenciais: " + ex.getMessage());
                    alert.showAndWait();
                }
            }
        });
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

        List<File> files = new ArrayList<>(event.getDragboard().getFiles());

        files.stream().filter(f -> f.getName().contains("Cadastros")).findFirst().ifPresent(f -> {
            driverTeams.clear();
            processDrivers(f);
        });

        List<File> logFiles = files.stream()
                .filter(f -> !f.getName().contains("Cadastros"))
                .collect(java.util.stream.Collectors.toList());

        if (!logFiles.isEmpty()) {
            processLogsAsync(logFiles);
        }
    }

    // -------------------------------------------------------------------------
    // Processamento assíncrono com overlay de loading
    // -------------------------------------------------------------------------

    private void processLogsAsync(List<File> files) {
        boolean isSelective = selectiveCheckBox.isSelected();
        boolean hardDnf     = hardDnfCheckBox.isSelected();
        List<Driver> teamsCopy = new ArrayList<>(driverTeams);

        boolean hasIRacingSelective = isSelective &&
                files.stream().anyMatch(MainController::isIRacingLog);

        // Platform.runLater direto — evita o coalescing do updateMessage() do Task
        java.util.function.Consumer<String> updateFile = msg -> {
            System.out.println("[Arquivo] " + msg);
            Platform.runLater(() -> loadingFileLabel.setText(msg));
        };
        java.util.function.Consumer<String> updateProgress = msg -> {
            System.out.println("[API]     " + msg);
            Platform.runLater(() -> loadingProgressLabel.setText(msg));
        };
        java.util.function.Consumer<String> updateDetail = msg -> {
            System.out.println("[Detalhe] " + msg);
            Platform.runLater(() -> loadingDetailLabel.setText(msg));
        };

        // Cria um único IRacingApiClient para todo o lote — evita re-autenticação por arquivo
        br.com.rsousa.iracing.IRacingApiClient sharedApiClient;
        if (hasIRacingSelective) {
            br.com.rsousa.iracing.IRacingCredentials creds = br.com.rsousa.iracing.IRacingCredentials.load();
            sharedApiClient = creds.isConfigured() ? new br.com.rsousa.iracing.IRacingApiClient(creds) : null;
        } else {
            sharedApiClient = null;
        }

        Task<Event> task = new Task<>() {
            @Override
            protected Event call() throws Exception {
                Event working = new Event();
                for (int i = 0; i < files.size(); i++) {
                    File file = files.get(i);
                    updateFile.accept("Arquivo " + (i + 1) + " de " + files.size()
                            + " — " + file.getName());
                    updateProgress.accept("");

                    SimulatorTransformer tr = resolveTransformer(file);
                    if (tr instanceof EmptyTransformer) continue;

                    if (hasIRacingSelective && tr instanceof IRacingJsonTransformer irT) {
                        irT.setProgressCallback(updateProgress);
                        irT.setDetailCallback(updateDetail);
                        if (sharedApiClient != null) irT.setApiClient(sharedApiClient);
                    }

                    try {
                        if (tr.processEvent()) {
                            Event fe = tr.processEvent(file, teamsCopy, hardDnf, isSelective);
                            if (fe != null) {
                                if (fe.getQualifySession() != null) {
                                    if (working.getQualifySession() == null) {
                                        working.setQualifySession(fe.getQualifySession());
                                    } else {
                                        // Concatenação direta — evita addDrivers/sortDriversByBestLap
                                        working.getQualifySession().drivers()
                                               .addAll(fe.getQualifySession().drivers());
                                        working.getQualifySession().getLapInvalidations()
                                               .addAll(fe.getQualifySession().getLapInvalidations());
                                    }
                                }
                                if (fe.getRaceSessions() != null)
                                    fe.getRaceSessions().forEach(s -> working.addSession(s, false));
                            }
                        } else {
                            br.com.rsousa.pojo.Session s =
                                    tr.processQualify(file, teamsCopy, hardDnf, isSelective);
                            working.addSession(s, isSelective);
                        }
                    } catch (Exception e) {
                        System.err.println("Erro ao processar " + file.getName() + ": " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                // Deduplica por nome mantendo o melhor tempo, depois ordena e reatribui posições
                if (working.getQualifySession() != null) {
                    java.util.Map<String, br.com.rsousa.pojo.Driver> best = new java.util.LinkedHashMap<>();
                    for (br.com.rsousa.pojo.Driver d : working.getQualifySession().drivers()) {
                        if (d.getBestLapMilliseconds() == null || d.getBestLapMilliseconds() <= 0) continue;
                        br.com.rsousa.pojo.Driver prev = best.get(d.getName());
                        if (prev == null || d.getBestLapMilliseconds() < prev.getBestLapMilliseconds()) {
                            best.put(d.getName(), d);
                        }
                    }
                    List<br.com.rsousa.pojo.Driver> sorted = new ArrayList<>(best.values());
                    sorted.sort(java.util.Comparator.comparingLong(br.com.rsousa.pojo.Driver::getBestLapMilliseconds));
                    for (int j = 0; j < sorted.size(); j++) {
                        sorted.get(j).setPosition(j + 1);
                        sorted.get(j).setPolePosition(j == 0);
                    }
                    working.getQualifySession().drivers().clear();
                    working.getQualifySession().drivers().addAll(sorted);
                }

                return working;
            }
        };

        task.setOnSucceeded(e -> Platform.runLater(() -> {
            event = task.getValue();
            updateBatteryComboBox();
            showResults();
            if (event.getQualifySession() != null || !event.getRaceSessions().isEmpty()) {
                showResultsPanel();
            }
            hideLoading();
        }));

        task.setOnFailed(e -> Platform.runLater(() -> {
            hideLoading();
            Throwable ex = task.getException();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao processar arquivo");
            alert.setHeaderText("Falha durante o processamento");
            alert.setContentText(ex != null ? ex.getMessage() : "Erro desconhecido");
            alert.showAndWait();
        }));

        showLoading(hasIRacingSelective ? "Consultando API do iRacing…" : "Processando…");
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    private SimulatorTransformer resolveTransformer(File file) {
        return switch (getFileExtension(file)) {
            case "xml", "XML" -> new RFactorTransformer();
            case "csv", "CSV" -> new IRacingCsvTransformer();
            case "json", "JSON" -> {
                if (isAssettoCorsaCompetizioneLog(file))  yield new AssettoCorsaCompetizioneTransformer();
                else if (isIRacingLog(file))              yield new IRacingJsonTransformer();
                else if (isAssettoCorsaLog(file))         yield new AssettoTransformer();
                else if (isAutomobilista2Log(file))       yield new Automobilista2Transformer();
                else                                      yield new EmptyTransformer();
            }
            default -> new EmptyTransformer();
        };
    }

    private void updateBatteryComboBox() {
        batteryComboBox.getItems().clear();
        for (int i = 1; i <= event.getRaceSessions().size(); i++) {
            batteryComboBox.getItems().add(i);
        }
        batteryComboBox.getSelectionModel().selectFirst();
    }

    private void showLoading(String message) {
        loadingFileLabel.setText(message);
        loadingProgressLabel.setText("");
        loadingDetailLabel.setText("");
        loadingOverlay.setVisible(true);
        loadingOverlay.setManaged(true);
    }

    private void hideLoading() {
        loadingOverlay.setVisible(false);
        loadingOverlay.setManaged(false);
    }



    private String getFileExtension(File file) {
        String fileName = file.getName();
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return ""; // empty extension
        }
        return fileName.substring(lastIndexOfDot + 1);
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
            Integer battery = batteryComboBox.getValue();
            if (battery == null) return;

            int selectedIndex = raceTableView.getSelectionModel().getSelectedIndex();

            raceTableView.getItems().clear();
            event.getRaceSessions().forEach(Session::sortDrivers);
            raceTableView.getItems().addAll(event.getRaceSessions().get(battery - 1).drivers());

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
