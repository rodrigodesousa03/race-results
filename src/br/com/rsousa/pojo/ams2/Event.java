
package br.com.rsousa.pojo.ams2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("attributes")
    @Expose
    private Attributes attributes;

    @SerializedName("event_name")
    @Expose
    private String eventName;

    @SerializedName("is_player")
    @Expose
    private Boolean isPlayer;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("participantid")
    @Expose
    private Integer participantid;

    @SerializedName("refid")
    @Expose
    private Integer refid;

    @SerializedName("time")
    @Expose
    private Integer time;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Boolean getIsPlayer() {
        return isPlayer;
    }

    public void setIsPlayer(Boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParticipantid() {
        return participantid;
    }

    public void setParticipantid(Integer participantid) {
        this.participantid = participantid;
    }

    public Integer getRefid() {
        return refid;
    }

    public void setRefid(Integer refid) {
        this.refid = refid;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}
