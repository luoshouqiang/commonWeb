// JavaScript Document
$(function(){
	siderbarControl("lframe");
	
	 $("#searchform").validate({
       submitHandler: function(form) {
			var vehiclePlatNo=$("#vehiclePlatNo").val();
			var vin=$("#vin").val();
			var startDate=$("#startDate").val();
			var endDate=$("#endDate").val();
			var inquiryStatus=$("#inquiryStatus").val();
			var searchData={vehiclePlatNo:vehiclePlatNo,vin:vin,startDate:startDate,endDate:endDate,inquiryStatus:inquiryStatus};
			getAskPriceUserList(searchData);//调取查询用户函数
		},
		rules: {
				userName: {//用户名字
					//minlength: 2
				},
				phoneNum: {//电话号码
					//isMobile: true
				},
				platNo: {//车牌号
					//pNumber:true
				},
				parkingNo: {//车位号
	
				},
			},
			messages: {
				userName: {
					required: "请输入姓名！",
					minlength: "姓名最少为两个字！"
				},
				phoneNum: {
					required: "请输入手机号码！"
				},
				platNo: {
					required: "请输入车位号！",
				},
				parkingNo: {
					required: "请输入车位号！",
				},
			},
			 //设置错误信息存放标签
				errorElement: "strong"
    });	
	})
	

 function getAskPriceUserList(searchData){
		 $.ajax({
             type: "post",
             url: "/insurance/order/list",
             data: JSON.stringify(searchData),
			 contentType: "application/json; charset=utf-8",
			 //data: searchData,
             dataType: "json",
             success: function(data){
             	console.log(data);
				var askPriceUserListTpl = Handlebars.compile($("#askPriceUserList").html());
				var html=askPriceUserListTpl(data);
				$('#table-a-list').html(html);
                      }
         });
		}


/*function getUserInfo(pageIndex,pageSize){//获取用户信息
    var areaid=$("#areaName").val();
	var username=$("#userName").val();
	var platno=$("#platNo").val();
	var roomno=$("#roomNo").val();
	var phonenum=$("#phoneNum").val();
	var parkingno=$("#parkingNo").val();
	if(pageIndex==null){
		pageIndex=1;
	}
	if (pageSize==null) {
		pageSize=20;
	}
	var searchData={areaId:areaid,userName:username,platNo:platno,roomNo:roomno,phoneNum:phonenum,parkingNo:parkingno,pageIndex:pageIndex,pageSize:pageSize};
	
	 $.ajax({
             type: "post",
             url: "/cms/areacms/list",
             data: JSON.stringify(searchData),
			 contentType: "application/json; charset=utf-8",
             dataType: "json",
             success: function(data){
				  $('#table-a-list').html(""); 
						 if(data.datas[0]==null){
							 alert("没有符合条件的搜索！");
							 return;
							 }
			//console.log(data);
                         var html = ''; 
                         $.each(data.datas, function(i, comment){
							 //console.log(i);
							 var plotsName=comment.plotsName;
							 var userId=comment.userId;
							 var roomNo=comment.roomNo;
							 var opr="";
							 if(plotsName==null){
								 plotsName="无";
								 }
							 var wrk=comment.vehicleStatus;
							  var cr="",trClass="";
							 if(wrk=="已入库"){cr="c-yellow";}else if(wrk=="未入库"){cr="c-blue";}else{cr="";}//设置是否入库的颜色
							 if(i%2!=0){
								 trClass="tr-flag";
								 }
							if(roomNo==null){roomNo="无";}
							if(userId==-1){
								userId="无";
								opr="无"
								}
							else{
								opr='<a href="/cms/html/user_modify.html?userid='+comment.userId+'&areaid='+comment.areaId+'">修改</a>';
								}
                         	html += '<tr class='+trClass+'><td>'+userId+'</td><td>'+comment.userName+'</td><td>'+comment.phoneNum+'</td>'
							+'<td>'+roomNo+'</td><td>'+comment.areaName+'</td>'
			+'<td>'+plotsName+'</td><td>'+comment.parkingNo+'</td><td>'+comment.parkingSpace+'</td><td>'+comment.parkingType+'</td>'
			+'<td>'+comment.platNo+'</td><td class='+cr+'>'+comment.vehicleStatus+'</td><td>'+comment.dueDate+'</td>'
            +'<td class="change operation-btn ta-r">'+opr+'</td></tr>';
                         });
                         $('#table-a-list').html(html);
                         pageList(data);//调用设置分页按钮函数
						 siderbarControl("lframe");//设置左侧导航条高度
                      }
         });
	}*/