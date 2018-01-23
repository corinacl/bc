bc.controller('ModifyClientController', [ '$timeout', 'Alertify', 'ClientsService', '$location','$routeParams',

	function($timeout, Alertify, ClientsService, $location, $routeParams) {
	"use strict";
	var vm = this;

	vm.id = $routeParams.idClient;
	vm.getClientById = getClientById;
	vm.modifyClient = modifyClient;
			
	function getClientById() {
		ClientsService.getClientById(vm.id).then(function(result) {
			vm.completed = true;
			vm.client = result;
		}, function(errors) {
			Alertify.error(errors);
		});
	}
	
	function modifyClient() {
		ClientsService.modifyClient(vm.client).then(function(result) {
			vm.completed = true;
			vm.client = {};
	        Alertify.success("¡El usuario ha sido modificado con éxito!");
			$location.path('/clients');
		}, function(errors) {
			Alertify.error("¡ERROR! " + errors);
		});
	}
}				
]);