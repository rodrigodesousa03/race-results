package br.com.rsousa.pojo;

public class Driver {
	private String name;
	private String team;
	private String id;
	private Double bestLap;
	private Integer laps;
	private Integer position;
	private boolean isBestLap;
	private boolean hattrick;
	private boolean grandChelem;
	private Integer licensePoints;
	
	public Driver(String name, String team, String id) {
		this.name = name;
		this.team = team;
		this.id = id;
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

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Driver name=" + name + ", team=" + team + ", id=" + id;
	}

	public Double getBestLap() {
		return bestLap;
	}

	public void setBestLap(Double bestLap) {
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
}
