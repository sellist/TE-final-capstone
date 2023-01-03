// Landing page for app. Hovering over a county on the map should highlight BOTH
the county and the list item, and vice versa.

<template>
    <div>
        <div class="title">Title!</div>
        <div class="blurb">Blurb about the thing!</div>
        
        <wrong-role v-show="!correctRole" />
        <div v-show="correctRole" class="main-map">
            <interactable-map />
            <home-screen />
        </div>
        <!-- <p>You must be authenticated to see this</p> -->
    </div>
</template>

<script>
import HomeScreen from "../components/Neighborhoods.vue";
import InteractableMap from "../components/InteractableMap.vue";
import WrongRole from "../components/WrongRole.vue";
export default {
    name: "home",
    components: {
        HomeScreen,
        InteractableMap,
        WrongRole
    },
    computed: {
        correctRole() {
            return this.$store.state.correctRole;
        },
        role() {
            return this.$store.state.currentRole;
        }
    },
    created() {
    this.$store.commit("UPDATE_IS_IN_LANDLORD_PORTAL", false);
  }
};
</script>

<style scoped>
.title {
    font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    text-align: center;
    font-size: 5rem;
}
.blurb {
    font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    text-align: center;
    font: 2rem;
}
.main-map {
    display: flex;
}
</style>
