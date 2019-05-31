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
