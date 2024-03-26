package com.example.domainstructuring;

import com.example.domainstructuring.exception.BlankException;
import com.example.domainstructuring.exception.ExcessiveCharactersException;
import com.example.domainstructuring.exception.MessageCreationException;
import com.example.domainstructuring.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message) {
        
        String text = message.getMessage_text();
        if (text.isBlank()) {
            throw new BlankException("Enter a valid message");
        }
        if (text.length() > 254) {
            throw new ExcessiveCharactersException("Message is too long");
        }
        
        accountRepository.findById(message.getPosted_by())
                    .orElseThrow(() -> new MessageCreationException("Message is not from a registered account"));

        return messageRepository.save(message);
    }

    public List<Message> retrieveAllMessages() {

        return messageRepository.findAll();
    }

    public Message retrieveMessageByMessageId(Integer messageId) {

        return messageRepository.findById(messageId).orElseGet(() -> null);
    }

    public int deleteMessageByMessageId(Integer messageId) {

        int rowsAffected = retrieveMessageByMessageId(messageId) == null ? 0 : 1;

        if (rowsAffected == 1) {
            messageRepository.deleteById(messageId);
        }

        return rowsAffected;
    }

    public int updateMessage(Integer messageId, Message newMessage) {

        Message oldMessage = messageRepository.findById(messageId).orElseGet(() -> null);

        int rowsAffected = oldMessage == null ? 0 : 1;

        if (rowsAffected == 1) {
            messageRepository.deleteById(messageId);
        }
        else {
            throw new ResourceNotFoundException("No prior message available");
        }

        String text = newMessage.getMessage_text();
        if (text.isBlank()) {
            throw new BlankException("Enter a valid message");
        }
        if (text.length() > 254) {
            throw new ExcessiveCharactersException("Message is too long");
        }
        messageRepository.save(newMessage);

        return rowsAffected;
    }

    public List<Message> retrieveAllMessagesForUser(Integer accountId) {

        List<Integer> user = new ArrayList<>();
        user.add(accountId);
        return messageRepository.findAllById(user);
    }
    
}