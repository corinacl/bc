bc.service('BookingsService', ['$http', '$q', function ($http, $q) {
   "use strict";
   
   const urlBase="http://localhost:8080/manager/api";
   var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}

   this.request = function(config) {
		  $http.defaults.headers.common['Authorization'] = 'Basic ' + Base64.encode(sessionStorage.token + ':');
	      let deferred = $q.defer();
	      $http(config).then(function (response) {
	    	  deferred.resolve(response.data);
	      }, function (response){
	    	  let errorMsg;
	    	  if(response.data.error === undefined) {
	    		  errorMsg="";
	    	  }else{
	    		  errorMsg = response.data.description;
	    	  }
	    	  deferred.reject(errorMsg);
	      });
	      return deferred.promise;	   
   }
   
   this.getBookings = function (pageInfo){
		  let config = {
		 	 method: 'GET',
		 	 url: `${urlBase}/bookings?page=${pageInfo.pageNumber}&size=${pageInfo.pageSize}` + 
		 	 `&sort=${pageInfo.sortParameter},${pageInfo.reverse}`
		  };
	      return this.request(config);
	}
   
//   this.initList = function (){
//  	   let config = {
//			   method: 'GET',
//  			   url: urlBase+"/bookings/list"
//  	   };
//  	  return this.request(config);
//    };
    

   this.getClients = function (){
	   let config = {
			   method: 'GET',
			   url: urlBase+"/clients/list"
	   };
		  return this.request(config);
	  }
   
   this.getBungalows = function (){
	   let config = {
			   method: 'GET',
			   url: urlBase+"/bungalows"
	   };
		  return this.request(config);
	  }
   
   this.createBooking = function (booking, arrival, departure){
	   let config = {
			   method: 'POST',
			   url: urlBase+"/bookings",
			   data:{
				   'idCliente': booking.idcliente,
				   'idBungalow': booking.idbungalow,
				   'arrival': arrival, 
				   'departure': departure
			   }
	   };
	  return this.request(config);
   }
   
   this.modifyBooking = function (booking, arrival, departure) {
   	let config = {
			   method: 'PUT',
			   url: urlBase+"/bookings",
			   data:{
				   'id': booking.id, 
				   'idClient': booking.idclient,
				   'idBungalow': booking.idbungalow,
				   'arrival': arrival, 
				   'departure': departure 
			   }
	   };
		  return this.request(config);
	}
   
   this.getBookingById = function (id){
   	let config = {
			   method: 'GET',
			   url: urlBase+"/bookings/"+id
	   };
		  return this.request(config);
   }
	
   
   this.search = function (client_id){
  	   let config = {
			   method: 'POST',
  			   url: urlBase+"/bookings/search",
  			   data:{'id': client_id}
  	   };
  	  return this.request(config);
    }
   
   this.deleteBooking = function (booking_id){
  	   let config = {
			   method: 'DELETE',
  			   url: urlBase+"/bookings/"+booking_id
  	   };
  	  return this.request(config);
    }
      
   this.checkDates = function (arrival, departure){
  	   let config = {
			   method: 'POST',
  			   url: urlBase+"/bungalows/search",
  			   data:{
  				   'arrival': arrival, 
				   'departure': departure 
  			   }
  	   };
  	  return this.request(config);
    }
   
   this.checkDatesModify = function (id, arrival, departure){
  	   let config = {
			   method: 'POST',
  			   url: urlBase+"/bungalows/search/modify",
  			   data:{
  				   'arrival': arrival, 
				   'idBooking': id,
				   'departure': departure
  			   }
  	   };
  	  return this.request(config);
    }
   
   this.searchBookings = function (arrival, departure, bungalow){
  	   let config = {
			   method: 'POST',
  			   url: urlBase+"/bookings/search/bookings",
  			   data:{
  				   'arrival': arrival, 
  				   'departure': departure,
  				   'bungalow': bungalow
  			   }
  	   };
  	  return this.request(config);
    }
   
   this.generatePdf = function(){
	   let config = {
			   method: 'GET',
			   url: urlBase+"/pdf",
			   params: {
				   fileName: "pdfTesttttt"
			   }
	   };
	   return this.request(config);
   }
   
   this.createConfirmation = function(booking_id){
	   let config = {
			   method: 'POST',
			   url: urlBase+"/pdf",
			   params: { 'id': booking_id }
	   };
	   return this.request(config);
   }

}]);