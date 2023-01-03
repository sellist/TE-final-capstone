//from landlord or renter portal, view all rent invoices

<template>
  <div id="container">
    <h1 id="heading">View All Rent Invoices</h1>
<div class="tableContents">
    <table id="tableAllRents">
      <thead>
        <tr>
          <th>Invoice ID</th>
          <th>Property ID</th>
          <th>Renter ID</th>
          <th>Created Date</th>
          <th>Amount Due</th>
          <th>Due Date</th>
          <th>Amount Paid</th>
          <th>Paid Date</th>
          <th>Status</th>
          <th>Overdue?</th>
        </tr>
      </thead>

        <tbody>
          <tr v-for="invoice in invoices" v-bind:key="invoice.id">
            <td>{{ invoice.invoiceId }}</td>
            <td>{{ invoice.propertyId }}</td>
            <!--<td>{{ getAddress(invoice.propertyId) }}</td> -->
            <td>{{ invoice.renterId }}</td>
            <td>{{ invoice.createdDate }}</td>
            <td>{{ invoice.amountDue }}</td>
            <td>{{ invoice.dueDate }}</td>
            <td>{{ invoice.amountPaid }}</td>
            <td>{{ invoice.paidDate }}</td>
            <td>{{ invoice.status > 0 ? "Unpaid" : "Paid" }}</td>
            <td>{{ today > invoice.dueDate ? "Yes" : "No" }}</td>
          </tr>

        </tbody>
    </table>
              </div>
  </div>
</template>

<script>
import InvoiceService from "@/services/InvoiceService.js";
//import PropertyService from "@/services/PropertyService.js"

export default {
  name: "invoices-all",
  props: ["userId"],

  data() {
    return {
      invoices: [],
      today: this.getToday(),
    };
  },

  methods: {
    getToday() {
      let currentDate = new Date().toJSON().slice(0, 10);
      return currentDate;
    },

    // getRenter(userId) {

    // },

    // getAddress(propertyId){
    //   let property = PropertyService.getPropertybyPropertyId(propertyId)
    //   .then((response) => {
    //   property = response.data
    //   })
    //   return property
    // }
  },

  created() {
    InvoiceService.getAllInvoicesByLandlordId(this.userId).then((response) => {
      this.invoices = response.data;
    });
  },
};
</script>

<style scoped>
table {
  width: 100%;
  font-family: Arial, Helvetica, sans-serif;
}

#heading {
  text-align: center;
  font-family: Arial, Helvetica, sans-serif;
}

th {
  height: 70px;
  text-align: center;
}


.tableContents {
  display: flex;
  align-items: center;
  border-color: black;
  border-style: solid;
  border-width: 1px;
  border-radius: 5px;
  margin-bottom: 8px;
  padding: 5px;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  text-align: center;
  width: auto;
}
</style>
