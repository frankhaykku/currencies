package app.currencies.api;

import app.currencies.model.RestResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("latest?")
    Observable<RestResponse> getRatesByName(@Query("base") String name);
}
