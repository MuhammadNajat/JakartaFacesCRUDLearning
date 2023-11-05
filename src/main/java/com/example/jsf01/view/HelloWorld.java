package com.example.jsf01.view;

import java.util.List;

import com.example.jsf01.model.Message;
import com.example.jsf01.service.MessageService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
@Named @RequestScoped
public class HelloWorld {
    private Message message = new Message();
    private List<Message> messages;
    @Inject
    private MessageService messageService;
    @PostConstruct
    public void init() {
        messages = messageService.list();
    }
    public void submit() {
        messageService.create(message);
        messages.add(message);
    }

    public Message getMessage() {
        return message;
    }
    public List<Message> getMessages() {
        return messages;
    }
}