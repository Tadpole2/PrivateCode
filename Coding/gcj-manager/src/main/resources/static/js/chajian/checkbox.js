
function gongneng(obj){
	this.doms=(obj==undefined)?"":obj;
	this.flag=true;
  this.jishi=2;
}
gongneng.prototype.yanzhengma=function(callback){
	var that=this;
	var state=parseInt(that.doms.attr("data-state"));
	var num=parseInt(that.doms.attr("data-time"));
	var t;
	if(state==1){
		that.doms.html(num+"秒");
		that.flag=false;
	    that.doms.css({"border":"1px solid #eeeeee","background":"#eeeeee","color":"#A9A9A9"});
	    t=setInterval(move,1000);
	    function move() {
	        that.doms.html(num+"秒");	       
	        if(num<=0){
	            clearInterval(t);
	            that.flag=true;
	            that.doms.attr("data-state","0");
	            that.doms.css({"border":"1px solid #3CC8B4","background":"#E8FCF9","color":"#3cc8b4"});
	            that.doms.html("发送验证码");
	            that.doms.attr("data","0");
	        }
	        num--;
	    }
	}
		
	this.doms[0].onselectstart=function(){
		return false;
	}
};
gongneng.prototype.djstwo=function(){
    var that=this;
    var tt=setInterval(function(){
        that.jishi--;
        if(that.jishi<=0){
            clearInterval(tt);
            window.location.href=that.doms.find("a").attr("href"); 
        }
        that.doms.find("i").html(that.jishi);
    },1000)
}




//获取指定form中的所有的<input>对象    
function getElements(formId) {    
    var form = document.getElementById(formId);    
    var elements = new Array();    
    var tagElements = form.getElementsByTagName('input');    
    for (var j = 0; j < tagElements.length; j++){  
         elements.push(tagElements[j]);  
  
    }  
    return elements;    
}   
  
//获取单个input中的【name,value】数组  
function inputSelector(element) {    
  if (element.checked)    
     return [element.name, element.value];    
}    
      
function input(element) {    
    switch (element.type.toLowerCase()) {    
      case 'submit':    
      case 'hidden':    
      case 'password':    
      case 'text':    
        return [element.name, element.value];    
      case 'checkbox':    
      case 'radio':    
        return inputSelector(element);    
    }    
    return false;    
}    
  
//组合URL  
function serializeElement(element) {    
    var method = element.tagName.toLowerCase();    
    var parameter = input(element);    
    
    if (parameter) {    
      var key = encodeURIComponent(parameter[0]);    
      if (key.length == 0) return;    
    
      if (parameter[1].constructor != Array)    
        parameter[1] = [parameter[1]];    
          
      var values = parameter[1];    
      var results = [];    
      for (var i=0; i<values.length; i++) {    
        results.push(key + '=' + encodeURIComponent(values[i]));    
      }    
      return results.join('&');    
    }    
 }    
  
//调用方法     
function serializeForm(formId) {    
    var elements = getElements(formId);    
    var queryComponents = new Array();    
    
    for (var i = 0; i < elements.length; i++) {    
      var queryComponent = serializeElement(elements[i]);    
      if (queryComponent)    
        queryComponents.push(queryComponent);    
    }    
    
    return queryComponents.join('&');  
}