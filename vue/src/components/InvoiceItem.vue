//From Renter portal, see their invoice 
<template>
  <div>
      <h1>View My Rent Invoice</h1>

          <table id="tableMyRent">
      <thead>
        <tr>
          <th>Amount Due</th>
          <th>Due Date</th>
          <th>Amount Paid</th>
          <th>Paid Date</th>
          <th>Status</th>
        </tr>
      </thead>
      <tbody>
        <tr>{{ invoice.amountDue }}</tr>
        <tr>{{ invoice.dueDate }}</tr>
        <tr>{{ invoice.amountPaid }}</tr>
        <tr>{{ invoice.paidDate }}</tr>
        <tr>{{ invoice.status }}</tr>
      </tbody>
  </div>
</template>

<script>
import InvoiceService from "@/services/InvoiceService.js"

export default {
  name: 'invoice-item',
  props: ['invoiceId'],
  data() {
    return {
      invoice: [],
    };
  },

  created(){
      InvoiceService.getInvoiceById(this.invoiceId) 
      .then((response) => {
        this.invoice = response.data;
      })
    }
  }

</script>

<style>

</style>