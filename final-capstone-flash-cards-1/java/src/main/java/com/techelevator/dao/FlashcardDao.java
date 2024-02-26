package com.techelevator.dao;

import com.techelevator.model.Flashcard;

import java.util.ArrayList;
import java.util.List;

public interface FlashcardDao {

    Flashcard createFlashcard ( Flashcard newFlashcard);

    void modifyFlashcard (Flashcard flashcard, int id);

    Flashcard findFlashcardById (int id);

    ArrayList<Flashcard> findFlashcardsByTag(String tag);

    ArrayList<Flashcard> findFlashcardsByUsername(String username);

    //Void deleteFlashcard (Flashcard flashcard);



}