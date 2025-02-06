package br.com.rsousa.pojo.iracing.json;
import java.util.List;

import com.google.gson.annotations.SerializedName;

   
public class SessionResult {

   @SerializedName("simsession_number")
   Integer simsessionNumber;

   @SerializedName("simsession_name")
   String simsessionName;

   @SerializedName("simsession_type")
   Integer simsessionType;

   @SerializedName("simsession_type_name")
   String simsessionTypeName;

   @SerializedName("simsession_subtype")
   Integer simsessionSubtype;

   @SerializedName("weather_result")
   WeatherResult weatherResult;

   @SerializedName("results")
   List<Result> results;


    public void setSimsessionNumber(Integer simsessionNumber) {
        this.simsessionNumber = simsessionNumber;
    }
    public Integer getSimsessionNumber() {
        return simsessionNumber;
    }
    
    public void setSimsessionName(String simsessionName) {
        this.simsessionName = simsessionName;
    }
    public String getSimsessionName() {
        return simsessionName;
    }
    
    public void setSimsessionType(Integer simsessionType) {
        this.simsessionType = simsessionType;
    }
    public Integer getSimsessionType() {
        return simsessionType;
    }
    
    public void setSimsessionTypeName(String simsessionTypeName) {
        this.simsessionTypeName = simsessionTypeName;
    }
    public String getSimsessionTypeName() {
        return simsessionTypeName;
    }
    
    public void setSimsessionSubtype(Integer simsessionSubtype) {
        this.simsessionSubtype = simsessionSubtype;
    }
    public Integer getSimsessionSubtype() {
        return simsessionSubtype;
    }
    
    public void setWeatherResult(WeatherResult weatherResult) {
        this.weatherResult = weatherResult;
    }
    public WeatherResult getWeatherResult() {
        return weatherResult;
    }
    
    public void setResults(List<Result> results) {
        this.results = results;
    }
    public List<Result> getResults() {
        return results;
    }
    
}