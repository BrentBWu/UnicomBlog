<%--
  Created by IntelliJ IDEA.
  User: User: Bowen Wu, Xueting Ou
  Date: 30/05/2019
  Time: 9:06 PM
  To change this template use File | Settings | File Templates.
--%>
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
            <a class="js-scroll-trigger" href="#page-main" >文章主页</a>
        </li>
        <li class="sidebar-nav-item">
                <a class="js-scroll-trigger" href="QryUserBlogList4JspServlet?uid=${user.uid}">个人主页</a>
        </li>
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
                <a class="btn btn-lg btn-dark" href="publish.jsp"
                   role="button">发表博文</a>
            </div>
            <div class="col-4 text-center">
                <c:if test="${user.uid == authorId}">
                    <a class="blog-header-logo text-dark" href="QryUserCollectBlogList4JspServlet?type=all">${nickName}</a>
                </c:if>
                <c:if test="${user.uid != authorId}">
                 <a class="blog-header-logo text-dark" href="#">${user.nickName}</a>
                </c:if>
            </div>
            <div class="col-4 d-flex justify-content-end align-items-center">
                <nav class="navbar navbar-light bg-light">
                    <form class="form-inline">
                        <input class="form-control mr-sm-2" type="search" placeholder="搜索" aria-label="Search">
                        <button class="btn btn-warning my-2 my-sm-0 fa fa-search btn-lg" aria-hidden="true" type="submit"></button>
                    </form>
                </nav>
            </div>
        </div>
    </header>
    <div class="nav-scroller py-1 mb-2">
    </div>

    <c:if test="${empty UserBlogList}">
        <p>暂无文章</p>
    </c:if>
    <c:if test="${not empty UserBlogList}">
        <c:forEach items="${UserBlogList}" var="userBlog" begin="0" end="0">

            <div class="jumbotron p-4 p-md-5 text-dark rounded bg-warning">
                <div class="col-md-6 px-0">
                    <h1 class="display-4 font-italic">${userBlog.title }</h1>
                    <p class="lead my-3">${userBlog.content }</p>
                    <p class="lead mb-0"><a href="view.jsp?bid=${userBlog.bid}"
                                            class="text text-dark font-weight-bold">Continue reading...</a></p>
                </div>
            </div>
        </c:forEach>

    </c:if>

    <c:if test="${length>1}">
        <div class="row mb-2">
            <c:forEach items="${UserBlogList}" var="userBlog" begin="1" end="2">
                <div class="col-md-6">
                    <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                        <div class="col p-4 d-flex flex-column position-static">
                            <strong class="d-inline-block mb-2 inline-title">Unicom</strong>
                            <h3 class="mb-0">${userBlog.title}</h3>
                            <div class="mb-1 text-muted">${userBlog.createTime}</div>
                            <p class="card-text mb-auto">${userBlog.content}</p>
                        </div>
                        <!--read more btn-->
                        <button class="btn btn-dark justify-content-center align-items-center read-more" onclick="View(${userBlog.bid})">
                            Read More
                        </button>
                    </div>
                </div>

            </c:forEach>
        </div>
    </c:if>
</div>

<main role="main" class="container" id="page-main">
    <div class="row">
        <div class="blog-main">
            <h3 class="pb-4 mb-4 font-italic border-bottom">
                From the Firehose
            </h3>

            <c:if test="${empty UserBlogList}">
                <p>暂无文章</p>
            </c:if>
            <c:if test="${not empty UserBlogList}">
                <c:if test="${length>1}">
                    <c:forEach items="${UserBlogList}" var="userBlog" begin="3" >
                        <div class="blog-post">
                            <h2 class="blog-post-title">${userBlog.title}</h2>
                            <p class="blog-post-meta">${userBlog.createTime}<a
                                    href="QryUserBlogList4JspServlet?uid=${userBlog.uid}">${userBlog.nickName}</a></p>

                            <p>${userBlog.content}</p>
                            <hr>

                        </div>
                     </c:forEach>
                </c:if>
            </c:if>
           <!-- /.blog-post -->

        </div><!-- /.blog-main -->

    </div><!-- /.row -->

</main><!-- /.container -->
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

</body>
</html>
