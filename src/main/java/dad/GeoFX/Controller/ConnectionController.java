package dad.GeoFX.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import dad.GeoFX.model.Ipapi;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ConnectionController implements Initializable {
	
	// controllers
	
	// model
	private StringProperty ans = new SimpleStringProperty();
	private StringProperty hostname = new SimpleStringProperty();
    private StringProperty ip = new SimpleStringProperty();
	private StringProperty isp = new SimpleStringProperty();
	private StringProperty type = new SimpleStringProperty();
	
	// view    
	@FXML
    private Label ansLabel;
    @FXML
    private Label hostnameLabel;
    @FXML
    private Label ipLabel;
    @FXML
    private Label ispLabel;
    @FXML
    private Label typeLabel;
	@FXML
	private BorderPane view;	
	
	public BorderPane getView() {
		return view;
	}
	
	public ConnectionController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConnectionView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// bindeos
		ansLabel.textProperty().bindBidirectional(ans); 
		hostnameLabel.textProperty().bindBidirectional(hostname); 
		ipLabel.textProperty().bindBidirectional(ip); 
		ispLabel.textProperty().bindBidirectional(isp); 
		typeLabel.textProperty().bindBidirectional(type); 
		
	}
	

	public void load(Ipapi result) {
		ip.set(result.getIp());
		isp.set(result.getConnection().getIsp());
		type.set(result.getType());
		ans.set(result.getConnection().getAsn().toString());
		hostname.set(result.getHostname());
	}
}