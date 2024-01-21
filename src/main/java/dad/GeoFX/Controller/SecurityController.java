package dad.GeoFX.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import dad.GeoFX.model.Ipapi;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class SecurityController implements Initializable {
	
	// controllers
	
	// model
	private StringProperty escudo = new SimpleStringProperty();
	private StringProperty potential = new SimpleStringProperty();
	private StringProperty threat = new SimpleStringProperty();
	private BooleanProperty crawler = new SimpleBooleanProperty();
	private BooleanProperty proxy = new SimpleBooleanProperty();
	private BooleanProperty tor = new SimpleBooleanProperty();
	
	// view
    @FXML
    private Label escudoLabel;
    @FXML
    private Label potentialLabel;
    @FXML
    private Label threatLabel;
	@FXML
    private CheckBox crawlerCheckBox;
    @FXML
    private CheckBox proxyCheckBox;
    @FXML
    private CheckBox torCheckBox;

	@FXML
	private BorderPane view;	
	
	public BorderPane getView() {
		return view;
	}
	
	public SecurityController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SecurityView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// bindeos
		escudoLabel.textProperty().bindBidirectional(escudo);
		potentialLabel.textProperty().bindBidirectional(potential);
		threatLabel.textProperty().bindBidirectional(threat);
		crawlerCheckBox.selectedProperty().bindBidirectional(crawler);
		proxyCheckBox.selectedProperty().bindBidirectional(proxy);
		torCheckBox.selectedProperty().bindBidirectional(tor);
		
		threat.addListener((o, ov, nv) -> {
			if (nv.toLowerCase() == "low") {
				escudo.set("This IP is safe. No threats have been detected.");
			} else if (nv.toLowerCase() == "medium") {
				escudo.set("Potential threats detected. This IP may be unsafe.");
			} else if (nv.toLowerCase() == "high") {
				escudo.set("Definitive threats detected. This IP is unsafe.");
			} else {
				escudo.set("Threat level unknown.");
			}
		});
	}
	
	public void load(Ipapi result) {
		crawler.set(result.getSecurity().getIsCrawler());
		proxy.set(result.getSecurity().getIsProxy());
		tor.set(result.getSecurity().getIsTor());
		threat.set(result.getSecurity().getThreatLevel());
		
		if (result.getSecurity().getThreatTypes() != null) {
			potential.set(result.getSecurity().getThreatTypes().stream().collect(Collectors.joining(", ")));
		} else {potential.set("No threats have been detected for this IP address.");}
	}
}