package br.com.rsousa.pojo;

public class Driver implements Comparable<Driver> {
	private String name;
	private String team;
	private String id;
	private String teamStatistics;
	private String bestLap;
	private Long bestLapMilliseconds;
	private Long raceTimeMilliseconds;
	private Integer laps;
	private Integer position;
	private boolean isBestLap;
	private boolean isPolePosition;
	private boolean hattrick;
	private boolean grandChelem;
	private Integer licensePoints;
	private DriverStatus status;
	private String raceTime;
	private String raceTimeFormatted;
	private Double driverTotalTime;
	private Integer carNumber;
	private String averageLap;
	private Integer incidents;


	public Driver(String name, String team, String id, String teamStatistics) {
		this.name = name;
		this.team = team;
		this.id = id;
		this.teamStatistics = teamStatistics;
		this.status = DriverStatus.FINISHED;
	}

	public Driver() {
		this.status = DriverStatus.FINISHED;
		this.licensePoints = 0;
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

	public String getTeamStatistics() {
		return teamStatistics;
	}

	public void setTeamStatistics(String teamStatistics) {
		this.teamStatistics = teamStatistics;
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

	public boolean isPolePosition() {
		return isPolePosition;
	}

	public void setPolePosition(boolean polePosition) {
		isPolePosition = polePosition;
	}

	public String text() {
		return position + " " + toString() + " " + raceTimeFormatted;
	}

	public Long getBestLapMilliseconds() {
		return bestLapMilliseconds;
	}

	public void setBestLapMilliseconds(Long bestLapMilliseconds) {
		this.bestLapMilliseconds = bestLapMilliseconds;
	}

	public Long getRaceTimeMilliseconds() {
		return raceTimeMilliseconds;
	}

	public void setRaceTimeMilliseconds(Long raceTimeMilliseconds) {
		this.raceTimeMilliseconds = raceTimeMilliseconds;
	}

	@Override
	public int compareTo(Driver driver) {
		return position - driver.getPosition();
	}

	public String positionText() {
		return getStatus() == DriverStatus.FINISHED ? getPosition().toString() : getStatus().text();
	}

	public String getRaceTime() {
		return raceTime;
	}

	public void setRaceTime(String raceTime) {
		this.raceTime = raceTime;
	}

	public Double getDriverTotalTime() {
		return driverTotalTime;
	}

	public void setDriverTotalTime(Double driverTotalTime) {
		this.driverTotalTime = driverTotalTime;
	}

    public Integer getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Integer carNumber) {
        this.carNumber = carNumber;
    }

    public String getAverageLap() {
        return averageLap;
    }

    public void setAverageLap(String averageLap) {
        this.averageLap = averageLap;
    }

    public Integer getIncidents() {
        return incidents;
    }

    public void setIncidents(Integer incidents) {
        this.incidents = incidents;
    }
}
