
package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackState {

    @SerializedName("leave_marbles")
    @Expose
    private Boolean leaveMarbles;
    @SerializedName("practice_grip_compound")
    @Expose
    private Integer practiceGripCompound;
    @SerializedName("practice_rubber")
    @Expose
    private Integer practiceRubber;
    @SerializedName("qualify_grip_compound")
    @Expose
    private Integer qualifyGripCompound;
    @SerializedName("qualify_rubber")
    @Expose
    private Integer qualifyRubber;
    @SerializedName("race_grip_compound")
    @Expose
    private Integer raceGripCompound;
    @SerializedName("race_rubber")
    @Expose
    private Integer raceRubber;
    @SerializedName("warmup_grip_compound")
    @Expose
    private Integer warmupGripCompound;
    @SerializedName("warmup_rubber")
    @Expose
    private Integer warmupRubber;

    public Boolean getLeaveMarbles() {
        return leaveMarbles;
    }

    public void setLeaveMarbles(Boolean leaveMarbles) {
        this.leaveMarbles = leaveMarbles;
    }

    public Integer getPracticeGripCompound() {
        return practiceGripCompound;
    }

    public void setPracticeGripCompound(Integer practiceGripCompound) {
        this.practiceGripCompound = practiceGripCompound;
    }

    public Integer getPracticeRubber() {
        return practiceRubber;
    }

    public void setPracticeRubber(Integer practiceRubber) {
        this.practiceRubber = practiceRubber;
    }

    public Integer getQualifyGripCompound() {
        return qualifyGripCompound;
    }

    public void setQualifyGripCompound(Integer qualifyGripCompound) {
        this.qualifyGripCompound = qualifyGripCompound;
    }

    public Integer getQualifyRubber() {
        return qualifyRubber;
    }

    public void setQualifyRubber(Integer qualifyRubber) {
        this.qualifyRubber = qualifyRubber;
    }

    public Integer getRaceGripCompound() {
        return raceGripCompound;
    }

    public void setRaceGripCompound(Integer raceGripCompound) {
        this.raceGripCompound = raceGripCompound;
    }

    public Integer getRaceRubber() {
        return raceRubber;
    }

    public void setRaceRubber(Integer raceRubber) {
        this.raceRubber = raceRubber;
    }

    public Integer getWarmupGripCompound() {
        return warmupGripCompound;
    }

    public void setWarmupGripCompound(Integer warmupGripCompound) {
        this.warmupGripCompound = warmupGripCompound;
    }

    public Integer getWarmupRubber() {
        return warmupRubber;
    }

    public void setWarmupRubber(Integer warmupRubber) {
        this.warmupRubber = warmupRubber;
    }

}
