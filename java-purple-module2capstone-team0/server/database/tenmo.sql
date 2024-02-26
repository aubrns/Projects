BEGIN TRANSACTION;

DROP TABLE IF EXISTS tenmo_user, account, transfer CASCADE;

DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id, seq_transfer_id CASCADE;

-- Sequence to start user_id values at 1001 instead of 1


  
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;
  
 

CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance numeric(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);


 CREATE SEQUENCE seq_transfer_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;
  

CREATE TABLE transfer (
	transfer_id int NOT NULL DEFAULT nextval('seq_transfer_id'),
	sender_account_id int NOT NULL,
	receiver_account_id int NOT NULL,
	--user_id int NOT NULL,
	--sender_user_id int NOT NULL,
	--receiver_user_id int NOT NULL,
	--sender_user_name varchar (50) NOT NULL,
	--receiver_user_name varchar (50) NOT NULL,
	transfer_status varchar(20) NOT NULL,
	--sender_balance numeric(13,2) NOT NULL,
	--receiver_balance numeric(13,2) NOT NULL,
	transfer_amount numeric (13,2)NOT NULL,
	CONSTRAINT PK_tansfer_id PRIMARY KEY (transfer_id),
	CONSTRAINT FK_sender_account_id FOREIGN KEY (sender_account_id) REFERENCES account (account_id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id);
	
	
);

-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables



--SELECT user_id FROM tenmo_user;
ALTER TABLE transfer
ADD user_id int,
ADD CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id);








COMMIT;
