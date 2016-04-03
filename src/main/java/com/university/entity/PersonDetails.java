package com.university.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Mher on 3/31/2016.
 */
@Entity
public class PersonDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long personId;
    private long currentCommRole;
    private long historicCommRole;
    private Double lastComp;
    private Double liquidWealth;
    private Double totalWealth;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public long getCurrentCommRole() {
        return currentCommRole;
    }

    public void setCurrentCommRole(long currentCommRole) {
        this.currentCommRole = currentCommRole;
    }

    public long getHistoricCommRole() {
        return historicCommRole;
    }

    public void setHistoricCommRole(long historicCommRole) {
        this.historicCommRole = historicCommRole;
    }

    public Double getLastComp() {
        return lastComp;
    }

    public void setLastComp(Double lastComp) {
        this.lastComp = lastComp;
    }

    public Double getLiquidWealth() {
        return liquidWealth;
    }

    public void setLiquidWealth(Double liquidWealth) {
        this.liquidWealth = liquidWealth;
    }

    public Double getTotalWealth() {
        return totalWealth;
    }

    public void setTotalWealth(Double totalWealth) {
        this.totalWealth = totalWealth;
    }
}
