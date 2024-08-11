
package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RaceSummary {

    @SerializedName("subsession_id")
    @Expose
    private Integer subsessionId;
    @SerializedName("average_lap")
    @Expose
    private Integer averageLap;
    @SerializedName("laps_complete")
    @Expose
    private Integer lapsComplete;
    @SerializedName("num_cautions")
    @Expose
    private Integer numCautions;
    @SerializedName("num_caution_laps")
    @Expose
    private Integer numCautionLaps;
    @SerializedName("num_lead_changes")
    @Expose
    private Integer numLeadChanges;
    @SerializedName("field_strength")
    @Expose
    private Integer fieldStrength;
    @SerializedName("heat_info_id")
    @Expose
    private Integer heatInfoId;
    @SerializedName("num_opt_laps")
    @Expose
    private Integer numOptLaps;
    @SerializedName("has_opt_path")
    @Expose
    private Boolean hasOptPath;
    @SerializedName("special_event_type")
    @Expose
    private Integer specialEventType;
    @SerializedName("special_event_type_text")
    @Expose
    private String specialEventTypeText;

    public Integer getSubsessionId() {
        return subsessionId;
    }

    public void setSubsessionId(Integer subsessionId) {
        this.subsessionId = subsessionId;
    }

    public Integer getAverageLap() {
        return averageLap;
    }

    public void setAverageLap(Integer averageLap) {
        this.averageLap = averageLap;
    }

    public Integer getLapsComplete() {
        return lapsComplete;
    }

    public void setLapsComplete(Integer lapsComplete) {
        this.lapsComplete = lapsComplete;
    }

    public Integer getNumCautions() {
        return numCautions;
    }

    public void setNumCautions(Integer numCautions) {
        this.numCautions = numCautions;
    }

    public Integer getNumCautionLaps() {
        return numCautionLaps;
    }

    public void setNumCautionLaps(Integer numCautionLaps) {
        this.numCautionLaps = numCautionLaps;
    }

    public Integer getNumLeadChanges() {
        return numLeadChanges;
    }

    public void setNumLeadChanges(Integer numLeadChanges) {
        this.numLeadChanges = numLeadChanges;
    }

    public Integer getFieldStrength() {
        return fieldStrength;
    }

    public void setFieldStrength(Integer fieldStrength) {
        this.fieldStrength = fieldStrength;
    }

    public Integer getHeatInfoId() {
        return heatInfoId;
    }

    public void setHeatInfoId(Integer heatInfoId) {
        this.heatInfoId = heatInfoId;
    }

    public Integer getNumOptLaps() {
        return numOptLaps;
    }

    public void setNumOptLaps(Integer numOptLaps) {
        this.numOptLaps = numOptLaps;
    }

    public Boolean getHasOptPath() {
        return hasOptPath;
    }

    public void setHasOptPath(Boolean hasOptPath) {
        this.hasOptPath = hasOptPath;
    }

    public Integer getSpecialEventType() {
        return specialEventType;
    }

    public void setSpecialEventType(Integer specialEventType) {
        this.specialEventType = specialEventType;
    }

    public String getSpecialEventTypeText() {
        return specialEventTypeText;
    }

    public void setSpecialEventTypeText(String specialEventTypeText) {
        this.specialEventTypeText = specialEventTypeText;
    }

}
