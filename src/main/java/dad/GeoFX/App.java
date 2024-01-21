package dad.GeoFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

	private MainController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new MainController();

		primaryStage.setTitle("GeoFX.fxml");
		primaryStage.getIcons().add(new Image("/images/ico.png"));
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.show();
	}
}
