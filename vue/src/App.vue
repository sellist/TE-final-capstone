<template>
    <div id="app">
        <!-- Nav bar should read data from $state and it should respond to that. Feel free to edit as needed. -->
        <nav-bar />

    <!-- Code given initially, commented out for now. -->
    <!-- <div id="nav">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div> -->
        <router-view />
    </div>
</template>

<script>
import NavBar from "./components/NavBar.vue";
export default {
    components: { NavBar },
};
</script>

<style scoped></style>
