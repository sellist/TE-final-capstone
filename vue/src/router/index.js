import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import StyleGuide from '../views/StyleGuide.vue'
import PropertyList from '../views/PropertyList.vue'
import PropertyDetails from '../views/PropertyDetails.vue'
import CreateRequest from '../views/CreateRequest.vue'
import MaintenancePortal from '../views/MaintenancePortal'
import LandlordInvoices from '../views/LandlordInvoices.vue'
import RenterInvoices from '../views/RenterInvoices.vue'
import LandlordPortal from '../views/LandlordPortal.vue'
import LandlordProperties from '../views/LandlordProperties.vue'
import RenterPortal from '../views/RenterPortal.vue'
import LandlordRequests from '../views/LandlordRequests.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false //This was initially set to false, which didn't allow unauthorized people to view the home page, so I changed it to true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      props: true,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/guide",
      name: "guide",
      component: StyleGuide,
      meta : {
        requiresAuth: false
      }
    },
    {
  
      path: "/properties",
      name: "propertyList",
      component: PropertyList,
      meta : {
        requiresAuth : false
      }
    },
    {
      path: "/properties/:neighborhood",
      name: "neighborhood",
      component: PropertyList,
      meta : {
        requiresAuth : false
      }
    },

    {
      path: "/properties/:propertyId",
      name: "propertyDetails",
      component: PropertyDetails,
      meta : {
        requiresAuth : false
      }
    },
    {
      path: "/maintenance/:propertyId/create",
      name: 'CreateRequest',
      component: CreateRequest,
      meta: {
        requiresAuth : false
      }
    },


    
    {
      path: "/maintenance/:workerId/",
      name: 'MaintenancePortal',
      component: MaintenancePortal,
      meta: {
        requiresAuth : false
      }
    },
    
    {
      path: '/invoices/landlord/:userId',
      name: 'LandlordInvoices',
      component: LandlordInvoices,
      meta: {
        requiresAuth : false
      }
    },
    
    {
      path: '/invoices/renter/:userId',
      name: 'RenterInvoices',
      component: RenterInvoices,
      meta: {
        requiresAuth : false
      }
    },

    {
      path: "/landlord",
      name: 'LandlordPortal',
      component: LandlordPortal,
      meta: {
        requiresAuth : false
      }
    },

    {
      path: "/landlord/properties",
      name: 'LandlordProperties',
      component: LandlordProperties,
      meta: {
        requiresAuth : false
      }
    },
    {
      path: "/renter",
      name: 'RenterPortal',
      component: RenterPortal,
      meta: {
        requiresAuth : false
      }
    },
    {
      path: "/landlord/requests",
      name: 'LandlordRequests',
      component: LandlordRequests,
      meta: {
        requestAuth : false
      }
    }




  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
