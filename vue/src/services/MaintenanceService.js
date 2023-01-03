import axios from 'axios';

export default {

    getRequestById(requestId) {
        return axios.get(`/maintenance/${requestId}`)
    },

    getRequestsByPropertyId(propertyId) {
        return axios.get(`/maintenance/property/${propertyId}`)
    },

    getRequestsByWorkerId(workerId) {
        return axios.get(`maintenance/worker/${workerId}`)
    },

    getRequestsByLandlordId(landlordId) {
        return axios.get(`maintenance/landlord/${landlordId}`)
    },

    createMaintenanceRequest(maintenanceRequest) {
        return axios.post(`/maintenance/`, maintenanceRequest)
    },

    updateMaintenanceRequest(requestId, maintenanceRequest) {
        return axios.put(`maintenance/${requestId}`, maintenanceRequest)
    },

    deleteMaintenanceRequest(requestId) {
        return axios.delete(`maintenance/${requestId}`)
    }

}

