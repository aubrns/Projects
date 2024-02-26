package com.techelevator.dao;


import com.techelevator.model.Flashcard;
import com.techelevator.model.FlashcardDeck;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class JdbcFlashcardDeckDao implements FlashcardDeckDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcFlashcardDeckDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FlashcardDeck createFlashcardDeck(FlashcardDeck newFlashcardDeck) {

        String sql = "INSERT INTO decks (user_id, username, description, deck_name, deck_image) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING deck_id;";
        Integer flashcardDeckId = jdbcTemplate.queryForObject(sql, Integer.class, newFlashcardDeck.getUserId(), newFlashcardDeck.getUsername(), newFlashcardDeck.getDescription(),
                newFlashcardDeck.getDeckName(), newFlashcardDeck.getDeckImage());


        return findDeckById(flashcardDeckId);
        //creating an empty deck

    }


    @Override
    public void modifyFlashcardDeck(FlashcardDeck flashcardDeck, int id) {

        String sql = "UPDATE decks SET username = ?, deck_name = ?, description = ?, deck_image = ? WHERE deck_id = ?;";

        jdbcTemplate.update(sql, flashcardDeck.getUsername(), flashcardDeck.getDeckName(), flashcardDeck.getDescription(), flashcardDeck.getDeckImage(), flashcardDeck.getId());

    }


    public ArrayList<Flashcard> findAllFlashcardsByDeckId(int deckId) {
        String sql = "SELECT flashcards.flashcard_id, user_id, question, answer, username, tags FROM flashcards JOIN flashcard_deck on flashcards.flashcard_id = flashcard_deck.flashcard_id  WHERE flashcard_deck.deck_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, deckId);
        ArrayList<Flashcard> flashcardsByDeckId = new ArrayList<>();

        while (results.next()) {
            flashcardsByDeckId.add(mapRowToFlashcard(results));
        }
        return flashcardsByDeckId;
    }


    @Override
    public void addFlashcardToDeckByIds(int flashcardId, int deckId) {

        String sql = "INSERT INTO flashcard_deck (flashcard_id, deck_id) VALUES (?, ?);";

        jdbcTemplate.update(sql, flashcardId, deckId);

    }


    @Override
    public FlashcardDeck findDeckById(int id) {

        String sql = "SELECT * FROM decks WHERE deck_id = ?;";
        FlashcardDeck foundDeck = new FlashcardDeck();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                foundDeck = mapRowToDeck(results);
            }
        } catch (DataAccessException e) {

        }
        return foundDeck;
    }


    @Override
    public ArrayList<FlashcardDeck> findDecksByUsername(String username) {

        String sql = "SELECT * FROM decks WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        ArrayList<FlashcardDeck> flashcardDecksByUsername = new ArrayList<>();

        while (results.next()) {
            flashcardDecksByUsername.add(mapRowToDeck(results));
        }
        return flashcardDecksByUsername;
    }

    @Override
    public void removeFlashcardFromDEck(int flashcardId, int deckId){
        String sql = "DELETE FROM flashcard_deck WHERE flashcard_id = ? AND deck_id = ?;";
        int numOfRows = jdbcTemplate.update(sql,flashcardId, deckId);
//        System.out.println(String.format("%s Number of rows removed from flashcard_deck deckId: %s flashcardId: %s",numOfRows,deckId,flashcardId));
    }


    private FlashcardDeck mapRowToDeck(SqlRowSet rs) {
        FlashcardDeck flashcardDeck = new FlashcardDeck();
        flashcardDeck.setId(rs.getInt("deck_id"));
        flashcardDeck.setUserId(rs.getInt("user_id"));
        flashcardDeck.setDeckName(rs.getString("deck_name"));
        flashcardDeck.setUsername(rs.getString("username"));
        flashcardDeck.setDescription(rs.getString("description"));
        flashcardDeck.setDeckImage(rs.getString("deck_image"));
        return flashcardDeck;
    }

    private Flashcard mapRowToFlashcard(SqlRowSet rs) {
        Flashcard flashcard = new Flashcard();
        flashcard.setId(rs.getInt("flashcard_id"));
        flashcard.setUserId(rs.getInt("user_id"));
        flashcard.setQuestion(rs.getString("question"));
        flashcard.setAnswer(rs.getString("answer"));
        flashcard.setUsername(rs.getString("username"));
        flashcard.setTags(rs.getString("tags"));
        return flashcard;
    }

}