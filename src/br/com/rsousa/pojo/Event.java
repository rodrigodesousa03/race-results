package br.com.rsousa.pojo;

import br.com.rsousa.utils.SessionUtils;

import java.util.Optional;

public class Event {
    private Session qualifySession;
    private Session raceSession;
    private Session originalRaceSession;

    public void addSession(Session session, boolean isSeletiva) {
        if (session == null) {
            return;
        }

        if (isSeletiva) {
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
                Optional<Driver> polepositionDriver = qualifySession.polepositionDriver();

                if (polepositionDriver.isPresent()) {
                    session.drivers().stream()
                            .filter(d -> d.getName().equals(polepositionDriver.get().getName()))
                            .forEach(d -> d.setPoleposition(true));
                }
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

    public void clear(boolean isSeletiva) {
        setRaceSession(null);
        setOriginalRaceSession(null);
        if (!isSeletiva) {
            setQualifySession(null);
        }
    }

    public void resetRace() {
        setRaceSession(SessionUtils.duplicateRace(originalRaceSession));
    }


}
