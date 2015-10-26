// JavaScript Document
$(function(){
	enterClick(".submit-btn");
	//左侧导航
	$(".nav").find(".nav-list-a").click(function(){
		var data_a=$(this).attr("data");
		$this_ul=$(this).siblings('ul');
		var ulHeight=$this_ul.height();
		if(data_a=="0"){
			$(".nav-list-a").each(function(i) {
               var dataVal=$(this).attr("data");
			   if(dataVal=="1"){
				   $(this).siblings('ul').slideUp();
			       $(this).attr("data","0");
				   $(this).removeClass("active");
				   }
			});
			$this_ul.slideDown();
			$(this).attr("data","1");
			$(this).find("i").removeClass("icon-nav");
			$(this).find("i").addClass("icon-nav-down");
			}
		else{
			$this_ul.slideUp();
			$(this).find("i").removeClass("icon-nav-down");
			$(this).find("i").addClass("icon-nav");
			$(".nav02-sub").not($this_ul).attr("data","1");
			$(this).attr("data","0");
			$(this).removeClass("active");
			}
		});
	})
 




//记住密码函数
function check(a){
	var checkId=$(a.checkId),checked=a.checked,unchecked=a.unchecked,flag=a.flag;
	checkId.click(function(){
		//console.log(a);
		$this=$(this);
		var f=parseInt($this.attr(flag));
		if(f==0){
			$this.removeClass(unchecked);
			$this.addClass(checked);
			$this.attr(flag,"1");
			}
		else{
			$this.removeClass(checked);
			$this.addClass(unchecked);
			$this.attr(flag,"0");
			}
		});
	}
//左侧高度控制
function siderbarControl(lr){
	var lr="lframe";
	var headerHeight=$(".header").height();//头部高度
	var footerHeight=$(".footer").height();//底部高度
	var rframeHeight=$(".rframe").height();//右侧内容高度
	var windowHeight=$(window).height();//窗口高度
	var mainHeight=windowHeight-footerHeight-headerHeight;
	var rframeWindow=rframeHeight-mainHeight;
	//console.log(rframeWindow);
	if(rframeWindow>0){
		$("."+lr).height(rframeHeight+20);
		}
	else{
		$("."+lr).height(mainHeight-25);
		}
	}
	/*
 * 获取url中的参数值
 */
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}	


//获取车位拥有方式函数
function ownWay(a){
	//console.log(a);
	var ownway=$(a.ownway);//拥有方式的类名
	 $.ajax({
	 type: "get",
	 url: "/cms/parking/relationWay",
	 data: {},		
	 dataType: "json",
	 success: function(data){
		 //console.log(data[0]);
				 var html = ''; 
				 $.each(data, function(i){
					//console.log(data[i]);
					 if(data[i]!="无"){
					   html += '<option value='+data[i]+'>'+data[i]+'</option>';
					   }
				 });
				   ownway.html(html);
			  }
 });
	
	}
//获取小区数据
function areaData(){
			 $.ajax({
             type: "get",
             url: "/cms/area/list",
             data: {},		
             dataType: "json",
             success: function(data){
                         var html = ''; 
                         $.each(data, function(i, area){
                               html += '<option value='+area.area_id+'>'+area.area_name+'</option>';
                         });
                         $('#areaName').append(html);
                      }
         });
		}
		
//获取停车场ID函数
function getParkingLotsId(areaId){
	//console.log(areaId);
			 $.ajax({
             type: "get",
             url: "/cms/parking/getAreaPlots",
             data: {areaId:areaId},		
             dataType: "json",
             success: function(data){
						//console.log(data);
						var html="";
						html='<option value="">请选择</option>';
						$.each(data,function(i,plot){
							html+='<option value='+plot.parking_lots_id+'>'+plot.parking_lots_name+'</option>'
							});
						$("#parking-lots-id").html(html);
                      }
         });
	}
