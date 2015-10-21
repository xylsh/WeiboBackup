<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Dashboard - System</title>

    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="../resources/css/dashboard.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<!--导航栏-->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="./dashboard.htm">Dashboard</a>
        </div>

        <div class="collapse navbar-collapse" id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Sign out</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
<div class="row">
<!--左边siderbar-->
<div class="col-sm-3 col-md-2 sidebar">
    <ul id="navTabList1" class="nav  nav-sidebar" role="tablist">
        <li role="presentation" class="active">
            <a id="overviewTab" href="#overview" aria-controls="overview" role="tab" data-toggle="tab">Overview
                <span class="sr-only">(current)</span></a>
        </li>
        <li role="presentation">
            <a id="presentationTab" href="#presentation" aria-controls="presentation" role="tab"
               data-toggle="tab">Presentation</a>
        </li>
        <li role="presentation">
            <a id="settingsTab" href="#settings" aria-controls="settings" role="tab"
               data-toggle="tab">Settings</a>
        </li>
        <li><a href="#">About</a></li>
    </ul>
    <!--<ul class="nav nav-sidebar">-->
    <!--<li><a href="">Nav item</a></li>-->
    <!--<li><a href="">Nav item again</a></li>-->
    <!--</ul>-->
</div>

<!--右边主内容-->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
<!--siderbar对应选项卡内容-->
<div class="tab-content">
<!--overview选项卡内容-->
<div id="overview" role="tabpanel" class="tab-pane fade in active">
    <h1 class="page-header">Overview</h1>

    <div class="row">
        <div class="col-xs-6 col-sm-3">
            <ul class="list-group">
                <li class="list-group-item">监控状态：<span class="right">正在监控</span></li>
                <li class="list-group-item">已加入监控：<span class="right">10人</span></li>
                <li class="list-group-item">正在监控：<span class="right">5人</span></li>
                <li class="list-group-item">总备份微博数：<span class="right">510条</span></li>
            </ul>
        </div>
    </div>
</div>
<!--presentation选项卡内容-->
<div id="presentation" role="tabpanel" class="tab-pane fade">
<h1 class="page-header">Presentation</h1>

<div class="row">
<!--列出所有监控的人-->
<div class="col-md-3">
    <ul id="monitorTabList" class="nav nav-pills nav-stacked">
        <li role="presentation" class="active">
            <a id="1monitorTab" href="#1monitor" aria-controls="1monitor" role="tab" data-toggle="tab">
                互联网的那些事</a></li>
        <li role="presentation">
            <a id="2monitorTab" href="#2monitor" aria-controls="2monitor" role="tab" data-toggle="tab">
                老赵</a></li>
        <li role="presentation">
            <a id="3monitorTab" href="#3monitor" aria-controls="3monitor" role="tab" data-toggle="tab">
                Easy</a></li>
    </ul>
</div>
<!--列出当前人的微博-->
<div class="col-md-8">

    <div id="weiboListPanel" class="tab-content">
        <!--1monitor对应内容-->
        <div id="1monitor" role="tabpanel" class="tab-pane fade in active">
            <div class="panel panel-info">
                <div class="panel-heading">互联网的那些事
                    <a href="#"><span class="label label-primary">刷新</span></a>
                    <a href="#"><span class="label label-primary">导出</span></a>
                    <a class="stop-monitor-button" monitorid="-1" href="#"><span class="label label-primary">停止监控</span></a>
                    <a href="#"><span class="label label-danger">删除</span></a>
                </div>
                <div class="panel-body">
                    <!--1monitor微博列表-->
                    <div class="media">
                        <div class="media-left">
                            <a href="#"><img class="media-object" src="holder.js/64x64/auto/sky" alt="..."></a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">互联网的那些事</h4>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
                            scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum
                            in vulputate at, tempus viverra turpis.
                            <!--嵌套媒体对象-->
                            <div class="media">
                                <div class="media-left">
                                    <a href="#">
                                        <img class="media-object" src="holder.js/64x64/auto/sky"
                                             alt="...">
                                    </a>
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">留几手</h4>
                                    Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
                                    scelerisque ante sollicitudin commodo. Cras purus odio,
                                    vestibulum in vulputate at, tempus viverra turpis.
                                    <!--图片-->
                                    <div class="row">
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div><!--嵌套媒体对象--结束-->
                        </div>
                    </div>
                    <!--1monitor微博列表--结束-->
                    <!--分页-->
                    <nav class="weibo-list-page-nave">
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <!--分页结束-->
                </div>
            </div>
        </div>
        <div id="2monitor" role="tabpanel" class="tab-pane fade">
            <div class="panel panel-info">
                <div class="panel-heading">老赵</div>
                <div class="panel-body">
                    <!--2monitor微博列表-->
                    <div class="media">
                        <div class="media-left">
                            <a href="#">
                                <img class="media-object" src="holder.js/64x64/auto/sky"
                                     alt="...">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">老赵</h4>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
                            scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum
                            in vulputate at, tempus viverra turpis.
                            <!--嵌套媒体对象-->
                            <div class="media">
                                <div class="media-left">
                                    <a href="#">
                                        <img class="media-object" src="holder.js/64x64/auto/sky"
                                             alt="...">
                                    </a>
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">留几手</h4>
                                    Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
                                    scelerisque ante sollicitudin commodo. Cras purus odio,
                                    vestibulum in vulputate at, tempus viverra turpis.
                                    <!--图片-->
                                    <div class="row">
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <!--嵌套媒体对象--结束-->
                        </div>
                    </div>
                    <!--分页-->
                    <nav class="weibo-list-page-nave">
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <!--分页结束-->
                    <!--2monitor微博列表--结束-->
                </div>
            </div>
        </div>
        <div id="3monitor" role="tabpanel" class="tab-pane fade">
            <div class="panel panel-info">
                <div class="panel-heading">老赵3</div>
                <div class="panel-body">
                    <!--2monitor微博列表-->
                    <div class="media">
                        <div class="media-left">
                            <a href="#">
                                <img class="media-object" src="holder.js/64x64/auto/sky"
                                     alt="...">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">老赵</h4>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
                            scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum
                            in vulputate at, tempus viverra turpis.
                            <!--嵌套媒体对象-->
                            <div class="media">
                                <div class="media-left">
                                    <a href="#">
                                        <img class="media-object" src="holder.js/64x64/auto/sky"
                                             alt="...">
                                    </a>
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">留几手</h4>
                                    Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
                                    scelerisque ante sollicitudin commodo. Cras purus odio,
                                    vestibulum in vulputate at, tempus viverra turpis.
                                    <!--图片-->
                                    <div class="row">
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-3 weibo-pic">
                                            <a href="#" class="thumbnail">
                                                <img src="holder.js/64x64/auto/vine" alt="...">
                                            </a>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <!--嵌套媒体对象--结束-->
                        </div>
                    </div>
                    <!--分页-->
                    <nav class="weibo-list-page-nave">
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <!--分页结束-->
                    <!--2monitor微博列表--结束-->
                </div>
            </div>
        </div>
    </div>
