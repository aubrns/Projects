package com.techelevator.dao;

import com.techelevator.model.Flashcard;
import com.techelevator.model.FlashcardDeck;

import java.util.ArrayList;

public interface FlashcardDeckDao {


    FlashcardDeck createFlashcardDeck (FlashcardDeck newFlashcardDeck);

    void modifyFlashcardDeck (FlashcardDeck flashcardDeck, int id);

    FlashcardDeck findDeckById (int id);

    ArrayList<FlashcardDeck> findDecksByUsername(String name);

    void addFlashcardToDeckByIds(int flashcardId, int deckId);

    public ArrayList<Flashcard> findAllFlashcardsByDeckId(int deckId);

    //FlashcardDeck deleteFlashcardDeck (FlashcardDeck deleteFlashcardDeck);
    public void removeFlashcardFromDEck(int flashcardId, int deckId);
}
