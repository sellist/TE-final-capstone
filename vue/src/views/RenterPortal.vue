// Renter portal, if renting, should show the property they have with option to
pay rent or request maintenance // if not renting, should give a message to
contact a landlord

<template>
  <div>
    <renter-home></renter-home>
  </div>
</template>

<script>
import RenterHome from "../components/RenterHome.vue";
export default {
  components: {
    RenterHome,
  },
};
</script>

<style></style>
