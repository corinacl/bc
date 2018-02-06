bc.controller('RegistrationController', ['$timeout', 'LoginService', 'Alertify',
		
	function($timeout, LoginService, Alertify) {
		"use strict";
		var vm = this;

		vm.password;
		vm.role;
		vm.registration = registration;
		vm.listUsers = listUsers;
		vm.username;

		function registration() {
			LoginService.registration(vm.username, vm.password, vm.role).then(function(result) {
				Alertify.success("¡Hecho! El usuario se ha creado con éxito");
				listUsers();
				vm.username=null;
				vm.password=null;
				vm.role=null;
			}, function(errors) {
				Alertify.error("ERROR: " + errors);
			});
		}

		function listUsers() {
			LoginService.listUsers().then(function(result) {
				vm.users = result;
			}, function(errors) {
				Alertify.error(errors);
			});
		}
	} 
]);