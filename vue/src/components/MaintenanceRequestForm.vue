// File containing form and stuff for creating a maint. request
<template>
    <form v-on:submit.prevent="submitForm" class="requestForm">
        <div class="status-message error" v-show="errorMsg !== ''">{{errorMsg}}</div>
        <div class="form-group">
            <label for="title">Title:</label>
            <input id="title" type="text" class="form-control" v-model="request.title" required />
        </div>
        <div class="form-group">
            <label for="category">Category:</label>
            <select id="category" class="form-control" v-model="request.category" required>
                <option value="Plumbing">Plumbing</option>
                <option value="Electric/Lighting">Electric/Lighting</option>
                <option value="HVAC">HVAC (Heating and Cooling)</option>
                <option value="Yard">Yard</option>
                <option value="Other">Other</option>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" class="form-control" v-model="request.description"></textarea>
        </div>
        <button class="submit-button" type="submit">Submit</button>
        <button class="submit-button" type="button" @click="cancelForm">Cancel</button>
    </form>
</template>

<script>
import maintenanceService from "../services/MaintenanceService";

export default {
    name: "request-form",
    props: ["propertyId"],
    data() {
        return {
            request: {
                propertyId: parseInt(this.propertyId),
                title: "",
                description: "",
                category: ""
            },
            errorMsg: ""
        };
    },
    methods: {
        submitForm() {
            maintenanceService
            .createMaintenanceRequest(this.request)
            .then(response => {
                if (response.status === 201) {
                    return this.$router.push(`/maintenance/`) // should direct them to a page showing their maintenance requests
                }
            })
            .catch(error => {
                this.handleErrorResponse(error, "adding");
            });
        },
        cancelForm() {
            this.$router.push(`/maintenance`);
        },
        handleErrorResponse(error, verb) {
            if (error.response) {
                this.errorMsg = 
                    "Error " + verb + " request. Response received was '" +
                    error.response.statusText +
                    "'.";
            } else if (error.request) {
                this.errorMsg =
                    "Error " + verb + " request. Server could not be reached.";
            } else {
                this.errorMsg = 
                    "Error " + verb + " request. Request could not be created.";
            }
        }
        }
}
</script>

<style>
.requestForm {
  padding: 10px;
  margin-bottom: 10px;
}
.form-group {
  margin-bottom: 10px;
  margin-top: 10px;
}
label {
  display: inline-block;
  margin-bottom: 0.5rem;
}
.form-control {
  display: block;
  width: 80%;
  height: 30px;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}
textarea.form-control {
  height: 75px;
  font-family: Arial, Helvetica, sans-serif;
}
select.form-control {
  width: 20%;
  display: inline-block;
  margin: 10px 20px 10px 10px;
}
.status-message {
  display: block;
  border-radius: 5px;
  font-weight: bold;
  font-size: 1rem;
  text-align: center;
  padding: 10px;
  margin-bottom: 10px;
}
.status-message.success {
  background-color: #90ee90;
}
.status-message.error {
  background-color: #f08080;
}
</style>