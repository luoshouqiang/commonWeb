// JavaScript Document
	 Handlebars.registerHelper("judgeNull",function(v,options){//判断是否为空
         if(v!=null){
          //继续执行
          return options.fn(this);
         }else{
          //执行else部分
            return options.inverse(this);
          }
       });
	   Handlebars.registerHelper("judgeIndexOddEven",function(v,options){//判断基偶行 设置不同的颜色标记
         if(v%2!=0){
          //继续执行
          return options.fn(this);
         }else{
          //执行else部分
            return options.inverse(this);
          }
       });
	   Handlebars.registerHelper("judgeStatus",function(v,options){//
         if(v=='START'){
          //继续执行
          return options.fn(this);
         }else{
          //执行else部分
            return options.inverse(this);
          }
       });