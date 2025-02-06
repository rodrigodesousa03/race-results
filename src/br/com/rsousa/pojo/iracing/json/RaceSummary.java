package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.SerializedName;

   
public class RaceSummary {

   @SerializedName("subsession_id")
   Integer subsessionId;

   @SerializedName("average_lap")
   Integer averageLap;

   @SerializedName("laps_complete")
   Integer lapsComplete;

   @SerializedName("num_cautions")
   Integer numCautions;

   @SerializedName("num_caution_laps")
   Integer numCautionLaps;

   @SerializedName("num_lead_changes")
   Integer numLeadChanges;

   @SerializedName("field_strength")
   Integer fieldStrength;

   @SerializedName("heat_info_id")
   Integer heatInfoId;

   @SerializedName("num_opt_laps")
   Integer numOptLaps;

   @SerializedName("has_opt_path")
   boolean hasOptPath;

   @SerializedName("special_event_type")
   Integer specialEventType;

   @SerializedName("special_event_type_text")
   String specialEventTypeText;


    public void setSubsessionId(Integer subsessionId) {
        this.subsessionId = subsessionId;
    }
    public Integer getSubsessionId() {
        return subsessionId;
    }
    
    public void setAverageLap(Integer averageLap) {
        this.averageLap = averageLap;
    }
    public Integer getAverageLap() {
        return averageLap;
    }
    
    public void setLapsComplete(Integer lapsComplete) {
        this.lapsComplete = lapsComplete;
    }
    public Integer getLapsComplete() {
        return lapsComplete;
    }
    
    public void setNumCautions(Integer numCautions) {
        this.numCautions = numCautions;
    }
    public Integer getNumCautions() {
        return numCautions;
    }
    
    public void setNumCautionLaps(Integer numCautionLaps) {
        this.numCautionLaps = numCautionLaps;
    }
    public Integer getNumCautionLaps() {
        return numCautionLaps;
    }
    
    public void setNumLeadChanges(Integer numLeadChanges) {
        this.numLeadChanges = numLeadChanges;
    }
    public Integer getNumLeadChanges() {
        return numLeadChanges;
    }
    
    public void setFieldStrength(Integer fieldStrength) {
        this.fieldStrength = fieldStrength;
    }
    public Integer getFieldStrength() {
        return fieldStrength;
    }
    
    public void setHeatInfoId(Integer heatInfoId) {
        this.heatInfoId = heatInfoId;
    }
    public Integer getHeatInfoId() {
        return heatInfoId;
    }
    
    public void setNumOptLaps(Integer numOptLaps) {
        this.numOptLaps = numOptLaps;
    }
    public Integer getNumOptLaps() {
        return numOptLaps;
    }
    
    public void setHasOptPath(boolean hasOptPath) {
        this.hasOptPath = hasOptPath;
    }
    public boolean getHasOptPath() {
        return hasOptPath;
    }
    
    public void setSpecialEventType(Integer specialEventType) {
        this.specialEventType = specialEventType;
    }
    public Integer getSpecialEventType() {
        return specialEventType;
    }
    
    public void setSpecialEventTypeText(String specialEventTypeText) {
        this.specialEventTypeText = specialEventTypeText;
    }
    public String getSpecialEventTypeText() {
        return specialEventTypeText;
    }
    
}