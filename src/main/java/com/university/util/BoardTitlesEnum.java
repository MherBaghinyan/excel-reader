package com.university.util;

/**
 * Created by Mher on 3/30/2016.
 */
public enum BoardTitlesEnum {

    PROFILE("Profile"),
    CURRENT_POSITIONS("Current Positions"),
    DETAILS("Details"),
    PAST_POSITIONS("Past Positions"),
    CURRENT_OTHER_ACTIVITIES("Current Other Activities"),
    EDUCATION("Education"),
    PAST_OTHER_ACTIVITIES("Past Other Activities");

    private String value;

    public String getValue() {
        return value;
    }

    BoardTitlesEnum(String value) {
        this.value = value;
    }
}
