<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>Personal Page</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/album/">

    <!-- Bootstrap core CSS -->
    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic"
          rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="CSS/personalpage.css">
</head>
<body data-gr-c-s-loaded="true">
<header>

    <div class="navbar navbar-dark bg-warning">
        <div class="container d-flex justify-content-between">
            <a href="#" class="navbar-brand d-flex align-items-center text-dark">
                <strong>Unicom Blog.</strong>
            </a>
<%--            <button class="navbar-toggler collapsed bg-dark d-flex justify-content-center align-items-center info-changer"--%>
<%--                    type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader"--%>
<%--                    aria-expanded="false" aria-label="Toggle navigation">--%>
<%--                <span class="text-warning font-weight-bold">资料修改</span>--%>
<%--            </button>--%>
        </div>
    </div>
<%--    <div class="bg-warning collapse" id="navbarHeader">--%>
<%--        <div class="container">--%>
<%--            <div class="row d-flex justify-content-center align-items-center changer-form">--%>
<%--                <form id="updatePwdForm">--%>
<%--                    <div class="form-group">--%>
<%--                        <label class="font-weight-bold text-dark font-weight-bold">原始密码</label>--%>
<%--                        <input type="password" class="form-control" id="oldpwd" name="oldPassword" placeholder="请输入旧密码" >--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label class="font-weight-bold text-dark font-weight-bold">密码</label>--%>
<%--                        <input type="password" class="form-control" id="pwd" name="password1" placeholder="请输入新密码" onblur="checkNewPass()">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label class="font-weight-bold text-dark font-weight-bold">确认密码</label>--%>
<%--                        <input type="password" class="form-control" id="pwd1" name="password2"  placeholder="请确认密码"--%>
<%--                               onkeypress="pwdRecover()">--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label class="font-weight-bold text-dark font-weight-bold">昵称</label>--%>
<%--                        <input type="text" class="form-control" id="text" name="nickname" placeholder="请输入昵称">--%>
<%--                        <button type="button" class="btn btn-dark font-weight-bold changer-footer" id="register-btn"--%>
<%--                                onmouseover="pwdVlidator()" onclick="updatePwdForm()">确认修改--%>
<%--                        </button>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
</header>

<main role="main">

    <section class="jumbotron bg-dark shadow-sm">
        <div class="container d-flex justify-content-center align-items-center bg-dark">
            <div class="btn btn-warning userImg"><a href="#"><img src="Img/userImg.png" alt=""></a></div>
        </div>
        <div class="container text-center user-info bg-dark">
            <div class="user-username text-light">用户名：${user.userName}</div>
            <div class="user-nickname text-light">昵称：${user.nickName}</div>
        </div>
    </section>

    <div class="album py-5 bg-light myblogs">
        <div class="container">
            <div class="text-dark font-weight-bold myblogstitle">我的文章</div>
            <div class="row">
                    <c:if test="${empty UserBlogList}">
                        <p>无文章</p>
                    </c:if>
                    <c:if test="${not empty UserBlogList}">
                        <c:forEach items="${UserBlogList}" var="userBlog"  end="2">
                            <div class="col-md-4">
                                <div class="card mb-4 shadow-sm">

                                    <div class="card-body">
                                        <h5 class="card-title font-weight-bold">${userBlog.title}</h5>
                                        <p class="card-text">${userBlog.content}</p>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                                                <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                                                <button type="button" class="btn btn-sm btn-danger btn-outline-secondary text-light" onclick="deleteBlog(${userBlog.bid})">Delete</button>
                                            </div>
                                            <small class="text-muted">9 mins</small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>



            </div>
            <div class="text-dark font-weight-bold myblogstitle">我的收藏</div>
            <div class="row">
                <c:if test="${empty collectionBolgList}">
                    <p>无收藏文章</p>
                </c:if>
                <c:if test="${not empty collectionBolgList}">
                    <c:forEach items="${collectionBolgList}" var="userBlog"  end="2">
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


    </div>

</main>

<footer class="bg-dark">
    <div class="footerInfo" id="foot-info">
        <i class="fa fa-copyright" aria-hidden="true">用户名</i>
    </div>
</footer>
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="JS/personalpage.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>