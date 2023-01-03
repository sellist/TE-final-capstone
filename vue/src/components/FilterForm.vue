<template>
  <div>

    <div id="filter-shower">
      <h3 @click="showForm = true"
      v-show="showForm == false">
        Filter Rental Units
      </h3>
    </div>
    
    <form v-on:submit.prevent v-show="showForm == true" class="filter-form"  >
      <div class="filter-item" :class="{ darken: filterSet.has(0) } "  >
        <label for="hood-select" >Neighborhood </label>
        <select @change="filterSet.add(0)"
          name="Neighborhood"
          id="hood-select"
          v-model="filters.neighborhood"
        >
        <option value="All Neighborhoods">All Neighborhoods</option>
          <option
            v-for="neighborhood in neighborhoods"
            v-bind:key="neighborhood"
          >
            {{ neighborhood }}
          </option>
        </select>
      </div>
      <div class="filter-item max-rent" @change="filterSet.add(1)" :class="{ darken: filterSet.has(1) } ">
        <label for="max-rent">Maximum monthly rent </label>
        <input

          type="range"
          id="max-rent"
          min="400"
          max="10000"
          v-model="filters.maxRent"
        />
        {{ "$" + filters.maxRent }}
      </div>

      <div class="filter-item" @change="filterSet.add(2)" :class="{ darken: filterSet.has(2) } ">
        <label for="type">Type of home </label>
        <select
          name="type"
          id="type"
          v-model="filters.homeType"
        >
          <option
            v-for="homeType in homeTypes"
            v-bind:key="homeType"
          >
            {{ homeType }}
          </option>
        </select>
      </div>

      <div class="filter-item" @change="filterSet.add(3)" :class="{ darken: filterSet.has(3) } ">
        <label for="bedrooms">Bedrooms </label>
        <input
          type="range"
          id="bedrooms"
          min="1"
          max="10"
          v-model="filters.bedrooms"
        />
        {{ filters.bedrooms }}
        <button @click="resetBedrooms" >Remove Bedroom Filter</button>
      </div>

      <div class="filter-item" @change="filterSet.add(4)" :class="{ darken: filterSet.has(4) } ">
        <label for="bathrooms">Bathrooms </label>
        <input
          type="range"
          id="bathrooms"
          min="1"
          max="4"
          v-model="filters.bathrooms"
        />
        {{ filters.bathrooms }}
        <button @click="resetBathrooms" >Remove Bathroom Filter</button>
        
      </div>

      <div class="filter-item" @change="filterSet.add(5)" :class="{ darken: filterSet.has(5) } ">
        <label for="min-area">Minimum Indoor Space: </label>
        <input
          type="range"
          id="min-area"
          min="100"
          max="10000"
          v-model="filters.area"
        />
        {{ filters.area + " sq ft" }}
      </div>

      <div class="filter-item" @change="filterSet.add(6)" :class="{ darken: filterSet.has(6) } ">
          <label for="">Air Conditioning? </label>
          <input type="checkbox" v-model="filters.ac">
      </div>

      <div class="filter-item" @change="filterSet.add(7)" :class="{ darken: filterSet.has(7) } ">
          <label for="">Pets Allowed? </label>
          <input type="checkbox" v-model="filters.pets">
      </div>
      
      <div class="filter-item" @change="filterSet.add(8)" :class="{ darken: filterSet.has(8) } ">
          <label for="">Smoking Allowed? </label>
          <input type="checkbox" v-model="filters.smoking">
      </div>

    <div id="parking" @change="filterSet.add(9)" :class="{ darken: filterSet.has(9) } ">
        <h4>Parking</h4>
        <div class="filter-item" :class="{ darken: filterSet.has(9) } ">
            <label for="">Garage? </label>
            <input type="checkbox" v-model="filters.parking.garage" @change="updateSpaces"> 
        
            <label class="driveway" for="">Driveway? </label>
            <input type="checkbox" v-model="filters.parking.driveway" @change="updateSpaces">
        </div>

        <div class="filter-item spaces" :class="{ darken: filterSet.has(9) } ">
            <label for="spaces">Spaces </label>
            <input
            type="range"
            id="spaces"
            min="0"
            max="4"
            v-model="filters.parking.spaces"
            />
            {{ filters.parking.spaces }}
            
        </div>
    </div>
      <div class="submit-button" @click="submitForm">
          Submit
        </div>
        <div class="submit-button" @click="resetForm">
          Reset
        </div>
        <div class="submit-button" @click="showForm = false">
          Close
        </div>

     
    </form>
  </div>
</template>

