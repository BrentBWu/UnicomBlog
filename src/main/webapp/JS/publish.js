// run summernote
$(document).ready(function() {
    $('#summernote').summernote();
});
function blogFormSubmit(uid){
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        url: "publishBlog" ,//url
        data: $('#blogForm').serialize(),
        success: function (result) {
            if (result.RESP_CODE == '0000'){
                // 成功
                window.location.href = "QryUserBlogList4JspServlet?uid="+uid;
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