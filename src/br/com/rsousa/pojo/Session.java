package br.com.rsousa.pojo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Session {
	public Session(SessionType type) {
		this.drivers = new ArrayList<>();
		this.type = type;
	}
	
	private SessionType type;
	
	private List<Driver> drivers;

	public List<Driver> drivers() {
		return drivers;
	}

	public void sortDrivers() {
		drivers = drivers.stream()
				.sorted(Comparator.comparingInt(Driver::getPosition))
				.collect(Collectors.toList());
	}

	public void addDriver(Driver driver) {
		drivers.add(driver);
	}

	public SessionType type() {
		return type;
	}
}
