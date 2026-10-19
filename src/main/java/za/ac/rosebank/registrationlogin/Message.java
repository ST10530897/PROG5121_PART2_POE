package za.ac.rosebank.registrationlogin;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.json.JSONObject;

public class Message {

    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private int messageNumber;

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();

        if (checkRecipientCell(recipient)) {
            this.recipient = recipient;
        } else {
            this.recipient = "";
        }

        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    // Generate a random 10-digit message ID.
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1_000_000_000L
                + (long) (rand.nextDouble() * 9_000_000_000L);

        return String.valueOf(id);
    }

    
    public boolean checkMessageID() {
        return messageID != null && messageID.matches("\\d{1,10}");
    }

    
    public boolean checkRecipientCell(String cellNumber) {
        if (cellNumber != null && cellNumber.matches("\\+[1-9]\\d{7,14}")) {
            System.out.println("Cell phone number successfully captured.");
            return true;
        }

        System.out.println(
                "Cell phone number is incorrectly formatted or does not "
                + "contain a valid international code. Please correct the "
                + "number and try again.");
        return false;
    }

    
    public boolean checkMessageLength() {
        if (messageText == null || messageText.trim().isEmpty()) {
            System.out.println("Message cannot be empty.");
            return false;
        }

        if (messageText.length() <= 250) {
            System.out.println("Message ready to send.");
            return true;
        }

        int excess = messageText.length() - 250;
        System.out.println(
                "Please enter a message of 250 characters or fewer.");
        System.out.println(
                "Message exceeds the limit by " + excess + " characters.");
        return false;
    }

   
    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2) {
            return "";
        }

        String firstTwoId = messageID.substring(0, 2);
        String firstWord = "";
        String lastWord = "";

        if (messageText != null && !messageText.trim().isEmpty()) {
           
            String cleaned = messageText
                    .replaceAll("[^a-zA-Z0-9\\s]", " ")
                    .trim();

            if (!cleaned.isEmpty()) {
                String[] words = cleaned.split("\\s+");
                firstWord = words[0];
                lastWord = words[words.length - 1];
            }
        }

        String combinedWords = (firstWord + lastWord).toUpperCase();

        return firstTwoId + ":" + messageNumber + ":" + combinedWords;
    }

   
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Message discarded.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid selection.";
        }
    }

  
    public String printMessages() {
        return "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + messageText;
    }

  
    public int returnTotalMessages() {
        return messageNumber;
    }

    
    public void storeMessage(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            System.out.println("Invalid filename.");
            return;
        }

        JSONObject json = new JSONObject();
        json.put("messageID", messageID);
        json.put("messageHash", messageHash);
        json.put("recipient", recipient);
        json.put("messageText", messageText);
        json.put("messageNumber", messageNumber);

        try (FileWriter file = new FileWriter(filename, true)) {
            file.write(json.toString());
            file.write(System.lineSeparator());
            System.out.println("Message successfully stored.");
        } catch (IOException e) {
            System.out.println("Error saving message to JSON: " + e.getMessage());
        }
    }

    
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public int getMessageNumber() {
        return messageNumber;
    }
}
