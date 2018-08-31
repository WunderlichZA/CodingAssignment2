package viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import model.AndroidVersion;
import rest.RetroClient;

public class VersionsViewModel extends ViewModel {

    private LiveData<List<AndroidVersion>> listLiveData;
    private RetroClient client = new RetroClient();

    public LiveData<List<AndroidVersion>> getListLiveData() {
        if(listLiveData == null){
            listLiveData = client.getAndroidVersions();
        }
        return listLiveData;
    }
}


