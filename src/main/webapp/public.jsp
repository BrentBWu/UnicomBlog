<%--
  Created by IntelliJ IDEA.
  author: Bowen Wu   Xueting ou
  Date: 30/06/2019
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()
            +":"+request.getServerPort()+request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>public</title>
    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
          rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">
    <!--Custom css-->
    <link rel="stylesheet" href="CSS/myblog.css" type="text/css">
    <link rel="stylesheet" href="CSS/public.css" type="text/css">
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
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="QryUserBlogList4JspServlet?uid=${user.uid}">个人主页</a>
        </li>
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="FollowUserListServlet">关注列表</a>
        </li>
        <!-- <li class="sidebar-nav-item">
                <a class="js-scroll-trigger" href="#footerInfo">关于我们</a>
            </li> -->
        <li class="sidebar-nav-item">
            <a class="js-scroll-trigger" href="#foot-info">版权所有</a>
        </li>
    </ul>
</nav>
<!--head for public html-->

<!--this is scroll group-->
<!--what is right-->
<header>
    <div class="navbar navbar-dark bg-dark">
        <div class="container d-flex justify-content-between">
            <a href="QryUserBlogList4JspServlet?uid=${user.uid}" class="navbar-brand d-flex align-items-center text-warning">
                <strong>My Blog.</strong>
            </a>
            <div class="col-4 d-flex justify-content-end align-items-center">
                <nav class="navbar navbar-light">
                    <a href="searchpage.jsp"><button class="btn btn-warning my-2 my-sm-0 fa fa-search btn-lg" aria-hidden="true"
                                                     type="submit"></button></a>
                </nav>
            </div>
        </div>
    </div>
</header>

<!--slide part 轮播图-->
<main role="main" data-gr-c-s-loaded="true">

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class=""></li>
            <li data-target="#myCarousel" data-slide-to="1" class=""></li>
            <li data-target="#myCarousel" data-slide-to="2" class="active"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item">
                <img class="slidegroup first-slide" src="Img/bg-masthead.jpg" alt="First slide">
                <div class="container">
                    <div class="carousel-caption text-left">
                        <h1>Example headline.</h1>

                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img class="slidegroup second-slide" src="Img/1.jpg" alt="Second slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Another example headline.</h1>
                    </div>
                </div>
            </div>
            <div class="carousel-item active">
                <img class="slidegroup third-slide" src="Img/3.jpg" alt="Third slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>One more for good measure.</h1>

                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#myCarousel"
           role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel"
           role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</main>

<!--&lt;!&ndash;jumbotro&ndash;&gt;-->

<!--    <div class="jumbotron p-4 p-md-5 text-dark rounded bg-warning">-->
<!--        <div class="col-md-6 px-0">-->
<!--            <h1 class="display-4 font-italic">Title of a longer featured blog post</h1>-->
<!--            <p class="lead my-3">Multiple lines of text that form the lede, informing new readers quickly and-->
<!--                efficiently about what’s most interesting in this post’s contents.</p>-->
<!--            <p class="lead mb-0"><a href="https://getbootstrap.com/docs/4.3/examples/blog/#"-->
<!--                                    class="text text-dark font-weight-bold">Continue reading...</a></p>-->
<!--        </div>-->
<!--    </div>-->
<div class="album py-5 bg-light myblogs">
    <div class="container">
        <div class="row">

            <c:if test="${empty hightBlogs}">
                <p>暂无文章</p>
            </c:if>
            <c:if test="${not empty hightBlogs}">
                <c:forEach items="${hightBlogs}" var="hightBlog">

                    <div class="col-md-4">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title font-weight-bold">${hightBlog.title}</h5>
                                <p class="card-text">${hightBlog.content}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a href="view.jsp?bid=${hightBlog.bid}"><button type="button" class="btn btn-sm btn-outline-secondary">View</button></a>
                                    </div>
                                    <small class="text-muted">9 mins</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </c:if>


        </div>
    </div>
</div>

<footer>
    <div class="footerInfo" id="foot-info">
        <i class="fa fa-copyright" aria-hidden="true">${user.nickName}</i>
    </div>
</footer>


<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="JS/myblog.js" type="text/javascript" charset="utf-8"></script>
<script src="JS/public.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>
