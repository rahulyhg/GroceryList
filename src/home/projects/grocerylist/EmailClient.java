package home.projects.grocerylist;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * This class will send an email with the grocery list items. It uses the properties from the emailconfig.properties file
 */
public class EmailClient {


    Properties prop = new Properties();
    InputStream input = null;
    private MimeMessage message;

    public EmailClient() {

        try {
            input = new FileInputStream("emailconfig.properties");
            prop.load(input);  //reading the file into the prop object
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This method will read the content of the fileNameWithPath and parse it into the grocery item and quantity
     *
     * @param  messageBody - the content of the email
     * @param  subject - this is the subject of the email
     */
    public void sendEmail(String messageBody, String subject) {
        try {
            Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(prop.getProperty("email.username"), //email.username is a property
                            prop.getProperty("email.password"));
                }
            });
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(prop.getProperty("from.email")));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(prop.getProperty("receipient.email"))); //sets the to address
            message.setSubject(subject);
            message.setContent(messageBody, "text/html");

            //once everything is ready, we call the send method to send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (Exception me) {
            me.printStackTrace();
        }
    }
}
