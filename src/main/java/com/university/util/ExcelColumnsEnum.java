package com.university.util;

/**
 * Created by Mher on 3/30/2016.
 */
public enum ExcelColumnsEnum {

    ORGANISATION("Organisation"),
    ORG_TYPE("Org Type"),
    INDIVIDUAL("Individual"),
    CONNECTED_ORG("Connected Org Current / Historic"),
    CONNECTED_ORG_DURATION("Connected Org Duration"),
    CONNECTED_ORG_ROLE("Connected Org Role"),
    CONNECTED_ORG_ROLE_START("Connected Org Role Start Date"),
    CONNECTED_ORG_ROLE_END("Connected Org Role End Date"),
    ESPRINET_SPA("ESPRINET SPA Current / Historic"),
    ESPRINET_SPA_DURATION("ESPRINET SPA Duration"),
    ESPRINET_SPA_ROLE("ESPRINET SPA Role"),
    ESPRINET_SPA_ROLE_START("ESPRINET SPA Role Start Date"),
    ESPRINET_SPA_ROLE_SEND("ESPRINET SPA Role End Date");

    private String value;

    public String getValue() {
        return value;
    }

    ExcelColumnsEnum(String value) {
        this.value = value;
    }
}
