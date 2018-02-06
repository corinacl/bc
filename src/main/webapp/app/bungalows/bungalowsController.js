bc.controller('ListBungalowsController', [ '$timeout', 'Alertify', 'BungalowsService',
	
	function($timeout, Alertify, BungalowsService) {
		"use strict";
		var vm = this;
			
		vm.listBungalows = listBungalows;
		vm.listBungalowType = listBungalowType;

		function listBungalows() {
			BungalowsService.listBungalows().then(function(result) {
				vm.bungalows = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function listBungalowType() {
			BungalowsService.listBungalowType().then(function(result) {
				vm.bungalowType = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);