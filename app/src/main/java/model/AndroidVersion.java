package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by student13 on 10/26/2016.
 */

public class AndroidVersion implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("version")
    private String version;
    @SerializedName("released")
    private String released;
    @SerializedName("api")
    private String api;
    @SerializedName("image")
    private String image;

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The version
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     *     The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return
     *     The released
     */
    public String getReleased() {
        return released;
    }

    /**
     *
     * @param released
     *     The released
     */
    public void setReleased(String released) {
        this.released = released;
    }

    /**
     *
     * @return
     *     The api
     */
    public String getApi() {
        return api;
    }

    /**
     *
     * @param api
     *     The api
     */
    public void setApi(String api) {
        this.api = api;
    }

    /**
     *
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }
}
