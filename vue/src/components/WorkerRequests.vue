<template>
  <div>
      
    <h1>Your Maintenance Requests</h1>

    <table id="workerRequests">
      <thead>
        <tr>
          <th>Request ID</th>
          <th>Property Address</th>
          <th>Category</th>
          <th>Title</th>
          <th>Description</th>
          <th>Request Date</th>
          <th>Completed?</th>
        </tr>
      </thead>
      <tbody v-if="vis">
        <tr v-for="request in requests" v-bind:key="request.requestId" >
          <td>{{request.requestId}}</td>
          <td>{{request.address}}</td>
          <td>{{request.category}}</td>
          <td>{{request.title}}</td>
          <td>{{request.description}}</td>
          <td>{{request.requestDate}}</td>
          <!-- there should be a toggle of some kind here to mark it completed which inputs a completed date -->
        </tr>

      </tbody>

    </table>

  </div>
</template>

<script>
import maintenanceService from "@/services/MaintenanceService.js";
import propertyService from "@/services/PropertyService.js";

export default {
  name: "worker-requests",
  props: ["workerId"], // TODO: this can come from the path for now, but should ultimately come from the principal
  data() {
    return {
      requests: [],
      vis: false,
    };
  },
  methods: {
    async getAddress(request) {
      propertyService.getPropertyByPropertyId(request.propertyId)
      .then((response) => {
        request.address = response.data.address;
        this.vis = true;
      })
    }
  },
  mounted() {
      maintenanceService
        .getRequestsByWorkerId(parseInt(this.workerId))
        .then((response) => {
          for (let o of response.data) {
            o['address'] = this.getAddress(o);
            this.requests.push(o);
          } 
      })
    }
};
</script>

<style>
</style>