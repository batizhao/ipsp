ipsp
====

这是一个 Unity Web Player 和 REST API 交互的例子，使用了 Spring Boot 和 Gradle 构建了一个 API 微服务，Unity 程序调用 API 上传数据到后台。

##1. 相关文件说明
* *courseMenu.html*  Web入口
* *courseMenu/courseMenu.unity3d* Unity 主程序文件（由 courseMenu.html 调取）
* *courseMenu/2.png* Unity 互动页面加载时所显示的 logo 图片
* *course/CXXX_ppt.course* 某具体课件的应知部分资源包，XXX为课件编号
* *course/CXXX_3d.course* 某具体课件的考核部分资源包，XXX为课件编号

测试地址：http://localhost:8080/courseMenu.html

##2. courseMenu.html

### 加载相关 js 文件

	<script type='text/javascript' src='https://ssl-webplayer.unity3d.com/download_webplayer-3.x/3.0/uo/jquery.min.js'></script>
	<script type="text/javascript">
		<!--
		var unityObjectUrl = "http://webplayer.unity3d.com/download_webplayer-3.x/3.0/uo/UnityObject2.js";
		if (document.location.protocol == 'https:')
			unityObjectUrl = unityObjectUrl.replace("http://", "https://ssl-");
		document.write('<script type="text\/javascript" src="' + unityObjectUrl + '"><\/script>');
		-->
	</script>
	
### Unity 初始化

	var config = {
		width: 1280,
		height: 800,
		params: { enableDebugging:"0" ,disableContextMenu:true,logoimage:"2.png"}
		
	};
	var u = new UnityObject2(config);

	jQuery(function() {

		var $missingScreen = jQuery("#unityPlayer").find(".missing");
		var $brokenScreen = jQuery("#unityPlayer").find(".broken");
		$missingScreen.hide();
		$brokenScreen.hide();
		
		u.observeProgress(function (progress) {
			switch(progress.pluginStatus) {
				case "broken":
					$brokenScreen.find("a").click(function (e) {
						e.stopPropagation();
						e.preventDefault();
						u.installPlugin();
						return false;
					});
					$brokenScreen.show();
				break;
				case "missing":
					$missingScreen.find("a").click(function (e) {
						e.stopPropagation();
						e.preventDefault();
						u.installPlugin();
						return false;
					});
					$missingScreen.show();
				break;
				case "installed":
					$missingScreen.remove();
				break;
				case "first":
				break;
			}
		});
		u.initPlugin(jQuery("#unityPlayer")[0], "courseMenu.unity3d");
	});
	
### Unity 初始化相关参数

	function OnUnityRequestInit()
	{			    
		u.getUnity().SendMessage("Asset Loader", "SetCourse", "http://localhost:8080/Junior/2.2.1/Examine");
        u.getUnity().SendMessage("_mainFrameBasicService", "SetUid", "111444");
	}	
	
## 3. 服务端接口

### 测试

    curl -l -H "Content-type: application/json" -X POST -d '{"userId":1,"result":"85"}' http://localhost:8080/api/score1
    
> 在其它类型的服务中，如果像 Demo 这样，课件和 Web Server 在同一个服务之下，服务端只需要实现同样的持久化 API 接口，Unity 会按相对路径 /api/score1 调用服务端接口。

## 4. 说明
> 如果初次使用请先安装 Gradle，第一次 run 时可能会下载很多包。

* 在项目根目录使用 gradle bootRun 启动，访问 localhost:8080/main.html
* 在浏览器开发工具中查看请求和响应头、内容。
* 在后台 Console 中观察打印结果（API 接口未持久化，只打印出接收到的数据）。