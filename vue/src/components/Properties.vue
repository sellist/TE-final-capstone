<template>
  <div>
    <div v-if="isLoading"></div>
    <div v-else>
     
      
      <div id="#property-table">
        <div class="table-head">
          <div class="spacer"></div>
          <td class="hood-column">Neighborhood</td>
          <td class="home-type-column">Type</td>
          <td class="add-column">Address</td>
          <td class="narrow-column">Bd</td>
          <td>Rent</td>
        </div>
        <div v-if="filteredProductsEmpty">
          <h4 class="centered-text no-listings">
            No rental units to display. Adjust your filters to see more
          </h4>
        </div>

        <div
          v-else
          class="preview"
          v-for="property in filteredProperties"
          v-bind:key="property.propertyId"
        >
        {{ setRenter(property) }}
          <property-list-item v-bind:property="property" v-bind:renter= "renter" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import propertyService from "../services/PropertyService.js";
import PropertyListItem from "../components/PropertyListItem.vue";

export default {
  components: { PropertyListItem },
  props: ["neighborhood"],
  data() {
    return {
      properties: [],
      renters: [],
      isLoading: true,
      filterByHood: false,
      renter: ''
    };
  },

  computed: {
    filteredProperties() {
      if (!this.filterByHood) {
        return this.$store.state.filteredProperties;
      }

      this.filterByNeighborHood();
      return this.properties;
    },

    filteredProductsEmpty() {
      return this.filteredProperties.length == 0;
    },

  },

  methods: {
    filterByNeighborHood() {
      this.properties = this.$store.state.filteredProperties.filter(
        (property) =>
          property.neighborhood.toLowerCase() == this.neighborhood.toLowerCase()
      );
      this.filterByHood = false;
    },

    setRenter(property) {
    if (property.renterId == 0) {
        this.renter = '';
      }
       this.renter = this.renters.filter(renter => renter.id == property.renterId)[0];
    },

  },

  created() {
    if (this.$store.state.isInLandlordPortal) {
      propertyService.getPropertiesByLandlordId().then((response) => {
        this.properties = response.data;
        this.$store.commit("UPDATE_FILTERED_PROPERTIES", this.properties);
        this.isLoading = false;
      });
      propertyService.getRentersByLandlordId().then((response) => {
        this.renters = response.data;
        this.isLoading = false;
      });

    } else {
      propertyService.getAllAvailableProperties().then((response) => {
        this.properties = response.data;
        this.$store.commit("UPDATE_FILTERED_PROPERTIES", this.properties);
        if (this.neighborhood != null) {
          this.filterByHood = true;
        }
        this.isLoading = false;
      });


    }
  },
};
</script>

<style>
.table-head {
  display: flex;
  font-weight: bold;
  margin-top: 20px;
  margin-bottom: 20px;
}

#create-button {
  margin-top: 40px;
  display: flex;
  justify-content: center;
  margin-left: auto;
  margin-right: auto;
  
  width: 300px;
}


.spacer {
  width: 120px;
}

.preview {
}
td {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;

  margin-right: 20px;
  width: 100px;
}

.hood-column {
  width: 118px;
}

.home-type-column {
  width: 80px;
}

.add-column {
  width: 160px;
}

.narrow-column {
  width: 20px;
}

.no-listings {
  margin-top: 100px;
}
</style>