<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>test - System</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">

    </style>
</head>
<body>
<div class="container">

    <ul class="nav nav-tabs" role="tablist" id="myTab">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">Home</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Profile</a></li>
        <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Messages</a>
        </li>
        <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Settings</a>
        </li>
    </ul>

    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="home">1</div>
        <div role="tabpanel" class="tab-pane" id="profile">2</div>
        <div role="tabpanel" class="tab-pane" id="messages">3</div>
        <div role="tabpanel" class="tab-pane" id="settings">4</div>
    </div>

    <script>
        function formatTime(millisecond) {
            var now = new Date();
            if(now.getTimezoneOffset() != -480){
                millisecond = millisecond + 12*60*1000;
            }

            var date = new Date(millisecond);
            var dateStr = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
                    + ' ' + date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();

            return dateStr;
        }              //1429438916000

        alert(formatTime(1429391898000));
//        $(function () {
//            $('#myTab a:last').tab('show')
//        })
    </script>



</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!--<script src="http://cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>-->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script type="text/javascript">
//    function bind_form_validate() {
//        $("#login_form").validate({
////            errorClass: "tip",
//            rules: {
//                username: { required: true },
//                password: { required: true }
//            },
//            messages: {
//                username: {required: "请填入用户名！"},
//                password: {required: "请填入密码！"}
//            },
//            errorPlacement: function (error, element) {
//                error.appendTo(".tip");
//            }
//        });
//    }
//
//    $(function () {
//        bind_form_validate();
//    });
</script>
</body>
</html>
