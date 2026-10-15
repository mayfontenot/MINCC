package entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author May Fontenot (may@mayware.net)
 */
public class Contact implements Serializable {
    private final int contactId;
    private String name;
    private String domain;
    private byte[] publicKey;
    
    public Contact(String name, String domain, byte[] publicKey) {
        contactId = hashCode();
        this.name = name;
        this.domain = domain;
        this.publicKey = publicKey;
    }
    
    public Contact(int contactId, String name, String domain, byte[] publicKey) {
        this.contactId = contactId;
        this.name = name;
        this.domain = domain;
        this.publicKey = publicKey;
    }

    public int getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }
    
    public String getDomain() {
        return domain;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.domain);
        
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
        
        final Contact other = (Contact) obj;
        
        return Objects.equals(this.domain, other.domain);
    }
}
