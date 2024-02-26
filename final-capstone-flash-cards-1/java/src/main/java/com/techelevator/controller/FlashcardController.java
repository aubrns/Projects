package com.techelevator.controller;

import com.techelevator.dao.FlashcardDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Flashcard;
import com.techelevator.model.FlashcardDTO;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class FlashcardController {

    private FlashcardDao flashcardDao;
    private UserDao userDao;

    public FlashcardController(FlashcardDao flashcardDao, UserDao userDao) {
        this.flashcardDao = flashcardDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/create/flashcard", method = RequestMethod.POST)
    public Flashcard createFlashcard (@RequestBody Flashcard flashcard, Principal principal) {

        String username = principal.getName();

        User user = userDao.getUserByUsername(username);
        flashcard.setUserId(user.getId());


        return flashcardDao.createFlashcard(flashcard);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/modify/flashcards/{id}", method = RequestMethod.PUT)
    public void modifyFlashcard (@RequestBody Flashcard flashcard, @PathVariable int id) {

        flashcard.setId(id);
        flashcardDao.modifyFlashcard(flashcard, id);
    }


    @RequestMapping(path="/search/flashcard/{id}", method = RequestMethod.GET)
    public Flashcard findFlashcardById (@PathVariable int id) {

        return flashcardDao.findFlashcardById(id);
    }


    @RequestMapping(path="/search/flashcards-byTag/{tag}", method = RequestMethod.GET)
    public ArrayList<Flashcard> findFlashcardsByTag (@PathVariable String tag) {

        ArrayList<Flashcard> foundFlashcardsByTag = flashcardDao.findFlashcardsByTag(tag);
        return foundFlashcardsByTag;
    }


    @RequestMapping(path="/search/flashcards-byUsername", method = RequestMethod.GET)
    public ArrayList<Flashcard> findFlashcardsByUsername (Principal principal) {


        ArrayList<Flashcard> foundFlashcardsByUsername = flashcardDao.findFlashcardsByUsername(principal.getName());
        return foundFlashcardsByUsername;
    }

    //finish
    @RequestMapping(path="/search/")
}








