<%--
  Created by IntelliJ IDEA.
  User: Ridiculous
  Date: 2016/5/27
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Detail</title>
    <link href="<%=path%>/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet">
<body>

<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form role="form" action="login.do" method="post">
                <div class="form-group">
                    <label for="username">USERNAME</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username">
                </div>
                <div class="form-group">
                    <label for="password">PASSWORD</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary btn-block">LOGIN</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>

</body>
</html>
