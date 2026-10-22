package entities;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author May Fontenot (may@mayware.net)
 */
public final class Message {
    private final int messageId;
    private final Contact sender;
    private final byte[] content;
    private final byte[] signature;
    private final String mimeType;
    private final String fileName;
    private final Timestamp timeReceived;

    public Message(Contact sender, byte[] content, byte[] signature, String mimeType) {
        messageId = hashCode();
        this.sender = sender;
        this.content = content;
        this.signature = signature;
        this.mimeType = mimeType;
        this.fileName = null;
        this.timeReceived = Timestamp.from(Instant.now());
    }
    
    public Message(int messageId, Contact sender, byte[] content, byte[] signature, String mimeType, String fileName, Timestamp timeReceived) {
        this.messageId = messageId;
        this.sender = sender;
        this.content = content;
        this.signature = signature;
        this.mimeType = mimeType;
        this.fileName = fileName;
        this.timeReceived = timeReceived;
    }

    public int getMessageId() {
        return messageId;
    }
    
    public Contact getSender() {
        return sender;
    }

    public byte[] getContent() {
        return content;
    }

    public byte[] getSignature() {
        return signature;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getFileName() {
        return fileName;
    }
    
    public Timestamp getTimeReceived() {
        return timeReceived;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Arrays.hashCode(this.signature);
        hash = 97 * hash + Objects.hashCode(this.timeReceived);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (obj == null)
            return false;
        
        if (getClass() != obj.getClass())
            return false;
        
        final Message other = (Message) obj;
        
        if (!Arrays.equals(this.signature, other.signature))
            return false;
        
        return Objects.equals(this.timeReceived, other.timeReceived);
    }
}
