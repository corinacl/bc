bc.controller('CreateBookingController', [ '$timeout', 'Alertify', 'BookingsService', '$location',
	
	function($timeout, Alertify, BookingsService, $location) {
		"use strict";
		var vm = this;
		vm.completed = false;
		vm.error = false;
		vm.createBooking = createBooking;
		vm.getClients = getClients;
		vm.checkDates = checkDates;
		vm.arrival;
		vm.departure;

		function createBooking() {
			BookingsService.createBooking(vm.booking, vm.arrival, vm.departure).then(function(result) {
				Alertify.success("¡La reserva ha sido creada con éxito!");
				$location.path('/bookings');
			}, function error(errors){
		    	Alertify.error("¡ERROR! La reserva no se ha podido crear"+ " -- " +errors);
		    });
		}
		
		function checkDates(){
			BookingsService.checkDates(vm.arrival, vm.departure).then(function(result) {
				vm.completed = true;
				vm.resBungalow = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function getClients() {
			BookingsService.getClients().then(function(result) {
				vm.completed = true;
				vm.clients = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);