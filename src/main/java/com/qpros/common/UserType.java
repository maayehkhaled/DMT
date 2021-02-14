package com.qpros.common;

    public enum UserType {
        B2C("mdotoum","1937_Mohd");

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


