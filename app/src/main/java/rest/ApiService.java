package rest;

import app.Versions;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by student13 on 10/27/2016.
 */

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */

    @GET("/androids.json")
    Call<Versions> getVersionsJSON();
}
