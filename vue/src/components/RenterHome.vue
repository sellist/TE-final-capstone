<template>
  <div class="main">
    <div class="option">
      <router-link class="submit-button" :to="{ name: 'RenterInvoices' }">
        <h2>Manage Rent</h2>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
    created() {
        this.$store.commit("UPDATE_IS_IN_RENTER_PORTAL", true);
    }

}
</script>

<style>

</style>