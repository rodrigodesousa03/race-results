package application;

import static br.com.rsousa.enums.FileType.QUALIFY;
import static br.com.rsousa.enums.FileType.RACE;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import static java.util.stream.Collectors.toList;

import br.com.rsousa.enums.FileType;
import br.com.rsousa.pojo.ams.Driver;
import br.com.rsousa.pojo.ams.RFactorXML;

public class RFactorTransformer {
	private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");
	
	static void proccessXMLQualify(File file, Map<String, String> driverTeams, Map<FileType, String> results) {
		if (file != null) {
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(RFactorXML.class);

				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

				RFactorXML raceResult = (RFactorXML) jaxbUnmarshaller.unmarshal(file);

				if (raceResult.getRaceResults().getQualify() == null) {
					proccessXMLRace(file, driverTeams, results);
					
					return;
				}
				
				Driver[] drivers = raceResult.getRaceResults().getQualify().getDriver();

				int position = 1;

				String resultStr = "";

				for (Driver driver : drivers) {
					if (isDriver(driver)) {
						String bestLapTimeFormatted = formatSeconds(driver.getBestLapTime(), "sem tempo");

						resultStr += resultLine(position, driver, bestLapTimeFormatted, driverTeams) + "\n";

						position++;
					}
				}
				
				results.put(QUALIFY, resultStr);
			} catch (Exception e) {
				results.put(QUALIFY, "Error transforming the XML file.");
				
				e.printStackTrace();
			}
		}
	}
	
	static void proccessXMLRace(File file, Map<String, String> driverTeams, Map<FileType, String> results) {
		if (file != null) {
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(RFactorXML.class);

				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

				RFactorXML raceResult = (RFactorXML) jaxbUnmarshaller.unmarshal(file);
				
				if (raceResult.getRaceResults().getRace() == null) {
					proccessXMLQualify(file, driverTeams, results);
					
					return;
				}

				List<Driver> drivers = Stream.of(raceResult.getRaceResults().getRace().getDriver())
						.sorted(Comparator.comparing(Driver::getPosition))
						.collect(toList());

				int position = 1;
				String resultStr = "";
				Driver driverBestLap = null;
				Integer totalLaps = 0;
				Double leaderFinishTime = null;

				for (Driver driver : drivers) {
					if (isDriver(driver)) {
						String raceTimeFormatted;

						if (position == 1) {
							driverBestLap = driver;
							raceTimeFormatted = driver.getLaps() + " voltas";
							totalLaps = driver.getLaps();
							leaderFinishTime = Double.parseDouble(driver.getFinishTime());
						} else {
							if (driver.getBestLapTime() != null && driverBestLap.getBestLapTime().compareTo(driver.getBestLapTime()) > 0) {
								driverBestLap = driver;
							}
							
							if (totalLaps == driver.getLaps()) {
								Double secondsBehindTheLeader = Double.parseDouble(driver.getFinishTime()) - leaderFinishTime;
								
								raceTimeFormatted = formatSeconds(secondsBehindTheLeader.toString());
								
							} else {
								int lapsBehindTheLeader = totalLaps - driver.getLaps();
								
								raceTimeFormatted = "+ " + lapsBehindTheLeader + " laps";
							}
						}
						
						if (!"Finished Normally".equals(driver.getFinishStatus()) && !"None".equals(driver.getFinishStatus())) {
							raceTimeFormatted += " (" + driver.getFinishStatus() + ")";
						}

						resultStr += resultLine(position, driver, raceTimeFormatted, driverTeams) + "\n";
						position++;
					}
				}

				resultStr += "Volta mais rápida: " + driverBestLap.getName() + ", "
						+ formatSeconds(driverBestLap.getBestLapTime());

				results.put(RACE, resultStr);
			} catch (Exception e) {
				results.put(RACE, "Error transforming the XML file.");
				
				e.printStackTrace();
			}
		}
	}
	
	private static boolean isDriver(Driver driver) {
		return !driver.getName().contains("Diretor") && !driver.getName().contains("Comentarista") && !driver.getName().contains("Narrador");
	}

	private static String resultLine(int position, Driver driver, String time, Map<String, String> driverTeams) {
		String driverTeamName = driverTeams.containsKey(driver.getName()) ? driverTeams.get(driver.getName()) : driver.getTeamName().trim();
		
		return position + " " + driver.getName() + " (" + driverTeamName + "), " + time;
	}
	
	private static String formatSeconds(String time, String textIfNull) {
		String formattedSeconds = formatSeconds(time);
		
		if (formattedSeconds == null) {
			return textIfNull;
		}
		
		return formattedSeconds;
	}

	private static String formatSeconds(String time) {
		if (time == null) {
			return null;
		}
		
		String[] bestLapTime = time.split("\\.");

		Integer totalSeconds = Integer.parseInt(bestLapTime[0]);
		String milliseconds = bestLapTime[1].substring(0, 3);

		String bestLapTimeFormatted = LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
				+ milliseconds;
		return bestLapTimeFormatted;
	}
}
