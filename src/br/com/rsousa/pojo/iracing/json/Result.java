package br.com.rsousa.pojo.iracing.json;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

   
public class Result {

   @SerializedName("cust_id")
   Integer custId;

   @SerializedName("display_name")
   String displayName;

   @SerializedName("aggregate_champ_points")
   Integer aggregateChampPoints;

   @SerializedName("ai")
   boolean ai;

   @SerializedName("average_lap")
   Integer averageLap;

   @SerializedName("best_lap_num")
   Integer bestLapNum;

   @SerializedName("best_lap_time")
   Long bestLapTime;

   @SerializedName("best_nlaps_num")
   Integer bestNlapsNum;

   @SerializedName("best_nlaps_time")
   Integer bestNlapsTime;

   @SerializedName("best_qual_lap_at")
   Date bestQualLapAt;

   @SerializedName("best_qual_lap_num")
   Integer bestQualLapNum;

   @SerializedName("best_qual_lap_time")
   Integer bestQualLapTime;

   @SerializedName("car_class_id")
   Integer carClassId;

   @SerializedName("car_class_name")
   String carClassName;

   @SerializedName("car_class_short_name")
   String carClassShortName;

   @SerializedName("car_id")
   Integer carId;

   @SerializedName("car_name")
   String carName;

   @SerializedName("champ_points")
   Integer champPoints;

   @SerializedName("class_interval")
   Integer classInterval;

   @SerializedName("club_id")
   Integer clubId;

   @SerializedName("club_name")
   String clubName;

   @SerializedName("club_points")
   Integer clubPoints;

   @SerializedName("club_shortname")
   String clubShortname;

   @SerializedName("country_code")
   String countryCode;

   @SerializedName("division")
   Integer division;

   @SerializedName("drop_race")
   boolean dropRace;

   @SerializedName("finish_position")
   Integer finishPosition;

   @SerializedName("finish_position_in_class")
   Integer finishPositionInClass;

   @SerializedName("friend")
   boolean friend;

   @SerializedName("helmet")
   Helmet helmet;

   @SerializedName("incidents")
   Integer incidents;

   @SerializedName("interval")
   Integer interval;

   @SerializedName("laps_complete")
   Long lapsComplete;

   @SerializedName("laps_lead")
   Integer lapsLead;

   @SerializedName("league_agg_points")
   Integer leagueAggPoints;

   @SerializedName("league_points")
   Integer leaguePoints;

   @SerializedName("license_change_oval")
   Integer licenseChangeOval;

   @SerializedName("license_change_road")
   Integer licenseChangeRoad;

   @SerializedName("livery")
   Livery livery;

   @SerializedName("max_pct_fuel_fill")
   Integer maxPctFuelFill;

   @SerializedName("multiplier")
   Integer multiplier;

   @SerializedName("new_cpi")
   double newCpi;

   @SerializedName("new_license_level")
   Integer newLicenseLevel;

   @SerializedName("new_sub_level")
   Integer newSubLevel;

   @SerializedName("new_ttrating")
   Integer newTtrating;

   @SerializedName("newi_rating")
   Integer newiRating;

   @SerializedName("old_cpi")
   double oldCpi;

   @SerializedName("old_license_level")
   Integer oldLicenseLevel;

   @SerializedName("old_sub_level")
   Integer oldSubLevel;

   @SerializedName("old_ttrating")
   Integer oldTtrating;

   @SerializedName("oldi_rating")
   Integer oldiRating;

   @SerializedName("opt_laps_complete")
   Integer optLapsComplete;

   @SerializedName("position")
   Integer position;

   @SerializedName("qual_lap_time")
   Integer qualLapTime;

   @SerializedName("reason_out")
   String reasonOut;

   @SerializedName("reason_out_id")
   Integer reasonOutId;

   @SerializedName("starting_position")
   Integer startingPosition;

   @SerializedName("starting_position_in_class")
   Integer startingPositionInClass;

   @SerializedName("suit")
   Suit suit;

   @SerializedName("watched")
   boolean watched;

   @SerializedName("weight_penalty_kg")
   Integer weightPenaltyKg;


