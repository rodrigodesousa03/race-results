
package br.com.rsousa.pojo.iracing.json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Session {

    @SerializedName("subsession_id")
    @Expose
    private Integer subsessionId;
    @SerializedName("associated_subsession_ids")
    @Expose
    private List<Integer> associatedSubsessionIds;
    @SerializedName("can_protest")
    @Expose
    private Boolean canProtest;
    @SerializedName("car_classes")
    @Expose
    private List<CarClass> carClasses;
    @SerializedName("caution_type")
    @Expose
    private Integer cautionType;
    @SerializedName("cooldown_minutes")
    @Expose
    private Integer cooldownMinutes;
    @SerializedName("corners_per_lap")
    @Expose
    private Integer cornersPerLap;
    @SerializedName("damage_model")
    @Expose
    private Integer damageModel;
    @SerializedName("driver_change_param1")
    @Expose
    private Integer driverChangeParam1;
    @SerializedName("driver_change_param2")
    @Expose
    private Integer driverChangeParam2;
    @SerializedName("driver_change_rule")
    @Expose
    private Integer driverChangeRule;
    @SerializedName("driver_changes")
    @Expose
    private Boolean driverChanges;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("event_average_lap")
    @Expose
    private Integer eventAverageLap;
    @SerializedName("event_laps_complete")
    @Expose
    private Integer eventLapsComplete;
    @SerializedName("event_strength_of_field")
    @Expose
    private Integer eventStrengthOfField;
    @SerializedName("event_type")
    @Expose
    private Integer eventType;
    @SerializedName("event_type_name")
    @Expose
    private String eventTypeName;
    @SerializedName("heat_info_id")
    @Expose
    private Integer heatInfoId;
    @SerializedName("host_id")
    @Expose
    private Integer hostId;
    @SerializedName("league_id")
    @Expose
    private Integer leagueId;
    @SerializedName("league_name")
    @Expose
    private String leagueName;
    @SerializedName("league_season_id")
    @Expose
    private Integer leagueSeasonId;
    @SerializedName("league_season_name")
    @Expose
    private String leagueSeasonName;
    @SerializedName("license_category")
    @Expose
    private String licenseCategory;
    @SerializedName("license_category_id")
    @Expose
    private Integer licenseCategoryId;
    @SerializedName("limit_minutes")
    @Expose
    private Integer limitMinutes;
    @SerializedName("max_team_drivers")
    @Expose
    private Integer maxTeamDrivers;
    @SerializedName("max_weeks")
    @Expose
    private Integer maxWeeks;
    @SerializedName("min_team_drivers")
    @Expose
    private Integer minTeamDrivers;
    @SerializedName("num_caution_laps")
    @Expose
    private Integer numCautionLaps;
    @SerializedName("num_cautions")
    @Expose
    private Integer numCautions;
    @SerializedName("num_laps_for_qual_average")
    @Expose
    private Integer numLapsForQualAverage;
    @SerializedName("num_laps_for_solo_average")
    @Expose
    private Integer numLapsForSoloAverage;
    @SerializedName("num_lead_changes")
    @Expose
    private Integer numLeadChanges;
    @SerializedName("official_session")
    @Expose
    private Boolean officialSession;
    @SerializedName("points_type")
    @Expose
    private String pointsType;
    @SerializedName("private_session_id")
    @Expose
    private Integer privateSessionId;
    @SerializedName("race_summary")
    @Expose
    private RaceSummary raceSummary;
    @SerializedName("race_week_num")
    @Expose
    private Integer raceWeekNum;
    @SerializedName("restrict_results")
    @Expose
    private Boolean restrictResults;
    @SerializedName("results_restricted")
    @Expose
    private Boolean resultsRestricted;
    @SerializedName("season_id")
    @Expose
    private Integer seasonId;
    @SerializedName("season_name")
    @Expose
    private String seasonName;
    @SerializedName("season_quarter")
    @Expose
    private Integer seasonQuarter;
    @SerializedName("season_short_name")
    @Expose
    private String seasonShortName;
    @SerializedName("season_year")
    @Expose
    private Integer seasonYear;
    @SerializedName("series_id")
    @Expose
    private Integer seriesId;
    @SerializedName("series_name")
    @Expose
    private String seriesName;
    @SerializedName("series_short_name")
    @Expose
    private String seriesShortName;
    @SerializedName("session_id")
    @Expose
    private Integer sessionId;
    @SerializedName("session_name")
    @Expose
    private String sessionName;
    @SerializedName("session_results")
    @Expose
    private List<SessionResult> sessionResults;
    @SerializedName("session_splits")
    @Expose
    private List<SessionSplit> sessionSplits;
    @SerializedName("special_event_type")
    @Expose
    private Integer specialEventType;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("track")
    @Expose
    private Track track;
    @SerializedName("track_state")
    @Expose
    private TrackState trackState;
    @SerializedName("weather")
    @Expose
    private Weather weather;

    public Integer getSubsessionId() {
        return subsessionId;
    }

    public void setSubsessionId(Integer subsessionId) {
        this.subsessionId = subsessionId;
    }

    public List<Integer> getAssociatedSubsessionIds() {
        return associatedSubsessionIds;
    }

    public void setAssociatedSubsessionIds(List<Integer> associatedSubsessionIds) {
        this.associatedSubsessionIds = associatedSubsessionIds;
    }

    public Boolean getCanProtest() {
        return canProtest;
    }

    public void setCanProtest(Boolean canProtest) {
        this.canProtest = canProtest;
    }

    public List<CarClass> getCarClasses() {
        return carClasses;
    }

    public void setCarClasses(List<CarClass> carClasses) {
        this.carClasses = carClasses;
    }

    public Integer getCautionType() {
        return cautionType;
    }

    public void setCautionType(Integer cautionType) {
        this.cautionType = cautionType;
    }

    public Integer getCooldownMinutes() {
        return cooldownMinutes;
    }

    public void setCooldownMinutes(Integer cooldownMinutes) {
        this.cooldownMinutes = cooldownMinutes;
    }

    public Integer getCornersPerLap() {
        return cornersPerLap;
    }

    public void setCornersPerLap(Integer cornersPerLap) {
        this.cornersPerLap = cornersPerLap;
    }

    public Integer getDamageModel() {
        return damageModel;
    }

    public void setDamageModel(Integer damageModel) {
        this.damageModel = damageModel;
    }

    public Integer getDriverChangeParam1() {
        return driverChangeParam1;
    }

    public void setDriverChangeParam1(Integer driverChangeParam1) {
        this.driverChangeParam1 = driverChangeParam1;
    }

    public Integer getDriverChangeParam2() {
        return driverChangeParam2;
    }

    public void setDriverChangeParam2(Integer driverChangeParam2) {
        this.driverChangeParam2 = driverChangeParam2;
    }

    public Integer getDriverChangeRule() {
        return driverChangeRule;
    }

    public void setDriverChangeRule(Integer driverChangeRule) {
        this.driverChangeRule = driverChangeRule;
    }

    public Boolean getDriverChanges() {
        return driverChanges;
    }

    public void setDriverChanges(Boolean driverChanges) {
        this.driverChanges = driverChanges;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getEventAverageLap() {
        return eventAverageLap;
    }

    public void setEventAverageLap(Integer eventAverageLap) {
        this.eventAverageLap = eventAverageLap;
    }

    public Integer getEventLapsComplete() {
        return eventLapsComplete;
    }

    public void setEventLapsComplete(Integer eventLapsComplete) {
        this.eventLapsComplete = eventLapsComplete;
    }

    public Integer getEventStrengthOfField() {
        return eventStrengthOfField;
    }

    public void setEventStrengthOfField(Integer eventStrengthOfField) {
        this.eventStrengthOfField = eventStrengthOfField;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public Integer getHeatInfoId() {
        return heatInfoId;
    }

    public void setHeatInfoId(Integer heatInfoId) {
        this.heatInfoId = heatInfoId;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Integer getLeagueSeasonId() {
        return leagueSeasonId;
    }

    public void setLeagueSeasonId(Integer leagueSeasonId) {
        this.leagueSeasonId = leagueSeasonId;
    }

    public String getLeagueSeasonName() {
        return leagueSeasonName;
    }

    public void setLeagueSeasonName(String leagueSeasonName) {
        this.leagueSeasonName = leagueSeasonName;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }

    public void setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
    }

    public Integer getLicenseCategoryId() {
        return licenseCategoryId;
    }

    public void setLicenseCategoryId(Integer licenseCategoryId) {
        this.licenseCategoryId = licenseCategoryId;
    }

    public Integer getLimitMinutes() {
        return limitMinutes;
    }

    public void setLimitMinutes(Integer limitMinutes) {
        this.limitMinutes = limitMinutes;
    }

    public Integer getMaxTeamDrivers() {
        return maxTeamDrivers;
    }

    public void setMaxTeamDrivers(Integer maxTeamDrivers) {
        this.maxTeamDrivers = maxTeamDrivers;
    }

    public Integer getMaxWeeks() {
        return maxWeeks;
    }

    public void setMaxWeeks(Integer maxWeeks) {
        this.maxWeeks = maxWeeks;
    }

    public Integer getMinTeamDrivers() {
        return minTeamDrivers;
    }

    public void setMinTeamDrivers(Integer minTeamDrivers) {
        this.minTeamDrivers = minTeamDrivers;
    }

    public Integer getNumCautionLaps() {
        return numCautionLaps;
    }

    public void setNumCautionLaps(Integer numCautionLaps) {
        this.numCautionLaps = numCautionLaps;
    }

    public Integer getNumCautions() {
        return numCautions;
    }

    public void setNumCautions(Integer numCautions) {
        this.numCautions = numCautions;
    }

    public Integer getNumLapsForQualAverage() {
        return numLapsForQualAverage;
    }

    public void setNumLapsForQualAverage(Integer numLapsForQualAverage) {
        this.numLapsForQualAverage = numLapsForQualAverage;
    }

    public Integer getNumLapsForSoloAverage() {
        return numLapsForSoloAverage;
    }

    public void setNumLapsForSoloAverage(Integer numLapsForSoloAverage) {
        this.numLapsForSoloAverage = numLapsForSoloAverage;
    }

    public Integer getNumLeadChanges() {
        return numLeadChanges;
    }

    public void setNumLeadChanges(Integer numLeadChanges) {
        this.numLeadChanges = numLeadChanges;
    }

    public Boolean getOfficialSession() {
        return officialSession;
    }

    public void setOfficialSession(Boolean officialSession) {
        this.officialSession = officialSession;
    }

    public String getPointsType() {
        return pointsType;
    }

    public void setPointsType(String pointsType) {
        this.pointsType = pointsType;
    }

    public Integer getPrivateSessionId() {
        return privateSessionId;
    }

    public void setPrivateSessionId(Integer privateSessionId) {
        this.privateSessionId = privateSessionId;
    }

    public RaceSummary getRaceSummary() {
        return raceSummary;
    }

    public void setRaceSummary(RaceSummary raceSummary) {
        this.raceSummary = raceSummary;
    }

    public Integer getRaceWeekNum() {
        return raceWeekNum;
    }

    public void setRaceWeekNum(Integer raceWeekNum) {
        this.raceWeekNum = raceWeekNum;
    }

    public Boolean getRestrictResults() {
        return restrictResults;
    }

    public void setRestrictResults(Boolean restrictResults) {
        this.restrictResults = restrictResults;
    }

    public Boolean getResultsRestricted() {
        return resultsRestricted;
    }

    public void setResultsRestricted(Boolean resultsRestricted) {
        this.resultsRestricted = resultsRestricted;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public Integer getSeasonQuarter() {
        return seasonQuarter;
    }

    public void setSeasonQuarter(Integer seasonQuarter) {
        this.seasonQuarter = seasonQuarter;
    }

    public String getSeasonShortName() {
        return seasonShortName;
    }

    public void setSeasonShortName(String seasonShortName) {
        this.seasonShortName = seasonShortName;
    }

    public Integer getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(Integer seasonYear) {
        this.seasonYear = seasonYear;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesShortName() {
        return seriesShortName;
    }

    public void setSeriesShortName(String seriesShortName) {
        this.seriesShortName = seriesShortName;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public List<SessionResult> getSessionResults() {
        return sessionResults;
    }

    public void setSessionResults(List<SessionResult> sessionResults) {
        this.sessionResults = sessionResults;
    }

    public List<SessionSplit> getSessionSplits() {
        return sessionSplits;
    }

    public void setSessionSplits(List<SessionSplit> sessionSplits) {
        this.sessionSplits = sessionSplits;
    }

    public Integer getSpecialEventType() {
        return specialEventType;
    }

    public void setSpecialEventType(Integer specialEventType) {
        this.specialEventType = specialEventType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public TrackState getTrackState() {
        return trackState;
    }

    public void setTrackState(TrackState trackState) {
        this.trackState = trackState;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

}
