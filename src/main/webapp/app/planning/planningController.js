bc.controller('PlanningController', [ '$timeout', 'Alertify', 'PlanningService',
	
	function($timeout, Alertify, PlanningService) {
		"use strict";
		var vm = this;
		vm.completed = false;
		vm.error = false;
		vm.month;
		vm.year;
		vm.showPlanning = showPlanning;
		vm.getBungalows = getBungalows;

		function showPlanning() {
			PlanningService.showPlanning(vm.month, vm.year).then(function(result) {
				vm.completed = true;
				vm.plan = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function daysOfMonth(){
			PlanningService.daysOfMonth(vm.month).then(function(result) {
				vm.completed = true;
				vm.plan = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
		
		function getBungalows(){
			PlanningService.getBungalows().then(function(result) {
				vm.completed = true;
				vm.bungalows = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	}				
]);