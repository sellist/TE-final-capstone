<template>
  <div>
    <div
      v-on:submit.prevent
      class="submit-button"
      id="create-button"
      @click="addingProperty = true"
      v-show="addingProperty == false"
    >
      Add New Property
    </div>
    <form
      v-on:submit.prevent
      class="new-property-form"
      v-show="addingProperty == true"
    >
      <!-- <div class="photo">
        <h4>Photo</h4>
        <label class="right-margin" for="">Add your apartment photo here: </label>

        <input type="file" accept="image/*">
      </div> -->

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
          <select id="hood-select" v-model="newProperty.neighborhood">
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
            v-model="newProperty.address.houseNumber"
          />
          <label class="right-margin" for="">Street: </label>
          <input type="text" v-model="newProperty.address.street" />
        </div>

        <label class="right-margin" for="">City: </label>
        <input
          class="right-margin"
          type="text"
          v-model="newProperty.address.city"
        />
        <label class="right-margin" for="">State: </label>
        <input
          class="right-margin"
          type="text"
          v-model="newProperty.address.state"
        />
        <label class="right-margin" for="">Zip: </label>
        <input type="text" v-model="newProperty.address.zip" />
      </div>

      <div>
        <h4>Description</h4>
        <textarea
          name=""
          id=""
          cols="80"
          rows="7"
          v-model="newProperty.description"
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
              v-model="newProperty.monthlyRent"
              type="number"
              step="10"
            />
          </span>
        </div>

        <div>
          <label for="type">Type of home </label>
          <select name="type" id="type" v-model="newProperty.type">
            <option v-for="homeType in homeTypes" v-bind:key="homeType">
              {{ homeType }}
            </option>
          </select>
        </div>

        <div>
          <label for="bedrooms">Bedrooms </label>
          <input
            type="range"
            id="bedrooms"
            min="1"
            max="10"
            v-model="newProperty.attributes.bedrooms"
          />
          {{ newProperty.attributes.bedrooms }}
        </div>

        <div>
          <label for="bathrooms">Bathrooms </label>
          <input
            type="range"
            id="bathrooms"
            min="1"
            max="8"
            v-model="newProperty.attributes.bathrooms"
          />
          {{ newProperty.attributes.bathrooms }}
        </div>

        <div>
          <label for="min-area">Indoor Space: </label>
          <input
            type="number"
            id="min-area"
            min="100"
            max="10000"
            v-model="newProperty.attributes.area"
          />
          {{ newProperty.area + " sq ft" }}
        </div>
        <div>
          <label for="">Heating type: </label>
          <input type="text" v-model="newProperty.attributes.heatingType" />
        </div>

        <div>
          <label for="">Air Conditioning? </label>
          <input type="checkbox" v-model="newProperty.attributes.hasAC" />
        </div>

        <div>
          <label for="">Pets Allowed? </label>
          <input type="checkbox" v-model="newProperty.attributes.petsAllowed" />
        </div>

        <div>
          <label for="">Smoking Allowed? </label>
          <input type="checkbox" v-model="newProperty.attributes.smoking" />
        </div>
      </div>

      <div id="parking">
        <h4>Parking</h4>
        <div>
          <div>
            <label for="">None </label>
            <input type="checkbox" class="right-margin" v-model="noParking" @change="toggleParking" />
            <label for="">On-Street </label>
            <input type="checkbox" v-model="parking.onStreet" />
          </div>
          <div></div>
          <div>
            <label for="driveway">Driveway spaces: </label>
            <input
              type="range"
              min="0"
              max="10"
              v-model="parking.drivewaySpaces"
            />
            {{ parking.drivewaySpaces }}
          </div>
          <div>
            <label for="garage">Garage spaces: </label>
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
        <div class="submit-button nav-spacing" @click="addingProperty = false">
          Close
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import PropertyService from "../services/PropertyService.js";
export default {
  data() {
    return {
      newProperty: {
        landlordId: "",
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
      newPropertyId: "",
      filename: "",
      image: "",
      imageUrl: "",
    };
  },
  computed: {
    neighborhoods() {
      return this.$store.state.neighborhoodList;
    },
    homeTypes() {
      return this.$store.state.homeTypeList;
    },
    noParking() {
      if (this.parking.drivewaySpaces == 0 && 
          this.parking.garageSpaces == 0 &&
          !this.parking.onStreet) {
            return true;
          }
          return false;
    },
  },
  methods: {
    resetForm() {
      this.newProperty.neighborhood = "";
      this.newProperty.description = "";
      this.newProperty.type = "";
      this.newProperty.address.houseNumber = "";
      this.newProperty.address.street = "";
      this.newProperty.address.city = "";
      this.newProperty.address.state = "";
      this.newProperty.address.zip = "";
      this.newProperty.attributes.bedrooms = 1;
      this.newProperty.attributes.bathrooms = 1;
      this.newProperty.attributes.area = "";
      this.newProperty.attributes.heatingType = "";
      this.newProperty.attributes.hasAC = "";
      this.newProperty.attributes.parking = "";
      this.newProperty.attributes.petsAllowed = "";
      this.newProperty.attributes.smoking = "";
      this.newProperty.monthlyRent = 500;
      this.newProperty.photoPaths = [];
      this.parking.drivewaySpaces = 0;
      this.parking.garageSpaces = 0;
      this.parking.onStreet = false;

      this.filterSet.clear();
    },

    toggleParking() {
      this.parking.drivewaySpaces = 0;
      this.parking.garageSpaces = 0;
      this.parking.onStreet = false;
    },

    setParking() {
      this.newProperty.attributes.parking = "";
      this.addSpaces(this.parking.drivewaySpaces, " driveway");
      this.addSpaces(this.parking.garageSpaces, " garage");
      if (this.parking.onStreet) {
        this.addComma();
        this.newProperty.attributes.parking += "on-street ";
      }
      if (this.newProperty.attributes.parking == "") {
        this.newProperty.attributes.parking == "none";
      }
    },

    addSpaces(spaces, spaceType) {
      if (spaces > 0) {
        this.addComma();
        this.newProperty.attributes.parking += spaces + spaceType + " space";
        spaces > 1
          ? (this.newProperty.attributes.parking += "s")
          : (this.newProperty.attributes.parking += "");
      }
    },

    addComma() {
      if (this.newProperty.attributes.parking != "") {
        this.newProperty.attributes.parking += ", ";
      }
    },

    closeForm() {
      this.addingProperty = false;
    },

    submitForm() {
      this.setParking();
      PropertyService.createProperty(this.newProperty).then((response) => {
        this.newPropertyId = response.data;
        this.$router.push({
          name: "propertyDetails",
          params: { propertyId: this.newPropertyId },
        });
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

.nav-spacing {
  margin-left: 5px;
}

.bottom-btns {
  margin-top: 10px;
  margin-bottom: 30px;
}
</style>