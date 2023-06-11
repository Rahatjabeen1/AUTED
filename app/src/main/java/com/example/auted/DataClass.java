package com.example.auted;

public class DataClass {

    private String dataStudentId;
    private String dataFirstName;
    private String dataLastName;
    private String dataDateOfBirth;
    private String dataGender;



    public String getDataStudentId() {
        return dataStudentId;
    }

    public String getDataFirstName() {
        return dataFirstName;
    }

    public String getDataLastName() {
        return dataLastName;
    }

    public String getDataDateOfBirth() {
        return dataDateOfBirth;
    }
    public String getDataGender() {
        return dataGender;
    }



    public DataClass(  String dataStudentId, String dataFirstName, String dataLastName, String dataDateOfBirth, String dataGender) {


        this.dataStudentId = dataStudentId;
        this.dataFirstName = dataFirstName;
        this.dataLastName = dataLastName;
        this.dataDateOfBirth = dataDateOfBirth;
        this.dataGender = dataGender;
    }
    public DataClass(){

    }
}