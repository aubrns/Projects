package com.techelevator.model;

import java.util.ArrayList;

public class FlashcardDeck {

    private int id;
    private int userId;
    private String deckName;
    private String username;
    private String description;
    private String deckImage;


    public FlashcardDeck() {};

    public FlashcardDeck(int id, int userId, String deckName, String username, String description, String deckImage){
        this.id = id;
        this.userId = userId;
        this.deckName = deckName;
        this.username = username;
        this.description = description;
        this.deckImage = deckImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeckImage() {
        return deckImage;
    }

    public void setDeckImage(String deckImage) {
        this.deckImage = deckImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
