package br.com.rsousa.pojo;

import br.com.rsousa.utils.SessionUtils;

public class Event {
    private Session qualifySession;
    private Session raceSession;
    private Session originalRaceSession;

    public void addSession(Session session) {
        if (session == null) {
            return;
        }

        if (session.type() == SessionType.QUALIFY) {
            setQualifySession(session);
        } else {
            setRaceSession(session);
            setOriginalRaceSession(SessionUtils.duplicateRace(session));
        }
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

    public void clear() {
        setRaceSession(null);
        setOriginalRaceSession(null);
        setQualifySession(null);
    }

    public void resetRace() {
        setRaceSession(SessionUtils.duplicateRace(originalRaceSession));
    }


}
