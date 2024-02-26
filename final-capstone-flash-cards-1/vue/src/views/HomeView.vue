<template>
    <div class="home light-blue-background">
      <h1>My Homepage</h1>
    </div>
  <div>
    <div class="slide">
      <Carousel
        class="slide"
        :value="flashcards"
        :numVisible="2"
        :numScroll="1"
        :responsiveOptions="responsiveOptions"
      >
        <template #header>
          <h2>My Flashcards</h2>
        </template>
        <template #item="slotProps">
          <div class="card-item">
            <div class="card-content">
              <div>
                <div class="card-question">
                  Question: {{ slotProps.data.question }}
                </div>
                <br />
                <div class="card-answer">
                  Answer: {{ slotProps.data.answer }}
                </div>
                <br />
                <button @click="editFlashcard(slotProps.data)">Modify</button>
              </div>
            </div>
          </div>
        </template>
      </Carousel>
    </div>
  </div>

  <nav class="card-nav">
    <router-link :to="{ name: 'create-flashcard' }"
      >Create New Flashcards</router-link
    >
    |
    <router-link :to="{ name: 'search-flashcards' }"
      >Search My Flashcards</router-link
    >
  </nav>
  <br />
  <div>
    <div class="slide">
      <Carousel
        class="slide"
        :value="decks"
        :numVisible="2"
        :numScroll="1"
        :responsiveOptions="responsiveOptions"
      >
        <template #header>
          <h2>My Decks</h2>
        </template>
        <template #item="slotProps">
          <div class="deck-item">
            <div class="deck-content">
              <div>
                <div class="deck-name">
                  Deck Name: {{ slotProps.data.deckName }}
                </div>
                <br />
                <div class="deck-description">
                  Description: {{ slotProps.data.description }}
                </div>
                <br />
                <button @click="viewFlashcards(slotProps.data.id)">View</button>
                <br/>
                <br/>
                <button @click="editDeck(slotProps.data)">Modify</button>
              </div>
            </div>
          </div>
        </template>
      </Carousel>
    </div>
  </div>
  <div class="test">
    <modify-deck
      v-if="selectedDeck"
      :deck="selectedDeck"
      @close="selectedDeck = null"
      @save="saveDeck"
    />
    <modify-card
      v-if="selectedFlashcard"
      :flashcard="selectedFlashcard"
      @close="selectedFlashcard = null"
      @save="saveFlashcard"
    />
  </div>

  <nav class="deck-nav-but-better">
    <!-- <button class="option" @click="toggle('create-deck')">Create Deck</button>
    <button class="option" @click="toggle('add-card-to-deck')">Add Flashcards to my deck</button> -->
    <router-link :to="{ name: 'create-deck' }">Create New Decks</router-link> |
    <router-link :to="{ name: 'study-session' }">Start A Study Session!</router-link>
  </nav>
</template>

<script>
import Carousel from "primevue/carousel";
// import Flashcard from "../components/Flashcard.vue";
// import FlashcardDeck from "../components/FlashcardDeck.vue";
import cardService from "../services/FlashcardService";
import deckService from "../services/FlashcardDeckService";
import ModifyCard from "../components/ModifyCard.vue";
import CreateFlashcardView from "../views/CreateFlashcardView.vue";
import ModifyDeck from "../components/ModifyDeck.vue";
import CreateDeckView from "../views/CreateDeckView.vue";
import StudySessionView from "../views/StudySessionView.vue";

export default {
  components: {
    Carousel,
    ModifyCard,
    ModifyDeck,
    // Flashcard,
    // FlashcardDeck
  },
  data() {
    return {
      flashcards: [],
      decks: [],
      selectedFlashcard: null,
      selectedDeck: null,
      activeIndex: 0,
      responsiveOptions: [
        {
          breakpoint: "1024px",
          numVisible: 3,
          numScroll: 3,
        },
        {
          breakpoint: "600px",
          numVisible: 2,
          numScroll: 2,
        },
        {
          breakpoint: "480px",
          numVisible: 1,
          numScroll: 1,
        },
      ],
    };
  },
  created() {
    cardService.findFlashcardsByUsername().then((response) => {
      if (response.status == 200) {
      console.log('hot dog')
      console.log(response)
        this.flashcards = response.data;
      }
    });
    deckService.findDecksByUsername().then((response) => {
      if (response.status == 200) {
        this.decks = response.data;
      }
    });
  },
  methods: {
    onSlide(event) {
      this.activeIndex = event.index;
    },
    editFlashcard(flashcard) {
      this.selectedFlashcard = { ...flashcard };
    },
    editDeck(deck) {
      this.selectedDeck = { ...deck };
    },
    viewFlashcards(deckId) {
      this.selectedFlashcards = { ...deckId };
      this.$router.push({name:'decks-flashcards', params: {deckId:deckId}})
    },
    saveFlashcard(flashcard) {
      cardService.modifyFlashcard(flashcard, flashcard.id).then((response) => {
        if (response.status == 200) {
          this.selectedFlashcard = null;
        }
        try {
          this.selectedFlashcard = null;
        } catch (error) {
          window.alert("Failed to update the flashcard.");
        }
        window.location.reload();
      });
    },
    saveDeck(deck) {
      deckService.modifyDeck(deck, deck.id).then((response) => {
        if (response.status == 200) {
          this.selectedDeck = null;
        }
        try {
          this.selectedDeck = null;
        } catch (error) {
          window.alert("Failed to update the deck.");
        }
        window.location.reload();
      });
    },
  },
};
</script>

<style scoped>
.home-page-container {
  text-align: center;
}

.light-blue-background {
  width: 100%; 
  background-color: lightblue;
  padding: 10px;
  text-align: center; 
  margin-bottom: 20px; 
  border-bottom-left-radius: 8px;
  border-bottom-right-radius: 8px;
}

.slide, .card-nav, .deck-nav-but-better {
  text-align: center; 
  max-width: 600px; 
  margin: 0 auto; 
}

.deck-flashcards-container, .slide {
  background-color: lightblue;
  border-radius: 8px;
  margin-top: 10px;
}

.card-item {
  display: flex;
  flex-direction: column;
  justify-content: space-between; 
  width: flex;
  height: 175px;
  border: 1px solid #eee;
  padding: 20px;
  margin: 10px; 
  border-radius: 8px;
  box-shadow: 0 2px 1px rgba(0, 0, 0, 0.1);
  background-color: #f0f0f0; 
  box-sizing: border-box; 
}

.deck-item {
  display: flex;
  flex-direction: column;
  justify-content: space-between; 
  width: flex;
  height: 220px;
  border: 1px solid #eee;
  padding: 20px;
  margin: 10px; 
  border-radius: 8px;
  box-shadow: 0 2px 1px rgba(0, 0, 0, 0.1);
  background-color: #f0f0f0; 
  box-sizing: border-box; 
}
.card-content {
  word-wrap: break-word; 
  overflow-wrap: break-word; 
}

.card-question, .card-answer {
  word-wrap: break-word;
  overflow-wrap: break-word;
  white-space: normal; 
}

</style>