/**
 *
 */
(function($) {
    "use strict"; // Start of use strict

    // Closes the sidebar menu
    $(".menu-toggle").click(function(e) {
        console.log("test");
        e.preventDefault();
        $("#sidebar-wrapper").toggleClass("active");
        $(".menu-toggle > .fa-bars, .menu-toggle > .fa-times").toggleClass("fa-bars fa-times");
        $(this).toggleClass("active");
    });

    // Smooth scrolling using jQuery easing
    $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function() {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            if (target.length) {
                $('html, body').animate({
                    scrollTop: target.offset().top
                }, 1000, "easeInOutExpo");
                return false;
            }
        }
    });


    // Closes responsive menu when a scroll trigger link is clicked
    $('#sidebar-wrapper .js-scroll-trigger').click(function() {
        $("#sidebar-wrapper").removeClass("active");
        $(".menu-toggle").removeClass("active");
        $(".menu-toggle > .fa-bars, .menu-toggle > .fa-times").toggleClass("fa-bars fa-times");
    });

    // Scroll to top button appear
    $(document).scroll(function() {
        var scrollDistance = $(this).scrollTop();
        if (scrollDistance > 100) {
            $('.scroll-to-top').fadeIn();
        } else {
            $('.scroll-to-top').fadeOut();
        }
    });

})(jQuery); // End of use strict

// Disable Google Maps scrolling
// See http://stackoverflow.com/a/25904582/1607849
// Disable scroll zooming and bind back the click event
var onMapMouseleaveHandler = function(event) {
    var that = $(this);
    that.on('click', onMapClickHandler);
    that.off('mouseleave', onMapMouseleaveHandler);
    that.find('iframe').css("pointer-events", "none");
}
var onMapClickHandler = function(event) {
    var that = $(this);
    // Disable the click handler until the user leaves the map area
    that.off('click', onMapClickHandler);
    // Enable scrolling zoom
    that.find('iframe').css("pointer-events", "auto");
    // Handle the mouse leave event
    that.on('mouseleave', onMapMouseleaveHandler);
}
// Enable map zooming with mouse scroll when the user clicks the map
$('.map').on('click', onMapClickHandler);


function View(bid) {
    window.location.href = "view.jsp?bid="+bid;
}
function FollowUser(authorId){
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        url: "ChkFollowUserServlet?uid="+authorId ,//url
        //data: $('#blogForm').serialize(),
        success: function (result) {
            if (result.RESP_CODE == '0000'){//未关注过
                // 成功
                    $.ajax({
                        //几个参数需要注意一下
                        type: "POST",//方法类型
                        url: "FollowUserServlet?uid="+authorId ,//url
                        //data: $('#blogForm').serialize(),
                        success: function (result) {
                            if (result.RESP_CODE == '0000'){//未关注过
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
                alert(result.RESP_DESC);
            }
        },
        error : function(err) {
            alert("网络异常！");
        }
    });
}
