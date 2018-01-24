bc.controller('ListBookingsController', [ '$timeout', 'Alertify', 'BookingsService',
	
	function($timeout, Alertify, BookingsService) {
		"use strict";
		var vm = this;
		
		vm.booking_id;
		vm.search = search;
		vm.sortBy = sortBy;
		vm.searchSortBy = searchSortBy;
		vm.initList = initList;
		vm.deleteBooking = deleteBooking;
		vm.searchBookings = searchBookings;
		vm.propertyName = 'id';
		vm.sortByProperty = 'bungalow.number'
		vm.reverse = true;
		vm.reverseSearch = true;
		  
		function sortBy(propertyName){
			vm.reverse = (vm.propertyName === propertyName) ? !vm.reverse : false;
			vm.propertyName = propertyName;
		}
		
		function searchSortBy(sortByProperty){
			vm.reverseSearch = (vm.sortByProperty === sortByProperty) ? !vm.reverseSearch : false;
			vm.sortByProperty = sortByProperty;
		}
		  
		function search(){
			BookingsService.search(vm.client_id).then(function(result) {
				vm.data2 = result;
			}, function(errors) {
				Alertify.error("¡ERROR! " + errors);
			});
		}
		
		function initList() {
			BookingsService.initList().then(function(result) {
				vm.bookings = result;
				vm.currentDate = new Date();
			}, function(errors) {
				Alertify.error("¡ERROR! " + errors);
			});
		}
		
		function deleteBooking(){
			BookingsService.deleteBooking(vm.booking_id).then(function(result) {
				Alertify.success("La reserva ha sido cancelada");
				sortBy('id');
				if(vm.arrival != null){
					searchBookings(vm.arrival, vm.departure, vm.bungalow);
				}			
			}, function(errors) {
				Alertify.error("¡ERROR! " + errors);
			});
		}
		
		function searchBookings(){
			BookingsService.searchBookings(vm.arrival, vm.departure, vm.bungalow).then(function(result) {
				vm.resultBookings = result;
			}, function(errors) {
				Alertify.error("¡ERROR! " + errors);
			});
		}
	}				
]);