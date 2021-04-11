package com.qpros.common;

import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.web.data.Files;

public enum UserType {
        LoginUser("farahm@q-pros.net","Qpros@2022"),
        LoginUser2(ReadWriteHelper.readCSVFile("C:\\Users\\Khaled Maayeh\\OneDrive - Quality Professionals",Files.Login_Credentials, 1, 2)[0][0],ReadWriteHelper.readCSVFile("C:\\Users\\Khaled Maayeh\\OneDrive - Quality Professionals",Files.Login_Credentials, 1, 2)[0][1]),
        LoginUser3("dutest123456@gmail.com","Dubai@12345"),
        LoginUser4("Amr3333@gmail.com","Amr@3333"),
        ChangePassword("Lamias@q-pros.com","Qpros@2022"),
        ChangePassword2("qpros@gmail.com","Qpros@3033"),
        PaymentUser("",""),
        User("dutest123456@gmail.com", "Dubai@12345");

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }

        private final String userName;
        private final String password;


        private UserType(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
    }


