// Landlord view of own properties, 
// add button to edit property alongside list of properties

<template>
<div>
    <new-property />
    <properties />


</div>
</template>

<script>
import Properties from "../components/Properties.vue";
import NewProperty from "../components/NewProperty.vue";

export default {
    name: "LandlordProperties",
    components: { Properties, NewProperty }

}
</script>

<style>

</style>