<template>
  <div class="light-blue-background">
      <h1><span class="deck-name-title">Flashcards in {{ deckName }}</span></h1>
    </div>
  <br>

  <div class="inner-container">
    <button @click="toggleDisplayMode()" class="toggle-display-mode">Toggle Display Mode</button>

    <div v-if="flashcards.length === 0">
      <p>No flashcards available in this deck.</p>
    </div>

    <!-- Carousel View -->
    <template v-if="showCarousel">
      <Carousel class="flashcard-carousel" :value="flashcards" :numVisible="3" :numScroll="1">
        <template v-slot:item="slotProps">
          <div class="flashcard">
            <div class="question">
              <strong>Question:</strong> {{ slotProps.data.question }}
            </div>
            <div class="answer">
              <strong>Answer:</strong> {{ slotProps.data.answer }}
            </div>
            <button class="remove-flashcard" @click="removeCard(slotProps.data.id, deckId)">Remove Card</button>
          </div>
        </template>
      </Carousel>
    </template>

    <!-- List View -->
    <div v-else>
      <div v-for="(flashcard, index) in flashcards" :key="index" class="flashcard-text-container">
        <p class="flashcard-text">
          <strong>Question:</strong> {{ flashcard.question }}<br>
          <strong>Answer:</strong> {{ flashcard.answer }}
          <br>
          <button class="remove-flashcard" @click="removeCard(flashcard.id, deckId)">Remove Card</button>
        </p>
      </div>
    </div>

    <button @click="returnHome" class="return-home-button">Return Home</button>
  </div>
</template>
  
  <script>
  import axios from "axios";
  import FlashcardDeckService from "../services/FlashcardDeckService";
  import Carousel from "primevue/carousel";
  export default {
    data() {
      return {
        deckId: this.$route.params.deckId,
        deckName: "",
        flashcards: [],
        showCarousel: true,
      };
    },
    components:{
      Carousel
    },
    async created() {
      await this.fetchDeckFlashcards();
      
    },
    methods: {
      async fetchDeckFlashcards() {
        try {
          const response = await axios.get(`http://localhost:9000/search/flashcardsByDeckId/${this.deckId}`);
          this.flashcards = response.data 
          
        } catch (error) {
          console.error("Error fetching flashcards:", error);
          this.flashcards = [];
        }
        FlashcardDeckService.findDeckById(this.deckId).then(
          response =>{
            this.deckName = response.data.deckName;
          }
        )
      },
      returnHome() {
        this.$router.push({ name: "home" });
      },
      removeCard(flashcardId,deckId){
        FlashcardDeckService.removeFlashcardFromDeck(flashcardId,deckId);
        window.location.reload();
      },
      toggleDisplayMode(){
        this.showCarousel = !this.showCarousel;
      }
    },
  };
  </script>
  
  <style scoped>
  .light-blue-background {
   width: 100%; 
  background-color: lightblue;
  padding: 20px;
  border-radius: 8px;
  text-align: center; 
  margin-bottom: 20px; 
  }
  
  .centered-content, .toggle-display-mode, .return-home-button {
    text-align: center;
    margin: 20px 0; 
  }
  
  .inner-container {
    display: flex;
    flex-direction: column;
    align-items: center; 
    margin: auto; 
    width: auto;
    max-width: 500px; 
  }
  
  .flashcard-carousel {
    margin: 5px; 
    padding: 40px; 
    background-color: lightblue; 
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); 
  }
  
  .flashcard, .flashcard-text-container {
  display: flex;
  flex-direction: column;
  justify-content: space-between; 
  width: auto;
  height: auto;
  border: 1px solid #eee;
  padding: 20px;
  margin: 10px; 
  border-radius: 8px;
  box-shadow: 0 2px 1px rgba(0, 0, 0, 0.1);
  background-color: #f0f0f0; 
  box-sizing: border-box; 
}
  
  .return-home-button {
    display: block;
    margin-top: 20px;
  }

  </style>