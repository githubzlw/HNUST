$(function(){	
	search();
	// 封装日期转换函数
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



    //搜索
	function search(page){
		var obj = {};
		var startTime = $('#startDate').val();
		var endTime = $('#endDate').val();		
		obj.startTime = startTime;
		obj.endTime = endTime;
		obj.page = page;
		data = JSON.stringify(obj);	
		
		$.ajax({
			url:'/track/selectTrack',
			type:'post',
			datatype:'json',
			data:{
				'param':data
			},
			success:function(data){
				
				if(data.ok){
					var json = data.data;				
					// console.log(json);
					// var totalPage = json.totalPage;
					var totalCount = json.totalCount;
					var totalpages = Math.ceil(totalCount/5);
					console.log(totalpages);
					var tracks = json.tracks;
					var tr = '';
					for(var i = 0;i<tracks.length;i++){
						var userName = tracks[i].userName;
						var outDate = tracks[i].outDate;
					//  var item = tracks[i].item;  行踪暂时没有接口参数
						
												
						var date = new Date(outDate);
						var d = date.Format("yyyy-MM-dd");
						
						var places='';
						var placeList = tracks[i].placeList;
						for(var j = 0;j<placeList.length;j++){
							places += placeList[j].place+",";
						}
						if(places){
							places = places.substring(0,places.length-1);
						}
						var id = tracks[i].id;					
						
						var remark = tracks[i].remark;
						
						tr += '<tr>'
						tr	+= '<td>'+ userName +'</td>'
						tr	+= '<td>'+ d +'</td>'
						tr	+= '<td>'+ places +'</td>'
						tr  += '<td> '+ remark +' </td>' // 行踪暂时没有接口参数
						tr  += '<td><a class="modify_btn" href="purchase_track_modify.html?id='+id+'" target="_blank" id="'+ id +'">修改</a></td>'
						tr += '</tr>'										
					}
					$('#vier_tbody').html(tr);
					
					// 添加分页
				 	var element = $('#pageUl');
			        var options = {
			            bootstrapMajorVersion:3,
			            currentPage: page,
			            numberOfPages: 10,
			            totalPages:totalpages
			        }
			        element.bootstrapPaginator(options);
				     					
				}
			},error:function(){
				
			}
		})
		
	}










