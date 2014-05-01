package be.ewils.decentralize.model;

import com.google.common.base.Optional;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author antoine
 */
public class Email {

    /**
     * *************************************************************************
     * private fields
     * ************************************************************************
     */
    private List<String> senders;
    private List<String> destinations;
    private List<String> destinationsInCopy;
    private List<String> destinationsInBlackCopy;
    private String subject;
    private String body;
    private int hash;


    private static final Map<String, Command> partCommands;
    private static final String DEFAULT_COMMAND = "default";

    static {
        partCommands = new HashMap<>();
        partCommands.put(DEFAULT_COMMAND, new GetTextCommand());
        partCommands.put("text/*", new GetTextCommand());
        partCommands.put("multipart/*", new GetMultiPartTextCommand());
    }

    /**
     * *************************************************************************
     * life cycle methods
     * ************************************************************************
     */
    public Email() {
        senders = new LinkedList<>();
        destinations = new LinkedList<>();
        destinationsInCopy = new LinkedList<>();
        destinationsInBlackCopy = new LinkedList<>();
    }

    /**
     * *************************************************************************
     * getters and setters
     * ************************************************************************
     */
    public List<String> getSenders() {
        return senders;
    }

    public void setSenders(List<String> senders) {
        this.senders = senders;
    }

    public void addSender(String sender) {
        this.senders.add(sender);
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public void addDestination(String destination) {
        destinations.add(destination);
    }

    public List<String> getDestinationsInCopy() {
        return destinationsInCopy;
    }

    public void setDestinationsInCopy(List<String> destinationsInCopy) {
        this.destinationsInCopy = destinationsInCopy;
    }

    public void addDestinationInCopy(String destinationInCopy) {
        this.destinationsInCopy.add(destinationInCopy);
    }

    public List<String> getDestinationsInBlackCopy() {
        return destinationsInBlackCopy;
    }

    public void setDestinationsInBlackCopy(List<String> destinationsInBlackCopy) {
        this.destinationsInBlackCopy = destinationsInBlackCopy;
    }

    public void addDestinationInBlackCopy(String destinationInBlackCopy) {
        this.destinationsInBlackCopy.add(destinationInBlackCopy);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setBody(Object body) {
        if (body.getClass().equals(String.class)) {
            setBody((String) body);
        } else if (body.getClass().equals(MimeMultipart.class)) {
            setBody((MimeMultipart) body);
        } else {
            this.body = body.toString();
        }
    }

    public void setBody(MimeMultipart body) {
        this.body = getText(body);
    }

    @Override
    public String toString() {
        return "Email{"
            + "senders=" + senders
            + ", destinations=" + destinations
            + ", destinationsInCopy=" + destinationsInCopy
            + ", destinationsInBlackCopy=" + destinationsInBlackCopy
            + ", subject=" + subject
            + ", body=" + body + '}';
    }

    @Override
    public int hashCode() {
        if(hash != 0) {
            return hash;
        }
        
        hash = 7;
        hash = 31 * hash + Objects.hashCode(this.senders);
        hash = 31 * hash + Objects.hashCode(this.destinations);
        hash = 31 * hash + Objects.hashCode(this.destinationsInCopy);
        hash = 31 * hash + Objects.hashCode(this.destinationsInBlackCopy);
        hash = 31 * hash + Objects.hashCode(this.subject);
        hash = 31 * hash + Objects.hashCode(this.body);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Email other = (Email) obj;
        if (!Objects.equals(this.senders, other.senders)) {
            return false;
        }
        if (!Objects.equals(this.destinations, other.destinations)) {
            return false;
        }
        if (!Objects.equals(this.destinationsInCopy, other.destinationsInCopy)) {
            return false;
        }
        if (!Objects.equals(this.destinationsInBlackCopy, other.destinationsInBlackCopy)) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        return true;
    } 

    private String getText(Multipart multipart) {
        try {
            return getText(multipart.getBodyPart(0));
        } catch (MessagingException e) {
            return "could not read text from body: " + e.getClass().getName() + ": " + e.getMessage();
        }
    }

    /**
     * Return the primary text content of the message.
     */
    private String getText(Part part) {

        try {
            String contentType = part.getContentType();
            contentType = contentType.replaceAll("/.*$", "*");
            
            final Command command = partCommands.get(contentType);
            final Command defaultCommand = partCommands.get(DEFAULT_COMMAND);

            return Optional.
                fromNullable(command).
                or(defaultCommand).
                getText(part);

        } catch (MessagingException | IOException e) {
            return "could not read text from body: " + e.getClass().getName() + ": " + e.getMessage();
        }
    }

    private interface Command {

        String getText(Part part) throws IOException, MessagingException;
    }

    private static class GetTextCommand implements Command {

        @Override
        public String getText(Part part) throws IOException, MessagingException {
            return (String) part.getContent();
        }
    }

    private static class GetMultiPartTextCommand implements Command {

        @Override
        public String getText(Part part) throws IOException, MessagingException {

            StringBuilder stringBuffer = new StringBuilder();
            Multipart multiPart = (Multipart) part.getContent();
            for (int i = 0; i < multiPart.getCount(); i++) {
                final BodyPart subPart = multiPart.getBodyPart(i);
                final String text = partCommands.get(subPart.getContentType()).getText(subPart);
                stringBuffer.append(text);
            }
            return (String) part.getContent();
        }
    }
}
