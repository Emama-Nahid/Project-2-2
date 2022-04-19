package com.example.C_Learning;

public class QustionModel {

    private String qustion,optionA,optionB,optionC,optionD,correctANS;

    private int setNo;

    public QustionModel(){
        //for firebase
    }

    public String getQustion() {
        return qustion;
    }

    public void setQustion(String qustion) {
        this.qustion = qustion;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectANS() {
        return correctANS;
    }

    public void setCorrectANS(String correctANS) {
        this.correctANS = correctANS;
    }

    public int getSetNo() {
        return setNo;
    }

    public void setSetNo(int setNo) {
        this.setNo = setNo;
    }

    public QustionModel(String qustion, String optionA, String optionB, String optionC, String optionD, String correctANS, int setNo) {
        this.setNo=setNo;
        this.qustion = qustion;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctANS = correctANS;
    }
}
