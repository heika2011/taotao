
layui.use(['layer'],function () {
    var layer = layui.layer,
    $=layui.jquery;

    $(".login-main").on("click","#js_submit",function () {
        $.ajax({
            url:"/user/dologin",
            type:"post",
            data:JSON.stringify({username:$("#user").val(),password:$("#password").val()}),
            contentType:"application/json",
            dataType:"json",
            success:function (datas) {
                if (datas.code==200){
                    layer.msg("登录成功")
                }else{
                    layer.msg(datas.msg)

                }

            }
        })

    })


})

