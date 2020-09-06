
package br.com.rsousa.pojo.acc;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Session {

    @SerializedName("sessionType")
    @Expose
    private String sessionType;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("sessionIndex")
    @Expose
    private Integer sessionIndex;
    @SerializedName("sessionResult")
    @Expose
    private SessionResult sessionResult;
    @SerializedName("laps")
    @Expose
    private List<Lap> laps = null;
    @SerializedName("penalties")
    @Expose
    private List<Object> penalties = null;

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public Integer getSessionIndex() {
        return sessionIndex;
    }

    public void setSessionIndex(Integer sessionIndex) {
        this.sessionIndex = sessionIndex;
    }

    public SessionResult getSessionResult() {
        return sessionResult;
    }

    public void setSessionResult(SessionResult sessionResult) {
        this.sessionResult = sessionResult;
    }

    public List<Lap> getLaps() {
        return laps;
    }

    public void setLaps(List<Lap> laps) {
        this.laps = laps;
    }

    public List<Object> getPenalties() {
        return penalties;
    }

    public void setPenalties(List<Object> penalties) {
        this.penalties = penalties;
    }

}
