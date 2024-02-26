import axios from 'axios';

export default {

  createFlashcard(flashcard) {
    return axios.post('/create/flashcard', flashcard)
  },

  modifyFlashcard(flashcard, id) {
    return axios.put(`/modify/flashcards/${id}`, flashcard)
  },

  findFlashcardById(id) {
    return axios.get(`/search/flashcard/${id}`)
  },

  findFlashcardsByTag(tag) {
    return axios.get(`/search/flashcards-byTag/${tag}`)
  },

  findFlashcardsByUsername() {
    return axios.get('/search/flashcards-byUsername')
  }
}
