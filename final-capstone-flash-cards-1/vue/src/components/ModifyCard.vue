<template>
  <div class="modal-overlay" @click.self="closeModal">
    <div class="modal-content light-blue-background">
      <h3>Edit Flashcard</h3>
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="question">Question:</label>
          <input
            type="text"
            id="question"
            v-model="editableFlashcard.question"
            required
          />
        </div>
        <div class="form-group">
          <label for="answer">Answer:</label>
          <input
            type="text"
            id="answer"
            v-model="editableFlashcard.answer"
            required
          />
        </div>
        <div class="form-group">
          <label for="tags">Tags:</label>
          <input type="text" id="tags" v-model="editableFlashcard.tags" />
        </div>
        <div class="form-group">
          <label for="username">Username:</label>
          <input
            type="text"
            id="username"
            v-model="editableFlashcard.username"
            required
            disabled
          />
        </div>
        Choose Deck:
        <select
          v-model="selectedDeck.id"
          @change="assignDeck(editableFlashcard.id, selectedDeck.id)"
        >
          <option v-for="deck in decks" :key="deck.id" :value="deck.id">
            {{ deck.deckName }}
          </option>
        </select>
        <br />
        <br />
        <button type="submit">Save Changes</button>
        <button type="button" @click="closeModal">Cancel</button>
      </form>
    </div>
  </div>
</template>
  
  <script>
import deckService from "../services/FlashcardDeckService.js";

export default {
  props: {
    flashcard: {},
  },
  data() {
    return {
      editableFlashcard: {},
      decks: [],
      selectedDeck: {},
    };
  },
  watch: {
    flashcard: {
      immediate: true,
      handler(newValue) {
        this.editableFlashcard = { ...newValue };
      },
    },
  },
  methods: {
    submitForm() {
      this.$emit("save", this.editableFlashcard);
    },
    closeModal() {
      this.$emit("close");
    },
    assignDeck(flashcardId, deckId) {
      deckService.addFlashcardsToDeckByIds(flashcardId, deckId);
    },
  },
  created() {
    deckService.findDecksByUsername().then((response) => {
      if (response.status == 200) {
        this.decks = response.data;
      }
    });
  },
};
</script>
  
<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  width: 500px;
  max-width: 90%;
}

.light-blue-background {
  background-color: #add8e6; 
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
}

.form-group input[type="text"] {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
}

button {
  margin-right: 10px;
}
</style>