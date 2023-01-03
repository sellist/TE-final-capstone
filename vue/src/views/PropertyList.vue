<template>
  <!-- View to hold requested properties components -->
  <!-- i.e. <property-listing v-for="property in this.returnedData" /> etc -->
  <div>
    <filter-form />
    <!-- <new-property v-show="$store.state.isInLandlordPortal == true" /> -->
    <properties :neighborhood="$route.params.neighborhood" />

  </div>
</template>

<script>

import Properties from "../components/Properties.vue";
import FilterForm from "../components/FilterForm.vue";
export default {
  components: { Properties, FilterForm },
  
  
};
</script>

<style></style>
