// run summernote
$(document).ready(function() {
    $('#summernote').summernote({
        height: 350,
        minHeight: 100,
        maxHeight: 700,
        focus: true,
        lang:'zh-CN',
    });
});
// $('#summernote').summernote('code', markupStr);

$(function () {
    var bid = base.GetQueryString("bid");
    base.commonAjax('getBlogFullContent','bid='+bid,function (data) {
        if(data.RESP_CODE == '0000'){

            document.getElementById("blogtitle").value = data.RESP_DATA.title;
            /*$('#blogtitle').attr('code',data.RESP_DATA.title);*/
            $('#summernote').summernote('code',data.RESP_DATA.content);
            console.log("123");
        }
    });
});
function blogUpdateSubmit(uid){
    var bid = base.GetQueryString("bid");
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        url: "modifyBlog?bid="+bid ,//url
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