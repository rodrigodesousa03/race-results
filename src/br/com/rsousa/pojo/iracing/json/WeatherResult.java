
package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResult {

    @SerializedName("avg_skies")
    @Expose
    private Integer avgSkies;
    @SerializedName("avg_cloud_cover_pct")
    @Expose
    private Double avgCloudCoverPct;
    @SerializedName("min_cloud_cover_pct")
    @Expose
    private Double minCloudCoverPct;
    @SerializedName("max_cloud_cover_pct")
    @Expose
    private Double maxCloudCoverPct;
    @SerializedName("temp_units")
    @Expose
    private Integer tempUnits;
    @SerializedName("avg_temp")
    @Expose
    private Double avgTemp;
    @SerializedName("min_temp")
    @Expose
    private Double minTemp;
    @SerializedName("max_temp")
    @Expose
    private Double maxTemp;
    @SerializedName("avg_rel_humidity")
    @Expose
    private Double avgRelHumidity;
    @SerializedName("wind_units")
    @Expose
    private Integer windUnits;
    @SerializedName("avg_wind_speed")
    @Expose
    private Double avgWindSpeed;
    @SerializedName("min_wind_speed")
    @Expose
    private Double minWindSpeed;
    @SerializedName("max_wind_speed")
    @Expose
    private Double maxWindSpeed;
    @SerializedName("avg_wind_dir")
    @Expose
    private Integer avgWindDir;
    @SerializedName("max_fog")
    @Expose
    private Integer maxFog;
    @SerializedName("fog_time_pct")
    @Expose
    private Integer fogTimePct;
    @SerializedName("precip_time_pct")
    @Expose
    private Integer precipTimePct;
    @SerializedName("precip_mm")
    @Expose
    private Integer precipMm;
    @SerializedName("precip_mm2hr_before_session")
    @Expose
    private Integer precipMm2hrBeforeSession;
    @SerializedName("simulated_start_time")
    @Expose
    private String simulatedStartTime;

    public Integer getAvgSkies() {
        return avgSkies;
    }

    public void setAvgSkies(Integer avgSkies) {
        this.avgSkies = avgSkies;
    }

    public Double getAvgCloudCoverPct() {
        return avgCloudCoverPct;
    }

    public void setAvgCloudCoverPct(Double avgCloudCoverPct) {
        this.avgCloudCoverPct = avgCloudCoverPct;
    }

    public Double getMinCloudCoverPct() {
        return minCloudCoverPct;
    }

    public void setMinCloudCoverPct(Double minCloudCoverPct) {
        this.minCloudCoverPct = minCloudCoverPct;
    }

    public Double getMaxCloudCoverPct() {
        return maxCloudCoverPct;
    }

    public void setMaxCloudCoverPct(Double maxCloudCoverPct) {
        this.maxCloudCoverPct = maxCloudCoverPct;
    }

    public Integer getTempUnits() {
        return tempUnits;
    }

    public void setTempUnits(Integer tempUnits) {
        this.tempUnits = tempUnits;
    }

    public Double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(Double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getAvgRelHumidity() {
        return avgRelHumidity;
    }

    public void setAvgRelHumidity(Double avgRelHumidity) {
        this.avgRelHumidity = avgRelHumidity;
    }

    public Integer getWindUnits() {
        return windUnits;
    }

    public void setWindUnits(Integer windUnits) {
        this.windUnits = windUnits;
    }

    public Double getAvgWindSpeed() {
        return avgWindSpeed;
    }

    public void setAvgWindSpeed(Double avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }

    public Double getMinWindSpeed() {
        return minWindSpeed;
    }

    public void setMinWindSpeed(Double minWindSpeed) {
        this.minWindSpeed = minWindSpeed;
    }

    public Double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(Double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public Integer getAvgWindDir() {
        return avgWindDir;
    }

    public void setAvgWindDir(Integer avgWindDir) {
        this.avgWindDir = avgWindDir;
    }

    public Integer getMaxFog() {
        return maxFog;
    }

    public void setMaxFog(Integer maxFog) {
        this.maxFog = maxFog;
    }

    public Integer getFogTimePct() {
        return fogTimePct;
    }

    public void setFogTimePct(Integer fogTimePct) {
        this.fogTimePct = fogTimePct;
    }

    public Integer getPrecipTimePct() {
        return precipTimePct;
    }

    public void setPrecipTimePct(Integer precipTimePct) {
        this.precipTimePct = precipTimePct;
    }

    public Integer getPrecipMm() {
        return precipMm;
    }

    public void setPrecipMm(Integer precipMm) {
        this.precipMm = precipMm;
    }

    public Integer getPrecipMm2hrBeforeSession() {
        return precipMm2hrBeforeSession;
    }

    public void setPrecipMm2hrBeforeSession(Integer precipMm2hrBeforeSession) {
        this.precipMm2hrBeforeSession = precipMm2hrBeforeSession;
    }

    public String getSimulatedStartTime() {
        return simulatedStartTime;
    }

    public void setSimulatedStartTime(String simulatedStartTime) {
        this.simulatedStartTime = simulatedStartTime;
    }

}
