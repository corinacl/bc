bc.controller('NavController', [ '$timeout', 'LoginService','Alertify', '$location',
	function($timeout, LoginService, Alertify, $location) {
		"use strict";
		var vm = this;
	
		vm.completed = false;
		vm.error = false;
		vm.logout = LoginService.logout;
	    vm.isActive = isActive;
	    vm.isLogged = LoginService.isLogged;
	    vm.isLoggedAuth = LoginService.isLoggedAuth;
	    vm.isLoggedAdmin = LoginService.isLoggedAdmin;
	
	    function isActive(viewLocation) {
	        return viewLocation === $location['path']();
	    }
	   	    
	} ]);