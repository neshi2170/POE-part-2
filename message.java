
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */
public class message {
     private boolean checkRecipientCell(String recipient) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean checkMessageID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String createMessageHash(String id, int i, String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Object sentMessage(String send) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  
public class QuickChatApp {

    static List<String> sentMessages = new ArrayList<>();
    static List<String> disregardedMessages = new ArrayList<>();
    static List<String> storedMessages = new ArrayList<>();
    static List<String> messageHashes = new ArrayList<>();
    static List<String> messageIDs = new ArrayList<>();

    // Use your previous classes
    static Registration reg = new Registration();
    static Message msgUtil = new Message();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to QuickChat.");

        // Get and validate username
        System.out.print("Username: ");
        String u = scan.nextLine();
        if (!reg.isValidUsername(u)) {
            System.out.println("Invalid username! Must be <=5 chars and contain '_'.");
            return;
        }

        // Get and validate password
        System.out.print("Password: ");
        String p = scan.nextLine();
        if (!reg.isValidPassword(p)) {
            System.out.println("Invalid password! Must be 8 chars with uppercase, number, special char.");
            return;
        }

        // Get and validate phone
        System.out.print("Phone (+27xxxxxxxxx): ");
        String phone = scan.nextLine();
        if (!reg.isValidPhoneNumber(phone)) {
            System.out.println("Invalid phone number!");
            return;
        }

        OUTER:
        while (true) {
            System.out.println("\n1. Send Msg");
            System.out.println("2. Show Sent Msgs");
            System.out.println("3. Display Longest Msg");
            System.out.println("4. Search by Message ID");
            System.out.println("5. Search by Recipient");
            System.out.println("6. Delete by Hash");
            System.out.println("7. Display Report");
            System.out.println("8. Quit");
            System.out.print("Pick: ");
            int y = Integer.parseInt(scan.nextLine());

            switch (y) {
                case 1 -> sendMessage();
                case 2 -> displaySentMessages();
                case 3 -> displayLongestMessage();
                case 4 -> searchByMessageID();
                case 5 -> searchByRecipient();
                case 6 -> deleteByHash();
                case 7 -> displayReport();
                case 8 -> { break OUTER; }
                default -> System.out.println("Wrong option.");
            }
        }
    }

    public static void sendMessage() {
        String recipient = JOptionPane.showInputDialog("Recipient num:");

        // Validate recipient using your Message class method
        if (!msgUtil.checkRecipientCell(recipient)) {
            JOptionPane.showMessageDialog(null, "Wrong number.");
            return;
        }

        String message = JOptionPane.showInputDialog("Your msg:");

        if (message.length() > 250) {
            JOptionPane.showMessageDialog(null, "Too long by " + (message.length() - 250));
            return;
        }

        // Generate Message ID with your method
        String id = makeId();

        // Validate Message ID with Message method
        if (!msgUtil.checkMessageID(id)) {
            JOptionPane.showMessageDialog(null, "Invalid generated message ID.");
            return;
        }

        // Use your Message class method to create the hash
        String hash = msgUtil.createMessageHash(id, 0, message);

        Object[] options = {"Send", "Store", "Ignore"};
        int choice = JOptionPane.showOptionDialog(null, "What now?", "Send",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        switch (choice) {
            case 0 -> {
                sentMessages.add("ID: " + id + ", HASH: " + hash + ", TO: " + recipient + ", MSG: " + message);
                messageIDs.add(id);
                messageHashes.add(hash);
                JOptionPane.showMessageDialog(null, msgUtil.sentMessage("Send"));
            }
            case 1 -> {
                storedMessages.add("ID: " + id + ", HASH: " + hash + ", TO: " + recipient + ", MSG: " + message);
                messageIDs.add(id);
                messageHashes.add(hash);
                JOptionPane.showMessageDialog(null, msgUtil.sentMessage("Store"));
            }
            default -> {
                disregardedMessages.add("ID: " + id + ", HASH: " + hash + ", TO: " + recipient + ", MSG: " + message);
                JOptionPane.showMessageDialog(null, msgUtil.sentMessage("Disregard"));
            }
        }
    }

    public static void displaySentMessages() {
        if (sentMessages.isEmpty()) {
            System.out.println("No sent messages.");
            return;
        }
        for (String msg : sentMessages) {
            System.out.println(msg);
        }
    }

    public static void displayLongestMessage() {
        String longestMsg = "";
        for (String msg : sentMessages) {
            String[] parts = msg.split(", MSG: ");
            if (parts.length == 2 && parts[1].length() > longestMsg.length()) {
                longestMsg = parts[1];
            }
        }
        System.out.println("Longest Message: " + longestMsg);
    }

    public static void searchByMessageID() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Message ID to search: ");
        String id = scan.nextLine();

        for (String msg : sentMessages) {
            if (msg.contains("ID: " + id)) {
                System.out.println("Message Found: " + msg);
                return;
            }
        }
        System.out.println("Message ID not found.");
    }

    public static void searchByRecipient() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Recipient to search: ");
        String recipient = scan.nextLine();

        boolean found = false;
        for (String msg : sentMessages) {
            if (msg.contains("TO: " + recipient)) {
                System.out.println(msg);
                found = true;
            }
        }
        for (String msg : storedMessages) {
            if (msg.contains("TO: " + recipient)) {
                System.out.println(msg);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    public static void deleteByHash() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Message Hash to delete: ");
        String hash = scan.nextLine();

        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).contains("HASH: " + hash)) {
                System.out.println("Message successfully deleted: " + sentMessages.get(i));
                sentMessages.remove(i);
                return;
            }
        }
        System.out.println("Message not found.");
    }

    public static void displayReport() {
        if (sentMessages.isEmpty()) {
            System.out.println("No sent messages to display.");
            return;
        }
        for (String msg : sentMessages) {
            System.out.println(msg);
        }
    }

    public static String makeId() {
        Random r = new Random();
        int a = r.nextInt(999999999);
        return String.format("%010d", a);
    
}}}
