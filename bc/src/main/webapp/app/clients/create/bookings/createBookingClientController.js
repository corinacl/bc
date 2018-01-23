bc.controller('CreateBookingClientController', [ '$timeout', 'Alertify', 'ClientsService', '$location','$routeParams',
	
	function($timeout, Alertify, ClientsService, $location, $routeParams) {
		"use strict";
		var vm = this;
		vm.completed = false;
		vm.error = false;
		vm.createBookingForClient = createBookingForClient;
		vm.getClientById = getClientById;
		vm.checkDates = checkDates;
		vm.arrival;
		vm.departure;
		vm.clientId = $routeParams.idClient;

		function createBookingForClient() {
			ClientsService.createBookingForClient(vm.booking, vm.arrival, vm.departure).then(function(result) {
				Alertify.success("¡La reserva ha sido creada con éxito!");
				$location.path('/bookings');
			}, function error(errors){
		    	Alertify.error("¡ERROR! La reserva no se ha podido crear"+ " -- " +errors);
		    });
		}
		
		function checkDates(){
			ClientsService.checkDates(vm.arrival, vm.departure).then(function(result) {
				vm.completed = true;
				vm.resBungalow = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function getClientById() {
			ClientsService.getClientById(vm.clientId).then(function(result) {
				vm.completed = true;
				vm.client = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}
]);