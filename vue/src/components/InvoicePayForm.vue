<template>
  <div>
    <form class="form" v-on:submit.prevent="submitPayment">
      <div class="form-item">
        <label for="InvoiceID">Invoice ID:</label>
        <input id="invoiceID" type="number" min="0" max="1000" required="required" autofocus/>
      </div>

      <div class="form-item">
        <label for="rentAmount">Amount Paid (Please Enter Amount in Whole Dollars): $</label>
        <input id="rentAmount" type="number" min="0" max="invoice.amountDue"  required="required"/>
      </div>

    <div class="form-item">
        <label for="paidDate">Date of Payment: </label>
        <input id="paidDate" type="date" />
      </div>
      

      <div class="submit-form"></div>
      <div class="actions">
        <button class="submit-button" @click="submitPayment" type = "submit">Submit</button>
        <button class="submit-button" @click="resetForm" type="button">Cancel</button>

      </div>
    </form>
  </div>
</template>

<script>
import InvoiceService from "@/services/InvoiceService.js";

export default {
  data() {
    return {
      updatedInvoice: {
        invoiceId: 0,
        amountPaid: 0,
        datePaid: '',

      },
    };
  },

  methods: {
      submitPayment() {            
          InvoiceService
            .updateInvoice(this.invoice)
            .then(response => {
                if (response.status === 201) {
                    return this.$router.push(`/`) 
                }
            })

      },

      resetForm() {

      }

  }
};
</script>

<style scoped>
    .form {
        text-align: center;
    }
</style>
