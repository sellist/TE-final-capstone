<template>
<!-- Feel free to change this, I currently have the 'role' property take in the desired role to login as from an outside source -->
<!-- i.e. from login button on home router-links to here with the param "renter" attached to let that instance of login know that a renter will log in -->
<div id="login" class="text-center outer-container">
    <form class="form-signin" @submit.prevent="login">
        <h1 class="h3 mb-3 font-weight-normal">{{ this.role.substring(5, 6) + this.role.substring(6).toLowerCase() }} Portal</h1>
        <div
            class="alert alert-danger"
            role="alert"
            v-if="invalidCredentials"
        >
            Invalid username and password!
        </div>
        <div
            class="alert alert-success"
            role="alert"
            v-if="this.$route.query.registration"
        >
            Thank you for registering, please sign in.
        </div>
        <label for="username" class="sr-only username">Username</label>
        <input
            type="text"
            id="username"
            class="form-control"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus
        />
        <label for="password" class="sr-only password">Password</label>
        <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="user.password"
            required
        />
        <router-link :to="{ name: 'register' }"
            >Need an account?</router-link
        >
        <button type="submit" class="submit-button">Sign in</button>
    </form>
</div>
</template>

<script>
import authService from "../services/AuthService";

export default {
    name: "login",
    data() {
        return {
            user: {
                username: "",
                password: "",
                role: "",
            },
            storedRole: '',
            invalidCredentials: false,
        };
    },
    props: ["role"],
    created() {
        // console.log(this.role);
    },
    methods: {
        login() {
            authService
                .login(this.user)
                .then((response) => {
                    if (response.status == 200) {
                        this.$store.commit(
                            "SET_AUTH_TOKEN",
                            response.data.token
                        );
                        this.$store.commit("SET_USER", response.data.user);
                        // console.log(this.$store.state.user.authorities[0]);
                        this.checkRole();
                        
                        // this.$router.push("/");
                    }
                })
                .catch((error) => {
                    const response = error.response;

                    if (response.status === 401) {
                        this.invalidCredentials = true;
                    }
                });
        },
        checkRole() {
            authService.getRoleById()
            .then(response => { 
                this.storedRole = response.data;
                // console.log(this.storedRole);
                if (this.storedRole == this.role) {
                    this.$store.commit("UPDATE_CORRECT_ROLE", true);
                    this.$store.commit("UPDATE_CURRENT_ROLE", this.role);
                    if (this.role == 'ROLE_LANDLORD') {
                        this.$router.push( { name: 'LandlordPortal' } ); 
                    }
                    if (this.role == 'ROLE_RENTER') {
                        this.$router.push( { name: 'RenterPortal' } ); 
                    }
                    if (this.role == 'ROLE_WORKER') {
                        this.$router.push( { name: 'MaintenancePortal' } ); 
                    }


                } else {
                    this.$store.commit("UPDATE_CORRECT_ROLE", false);
                    this.$store.commit("UPDATE_CURRENT_ROLE", this.role);
                    this.$router.push("/");
                }
                
                // this.$router.push("/logout");
            });
            
            
        }
    },

};
</script>

<style scoped>
.outer-container {
    display: grid;
    place-content: center;
}

.username {
    display: flex;
}

.password {
    display: flex;
}

.form-signin {
    max-width: 40vh;
    display: flex;
    flex-direction: column;
}

.form-control {
    min-width: 160px;
    min-height: 60px;
    border-color: white;
    appearance: none;
    backface-visibility: hidden;
    background-color: white;
    border-radius: 999px;
    border: 2px solid black;
    box-shadow: rgba(0, 0, 0, 0.15) 0 4px 9px;
    box-sizing: border-box;
    color: black;
    cursor: pointer;
    display: inline-block;
    font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    font-size: 2em;
    font-weight: 600;
    line-height: 1.5;
    outline: none;
    overflow: hidden;
    padding: 25px 30px;
    position: relative;
    text-align: center;
    transform: translate3d(0, 0, 0);
    transition: all 0.3s;
    touch-action: manipulation;
    vertical-align: top;
}
.submit-button {
    min-width: 160px;
    min-height: 60px;
    border-color: white;
    appearance: none;
    backface-visibility: hidden;
    background-color: #27ae60;
    border-radius: 999px;
    border-style: none;
    box-shadow: rgba(39, 174, 96, 0.15) 0 4px 9px;
    box-sizing: border-box;
    color: #fff;
    cursor: pointer;
    display: inline-block;
    font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    font-size: 2em;
    font-weight: 600;
    line-height: 1.5;
    outline: none;
    overflow: hidden;
    padding: 25px 30px;
    position: relative;
    text-align: center;
    transform: translate3d(0, 0, 0);
    transition: all 0.3s;
    touch-action: manipulation;
    vertical-align: top;
}

.submit-button:hover {
    background-color: #1e8449;
    opacity: 1;
    transform: translateY(0);
    transition-duration: 0.35s;
}

.submit-button:active {
    transform: translateY(2px);
    transition-duration: 0.35s;
}

.submit-button:hover {
    box-shadow: rgba(39, 174, 96, 0.2) 0 6px 12px;
}
</style>
