<template>
  <!-- This is the component for use on the home page, should talk to the map and highlight specific list items at the same time. -->
  <div>
    
    <div id="hood-list" class="centered-text">
      <div>
        <h3>Find an apartment</h3>
        </div>
        
      <router-link :to=" { name: 'neighborhood', params: { neighborhood: 'Bloomfield' } } ">
        <div>Bloomfield</div>
      </router-link>
      <router-link :to=" { name: 'neighborhood', params: { neighborhood: 'East Liberty' } } ">
        <div>East Liberty</div>
      </router-link>
      <router-link :to=" { name: 'neighborhood', params: { neighborhood: 'Highland Park' } } ">
        <div>Highland Park</div>
      </router-link>
      <router-link :to=" { name: 'neighborhood', params: { neighborhood: 'Lawrenceville' } } ">
        <div>Lawrenceville</div>
      </router-link>
      <router-link :to=" { name: 'neighborhood', params: { neighborhood: 'Lawrenceville' } } ">
        <div>Morningside</div>
      </router-link>

      <router-link :to=" { name: 'neighborhood', params: { neighborhood: 'Oakland' } } ">
        <div>Oakland</div>
      </router-link>
      <router-link :to=" { name: 'neighborhood', params: { neighborhood: 'Point Breeze' } } ">
        <div>Point Breeze</div>
      </router-link>
      <router-link :to=" { name: 'neighborhood', params: { neighborhood: 'Regent Square' } } ">
        <div>Regent Square</div>
      </router-link>
      <router-link :to=" { name: 'neighborhood', params: { neighborhood: 'Shadyside' } } ">
        <div>Shadyside</div>
      </router-link>
      <router-link :to=" { name: 'neighborhood', params: { neighborhood: 'Squirrel Hill North' } } ">
        <div>Squirrel Hill North</div>
      </router-link>
      <router-link :to=" { name: 'neighborhood', params: { neighborhood: 'Squirrel Hill South' } } ">
        <div>Squirrel Hill South</div>
      </router-link>

      <router-link class="submit-button all-properties" :to=" { name: 'propertyList' } ">
        See all available properties
      </router-link>
    
      
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>




.centered-text {
  text-align: center;
}

.all-properties {
  margin-top: 20px;
}

#hood-list h4 {
  margin-bottom: 0px;
  margin-top: 5px;
}

a {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

</style>
