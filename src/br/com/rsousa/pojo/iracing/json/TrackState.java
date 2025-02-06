package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.SerializedName;

   
public class TrackState {

   @SerializedName("leave_marbles")
   boolean leaveMarbles;

   @SerializedName("practice_grip_compound")
   Integer practiceGripCompound;

   @SerializedName("practice_rubber")
   Integer practiceRubber;

   @SerializedName("qualify_grip_compound")
   Integer qualifyGripCompound;

   @SerializedName("qualify_rubber")
   Integer qualifyRubber;

   @SerializedName("race_grip_compound")
   Integer raceGripCompound;

   @SerializedName("race_rubber")
   Integer raceRubber;

   @SerializedName("warmup_grip_compound")
   Integer warmupGripCompound;

   @SerializedName("warmup_rubber")
   Integer warmupRubber;


    public void setLeaveMarbles(boolean leaveMarbles) {
        this.leaveMarbles = leaveMarbles;
    }
    public boolean getLeaveMarbles() {
        return leaveMarbles;
    }
    
    public void setPracticeGripCompound(Integer practiceGripCompound) {
        this.practiceGripCompound = practiceGripCompound;
    }
    public Integer getPracticeGripCompound() {
        return practiceGripCompound;
    }
    
    public void setPracticeRubber(Integer practiceRubber) {
        this.practiceRubber = practiceRubber;
    }
    public Integer getPracticeRubber() {
        return practiceRubber;
    }
    
    public void setQualifyGripCompound(Integer qualifyGripCompound) {
        this.qualifyGripCompound = qualifyGripCompound;
    }
    public Integer getQualifyGripCompound() {
        return qualifyGripCompound;
    }
    
    public void setQualifyRubber(Integer qualifyRubber) {
        this.qualifyRubber = qualifyRubber;
    }
    public Integer getQualifyRubber() {
        return qualifyRubber;
    }
    
    public void setRaceGripCompound(Integer raceGripCompound) {
        this.raceGripCompound = raceGripCompound;
    }
    public Integer getRaceGripCompound() {
        return raceGripCompound;
    }
    
    public void setRaceRubber(Integer raceRubber) {
        this.raceRubber = raceRubber;
    }
    public Integer getRaceRubber() {
        return raceRubber;
    }
    
    public void setWarmupGripCompound(Integer warmupGripCompound) {
        this.warmupGripCompound = warmupGripCompound;
    }
    public Integer getWarmupGripCompound() {
        return warmupGripCompound;
    }
    
    public void setWarmupRubber(Integer warmupRubber) {
        this.warmupRubber = warmupRubber;
    }
    public Integer getWarmupRubber() {
        return warmupRubber;
    }
    
}