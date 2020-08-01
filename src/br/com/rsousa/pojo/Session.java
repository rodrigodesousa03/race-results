package br.com.rsousa.pojo;

import java.util.ArrayList;
import java.util.List;

public class Session {
	public Session(SessionType type) {
		this.drivers = new ArrayList<>();
		this.type = type;
	}
	
	private SessionType type;
	
	private String circuit;
	
	private List<Driver> drivers;

	public List<Driver> drivers() {
		return drivers;
	}
	
	public Driver driverForName(String name) {
		return drivers.stream()
				.filter(d -> name.equals(d.getName()))
				.findFirst()
				.orElse(null);
	}

	public void addDriver(Driver driver) {
		drivers.add(driver);
	}

	public String circuit() {
		return circuit;
	}

	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}

	public SessionType type() {
		return type;
	}
}
