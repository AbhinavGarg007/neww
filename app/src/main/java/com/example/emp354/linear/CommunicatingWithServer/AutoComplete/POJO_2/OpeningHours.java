package com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO_2;

import java.util.List;

public class OpeningHours {

    private boolean open_now;
    private List<String> weekday_text;
    private List<Period> periods;

    public boolean isOpen_now() {
        return open_now;
    }

    public void setOpen_now(boolean open_now) {
        this.open_now = open_now;
    }

    public List<String> getWeekday_text() {
        return weekday_text;
    }

    public void setWeekday_text(List<String> weekday_text) {
        this.weekday_text = weekday_text;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