    public void setCustId(Integer custId) {
        this.custId = custId;
    }
    public Integer getCustId() {
        return custId;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
    
    public void setAggregateChampPoints(Integer aggregateChampPoints) {
        this.aggregateChampPoints = aggregateChampPoints;
    }
    public Integer getAggregateChampPoints() {
        return aggregateChampPoints;
    }
    
    public void setAi(boolean ai) {
        this.ai = ai;
    }
    public boolean getAi() {
        return ai;
    }
    
    public void setAverageLap(Integer averageLap) {
        this.averageLap = averageLap;
    }
    public Integer getAverageLap() {
        return averageLap;
    }
    
    public void setBestLapNum(Integer bestLapNum) {
        this.bestLapNum = bestLapNum;
    }
    public Integer getBestLapNum() {
        return bestLapNum;
    }
    
    public void setBestLapTime(Long bestLapTime) {
        this.bestLapTime = bestLapTime;
    }
    public Long getBestLapTime() {
        return bestLapTime;
    }
    
    public void setBestNlapsNum(Integer bestNlapsNum) {
        this.bestNlapsNum = bestNlapsNum;
    }
    public Integer getBestNlapsNum() {
        return bestNlapsNum;
    }
    
    public void setBestNlapsTime(Integer bestNlapsTime) {
        this.bestNlapsTime = bestNlapsTime;
    }
    public Integer getBestNlapsTime() {
        return bestNlapsTime;
    }
    
    public void setBestQualLapAt(Date bestQualLapAt) {
        this.bestQualLapAt = bestQualLapAt;
    }
    public Date getBestQualLapAt() {
        return bestQualLapAt;
    }
    
    public void setBestQualLapNum(Integer bestQualLapNum) {
        this.bestQualLapNum = bestQualLapNum;
    }
    public Integer getBestQualLapNum() {
        return bestQualLapNum;
    }
    
    public void setBestQualLapTime(Integer bestQualLapTime) {
        this.bestQualLapTime = bestQualLapTime;
    }
    public Integer getBestQualLapTime() {
        return bestQualLapTime;
    }
    
    public void setCarClassId(Integer carClassId) {
        this.carClassId = carClassId;
    }
    public Integer getCarClassId() {
        return carClassId;
    }
    
    public void setCarClassName(String carClassName) {
        this.carClassName = carClassName;
    }
    public String getCarClassName() {
        return carClassName;
    }
    
    public void setCarClassShortName(String carClassShortName) {
        this.carClassShortName = carClassShortName;
    }
    public String getCarClassShortName() {
        return carClassShortName;
    }
    
    public void setCarId(Integer carId) {
        this.carId = carId;
    }
    public Integer getCarId() {
        return carId;
    }
    
    public void setCarName(String carName) {
        this.carName = carName;
    }
    public String getCarName() {
        return carName;
    }
    
    public void setChampPoints(Integer champPoints) {
        this.champPoints = champPoints;
    }
    public Integer getChampPoints() {
        return champPoints;
    }
    
    public void setClassInterval(Integer classInterval) {
        this.classInterval = classInterval;
    }
    public Integer getClassInterval() {
        return classInterval;
    }
    
    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }
    public Integer getClubId() {
        return clubId;
    }
    
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
    public String getClubName() {
        return clubName;
    }
    
    public void setClubPoints(Integer clubPoints) {
        this.clubPoints = clubPoints;
    }
    public Integer getClubPoints() {
        return clubPoints;
    }
    
    public void setClubShortname(String clubShortname) {
        this.clubShortname = clubShortname;
    }
    public String getClubShortname() {
        return clubShortname;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getCountryCode() {
        return countryCode;
    }
    
    public void setDivision(Integer division) {
        this.division = division;
    }
    public Integer getDivision() {
        return division;
    }
    
    public void setDropRace(boolean dropRace) {
        this.dropRace = dropRace;
    }
    public boolean getDropRace() {
        return dropRace;
    }
    
    public void setFinishPosition(Integer finishPosition) {
        this.finishPosition = finishPosition;
    }
    public Integer getFinishPosition() {
        return finishPosition;
    }
    
    public void setFinishPositionInClass(Integer finishPositionInClass) {
        this.finishPositionInClass = finishPositionInClass;
    }
    public Integer getFinishPositionInClass() {
        return finishPositionInClass;
    }
    
    public void setFriend(boolean friend) {
        this.friend = friend;
    }
    public boolean getFriend() {
        return friend;
    }
    
    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }
    public Helmet getHelmet() {
        return helmet;
    }
    
    public void setIncidents(Integer incidents) {
        this.incidents = incidents;
    }
    public Integer getIncidents() {
        return incidents;
    }
    
    public void setInterval(Integer interval) {
        this.interval = interval;
    }
    public Integer getInterval() {
        return interval;
    }
    
    public void setLapsComplete(Long lapsComplete) {
        this.lapsComplete = lapsComplete;
    }
    public Long getLapsComplete() {
        return lapsComplete;
    }
    
    public void setLapsLead(Integer lapsLead) {
        this.lapsLead = lapsLead;
    }
    public Integer getLapsLead() {
        return lapsLead;
    }
    
