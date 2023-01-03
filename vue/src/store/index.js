import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    neighborhoodList: [ 'Bloomfield', 'East Liberty', 'Highland Park', 'Lawrenceville', 'Morningside',
   'Oakland', 'Point Breeze', 'Shadyside', 'Squirrel Hill North', 'Squirrel Hill South'],
    homeTypeList: [ 'All types', 'apartment', 'house', 'townhouse', 'other' ],
    filteredProperties: [],
    currentRole: '',
    correctRole: true,
    isInLandlordPortal: false,
    isInRenterPortal: false
  
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      state.currentRole = '';
      axios.defaults.headers.common = {};
    },
    UPDATE_FILTERED_PROPERTIES(state, properties) {
      this.state.filteredProperties = properties;
    },
    UPDATE_CORRECT_ROLE(state, isCorrect) {
      this.state.correctRole = isCorrect;
    },
    UPDATE_CURRENT_ROLE(state, role) {
      this.state.currentRole = role;
    },
    UPDATE_IS_IN_LANDLORD_PORTAL(state, isIn) {
      this.state.isInLandlordPortal = isIn;
    },
    UPDATE_IS_IN_RENTER_PORTAL(state, isIn) {
      this.state.isInRenterPortal = isIn;
    },

  }
})
