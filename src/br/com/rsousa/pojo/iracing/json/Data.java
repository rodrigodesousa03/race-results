package br.com.rsousa.pojo.iracing.json;
import java.util.List;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

   
public class Data {

   @SerializedName("subsession_id")
   Integer subsessionId;

   @SerializedName("associated_subsession_ids")
   List<Integer> associatedSubsessionIds;

   @SerializedName("can_protest")
   boolean canProtest;

   @SerializedName("car_classes")
   List<CarClass> carClasses;

   @SerializedName("caution_type")
   Integer cautionType;

   @SerializedName("cooldown_minutes")
   Integer cooldownMinutes;

   @SerializedName("corners_per_lap")
   Integer cornersPerLap;

   @SerializedName("damage_model")
   Integer damageModel;

   @SerializedName("driver_change_param1")
   Integer driverChangeParam1;

   @SerializedName("driver_change_param2")
   Integer driverChangeParam2;

   @SerializedName("driver_change_rule")
   Integer driverChangeRule;

   @SerializedName("driver_changes")
   boolean driverChanges;

   @SerializedName("driver_licenses")
   DriverLicense driverLicenses;

   @SerializedName("end_time")
   Date endTime;

   @SerializedName("event_average_lap")
   Integer eventAverageLap;

   @SerializedName("event_best_lap_time")
   Integer eventBestLapTime;

   @SerializedName("event_laps_complete")
   Integer eventLapsComplete;

   @SerializedName("event_strength_of_field")
   Integer eventStrengthOfField;

   @SerializedName("event_type")
   Integer eventType;

   @SerializedName("event_type_name")
   String eventTypeName;

   @SerializedName("heat_info_id")
   Integer heatInfoId;

   @SerializedName("host_id")
   Integer hostId;

   @SerializedName("league_id")
   Integer leagueId;

   @SerializedName("league_name")
   String leagueName;

   @SerializedName("league_season_id")
   Integer leagueSeasonId;

   @SerializedName("league_season_name")
   String leagueSeasonName;

   @SerializedName("license_category")
   String licenseCategory;

   @SerializedName("license_category_id")
   Integer licenseCategoryId;

   @SerializedName("limit_minutes")
   Integer limitMinutes;

   @SerializedName("max_team_drivers")
   Integer maxTeamDrivers;

   @SerializedName("max_weeks")
   Integer maxWeeks;

   @SerializedName("min_team_drivers")
   Integer minTeamDrivers;

   @SerializedName("num_caution_laps")
   Integer numCautionLaps;

   @SerializedName("num_cautions")
   Integer numCautions;

   @SerializedName("num_drivers")
   Integer numDrivers;

   @SerializedName("num_laps_for_qual_average")
   Integer numLapsForQualAverage;

   @SerializedName("num_laps_for_solo_average")
   Integer numLapsForSoloAverage;

   @SerializedName("num_lead_changes")
   Integer numLeadChanges;

   @SerializedName("official_session")
   boolean officialSession;

   @SerializedName("points_type")
   String pointsType;

   @SerializedName("private_session_id")
   Integer privateSessionId;

   @SerializedName("race_summary")
   RaceSummary raceSummary;

   @SerializedName("race_week_num")
   Integer raceWeekNum;

   @SerializedName("restrict_results")
   boolean restrictResults;

   @SerializedName("results_restricted")
   boolean resultsRestricted;

   @SerializedName("season_id")
   Integer seasonId;

   @SerializedName("season_name")
   String seasonName;

   @SerializedName("season_quarter")
   Integer seasonQuarter;

   @SerializedName("season_short_name")
   String seasonShortName;

   @SerializedName("season_year")
   Integer seasonYear;

   @SerializedName("series_id")
   Integer seriesId;

   @SerializedName("series_name")
   String seriesName;

   @SerializedName("series_short_name")
   String seriesShortName;

   @SerializedName("session_id")
   Integer sessionId;

   @SerializedName("session_name")
   String sessionName;

   @SerializedName("session_results")
   List<SessionResult> sessionResults;

   @SerializedName("session_splits")
   List<SessionSplits> sessionSplits;

   @SerializedName("special_event_type")
   Integer specialEventType;

   @SerializedName("start_time")
   Date startTime;

   @SerializedName("track")
   Track track;

   @SerializedName("track_state")
   TrackState trackState;

   @SerializedName("weather")
   Weather weather;


    public void setSubsessionId(Integer subsessionId) {
        this.subsessionId = subsessionId;
    }
    public Integer getSubsessionId() {
        return subsessionId;
    }
    
    public void setAssociatedSubsessionIds(List<Integer> associatedSubsessionIds) {
        this.associatedSubsessionIds = associatedSubsessionIds;
    }
    public List<Integer> getAssociatedSubsessionIds() {
        return associatedSubsessionIds;
    }
    
