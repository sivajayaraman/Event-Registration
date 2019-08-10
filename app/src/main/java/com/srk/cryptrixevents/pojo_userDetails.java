package com.srk.cryptrixevents;

public class pojo_userDetails {
    String participantOneUniqueId;
    String participantTwoUniqueId;
    String participantOne;
    String participantTwo;
    String phoneNumberOne;
    String PhoneNumberTwo;
    private static pojo_userDetails dataHolder = null;

    private pojo_userDetails() {
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
        PhoneNumberTwo = phoneNumberTwo;
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
        return PhoneNumberTwo;
    }
}
