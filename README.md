ipsp
====

Unity Web Player 和 REST API 交互例子

## 简单传值
> 此方法为 Unity 回调函数，在生成考试结果后，通过调用 callback 函数，把数据传给服务器。

    function callback(result) {
        $.post('api/score?message=' + result,
                function (data) {
                    console.log(data);
                });
    }

## JSON 传值

### 测试

    curl -l -H "Content-type: application/json" -X POST -d '{"userId":1,"result":"85"}' http://localhost:8080/api/score1

### JQuery 提交
> 实际使用时用 result 参数替换 data 中的 “85”。

    function callback(result) {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            dataType: 'text',
            url: 'api/score1',
            data: '{"userId":1,"result":"85"}',
            success: function (data) {
                console.log(data);
            }
        });
    }