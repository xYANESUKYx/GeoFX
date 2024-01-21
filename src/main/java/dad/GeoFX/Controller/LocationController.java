package dad.GeoFX.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import dad.GeoFX.model.Ipapi;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class LocationController implements Initializable {
	
	// controllers
	
	// model
	private StringProperty callingCode = new SimpleStringProperty();
	private StringProperty city = new SimpleStringProperty();
	private StringProperty currency = new SimpleStringProperty();
	private StringProperty languaje = new SimpleStringProperty();
	private DoubleProperty latitude = new SimpleDoubleProperty();
	private StringProperty locationXD = new SimpleStringProperty();
	private DoubleProperty longitude = new SimpleDoubleProperty();
	private StringProperty timeZone = new SimpleStringProperty();
	private StringProperty zipCode = new SimpleStringProperty();
	
	// view
	@FXML
    private Label callingCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label currencyLabel;
    @FXML
    private Label languajeLabel;
    @FXML
    private Label latitudeLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label longitudeLabel;
    @FXML
    private Label timeZoneLabel;
    @FXML
    private Label zipCodeLabel;
    @FXML
    private ImageView banderaImage;


	@FXML
	private BorderPane view;	
	
	public BorderPane getView() {
		return view;
	}
	
	public LocationController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LocationView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// bindeos
		callingCodeLabel.textProperty().bindBidirectional(callingCode); 
		cityLabel.textProperty().bindBidirectional(city); 
		currencyLabel.textProperty().bindBidirectional(currency); 
		languajeLabel.textProperty().bindBidirectional(languaje); 
		locationLabel.textProperty().bindBidirectional(locationXD); 
		timeZoneLabel.textProperty().bindBidirectional(timeZone); 
		zipCodeLabel.textProperty().bindBidirectional(zipCode); 
		latitudeLabel.textProperty().bind(latitude.asString("%1.6f"));
		longitudeLabel.textProperty().bind(longitude.asString("%1.6f"));
	}

	public void load(Ipapi result) {
		latitude.set(result.getLatitude());				
		longitude.set(result.getLongitude());
		banderaImage.setImage(new Image("/images/banderas/" + result.getCountryCode() + ".png"));
		locationXD.set("  " + result.getCountryName() + " (" + result.getCountryCode() + ")");
		city.set(result.getCity() + " (" + result.getRegionName() + ")");     
		zipCode.set(result.getZip());
		languaje.set(result.getLocation().getLanguages().get(0).getName() + " (" + result.getLocation().getLanguages().get(0).getCode().toUpperCase() + ")");
		timeZone.set(result.getTimeZone().getCode());
		callingCode.set("+" + result.getLocation().getCallingCode());
		currency.set(result.getCurrency().getName() + " (" + result.getCurrency().getSymbol() + ")");
	}
}