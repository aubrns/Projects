import axios from 'axios';

export default {

    createFlashcardDeck(flashcardDeck) {
        return axios.post('/create/deck', flashcardDeck)
    },

    modifyDeck(flashcardDeck, id) {
        return axios.put(`/modify/deck/${id}`, flashcardDeck)
    },
    
    addFlashcardsToDeckByIds(flashcardId, deckId) {
        return axios.post(`/fill-deck/${flashcardId}/${deckId}`)
    },

    findDeckById(id) {
        return axios.get(`/search/deck/${id}`)
    },

    findDecksByUsername() {
        return axios.get('/search/deck-byUsername')
    },

    findFlashcardsByDeckId(id) {
        return axios.get(`/search/flashcardsByDeckId/${id}`)
    },

    removeFlashcardFromDeck(flashcardId, deckId){
        return axios.delete(`/remove-flashcard-from-deck/${flashcardId}/${deckId}`)
    }

}