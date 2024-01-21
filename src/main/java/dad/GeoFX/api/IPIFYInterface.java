package dad.GeoFX.api;

import dad.GeoFX.model.Ipify;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IPIFYInterface {
	
	@GET("?format=json")
	public Call<Ipify> getIP();

}