<template>
  <div>
      <invoices-all :landlordId="$route.params.landlordId"></invoices-all> 

  </div>
</template>

<script>
import InvoicesAll from "../components/InvoicesAll.vue"

export default {
  components: {
    InvoicesAll
  }
}
</script>

<style>

.main {
  text-align: center;
}

.option {
  margin-top: 40px;
  margin-bottom: 40px;
  
}
</style>