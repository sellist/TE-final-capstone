<template>
  <div class="wrong">
      <h2>It looks like you are not a {{ role }}.</h2>
      <h2>Please select the correct portal before you log in.</h2>
      <div class="submit-button" @click="proceed"><h2>Continue</h2></div>
  </div>
</template>

<script>
export default {
    name: "WrongRole",
    created() {
            console.log(this.$store.state.currentRole);
            console.log(this.$store.state);
        },
    
    computed: {
        role() {
            return this.$store.state.currentRole.substring(5).toLowerCase();
        }
    },
    methods: {
        
        proceed() {
            this.$store.commit("UPDATE_CORRECT_ROLE", true);
            this.$router.push("/logout");
        },
        
    }
}
</script>

<style>
.wrong {
    margin-top: 30px;
    text-align: center;
}
</style>