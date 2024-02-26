package com.techelevator.dao;


import com.techelevator.model.Flashcard;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFlashcardDao implements FlashcardDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcFlashcardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Flashcard createFlashcard( Flashcard newFlashcard) {

        String sql = "INSERT INTO flashcards (user_id, question, answer, username, tags) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING flashcard_id";
        Integer flashcardId = jdbcTemplate.queryForObject(sql, Integer.class, newFlashcard.getUserId(),
                newFlashcard.getQuestion(), newFlashcard.getAnswer(), newFlashcard.getUsername(), newFlashcard.getTags());

        return findFlashcardById(flashcardId);

    }


    @Override
    public void modifyFlashcard(Flashcard flashcard, int id) {

        String sql = "UPDATE flashcards SET question = ?, answer = ?, tags = ? WHERE flashcards.flashcard_id = ?;";

        jdbcTemplate.update(sql, flashcard.getQuestion(), flashcard.getAnswer(), flashcard.getTags(),flashcard.getId());

    }


    @Override
    public Flashcard findFlashcardById (int id) {

        String sql = "SELECT flashcard_id, user_id, question, answer, username, tags FROM flashcards WHERE flashcard_id = ?;";
        Flashcard foundFlashcard = new Flashcard();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if(results.next()) {
                foundFlashcard = mapRowToFlashcard(results);
            }
        } catch (DataAccessException e) {

        }
        return foundFlashcard;
    }


    @Override
    public ArrayList<Flashcard> findFlashcardsByTag(String tag) {

        String searchTerm = "%" + tag + "%";
        String sql = "SELECT * FROM flashcards WHERE tags LIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, searchTerm);
        ArrayList<Flashcard> flashcardsByTag = new ArrayList<>();

        while (results.next()) {
            flashcardsByTag.add(mapRowToFlashcard(results));
        }
        return flashcardsByTag;

    }


    @Override
    public ArrayList<Flashcard> findFlashcardsByUsername (String username) {

        String sql = "SELECT * FROM flashcards WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        ArrayList<Flashcard> flashcardsByUsername = new ArrayList<>();

        while (results.next()) {
            flashcardsByUsername.add(mapRowToFlashcard(results));
        }
        return flashcardsByUsername;
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