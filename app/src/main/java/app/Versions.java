package app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import model.AndroidVersion;

/**
 * Created by student13 on 10/27/2016.
 */

public class Versions {

    @SerializedName("versions")
    private List<AndroidVersion> versions = new ArrayList<AndroidVersion>();
    /**
     *
     * @return
     *     The versions
     */
    public List<AndroidVersion> getVersions() {
        return versions;
    }
    /**
     *
     * @param versions
     *     The versions
     */
    public void setVersions(List<AndroidVersion> versions) {
        this.versions = versions;
    }
}
