bc.controller('ListClientsController', [ '$timeout', 'Alertify', 'ClientsService',
	
	function($timeout, Alertify, ClientsService) {
		"use strict";
		var vm = this;

		vm.booking_id;
		vm.searchBy;
		vm.searchData;
		vm.initList = initList;
		vm.search = search;
		vm.sortBy = sortBy;
		vm.getBookingsByClient = getBookingsByClient;
		vm.deleteBooking = deleteBooking;
		vm.bookingsByClient;
		vm.client_id;
		vm.client;
		vm.propertyName = 'name';
		vm.reverse = false;

		function initList() {
			ClientsService.initList().then(function(result) {
				vm.completed = true;
				vm.data = result;
				vm.currentDate = new Date();
			}, function(errors) {
				Alertify.error(errors);
			});
		}
			
		function search(){
			ClientsService.search(vm.searchBy, vm.searchData).then(function(result) {
				vm.completed = true;
				vm.data2 = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function getBookingsByClient(){
			ClientsService.getBookingsByClient(vm.client_id).then(function(result) {
				vm.completed = true;
				vm.bookingsByClient = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function sortBy(propertyName){
			vm.reverse = (vm.propertyName === propertyName) ? !vm.reverse : false;
			vm.propertyName = propertyName;
		}
		
		function deleteBooking(){
			ClientsService.deleteBooking(vm.booking_id).then(function(result) {
				Alertify.success("La reserva ha sido cancelada");
				sortBy('id');
				getBookingsByClient(vm.client_id);			
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);