package com.solvd.university.domain;

public class Building {
    private Long buildingId;
    private String buildingName;
    private Long classroomId;

    public Building() {
    }

    public Building(Long buildingId, String buildingName, Long classroomId) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.classroomId = classroomId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }
}
