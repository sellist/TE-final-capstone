import axios from "axios";

// export default {
//     data () {
//       return {
//         address: {
//             houseNumber: "",
//             street: "",
//             city: "",
//             state: "",
//             zip: "",
//           },
//           formattedAddress: "",
//       }
//     },
//     methods: {
//       formatAddressAsStringService() {
//         GoogleAddressService.validateAddress(this.address).then(response => {
//           this.formattedAddress = response.data.result.address.formattedAddress;
//         });
//       }
//     }
//   }

const googleClient = axios.create({
    baseURL: "https://addressvalidation.googleapis.com/v1:validateAddress?key=AIzaSyAn3SicAfZYFBciU1nzYaO_wf_wudGXVlA",
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    }
});

export default {
    validateAddress(input) {
        let formatQuery = {
            "address": {
                regionCode: "US",
                administrativeArea: `${input.state.trim()}`,
                addressLines: [`${input.houseNumber.trim()} ${input.street.trim()}`],
                postalCode: `${input.zip.trim()}`,
            }
        };
        return googleClient.post(googleClient.baseURL, formatQuery);
    }
}