package com.techelevator.model;


public class Flashcard {
    private int id;
    private int userId;
    private String question;
    private String answer;
    private String username;
    private String tags;

    public Flashcard() {};

    //    public String getTagsAsString() {
//        String tags = "";
//        for (int x = 0; x < this.tags.size(); x++) {
//            tags += this.tags.get(x);
//        }
//        return tags;
//    }

    public Flashcard(int id, int userId, String question, String answer, String username, String tags) {
        this.id = id;
        this.userId = userId;
        this.question = question;
        this.answer = answer;
        this.username = username;
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "{" +
                "flashcardId : " + id + ",\n" +
                "question : " + question + ",\n" +
                "answer : " + answer + ",\n" +
                "username : " + username + "\n" +
                "}";
    }
}