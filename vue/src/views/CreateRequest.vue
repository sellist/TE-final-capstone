<template>
  <div>
      <h1 class="heading">Submit a Maintenance Request</h1>
      <maintenance-form :propertyId="$route.params.propertyId" />
  </div>
</template>

<script>
import maintenanceForm from "@/components/MaintenanceRequestForm.vue";
export default {
    components: {
        maintenanceForm
    }
}
</script>

<style>
.heading {
    font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
    text-align: center;
    font-size: 2rem;
}
</style>