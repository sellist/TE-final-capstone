<template>
  <div v-show="showForm == true">
    <form v-on:submit.prevent class="new-property-form">
      <button class="btn btn-info" @click="onPickFile">
        Upload profile picture
      </button>
      <input
        type="file"
        style="display: none"
        ref="fileInput"
        accept="image/*"
        @change="onFilePicked"
      />

      <div class="address">
        <h4>Location</h4>
        <div>
          <label class="right-margin" name="Neighborhood" for="hood-select"
            >Neighborhood
          </label>
          <select id="hood-select" v-model="currentProperty.neighborhood">
            <option
              v-for="neighborhood in neighborhoods"
              v-bind:key="neighborhood"
            >
              {{ neighborhood }}
            </option>
          </select>
        </div>

        <div>
          <label class="right-margin" for="">House Number: </label>
          <input
            class="right-margin"
            type="text"
            v-model="currentProperty.address.houseNumber"
          />
          <label class="right-margin" for="">Street: </label>
          <input type="text" v-model="currentProperty.address.street" />
        </div>

        <label class="right-margin" for="">City: </label>
        <input
          class="right-margin"
          type="text"
          v-model="currentProperty.address.city"
        />
        <label class="right-margin" for="">State: </label>
        <input
          class="right-margin"
          type="text"
          v-model="currentProperty.address.state"
        />
        <label class="right-margin" for="">Zip: </label>
        <input type="text" v-model="currentProperty.address.zip" />
      </div>

      <div>
        <h4>Description</h4>
        <textarea
          name=""
          id=""
          cols="80"
          rows="7"
          v-model="currentProperty.description"
        ></textarea>
      </div>

      <div>
        <h4>Information</h4>
        <div class="max-rent">
          <label for="">Monthly Rent: </label>
          <span>
            $
            <input
              id="monthly-rent"
              min="100"
              max="10000"
              v-model="currentProperty.monthlyRent"
              type="number"
              step="10"
            />
          </span>
        </div>

        <div>
          <label class="right-margin" for="type">Type of home </label>
          <select name="type" id="type" v-model="currentProperty.type">
            <option v-for="homeType in homeTypes" v-bind:key="homeType">
              {{ homeType }}
            </option>
          </select>
        </div>

        <div>
          <label class="right-margin" for="bedrooms">Bedrooms </label>
          <input
            type="range"
            id="bedrooms"
            min="1"
            max="10"
            v-model="currentProperty.attributes.bedrooms"
          />
          {{ currentProperty.attributes.bedrooms }}
        </div>

        <div>
          <label class="right-margin" for="bathrooms">Bathrooms </label>
          <input
            type="range"
            id="bathrooms"
            min="1"
            max="8"
            v-model="currentProperty.attributes.bathrooms"
          />
          {{ currentProperty.attributes.bathrooms }}
        </div>

        <div>
          <label class="right-margin" for="min-area">Indoor Space: </label>
          <input
            type="number"
            id="min-area"
            min="100"
            max="10000"
            v-model="currentProperty.attributes.area"
          />
          {{ currentProperty.area + " sq ft" }}
        </div>
        <div>
          <label class="right-margin" for="">Heating type: </label>
          <input type="text" v-model="currentProperty.attributes.heatingType" />
        </div>

        <div>
          <label class="right-margin" for="">Air Conditioning? </label>
          <input type="checkbox" v-model="currentProperty.attributes.hasAC" />
        </div>

        <div>
          <label class="right-margin" for="">Pets Allowed? </label>
          <input type="checkbox" v-model="currentProperty.attributes.petsAllowed" />
        </div>

        <div>
          <label class="right-margin" for="">Smoking Allowed? </label>
          <input type="checkbox" v-model="currentProperty.attributes.smoking" />
        </div>
      </div>

      <div id="parking">
        <h4>Parking</h4>
        <div>
          <div>
            <label class="right-margin" for="">None </label>
            <input
              type="checkbox"
              class="right-margin"
              v-model="noParking"
              @change="toggleParking"
            />
            <label class="left-margin right-margin" for="">On-Street </label>
            <input type="checkbox" v-model="parking.onStreet"  />
          </div>
          <div></div>
          <div>
            <label class="right-margin" for="driveway">Driveway spaces: </label>
            <input
              type="range"
              min="0"
              max="10"
              v-model="parking.drivewaySpaces"
            />
            {{ parking.drivewaySpaces }}
          </div>
          <div>
            <label class="right-margin" for="garage">Garage spaces: </label>
            <input
              type="range"
              min="0"
              max="10"
              v-model="parking.garageSpaces"
            />
            {{ parking.garageSpaces }}
          </div>
        </div>
      </div>
      <div class="bottom-btns">
        <div class="submit-button nav-spacing" @click="submitForm">Submit</div>
        <div class="submit-button nav-spacing" @click="resetForm">Reset</div>
        <div class="submit-button nav-spacing" @click="showForm = false">
          Close
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import PropertyService from "../services/PropertyService.js";
export default {
  props: ["property", "updateProperty" ],
  data() {
    return {
      currentProperty: {
        propertyId: '',
        landlordId: "",
        renterId: "",
        neighborhood: "",
        description: "",
        type: "apartment",
        address: {
          houseNumber: "",
          street: "",
          city: "Pittsburgh",
          state: "PA",
          zip: "15",
        },
        attributes: {
          bedrooms: 1,
          bathrooms: 1,
          area: 300,
          heatingType: "gas",
          hasAC: "",
          parking: "",
          petsAllowed: "",
          smoking: "",
        },
        monthlyRent: 500,
        photoPaths: [],
      },
      parking: {
        drivewaySpaces: 0,
        garageSpaces: 0,
        onStreet: false,
      },
      filterSet: new Set(),
      addingProperty: false,
      currentPropertyId: "",
      filename: "",
      image: "",
      imageUrl: "",
      showForm: true,
    };
  },
  created() {
      this.resetForm();
  },

  computed: {
    neighborhoods() {
      return this.$store.state.neighborhoodList;
    },
    homeTypes() {
      return this.$store.state.homeTypeList;
    },
    noParking() {
      if (
        this.parking.drivewaySpaces == 0 &&
        this.parking.garageSpaces == 0 &&
        !this.parking.onStreet
      ) {
        return true;
      }
      return false;
    },
  },
  methods: {
   
    calculateParking() {
        this.parking.drivewaySpaces = '';
        if (this.currentProperty.attributes.parking.includes('driveway')) {
            this.parking.drivewaySpaces = this.currentProperty.attributes.parking.substring(0, 1);
        } else {
            this.parking.drivewaySpaces = 0;
        }
        if (this.currentProperty.attributes.parking.includes('garage')) {
            this.parking.garageSpaces = 
            this.currentProperty.attributes.parking.substring(this.currentProperty.attributes.parking.indexOf('garage') - 2 , this.currentProperty.attributes.parking.indexOf('garage') - 1);
        } else {
            this.parking.garageSpaces = 0;
        }
        if (this.currentProperty.attributes.parking.includes('on-street')) {
            this.parking.onStreet = true; 
      }
    },

    resetForm() {
      this.currentProperty.neighborhood = this.property.neighborhood;
        this.currentProperty.propertyId = this.property.propertyId;
        this.currentProperty.landlordId = this.property.landlordId;
        this.currentProperty.renterId = this.property.renterId;
      this.currentProperty.description = this.property.description;
      this.currentProperty.type = this.property.type;
      this.currentProperty.address.houseNumber = this.property.address.houseNumber;
      this.currentProperty.address.street = this.property.address.street;
      this.currentProperty.address.city = this.property.address.city;
      this.currentProperty.address.state = this.property.address.state;
      this.currentProperty.address.zip = this.property.address.zip;
      this.currentProperty.attributes.bedrooms = this.property.attributes.bedrooms;
      this.currentProperty.attributes.bathrooms = this.property.attributes.bathrooms;
      this.currentProperty.attributes.area = this.property.attributes.area;
      this.currentProperty.attributes.heatingType = this.property.attributes.heatingType;
      this.currentProperty.attributes.hasAC = this.property.attributes.hasAC;
      this.currentProperty.attributes.parking = this.property.attributes.parking;
      this.currentProperty.attributes.petsAllowed = this.property.attributes.petsAllowed;
      this.currentProperty.attributes.smoking = this.property.attributes.smoking;
      this.currentProperty.monthlyRent = this.property.monthlyRent;
      this.currentProperty.photoPaths = this.property.photoPaths;
      this.calculateParking();
    },

    toggleParking() {
      this.parking.drivewaySpaces = 0;
      this.parking.garageSpaces = 0;
      this.parking.onStreet = false;
    },

    setParking() {
      this.currentProperty.attributes.parking = "";
      this.addSpaces(this.parking.drivewaySpaces, " driveway");
      this.addSpaces(this.parking.garageSpaces, " garage");
      if (this.parking.onStreet) {
        this.addComma();
        this.currentProperty.attributes.parking += "on-street ";
      }
      if (this.currentProperty.attributes.parking == "") {
        this.currentProperty.attributes.parking == "none";
      }
    },

    addSpaces(spaces, spaceType) {
      if (spaces > 0) {
        this.addComma();
        this.currentProperty.attributes.parking += spaces + spaceType + " space";
        spaces > 1
          ? (this.currentProperty.attributes.parking += "s")
          : (this.currentProperty.attributes.parking += "");
      }
    },

    addComma() {
      if (this.currentProperty.attributes.parking != "") {
        this.currentProperty.attributes.parking += ", ";
      }
    },

    closeForm() {
      this.$props.updateProperty = false;
    },

    submitForm() {
      this.setParking();
      PropertyService.updateProperty(this.currentProperty, this.currentProperty.propertyId).then((response) => {
        this.currentPropertyId = response.data;
        // this.$router.push({
        //   name: "propertyDetails",
        //   params: { propertyId: this.currentPropertyId },
        // });
      });
    },

    onPickFile() {
      this.$refs.fileInput.click();
    },

    onFilePicked(event) {
      const files = event.target.files;
      this.filename = files[0].name;
      const fileReader = new FileReader();
      fileReader.addEventListener("load", () => {
        this.imageUrl = fileReader.result;
      });
      fileReader.readAsDataURL(files[0]);
      this.image = files[0];
    },
  },
};
</script>

<style>
.address {
  margin-bottom: 20px;
}

.new-property-form {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

.right-margin {
  margin-right: 5px;
}

.left-margin {
  margin-left: 15px;
}

.nav-spacing {
  margin-left: 5px;
}

.bottom-btns {
  margin-top: 10px;
  margin-bottom: 30px;
}
</style>