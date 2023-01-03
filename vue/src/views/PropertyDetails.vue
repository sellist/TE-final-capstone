// View for when a property is clicked, will show description and details about that properties. Should have a back button somewhere probly.

<template>
  <div>
    <property-listing :propertyId="$route.params.propertyId" />
  </div>
</template>

<script>
import PropertyListing from "../components/PropertyListing.vue";
export default {
  components: {
    PropertyListing
  }

}
</script>

<style scoped>

</style>