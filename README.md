ipsp
====

Unity Web Player 和 REST API 交互例子

## 回调客户端 Js 提交结果
callback 方法为 Unity 回调函数，在生成考试结果后，通过调用 callback 函数，把数据传给服务器。

### 简单传值
> user 在服务端通过 session 绑定

    function callback(result) {
        $.post('api/score?message=' + result,
                function (data) {
                    console.log(data);
                });
    }

### JSON 传值

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

## 课件直接提交结果
此种方式需要在课件中取得当前用户的 ID，然后调用 api/score1 直接提交数据到后台。