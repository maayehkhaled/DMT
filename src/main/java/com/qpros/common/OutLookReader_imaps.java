package com.qpros.common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

public class OutLookReader_imaps {

    Folder inbox;
    String link;

    // Constructor of the calss.

    public OutLookReader_imaps() {
    }

    public String getFromEmailTheLink() {
        System.out.println("Inside MailReader()...");
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        // Set manual Properties
        props.setProperty("mail.imaps.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imaps.socketFactory.fallback", "false");
        props.setProperty("mail.imaps.port", "993");
        props.setProperty("mail.imaps.socketFactory.port", "993");
        props.put("mail.imaps.host", "outlook.office365.com");


        try {
            /* Create the session and get the store for read the mail. */

            Session session = Session.getDefaultInstance(System.getProperties(), null);
            Store store = session.getStore("imaps");

            store.connect("outlook.office365.com", 993, "khaledm@q-pros.com", "QPros123");

            /* Mention the folder name which you want to read. */


            inbox = store.getFolder("INBOX");

            /* Open the inbox using store. */

            //inbox.open(Folder.READ_ONLY);
            inbox.open(Folder.READ_WRITE);

            Message messages[] = inbox.search(new FlagTerm(new Flags(
                    Flags.Flag.ANSWERED), false));
            //Message[] msgs = inbox.getMessages();

            System.out.println("No. of Unread Messages : " + inbox.getUnreadMessageCount());
            System.out.println("No. of Messages : " + inbox.getMessageCount());
            System.out.println("No. of Deleted Messages : " + inbox.getMode());

            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);

            inbox.fetch(messages, fp);

            try {

                //printAllMessages(messages);
                 link = getLatestMessage(messages);

                inbox.close(true);
                store.close();
                return link;
            } catch (Exception ex) {
                System.out.println("Exception arise at the time of read mail");
                ex.printStackTrace();
            }

        } catch (MessagingException e) {
            System.out.println("Exception while connecting to server: " + e.getLocalizedMessage());
            e.printStackTrace();
            System.exit(2);
        }

        return link;
    }

    public void printAllMessages(Message[] msgs) throws Exception {
        for (int i = 0; i < msgs.length; i++) {
            System.out.println("MESSAGE #" + (i + 1) + ":");
            printEnvelope(msgs[i]);
        }
    }

    public String getLatestMessage(Message[] msgs) throws Exception {
        System.out.print(msgs[msgs.length - 1]);

        return printEnvelope(msgs[msgs.length - 1]);


    }

    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                System.out.print("plain email");
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                System.out.print("Html email");
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).html();
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
            }
        }
        return result;
    }

    public String printEnvelope(Message message) throws Exception {

        Address[] a;

        if ((a = message.getFrom()) != null) {
            for (int j = 0; j < a.length; j++) {
                System.out.println("Email From : " + a[j].toString());
            }
        }

        String subject = message.getSubject();

        Date receivedDate = message.getReceivedDate();
        Date sentDate = message.getSentDate();


        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Email Subject : " + subject);

        if (receivedDate != null) {
            System.out.println("Received Date: " + df.format(receivedDate));
        }

        System.out.println("Sent Date : " + df.format(sentDate));
        String html = getTextFromMessage(message);
        Document doc = Jsoup.parse(html);
        Element link = doc.select("a").get(0);

        String text = doc.body().text(); // "An example link"
        String linkHref = link.attr("href"); // "http://example.com/"
        String linkText = link.text(); // "example""

        String linkOuterH = link.outerHtml();
        // "<a href="http://example.com"><b>example</b></a>"
        String linkInnerH = link.html(); // "<b>example</b>"
        System.out.println("link Outer: " + linkOuterH);
        System.out.println("link Inner: " + linkInnerH);
        System.out.println("link text: " + linkText);
        System.out.println("link href: " + linkHref);
        return linkHref;
    }


    public static void main(String args[]) {
        new OutLookReader_imaps();
    }
}
