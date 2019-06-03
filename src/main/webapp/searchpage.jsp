<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>search page</title>
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
    <link rel="stylesheet" href="CSS/searchpage.css" type="text/css">
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
        </div>
    </div>
</header>


<div class="container search-bar d-flex justify-content-center align-items-center">
    <form action="QryBlogListByKeyword4JspServlet" class="row" method="get">
        <input class="form-control mr-lg-2 bg-light input-lg" type="search" style="width:400px;" placeholder="搜索"
               aria-label="Search" name="keyword">
        <button class="btn btn-warning my-2 my-sm-0 fa fa-search btn-lg" aria-hidden="true"
                type="submit"></button>
    </form>
</div>

<%--<% ArrayList list = (ArrayList) request.getAttribute("respData");%>--%>

<h4 class="bg-light font-weight-bold search-bar-title">搜索结果</h4>

<div class="container">
    <c:if test="${empty respData}">
        <div class="d-flex justify-content-center align-items-center">
            <h4 style="font-family: 微软雅黑;">无文章</h4>
        </div>
    </c:if>
    <c:if test="${not empty respData}">
        <c:forEach items="${respData}" var="userBlog" end="2">
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title font-weight-bold">${userBlog.title}</h5>
                        <p class="card-text">${userBlog.content}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
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
<%--<script src="JS/myblog.js" type="text/javascript" charset="utf-8"></script>--%>
<%--<script src="JS/public.js" type="text/javascript" charset="utf-8"></script>--%>
<script src="JS/searchpage.js" type="text/javascript" charset="UTF-8"></script>

</body>
</html>
