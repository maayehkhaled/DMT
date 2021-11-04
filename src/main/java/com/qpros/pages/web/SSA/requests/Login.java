package com.qpros.pages.web.SSA.requests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Login {
    private String sessionLogin;
    public void sendLogin() throws UnirestException {
        Unirest.setTimeouts(300, 300);
        HttpResponse<String> response = Unirest.get("https://uat.ssa.gov.ae/DCDAgentFrontEnd/ApplicationsList.aspx")
                .header("host", "uat.ssa.gov.ae")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:91.0) Gecko/20100101 Firefox/91.0")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("accept-language", "en-GB,en;q=0.5")
                .header("accept-encoding", "gzip, deflate, br")
                .header("connection", "keep-alive")
                .header("referer", "https://uat.ssa.gov.ae/AgentPortal_CW/Login.aspx")
                .header("cookie", "ASP.NET_SessionId=4j1tdujzfmu5cb5aw1zjjgvi; osVisitor=88a0fa8e-3017-4e5e-b2ec-ac9ab5a86c11; osVisit=637c682f-db9f-4511-9f57-ff5083ff91a6; pageLoadedFromBrowserCache=true; DEVICE_TYPE=desktop hd; DEVICE_SIMULATION=true; Users.sid=151293602760757394601035924487257765998; RT=s=1629799979981&r=https%3A%2F%2Fuat.ssa.gov.ae%2FAgentPortal_CW%2FLogin.aspx")
                .header("upgrade-insecure-requests", "1")
                .header("sec-fetch-dest", "document")
                .header("sec-fetch-mode", "navigate")
                .header("sec-fetch-site", "same-origin")
                .header("sec-fetch-user", "?1")
                .header("x-postman-captr", "2775938")
                .asString();
    }

    public void testLogin() throws UnirestException {

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("https://uat.ssa.gov.ae/AgentPortal_CW/LoginPage.aspx?_ts=1629800372236")
                .header("host", "uat.ssa.gov.ae")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:91.0) Gecko/20100101 Firefox/91.0")
                .header("accept", "text/plain, */*; q=0.01")
                .header("accept-language", "en-GB,en;q=0.5")
                .header("accept-encoding", "gzip, deflate, br")
                .header("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("x-requested-with", "XMLHttpRequest")
                .header("content-length", "584")
                .header("origin", "https://uat.ssa.gov.ae")
                .header("connection", "keep-alive")
                .header("referer", "https://uat.ssa.gov.ae/AgentPortal_CW/Login.aspx")
                .header("cookie", "ASP.NET_SessionId=" + sessionLogin + "; osVisitor=88a0fa8e-3017-4e5e-b2ec-ac9ab5a86c11; osVisit=637c682f-db9f-4511-9f57-ff5083ff91a6; pageLoadedFromBrowserCache=true; DEVICE_TYPE=desktop hd; DEVICE_SIMULATION=true; Users.sid=151293602760757394601035924487257765998; RT=s=1629800098080&r=https%3A%2F%2Fuat.ssa.gov.ae%2FDCDAgentFrontEnd%2FApplicationsList.aspx; ECT_ClickedFeedback=|976|")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-origin")
                .header("x-postman-captr", "7501276")
                .body("__EVENTTARGET=wt13%24wtMainContent%24wtLogin&__EVENTARGUMENT=&__OSVSTATE=sZ4aDiBwBbzZDxnlVI4mOI76HzrMN%2FYo4cmp7mFtKMAq%2B5RS%2B9spmv8eIS1OV5qR%2BniJ1FPNJup2ZKisR%2FyyuEZStPgosbacO2pbkaCxnsuGZfF72wetnmV01%2Fz2zMJM4h8odL6oj8nwuAy2y%2Foq%2FarHyG%2Fzw8uFzRJ2UEFadELiUmcFVekBmDmCOlBJ%2FIFvCLpcwHsdQ3jbxGPPDOb7yQ%3D%3D&__VIEWSTATE=&__VIEWSTATEGENERATOR=BDAC5822&wt13%24wtMainContent%24wt6=Specialist2&wt13%24wtMainContent%24wt18=Specialist2&wt13%24WebPatterns_wt9%24block%24wt4%24wt43%24wtCheckbox%24wt37=on&__AJAX=1903%2C861%2Cwt13_wtMainContent_wtLogin%2C374%2C696%2C0%2C0%2C814%2C424%2C")
                .asString();

    }


}
