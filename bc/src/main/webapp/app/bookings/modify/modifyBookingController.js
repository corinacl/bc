bc.controller('ModifyBookingController', [ '$timeout', 'Alertify', 'BookingsService', '$location','$routeParams',

	function($timeout, Alertify, BookingsService, $location, $routeParams) {
		"use strict";
		var vm = this;
	
		vm.id = $routeParams.idBooking;
		vm.getBookingById = getBookingById;
		vm.modifyBooking = modifyBooking;
		vm.getClients = getClients;
		vm.checkDatesModify = checkDatesModify;
		vm.arrival; 
		vm.departure;


		function getBookingById() {
			BookingsService.getBookingById(vm.id).then(function(result) {
				vm.completed = true;
				vm.booking = result;
				vm.arrival = vm.booking.arrival;
				vm.departure = vm.booking.departure;
				vm.booking.idclient = vm.booking.client.id;
				vm.booking.idbungalow = vm.booking.bungalow.id;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	
		function modifyBooking() {
			BookingsService.modifyBooking(vm.booking, vm.arrival, vm.departure).then(function(result) {
				vm.completed = true;
				vm.booking = {};
		        Alertify.success("¡La reserva ha sido modificada con éxito!");
				$location.path('/bookings');
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
		
		function checkDatesModify(){
			BookingsService.checkDatesModify(vm.id, vm.arrival, vm.departure).then(function(result) {
				vm.completed = true;
				vm.resBungalow = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);