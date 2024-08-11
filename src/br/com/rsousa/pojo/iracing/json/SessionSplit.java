
package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionSplit {

    @SerializedName("subsession_id")
    @Expose
    private Integer subsessionId;
    @SerializedName("event_strength_of_field")
    @Expose
    private Integer eventStrengthOfField;

    public Integer getSubsessionId() {
        return subsessionId;
    }

    public void setSubsessionId(Integer subsessionId) {
        this.subsessionId = subsessionId;
    }

    public Integer getEventStrengthOfField() {
        return eventStrengthOfField;
    }

    public void setEventStrengthOfField(Integer eventStrengthOfField) {
        this.eventStrengthOfField = eventStrengthOfField;
    }

}
