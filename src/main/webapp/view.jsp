<!--  Created by IntelliJ IDEA.-->
<!--  User: Bowen Wu, Xueting Ou-->
<!--  Date: 1/06/2019-->
<!--  Time: 10:04 AM-->
<!--  To change this template use File | Settings | File Templates.-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>myblog</title>
    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
          rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">
    <!--Custom css-->
    <link rel="stylesheet" href="CSS/myblog.css" type="text/css">
</head>

<body class="bg-light">
<!--Navigation-->
<a class="menu-toggle rounded" href="#">
    <i class="fas fa-bars"></i>
</a>

<nav class="bg-dark" id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <a class="js-scroll-trigger" href="#page-top">Unicom Blog</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#page-main">文章主页</a>
        </li>
        <c:if test="${not empty user}">
            <li class="sidebar-nav-item">
                <a class="js-scroll-trigger" href="QryUserCollectBlogList4JspServlet?type=all">个人主页</a>
            </li>
        </c:if>

        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#">关注列表</a>
        </li>
        <!-- <li class="sidebar-nav-item">
                <a class="js-scroll-trigger" href="#footerInfo">关于我们</a>
            </li> -->
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#foot-info">版权所有</a>
        </li>
    </ul>
</nav>

<div class="container" id="page-top">
    <header class="blog-header py-3">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="col-4 pt-1">
                <a class="btn btn-warning btn-lg fa fa-heart" href="#"></a>
            </div>
            <div class="col-4 text-center">
                <a class="blog-header-logo text-dark" href="#" id="username"></a>
            </div>
            <div class="col-4 d-flex justify-content-end align-items-center">
                <nav class="navbar navbar-light bg-light">
                    <form class="form-inline">
                        <input class="form-control mr-sm-2" type="search" placeholder="搜索" aria-label="Search">
                        <button class="btn btn-warning my-2 my-sm-0 fa fa-search btn-lg" aria-hidden="true"
                                type="submit"></button>
                    </form>
                </nav>
            </div>
        </div>
    </header>
    <div class="nav-scroller py-1 mb-2">
    </div>
</div>

<main role="main" class="container" id="page-main">
    <div class="row">
        <div class="blog-main">

            <div class="blog-post">
                <h2 class="blog-post-title" id="title"></h2>
                <p class="blog-post-meta" id="createDate">
                    <a href="https://getbootstrap.com/docs/4.3/examples/blog/#" id="author">ff</a>
                </p>

                <hr>
                <p id="content"></p>
                <p id="like"></p>

            </div><!-- /.blog-post -->
        </div><!-- /.blog-main -->

    </div><!-- /.row -->

</main><!-- /.container -->

<div class="container btn-like">
    <button class="btn btn-warning btn-lg btn-circle" id="like-icon" onclick="likeBolg()">
        <i class="fa fa-2x fa-thumbs-up" aria-hidden="true"></i>
    </button>
</div>

<footer>
    <div class="footerInfo" id="foot-info">
        <i class="fa fa-copyright" aria-hidden="true">用户名</i>
    </div>
</footer>


<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="JS/myblog.js" type="text/javascript" charset="utf-8"></script>
<script src="JS/common.js" type="text/javascript" charset="utf-8"></script>
<script src="JS/view.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>