//获取车位，根据小区
function getParking(areaId,lotsId,parkingFloor,parkingType,areaName,pageIndex,pageSize){
	if(pageIndex==null){
		pageIndex=1;
	}
	if (pageSize==null) {
		pageSize=10;
	}
	var pageIndex=parseInt(pageIndex);
			 $.ajax({
             type: "get",
             url: "/cms/area/getParking",
             data: {areaId:areaId,lotsId:lotsId,parkingFloor:parkingFloor,parkingType:parkingType,pageIndex:pageIndex,pageSize:pageSize},		
             dataType: "json",
             success: function(data){
						console.log(data);
						var html='';
						$.each(data.data.datas,function(i,dVal){
								html += '<tr><td class="ta-l">'+areaName+'</td><td>'+dVal.parking_lots_name+'</td><td>'+dVal.parking_floor+'</td>'
			                        +'<td>'+dVal.parking_code+'</td><td class="ta-r">'+dVal.parking_space+'</td></tr>';
							});
						$("#table-a-list").html(html);
						pageList(data.data);//调用设置分页按钮函数
						siderbarControl();
                      }
         });
	}
	

	
//enter键触发click事件
function enterClick(a){
	var enterbtn=$(a);
	$(document).keyup(function(event){
  if(event.keyCode ==13){
   enterbtn.trigger("click");
  }
   });
	}

/*显示弹框*/
function showBombBox(a){
	//console.log(a);
	var tkbg=$(a.tkbg);
	var tkbox=$(a.tkbox);
	tkbg.show();
	tkbox.show();
	}

/*隐藏弹框*/
function hideBombBox(a){
	var tkbg=$(a.tkbg);
	var tkbox=$(a.tkbox);
	tkbg.hide();
	tkbox.hide();
	}

//tab切换
function tab(){
	$tabtitle_li=$("#tab-title").find("ul").find("li");
	$tab_text=$("#tab-text").find("div.text");
	$.each($tabtitle_li,function(i){
		$($tabtitle_li[i]).attr("id",i);
		$($tabtitle_li[i]).click(function(){
			$tabtitle_li.removeClass("active");
			$(this).addClass("active");
			$tab_text.css("display","none");
			$($tab_text[$(this).attr("id")]).css("display","block");
			});
		});
}


//分页函数
function pageList(data){
	var pageHtml='';
             var total=parseInt(data.total);
             var pIndex=parseInt(data.pageIndex);
             var pSize=data.pageSize;
             var totalPindex=total-pIndex;
             console.log(total);
          
          
             if(total==1||total==0){
             	 $('.page').html("");
             	return;
             }
             else if(total<6&&total!=1){//小于六条
	             pageHtml+='<a href="javascript:void(0);" class="btn btn-yellow pageone pre" data="pre">上一页</a>';
	               for(var i=1;i<total+1;i++){
						var j=i;
	                   	if(pIndex==j){
	                   		pageHtml+= '<a href="javascript:void(0);" class="btn btn-yellow current" data="current">'+j+'</a>';
	                   	}
	                   	else{
	                   		pageHtml+= '<a href="javascript:void(0);" class="btn btn-gray pageone" data='+j+'>'+j+'</a>';
	                   	}
	               }  
	              pageHtml+='<a href="javascript:void(0);" class="btn btn-yellow pageone next" data="next">下一页</a>';
		         $('.page').attr("total",total);
		         $('.page').attr("pageIndex",pIndex);
	             $('.page').html(pageHtml);
           }else{
	           	pageHtml+='<a href="javascript:void(0);" class="btn btn-yellow pageone pre" data="pre">上一页</a>';
	               for(var i=1;i<6;i++){
	               	var j;
	               	if(totalPindex<5){
	               		 j=total-(5-i);
	               	}else{
	               		 j=5*parseInt((pIndex-1)/5)+i;
	               	}
	                
	                   	if(pIndex==j){
	                   		pageHtml+= '<a href="javascript:void(0);" class="btn btn-yellow current" data="current">'+j+'</a>';
	                   	}
	                   	else{
	                   		pageHtml+= '<a href="javascript:void(0);" class="btn btn-gray pageone" data='+j+'>'+j+'</a>';
	                   	}
	               }  
	             pageHtml+='<a href="javascript:void(0);" class="btn btn-yellow pageone next" data="next">下一页</a>';
	             $('.page').attr("total",total);
		         $('.page').attr("pageIndex",pIndex);
	             $('.page').html(pageHtml);
           }
              siderbarControl("lframe");//设置左侧导航条高度


}