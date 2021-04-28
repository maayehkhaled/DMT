package com.qpros.common;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.UnsupportedEncodingException;

public class ConnectToOffice365REST {

    /**
     * This methods accepts a username and password of the Office 365   * mailbox and send API request to Office 365 Server. Which returns * json object with list of unread mails.
     *
     * @param user
     * @param password
     * @return JSON object in string format.
     */

    public static String getMessagesFromOffice(String user, String password) {

        JsonNode jsonObject = null;

        HttpResponse<JsonNode> response;

        String returnString = "";

        try {

            // It send request to get list of unread mails from
            // inbox folder of mailbox and does basic
            // authentication with provided username and password

            response = Unirest.get("https://outlook.office.com/api/v2.0/me/messages?$filter=" +java.net.URLEncoder.encode("IsRead eq false", "UTF-8")).basicAuth(user,password).asJson();

            System.out.println("request : "+"https://outlook.office.com/api/v2.0/me/messages?$filter=" +java.net.URLEncoder.encode("IsRead eq false", "UTF-8"));
            System.out.println("response : "+response.getBody());

            jsonObject = response.getBody();

            if (response.getBody() == null) {

                returnString = "NULL";

            } else {

                returnString = jsonObject.toString();

            }

        } catch (UnsupportedEncodingException | UnirestException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return returnString;

    }


    public static void main (String []args){
        getMessagesFromOffice("khaledm@q-pros.com","QPros123");
    }
}
