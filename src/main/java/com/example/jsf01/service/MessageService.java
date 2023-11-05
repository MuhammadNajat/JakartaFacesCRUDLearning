package com.example.jsf01.service;

import java.util.List;

import com.example.jsf01.model.Message;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Stateless
public class MessageService {
    @PersistenceContext
    private EntityManager entityManager;
    public void create(Message message) {
        entityManager.persist(message);
    }
    public List<Message> list() {
        return entityManager
                .createQuery("FROM Message m", Message.class)
                .getResultList();
    }
}