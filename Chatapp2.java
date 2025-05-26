/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.quickchat.chatapp2;

/**
 *
 * @author RC_Student_lab
 */
public class Chatapp2 {

    public static void main(String[] args) {
      System.out.println("Welcome to ChatApp.");

        // Step 1: Registration
        Registration reg = new Registration();
        String username = "u_ser";
        String password = "Abc!1234";
        String phone = "+27812345678";

        System.out.println("Username valid? " + reg.isValidUsername(username));
        System.out.println("Password valid? " + reg.isValidPassword(password));
        System.out.println("Phone valid? " + reg.isValidPhoneNumber(phone));

        // Step 2: Message
        Message msg = new Message();
        String messageId = "0012345678";
        String recipient = "+27718693002";
        String messageText = "Hi Mike, can you join us for dinner tonight";
        int messageNum = 0;

        System.out.println("Message ID valid? " + msg.checkMessageID(messageId));
        System.out.println("Recipient cell valid? " + msg.checkRecipientCell(recipient));

        String hash = msg.createMessageHash(messageId, messageNum, messageText);
        System.out.println("Generated hash: " + hash);

        String result = msg.sentMessage("Send");
        System.out.println("Message status: " + result);
    }
    }
 
