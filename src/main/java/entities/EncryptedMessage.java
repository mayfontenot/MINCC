package entities;

import java.io.Serializable;

/**
 * @author May Fontenot (may@mayware.net)
 */
public class EncryptedMessage implements Serializable {
    private final Contact sender;
    private final Contact[] recipients;
    private final byte[] content;
    private final byte[] signature;
    private final byte[] mimeType;
    private final byte[] fileName;

    public EncryptedMessage(Contact sender, Contact[] recipients, byte[] content, byte[] signature, byte[] mimeType) {
        this.sender = sender;
        this.recipients = recipients;
        this.content = content;
        this.signature = signature;
        this.mimeType = mimeType;
        this.fileName = null;
    }
    
    public EncryptedMessage(Contact sender, Contact[] recipients, byte[] content, byte[] signature, byte[] mimeType, byte[] fileName) {
        this.sender = sender;
        this.recipients = recipients;
        this.content = content;
        this.signature = signature;
        this.mimeType = mimeType;
        this.fileName = fileName;
    }

    public Contact getSender() {
        return sender;
    }

    public Contact[] getRecipients() {
        return recipients;
    }

    public byte[] getContent() {
        return content;
    }

    public byte[] getSignature() {
        return signature;
    }

    public byte[] getMimeType() {
        return mimeType;
    }

    public byte[] getFileName() {
        return fileName;
    }
}
