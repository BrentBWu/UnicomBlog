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
            <a href="QryUserBlogList4JspServlet?uid=${user.uid}" class="navbar-brand d-flex align-items-center text-dark">
                <strong>My Blog.</strong>
            </a>

        </div>
    </div>
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
            <div class="text-dark font-weight-bold myblogstitle">我的关注列表</div>
            <div class="row">
                    <c:if test="${empty followUserList}">
                        <p>暂无关注人员</p>
                    </c:if>
                    <c:if test="${not empty followUserList}">
                        <c:forEach items="${followUserList}" var="followuser" >
                            <div class="col-md-4">
                                <div class="card mb-4 shadow-sm">
                                    <div class="card-body">
                                        <h5 class="card-title font-weight-bold">${followuser.nickName}</h5>
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
        <i class="fa fa-copyright" aria-hidden="true">${user.nickName}</i>
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