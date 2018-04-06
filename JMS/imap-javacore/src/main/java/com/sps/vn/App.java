package com.sps.vn;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

import com.sps.vn.Encrypt.EncryptationHelper;

/**
 * Hello world!
 *
 */
public class App {
    
    
    public static void main( String[] args ) throws Exception {
        Folder inbox= loadEmails();
        printMail(inbox);
    }
    
    private static Folder loadEmails() {        
        Properties connectionProperties= new Properties();
        Session session = Session.getDefaultInstance(connectionProperties);
         
        try {
            String storeName = "imap";    
            Store store = session.getStore(storeName);
             
            String server = "ngwnameserver";
            store.connect(server, "tqminh_1@spsvietnam.vn", EncryptationHelper.decrypt("Pb5MM94WMjneklRg2yfAoErlHx0nqoM8"));
                          
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
             
            
            return inbox;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static void printMail(Folder inbox) throws Exception {
        System.out.println("Reading messages.../n");
        
        Message messages[] = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        
        for(Message message:messages) {
            
            for (Address a: message.getFrom()) {
                System.out.println("From:" + a);
            }
            System.out.println("Title: " + message.getSubject());
            System.out.println();
            System.out.println(message.getContent());
            System.out.println("---");
            message.setFlag(Flag.SEEN, false);
        }
        inbox.close(true);
    }
}
