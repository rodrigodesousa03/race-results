package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.SerializedName;

   
public class Track {

   @SerializedName("category")
   String category;

   @SerializedName("category_id")
   Integer categoryId;

   @SerializedName("config_name")
   String configName;

   @SerializedName("track_id")
   Integer trackId;

   @SerializedName("track_name")
   String trackName;


    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }
    
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    
    public void setConfigName(String configName) {
        this.configName = configName;
    }
    public String getConfigName() {
        return configName;
    }
    
    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }
    public Integer getTrackId() {
        return trackId;
    }
    
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    public String getTrackName() {
        return trackName;
    }
    
}