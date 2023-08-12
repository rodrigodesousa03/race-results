package br.com.rsousa.pojo;

import br.com.rsousa.utils.SessionUtils;

import java.util.Optional;

public class Event {
    private Session qualifySession;
    private Session raceSession;
    private Session originalRaceSession;

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
            setRaceSession(session);
            setOriginalRaceSession(SessionUtils.duplicateRace(session));

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

    public Session getRaceSession() {
        return raceSession;
    }

    public void setRaceSession(Session raceSession) {
        this.raceSession = raceSession;
    }

    public void setOriginalRaceSession(Session originalRaceSession) {
        this.originalRaceSession = originalRaceSession;
    }

    public void clear(boolean isSelective) {
        setRaceSession(null);
        setOriginalRaceSession(null);
        if (!isSelective) {
            setQualifySession(null);
        }
    }

    public void resetRace() {
        setRaceSession(SessionUtils.duplicateRace(originalRaceSession));
    }


}
