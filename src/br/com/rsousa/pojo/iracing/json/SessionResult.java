
package br.com.rsousa.pojo.iracing.json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionResult {

    @SerializedName("simsession_number")
    @Expose
    private Integer simsessionNumber;
    @SerializedName("simsession_type")
    @Expose
    private Integer simsessionType;
    @SerializedName("simsession_type_name")
    @Expose
    private String simsessionTypeName;
    @SerializedName("simsession_subtype")
    @Expose
    private Integer simsessionSubtype;
    @SerializedName("simsession_name")
    @Expose
    private String simsessionName;
    @SerializedName("weather_result")
    @Expose
    private WeatherResult weatherResult;
    @SerializedName("results")
    @Expose
    private List<Result> results;

    public Integer getSimsessionNumber() {
        return simsessionNumber;
    }

    public void setSimsessionNumber(Integer simsessionNumber) {
        this.simsessionNumber = simsessionNumber;
    }

    public Integer getSimsessionType() {
        return simsessionType;
    }

    public void setSimsessionType(Integer simsessionType) {
        this.simsessionType = simsessionType;
    }

    public String getSimsessionTypeName() {
        return simsessionTypeName;
    }

    public void setSimsessionTypeName(String simsessionTypeName) {
        this.simsessionTypeName = simsessionTypeName;
    }

    public Integer getSimsessionSubtype() {
        return simsessionSubtype;
    }

    public void setSimsessionSubtype(Integer simsessionSubtype) {
        this.simsessionSubtype = simsessionSubtype;
    }

    public String getSimsessionName() {
        return simsessionName;
    }

    public void setSimsessionName(String simsessionName) {
        this.simsessionName = simsessionName;
    }

    public WeatherResult getWeatherResult() {
        return weatherResult;
    }

    public void setWeatherResult(WeatherResult weatherResult) {
        this.weatherResult = weatherResult;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
