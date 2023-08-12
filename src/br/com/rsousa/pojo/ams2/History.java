
package br.com.rsousa.pojo.ams2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class History {

    @SerializedName("end_time")
    @Expose
    private Integer endTime;
    @SerializedName("finished")
    @Expose
    private Boolean finished;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("members")
    @Expose
    private HashMap<String,Member> members;

    @SerializedName("setup")
    @Expose
    private Setup setup;
    @SerializedName("stages")
    @Expose
    private HashMap<String, Stage> stages;
    @SerializedName("start_time")
    @Expose
    private Integer startTime;

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public HashMap<String,Member> getMembers() {
        return members;
    }

    public void setMembers(HashMap<String,Member> members) {
        this.members = members;
    }

    public Setup getSetup() {
        return setup;
    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

    public HashMap<String, Stage> getStages() {
        return stages;
    }

    public void setStages(HashMap<String, Stage> stages) {
        this.stages = stages;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

}
