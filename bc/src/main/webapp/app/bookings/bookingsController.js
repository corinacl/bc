bc.controller('ListBookingsController', [ '$timeout', '$route', 'Alertify', 'BookingsService',
	
	function($timeout, $route, Alertify, BookingsService) {
		"use strict";
		var vm = this;
		
		vm.booking_id;
		vm.search = search;
		vm.sortBy = sortBy;
		vm.searchSortBy = searchSortBy;
		vm.generatePdf = generatePdf;
		//vm.initList = initList;
		vm.deleteBooking = deleteBooking;
		vm.searchBookings = searchBookings;
		vm.sortByProperty = 'bungalow.number'
		vm.reverseSearch = true;
		vm.loading = true;
		vm.bookings = [];
		
		
		vm.pageInfo = {
			pageNumber: 0,
			pageSize: 10,
			totalBookings: 0,
			sortParameter: "id",
			reverse: "desc"
		};
		
		loadBookings();
		
		function loadBookings(){
			BookingsService.getBookings(vm.pageInfo).then(function(result) {
				vm.loading = false;
				vm.bookings = result.content;
				vm.pageInfo.pageNumber = result.number;
				vm.pageInfo.totalBookings = result.totalElements;
				vm.pageInfo.pageSize = result.size;
				vm.currentDate = new Date();
			}, function(errors) {
				vm.loading = false;
				Alertify.error(errors);
			});
		}
		
		vm.changeToPage = pageNumber => {
			vm.pageInfo.pageNumber = pageNumber;
			loadBookings();
		}
		  
		function sortBy(sortParameter){
			if ((vm.pageInfo.reverse == "asc" && vm.pageInfo.sortParameter == sortParameter) || (vm.pageInfo.reverse == "desc" && vm.pageInfo.sortParameter != sortParameter)) {
				vm.pageInfo.reverse = "desc";
			} else {
				vm.pageInfo.reverse = "asc";
			}
			vm.pageInfo.sortParameter = sortParameter;
			loadBookings();
		}
		
		function generatePdf(){
			BookingsService.generatePdf().then(function(result) {
				Alertify.success("El PDF se ha generado correctamente");		
			}, function(errors) {
				Alertify.error("¡ERROR! " + errors);
			});
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
		
		/*function initList() {
			BookingsService.initList().then(function(result) {
				vm.bookings = result;
			}, function(errors) {
				Alertify.error("¡ERROR! " + errors);
			});
		}*/
		
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