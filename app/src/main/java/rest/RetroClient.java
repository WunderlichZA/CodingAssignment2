package rest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import model.AndroidVersion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    private static Retrofit retrofit = null;

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public LiveData<List<AndroidVersion>> getAndroidVersions(){
        final MutableLiveData<List<AndroidVersion>> listMutableLiveData
                = new MutableLiveData<>();
        getRetrofitInstance().create(ApiService.class).getVersions()
                .enqueue(new Callback<List<AndroidVersion>>() {
                    @Override
                    public void onResponse(Call<List<AndroidVersion>> call, Response<List<AndroidVersion>> response) {
                        listMutableLiveData.postValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<AndroidVersion>> call, Throwable t) {
                        Log.e("RetrofitClient", "Error getting Versions");
                    }
                });
        return listMutableLiveData;
    }
}