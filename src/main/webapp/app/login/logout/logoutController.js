bc.controller('LogoutController', [ '$timeout', 'LoginService','Alertify', '$location',
		function($timeout, LoginService, Alertify, $location) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.logout = logout;
			vm.username;
			vm.password;
			vm.respuesta = "";

			function logout() {
				LoginService.logout().then(function(result) {
					vm.completed = true;
					vm.response = result.token + ":" + result.rol;
					sessionStorage.token = '';
					$location.path('/login');
					if (sessionStorage.token != null && sessionStorage.token != undefined){
						$("#logout").addClass("hidden");
						$("#login").removeClass("hidden");
					}
					Alertify.success("Has salido!");
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
				});
			}
		} ]);