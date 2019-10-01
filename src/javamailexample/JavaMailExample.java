/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamailexample;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Kostis.Mikroulis
 */
public class JavaMailExample {

    static Properties mailserverproperties;
    static Session mailsession;
    static MimeMessage mymailmessage;

    public static void main(String[] args) throws AddressException, MessagingException {
        // STEP [ 01 ]
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        // current system properties.
        mailserverproperties = System.getProperties();
        mailserverproperties.put("mail.smtp.port", "587");
        mailserverproperties.put("mail.smtp.auth", "true");
        mailserverproperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");
        // STEP [ 02 ]
        System.out.println("\n\n 2nd ===> get Mail Session..");

//   Almost all code should use Session.getInstance.
//   The Session.getDefaultInstance method creates a new Session the first time it's 
//   called, using the Properties that are passed. 
//   Subsequent calls will return that original Session 
//   and ignore any Properties you pass in. If you want to
//   create different Sessions 
//   with different properties,
        mailsession = Session.getInstance(mailserverproperties, null);
        System.out.println("Mail Session has been created successfully..");

        // Create mail object.
        mymailmessage = new MimeMessage(mailsession);
        mymailmessage.addRecipient(Message.RecipientType.TO, new InternetAddress("mikroulhs93@gmail.com"));
        mymailmessage.setSubject("With this program,\nThe spam is Real!");

        // Object to hold the parts of the body :
        Multipart multipart = new MimeMultipart();

        //This class represents a MIME body part. It implements the BodyPart 
        //abstract class and the MimePart interface. 
        
        //MimeBodyParts are contained in MimeMultipart objects. 
        // One part for attachement,
        MimeBodyPart mimeBodyPartForAttachment = new MimeBodyPart();
        // one part for text.
        // comment-OUT following and it makes another object to use the attachFIle() method;.
        MimeBodyPart mimeBodyPartforBody = new MimeBodyPart();

//        File attachement = new File("F:\\2Test.docx");
//        try {
//            mimeBodyPartForAttachment.attachFile(attachement);
//        } catch (IOException ex) {
//            Logger.getLogger(JavaMailExample.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String emailBody = "You are in our newssletter!!";
        mimeBodyPartforBody.setText(emailBody);
        multipart.addBodyPart(mimeBodyPartforBody);

        //multipart.addBodyPart(mimeBodyPartForAttachment);
        mymailmessage.setContent(multipart);

        // STEP [ 03 ]
        System.out.println("\n\n 3rd ===> Get Session and Send mail");

        //An abstract class that models a message transport.
        Transport transport = mailsession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        transport.connect("smtp.gmail.com", "mikroulhs93", "tsokotines");
        transport.sendMessage(mymailmessage, mymailmessage.getAllRecipients());
        transport.close();
    }
}
