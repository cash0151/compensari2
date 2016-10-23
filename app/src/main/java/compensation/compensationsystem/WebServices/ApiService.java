package compensation.compensationsystem.WebServices;

import compensation.compensationsystem.WebServices.Requests.LoginRequest;
import compensation.compensationsystem.WebServices.Responses.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Irina.B on 20.10.2016.
 */
public interface ApiService
{
    @POST("login") // POST sau GET si numele serviciului
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);
}
