package com.vroong.newbee.Java8.junit;

public class Study {
  private StudyStatus status = StudyStatus.DRAFT;

  private int limit;

  private boolean isWithinLimit;

  private String name;

  public Study() {
  }

  public Study(int limit) {
    this.limit = limit;
  }

  public Study(int limit, String name) {
    this.limit = limit;
    this.name = name;
  }

  public Study(int limit, boolean isWithinLimit) {
    this.limit = limit;
    this.isWithinLimit = isWithinLimit;
  }

  public StudyStatus getStatus() {
    return status;
  }

  public int getLimit() {
    return limit;
  }

  public String getName() {
    return name;
  }

  public boolean isWithinLimit(){
    this.isWithinLimit = limit < 10;
    return this.isWithinLimit;
  }

  @Override
  public String toString() {
    return "Study{" +
        "status=" + status +
        ", limit=" + limit +
        ", name='" + name + '\'' +
        '}';
  }
}
