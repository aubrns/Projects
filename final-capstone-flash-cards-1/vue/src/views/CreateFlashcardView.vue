<template>
    <div class="light-blue-background">
      <h1>Create A Flashcard</h1>
    </div>
    <br>
    <div class="form-outer-container">
      <form @submit.prevent="saveFlashcard" class="form-inner-container">
        <div v-if="flashcardErrors" class="error-message">
          {{ flashcardErrorMsg }}
        </div>
        <div class="form-group">
          <label for="question">Question: </label>
          <input type="text" id="question" v-model="flashcard.question" required />
        </div>
        <div class="form-group">
          <label for="answer">Answer: </label>
          <input type="text" id="answer" v-model="flashcard.answer" required />
        </div>
        <div class="form-group">
          <label for="username">Username: </label>
          <input type="text" id="username" v-model="flashcard.username" required />
        </div>
        <div class="form-group">
          <label for="tags">Tags: </label>
          <input type="text" id="tags" v-model="flashcard.tags" required/>
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
          username: this.$store.state.user.username,
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
.light-blue-background {
  background-color: lightblue;
  padding: 20px;
  border-bottom-left-radius: 8px;
  border-bottom-right-radius: 8px;
  text-align: center;
}

.form-outer-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid #ccc;
  padding: 20px;
  margin: 20px auto; 
  border-radius: 5px;
  background-color: #f0f0f0;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  width: 80%;
  max-width: 500px; 
  box-sizing: border-box;
}

.form-inner-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-group {
  margin-bottom: 15px; 
}

.error-message {
  color: red;
  margin-bottom: 10px;
}

</style>