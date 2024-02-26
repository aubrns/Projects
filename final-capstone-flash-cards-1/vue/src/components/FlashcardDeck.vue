<template>
  <div class="deck" v-for="deck in decks" :key="deck.id">
    <div class="deckName">{{ deck.deckName }}</div>
    <div class="description">{{ deck.description }}</div>
  </div>
</template>

<script>
import cardDeckService from '../services/FlashcardDeckService'

export default {
  data() {
    return {
      decks: []
    }
  },
  created() {
    cardDeckService.findDecksByUsername().then(
      (response) => {
        if (response.status === 200) {
          this.decks = response.data;
        }
      }
    )
  }
}
</script>

<style>
.deck {
  display: inline-block;
  border: 1px solid #eee;
  padding: 20px;
  margin-bottom: 10px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  background-color: #add8e6; 
}

</style>
