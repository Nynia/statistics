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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>业务统计系统-登录</title>
    <link href="<%=path%>/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="<%=path%>/resources/css/login_style.css" rel="stylesheet">
<body>

<div class="title">
    <p>业务统计系统</p>
</div>
<main>
    <div class="main">
        <form method="post">
            <ul>
                <li>
                    <label class="l-ico fl">账&#x3000;号：</label>
                    <input id="username" class="fl" name="username" type="text" value=""/>
                </li>
                <li>
                    <label class="l-ico fl">密&#x3000;码：</label>
                    <input id="password" name="password" class="fl" type="password" value=""/>
                </li>
                <li>
                    <label class="l-ico fl">验证码：</label>
                    <input id="captcha" name="captcha" class="fl" type="text" value=""/>
                    <img src="captcha.do" alt="" class="code" id="captcha_img" onclick="return reloadCaptcha()"/>
                </li>
                <div class="clear"></div>
            </ul>
            <div id="warn-msg" class="message">
            </div>

            <button id="submit">登&#x3000;录</button>
        </form>
    </div>
</main>
<script type="application/javascript" src="<%=path%>/resources/js/jquery-1.8.3.min.js"></script>
<script type="application/javascript" src="<%=path%>/resources/js/cryptojslib/rollups/aes.js"></script>
<script type="application/javascript"
        src="<%=path%>/resources/js/cryptojslib/components/pad-zeropadding-min.js"></script>
<script>

    $('#submit').click(function (event) {
        event.preventDefault();
        var username = $('#username').val();
        var captcha = $('#captcha').val();
        var password_plain = $('#password').val();
        console.log(password_plain);
        console.log(captcha);
        var key = CryptoJS.enc.Latin1.parse('1234567812345678');
        var iv = CryptoJS.enc.Latin1.parse('1234567812345678');
        //加密
        var password_cipher = CryptoJS.AES.encrypt(
            password_plain,
            key,
            {
                iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.ZeroPadding
            });
        console.log('encrypted password: ' + password_cipher);

        $.ajax({
            url: 'login.do',
            type: 'POST',
            dataType: 'text',
            data: {
                'username': username,
                'password': password_cipher.toString(),
                'captcha': captcha
            },
            beforeSend: function () {
                console.log("正在进行，请稍候");
            },
            success: function (data) {
                console.log(data);
                if (!(data === 'success')) {
                    $('#warn-msg').html("<img src=\"<%=path%>/resources/img/warn.png\" alt=\"\"/>" + data);
                    reloadCaptcha()
                }
                else {
                    window.location.href = "home.do";
                }

            },
            error: function () {
                alert('Unexpected error');
            }

        });
    });

    function reloadCaptcha() {
        document.getElementById("captcha_img").src = document.getElementById("captcha_img").src + "?nocache=" + new Date().getTime();
    }

    function AESEncrypt() {
        console.log('AES');
        var password_input = document.getElementById('password');
        var password_plain = password_input.value;
        var key = CryptoJS.enc.Latin1.parse('1234567812345678');
        var iv = CryptoJS.enc.Latin1.parse('1234567812345678');
        //加密
        var password_cipher = CryptoJS.AES.encrypt(
            password_plain,
            key,
            {
                iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.ZeroPadding
            });
        console.log('encrypted password: ' + password_cipher);
        password_input.value = password_cipher;
        //return true;
    }
</script>
</body>
</html>
