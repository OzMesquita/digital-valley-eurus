package ufc.russas.encontrosuniversitarios.model.dao.webservice;

import org.joda.time.DateTime;

public class StartingTime {
    private boolean isStartingTime;
    private DateTime time;

    public StartingTime(boolean isStartingTime, DateTime time) {
        this.isStartingTime = isStartingTime;
        this.time = time;
    }

    public boolean isStartingTime() {
        return isStartingTime;
    }

    public void setStartingTime(boolean startingTime) {
        isStartingTime = startingTime;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public DateTime getTime() {
        return time;
    }
}
