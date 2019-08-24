package com.srk.cryptrixevents;

public class pojo_userDetails {
    String participantOneUniqueId;
    String participantTwoUniqueId;
    String participantOne;
    String participantTwo;
    String phoneNumberOne;
    String phoneNumberTwo;
    boolean userRegisteredOne = false;
    boolean userRegisteredTwo = false;
    String collegeNameOne;
    String CollegeNameTwo;
    String barcodeValueOne;
    String barcodeValueTwo;

    private static pojo_userDetails dataHolder = null;

    private pojo_userDetails() {
    }

    public void clearDataHolder(){
        dataHolder = null;
    }
    public static pojo_userDetails getInstanceOf(){
        if(dataHolder == null){
            dataHolder = new pojo_userDetails();
        }
        return dataHolder;
    }
    public void setParticipantOneUniqueId(String participantOneUniqueId) {
        this.participantOneUniqueId = participantOneUniqueId;
    }

    public void setParticipantTwoUniqueId(String participantTwoUniqueId) {
        this.participantTwoUniqueId = participantTwoUniqueId;
    }

    public void setParticipantOne(String participantOne) {
        this.participantOne = participantOne;
    }

    public void setParticipantTwo(String participantTwo) {
        this.participantTwo = participantTwo;
    }

    public void setPhoneNumberOne(String phoneNumberOne) {
        this.phoneNumberOne = phoneNumberOne;
    }

    public void setPhoneNumberTwo(String phoneNumberTwo) {
        this.phoneNumberTwo = phoneNumberTwo;
    }

    public String getParticipantOneUniqueId() {
        return participantOneUniqueId;
    }

    public String getParticipantTwoUniqueId() {
        return participantTwoUniqueId;
    }

    public String getParticipantOne() {
        return participantOne;
    }

    public String getParticipantTwo() {
        return participantTwo;
    }

    public String getPhoneNumberOne() {
        return phoneNumberOne;
    }

    public String getPhoneNumberTwo() {
        return phoneNumberTwo;
    }
    public boolean isUserRegisteredOne() {
        return userRegisteredOne;
    }

    public void setUserRegisteredOne(boolean userRegisteredOne) {
        this.userRegisteredOne = userRegisteredOne;
    }

    public boolean isUserRegisteredTwo() {
        return userRegisteredTwo;
    }

    public void setUserRegisteredTwo(boolean userRegisteredTwo) {
        this.userRegisteredTwo = userRegisteredTwo;
    }

    public String getCollegeNameOne() {
        return collegeNameOne;
    }

    public void setCollegeNameOne(String collegeNameOne) {
        this.collegeNameOne = collegeNameOne;
    }

    public String getCollegeNameTwo() {
        return CollegeNameTwo;
    }

    public void setCollegeNameTwo(String collegeNameTwo) {
        CollegeNameTwo = collegeNameTwo;
    }
    public String getBarcodeValueOne() {
        return barcodeValueOne;
    }

    public void setBarcodeValueOne(String barcodeValueOne) {
        this.barcodeValueOne = barcodeValueOne;
    }

    public String getBarcodeValueTwo() {
        return barcodeValueTwo;
    }

    public void setBarcodeValueTwo(String barcodeValueTwo) {
        this.barcodeValueTwo = barcodeValueTwo;
    }
}
