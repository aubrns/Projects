<template>
  <div class="flashcard" v-for="flashcard in flashcards" :key="flashcard.id">
    <div class="question">{{ flashcard.question }}</div>
    <div class="answer">{{ flashcard.answer }}</div>
  </div>
</template>

<script>
import cardService from '../services/FlashcardService'

export default {
  data() {
    return {
      flashcards: []
    }
  },
  created() {
    cardService.findFlashcardsByUsername().then(
      (response) => {
        if (response.status === 200) {
          this.flashcards = response.data;
        }
      }
    )
  }
}
</script>

<style scoped>
.flashcard {
  display: inline-block;
  border: 1px solid #eee;
  padding: 20px;
  margin-bottom: 10px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #add8e6; 
}

</style>