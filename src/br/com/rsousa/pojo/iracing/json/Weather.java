package br.com.rsousa.pojo.iracing.json;
import java.time.LocalDateTime;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

   
public class Weather {

   @SerializedName("allow_fog")
   boolean allowFog;

   @SerializedName("fog")
   Integer fog;

   @SerializedName("precip_mm2hr_before_final_session")
   Integer precipMm2hrBeforeFinalSession;

   @SerializedName("precip_mm_final_session")
   Integer precipMmFinalSession;

   @SerializedName("precip_option")
   Integer precipOption;

   @SerializedName("precip_time_pct")
   Integer precipTimePct;

   @SerializedName("rel_humidity")
   Integer relHumidity;

   @SerializedName("simulated_start_time")
   String simulatedStartTime;

   @SerializedName("skies")
   Integer skies;

   @SerializedName("temp_units")
   Integer tempUnits;

   @SerializedName("temp_value")
   Integer tempValue;

   @SerializedName("time_of_day")
   Integer timeOfDay;

   @SerializedName("track_water")
   Integer trackWater;

   @SerializedName("type")
   Integer type;

   @SerializedName("version")
   Integer version;

   @SerializedName("weather_var_initial")
   Integer weatherVarInitial;

   @SerializedName("weather_var_ongoing")
   Integer weatherVarOngoing;

   @SerializedName("wind_dir")
   Integer windDir;

   @SerializedName("wind_units")
   Integer windUnits;

   @SerializedName("wind_value")
   Integer windValue;


    public void setAllowFog(boolean allowFog) {
        this.allowFog = allowFog;
    }
    public boolean getAllowFog() {
        return allowFog;
    }
    
    public void setFog(Integer fog) {
        this.fog = fog;
    }
    public Integer getFog() {
        return fog;
    }
    
    public void setPrecipMm2hrBeforeFinalSession(Integer precipMm2hrBeforeFinalSession) {
        this.precipMm2hrBeforeFinalSession = precipMm2hrBeforeFinalSession;
    }
    public Integer getPrecipMm2hrBeforeFinalSession() {
        return precipMm2hrBeforeFinalSession;
    }
    
    public void setPrecipMmFinalSession(Integer precipMmFinalSession) {
        this.precipMmFinalSession = precipMmFinalSession;
    }
    public Integer getPrecipMmFinalSession() {
        return precipMmFinalSession;
    }
    
    public void setPrecipOption(Integer precipOption) {
        this.precipOption = precipOption;
    }
    public Integer getPrecipOption() {
        return precipOption;
    }
    
    public void setPrecipTimePct(Integer precipTimePct) {
        this.precipTimePct = precipTimePct;
    }
    public Integer getPrecipTimePct() {
        return precipTimePct;
    }
    
    public void setRelHumidity(Integer relHumidity) {
        this.relHumidity = relHumidity;
    }
    public Integer getRelHumidity() {
        return relHumidity;
    }
    
    public void setSimulatedStartTime(String simulatedStartTime) {
        this.simulatedStartTime = simulatedStartTime;
    }
    public String getSimulatedStartTime() {
        return simulatedStartTime;
    }
    
    public void setSkies(Integer skies) {
        this.skies = skies;
    }
    public Integer getSkies() {
        return skies;
    }
    
    public void setTempUnits(Integer tempUnits) {
        this.tempUnits = tempUnits;
    }
    public Integer getTempUnits() {
        return tempUnits;
    }
    
    public void setTempValue(Integer tempValue) {
        this.tempValue = tempValue;
    }
    public Integer getTempValue() {
        return tempValue;
    }
    
    public void setTimeOfDay(Integer timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
    public Integer getTimeOfDay() {
        return timeOfDay;
    }
    
    public void setTrackWater(Integer trackWater) {
        this.trackWater = trackWater;
    }
    public Integer getTrackWater() {
        return trackWater;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getType() {
        return type;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
    public Integer getVersion() {
        return version;
    }
    
    public void setWeatherVarInitial(Integer weatherVarInitial) {
        this.weatherVarInitial = weatherVarInitial;
    }
    public Integer getWeatherVarInitial() {
        return weatherVarInitial;
    }
    
    public void setWeatherVarOngoing(Integer weatherVarOngoing) {
        this.weatherVarOngoing = weatherVarOngoing;
    }
    public Integer getWeatherVarOngoing() {
        return weatherVarOngoing;
    }
    
    public void setWindDir(Integer windDir) {
        this.windDir = windDir;
    }
    public Integer getWindDir() {
        return windDir;
    }
    
    public void setWindUnits(Integer windUnits) {
        this.windUnits = windUnits;
    }
    public Integer getWindUnits() {
        return windUnits;
    }
    
    public void setWindValue(Integer windValue) {
        this.windValue = windValue;
    }
    public Integer getWindValue() {
        return windValue;
    }
    
}