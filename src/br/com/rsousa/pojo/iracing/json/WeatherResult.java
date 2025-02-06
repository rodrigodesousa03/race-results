package br.com.rsousa.pojo.iracing.json;
import java.time.LocalDateTime;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

   
public class WeatherResult {

   @SerializedName("avg_skies")
   Integer avgSkies;

   @SerializedName("avg_cloud_cover_pct")
   double avgCloudCoverPct;

   @SerializedName("min_cloud_cover_pct")
   double minCloudCoverPct;

   @SerializedName("max_cloud_cover_pct")
   double maxCloudCoverPct;

   @SerializedName("temp_units")
   Integer tempUnits;

   @SerializedName("avg_temp")
   double avgTemp;

   @SerializedName("min_temp")
   double minTemp;

   @SerializedName("max_temp")
   double maxTemp;

   @SerializedName("avg_rel_humidity")
   double avgRelHumidity;

   @SerializedName("wind_units")
   Integer windUnits;

   @SerializedName("avg_wind_speed")
   double avgWindSpeed;

   @SerializedName("min_wind_speed")
   double minWindSpeed;

   @SerializedName("max_wind_speed")
   double maxWindSpeed;

   @SerializedName("avg_wind_dir")
   Integer avgWindDir;

   @SerializedName("max_fog")
   Integer maxFog;

   @SerializedName("fog_time_pct")
   Integer fogTimePct;

   @SerializedName("precip_time_pct")
   Integer precipTimePct;

   @SerializedName("precip_mm")
   Integer precipMm;

   @SerializedName("precip_mm2hr_before_session")
   Integer precipMm2hrBeforeSession;

   @SerializedName("simulated_start_time")
   String simulatedStartTime;


    public void setAvgSkies(Integer avgSkies) {
        this.avgSkies = avgSkies;
    }
    public Integer getAvgSkies() {
        return avgSkies;
    }
    
    public void setAvgCloudCoverPct(double avgCloudCoverPct) {
        this.avgCloudCoverPct = avgCloudCoverPct;
    }
    public double getAvgCloudCoverPct() {
        return avgCloudCoverPct;
    }
    
    public void setMinCloudCoverPct(double minCloudCoverPct) {
        this.minCloudCoverPct = minCloudCoverPct;
    }
    public double getMinCloudCoverPct() {
        return minCloudCoverPct;
    }
    
    public void setMaxCloudCoverPct(double maxCloudCoverPct) {
        this.maxCloudCoverPct = maxCloudCoverPct;
    }
    public double getMaxCloudCoverPct() {
        return maxCloudCoverPct;
    }
    
    public void setTempUnits(Integer tempUnits) {
        this.tempUnits = tempUnits;
    }
    public Integer getTempUnits() {
        return tempUnits;
    }
    
    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }
    public double getAvgTemp() {
        return avgTemp;
    }
    
    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }
    public double getMinTemp() {
        return minTemp;
    }
    
    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }
    public double getMaxTemp() {
        return maxTemp;
    }
    
    public void setAvgRelHumidity(double avgRelHumidity) {
        this.avgRelHumidity = avgRelHumidity;
    }
    public double getAvgRelHumidity() {
        return avgRelHumidity;
    }
    
    public void setWindUnits(Integer windUnits) {
        this.windUnits = windUnits;
    }
    public Integer getWindUnits() {
        return windUnits;
    }
    
    public void setAvgWindSpeed(double avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }
    public double getAvgWindSpeed() {
        return avgWindSpeed;
    }
    
    public void setMinWindSpeed(double minWindSpeed) {
        this.minWindSpeed = minWindSpeed;
    }
    public double getMinWindSpeed() {
        return minWindSpeed;
    }
    
    public void setMaxWindSpeed(double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }
    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }
    
    public void setAvgWindDir(Integer avgWindDir) {
        this.avgWindDir = avgWindDir;
    }
    public Integer getAvgWindDir() {
        return avgWindDir;
    }
    
    public void setMaxFog(Integer maxFog) {
        this.maxFog = maxFog;
    }
    public Integer getMaxFog() {
        return maxFog;
    }
    
    public void setFogTimePct(Integer fogTimePct) {
        this.fogTimePct = fogTimePct;
    }
    public Integer getFogTimePct() {
        return fogTimePct;
    }
    
    public void setPrecipTimePct(Integer precipTimePct) {
        this.precipTimePct = precipTimePct;
    }
    public Integer getPrecipTimePct() {
        return precipTimePct;
    }
    
    public void setPrecipMm(Integer precipMm) {
        this.precipMm = precipMm;
    }
    public Integer getPrecipMm() {
        return precipMm;
    }
    
    public void setPrecipMm2hrBeforeSession(Integer precipMm2hrBeforeSession) {
        this.precipMm2hrBeforeSession = precipMm2hrBeforeSession;
    }
    public Integer getPrecipMm2hrBeforeSession() {
        return precipMm2hrBeforeSession;
    }
    
    public void setSimulatedStartTime(String simulatedStartTime) {
        this.simulatedStartTime = simulatedStartTime;
    }
    public String getSimulatedStartTime() {
        return simulatedStartTime;
    }
    
}