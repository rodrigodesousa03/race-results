package application;
	
import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
		
		Scene scene = new Scene(root, 1280, 750);
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setMinWidth(1200);
		primaryStage.setMinHeight(700);
		primaryStage.setResizable(true);
		primaryStage.setTitle("Race Results");
		primaryStage.setMaximized(true); // Inicia maximizado
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
