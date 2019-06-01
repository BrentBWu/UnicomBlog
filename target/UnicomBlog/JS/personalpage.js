// 密码确认过滤器
function pwdVlidator() {
    var pwd1 = document.getElementById("pwd").value;
    var pwd2 = document.getElementById("pwd1").value;

    if (pwd1 == pwd2){
        document.getElementById("register-btn").disabled = false;
    }else {
        document.getElementById("register-btn").disabled = true;
        alert("两次密码输入不相同")
    }
}

// 密码确认恢复器
function pwdRecover() {
    document.getElementById("register-btn").disabled = false;
}
//密码修改提交
function updatePwdForm() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        url: "UpdateUserInfoServlet" ,//url
        data: $('#updatePwdForm').serialize(),
        success: function (result) {
                alert(result);
        },
        error : function(err) {
            alert("网络异常！");
        }
    });
}
function deleteBlog(bid){

    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        url: "deleteBlog?bid="+bid ,//url
        //data: $('#updatePwdForm').serialize(),
        success: function (result) {
            if (result.RESP_CODE == '0000'){
                // 成功
                alert(result.RESP_DESC);
                window.location.href = "QryUserCollectBlogList4JspServlet?type=all";
            }else {
                // 失败
                alert(result.RESP_DESC);
            }
        },
        error : function(err) {
            alert("网络异常！");
        }
    });
}
