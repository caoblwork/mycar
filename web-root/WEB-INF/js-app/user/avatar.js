function jsfun(obj)
{
//	if(obj.type == "avatarSuccess") alert("头像上传成功");
//	if(obj.type == "avatarError") alert("头像上传失败");
//	if(obj.type == "init") alert("flash初始化完成");
//	if(obj.type == "cancel") alert("取消编辑头像");
//	if(obj.type == "FileSelectCancel") alert("取消选取本机图片");
//	console.log(obj);
}

jQuery(function() {

	var base = $("base").attr("href");
	var flashvars = {
		js_handler:"jsfun",
		swfID:"avatarEdit",
		sourceAvatar:"images/d5d5d5.png",
		avatarLabel:"预览",
//		sourceLabel:"保存原图",
//		sourcePicAPI:"http://asv5.sinaapp.com/widget/upload.php",
		avatarAPI:   base + "user/avatar",
		avatarSize: "40,40",
		avatarSizeLabel: "头像"
	};
	
	var params = {
		menu: "false",
		scale: "noScale",
		allowFullscreen: "true",
		allowScriptAccess: "always",
		bgcolor: "",
		wmode: "direct" // can cause issues with FP settings & webcam
	};
	
	var attributes = {
		id:"AvatarUpload"
	};
	
	swfobject.embedSWF(
		"swf/avatarUpload.swf", 
		"altContent", "100%", "100%", "10.0.0", 
		"swf/expressInstall.swf", 
		flashvars, params, attributes
	);
	
	$("#refresh").click(function() {
		window.location = window.location;
	});

});
