package br.com.rsousa.pojo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Session {
	public Session(SessionType type, boolean isSelective) {
		this.drivers = new ArrayList<>();
		this.type = type;
		this.isSelective = isSelective;
	}
	
	private final SessionType type;
	private final boolean isSelective;
	
	private List<Driver> drivers;

	public List<Driver> drivers() {
		return drivers;
	}

	public boolean isSelective() {
		return isSelective;
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
                    .filter(d -> d.getBestLapMilliseconds() != null && d.getBestLapMilliseconds() != 0)
					.min(Comparator.comparing(Driver::getBestLapMilliseconds))
					.ifPresent(d -> d.setBestLap(true));
		} else {
			if (driver.getPosition() == 1) {
				driver.setPolePosition(true);
			}
		}
	}

	public SessionType type() {
		return type;
	}

	public Optional<Driver> polePositionDriver() {
		return drivers.stream().filter(Driver::isPolePosition)
				.findFirst();
	}

	public void addDrivers(List<Driver> drivers) {
		for (Driver driver : drivers) {
			Driver driverSession = drivers().stream()
					.filter(d -> d.getName().equals(driver.getName()))
					.findFirst()
					.orElse(null);

			if (driverSession == null) {
				drivers().add(driver);
			} else {
				if (driver.getBestLapMilliseconds() != 0 && driver.getBestLapMilliseconds() < driverSession.getBestLapMilliseconds()) {
					drivers().remove(driverSession);
					drivers().add(driver);
				}
			}
		}

		sortDriversByBestLap();
	}

	private void sortDriversByBestLap() {
		drivers = drivers.stream()
				.filter(d -> d.getBestLapMilliseconds() > 0)
				.sorted(Comparator.comparingLong(Driver::getBestLapMilliseconds))
				.collect(Collectors.toList());

		for (int i=0;i<drivers.size();i++) {
			Driver driver = drivers.get(i);
			driver.setPosition(i+1);
		}
	}

	public void sortDriversByLapsAndTotalTime() {
		drivers = drivers.stream()
				.sorted(Comparator.comparingLong(Driver::getLaps).reversed().thenComparing(Driver::getRaceTimeMilliseconds))
				.collect(Collectors.toList());

		for (int i=0;i<drivers.size();i++) {
			Driver driver = drivers.get(i);
			driver.setPosition(i+1);
		}
	}

}
