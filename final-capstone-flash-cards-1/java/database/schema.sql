BEGIN TRANSACTION;

--ROLLBACK;

DROP TABLE IF EXISTS tags, flashcard_deck, flashcards, deck_user, decks, users;

DROP SEQUENCE IF EXISTS seq_user_id, seq_flashcard_id, seq_deck_id;

CREATE SEQUENCE seq_user_id
    INCREMENT BY 1
    START WITH 1
    NO MAXVALUE;

CREATE TABLE users (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)

);

CREATE SEQUENCE seq_flashcard_id
    INCREMENT BY 1
    START WITH 2001
    NO MAXVALUE;

CREATE TABLE flashcards (
    flashcard_id int NOT NULL DEFAULT nextval('seq_flashcard_id'),
    user_id int NOT NULL,
    question varchar(500) NOT NULL,
    answer varchar(5000) NOT NULL,
    username varchar(50) NOT NULL,
	tags varchar(200) NOT NUll,
    CONSTRAINT PK_flashcards PRIMARY KEY (flashcard_id),
    CONSTRAINT FK_flashcards_users FOREIGN KEY (user_id) REFERENCES users (user_id)

);

CREATE SEQUENCE seq_deck_id
    INCREMENT BY 1
    START WITH 5001
    NO MAXVALUE;

CREATE TABLE decks (
    deck_id int NOT NULL DEFAULT nextval('seq_deck_id'),
    user_id int NOT NULL,
    deck_name varchar(50) NOT NULL,
    username varchar(50) NOT NULL,
    description varchar(500) NOT NULL,
    deck_image varchar(500),
    CONSTRAINT PK_decks PRIMARY KEY (deck_id),
    CONSTRAINT FK_decks_users FOREIGN KEY (user_id) REFERENCES users (user_id)

);

CREATE TABLE flashcard_deck (
	flashcard_id int NOT NULL,
	deck_id int NOT NULL,
	CONSTRAINT PK_flashcard_deck PRIMARY KEY (deck_id, flashcard_id),
	CONSTRAINT FK_flashcard_deck_flashcards FOREIGN KEY (flashcard_id) REFERENCES flashcards (flashcard_id),
	CONSTRAINT FK_flashcard_deck_decks FOREIGN KEY (deck_id) REFERENCES decks (deck_id)
);

CREATE TABLE deck_user (
	deck_id int NOT NULL,
	user_id int NOT NULL,
	CONSTRAINT PK_deck_user PRIMARY KEY (deck_id, user_id),
	CONSTRAINT FK_deck_user_deck FOREIGN KEY (deck_id) REFERENCES decks (deck_id),
	CONSTRAINT FK_deck_user_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

-- ALTER TABLE flashcards ADD CONSTRAINT FK_flashcards_decks FOREIGN KEY (deck_id) REFERENCES decks (deck_id);

COMMIT TRANSACTION;
