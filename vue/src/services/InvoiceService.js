import axios from "axios";

export default {

    getAllInvoicesByLandlordId(userId){
        return axios.get(`invoices/landlord/${userId}`)
    },

    getAllInvoicesByRenterId(renterId){
        return axios.get(`invoices/renter/${renterId}`)
    },

    getInvoiceById(id){
        return axios.get(`/invoices/${id}`)
    },

    createInvoice(newInvoice){
        return axios.post('/invoices/create', newInvoice)
    },

    updateInvoice(updatedInvoice) {
        return axios.put(`/invoices/update/${updatedInvoice.id}`, updatedInvoice)
    }

}