package br.com.rsousa.pojo;

public class Event {
    private Session qualifySession;
    private Session raceSession;

    public void addSession(Session session) {
        if (session == null) {
            return;
        }

        if (session.type() == SessionType.QUALIFY) {
            setQualifySession(session);
        } else {
            setRaceSession(session);
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

    public void clear() {
        setRaceSession(null);
        setQualifySession(null);
    }
}
