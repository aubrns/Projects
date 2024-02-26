<template>
  <div>
    <div class="light-blue-background">
      <h1>Study Session</h1>
    </div>
    <br>

    <div class="inner-container">
      <div v-if="sessionEnded">
        <h1>Session Summary</h1>
        <p>Correct Answers: {{ correctAnswers }}</p>
        <p>Incorrect Answers: {{ incorrectAnswers }}</p>
        <button @click="restartSession" class="action-button">Restart Session</button>
        <button @click="endStudySession(true)" class="action-button">Return Home</button>
      </div>

      <div v-else>
        <div class="flip-card" @click="isFlipped = !isFlipped">
          <div :class="['flip-card-inner', { 'is-flipped': isFlipped }]">
            <div class="flip-card-front">
              <h1><span style="font-weight: normal;">Question:</span> <br>{{ currentFlashcard.question }}</h1>
            </div>
            <div class="flip-card-back">
              <h1><span style="font-weight: normal;">Answer:</span> <br>{{ currentFlashcard.answer }}</h1>
              <!-- Move Correct and Incorrect buttons here -->
              <div class="buttons-container">
                <button @click.stop="markAnswer(true)" class="action-button">Correct</button>
                <button @click.stop="markAnswer(false)" class="action-button">Incorrect</button>
              </div>
            </div>
          </div>
        </div>
        <div class="session-action-buttons">
        <button @click="nextFlashcard" class="action-button">Skip Card</button>
        <button @click="endStudySession(false)" class="action-button">End Study Session</button>
      </div>
      </div>
    </div>
  </div>
</template>
  
<script>
import axios from "axios";

export default {
  data() {
    return {
      flashcards: [],
      currentIndex: 0,
      showAnswer: false,
      correctAnswers: 0,
      incorrectAnswers: 0,
      sessionEnded: false,
      isFlipped: false,
    };
  },
  computed: {
    currentFlashcard() {
      return this.flashcards[this.currentIndex] || {};
    },
  },
  async created() {
    await this.fetchFlashcards();
  },
  methods: {
    async fetchFlashcards() {
      try {
        const response = await axios.get(
          "http://localhost:9000/search/flashcards-byUsername"
        );
        this.flashcards = response.data;
        this.currentIndex = 0;
        this.correctAnswers = 0;
        this.incorrectAnswers = 0;
        this.sessionEnded = false;
      } catch (error) {
        console.error("Error fetching flashcards:", error);
        this.flashcards = [];
      }
    },
    nextFlashcard() {
      this.currentIndex = (this.currentIndex + 1) % this.flashcards.length;
      this.isFlipped = false; 
    },
    markAnswer(isCorrect) {
      if (isCorrect) {
        this.correctAnswers++;
      } else {
        this.incorrectAnswers++;
      }
      this.nextFlashcard(); 
    },
    endStudySession(goHome) {
      this.sessionEnded = true;
      if (goHome) {
        this.$router.push({ name: "home" });
      }
    },
    restartSession() {
      this.fetchFlashcards();
    },
  },
};
</script>
  
<style scoped>
.light-blue-background {
  background-color: lightblue;
  padding: 20px;
  text-align: center;
  margin-bottom: 20px;
  border-bottom-left-radius: 8px;
  border-bottom-right-radius: 8px;
}

.inner-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: auto;
  max-width: 600px;
  margin: auto;
}

.toggle-display-mode, .return-home-button {
  margin: 20px;
  padding: 8px 15px;
  border: none;
  border-radius: 8px;
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

.session-action-buttons {
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%; 
  margin-top: 20px; 
}

.action-button:hover {
  background-color: #0056b3;
}

.flip-card {
  perspective: 1000px;
  width: 300px; 
  height: 300px; 
  margin: 20px auto; 
  border: 2px solid #ddd; 
  border-radius: 8px; 
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
  background-color: #f9f9f9; 
  display: flex;
  justify-content: center;
  align-items: center;
}

.flip-card-inner {
  position: relative;
  width: 90%; 
  height: 90%; 
  text-align: center;
  transition: transform 0.6s;
  transform-style: preserve-3d;
}

.flip-card-front, .flip-card-back {
  display: flex;
  flex-direction: column;
  justify-content: space-between; 
  align-items: center; 
  position: absolute;
  width: 100%;
  height: 100%; 
  backface-visibility: hidden;
  border-radius: 8px;
  padding: 20px; 
}

.flip-card-back {
  transform: rotateY(180deg);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
}

.buttons-container {
  width: 100%;
  display: flex;
  justify-content: space-evenly; /* Adjust this as needed */
  margin-top: 20px; /* Adds some space between the answer and the buttons */
}

.flip-card-inner.is-flipped {
  transform: rotateY(180deg);

  .unbold {
  font-weight: normal;
}
}
</style>