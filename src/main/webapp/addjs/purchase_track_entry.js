

// 录入内容默认隐藏，点击按钮才开始录入
$('.input_content').hide();
// 根据日期生成每日所在行程
$('.add_input').click(function(){
	
	// 选择的日期区间
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();	
	var selectStartDay = getBeforeDay(startDate);	
	var selectEndDay = getNextDay(endDate);
	
	// 根据选择的日期区间,计算日期区间天数
	var days = daysBetween(startDate,endDate);
	if(days > 10){
		$('.tc').show();
		$('.tc').fadeOut(8000);
	}else{
		$('.input_content').show();
	}
	
	// 根据选择的日期区间，生成录入单
	var result = getAll(selectStartDay, selectEndDay);	
	var str = result.substring(0,result.length-1);
	var array = str.split(',');		
	var add_row_border = ''
	for(var i=0;i<array.length;i++){
		var a_1 = array[i];
		add_row_border += '<div class="row row_border">'
		add_row_border +='<div class="col-xs-12 outDate"><p>日期:<span class="date_p">'+ a_1 +'</span></p><label> <input type="radio" name="outDate_'+ i +'" value="正常上班"><span>正常上班</span></label><label> <input type="radio" name="outDate_'+ i +'" value="非工作日/其他"><span>非工作日/其他</span></label></div>'
		add_row_border +='<div class="col-xs-12 place"><p>去处:</p><label> <input type="radio" name="place_'+ i +'" value="公司"><span>公司</span></label> <label> <input type="radio" name="place_'+ i +'" value="仓库"><span>仓库</span></label> <label> <input type="radio" name="place_'+ i +'" value="工厂名"><span>工厂名</span></label></div>'
		add_row_border +='<div class="col-xs-12 item"><textarea class="form-control" placeholder="可输入具体事项" value=""></textarea></div>'
		add_row_border +='</div>'							
	};	
	$('.row_borders').html(add_row_border);		
});


//获取当前录入人数值
var entryPerson_val = '';
$('#entryPerson').change(function(){
	entryPerson = $('#entryPerson').val();
	console.log(entryPerson);
	entryPerson_val = entryPerson;
});

// 点击提交
$('#submit').click(function(){	
	console.log(entryPerson_val);
	// 获取当前外出日期数据值
	var data_array = [];
	
	// data_array.push(entryPerson_val);
	$('.row_border').each(function(){
		var arr = new Object();
		var date_p = $(this).find('.date_p').text();
		var outDate = $(this).find('.outDate input:checked').val();
		var place = $(this).find('.place input:checked').val();
		var remark =  $(this).find('.item textarea').val();

//		var strs = [];
//		var p = {"place":place};
//		strs.push(p);
		arr.outDate = date_p;
		arr.remark = remark;
		arr.userName = entryPerson_val;
		arr.trackPlaces = place;
		
		data_array.push(arr);				
	});
	console.log(data_array);	
	var data = JSON.stringify(data_array);
		$.ajax({
			url:'/track/addTrack',
			type:'post',
			dateType:'json',
			data:{
				'param':data
			},
			success:function(data){
				console.log(data)
				if(data.ok){				
					window.location.href= 'purchase_track_view.html';
				}
				
			},
			error:function(){
				
			}
		});

	
});

//获取指定日期的前一天
function getBeforeDay(d){
    d = new Date(d);
    d = +d - 1000*60*60*24;
    d = new Date(d);
    //return d;
    //格式化
    return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
     
}

// 获取指定日期的后一天
function getNextDay(d){
    d = new Date(d);
    d = +d + 1000*60*60*24;
    d = new Date(d);
    //return d;
    //格式化
    return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
     
}

//获取2个日期之间的所有日期
Date.prototype.format = function() {
	var s = '';
	s += this.getFullYear() + '-'; // 获取年份。
	if((this.getMonth() + 1) >= 10) {// 获取月份。
		s += (this.getMonth() + 1) + "-";
	} else {
		s += "0" + (this.getMonth() + 1) + "-";
	}
	if(this.getDate() >= 10) {// 获取日。
		s += this.getDate();
	} else {
		s += "0" + this.getDate();
	}
	return(s); // 返回日期。
};

function getAll(begin, end) {
	var ab = begin.split("-");
	var ae = end.split("-");
	var db = new Date();
	db.setUTCFullYear(ab[0], ab[1] - 1, ab[2]);
	var de = new Date();
	de.setUTCFullYear(ae[0], ae[1] - 1, ae[2]);
	var unixDb = db.getTime();
	var unixDe = de.getTime();
	var str = "";
	for(var k = unixDb + 24 * 60 * 60 * 1000; k < unixDe;) {
		str += (new Date(parseInt(k))).format() + ",";
		k = k + 24 * 60 * 60 * 1000;
	}
	
	return str;
}
// 2个日期之间的天数(包含选中的日期)
function daysBetween(sDate1,sDate2){
	//Date.parse() 解析一个日期时间字符串，并返回1970/1/1 午夜距离该日期时间的毫秒数
	var time1 = Date.parse(new Date(sDate1));
	var time2 = Date.parse(new Date(sDate2));
	var nDays = Math.abs(parseInt((time2 - time1)/1000/3600/24));	
	return nDays +1 ;  // (包含选中的日期，+1 )
};

























