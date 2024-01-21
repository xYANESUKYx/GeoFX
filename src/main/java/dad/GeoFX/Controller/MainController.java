package dad.GeoFX.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import dad.GeoFX.api.IPAPIService;
import dad.GeoFX.api.IPIFYService;
import dad.GeoFX.model.Ipapi;
import dad.GeoFX.model.Ipify;
import javafx.concurrent.Task;

public class MainController implements Initializable {
	
	// controllers
	private LocationController locationC = new LocationController();
	private ConnectionController connection = new ConnectionController();
	private SecurityController security = new SecurityController();
	private IPIFYService ipifyService = new IPIFYService();
	private IPAPIService ipapiService = new IPAPIService();
	
	// model
	private StringProperty ip = new SimpleStringProperty();
	
	// view    
	@FXML
    private Button checkButton;
    @FXML
    private Tab connectionTab;
    @FXML
    private TextField ipText;
    @FXML
    private Tab locationTab;
    @FXML
    private Tab securityTab;
    @FXML
	private BorderPane view;	
	
	public BorderPane getView() {
		return view;
	}
	
	public MainController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//bindeos
		ipText.textProperty().bindBidirectional(ip); 

		locationTab.setContent(locationC.getView());
		connectionTab.setContent(connection.getView());
		securityTab.setContent(security.getView());

		loadIP();
	}
	
	@FXML
    void onCheckIpAction(ActionEvent event) {
		Task<Ipapi> task = new Task<Ipapi>() {
			protected Ipapi call() throws Exception {
				return ipapiService.getIPData(ip.get());
			}
		};
		task.setOnSucceeded(taskEvent -> {
			Ipapi result = task.getValue();
			locationC.load(result);
			connection.load(result);
			security.load(result);
		});
		task.setOnFailed(taskEvent -> taskEvent.getSource().getException().printStackTrace());
		new Thread(task).start();
    }
	
	public void loadIP() {
		Task<Ipify> task = new Task<Ipify>() {
			protected Ipify call() throws Exception {
				return ipifyService.getIP();
			}
		};
		task.setOnSucceeded(event -> {
			ip.set(task.getValue().getIp());
			onCheckIpAction(null);
		});
		task.setOnFailed(event -> event.getSource().getException().printStackTrace());
		new Thread(task).start();
	}
}
