
<!--  Created by IntelliJ IDEA.-->
<!--  User: Bowen Wu, Xueting Ou-->
<!--  Date: 1/06/2019-->
<!--  Time: 10:04 AM-->
<!--  To change this template use File | Settings | File Templates.-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()
+":"+request.getServerPort()+request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>publish</title>
    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
          rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">
    <!--Custom css-->
    <link rel="stylesheet" href="CSS/publish.css" type="text/css">
    <!--summernote-->
    <link href="vendor/dist/summernote-bs4.css" rel="stylesheet">
</head>
<body>
<div class="navbar navbar-dark bg-warning">
    <div class="container d-flex justify-content-between">
        <a href="#" class="navbar-brand d-flex align-items-center text-dark">
            <strong>Unicom Blog.</strong>
        </a>
        <button class="navbar-toggler collapsed bg-dark d-flex justify-content-center align-items-center info-changer text-warning"
                type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader"
                aria-expanded="false" aria-label="Toggle navigation">
            ${user.nickName}
        </button>
    </div>
</div>

<!--<div id="summernote">Hello Summernote</div>-->

<form id="blogForm">
    <div class="form-group blog-title">
        <label class="font-weight-bold text-dark font-weight-bold">文章标题</label>
        <input type="text"  class="form-control" id="blogtitle" name="title" placeholder="请输入文章标题" required>
    </div>
    <div class="form-group">
        <textarea id="summernote" name="content"></textarea>
    </div>
    <div class="d-flex justify-content-center align-items-center">
    <button type="button" class="btn btn-warning font-weight-bold text-dark" id="confirm-btn" onclick="blogFormSubmit(${user.uid})">确认发布</button>
    </div>
</form>


<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="JS/publish.js" type="text/javascript" charset="utf-8"></script>
<!-- include summernote css/js -->

<script src="vendor/dist/summernote-bs4.js"></script>


</body>
</html>
