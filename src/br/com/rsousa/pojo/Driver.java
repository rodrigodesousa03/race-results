package br.com.rsousa.pojo;

public class Driver {
	private String name;
	private String team;
	private String id;
	
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
		return "Driver [name=" + name + ", team=" + team + ", id=" + id + "]";
	}
}
