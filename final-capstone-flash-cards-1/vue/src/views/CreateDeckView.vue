<template>
    <div class="light-blue-background">
      <h1>Create A Deck</h1>
    </div>
    <br>
    <div class="form-outer-container">
      <form @submit.prevent="saveDeck" class="form-inner-container">
        <div v-if="deckErrors" class="error-message">
          {{ deckErrorMsg }}
        </div>
        <div class="form-group">
          <label for="deckName">Deck Name: </label>
          <input type="text" id="deckName" v-model="flashcardDeck.deckName" required />
        </div>
        <div class="form-group">
          <label for="username">Username: </label>
          <input type="text" id="username" v-model="flashcardDeck.username" required />
        </div>
        <div class="form-group">
          <label for="description">Description: </label>
          <input type="text" id="description" v-model="flashcardDeck.description" required />
        </div>
        <div class="button-container">
          <button type="submit">Save</button>
        </div>
      </form>
    </div>
</template>

<script>
import deckService from "../services/FlashcardDeckService";
// import FlashcardDeck from "../components/FlashcardDeck.vue";

export default {
  data() {
    return {
      flashcardDeck: {
        deckName: '',
        username: this.$store.state.user.username,
        description: '',
      },
      deckErrors: false,
      deckErrorMsg: '',
    };
  },
  methods: {
    saveDeck() {
      if (!this.validateForm()) {
        return;
      }

      deckService
        .createFlashcardDeck(this.flashcardDeck)
        .then((response) => {
          if (response.status === 201) {
            window.alert("Deck saved successfully.");
            // Clear the form
            this.flashcardDeck.deckName = '';
            this.flashcardDeck.username = '';
            this.flashcardDeck.description = '';
          }
        })
        .catch((error) => {
          console.error("Error saving deck:", error);
          this.deckErrors = true;
          this.deckErrorMsg = "There were problems saving this deck.";
        });
    },
    validateForm() {
      if (!this.flashcardDeck.deckName || !this.flashcardDeck.description) {
        this.deckErrors = true;
        this.deckErrorMsg = "Please fill out both Deck Name and Description.";
        return false;
      }
      return true;
    },
  },
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

.button-container {
  display: flex;
  justify-content: center;
  width: 100%; 
}

.error-message {
  color: red;
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 15px;
}
</style>