bc.controller('CreateClientController', [ '$timeout', 'Alertify', 'ClientsService', '$location',
	
	function($timeout, Alertify, ClientsService, $location) {
		"use strict";
		var vm = this;
		vm.completed = false;
		vm.error = false;
		vm.createClient = createClient;

		function createClient() {
			ClientsService.createClient(vm.client).then(function(result) {
				Alertify.success("¡El cliente ha sido creado con éxito!");
				$location.path('/clients');
			}, function error(errors){
		    	Alertify.error("¡ERROR! " + errors);
		    });
		}
	}				
]);