ipsp
====

Unity Web Player 和 REST API 交互例子

## 测试

    curl -l -H "Content-type: application/json" -X POST -d '{"userId":1,"result":"85"}' http://localhost:8080/api/score
    
## ajax 调用
> 此方法为 Unity 回调函数，在生成考试结果后，通过调用 callback 函数，把数据传给服务器。

    function callback(str) {
	    $.ajax({
	        type: 'POST',
	        contentType: 'application/json',
	        dataType: 'text',
	        url: 'api/score',
	        data: '{"userId":1,"result":"85"}',
	        success: function (aa) {
	            alert(aa);
	        }
	    });
	}
    
* userId: int，从 session 中获取登录用户。
* result: string，callback 中的参数值 str。    
