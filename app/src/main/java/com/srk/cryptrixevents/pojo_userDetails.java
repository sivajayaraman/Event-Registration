package com.srk.cryptrixevents;

public class pojo_userDetails {
    String p1;
    String p2;
    String n1;
    String n2;
    String pn1;
    String pn2;
    boolean r1 = false;
    boolean r2 = false;
    String c1;
    String c2;
    String b1;
    String b2;

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
    public void setp1(String p1) {
        this.p1 = p1;
    }

    public void setp2(String p2) {
        this.p2 = p2;
    }

    public void setn1(String n1) {
        this.n1 = n1;
    }

    public void setn2(String n2) {
        this.n2 = n2;
    }

    public void setpn1(String pn1) {
        this.pn1 = pn1;
    }

    public void setpn2(String pn2) {
        this.pn2 = pn2;
    }

    public String getp1() {
        return p1;
    }

    public String getp2() {
        return p2;
    }

    public String getn1() {
        return n1;
    }

    public String getn2() {
        return n2;
    }

    public String getpn1() {
        return pn1;
    }

    public String getpn2() {
        return pn2;
    }
    public boolean isr1() {
        return r1;
    }

    public void setr1(boolean r1) {
        this.r1 = r1;
    }

    public boolean isr2() {
        return r2;
    }

    public void setr2(boolean r2) {
        this.r2 = r2;
    }

    public String getc1() {
        return c1;
    }

    public void setc1(String c1) {
        this.c1 = c1;
    }

    public String getc2() {
        return c2;
    }

    public void setc2(String c2) {
        this.c2 = c2;
    }
    public String getb1() {
        return b1;
    }

    public void setb1(String b1) {
        this.b1 = b1;
    }

    public String getb2() {
        return b2;
    }

    public void setb2(String b2) {
        this.b2 = b2;
    }
}