    public void setCanProtest(boolean canProtest) {
        this.canProtest = canProtest;
    }
    public boolean getCanProtest() {
        return canProtest;
    }
    
    public void setCarClasses(List<CarClass> carClasses) {
        this.carClasses = carClasses;
    }
    public List<CarClass> getCarClasses() {
        return carClasses;
    }
    
    public void setCautionType(Integer cautionType) {
        this.cautionType = cautionType;
    }
    public Integer getCautionType() {
        return cautionType;
    }
    
    public void setCooldownMinutes(Integer cooldownMinutes) {
        this.cooldownMinutes = cooldownMinutes;
    }
    public Integer getCooldownMinutes() {
        return cooldownMinutes;
    }
    
    public void setCornersPerLap(Integer cornersPerLap) {
        this.cornersPerLap = cornersPerLap;
    }
    public Integer getCornersPerLap() {
        return cornersPerLap;
    }
    
    public void setDamageModel(Integer damageModel) {
        this.damageModel = damageModel;
    }
    public Integer getDamageModel() {
        return damageModel;
    }
    
    public void setDriverChangeParam1(Integer driverChangeParam1) {
        this.driverChangeParam1 = driverChangeParam1;
    }
    public Integer getDriverChangeParam1() {
        return driverChangeParam1;
    }
    
    public void setDriverChangeParam2(Integer driverChangeParam2) {
        this.driverChangeParam2 = driverChangeParam2;
    }
    public Integer getDriverChangeParam2() {
        return driverChangeParam2;
    }
    
    public void setDriverChangeRule(Integer driverChangeRule) {
        this.driverChangeRule = driverChangeRule;
    }
    public Integer getDriverChangeRule() {
        return driverChangeRule;
    }
    
    public void setDriverChanges(boolean driverChanges) {
        this.driverChanges = driverChanges;
    }
    public boolean getDriverChanges() {
        return driverChanges;
    }
    
