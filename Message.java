/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quickchat.chatapp2;

/**
 *
 * @author RC_Student_lab
 */
class Message {
    public boolean checkMessageID(String messageId) {
        return messageId.length() <= 10;
    }

    public boolean checkRecipientCell(String recipient) {
        return recipient.startsWith("+") && recipient.length() <= 13;
    }

    public String createMessageHash(String messageId, int num, String message) {
        String[] words = message.split(" ");
        String hash = messageId.substring(0, 2) + ":" + num + ":" + words[0] + words[words.length - 1];
        return hash.toUpperCase();
    }

    public String sentMessage(String option) {
        return switch (option) {
            case "Send" -> "Message successfully sent.";
            case "Disregard" -> "Press 0 to delete message.";
            case "Store" -> "Message successfully stored.";
            default -> "Invalid option.";
        };
    }
}
