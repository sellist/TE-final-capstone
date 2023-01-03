// This file is used as a playground for testing out components. Path to this component in view is localhost:8080/guide

<template>
  <div class="guide-container">
    <input type="text" name="houseNumber" id="" v-model="address.houseNumber" placeholder="houseNumber">
    <input type="text" name="city" id="" v-model="address.city" placeholder="city">
    <input type="text" name="state" id="" v-model="address.state" placeholder="state">
    <input type="text" name="street" id="" v-model="address.street" placeholder="street">
    <input type="text" name="zip" id="" v-model="address.zip" placeholder="zip">
    <button v-on:click="callGoogle(address)">sub</button>
    <div>
      <div>{{this.formattedAddress}}</div>
    </div>
  </div>
</template>

<script>
import GoogleAddressService from '../services/GoogleAddressService';

export default {
  data () {
    return {
      address: {
          houseNumber: "",
          street: "",
          city: "",
          state: "",
          zip: "",
        },
        formattedAddress: "",
    }
  },
  methods: {
    formatAddressAsStringService() {
      GoogleAddressService.validateAddress(this.address).then(response => {
        this.formattedAddress = response.data.result.address.formattedAddress;
      });
    }
  }
}
</script>

<style scoped>
  .guide-container {
    display: flex;
    flex-direction: column;
  }
</style>

