package com.example.mycrypto;

public class ReadWriteUserDetails {
        public String firstName, lastName, cardNumber, cardName, cardExp, username;
        //constructor
        public ReadWriteUserDetails(){};

        public ReadWriteUserDetails(String textFirstName , String textLastName,String textUserName, String textCardNumber, String textCardName, String textCardExp) {
            this.firstName = textFirstName;
            this.lastName = textLastName;
            this.username=textUserName;
            this.cardNumber = textCardNumber;
            this.cardName = textCardName;
            this.cardExp = textCardExp;

        }
        }
