<template>
  <div class="ll-main">

    <div class="ll-option">
      <router-link class="submit-button" :to="{ name: 'LandlordProperties' }">
        <h2>Manage Properties</h2>
      </router-link>
    </div>
    <div class="ll-option">
      <router-link class="submit-button" :to="{ name: 'LandlordInvoices' }">
        <h2>Manage Invoices</h2>
      </router-link>
    </div>
    <div class="ll-option">
      <router-link class="submit-button" :to="{ name: 'LandlordRequests' }">
        <h2>Manage Maintenance Requests</h2>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  created() {
    this.$store.commit("UPDATE_IS_IN_LANDLORD_PORTAL", true);
  }
};
</script>

<style>

.ll-main {
  text-align: center;
}

.ll-option {
  margin-top: 40px;
  margin-bottom: 40px;
  
}
a {
  text-decoration: none;
}

</style>