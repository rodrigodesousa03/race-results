package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.SerializedName;

   
public class SessionSplits {

   @SerializedName("subsession_id")
   Integer subsessionId;

   @SerializedName("event_strength_of_field")
   Integer eventStrengthOfField;


    public void setSubsessionId(Integer subsessionId) {
        this.subsessionId = subsessionId;
    }
    public Integer getSubsessionId() {
        return subsessionId;
    }
    
    public void setEventStrengthOfField(Integer eventStrengthOfField) {
        this.eventStrengthOfField = eventStrengthOfField;
    }
    public Integer getEventStrengthOfField() {
        return eventStrengthOfField;
    }
    
}