</div>

</div>
</div>
<!--settings选项卡内容-->
<div id="settings" role="tabpanel" class="tab-pane fade">
    <h1 class="page-header">Settings</h1>

    <div class="row">
        <div class="col-md-3">
            <!--设置功能项列表-->
            <ul id="settingList" class="nav nav-pills nav-stacked">
                <li role="presentation" class="active">
                    <a id="addMonitorTab" href="#addMonitor" aria-controls="addMonitor" role="tab"
                       data-toggle="tab"> 添加监控</a></li>
                <li role="presentation">
                    <a id="controlSettingTab" href="#controlSetting" aria-controls="controlSetting" role="tab"
                       data-toggle="tab">控制</a></li>
            </ul>
        </div>
        <div class="col-md-7">
            <div class="tab-content">
                <!--添加监控面板-->
                <div id="addMonitor" role="tabpanel" class="tab-pane fade in active">
                    <div class="panel panel-info">
                        <div class="panel-heading">添加监控</div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-2">
                                    <!--添加监控的表格-->
                                    <form id="addMonitorForm" action="./addMonitor.json" method="POST">
                                        <div class="form-group">
                                            <label for="nickname">微博昵称</label>
                                            <input id="nickname" name="weiboNickname" type="text" class="form-control"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="weiboUid">微博UID</label>
                                            <input id="weiboUid" name="weiboUid" type="text" class="form-control"/>
                                        </div>
                                        <%--<div class="form-group">--%>
                                            <%--<label for="weiboUrl">微博URL</label>--%>
                                            <%--<input id="weiboUrl" name="weiboUrl" type="text" class="form-control"/>--%>
                                        <%--</div>--%>
                                        <div class="form-group">
                                            <label for="weiboInterfaceUrl">微博接口地址</label>
                                            <input id="weiboInterfaceUrl" name="weiboInterfaceUrl" type="text"
                                                   class="form-control"/>
                                        </div>
                                        <div class="btn-group" data-toggle="buttons">
                                            <label class="btn btn-primary active">
                                                <input type="radio" name="isStop" value="false" id="option1"
                                                       autocomplete="off"
                                                       checked> 开启监控
                                            </label>
                                            <label class="btn btn-primary">
                                                <input type="radio" name="isStop" value="true" id="option2"
                                                       autocomplete="off">
                                                关闭监控
                                            </label>
                                        </div>

                                        <button id="resetForm" type="button" class="btn btn-link">重置表格</button>
                                        <p><span class="tip"></span></p>
                                        <button id="addMonitorFormSubmitButton" type="submit"
                                                class="btn btn-primary btn-block" onclick="">添加
                                        </button>

                                    </form>
                                    <!--添加监控的表格--end-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--控制设置面板-->
                <div id="controlSetting" role="tabpanel" class="tab-pane fade">
                    <div class="panel panel-info">
                        <div class="panel-heading">控制</div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-8 col-md-offset-2">
                                    <!--停止当前备份线程的按钮-->
                                    <button id="stopCurrentBackup" type="button" class="btn btn-primary">停止当前正在进行的备份</button>
                                    <!--停止当前备份线程的按钮--end-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

</div>
</div>
</div>
</div>


<%--<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>--%>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/holder/2.6.0/holder.min.js"></script>
<script src="../resources/js/dashboard.js"></script>
</body>
</html>
