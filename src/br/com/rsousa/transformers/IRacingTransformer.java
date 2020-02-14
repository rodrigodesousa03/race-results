package br.com.rsousa.transformers;

import static br.com.rsousa.enums.FileType.QUALIFY;
import static br.com.rsousa.enums.FileType.RACE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import br.com.rsousa.enums.FileType;
import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.iracing.DriverSession;

public class IRacingTransformer {
	private static final int FIRST_LINE = 10;

	public static void processQualify(File file, List<Driver> driverTeams, Map<FileType, String> results) {
		BufferedReader br = null;
        String line = "";
        int lineNumber = 1;
        String result = "";
        
        if (file != null) {
			try {
	            br = new BufferedReader(new FileReader(file));
	            while ((line = br.readLine()) != null || lineNumber <= FIRST_LINE) {

	            	if (lineNumber >= FIRST_LINE) {
		                DriverSession driverSession = transformLineInDriverSession(file, driverTeams, results, line);
	            		
		                if (lineNumber == FIRST_LINE && driverSession.getQualifyTime().isEmpty()) {
		                		processRace(file, driverTeams, results);
		                		
		                		return;
		                }
		                
		                if (driverSession != null) {
		                		result += resultLine(driverSession.getFinalPosition(), driverSession, driverSession.getFastLap(), driverTeams) + "\n";
		                }
	            	}
	            	
	            	lineNumber++;
	            }
	            
	            results.put(QUALIFY, result);
	        } catch (Exception e) {
				results.put(QUALIFY, "Error transforming the CSV file.");
				
				e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		}
        
 
	}

	public static void processRace(File file, List<Driver> driverTeams, Map<FileType, String> results) {
		BufferedReader br = null;
        String line = "";
        int lineNumber = 1;
        String result = "";
		DriverSession driverBestLap = null;
        
        if (file != null) {
			try {
	            br = new BufferedReader(new FileReader(file));
	            while ((line = br.readLine()) != null || lineNumber <= FIRST_LINE) {
	            	if (lineNumber >= FIRST_LINE) {
		                DriverSession driverSession = transformLineInDriverSession(file, driverTeams, results, line);
	            		
		                if (lineNumber == FIRST_LINE) {
		                		result += resultLine(driverSession.getFinalPosition(), driverSession, driverSession.getCompletedLaps() + " Laps", driverTeams) + "\n";
		                		
		                		driverBestLap = driverSession;
		                } else {
				            if (driverSession != null) {
				            		if (!driverSession.getFastLap().trim().isEmpty() && driverBestLap.getFastLap().compareTo(driverSession.getFastLap()) > 0) {
									driverBestLap = driverSession;
								}
				            		
				            		String formattedTime = driverSession.getOut().equals("Disqualified") ? driverSession.getInterval() + " (DNF)" : driverSession.getInterval();
				            	
		                			result += resultLine(driverSession.getFinalPosition(), driverSession, formattedTime, driverTeams) + "\n";
				            }
		                }
	            	}
	            	
	            	lineNumber++;
	            }
	            
	            result += "Volta mais rápida: " + driverBestLap.getName() + ", "
						+ driverBestLap.getFastLap();
	            
	        	results.put(RACE, result);
	        } catch (Exception e) {
				results.put(RACE, "Error transforming the CSV file.");
	        	
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		}
	}
	
	private static DriverSession transformLineInDriverSession(File file, List<Driver> driverTeams, Map<FileType, String> results,
			String line) {
		String[] driverSessionArray = line.replaceAll("\"", "").split(",");
		
		if (driverSessionArray[0].equals("Fin Pos")) {
			return null;
		}
		
		DriverSession driverSession = new DriverSession();
		driverSession.setFinalPosition(Integer.parseInt(driverSessionArray[0]));
		driverSession.setId(driverSessionArray[6]);
		driverSession.setName(driverSessionArray[7]);
		driverSession.setOut(driverSessionArray[11]);
		driverSession.setInterval(driverSessionArray[12]);
		driverSession.setQualifyTime(driverSessionArray[14]);
		driverSession.setFastLap(driverSessionArray[16]);
		driverSession.setCompletedLaps(Integer.parseInt(driverSessionArray[18]));
		
		return driverSession;
	}

	private static String resultLine(int position, DriverSession driver, String time, List<Driver> driverTeams) {
		String driverTeamName = driverTeams.stream().filter(d -> driver.getId().equals(d.getId()))
				.map(d -> d.getTeam())
				.findAny()
				.orElse("Independente");
		
		String name = driverTeams.stream().filter(d -> driver.getId().equals(d.getId()))
				.map(d -> d.getName())
				.findAny()
				.orElse(driver.getName());
		
		time = !time.trim().isEmpty() ? time : "sem tempo";
		
		return position + " " + name + " (" + driverTeamName + "), " + time;
	}
}
