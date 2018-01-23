bc.controller('LoginController', [ '$timeout', 'LoginService','Alertify', '$location',
		function($timeout, LoginService, Alertify, $location) {
			"use strict";
			var vm = this;

			vm.login = login;
			vm.username;
			vm.password;
			vm.isLogged = isLogged;
			vm.logout = logout;
			vm.isLoggedAuth = isLoggedAuth;
			vm.isLoggedAdmin = isLoggedAdmin;

			function login() {
				LoginService.login(vm.username, vm.password).then(function(result) {
					vm.response = result.token + ":" + result.rol;
					sessionStorage.token = result.token;
					sessionStorage.rol = result.rol;
					$location.path('/planning');
					Alertify.success("Te has logueado con éxito");
				}, function(errors) {
					Alertify.error("ERROR: " + errors);
				});
			}
						
			function isLogged(){
				LoginService.isLogged().then(function(result) {
				}, function(errors) {
					Alertify.error("ERROR: " + errors);
				});
			}
			
			function isLoggedAuth(){
				LoginService.isLoggedAuth().then(function(result) {
				}, function(errors) {
					Alertify.error("ERROR: " + errors);
				});
			}
			
			function isLoggedAdmin(){
				LoginService.isLoggedAdmin().then(function(result) {
				}, function(errors) {
					Alertify.error("ERROR: " + errors);
				});
			}
			
			function logout(){
				LoginService.logout().then(function(result) {
				}, function(errors) {
					Alertify.error("ERROR: " + errors);
				});
			}
		} ]);