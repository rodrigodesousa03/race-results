package br.com.rsousa.pojo;

import br.com.rsousa.utils.SessionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Event {
    private Session qualifySession;
    private List<Session> raceSessions;
    private List<Session> originalRaceSessions;

    public void addSession(Session session, boolean isSelective) {
        if (session == null) {
            return;
        }

        if (isSelective) {
            if (qualifySession != null) {
                joinSession(session);
            } else {
                setQualifySession(session);
            }
        } else if (session.type() == SessionType.QUALIFY) {
            setQualifySession(session);
        } else {
            raceSessions.add(session);
            originalRaceSessions.add(SessionUtils.duplicateRace(session));

            if (qualifySession != null) {
                Optional<Driver> polePositionDriver = qualifySession.polePositionDriver();

                polePositionDriver.ifPresent(driver -> session.drivers().stream()
                        .filter(d -> d.getName().equals(driver.getName()))
                        .forEach(d -> d.setPolePosition(true)));
            }
        }
    }

    private void joinSession(Session session) {
        qualifySession.addDrivers(session.drivers());
    }

    public Session getQualifySession() {
        return qualifySession;
    }

    public void setQualifySession(Session qualifySession) {
        this.qualifySession = qualifySession;
    }

    public List<Session> getRaceSessions() {
        return raceSessions;
    }

    public void setRaceSessions(List<Session> raceSessions) {
        this.raceSessions = raceSessions;
    }

    public void setRaceSessions(Session raceSession) {
        List<Session> raceSessionsList = new ArrayList<>();
        raceSessionsList.add(raceSession);

        this.raceSessions = raceSessionsList;
    }

    public void setOriginalRaceSessions(List<Session> originalRaceSessions) {
        this.originalRaceSessions = originalRaceSessions;
    }

    public void clear(boolean isSelective) {
        setRaceSessions(new ArrayList<>());
        setOriginalRaceSessions(null);
        if (!isSelective) {
            setQualifySession(null);
        }
    }

    public void resetRace() {
        originalRaceSessions.stream()
                .map(SessionUtils::duplicateRace)
                .forEach(raceSessions::add);
    }


}
