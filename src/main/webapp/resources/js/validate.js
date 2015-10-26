// JavaScript Document
//验证电话号码
$.validator.addMethod("isMobile",function(value,element,params){  
var mobile = /^0?(13|15|18|14|17)[0-9]{9}$/;
//console.log(mobile.test(value));
	if(value!=""){
		if(value.length == 11 && mobile.test(value)){
			return true;
		}
		else{		
		return false;
		}
	}else{		
		return true;
	}
},"电话号码不正确！");

$.validator.addMethod("isMultiMobile",function(value,element,params){  
var mobile = /^1[3578][0-9]{9}(,1[3578][0-9]{9})*$/;
//console.log(mobile.test(value));
	if(value!=""){
		if(mobile.test(value)){
			return true;
		}
		else{		
		return false;
		}
	}else{		
		return true;
	}
},"输入格式不正确！");

//验证车牌号
$.validator.addMethod("pNumber",function(value,element,params){  
var pNumber = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9\u4e00-\u9fa5]{1}$/;//console.log(pNumber.test(value));
	if(value!=""){
		if(pNumber.test(value)){
			return true;
		}
		else{		
		return false;
		}
	}else{		
		return true;
	}
},"车牌号不正确！");

//验证密码
$.validator.addMethod("pwd",function(value,element,params){  
var p = /^.*[A-Za-z0-9\\w_-]+.*$/;
//console.log(p.test(value));
		if(p.test(value)){
			return true;
		}
		else{		
		return false;
		}
},"密码格式不正确！");


//验证房号
$.validator.addMethod("isRoomNo",function(value,element,params){  
//var roomno = /^[0-9]{1,9}-[0-9]{1,9}-[0-9]{1,9}$/;
var roomno = /^[0-9]{1,9}(-[0-9]{1,9})*$/;
//console.log(mobile.test(value));
	if(value!=""){
		if(roomno.test(value)){
			return true;
		}
		else{		
		return false;
		}
	}else{		
		return true;
	}
},"输入格式不正确！");

//验证身份证号
$.validator.addMethod("isID",function(value,element,params){  
//var roomno = /^[0-9]{1,9}-[0-9]{1,9}-[0-9]{1,9}$/;
var ID = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
//console.log(mobile.test(value));
	if(value!=""){
		if(ID.test(value)){
			return true;
		}
		else{		
		return false;
		}
	}else{		
		return true;
	}
},"输入格式不正确！");
//验证输入为正整数
$.validator.addMethod("intNo",function(value,element,params){  
var p = /^[1-9]{1}[0-9]*$/;
//console.log(p.test(value));
		if(p.test(value)){
			return true;
		}
		else{		
		return false;
		}
},"请输入正整数！");
//验证输入一位大写字母
$.validator.addMethod("oneCapitalLetter",function(value,element,params){  
var p = /^[A-Za-z]{1}$/;
//console.log(p.test(value));
		if(p.test(value)){
			return true;
		}
		else{		
		return false;
		}
},"请输入一个字母！");
