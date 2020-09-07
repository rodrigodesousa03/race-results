package br.com.rsousa.pojo;

import java.math.BigDecimal;

public class Driver implements Comparable<Driver> {
	private String name;
	private String team;
	private String id;
	private String bestLap;
	private Integer bestLapSeconds;
	private Integer laps;
	private Integer position;
	private boolean isBestLap;
	private boolean isPoleposition;
	private boolean hattrick;
	private boolean grandChelem;
	private Integer licensePoints;
	private DriverStatus status;

	private String raceTime;
	private String raceTimeFormatted;

	public Driver(String name, String team, String id) {
		this.name = name;
		this.team = team;
		this.id = id;
		this.status = DriverStatus.FINISHED;
	}

	public Driver() {
		this.status = DriverStatus.FINISHED;
	}

	public DriverStatus getStatus() {
		return status;
	}

	public void setStatus(DriverStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return name + " (" + team + ")";
	}

	public String getBestLap() {
		return bestLap;
	}

	public void setBestLap(String bestLap) {
		this.bestLap = bestLap;
	}

	public boolean isBestLap() {
		return isBestLap;
	}

	public void setBestLap(boolean isBestLap) {
		this.isBestLap = isBestLap;
	}

	public boolean isHattrick() {
		return hattrick;
	}

	public void setHattrick(boolean hattrick) {
		this.hattrick = hattrick;
	}

	public boolean isGrandChelem() {
		return grandChelem;
	}

	public void setGrandChelem(boolean grandChelem) {
		this.grandChelem = grandChelem;
	}

	public Integer getLicensePoints() {
		return licensePoints;
	}

	public void setLicensePoints(Integer licensePoints) {
		this.licensePoints = licensePoints;
	}

	public Integer getLaps() {
		return laps;
	}

	public void setLaps(Integer laps) {
		this.laps = laps;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getRaceTimeFormatted() {
		return raceTimeFormatted;
	}

	public void setRaceTimeFormatted(String raceTimeFormatted) {
		this.raceTimeFormatted = raceTimeFormatted;
	}

	public boolean isPoleposition() {
		return isPoleposition;
	}

	public void setPoleposition(boolean poleposition) {
		isPoleposition = poleposition;
	}

	public Integer getBestLapSeconds() {
		return bestLapSeconds;
	}

	public void setBestLapSeconds(Integer bestLapSeconds) {
		this.bestLapSeconds = bestLapSeconds;
	}

	public String getRaceTime() {
		return raceTime;
	}

	public void setRaceTime(String raceTime) {
		this.raceTime = raceTime;
	}

	public String text() {
		return position + " " + toString() + " " + raceTimeFormatted;
	}

	@Override
	public int compareTo(Driver driver) {
		return position - driver.getPosition();
	}

	public String positionText() {
		return getStatus() == DriverStatus.FINISHED ? getPosition().toString() : getStatus().text();
	}
}
