<template>
  <!-- Navbar to be on screen at all times. Data should be read from $state -->
  <div class="navbar">


    <div class="left">
      <router-link v-bind:to="{ name: 'home' }">
        <action-button> Home </action-button>
      </router-link>
    </div>

    <div class="portal-buttons">
      <router-link v-show="this.$store.state.currentRole === 'ROLE_RENTER'"
        v-bind:to="{ name: 'RenterPortal', params: { role: 'ROLE_RENTER' } }"
      >
        <action-button> Renter Portal </action-button>
      </router-link>

      <router-link v-show="this.$store.state.currentRole === 'ROLE_LANDLORD'"
        v-bind:to="{ name: 'LandlordPortal', params: { role: 'ROLE_LANDLORD' } }"
      >
        <action-button> Landlord Portal </action-button>
      </router-link>

      <router-link v-show="this.$store.state.currentRole === 'ROLE_WORKER'"
        v-bind:to="{ name: 'MaintenancePortal', params: { role: 'ROLE_WORKER' } }"
      >
        <action-button> Worker Portal </action-button>
      </router-link>
    </div> 

    <div>   
      <router-link v-bind:to="{ name: 'logout' }" v-show="$store.state.currentRole != ''">
      <action-button class="nav-spacing"> Logout </action-button>
    </router-link>

    </div>

    <!-- <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">
      <action-button> Logout </action-button>
    </router-link> -->
    


    <div class="login-buttons" v-show="$store.state.currentRole == ''">
      <router-link
        v-bind:to="{ name: 'login', params: { role: 'ROLE_RENTER' } }"
      >
        <action-button class="nav-spacing"> Login Renter </action-button>
      </router-link>
      <router-link
        v-bind:to="{ name: 'login', params: { role: 'ROLE_LANDLORD' } }"
      >
        <action-button class="nav-spacing"> Login Landlord</action-button>
      </router-link>
      <router-link
        v-bind:to="{ name: 'login', params: { role: 'ROLE_WORKER' } }"
      >
        <action-button class="nav-spacing"> Login Worker </action-button>
      </router-link>
    </div>

  </div>
</template>

<script>
import ActionButton from "./sub-components/ActionButton.vue";
export default {
  components: { ActionButton }
};
</script>

<style>
.navbar {
  width: 100%;
  display: flex;
}
.left {
  margin-right: auto;
}

.nav-spacing {
  margin-left: 5px;
}
</style>
