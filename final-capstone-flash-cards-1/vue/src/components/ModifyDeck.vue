<template>
  <div class="modal-overlay" @click.self="closeModal">
    <div class="modal-content light-blue-background">
      <h3>Edit Deck</h3>
      <form @submit.prevent="submitForm">
          <div class="form-group">
            <label for="deck-name">Deck Name:</label>
            <input type="text" id="deck-name" v-model="editableDeck.deckName" required>
          </div>
          <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" v-model="editableDeck.username">
          </div>
          <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" id="description" v-model="editableDeck.description" required>
          </div>
          <button type="submit">Save Changes</button>
          <button type="button" @click="closeModal">Cancel</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      deck: {
      },
    },
    data() {
      return {
        editableDeck: {},
      };
    },
    watch: {
      deck: {
        immediate: true,
        handler(newValue) {
          this.editableDeck = { ...newValue };
        },
      },
    },
    methods: {
      submitForm() {
        this.$emit('save', this.editableDeck);
      },
      closeModal() {
        this.$emit('close');
      },
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
    background-color: #add8e6; /* Light blue background color */
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