<template>
  <div class="search-page-container">
    <div class="light-blue-background">
      <h1>Search Flashcards</h1>
    </div>
    <form @submit.prevent="searchFlashcards" class="search-form">
      <label for="search-tag">Search by tag:   </label>
      <input id="search-tag" type="text" v-model="searchTerm" />
      <button type="submit">Submit</button>
    </form>

    <div v-if="flashcards.length > 0">
      <h2>Search Results</h2>
      <div v-for="flashcard in flashcards" :key="flashcard.id" class="flashcard">
       <p><strong>Question: </strong> {{ flashcard.question }}</p>
       <p><strong>Answer: </strong> {{ flashcard.answer }}</p>
        <p><strong>Tags: </strong> {{ flashcard.tags }}</p>
        <button @click="editFlashcard(flashcard)">Modify</button>
      </div>
    </div>
    <div v-else>
      <p>No results found.</p>
    </div>

    <modify-card
      v-if="selectedFlashcard"
      :flashcard="selectedFlashcard"
      @close="selectedFlashcard = null"
      @save="saveFlashcard"
    />
  </div>
</template>
  
  <script>
  import axios from 'axios';
  import ModifyCard from '../components/ModifyCard.vue';
  
  export default {
    components: {
      ModifyCard
    },
    data() {
      return {
        searchTerm: '',
        flashcards: [],
        selectedFlashcard: null,
      };
    },
    methods: {
      async searchFlashcards() {
        if (!this.searchTerm.trim()) {
          alert('Please enter a search term.');
          return;
        }
        try {
          const response = await axios.get(`http://localhost:9000/search/flashcards-byTag/${this.searchTerm}`);
          this.flashcards = response.data;
        } catch (error) {
          console.error('Error searching flashcards:', error);
          this.flashcards = [];
        }
      },
      editFlashcard(flashcard) {
        this.selectedFlashcard = { ...flashcard };
      },
      async saveFlashcard(updatedFlashcard) {
        try {
          await axios.put(`http://localhost:9000/modify/flashcards/${updatedFlashcard.id}`, updatedFlashcard);
          alert('Flashcard updated successfully.');
          this.searchFlashcards(); 
        } catch (error) {
          console.error('Error updating flashcard:', error);
          alert('Failed to update the flashcard.');
        }
        this.selectedFlashcard = null;
      }
    },
  };
  </script>
  
  <style scoped>
  .search-page-container {
  text-align: center;
}

.light-blue-background {
  background-color: lightblue;
  padding: 10px;
  border-bottom-left-radius: 8px;
  border-bottom-right-radius: 8px;
}

.flashcard {
  border: 1px solid #eee;
  padding: 20px;
  margin: 10px auto; 
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #f0f0f0; 
  max-width: 250px;
}

.search-form {
  margin: 20px 0;
}
</style>