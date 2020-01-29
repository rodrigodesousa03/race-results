package br.com.rsousa.transformers;

import static br.com.rsousa.enums.FileType.QUALIFY;
import static br.com.rsousa.enums.FileType.RACE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import br.com.rsousa.enums.FileType;
import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.assetto.Lap;
import br.com.rsousa.pojo.assetto.Result;
import br.com.rsousa.pojo.assetto.Session;

public class AssettoTransformer {
	private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");
	
	public static void processQualify(File file, List<Driver> driverTeams, Map<FileType, String> results) {
		String resultStr = "";
		
		if (file != null) {
			try {
				Session session = createSession(file);
				
				if ("RACE".equals(session.getType())) {
					processRace(file, driverTeams, results);
					return;
				}
				
				int position = 1;
				
				for (Result result : session.getResult()) {
					if (isDriver(result.getDriverName()) && result.getBestLap() != 999999999) {
						
						resultStr += resultLine(position, result.getDriverName(), formatSeconds(result.getBestLap()), driverTeams) + "\n";
						
						position++;
					}
				}
				
				results.put(QUALIFY, resultStr);
			} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
				e.printStackTrace();
				
				results.put(QUALIFY, "Error transforming the JSON file.");
			}
		}
	}
	
	public static void processRace(File file, List<Driver> driverTeams, Map<FileType, String> results) {
		String resultStr = "";
		
		if (file != null) {
			try {
				Session session = createSession(file);
				
				int position = 1;
				Result driverBestLap = null;
				long totalLaps = 0;
				List<Lap> laps = session.getLaps();
				int leaderFinishTime = 0;
				String raceTimeFormatted = null;
				
				for (Result result : session.getResult()) {
					if (isDriver(result.getDriverName()) && result.getBestLap() != 999999999) {
						long driverLaps = lapsFor(result.getDriverName(), laps);
						if (position == 1) {
							driverBestLap = result;
							raceTimeFormatted = driverLaps + " voltas";
							totalLaps = driverLaps;
							leaderFinishTime = result.getTotalTime();
						} else {
							if (driverBestLap.getBestLap().compareTo(result.getBestLap()) > 0) {
								driverBestLap = result;
							}
							
							if (totalLaps == driverLaps) {
								int secondsBehindTheLeader = result.getTotalTime() - leaderFinishTime;
								
								raceTimeFormatted = formatSeconds(secondsBehindTheLeader);
								
							} else {
								long lapsBehindTheLeader = totalLaps - driverLaps;
								
								raceTimeFormatted = "+ " + lapsBehindTheLeader + " laps";
							}
						}
						
						resultStr += resultLine(position, result.getDriverName(), raceTimeFormatted, driverTeams) + "\n";
						
						position++;
					}
				}
				
				resultStr += "Volta mais rápida: " + driverBestLap.getDriverName() + ", "
						+ formatSeconds(driverBestLap.getBestLap());
				
				results.put(RACE, resultStr);
			} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
				e.printStackTrace();
				
				results.put(RACE, "Error transforming the JSON file.");
			}
		}
	}
	
	private static long lapsFor(String driverName, List<Lap> laps) {
		return laps.stream().filter(l -> l.getDriverName().equals(driverName)).count();
	}

	private static Session createSession(File file) throws FileNotFoundException {
		Gson gson = new Gson();
		
		return gson.fromJson(new FileReader(file), Session.class);
	}
	
	private static String resultLine(int position, String name, String time, List<Driver> driverTeams) {
		String driverTeamName = driverTeams.stream().filter(d -> name.equals(d.getName()))
				.map(d -> d.getTeam())
				.findAny()
				.orElse("Independente");
		
		return position + " " + name + " (" + driverTeamName + "), " + time;
	}
	
	private static String formatSeconds(int time) {
		String bestLapTime = String.valueOf(time);

		Integer totalSeconds = Integer.parseInt(bestLapTime.substring(0, bestLapTime.length() - 3));
		String milliseconds = bestLapTime.substring(bestLapTime.length() - 3);

		String bestLapTimeFormatted = LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
				+ milliseconds;
		
		return bestLapTimeFormatted;
	}
	
	private static boolean isDriver(String name) {
		return !name.isEmpty() && !name.contains("Diretor") && !name.contains("Comentarista") && !name.contains("Narrador");
	}
}
