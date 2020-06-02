

//工艺中文
var mainProcessList = [
                       {
                           name: "1-注塑"
                       },
                       {
                           name: "1-吸塑"
                       },
                       {
                           name: "1-滚塑"
                       },
                       {
                           name: "1-吹塑"
                       },
                       {
                           name: "1-挤塑"
                       },
                       {
                           name: "1-其他塑料工艺"
                       },
                       {
                           name: "2-不锈钢铸造(硅溶胶、水玻璃)"
                       },
                       {
                           name: "2-压铸(铝、锌、镁、铜等)"
                       },
                       {
                           name: "2-熔模铸(钢、铁、铜、铝等)"
                       },
                       {
                           name: "2-低压重力铸造(铝、铜等)"
                       },
                       {
                    	   name: "2-粉末冶金"
                       },
                       {
                           name: "2-其他铸造"
                       },
                       {
                           name: "3-热锻",                        
                       },
                       {
                           name: "3-冷锻",                        
                       },
                       {
                           name: "3-原料采购粗加工"
                       },
                       {
                           name: "4-其他钣金加工"
                       },
                       {
                           name: "4-激光等离子火焰切割"
                       },
                       {
                    	   name: "4-折弯"
                       },
                       {
                    	   name: "4-弯管"
                       },
                       {
                    	   name: "4-焊接"
                       },
                       {
                    	   name: "4-装配"
                       },
                       {
                    	   name: "4-冲压"
                       },
                       {
                    	   name: "4-深拉伸"
                       },
                       {
                    	   name: "4-数冲"
                       },
                       {
                           name: "4-旋压"
                       },
                       {
                           name: "4-铝挤压"
                       },
                       {
                           name: "5-3轴以上精密加工中心"
                       },
                       {
                           name: "5-高速车床"
                       },
                       {
                           name: "5-高速铣床"
                       },
                       {
                           name: "5-普通车铣钻磨"
                       },
                       {
                           name: "5-其他机加工"
                       },
                       {
                    	   name: "6-阳极氧化"
                       },
                       {
                    	   name: "6-喷塑"
                       },
                       {
                    	   name: "6-电镀"
                       },
                       {
                    	   name: "6-热浸锌"
                       },
                       {
                    	   name: "6-抛光或拉丝"
                       },
                       {
                    	   name: "6-其他表面处理"
                       },
                       {
                    	   name: "7-电子"
                       },
                       {
                    	   name: "7-零售包装"
                       },
                       {
                           name: "7-现成商品采购"
                       },
                       {
                           name: "7-其他采购"
                       }
                   ];


//添加工艺事件
function getProcessList(){	
	var tl = mainProcessList.length;
	var ary = [];
	for(var i=0;i<tl;i++){
		ary.push(mainProcessList[i].name)
	}
	return ary;
}

//获取工艺，添加到select
function addProcess(){
	var tl = mainProcessList.length;
	var options = "";
	for(var i=0;i<tl;i++){
		var name = mainProcessList[i].name;
		var processName = name.split("-")[1];
		options += '<option value="'+processName+'">'+mainProcessList[i].name+'</option>';	
	}
	return options;
}

function getProcessByIndex(index){
	return mainProcessList[index].name;
}


