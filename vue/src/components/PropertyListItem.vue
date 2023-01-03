<template>
  <!-- <router-link :to="{ name: 'propertyDetails', params: { propertyId: propertyId } }"> -->
  <div>
    <div
      @click.self="goToListing"
      class="listing"
      :class="{
        inactive:
          $store.state.isInLandlordPortal == true && property.renterId == '',
      }"
    >
      <td v-if="hasImage">
        <img
          class="apartment-img"
          :src="require(`@/assets/${property.photoPaths[0]}`)"
          alt="property image"
        />
      </td>
      <td v-else>
        <img class="apartment-img" src="../assets/no-image.jpg" alt="" />
      </td>

      <div class="info" >
        <div class="prop-specs" @click.self="goToListing">
          <td class="wide-column" @click.self="goToListing">{{ property.neighborhood }}</td>
          <td class="type-column" @click.self="goToListing">{{ property.type }}</td>
          <td class="address-column" @click.self="goToListing">
            {{
              property.address.houseNumber +
              " " +
              property.address.street +
              ", " +
              property.address.city +
              " " +
              property.address.zip
            }}
          </td>
          <td class="narrow-column" @click.self="goToListing">{{ property.attributes.bedrooms }}</td>
          <td @click.self="goToListing">{{ "$" + property.monthlyRent }}</td>
        </div>
        <div class="lord-tools" v-show="$store.state.isInLandlordPortal == true" @click.self="goToListing">
          <div id="status" @click.self="goToListing">
            {{ currentRenter }}

          </div>
          <div class="btns" v-show="hasRequest == true">
            <h4 id="maint">{{ maintenanceRequests }}</h4>
            
          </div>
        </div>
      </div>

      
      <td class="btns edit-delete" v-show="$store.state.isInLandlordPortal == true">
        <h4 @click="updateProperty = !updateProperty">Edit</h4>
        <h4>Delete</h4>
      </td>
    </div>
    <div>
      <update-property
        v-show="updateProperty == true"
        :property="property"
        :updateProperty="updateProperty"
      />
    </div>
  </div>

  <!-- </router-link> -->
</template>


<script>
import UpdateProperty from "../components/UpdateProperty.vue";
import MaintenanceService from '../services/MaintenanceService';
export default {
  components: { UpdateProperty },
  props: ["property", "renter"],
  data() {
    return {
      requests: [],
      updateProperty: false,
      hasRequest: false
    };
  },
  created() {
    MaintenanceService.getRequestsByPropertyId(this.property.propertyId).then((response) => {
        this.requests = response.data;
        this.setHasRequest();
        this.isLoading = false;
      });
  },
  computed: {
    maintenanceRequests() {
      if (this.requests.length > 0) {
        return "Maintenance Request"
      }
      return '';
    },

    hasImage() {
      return this.property.photoPaths.length > 0;
    },

    propertyId() {
      return this.property.propertyId;
    },

    currentProperty() {
      return this.property;
    },

    currentRenter() {
      if (this.renter != undefined) {
        return "Tenant: " + this.renter.username;
        // return this.renter.username;
      }
      return "Vacant";
    },
  },
  methods: {
    setHasRequest() {
      if (this.requests.length > 0) {
        this.hasRequest = true;
      }
    },

    goToListing() {
      this.$router.push({
        name: "propertyDetails",
        params: { propertyId: this.propertyId },
      });
    },

  },
};
</script>


<style>
.listing {
  display: flex;
  align-items: center;
  border-color: black;
  border-style: solid;
  border-width: 1px;
  border-radius: 5px;
  margin-bottom: 8px;
  padding: 5px;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  width: 800px;
  /* background-color: #ffeb54; */
}

.listing:hover {
  cursor: pointer;
  background-color: rgb(214, 214, 214);
  
}
.wide-column {
  width: 140px;
}

.type-column {
  width: 100px;
}

.address-column {
  width: 210px;
}

.narrow-column {
  width: 20px;
}

.info {
  display: flex;
  flex-direction: column;
  width: 550px;
}

.prop-specs {
  display: flex;
}

.lord-tools {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.inactive {
  opacity: 70%;
  /* background-color: #ffeb54; */
}

.apartment-img {
  width: 100px;
}

.btns h4 {
  margin-top: 5px;
  margin-bottom: 5px;
  padding-left: 10px;
  padding-right: 10px;
  padding-bottom: 3px;
  background-color: #046a38;
  border-radius: 20px;
  text-align: center;
  color: white;
  
}

.btns {
  justify-self: flex-end;
  
}

.edit-delete {
  width: 100px;
}

#status {
  color: #9447ff;
}

#maint {
margin-left: 20px;
background-color: #9447ff;
border-radius: 20px;
padding-top: 0px;
padding-bottom: 3px;
}
</style>