    public void setDriverLicenses(DriverLicense driverLicenses) {
        this.driverLicenses = driverLicenses;
    }
    public DriverLicense getDriverLicenses() {
        return driverLicenses;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEventAverageLap(Integer eventAverageLap) {
        this.eventAverageLap = eventAverageLap;
    }
    public Integer getEventAverageLap() {
        return eventAverageLap;
    }
    
    public void setEventBestLapTime(Integer eventBestLapTime) {
        this.eventBestLapTime = eventBestLapTime;
    }
    public Integer getEventBestLapTime() {
        return eventBestLapTime;
    }
    
    public void setEventLapsComplete(Integer eventLapsComplete) {
        this.eventLapsComplete = eventLapsComplete;
    }
    public Integer getEventLapsComplete() {
        return eventLapsComplete;
    }
    
    public void setEventStrengthOfField(Integer eventStrengthOfField) {
        this.eventStrengthOfField = eventStrengthOfField;
    }
    public Integer getEventStrengthOfField() {
        return eventStrengthOfField;
    }
    
    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }
    public Integer getEventType() {
        return eventType;
    }
    
    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }
    public String getEventTypeName() {
        return eventTypeName;
    }
    
    public void setHeatInfoId(Integer heatInfoId) {
        this.heatInfoId = heatInfoId;
    }
    public Integer getHeatInfoId() {
        return heatInfoId;
    }
    
    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }
    public Integer getHostId() {
        return hostId;
    }
    
    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }
    public Integer getLeagueId() {
        return leagueId;
    }
    
    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }
    public String getLeagueName() {
        return leagueName;
    }
    
    public void setLeagueSeasonId(Integer leagueSeasonId) {
        this.leagueSeasonId = leagueSeasonId;
    }
    public Integer getLeagueSeasonId() {
        return leagueSeasonId;
    }
    
    public void setLeagueSeasonName(String leagueSeasonName) {
        this.leagueSeasonName = leagueSeasonName;
    }
    public String getLeagueSeasonName() {
        return leagueSeasonName;
    }
    
    public void setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
    }
    public String getLicenseCategory() {
        return licenseCategory;
    }
    
    public void setLicenseCategoryId(Integer licenseCategoryId) {
        this.licenseCategoryId = licenseCategoryId;
    }
    public Integer getLicenseCategoryId() {
        return licenseCategoryId;
    }
    
    public void setLimitMinutes(Integer limitMinutes) {
        this.limitMinutes = limitMinutes;
    }
    public Integer getLimitMinutes() {
        return limitMinutes;
    }
    
    public void setMaxTeamDrivers(Integer maxTeamDrivers) {
        this.maxTeamDrivers = maxTeamDrivers;
    }
    public Integer getMaxTeamDrivers() {
        return maxTeamDrivers;
    }
    
    public void setMaxWeeks(Integer maxWeeks) {
        this.maxWeeks = maxWeeks;
    }
    public Integer getMaxWeeks() {
        return maxWeeks;
    }
    
    public void setMinTeamDrivers(Integer minTeamDrivers) {
        this.minTeamDrivers = minTeamDrivers;
    }
    public Integer getMinTeamDrivers() {
        return minTeamDrivers;
    }
    
    public void setNumCautionLaps(Integer numCautionLaps) {
        this.numCautionLaps = numCautionLaps;
    }
    public Integer getNumCautionLaps() {
        return numCautionLaps;
    }
    
    public void setNumCautions(Integer numCautions) {
        this.numCautions = numCautions;
    }
    public Integer getNumCautions() {
        return numCautions;
    }
    
    public void setNumDrivers(Integer numDrivers) {
        this.numDrivers = numDrivers;
    }
    public Integer getNumDrivers() {
        return numDrivers;
    }
    
    public void setNumLapsForQualAverage(Integer numLapsForQualAverage) {
        this.numLapsForQualAverage = numLapsForQualAverage;
    }
    public Integer getNumLapsForQualAverage() {
        return numLapsForQualAverage;
    }
    
    public void setNumLapsForSoloAverage(Integer numLapsForSoloAverage) {
        this.numLapsForSoloAverage = numLapsForSoloAverage;
    }
    public Integer getNumLapsForSoloAverage() {
        return numLapsForSoloAverage;
    }
    
    public void setNumLeadChanges(Integer numLeadChanges) {
        this.numLeadChanges = numLeadChanges;
    }
    public Integer getNumLeadChanges() {
        return numLeadChanges;
    }
    
    public void setOfficialSession(boolean officialSession) {
        this.officialSession = officialSession;
    }
    public boolean getOfficialSession() {
        return officialSession;
    }
    
    public void setPointsType(String pointsType) {
        this.pointsType = pointsType;
    }
    public String getPointsType() {
        return pointsType;
    }
    
    public void setPrivateSessionId(Integer privateSessionId) {
        this.privateSessionId = privateSessionId;
    }
    public Integer getPrivateSessionId() {
        return privateSessionId;
    }
    
    public void setRaceSummary(RaceSummary raceSummary) {
        this.raceSummary = raceSummary;
    }
    public RaceSummary getRaceSummary() {
        return raceSummary;
    }
    
    public void setRaceWeekNum(Integer raceWeekNum) {
        this.raceWeekNum = raceWeekNum;
    }
    public Integer getRaceWeekNum() {
        return raceWeekNum;
    }
    
    public void setRestrictResults(boolean restrictResults) {
        this.restrictResults = restrictResults;
    }
    public boolean getRestrictResults() {
        return restrictResults;
    }
    
    public void setResultsRestricted(boolean resultsRestricted) {
        this.resultsRestricted = resultsRestricted;
    }
    public boolean getResultsRestricted() {
        return resultsRestricted;
    }
    
    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }
    public Integer getSeasonId() {
        return seasonId;
    }
    
    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }
    public String getSeasonName() {
        return seasonName;
    }
    
    public void setSeasonQuarter(Integer seasonQuarter) {
        this.seasonQuarter = seasonQuarter;
    }
    public Integer getSeasonQuarter() {
        return seasonQuarter;
    }
    
    public void setSeasonShortName(String seasonShortName) {
        this.seasonShortName = seasonShortName;
    }
    public String getSeasonShortName() {
        return seasonShortName;
    }
    
    public void setSeasonYear(Integer seasonYear) {
        this.seasonYear = seasonYear;
    }
    public Integer getSeasonYear() {
        return seasonYear;
    }
    
    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }
    public Integer getSeriesId() {
        return seriesId;
    }
    
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
    public String getSeriesName() {
        return seriesName;
    }
    
    public void setSeriesShortName(String seriesShortName) {
        this.seriesShortName = seriesShortName;
    }
    public String getSeriesShortName() {
        return seriesShortName;
    }
    
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
    public Integer getSessionId() {
        return sessionId;
    }
    
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }
    public String getSessionName() {
        return sessionName;
    }
    
    public void setSessionResults(List<SessionResult> sessionResults) {
        this.sessionResults = sessionResults;
    }
    public List<SessionResult> getSessionResults() {
        return sessionResults;
    }
    
    public void setSessionSplits(List<SessionSplits> sessionSplits) {
        this.sessionSplits = sessionSplits;
    }
    public List<SessionSplits> getSessionSplits() {
        return sessionSplits;
    }
    
    public void setSpecialEventType(Integer specialEventType) {
        this.specialEventType = specialEventType;
    }
    public Integer getSpecialEventType() {
        return specialEventType;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getStartTime() {
        return startTime;
    }
    
    public void setTrack(Track track) {
        this.track = track;
    }
    public Track getTrack() {
        return track;
    }
    
    public void setTrackState(TrackState trackState) {
        this.trackState = trackState;
    }
    public TrackState getTrackState() {
        return trackState;
    }
    
    public void setWeather(Weather weather) {
        this.weather = weather;
    }
    public Weather getWeather() {
        return weather;
    }
    
}