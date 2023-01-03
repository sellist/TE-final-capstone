import axios from 'axios';

export default {

    getAllAvailableProperties() {
        return axios.get('/properties')
    },

    getPropertiesByLandlordId() {
        return axios.get('/landlord/properties')
    },

    getPropertyByRenterId() {
        return axios.get('/renter/properties')
    },

    getPropertyByPropertyId(id) {
        return axios.get(`/properties/${id}`)
    },

    getRentersByLandlordId() {
        return axios.get('/landlord/renters')
    },

    createProperty(newProperty) {
        return axios.post('/properties', newProperty)
    },

    updateProperty(updatedProperty, id) {
        return axios.put(`/properties/${id}/update`, updatedProperty)
    },

    deleteProperty(id) {
        axios.delete(`properties/${id}`)
    }

}