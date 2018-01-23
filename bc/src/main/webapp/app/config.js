var bc = angular.module("bc", ["ngRoute", 'bw.paging', "Alertify", "angucomplete-alt", "ngMessages", "checklist-model"]);

bc.controller ("NavController",function(){
    
});

bc.config(function ($routeProvider) {
    "use strict";
    $routeProvider
        .when("/", {
        	templateUrl: "app/planning/planning.html",
            controller: "PlanningController",
            controllerAs: "vm",
            resolve: {
            	notAutorized: checkLogged
            }
        }) 
        /*
         * Clientes 
         */
        .when("/clients", {
            templateUrl: "app/clients/clients.html",
            controller: "ListClientsController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        .when("/clients/create", {
            templateUrl: "app/clients/create/create_client.html",
            controller: "CreateClientController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        .when("/clients/modify/:idClient", {
            templateUrl: "app/clients/modify/modify_client.html",
            controller: "ModifyClientController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        .when("/bookings/create/:idClient", {
            templateUrl: "app/clients/create/bookings/create_bookingByClient.html",
            controller: "CreateBookingClientController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        /*
         * Reservas
         */
        .when("/bookings", {
            templateUrl: "app/bookings/bookings.html",
            controller: "ListBookingsController",
            controllerAs: "vm",
            resolve: {
            	notAutorized: checkAuthAdminOrManager
            }
        })
        .when("/bookings/create", {
            templateUrl: "app/bookings/create/create_booking.html",
            controller: "CreateBookingController",
            controllerAs: "vm",
            resolve: {
            	notAutorized: checkAuthAdminOrManager
            }
        })
        .when("/bookings/modify/:idBooking", {
            templateUrl: "app/bookings/modify/modify_booking.html",
            controller: "ModifyBookingController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdminOrManager
              }
        })
        /*
         * Login
         */
        .when("/login", {
            templateUrl: "app/login/login.html",
            controller: "LoginController",
            controllerAs: "vm"
        })
        /*
         * Register
         */
        .when("/register", {
            templateUrl: "app/login/register/registration.html",
            controller: "RegistrationController",
            controllerAs: "vm",
            resolve: {
                notAutorized: checkAuthAdmin
              }
        })
        /*
         * Planning
         */
        .when("/planning", {
            templateUrl: "app/planning/planning.html",
            controller: "PlanningController",
            controllerAs: "vm",
            resolve: {
            	notAutorized: checkLogged
            }
        })
        /*
         * Bungalows 
         */
        .when("/bungalows", {
            templateUrl: "app/bungalows/bungalows.html",
            controller: "ListBungalowsController",
            controllerAs: "vm",
        	resolve: {
                notAutorized: checkAuthAdmin
              }
        })
        .when("/bungalows/type/modify/:idBungalowType", {
            templateUrl: "app/bungalows/modify/modify_bungalowType.html",
            controller: "ModifyBungalowTypeController",
            controllerAs: "vm",
            resolve: {
                notAutorized: checkAuthAdmin
              }
        })
        .otherwise({ 
            redirectTo: 'app/views/planning.html'
        });
});

/*logueado como manager o admin*/
function checkAuthAdminOrManager($window, $location, Alertify){
    var role = $window.sessionStorage.getItem('rol');
    if (!role || (role !== "ADMIN" && role !== "MANAGER")) {
      $location.url('/planning');
      Alertify.error('No tienes acceso a esta función');
    }
 }

/*logueado como admin*/
function checkAuthAdmin($window, $location, Alertify){
    var role = $window.sessionStorage.getItem('rol');
    if (!role || role !== "ADMIN") {
      $location.url('/planning');
      Alertify.error('Sólo el administrador tiene permiso para acceder a esta función.');
    }
  }

/*logueado*/
function checkLogged($window, $location, Alertify){
    var role = $window.sessionStorage.getItem('rol');
    if (!role) {
      $location.url('/login');
      Alertify.error('No estás logueado');
    }
  }

bc.config(['$httpProvider',
  function ($httpProvider) {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
  }
]);