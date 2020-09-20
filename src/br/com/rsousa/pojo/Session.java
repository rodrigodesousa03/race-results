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

	public Driver bestLapDriver() {
		return drivers().stream()
				.filter(Driver::isBestLap)
				.findFirst()
				.orElse(null);
	}

	public void addDriver(Driver driver) {
		drivers.add(driver);

		if (type == SessionType.RACE) {
			drivers().forEach((d) -> d.setBestLap(false));

			drivers().stream()
					.filter(d -> d.getBestLapMilliseconds() != 0)
					.sorted((d1, d2) -> d1.getBestLapMilliseconds().compareTo(d2.getBestLapMilliseconds()))
					.findFirst()
					.ifPresent(d -> d.setBestLap(true));
		}
	}

	public SessionType type() {
		return type;
	}
}
