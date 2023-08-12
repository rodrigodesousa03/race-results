
package br.com.rsousa.pojo.ams2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Counts {

    @SerializedName("qualify")
    @Expose
    private Qualify qualify;
    @SerializedName("race")
    @Expose
    private Race race;
    @SerializedName("race_finishes")
    @Expose
    private Integer raceFinishes;
    @SerializedName("race_joins")
    @Expose
    private Integer raceJoins;
    @SerializedName("race_loads")
    @Expose
    private Integer raceLoads;
    @SerializedName("race_loads_done")
    @Expose
    private Integer raceLoadsDone;
    @SerializedName("track_distances")
    @Expose
    private HashMap<String, Integer> trackDistances;
    @SerializedName("tracks")
    @Expose
    private HashMap<String,Integer> tracks;
    @SerializedName("vehicle_distances")
    @Expose
    private HashMap<String,Integer> vehicleDistances;
    @SerializedName("vehicles")
    @Expose
    private HashMap<String,Integer> vehicles;

    public Qualify getQualify() {
        return qualify;
    }

    public void setQualify(Qualify qualify) {
        this.qualify = qualify;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Integer getRaceFinishes() {
        return raceFinishes;
    }

    public void setRaceFinishes(Integer raceFinishes) {
        this.raceFinishes = raceFinishes;
    }

    public Integer getRaceJoins() {
        return raceJoins;
    }

    public void setRaceJoins(Integer raceJoins) {
        this.raceJoins = raceJoins;
    }

    public Integer getRaceLoads() {
        return raceLoads;
    }

    public void setRaceLoads(Integer raceLoads) {
        this.raceLoads = raceLoads;
    }

    public Integer getRaceLoadsDone() {
        return raceLoadsDone;
    }

    public void setRaceLoadsDone(Integer raceLoadsDone) {
        this.raceLoadsDone = raceLoadsDone;
    }

    public HashMap<String, Integer> getTrackDistances() {
        return trackDistances;
    }

    public void setTrackDistances(HashMap<String, Integer> trackDistances) {
        this.trackDistances = trackDistances;
    }

    public HashMap<String,Integer> getTracks() {
        return tracks;
    }

    public void setTracks(HashMap<String,Integer> tracks) {
        this.tracks = tracks;
    }

    public HashMap<String,Integer> getVehicleDistances() {
        return vehicleDistances;
    }

    public void setVehicleDistances(HashMap<String,Integer> vehicleDistances) {
        this.vehicleDistances = vehicleDistances;
    }

    public HashMap<String,Integer> getVehicles() {
        return vehicles;
    }

    public void setVehicles(HashMap<String,Integer> vehicles) {
        this.vehicles = vehicles;
    }

}
