
package br.com.rsousa.pojo.iracing.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("cust_id")
    @Expose
    private Integer custId;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("finish_position")
    @Expose
    private Integer finishPosition;
    @SerializedName("finish_position_in_class")
    @Expose
    private Integer finishPositionInClass;
    @SerializedName("laps_lead")
    @Expose
    private Integer lapsLead;
    @SerializedName("laps_complete")
    @Expose
    private Long lapsComplete;
    @SerializedName("opt_laps_complete")
    @Expose
    private Integer optLapsComplete;
    @SerializedName("interval")
    @Expose
    private Integer interval;
    @SerializedName("class_interval")
    @Expose
    private Integer classInterval;
    @SerializedName("average_lap")
    @Expose
    private Integer averageLap;
    @SerializedName("best_lap_num")
    @Expose
    private Integer bestLapNum;
    @SerializedName("best_lap_time")
    @Expose
    private Long bestLapTime;
    @SerializedName("best_nlaps_num")
    @Expose
    private Integer bestNlapsNum;
    @SerializedName("best_nlaps_time")
    @Expose
    private Integer bestNlapsTime;
    @SerializedName("best_qual_lap_at")
    @Expose
    private String bestQualLapAt;
    @SerializedName("best_qual_lap_num")
    @Expose
    private Integer bestQualLapNum;
    @SerializedName("best_qual_lap_time")
    @Expose
    private Integer bestQualLapTime;
    @SerializedName("reason_out_id")
    @Expose
    private Integer reasonOutId;
    @SerializedName("reason_out")
    @Expose
    private String reasonOut;
    @SerializedName("champ_points")
    @Expose
    private Integer champPoints;
    @SerializedName("drop_race")
    @Expose
    private Boolean dropRace;
    @SerializedName("club_points")
    @Expose
    private Integer clubPoints;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("qual_lap_time")
    @Expose
    private Integer qualLapTime;
    @SerializedName("starting_position")
    @Expose
    private Integer startingPosition;
    @SerializedName("starting_position_in_class")
    @Expose
    private Integer startingPositionInClass;
    @SerializedName("car_class_id")
    @Expose
    private Integer carClassId;
    @SerializedName("car_class_name")
    @Expose
    private String carClassName;
    @SerializedName("car_class_short_name")
    @Expose
    private String carClassShortName;
    @SerializedName("club_id")
    @Expose
    private Integer clubId;
    @SerializedName("club_name")
    @Expose
    private String clubName;
    @SerializedName("club_shortname")
    @Expose
    private String clubShortname;
    @SerializedName("division")
    @Expose
    private Integer division;
    @SerializedName("old_license_level")
    @Expose
    private Integer oldLicenseLevel;
    @SerializedName("old_sub_level")
    @Expose
    private Integer oldSubLevel;
    @SerializedName("old_cpi")
    @Expose
    private Double oldCpi;
    @SerializedName("oldi_rating")
    @Expose
    private Integer oldiRating;
    @SerializedName("old_ttrating")
    @Expose
    private Integer oldTtrating;
    @SerializedName("new_license_level")
    @Expose
    private Integer newLicenseLevel;
    @SerializedName("new_sub_level")
    @Expose
    private Integer newSubLevel;
    @SerializedName("new_cpi")
    @Expose
    private Double newCpi;
    @SerializedName("newi_rating")
    @Expose
    private Integer newiRating;
    @SerializedName("new_ttrating")
    @Expose
    private Integer newTtrating;
    @SerializedName("multiplier")
    @Expose
    private Integer multiplier;
    @SerializedName("license_change_oval")
    @Expose
    private Integer licenseChangeOval;
    @SerializedName("license_change_road")
    @Expose
    private Integer licenseChangeRoad;
    @SerializedName("incidents")
    @Expose
    private Integer incidents;
    @SerializedName("max_pct_fuel_fill")
    @Expose
    private Integer maxPctFuelFill;
    @SerializedName("weight_penalty_kg")
    @Expose
    private Integer weightPenaltyKg;
    @SerializedName("league_points")
    @Expose
    private Integer leaguePoints;
    @SerializedName("league_agg_points")
    @Expose
    private Integer leagueAggPoints;
    @SerializedName("car_id")
    @Expose
    private Integer carId;
    @SerializedName("car_name")
    @Expose
    private String carName;
    @SerializedName("aggregate_champ_points")
    @Expose
    private Integer aggregateChampPoints;
    @SerializedName("livery")
    @Expose
    private Livery livery;
    @SerializedName("suit")
    @Expose
    private Suit suit;
    @SerializedName("helmet")
    @Expose
    private Helmet helmet;
    @SerializedName("watched")
    @Expose
    private Boolean watched;
    @SerializedName("friend")
    @Expose
    private Boolean friend;
    @SerializedName("ai")
    @Expose
    private Boolean ai;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getFinishPosition() {
        return finishPosition;
    }

    public void setFinishPosition(Integer finishPosition) {
        this.finishPosition = finishPosition;
    }

    public Integer getFinishPositionInClass() {
        return finishPositionInClass;
    }

    public void setFinishPositionInClass(Integer finishPositionInClass) {
        this.finishPositionInClass = finishPositionInClass;
    }

    public Integer getLapsLead() {
        return lapsLead;
    }

    public void setLapsLead(Integer lapsLead) {
        this.lapsLead = lapsLead;
    }

    public Long getLapsComplete() {
        return lapsComplete;
    }

    public void setLapsComplete(Long lapsComplete) {
        this.lapsComplete = lapsComplete;
    }

    public Integer getOptLapsComplete() {
        return optLapsComplete;
    }

    public void setOptLapsComplete(Integer optLapsComplete) {
        this.optLapsComplete = optLapsComplete;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getClassInterval() {
        return classInterval;
    }

    public void setClassInterval(Integer classInterval) {
        this.classInterval = classInterval;
    }

    public Integer getAverageLap() {
        return averageLap;
    }

    public void setAverageLap(Integer averageLap) {
        this.averageLap = averageLap;
    }

    public Integer getBestLapNum() {
        return bestLapNum;
    }

    public void setBestLapNum(Integer bestLapNum) {
        this.bestLapNum = bestLapNum;
    }

    public Long getBestLapTime() {
        return bestLapTime;
    }

    public void setBestLapTime(Long bestLapTime) {
        this.bestLapTime = bestLapTime;
    }

    public Integer getBestNlapsNum() {
        return bestNlapsNum;
    }

    public void setBestNlapsNum(Integer bestNlapsNum) {
        this.bestNlapsNum = bestNlapsNum;
    }

    public Integer getBestNlapsTime() {
        return bestNlapsTime;
    }

    public void setBestNlapsTime(Integer bestNlapsTime) {
        this.bestNlapsTime = bestNlapsTime;
    }

    public String getBestQualLapAt() {
        return bestQualLapAt;
    }

    public void setBestQualLapAt(String bestQualLapAt) {
        this.bestQualLapAt = bestQualLapAt;
    }

    public Integer getBestQualLapNum() {
        return bestQualLapNum;
    }

    public void setBestQualLapNum(Integer bestQualLapNum) {
        this.bestQualLapNum = bestQualLapNum;
    }

    public Integer getBestQualLapTime() {
        return bestQualLapTime;
    }

    public void setBestQualLapTime(Integer bestQualLapTime) {
        this.bestQualLapTime = bestQualLapTime;
    }

    public Integer getReasonOutId() {
        return reasonOutId;
    }

    public void setReasonOutId(Integer reasonOutId) {
        this.reasonOutId = reasonOutId;
    }

    public String getReasonOut() {
        return reasonOut;
    }

    public void setReasonOut(String reasonOut) {
        this.reasonOut = reasonOut;
    }

    public Integer getChampPoints() {
        return champPoints;
    }

    public void setChampPoints(Integer champPoints) {
        this.champPoints = champPoints;
    }

    public Boolean getDropRace() {
        return dropRace;
    }

    public void setDropRace(Boolean dropRace) {
        this.dropRace = dropRace;
    }

    public Integer getClubPoints() {
        return clubPoints;
    }

    public void setClubPoints(Integer clubPoints) {
        this.clubPoints = clubPoints;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getQualLapTime() {
        return qualLapTime;
    }

    public void setQualLapTime(Integer qualLapTime) {
        this.qualLapTime = qualLapTime;
    }

    public Integer getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Integer startingPosition) {
        this.startingPosition = startingPosition;
    }

    public Integer getStartingPositionInClass() {
        return startingPositionInClass;
    }

    public void setStartingPositionInClass(Integer startingPositionInClass) {
        this.startingPositionInClass = startingPositionInClass;
    }

    public Integer getCarClassId() {
        return carClassId;
    }

    public void setCarClassId(Integer carClassId) {
        this.carClassId = carClassId;
    }

    public String getCarClassName() {
        return carClassName;
    }

    public void setCarClassName(String carClassName) {
        this.carClassName = carClassName;
    }

    public String getCarClassShortName() {
        return carClassShortName;
    }

    public void setCarClassShortName(String carClassShortName) {
        this.carClassShortName = carClassShortName;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubShortname() {
        return clubShortname;
    }

    public void setClubShortname(String clubShortname) {
        this.clubShortname = clubShortname;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public Integer getOldLicenseLevel() {
        return oldLicenseLevel;
    }

    public void setOldLicenseLevel(Integer oldLicenseLevel) {
        this.oldLicenseLevel = oldLicenseLevel;
    }

    public Integer getOldSubLevel() {
        return oldSubLevel;
    }

    public void setOldSubLevel(Integer oldSubLevel) {
        this.oldSubLevel = oldSubLevel;
    }

    public Double getOldCpi() {
        return oldCpi;
    }

    public void setOldCpi(Double oldCpi) {
        this.oldCpi = oldCpi;
    }

    public Integer getOldiRating() {
        return oldiRating;
    }

    public void setOldiRating(Integer oldiRating) {
        this.oldiRating = oldiRating;
    }

    public Integer getOldTtrating() {
        return oldTtrating;
    }

    public void setOldTtrating(Integer oldTtrating) {
        this.oldTtrating = oldTtrating;
    }

    public Integer getNewLicenseLevel() {
        return newLicenseLevel;
    }

    public void setNewLicenseLevel(Integer newLicenseLevel) {
        this.newLicenseLevel = newLicenseLevel;
    }

    public Integer getNewSubLevel() {
        return newSubLevel;
    }

    public void setNewSubLevel(Integer newSubLevel) {
        this.newSubLevel = newSubLevel;
    }

    public Double getNewCpi() {
        return newCpi;
    }

    public void setNewCpi(Double newCpi) {
        this.newCpi = newCpi;
    }

    public Integer getNewiRating() {
        return newiRating;
    }

    public void setNewiRating(Integer newiRating) {
        this.newiRating = newiRating;
    }

    public Integer getNewTtrating() {
        return newTtrating;
    }

    public void setNewTtrating(Integer newTtrating) {
        this.newTtrating = newTtrating;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }

    public Integer getLicenseChangeOval() {
        return licenseChangeOval;
    }

    public void setLicenseChangeOval(Integer licenseChangeOval) {
        this.licenseChangeOval = licenseChangeOval;
    }

    public Integer getLicenseChangeRoad() {
        return licenseChangeRoad;
    }

    public void setLicenseChangeRoad(Integer licenseChangeRoad) {
        this.licenseChangeRoad = licenseChangeRoad;
    }

    public Integer getIncidents() {
        return incidents;
    }

    public void setIncidents(Integer incidents) {
        this.incidents = incidents;
    }

    public Integer getMaxPctFuelFill() {
        return maxPctFuelFill;
    }

    public void setMaxPctFuelFill(Integer maxPctFuelFill) {
        this.maxPctFuelFill = maxPctFuelFill;
    }

    public Integer getWeightPenaltyKg() {
        return weightPenaltyKg;
    }

    public void setWeightPenaltyKg(Integer weightPenaltyKg) {
        this.weightPenaltyKg = weightPenaltyKg;
    }

    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public Integer getLeagueAggPoints() {
        return leagueAggPoints;
    }

    public void setLeagueAggPoints(Integer leagueAggPoints) {
        this.leagueAggPoints = leagueAggPoints;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getAggregateChampPoints() {
        return aggregateChampPoints;
    }

    public void setAggregateChampPoints(Integer aggregateChampPoints) {
        this.aggregateChampPoints = aggregateChampPoints;
    }

    public Livery getLivery() {
        return livery;
    }

    public void setLivery(Livery livery) {
        this.livery = livery;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public Boolean getWatched() {
        return watched;
    }

    public void setWatched(Boolean watched) {
        this.watched = watched;
    }

    public Boolean getFriend() {
        return friend;
    }

    public void setFriend(Boolean friend) {
        this.friend = friend;
    }

    public Boolean getAi() {
        return ai;
    }

    public void setAi(Boolean ai) {
        this.ai = ai;
    }

}
