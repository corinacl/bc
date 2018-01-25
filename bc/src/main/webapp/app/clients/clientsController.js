bc.controller('ListClientsController', function($route, $timeout, Alertify, ClientsService) {
	"use strict";
	
	var vm = this;
	vm.booking_id;
	vm.searchBy;
	vm.searchData;
	vm.search = search;
	vm.sortBy = sortBy;
	vm.getBookingsByClient = getBookingsByClient;
	vm.deleteBooking = deleteBooking;
	vm.bookingsByClient;
	vm.client_id;
	vm.client;
	vm.loading = true;
	vm.clients = [];
	
	vm.pageInfo = {
		pageNumber: 0,
		pageSize:4,
		totalClients: 0,
		sortParameter: "name",
		reverse: "asc"
	};
	
	loadClients();
	
	function loadClients(){
		ClientsService.getClients(vm.pageInfo).then(function(result) {
			vm.loading = false;
			vm.clients = result.content;
			vm.pageInfo.pageNumber = result.number;
			vm.pageInfo.totalClients = result.totalElements;
			vm.pageInfo.pageSize = result.size;
			vm.currentDate = new Date();
		}, function(errors) {
			vm.loading = false;
			Alertify.error(errors);
		});
	}

	vm.changeToPage = pageNumber => {
		vm.pageInfo.pageNumber = pageNumber;
		loadClients();
	}
	
	function initList() {
		ClientsService.initList().then(function(result) {
			vm.data = result;
		}, function(errors) {
			Alertify.error(errors);
		});
	}
	
	function search(){
		ClientsService.search(vm.searchBy, vm.searchData).then(function(result) {
			vm.data2 = result;
		}, function(errors) {
			Alertify.error(errors);
		});
	}
	
	function getBookingsByClient(){
		ClientsService.getBookingsByClient(vm.client_id).then(function(result) {
			vm.bookingsByClient = result;
		}, function(errors) {
			Alertify.error(errors);
		});
	}
	
	function sortBy(sortParameter){
		if ((vm.pageInfo.reverse == "asc" && vm.pageInfo.sortParameter == sortParameter) || (vm.pageInfo.reverse == "desc" && vm.pageInfo.sortParameter != sortParameter)) {
			vm.pageInfo.reverse = "desc";
		} else {
			vm.pageInfo.reverse = "asc";
		}
		vm.pageInfo.sortParameter = sortParameter;
		loadClients();
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
		
});