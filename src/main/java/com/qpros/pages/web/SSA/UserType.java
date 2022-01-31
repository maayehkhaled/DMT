package com.qpros.pages.web.SSA;

public enum UserType {

    CaseManagerHead("CaseManagerHead","123456"),
    Specialist100("Specialist100", "123456"),
    Superuser("Superuser", "123456"),
    Specialist2("Specialist2", "Specialist2"),
    SeniorSpecialist1("SeniorSpecialist1", "SeniorSpecialist1"),
    SeniorSpecialist5("SeniorSpecialist5", "SeniorSpecialist5"),
    SeniorSpecialist2("SeniorSpecialist2", "SeniorSpecialist2"),
    SeniorSpecialist100("SeniorSpecialist100", "SeniorSpecialist100"),
    Committee100("Committee100", "Committee100"),
    Committee1("Committee1", "Committee1"),
    Committee2("Committee2","Committee2"),
    ApplicationDirector1("ApplicationDirector1", "ApplicationDirector1"),
    PaymentSeniorSpecialist("PaymentSeniorSpecialist", "123456"),
    PaymentSectionHead("PaymentSectionHead", "123456"),
    PaymentSpecialist("PaymentSpecialist", "123456"),
    PaymentSeniorAccountant("PaymentSeniorAccountant", "123456"),
    FinanceTeam("FinanceTeam", "123456");


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    private final String userName;
    private final String password;


    UserType(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}


