<!--searching by deckId-->

const flashCardsInDeck = allFlashCards.filter( card => card.deckId == deckId);


<template>
    <div>
      <h3>View Deck Cards</h3>
      <form @submit.prevent="saveFlashcard"> 
        <div v-if="flashcardErrors" class="error-message">
          {{ flashcardErrorMsg }}
        </div>
        <div class="form-group">
          <label for="question">Question:</label>
          <input type="text" id="question" v-model="flashcard.question" required />
        </div>
        <div class="form-group">
          <label for="answer">Answer:</label>
          <input type="text" id="answer" v-model="flashcard.answer" required />
        </div>
        <div class="form-group">
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="flashcard.username" required />
        </div>
        <div class="form-group">
          <label for="tags">Tags:</label>
          <input type="text" id="tags" v-model="flashcard.tags" />
        </div>
        <button type="submit">Save</button> 
      </form>
    </div>
  </template>
  
  <script>
  import FlashcardService from '../services/FlashcardService';
  
  export default {
    data() {
      return {
        flashcard: {
          question: '',
          answer: '',
          username: '',
          tags: ''
        },
        flashcardErrors: false,
        flashcardErrorMsg: ''
      };
    },
    methods: {
      saveFlashcard() {
        if (!this.validateForm()) {
          return;
        }
  
        FlashcardService.createFlashcard(this.flashcard)
          .then(response => {
            if (response.status === 201) {
              window.alert('Flashcard saved successfully.');
              // Clear the form
              this.flashcard.question = '';
              this.flashcard.answer = '';
              this.flashcard.username = '';
              this.flashcard.tags = '';
            }
          })
          .catch(error => {
            console.error('Error saving flashcard:', error);
            this.flashcardErrors = true;
            this.flashcardErrorMsg = 'There were problems saving this flashcard.';
          });
      },
      validateForm() {
        if (!this.flashcard.question || !this.flashcard.answer) {
          this.flashcardErrors = true;
          this.flashcardErrorMsg = 'Please fill out both question and answer.';
          return false;
        }
        return true;
      }
    }
  };
  </script>
  
  <style scoped>
  .error-message {
    color: red;
    margin-bottom: 10px;
  }
  .form-group {
    margin-bottom: 15px;
  }
  </style>