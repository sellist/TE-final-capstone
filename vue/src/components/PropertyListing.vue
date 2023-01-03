<template>
  <div>
    <div v-if="isLoading"></div>
    <div v-else class="this-listing" >
      
      <div v-if="hasImage" id="main-photo">
        <img 
          :src="require(`@/assets/${property.photoPaths[0]}`)"
          alt="property image"
        />
      </div>
      <div v-else id="main-photo">
        <img src="../assets/no-image.jpg" alt="">
      </div>
    
      <h2 id="n-hood" class="centered-text attribute">Neighborhood: {{ property.neighborhood }}</h2>
      <h2 id="address" class="centered-text attribute">
        Address:
        {{
          property.address.houseNumber +
          " " +
          property.address.street +
          ", " +
          property.address.city +
          " " +
          property.address.zip
        }}
      </h2>

      <h3 id="type" class="attribute">Type: {{ property.type }}</h3>
      <h3 id="rent" class="attribute">Monthly Rent: ${{ property.monthlyRent }}</h3>
      <h3 id="description" class="attribute">Description: {{ property.description }}</h3>
      <h4 id="beds" class="attribute">Bedrooms: {{ property.attributes.bedrooms }}</h4>
      <h4 id="baths" class="attribute">Bathrooms: {{ property.attributes.bathrooms }}</h4>
      <h4 id="area" class="attribute">Area: {{ property.attributes.area }} sq ft</h4>
      <h4 id="ac" class="attribute">Air Con: {{ property.attributes.hasAc ? "yes" : "no" }}</h4>
      <h4 id="heating" class="attribute">Heating: {{ property.attributes.heatingType }} sq ft</h4>
      <h4 id="smoking" class="attribute">
        Smoking: {{ property.attributes.smoking ? "yes" : "no" }}
      </h4>
      <h4 id="pets" class="attribute">
        Pets Allowed: {{ property.attributes.isPetsAllowed ? "yes" : "no" }}
      </h4>
      <h4 id="parking" class="attribute">Parking: {{ property.attributes.parking }}</h4>
    </div>
  </div>
</template>

<script>
import propertyService from "../services/PropertyService.js";
export default {
  props: ["propertyId"],
  data() {
    return {
      property: {},
      isLoading: true,
    };
  },
  computed: {
    hasImage() {
      return this.property.photoPaths.length > 0;
    }
  },

  created() {
    propertyService
      .getPropertyByPropertyId(this.propertyId)
      .then((response) => {
        this.property = response.data;
        this.isLoading = false;
      });
  },
};
</script>

<style>
.this-listing {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  display: grid;
  width: 800px;
  grid-template-areas: "photo photo"
                        "hood hood"
                        "address address"
                        "type rent"
                        "desc desc"
                        "beds ac"
                        "baths pets"
                        "area smoking"
                        "heating parking"

}

.this-listing {
margin-top: 30px;

}
#main-photo {
  
  grid-area: photo;
  margin-bottom: 10px;
  text-align: center;
}

img {
  width: 400px;
}

#n-hood {
  grid-area: hood;
}

#address {
  grid-area: address;
  margin-bottom: 20px;
}

#type {
  grid-area: type;
}

#rent {
  grid-area: rent;
}

#description {
  grid-area: desc;
  margin-bottom: 20px;
}

#beds {
  grid-area: beds;
}

#baths {
  grid-area: baths;
}

#area {
  grid-area: area;
}

#heating {
  grid-area: heating;
}

#ac {
  grid-area: ac;
}

#pets {
  grid-area: pets;
}

#smoking {
  grid-area: smoking;
}

#parking {
  grid-area: parking;
}

.attribute {
  margin-top: 5px;
  margin-bottom: 5px;
}




</style>