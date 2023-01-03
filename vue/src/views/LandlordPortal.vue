//View only for landlords, should include: 
  //View Own Properties button, 
  //Add New Property button, 
  //View All Rents button, 
  //View Maintenance Requests button

<template>
  <div>
      <landlord-home />
  </div>
</template>

<script>
import LandlordHome from "../components/LandlordHome.vue";
export default {
  name: "LandlordPortal",
  components: { LandlordHome }

}
</script>

<style>

</style>