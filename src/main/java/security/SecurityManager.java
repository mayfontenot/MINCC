package security;

import entities.Contact;
import entities.Message;
import java.security.PrivateKey;
import javax.crypto.SecretKey;

/**
 *
 * @author azurith
 */
public class SecurityManager {
    private static PrivateKey privateKey;
    private static SecretKey secretKey;
    
    public static Message encryptAndSign(Contact recipient, Message m) {
        return null;   
    }
    
    public static Message decryptAndVerify(Message m) {
        return null;
    }
}
