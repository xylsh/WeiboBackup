<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Log in - System</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="resources/css/login.css"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">

    <div class="panel panel-default">
        <!--<div class="panel-heading">Log in</div>-->
        <div class="panel-body">
            <div class="page-header">
                <h2> 登陆 </h2>
            </div>
            <form id="loginForm" action="./login/login.action" method="POST">
                <p><input id="username" name="username" type="text" class="form-control" placeholder="Username"/></p>

                <p><input id="password" name="password" type="password" class="form-control" placeholder="Password"></p>

                <p><span class="tip"></span></p>
                <button type="submit" class="btn btn-primary btn-block btn-lg" onclick="">登陆</button>
            </form>

        </div>
    </div>


</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!--<script src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>-->
<%--<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>--%>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="resources/js/login.js"></script>
</body>
</html>
