// run summernote
$(document).ready(function() {
    $('#summernote').summernote();
});
$(function () {
    var bid = base.GetQueryString("bid");
    base.commonAjax('getBlogFullContent','bid='+bid,function (data) {
        if(data.RESP_CODE == '0000'){
            $('#blogtitle').attr('placeholder',data.RESP_DATA.title);
            $('#summernote').attr('placeholder',data.RESP_DATA.content);
        }
    });
})