<script>
import propertyService from "../services/PropertyService.js";
export default {
  data() {
    return {
      filters: {
        neighborhood: "All Neighborhoods",
        maxRent: 10000,
        homeType: "All types",
        bedrooms: '',
        bathrooms: '',
        area: 100,
        ac: '',
        pets: '',
        smoking: '',
        parking: {
            garage: '',
            driveway: '',
            spaces: ''
        }
      },
      filterSet: new Set(),
      properties: [], 
      filteredProperties: [],
      showForm: false,
      isLoading: false,
        isActive: [false, false, false, 
        false, false, false, false,
        false, false, false],

      
    };
  },
  computed: {
    neighborhoods() {
      return this.$store.state.neighborhoodList;
    },

    homeTypes() {
        return this.$store.state.homeTypeList;
    },

  },
  methods: {
    submitForm() {
        this.showForm = false;
        propertyService.getAllAvailableProperties().then((response) => {
        this.properties = response.data;
        this.filterProperties();
        
        this.$store.commit("UPDATE_FILTERED_PROPERTIES", this.filteredProperties);
        // this.isLoading = false;
    });
    },

    updateSpaces() {
        if (this.filters.parking.spaces == '') {
            this.filters.parking.spaces = 1;
        }
    
    },
    filterProperties() {
        this.filteredProperties = this.properties;
        if (this.filters.neighborhood != 'All Neighborhoods') {
            this.filteredProperties = this.filteredProperties.filter(property => property.neighborhood.toLowerCase() == this.filters.neighborhood.toLowerCase());
        }
        this.filteredProperties = this.filteredProperties.filter(property => property.monthlyRent <= this.filters.maxRent);
        
        if (this.filters.homeType != 'All types') {
            this.filteredProperties = this.filteredProperties.filter(property => property.type == this.filters.homeType);
        }

        if (this.filters.bedrooms != '') {
            this.filteredProperties = this.filteredProperties.filter(property => property.attributes.bedrooms == this.filters.bedrooms);
        }
        if (this.filters.bathrooms != '') {
            this.filteredProperties = this.filteredProperties.filter(property => property.attributes.bathrooms == this.filters.bathrooms);
        }
        this.filteredProperties = this.filteredProperties.filter(property => property.attributes.area >= this.filters.area);

        if (this.filters.ac) {
            this.filteredProperties = this.filteredProperties.filter(property => property.attributes.hasAC == true);
        }

        if (this.filters.pets) {
            this.filteredProperties = this.filteredProperties.filter(property => property.attributes.petsAllowed == true);
        }

        if (this.filters.smoking) {
            this.filteredProperties = this.filteredProperties.filter(property => property.attributes.smoking == true);
        }

        if (this.filters.parking.driveway && this.filters.parking.garage) {
            this.filteredProperties = this.filteredProperties.filter(property => property.attributes.parking.includes('driveway') || property.attributes.parking.includes('garage'));
        } else {
            if (this.filters.parking.garage) {
                this.filteredProperties = this.filteredProperties.filter(property => property.attributes.parking.includes('garage'));
            }

            if (this.filters.parking.driveway) {
                this.filteredProperties = this.filteredProperties.filter(property => property.attributes.parking.includes('driveway'));
            }
      }

        if (this.filters.parking.spaces != '') {
            this.filteredProperties = this.filteredProperties.filter(property => parseInt(property.attributes.parking.substring(0, 1)) >= this.filters.parking.spaces);
        }



    },
    resetBedrooms() {
        this.filters.bedrooms = ''
    },
    resetBathrooms() {
        this.filters.bathrooms = ''
    },
    resetForm() {
      this.filters.neighborhood = "All Neighborhoods";
        this.filters.maxRent = 10000;
        this.filters.homeType = "All types";
        this.filters.bedrooms = '';
        this.filters.bathrooms = '';
        this.filters.area = 100;
        this.filters.ac = '';
        this.filters.pets = '';
        this.filters.smoking = '';
        this.filters.parking.garage = '';
        this.filters.parking.driveway= '';
        this.filters.parking.spaces = '';
        this.filterSet.clear();
      
    },
    activate(index) {
        this.isActive[index] = true;
    },
    closeForm() {
      this.showForm = false;
    }
    
  },
  
};
</script>

<style>
.filter-item, h3, h4, option {
    font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

.filter-item {
  opacity: 50%;
}

#filter-shower {
    background-color: greenyellow;
    width: 180px;
    padding-left: 20px;
    border-radius: 10px;
     
}

#filter-shower:hover {
    cursor: pointer;
}

.filter-form {
    margin-top: 30px;
    /* opacity: 50%; */
}

.driveway {
    margin-left: 30px;
}

h4 {
    margin-bottom: 5px;
}

.spaces {
    margin-bottom: 20px;
}

.lighten {
    opacity: 50%;
}

.darken {
    opacity: 100%;
}
</style>