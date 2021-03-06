$(function () {
    var bid = base.GetQueryString("bid");
    base.commonAjax('getBlogFullContent','bid='+bid,function (data) {
        if(data.RESP_CODE == '0000'){
            $('#title').text(data.RESP_DATA.title);
            $('#createDate').text(data.RESP_DATA.createTime);
            $('#author').text(data.RESP_DATA.nickName);
            $('#content').append(data.RESP_DATA.content);
            $('#username').text(data.RESP_DATA.nickName);
            var hrefurl = 'QryUserBlogList4JspServlet?uid='+data.RESP_DATA.uid;
            $('#username').attr('href',hrefurl);
            $('#like').text("点赞数："+data.RESP_DATA.likesCount);
        }
    });
})
function likeBolg() {
    var bid = base.GetQueryString("bid");
    console.log(bid);
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        url: "likeBlog?bid="+bid ,//url
        //data: $('#blogForm').serialize(),
        success: function (result) {
            if (result.RESP_CODE == '0000'){
                alert(result.RESP_DESC);
                $('#like').text("点赞数："+result.RESP_DATA);
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


function getUrlParam(name){
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}


function CollectArticle(){
    (function ($) {
        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }
    })(jQuery);
    var bid = $.getUrlParam('bid');
    console.log(bid);
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        url: "chkCollectBlog?bid="+bid ,//url
        //data: $('#blogForm').serialize(),
        success: function (result) {
            if (result.RESP_DATA == false){//未关注过
                // 成功
                $.ajax({
                    //几个参数需要注意一下
                    type: "POST",//方法类型
                    url: "collectBlog?bid="+bid ,//url
                    //data: $('#blogForm').serialize(),
                    success: function (result) {
                        if (result.RESP_DATA == false){//未关注过
                            alert(result.RESP_DESC);
                        }else {
                            // 失败
                            alert(result.RESP_DESC);
                        }
                    },
                    error : function(err) {
                        alert("网络异常！");
                    }
                });

            }else {
                // 失败
                alert("已收藏过");
            }
        },
        error : function(err) {
            alert("网络异常！");
        }
    });
}

