package com.bridgelabz.crickinfo.model;

public class matchDataModel {

    private int unique_id;
    private String matchType;
    private String matchFirstTeam;
    private String matchSecTeam;
    private String matchToss;
    private String matchTime;

    public matchDataModel() {
    }

    public matchDataModel(int unique_id,String matchType, String matchFirstTeam, String matchSecTeam, String matchToss, String matchTime) {
        this.unique_id = unique_id;
        this.matchType = matchType;
        this.matchFirstTeam = matchFirstTeam;
        this.matchSecTeam = matchSecTeam;
        this.matchToss = matchToss;
        this.matchTime = matchTime;
    }

    public int getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMatchFirstTeam() {
        return matchFirstTeam;
    }

    public void setMatchFirstTeam(String matchFirstTeam) {
        this.matchFirstTeam = matchFirstTeam;
    }

    public String getMatchSecTeam() {
        return matchSecTeam;
    }

    public void setMatchSecTeam(String matchSecTeam) {
        this.matchSecTeam = matchSecTeam;
    }

    public String getMatchToss() {
        return matchToss;
    }

    public void setMatchToss(String matchToss) {
        this.matchToss = matchToss;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }
}
