
package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("config_name")
    @Expose
    private String configName;
    @SerializedName("track_id")
    @Expose
    private Integer trackId;
    @SerializedName("track_name")
    @Expose
    private String trackName;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

}
