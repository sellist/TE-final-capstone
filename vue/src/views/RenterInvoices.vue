<template>
  <div>
    <invoices-all :renterId="$route.params.renterId"></invoices-all>
   
    <button id="button" class="submit-button" v-on:click="isHidden = !isHidden">
      Pay Rent
    </button>
    <br />
    <div v-if="!isHidden">
      <invoice-pay-form :userId="$route.params.userId"></invoice-pay-form>
    </div>
  </div>
</template>

<script>
import InvoicesAll from "../components/InvoicesAll.vue";
import InvoicePayForm from "../components/InvoicePayForm.vue";

export default {
  data() {
    return {
      isHidden: true,
    };
  },
  components: {
    InvoicesAll,
    InvoicePayForm,
  },
};
</script>

<style>
#button {
    text-align: center;
}
</style>
