$(function(){
	
	return_date();
	
// 最开始录入的消息回显
		
function return_date(){
	
	 var str = location.href;
	 var num = str.indexOf("?");
	 var str = str.substr(num + 1);
	 var n = str.length;
	 var m = n-1;	
	 var id = str.substring(m,n);
	 
	// console.log(id);
	
	$.ajax({
		url:'/track/selectTrackById',
		type:'get',
		datatype:'json',
		data:{
			'id':id
		},
		success:function(data){				
			if(data.ok){
				var json = data.data;
			
				console.log(json);
				
				var outDate = json.outDate;
				
				var date = new Date(outDate);
				var d = date.Format("yyyy-MM-dd");
				
				$('.person').text(' ' + json.userName + ' ');
				$('.outDate').text(' ' + d + ' ');

				// 回显去哪地址
				var places='';
				var placeList = json.placeList;
				
				for(var j = 0;j<placeList.length;j++){
					places += placeList[j].place+",";										
				}				
				if(places){
					places = places.substring(0,places.length-1);
				}
								
				
				// 正常上班/非工作日暂时没有数据
				
//				$('.duty_row input[name="duty"]').each(function(){
//					var this_duty = $(this).val();
//					if(this_duty == duty){
//						$(this).prop('checked',true);
//						
//					}else{
//						$(this).prop('checked',false);
//						$(this).parent.siblings('label').find('input[name="duty"]').prop('checked',true);
//					}
//					
//				});
				
				
				// 去处
				$('.place_row input[name="place"]').each(function(){
					var this_place = $(this).val();	
					// console.log(this_place);
					if(this_place == places){
						$(this).prop('checked',true);
					}	
				});
				
				// 注意事项暂时没有数据
				//  $('.item').text(' '+ item +' ');
				
			}
		}
		
	});
}	


// 提交修改后的数据
$('#confirmSubmit').click(function(){
	 var str = location.href;
	 var num = str.indexOf("?");
	var str = str.substr(num + 1);
	 var n = str.length;
	 var m = n-1;	
	 var id = str.substring(m,n);

	var arr = {};
	$('.row_border').each(function(){
		
		var this_duty = $(this).find('input[name="duty"]:checked').val();
		var place = $(this).find('input[name="place"]:checked').val();
		var outDate = $(this).find('span[class="outDate"]').text();
		
		var remark = $(this).find('.item').val();
	//	arr.this_duty = this_duty;
		arr.trackPlaces = place;
		arr.id = id;
		arr.outDate = Date.parse(new Date(outDate));
		console.log(outDate);
		arr.remark = remark;
	//	obj.push(arr);
		
	})
	
	var data = JSON.stringify(arr);
	console.log(data)
	
	$.ajax({
		url:'/track/updateTrack',
		type:'post',
		datatypr:'post',
		data:{
			'param':data
		},
		success:function(data){
			// console.log(data);
			if(data.ok){
				window.location.href = 'purchase_track_view.html'
			}
		},
		error:function(){
			
		}
	});		
});

//封装日期转换函数
Date.prototype.Format = function(fmt)   
{ 
//author:wangweizhen
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}; 


})