package com.techelevator.controller;

import com.techelevator.dao.FlashcardDeckDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Flashcard;
import com.techelevator.model.FlashcardDeck;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@CrossOrigin
public class FlashcardDeckController {

    private FlashcardDeckDao flashcardDeckDao;
    private UserDao userDao;

    public FlashcardDeckController(FlashcardDeckDao flashcardDeckDao, UserDao userDao) {
        this.flashcardDeckDao = flashcardDeckDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/create/deck", method = RequestMethod.POST)
    public FlashcardDeck createFlashcardDeck (@RequestBody FlashcardDeck flashcardDeck, Principal principal) {

        String username = principal.getName();

        User user = userDao.getUserByUsername(username);
        flashcardDeck.setUserId(user.getId());


        return flashcardDeckDao.createFlashcardDeck(flashcardDeck);
    }


    @RequestMapping(path="/modify/deck/{id}", method = RequestMethod.PUT)
    public void modifyDeck (@RequestBody FlashcardDeck flashcardDeck, @PathVariable int id) {

        flashcardDeck.setUserId(id);
        flashcardDeckDao.modifyFlashcardDeck(flashcardDeck, id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/fill-deck/{flashcardId}/{deckId}", method = RequestMethod.POST)
    public void addFlashcardToDeckByIds(@PathVariable int flashcardId, @PathVariable int deckId) {

        flashcardDeckDao.addFlashcardToDeckByIds(flashcardId, deckId);
    }


    @RequestMapping(path="/search/deck/{id}", method = RequestMethod.GET)
    public FlashcardDeck findDeckById (@PathVariable int id) {

        return flashcardDeckDao.findDeckById(id);
    }


    @RequestMapping(path="/search/deck-byUsername", method = RequestMethod.GET)
    public ArrayList<FlashcardDeck> findDecksByUsername (Principal principal) {

        ArrayList<FlashcardDeck> findDeckByUsername = flashcardDeckDao.findDecksByUsername(principal.getName());
        return findDeckByUsername;
    }

    @RequestMapping(path="/search/flashcardsByDeckId/{id}", method = RequestMethod.GET)
    public ArrayList<Flashcard> findFlashcardsByDeck (@PathVariable int id){

        return flashcardDeckDao.findAllFlashcardsByDeckId(id);
    }

    //put in delete http status
    @RequestMapping(path="/remove-flashcard-from-deck/{flashcardId}/{deckId}", method = RequestMethod.DELETE)
    public void removeFlashcardFromDEck (@PathVariable int flashcardId, @PathVariable int deckId){
        flashcardDeckDao.removeFlashcardFromDEck(flashcardId, deckId);
    }
}
