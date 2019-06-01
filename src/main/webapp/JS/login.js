/**
 * 
 */
(function($) {
  "use strict"; // Start of use strict

  // Closes the sidebar menu
  $(".menu-toggle").click(function(e) {
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
//注册表单提交
function registForm(){
  $.ajax({
    //几个参数需要注意一下
    type: "POST",//方法类型
    url: "RegistServlet" ,//url
    data: $('#registForm').serialize(),
    success: function (result) {
      alert(result);
    },
    error : function(err) {
      alert("网络异常！");
    }
  });
}
//登陆模块验证
/*function loginUser() {

  var username = document.getElementById("loginUsername").value;
  if(username.length<6||username.length>12){
    alert("用户名长度6-12位");
  }
}
function loginPass() {
  var password = document.getElementById("loginPassword").value;
  if(password.length<6||password.length>20){
    alert("密码长度6-20位");
  }
}*/
//登陆表单提交
function loginFormSubmit(){
  $.ajax({
    //几个参数需要注意一下
    type: "POST",//方法类型
    url: "LoginServlet" ,//url
    data: $('#loginForm').serialize(),
    success: function (result) {
      if (result == '登陆成功'){
        window.location.href = "public.jsp";
      }else{
        alert(result);
      }
    },
    error : function(err) {
      alert("网络异常！");
    }
  });
}

