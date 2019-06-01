<%--
  Created by IntelliJ IDEA.
  User: brent
  Date: 30/05/2019
  Time: 9:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()
            +":"+request.getServerPort()+request.getContextPath();
%>
<!DOCTYPE html lang="en">
<html>
<head>
    <meta charset="UTF-8">
    <title>Unicom Blog system</title>
    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
          rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">

    <!--Custom CSS-->
    <link rel="stylesheet" type="text/css" href="CSS/login.css"/>
</head>

<body id="page-top">
<!--Navigation-->
<a class="menu-toggle rounded" href="#">
    <i class="fas fa-bars"></i>
</a>
<nav id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <a class="js-scroll-trigger" href="#page-top">Unicom Blog</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="login-btn" href="#" data-toggle="modal" data-target="#loginPopUp">用户登陆</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="register" href="#" data-toggle="modal" data-target="#registerPopUp">用户注册</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#about">随便看看</a>
        </li>
        <!-- <li class="sidebar-nav-item">
                <a class="js-scroll-trigger" href="#footerInfo">关于我们</a>
            </li> -->
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#footerInfo">版权所有</a>
        </li>
    </ul>
</nav>

<header class="masthead d-flex">
    <div class="container text-center my-auto">
        <h1 class="mb-1">Unicom Blog System</h1>
        <h3 class="mb-5">
            书写你的简单生活
        </h3>
        <a class="btn btn-primary btn-xl js-scroll-trigger" id="lookaround" href="#about">随便看看</a>
    </div>
    <div class="overlay"></div>
</header>

<!--modal登录模块 -->
<div class="modal fade" id="loginPopUp" role="dialog" aria-labelledby="loginPopUp" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <!-- <h5 class="modal-title" id="loginPopUp">用户登陆</h5> -->
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- 登陆表单 -->
                <form action="<%=basePath%>/LoginServlet" method="get">
                    <div class="form-group">
                        <label for="userId" class="font-weight-bold">用户名</label>
                        <input type="text" class="form-control" name="username" placeholder="请输入用户名"
                               pattern="[a-zA-z]{6-12}" required>
                    </div>
                    <div class="form-group">
                        <label class="font-weight-bold">密码</label>
                        <input type="password" class="form-control" name="password" placeholder="请输入密码"
                               pattern="[0-9]{6,}" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary" value="submit">登录</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<!-- modal注册模块 -->
<div class="modal fade" id="registerPopUp" role="dialog" aria-labelledby="registerPopUp" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- 注册表单 -->
                <form action="<%=basePath%>/RegistServlet" method="get">
                    <div class="form-group">
                        <label for="userId" class="font-weight-bold">用户名</label>
                        <input type="text" class="form-control" id="userId" name="username" placeholder="请输入用户名">
                    </div>
                    <div class="form-group">
                        <label class="font-weight-bold">密码</label>
                        <input type="password" class="form-control" id="pwd" name="password"  placeholder="请输入密码">
                    </div>
                    <div class="form-group">
                        <label class="font-weight-bold">确认密码</label>
                        <input type="password" class="form-control" id="pwd1" placeholder="请确认密码" onkeypress="pwdRecover()">
                    </div>
                    <div class="form-group">
                        <label class="font-weight-bold">昵称</label>
                        <input type="text" class="form-control" id="text" name="nickname" placeholder="请输入昵称">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary" id="register-btn" onmouseover="pwdVlidator()">注册</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<!--blog information-->

<div class="container" id="about">
    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-center align-items-center">
            <div class="col-4 text-center  justify-content-center align-items-center">
                <div class="blog-header-logo text-dark">Unicom</div>
            </div>
        </div>
    </header>

    <div class="nav-scroller py-1 mb-2">
    </div>

    <div class="jumbotron p-4 p-md-5 text-white rounded">
        <div class="col-md-6 px-0">
            <h1 class="display-4 font-italic">Title of a longer featured blog post</h1>
            <p class="lead my-3">Multiple lines of text that form the lede, informing new readers quickly and
                efficiently about what’s most interesting in this post’s contents.</p>
            <p class="lead mb-0"><a href="https://getbootstrap.com/docs/4.3/examples/blog/#"
                                    class="text-white font-weight-bold">Continue reading...</a></p>
        </div>
    </div>

    <div class="row mb-2">
        <div class="col-md-6">
            <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                    <strong class="d-inline-block mb-2 inline-title">World</strong>
                    <h3 class="mb-0">Featured post</h3>
                    <div class="mb-1 text-muted">Nov 12</div>
                    <p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to
                        additional content.</p>
                </div>
                <!--read more btn-->
                <button href="#" class="btn btn-primary justify-content-center align-items-center read-more">
                    Read More
                </button>
            </div>
        </div>
        <div class="col-md-6">
            <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                    <strong class="d-inline-block mb-2 inline-title">Design</strong>
                    <h3 class="mb-0">Post title</h3>
                    <div class="mb-1 text-muted">Nov 11</div>
                    <p class="mb-auto">This is a wider card with supporting text below as a natural lead-in to
                        additional content.</p>
                </div>
                <!-- read more btn-->
                <button href="#" class="btn btn-primary justify-content-center align-items-center read-more">
                    Read More
                </button>

            </div>
        </div>
    </div>
</div>


<footer id="footerInfo">
    <div class="footerInfo">
        <i class="fa fa-copyright" aria-hidden="true">后端开发第二组</i>
    </div>
</footer>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="JS/login.js" type="text/javascript" charset="utf-8"></script>

</body>

</html>