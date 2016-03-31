package com.university.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Mher on 3/31/2016.
 */
@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long personId;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private String organization;
    private String role;
    private String roleDescription;
    @Column(name = "yrs_to_retirement")
    private String yearsToRetirement;
    private String committee;
    private Double lastComp;
    private Double liquidWealth;
    private Double totalWealth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getYearsToRetirement() {
        return yearsToRetirement;
    }

    public void setYearsToRetirement(String yearsToRetirement) {
        this.yearsToRetirement = yearsToRetirement;
    }

    public String getCommittee() {
        return committee;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
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
