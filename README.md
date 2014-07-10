ipsp
====

这是一个 Unity Web Player 和 REST API 交互的例子，使用了 Spring Boot 和 Gradle 构建了一个 API 微服务，实现了两种把 Unity 交互结果上传后台的方法。

## 1. 客户端上传结果
callback 方法为 Unity 回调函数，在生成考试结果后，通过调用 callback 函数，把数据传给服务器。

### 1.1 简单传值

#### 测试

	curl -X POST http://localhost:8080/api/score?message=C

#### JQuery 提交

    function callback(result) {
        $.post(
        	'api/score?message=' + result,
            function (data) {
                console.log(data);
            }
        );
    }

### 1.2 JSON 传值

#### 测试

    curl -l -H "Content-type: application/json" -X POST -d '{"userId":1,"result":"85"}' http://localhost:8080/api/score1

#### JQuery 提交
> user 在客户端绑定

    function callback(result) {
        message = JSON.stringify(result);
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            dataType: 'text',
            url: 'api/score1',
            data: '{"userId":1,"result":' + message + '}',
            success: function (data) {
                console.log(data);
            }
        });
    }

## 2. 服务端上传结果
> 此种方式需要实现一个回调函数，发送当前用户的 ID 给 Unity。

### 取得当前用户的 ID

实现回调函数 getJson ，并调用 SendMessage 方法。第三个参数为当前用户 ID。Unity 会取得消息后，直接调用 api/score1 进行绑定和上传。

	function getJson(){
        u.getUnity().SendMessage("main", "getParamete","3");
    }
    
    
## 3. 分析
这两种方法，相同点是都要实现一个回调函数。  
前一种方法更开放，Unity 只需要输出结果就行，更容易 Web 开发和调试。  
后一种方法对 Web 端来说更简单，但提交结果是在 Untiy 里实现，调试和维护起来比较麻烦。   

安全性上后一种方法稍大于前一种方法，但都不安全，存在篡改结果的可能。

## 4. 说明
> 如果初次使用请先安装 Gradle，第一次 run 时可能会下载很多包。

* 在项目根目录使用 gradle bootRun 启动，访问 localhost:8080/web.html
* 分别点击 Unity 插件的中按钮测试以上接口。
* 在浏览器开发工具中查看请求和响应头、内容。
* 在后台 Console 中观察打印结果（API 接口未持久化，只打印出接收到的数据）。