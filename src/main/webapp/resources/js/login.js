
$(function(){
	
	var type=getQueryString("type");//判断是否登录超时
	if(type=="1"){
		$(".error-info").html("登录超时，请重新登录！").addClass("icon-error-info");
		}
	
	$("#loginbtn").click(function(){
		var va=new Validate({account:'#account',password:'#password'});
         var userAccountFlag=va.userAccount();
		 var userPasswordFlag=va.userPassword();
		 if(userAccountFlag==false&&userPasswordFlag==false){
			 $(".error-info").html("请输入用户名和密码！").addClass("icon-error-info");
			 validateTipError($("#account"));
			 validateTipError($("#password"));
			 }
		else if(userAccountFlag==false&&userPasswordFlag==true){
			$(".error-info").html("用户名不能为空！").addClass("icon-error-info");
			 validateTipError($("#account"));
			}
		else if(userAccountFlag==true&&userPasswordFlag==false){
			$(".error-info").html("密码不能为空！").addClass("icon-error-info");
			 validateTipError($("#password"));
			}
		else{
			$(".error-info").removeClass("icon-error-info");
			$(".error-info").html("");
			ajaxLogin();//验证成功后ajax提交数据
			}
			
			
		});
		
		
/*$(document).keyup(function(event){
  if(event.keyCode ==13){
    $("#loginbtn").trigger("click");
  }
});*/
		//登录提交函数
function ajaxLogin(){
	var loginData={loginName :$("#account").val(),password:$("#password").val(),remenberPwd:$("#check").val()};
			 $.ajax({
             type: "post",
             url: "/cms/areacms/login",
             data: loginData,			 
             dataType: "json",
             success: function(data){
				if(data.manageId==0){
			       $(".error-info").html("用户名或密码错误！").addClass("icon-error-info");
					}
				else{
					//console.log(data.manageId);
					var cookietime = new Date();
					cookietime.setTime(cookietime.getTime());
					$.cookie("manageid", ""+data.manageId+"",{manageid:cookietime}); 
					//alert("11"+$.cookie("manageid"));
					//window.location.href="/cms/html/user_search.html?manageid="+data.manageId+"";//成功后跳转
					window.location.href="/cms/html/user_search.html";//成功后跳转
					}
                      }
         });
	}
	
		
		//pwdPlacehoder();
	  $("#account").blur(function(){
		  $this=$(this);
		  var va=new Validate({account:'#account'});
		 var userAccountFlag=va.userAccount();
		// console.log(userAccountFlag);
		 if(userAccountFlag==false){
			 validateTipError($this);
			 }
		 else{
			 validateTipOk($this);
			 }
		  });
		$("#password").blur(function(){
			$this=$(this);
			var va=new Validate({password:'#password'});
			var userPasswordFlag=va.userPassword();
			//console.log(userPasswordFlag);
			if(userPasswordFlag==false){
				//$this.hide();
				//$("#pwdText").show();
			    validateTipError($("#pwdText"));
			 }
			else{
				//$(this).parent()
				//$("#pwdText").hide();
			    validateTipOk($this);
			 }
          });
		
	})




//验证提示函数
 function validateTipOk(a){
	  $a=a;
	  $a.siblings("strong").removeClass("icon-error");
	  $a.siblings("strong").addClass("icon-correct");
	  $a.parent("p").css({
			"border":"solid 1px #e2dcd6",
			"border-left":"none"
			});
	 }
function validateTipError(a){
	   $a=a;
	   $a.siblings("strong").removeClass("icon-correct");
	   $a.siblings("strong").addClass("icon-error");
	    $a.parent("p").css({
			"border":"solid 1px #ff5958",
			"border-left":"none"
			});
	 }

//验证函数	
	function Validate(a){
		this.account=a.account;
		this.password=a.password;
	}
	Validate.prototype={
		userAccount:function(){
			var account=this.account;
			var accountVal=$(account).val();
			if(accountVal==""){
				return false;
				}
			else{
				return true;
				}
			},
		
		userPassword:function(){
			var password=this.password;
			var passwordVal=$(password).val();
			if(passwordVal==""){
				return false;
				}
			else{
				return true;
				}
			}
		}
	
