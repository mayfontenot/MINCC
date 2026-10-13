package entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author azurith
 */
public class Conversation {
    private final int conversationId;
    private String name;
    private final List<Contact> recipients;
    private final List<Message> messages;

    public Conversation(String name, List<Contact> recipients, List<Message> messages) {
        conversationId = hashCode();
        this.name = name;
        this.recipients = recipients;
        this.messages = messages;
    }
    
    public Conversation(int conversationId, String name, List<Contact> recipients, List<Message> messages) {
        this.conversationId = conversationId;
        this.name = name;
        this.recipients = recipients;
        this.messages = messages;
    }

    public int getConversationId() {
        return conversationId;
    }

    public String getName() {
        return name;
    }
    
    public List<Contact> getRecipients() {
        return recipients;
    }

    public List<Message> getMessages() {
        return messages;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.recipients);
        
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
        
        if (!Objects.equals(this.name, other.name))
            return false;
        
        return Objects.equals(this.recipients, other.recipients);
    }
}
