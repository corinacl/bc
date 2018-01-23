bc.controller('ModifyBungalowTypeController', [ '$timeout', 'Alertify', 'BungalowsService', '$location','$routeParams',

	function($timeout, Alertify, BungalowsService, $location, $routeParams) {
		"use strict";
		var vm = this;
	
		vm.id = $routeParams.idBungalowType;
		vm.getBungalowTypeById = getBungalowTypeById;
		vm.modifyBungalowType = modifyBungalowType;
		
		function getBungalowTypeById() {
			BungalowsService.getBungalowTypeById(vm.id).then(function(result) {
				vm.bungalowType = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	
		function modifyBungalowType() {
			BungalowsService.modifyBungalowType(vm.bungalowType).then(function(result) {
				vm.bungalowType = {};
		        Alertify.success("¡El tipo de bungalow ha sido modificado con éxito!");
				$location.path('/bungalows');
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);