    public void setLeagueAggPoints(Integer leagueAggPoints) {
        this.leagueAggPoints = leagueAggPoints;
    }
    public Integer getLeagueAggPoints() {
        return leagueAggPoints;
    }
    
    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }
    public Integer getLeaguePoints() {
        return leaguePoints;
    }
    
    public void setLicenseChangeOval(Integer licenseChangeOval) {
        this.licenseChangeOval = licenseChangeOval;
    }
    public Integer getLicenseChangeOval() {
        return licenseChangeOval;
    }
    
    public void setLicenseChangeRoad(Integer licenseChangeRoad) {
        this.licenseChangeRoad = licenseChangeRoad;
    }
    public Integer getLicenseChangeRoad() {
        return licenseChangeRoad;
    }
    
    public void setLivery(Livery livery) {
        this.livery = livery;
    }
    public Livery getLivery() {
        return livery;
    }
    
    public void setMaxPctFuelFill(Integer maxPctFuelFill) {
        this.maxPctFuelFill = maxPctFuelFill;
    }
    public Integer getMaxPctFuelFill() {
        return maxPctFuelFill;
    }
    
    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }
    public Integer getMultiplier() {
        return multiplier;
    }
    
    public void setNewCpi(double newCpi) {
        this.newCpi = newCpi;
    }
    public double getNewCpi() {
        return newCpi;
    }
    
    public void setNewLicenseLevel(Integer newLicenseLevel) {
        this.newLicenseLevel = newLicenseLevel;
    }
    public Integer getNewLicenseLevel() {
        return newLicenseLevel;
    }
    
    public void setNewSubLevel(Integer newSubLevel) {
        this.newSubLevel = newSubLevel;
    }
    public Integer getNewSubLevel() {
        return newSubLevel;
    }
    
    public void setNewTtrating(Integer newTtrating) {
        this.newTtrating = newTtrating;
    }
    public Integer getNewTtrating() {
        return newTtrating;
    }
    
    public void setNewiRating(Integer newiRating) {
        this.newiRating = newiRating;
    }
    public Integer getNewiRating() {
        return newiRating;
    }
    
    public void setOldCpi(double oldCpi) {
        this.oldCpi = oldCpi;
    }
    public double getOldCpi() {
        return oldCpi;
    }
    
    public void setOldLicenseLevel(Integer oldLicenseLevel) {
        this.oldLicenseLevel = oldLicenseLevel;
    }
    public Integer getOldLicenseLevel() {
        return oldLicenseLevel;
    }
    
    public void setOldSubLevel(Integer oldSubLevel) {
        this.oldSubLevel = oldSubLevel;
    }
    public Integer getOldSubLevel() {
        return oldSubLevel;
    }
    
    public void setOldTtrating(Integer oldTtrating) {
        this.oldTtrating = oldTtrating;
    }
    public Integer getOldTtrating() {
        return oldTtrating;
    }
    
    public void setOldiRating(Integer oldiRating) {
        this.oldiRating = oldiRating;
    }
    public Integer getOldiRating() {
        return oldiRating;
    }
    
    public void setOptLapsComplete(Integer optLapsComplete) {
        this.optLapsComplete = optLapsComplete;
    }
    public Integer getOptLapsComplete() {
        return optLapsComplete;
    }
    
    public void setPosition(Integer position) {
        this.position = position;
    }
    public Integer getPosition() {
        return position;
    }
    
    public void setQualLapTime(Integer qualLapTime) {
        this.qualLapTime = qualLapTime;
    }
    public Integer getQualLapTime() {
        return qualLapTime;
    }
    
    public void setReasonOut(String reasonOut) {
        this.reasonOut = reasonOut;
    }
    public String getReasonOut() {
        return reasonOut;
    }
    
    public void setReasonOutId(Integer reasonOutId) {
        this.reasonOutId = reasonOutId;
    }
    public Integer getReasonOutId() {
        return reasonOutId;
    }
    
    public void setStartingPosition(Integer startingPosition) {
        this.startingPosition = startingPosition;
    }
    public Integer getStartingPosition() {
        return startingPosition;
    }
    
    public void setStartingPositionInClass(Integer startingPositionInClass) {
        this.startingPositionInClass = startingPositionInClass;
    }
    public Integer getStartingPositionInClass() {
        return startingPositionInClass;
    }
    
    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    public Suit getSuit() {
        return suit;
    }
    
    public void setWatched(boolean watched) {
        this.watched = watched;
    }
    public boolean getWatched() {
        return watched;
    }
    
    public void setWeightPenaltyKg(Integer weightPenaltyKg) {
        this.weightPenaltyKg = weightPenaltyKg;
    }
    public Integer getWeightPenaltyKg() {
        return weightPenaltyKg;
    }
    
}