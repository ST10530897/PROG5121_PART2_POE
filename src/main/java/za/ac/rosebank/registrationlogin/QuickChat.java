
package za.ac.rosebank.registrationlogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickChat {

    public void start(Scanner scanner) {

        System.out.println("Welcome to QuickChat!");

        boolean running = true;
        int totalMessages = 0;

        List<Message> messages = new ArrayList<>();

        while (running) {

            System.out.println();
            System.out.println("QUICKCHAT MENU");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");

            String choiceInput = scanner.nextLine().trim();

            switch (choiceInput) {

                case "1":
                    System.out.print(
                        "How many messages do you wish to enter? "
                    );

                    int numberOfMessages;

                    try {
                        numberOfMessages = Integer.parseInt(
                            scanner.nextLine().trim()
                        );

                        if (numberOfMessages <= 0) {
                            System.out.println(
                                "Enter a number greater than zero."
                            );
                            break;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println(
                            "Please enter a valid whole number."
                        );
                        break;
                    }

                    for (int i = 1; i <= numberOfMessages; i++) {

                        System.out.println();
                        System.out.println(
                            "Entering Message " + i
                            + " of " + numberOfMessages
                        );

                        // Validate recipient number
                        String recipient;

                        while (true) {
                            System.out.print(
                                "Enter recipient cell number "
                                + "(+27 followed by 9 digits): "
                            );

                            recipient = scanner.nextLine().trim();

                            if (recipient.matches("\\+27[0-9]{9}")) {
                                System.out.println(
                                    "Recipient number accepted."
                                );
                                break;
                            } else {
                                System.out.println(
                                    "Invalid number. Enter +27 followed "
                                    + "by exactly 9 digits. Try again."
                                );
                            }
                        }

                        
                        String text;

                        while (true) {
                            System.out.print(
                                "Enter message text (maximum 250 characters): "
                            );

                            text = scanner.nextLine();

                            if (!text.trim().isEmpty()
                                    && text.length() <= 250) {
                                break;
                            } else if (text.trim().isEmpty()) {
                                System.out.println(
                                    "Message cannot be empty. Try again."
                                );
                            } else {
                                System.out.println(
                                    "Message exceeds 250 characters. "
                                    + "Please shorten it and try again."
                                );
                            }
                        }

                        Message msg = new Message(
                            totalMessages + 1,
                            recipient,
                            text
                        );

                        System.out.println();
                        System.out.println("Choose an action:");
                        System.out.println("1) Send message");
                        System.out.println("2) Discard message");
                        System.out.println("3) Store message");
                        System.out.print("Select option (1-3): ");

                        String actionChoice = scanner.nextLine().trim();

                        if (actionChoice.equals("1")) {

                            System.out.println(msg.sentMessage(1));

                            messages.add(msg);
                            totalMessages++;

                            System.out.println();
                            System.out.println("--- Message Details ---");
                            System.out.println(msg.printMessages());

                        } else if (actionChoice.equals("2")) {

                            System.out.println(msg.sentMessage(2));

                        } else if (actionChoice.equals("3")) {

                            msg.storeMessage("messages.json");

                            messages.add(msg);
                            totalMessages++;

                        } else {

                            System.out.println(
                                "Invalid selection. Message not processed."
                            );
                            continue;
                        }

                        System.out.println(
                            "Total Messages Processed: " + totalMessages
                        );
                    }

                    break;

                case "2":
                    if (messages.isEmpty()) {
                        System.out.println(
                            "Recently sent messages are not available yet."
                        );
                    } else {
                        System.out.println("RECENTLY PROCESSED MESSAGES");

                        for (Message msg : messages) {
                            System.out.println();
                            System.out.println(msg.printMessages());
                        }
                    }
                    break;

                case "3":
                case "quit":
                case "Quit":
                    System.out.println(
                        "Exiting QuickChat. Goodbye!"
                    );
                    running = false;
                    break;

                default:
                    System.out.println(
                        "Invalid option. Please select 1, 2, or 3."
                    );
            }
        }
    }
}
