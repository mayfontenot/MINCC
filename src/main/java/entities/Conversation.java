package entities;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author May Fontenot (may@mayware.net)
 */
public class Conversation {
    private final int conversationId;
    private String name;
    private final Contact[] recipients;
    private final List<Message> messages;
    private final Timestamp timeCreated;

    public Conversation(Contact[] recipients, List<Message> messages) {
        conversationId = hashCode();
        this.name = null;
        this.recipients = recipients;
        this.messages = messages;
        this.timeCreated = Timestamp.from(Instant.now());
    }
    
    public Conversation(int conversationId, String name, Contact[] recipients, List<Message> messages, Timestamp timeCreated) {
        this.conversationId = conversationId;
        this.name = name;
        this.recipients = recipients;
        this.messages = messages;
        this.timeCreated = timeCreated;
    }

    public int getConversationId() {
        return conversationId;
    }

    public String getName() {
        return name;
    }
    
    public Contact[] getRecipients() {
        return recipients;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Arrays.deepHashCode(this.recipients);
        hash = 29 * hash + Objects.hashCode(this.timeCreated);
        
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
        
        final Conversation other = (Conversation) obj;
        
        if (!Arrays.deepEquals(this.recipients, other.recipients))
            return false;
        
        return Objects.equals(this.timeCreated, other.timeCreated);
    }
}