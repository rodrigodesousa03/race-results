
package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("allow_fog")
    @Expose
    private Boolean allowFog;
    @SerializedName("fog")
    @Expose
    private Integer fog;
    @SerializedName("precip_mm2hr_before_final_session")
    @Expose
    private Integer precipMm2hrBeforeFinalSession;
    @SerializedName("precip_mm_final_session")
    @Expose
    private Integer precipMmFinalSession;
    @SerializedName("precip_option")
    @Expose
    private Integer precipOption;
    @SerializedName("precip_time_pct")
    @Expose
    private Integer precipTimePct;
    @SerializedName("rel_humidity")
    @Expose
    private Integer relHumidity;
    @SerializedName("simulated_start_utc_offset")
    @Expose
    private Integer simulatedStartUtcOffset;
    @SerializedName("simulated_start_utc_time")
    @Expose
    private String simulatedStartUtcTime;
    @SerializedName("skies")
    @Expose
    private Integer skies;
    @SerializedName("temp_units")
    @Expose
    private Integer tempUnits;
    @SerializedName("temp_value")
    @Expose
    private Integer tempValue;
    @SerializedName("time_of_day")
    @Expose
    private Integer timeOfDay;
    @SerializedName("track_water")
    @Expose
    private Integer trackWater;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("weather_var_initial")
    @Expose
    private Integer weatherVarInitial;
    @SerializedName("weather_var_ongoing")
    @Expose
    private Integer weatherVarOngoing;
    @SerializedName("wind_dir")
    @Expose
    private Integer windDir;
    @SerializedName("wind_units")
    @Expose
    private Integer windUnits;
    @SerializedName("wind_value")
    @Expose
    private Integer windValue;

    public Boolean getAllowFog() {
        return allowFog;
    }

    public void setAllowFog(Boolean allowFog) {
        this.allowFog = allowFog;
    }

    public Integer getFog() {
        return fog;
    }

    public void setFog(Integer fog) {
        this.fog = fog;
    }

    public Integer getPrecipMm2hrBeforeFinalSession() {
        return precipMm2hrBeforeFinalSession;
    }

    public void setPrecipMm2hrBeforeFinalSession(Integer precipMm2hrBeforeFinalSession) {
        this.precipMm2hrBeforeFinalSession = precipMm2hrBeforeFinalSession;
    }

    public Integer getPrecipMmFinalSession() {
        return precipMmFinalSession;
    }

    public void setPrecipMmFinalSession(Integer precipMmFinalSession) {
        this.precipMmFinalSession = precipMmFinalSession;
    }

    public Integer getPrecipOption() {
        return precipOption;
    }

    public void setPrecipOption(Integer precipOption) {
        this.precipOption = precipOption;
    }

    public Integer getPrecipTimePct() {
        return precipTimePct;
    }

    public void setPrecipTimePct(Integer precipTimePct) {
        this.precipTimePct = precipTimePct;
    }

    public Integer getRelHumidity() {
        return relHumidity;
    }

    public void setRelHumidity(Integer relHumidity) {
        this.relHumidity = relHumidity;
    }

    public Integer getSimulatedStartUtcOffset() {
        return simulatedStartUtcOffset;
    }

    public void setSimulatedStartUtcOffset(Integer simulatedStartUtcOffset) {
        this.simulatedStartUtcOffset = simulatedStartUtcOffset;
    }

    public String getSimulatedStartUtcTime() {
        return simulatedStartUtcTime;
    }

    public void setSimulatedStartUtcTime(String simulatedStartUtcTime) {
        this.simulatedStartUtcTime = simulatedStartUtcTime;
    }

    public Integer getSkies() {
        return skies;
    }

    public void setSkies(Integer skies) {
        this.skies = skies;
    }

    public Integer getTempUnits() {
        return tempUnits;
    }

    public void setTempUnits(Integer tempUnits) {
        this.tempUnits = tempUnits;
    }

    public Integer getTempValue() {
        return tempValue;
    }

    public void setTempValue(Integer tempValue) {
        this.tempValue = tempValue;
    }

    public Integer getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(Integer timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public Integer getTrackWater() {
        return trackWater;
    }

    public void setTrackWater(Integer trackWater) {
        this.trackWater = trackWater;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getWeatherVarInitial() {
        return weatherVarInitial;
    }

    public void setWeatherVarInitial(Integer weatherVarInitial) {
        this.weatherVarInitial = weatherVarInitial;
    }

    public Integer getWeatherVarOngoing() {
        return weatherVarOngoing;
    }

    public void setWeatherVarOngoing(Integer weatherVarOngoing) {
        this.weatherVarOngoing = weatherVarOngoing;
    }

    public Integer getWindDir() {
        return windDir;
    }

    public void setWindDir(Integer windDir) {
        this.windDir = windDir;
    }

    public Integer getWindUnits() {
        return windUnits;
    }

    public void setWindUnits(Integer windUnits) {
        this.windUnits = windUnits;
    }

    public Integer getWindValue() {
        return windValue;
    }

    public void setWindValue(Integer windValue) {
        this.windValue = windValue;
    }

}
