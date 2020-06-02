
//上来先判断PC还是移动端
browserRedirect();
//分辨率变化从新判断并刷新页面
window.onresize = function() {
	browserRedirect();
	//window.location.reload(); //刷新当前页面;
}
//判断pc还是移动端
function browserRedirect() {
	var sUserAgent = navigator.userAgent.toLowerCase();
	var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
	var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
	var bIsMidp = sUserAgent.match(/midp/i) == "midp";
	var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
	var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
	var bIsAndroid = sUserAgent.match(/android/i) == "android";
	var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
	var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
	if(!(bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM)) {
		FontSie_PC();
	} else {
		FontSie_Mobile();
	}
}
//手机字体样式
function FontSie_Mobile() {
	var screenwid = document.documentElement.offsetWidth || document.body.offsetWidth;
	var html = document.getElementsByTagName("html")[0];
	var num = screenwid /750 * 100;
	html.style.fontSize = num + "px";
}
//PC字体样式
function FontSie_PC() {
	var screenwid = document.documentElement.offsetWidth || document.body.offsetWidth;
	var html = document.getElementsByTagName("html")[0];
	var num = screenwid / 1680 * 100;
	html.style.fontSize = num + "px";
}