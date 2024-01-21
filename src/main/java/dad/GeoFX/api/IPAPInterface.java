package dad.GeoFX.api;

import dad.GeoFX.model.Ipapi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPAPInterface {
	
	@GET("ip_api.php")
	public Call<Ipapi> getIPData(@Query("ip") String ip);

}