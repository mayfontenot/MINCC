package security;

import database.DatabaseManager;
import entities.Contact;
import entities.EncryptedMessage;
import entities.Message;
import java.security.PrivateKey;
import javax.crypto.SecretKey;

/**
 * Handles encryption and decryption of messages
 * @author May Fontenot (may@mayware.net)
 */
public class SecurityManager {
    private static PrivateKey privateKey;
    private static SecretKey secretKey;
    
    public static Message encryptAndSign(Contact recipient, Message m) {
        return null;
    }
    
    public static Message decryptAndVerify(EncryptedMessage m) {
        return null;
    }
    
    public static void generateNewKeyPair() {
        DatabaseManager.getMyContact().setPublicKey(new byte[] {});
    }
}
