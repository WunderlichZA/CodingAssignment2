package rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by student13 on 10/27/2016.
 */

public class RetroClient {

    /********
     * URLS
     *******/
    public static final String ROOT_URL = "http://codetest.cobi.co.za";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService(){
        return getRetrofitInstance().create(ApiService.class);
    }
}
