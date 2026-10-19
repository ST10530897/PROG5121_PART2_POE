
package za.ac.rosebank.registrationlogin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MessageTest {

    @Test
    public void testValidRecipientNumber() {
        Message message = new Message(
            1,
            "+27821234567",
            "Hello"
        );

        assertTrue(
            message.checkRecipientCell("+27821234567")
        );
    }

    @Test
    public void testInvalidRecipientNumber() {
        Message message = new Message(
            1,
            "+27821234567",
            "Hello"
        );

        assertFalse(
            message.checkRecipientCell("0821234567")
        );
    }

    @Test
    public void testValidMessageLength() {
        Message message = new Message(
            1,
            "+27821234567",
            "Hello there"
        );

        assertTrue(message.checkMessageLength());
    }

    @Test
    public void testMessageID() {
        Message message = new Message(
            1,
            "+27821234567",
            "Hello"
        );

        assertTrue(message.checkMessageID());
    }
    @Test
public void testMessageHashCreation() {
    Message msg = new Message(
        1,
        "+27718693002",
        "HI Mike, can you join us for dinner tonight?"
    );

    assertNotNull(msg.getMessageHash());
    assertTrue(msg.getMessageHash().contains(":1:"));
}
}

