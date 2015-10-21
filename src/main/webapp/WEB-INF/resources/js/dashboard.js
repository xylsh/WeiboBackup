//////////////////////////////////////////////////////
//presentation tab

function registerPresentationTabEvent() {
    $("#presentationTab").click(function (event) {
        event.preventDefault();
        //加载所有监控的人
        loadAllMonitor();
    });
}

function registerMonitorTabEvent() {
    //移除后添加
//    $('#monitorTabList').off('click','a').on('click', 'a', function (event) {
//        alert("test");
//    });
    $('#monitorTabList').on('click', 'a', function (event) {
        var monitorId = $(this).attr("monitorid");
        var url = './getWeiboByMonitorIdWithPage.json?monitorId=' + monitorId + '&pageSize=20';
        $.getJSON(url + '&currPage=1', function (data) {
            if (data.ret === false) {
                alert("请求微博内容出错！\n" + data.errmsg);
                return;
            }
            $('#weiboListPanel div[monitorid=' + monitorId + '] .panel-body').html(createWeiboListHtml(data.data, url, monitorId));
        });
    });
}

//给分页绑定事件
function registerPageNaveEvent() {
    $('#weiboListPanel').on('click', 'nav a', function (event) {
        event.preventDefault();
        var monitorId = $(this).attr("monitorid");
        var url = $(this).attr('href');
        $.getJSON(url+'&currPage='+$(this).text(), function (data) {
            if (data.ret === false) {
                alert("请求微博内容出错！\n" + data.errmsg);
                return;
            }
            $('#weiboListPanel div[monitorid=' + monitorId + '] .panel-body').html(createWeiboListHtml(data.data, url, monitorId));
        });
    });
}

function createWeiboListHtml(data, url, monitorId) {
    var weiboList = data.weiboList;
    var html = "";
    $.each(weiboList, function (key, weibo) {
        //加载主微博
        html += '<!--该条微博开始--><div class="media"><div class="media-left"><a href="#">'
            + '<img class="media-object" src="http://placehold.it/50/9966FF"></a></div>'
            + '<div class="media-body"><h4 class="media-heading">' + weibo.mainWeibo.weiboNickname + '</h4>'
            + weibo.mainWeibo.weiboText
            + '&nbsp;&nbsp;&nbsp;<small><i>[' + formatTime(weibo.mainWeibo.weiboTime) + ']</i></small>';

        //加载主微博图片
        if (weibo.mainWeibo.parsedWeiboPics != null && weibo.mainWeibo.parsedWeiboPics.length > 0) {
            html += '<div class="row">';
            $.each(weibo.mainWeibo.parsedWeiboPics, function (key, currPic) {
                var picSrc = '../resources/pic/' + currPic;   //图片路径
                html += '<div class="col-md-3 weibo-pic">'
                    + '<a href="' + picSrc + '" target="_blank" class="thumbnail"><img src="' + picSrc + '"></a>'
                    + '</div>';
            });
            html += '</div>';
        } else if (weibo.retweetWeibo != null) {
            //加载被转发微博
            html += '<!--嵌套微博--开始--><div class="media"><div class="media-left"><a href="#">'
                + '<img class="media-object" src="http://placehold.it/50/FF0033"></a></div>'
                + '<div class="media-body"><h4 class="media-heading">' + weibo.retweetWeibo.weiboNickname + '</h4>'
                + weibo.retweetWeibo.weiboText
                + '&nbsp;&nbsp;&nbsp;<small><i>[' + formatTime(weibo.retweetWeibo.weiboTime) + ']</i></small>';

            //加载被转发微博图片
            if (weibo.retweetWeibo.parsedWeiboPics != null && weibo.retweetWeibo.parsedWeiboPics.length > 0) {
                html += '<div class="row">'
                $.each(weibo.retweetWeibo.parsedWeiboPics, function (key, currPic) {
                    var picSrc = '../resources/pic/' + weibo.mainWeiboUid + '/' + currPic;  //图片路径
                    html += '<div class="col-md-3 weibo-pic">'
                        + '<a href="' + picSrc + '#" target="_blank" class="thumbnail"><img src="' + picSrc + '"></a>'
                        + '</div>';
                });
                html += '</div>'
            }

            html += '</div></div><!--嵌套微博--结束-->';
        }
        html += '</div></div><!--该条微博结束-->';

    });

    if (data.totalWeibo <= data.pageSize || data.totalPage <= 1) {   //如果不需要分页
        return html;
    }

    //分页
    html += '<!--分页开始--><nav class="weibo-list-page-nave"><ul class="pagination">';
    html += '<li><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
    for (var i = 1; i <= data.totalPage; i++) {
        if (i == data.currPage) {
            html += '<li class="active"><a monitorid="' + monitorId + '" href="' + url + '">' + i + '</a></li>';
        } else {
            html += '<li><a monitorid="' + monitorId + '" href="' + url + '">' + i + '</a></li>';
        }
    }
    html += '<li><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>';
    html += '</ul></nav><!--分页结束-->';

    return html;
}

function formatTime(millisecond) {
    var date = new Date(millisecond);
    var dateStr = date.getFullYear() + '-' + fillNum(date.getMonth() + 1) + '-' + fillNum(date.getDate())
        + ' ' + fillNum(date.getHours()) + ':' + fillNum(date.getMinutes()) + ':' + fillNum(date.getSeconds());
    return dateStr;
}

/**
 * 把形如'9'的字符串变为'09'
 */
function fillNum(number) {
    if (number < 10) {
        return '0' + number;
    }
    return number;
}

function loadAllMonitor() {
    //加载所有监控的人
    $.getJSON("./getAllMonitor.json", function (data) {
        if (data.ret !== true) {
            return;
        }
        var monitors = data.data;
        var html = "";
        //设置左边monitor列表
        $.each(monitors, function (key, monitor) {
            if (key == 0) {
                html = html + "<li role='presentation' class='active'><a id='" + key + "monitorTab' href='#" + key
                    + "monitor' aria-controls='" + key
                    + "monitor' role='tab' data-toggle='tab' monitorid='" + monitor.id + "'>"
                    + monitor.weiboNickname + "</a></li>";
            } else {
                html = html + "<li role='presentation'><a id='" + key + "monitorTab' href='#" + key
                    + "monitor' aria-controls='" + key
                    + "monitor' role='tab' data-toggle='tab' monitorid='" + monitor.id + "'>"
                    + monitor.weiboNickname + "</a></li>";
            }
        });
        $("#monitorTabList").html(html);
        //设置右边monitor的微博panel，初始化为空panel
        html = "";
        $.each(monitors, function (key, monitor) {
            if (key == 0) {
                html += '<div id="'
                    + key + 'monitor" role="tabpanel" class="tab-pane fade in active" monitorid=' + monitor.id + ' ><div class="panel panel-info"><div class="panel-heading">'
                    + monitor.weiboNickname
                    + ' <a href="#"><span class="label label-primary">导出</span></a>'
                    //+ ' <a class="stop-monitor-button" monitorid="' + monitor.id + '" href="#"><span class="label label-primary">' + getStopStr(monitor) + '</span></a>'
                    + getStopHtml(monitor)
                    + ' <a href="#"><span class="label label-danger">删除</span></a>'
                    + '</div><div class="panel-body"></div></div></div>';
            } else {
                html += '<div id="'
                    + key + 'monitor" role="tabpanel" class="tab-pane fade" monitorid=' + monitor.id + ' ><div class="panel panel-info"><div class="panel-heading">'
                    + monitor.weiboNickname
                    + ' <a href="#"><span class="label label-primary">导出</span></a>'
                    //+ ' <a class="stop-monitor-button" monitorid="' + monitor.id + '" href="#"><span class="label label-primary">' + getStopStr(monitor) + '</span></a>'
                    + getStopHtml(monitor)
                    + ' <a href="#"><span class="label label-danger">删除</span></a>'
                    + '</div><div class="panel-body"></div></div></div>';
            }

        });
        $("#weiboListPanel").html(html);
    });
}

function getStopHtml(monitor) {
    var html = '';
    if (monitor.isStop) {
        html += ' <a class="start-monitor-button" monitorid="' + monitor.id + '" href="#"><span class="label label-primary">'
            + '启动监控' + '</span></a>';
    } else {
        html += ' <a class="stop-monitor-button" monitorid="' + monitor.id + '" href="#"><span class="label label-primary">'
            + '停止监控' + '</span></a>';

    }
    return html;
}

function registerStopMonitorButtonEvent() {
    $('#weiboListPanel').on('click', '.stop-monitor-button', function (event) {
        event.preventDefault();
        var monitorId = $(this).attr("monitorid");
        $.getJSON("./stopMonitor.json?monitorId=" + monitorId, function (data) {
            if (data.ret === false) {
                alert("请求停止监控该monitor出错！\n" + data.errmsg);
                return;
            }
            alert(data.data);
            //todo:更改文字和事件
            //$(this).html('<span class="label label-primary">启动监控</span>');
        });
    });
}

//////////////////////////////////////////////////////////////
//settings tab

//给addMonitorForm注册事件
function registerFormSubmitEvent() {
    var form = $("#addMonitorForm");
    form.submit(function () {
        $.post(form.attr("action"),
            form.serialize(),
            function (data, status) {
                if (data.ret === true) {
                    alert("添加成功！");
                } else {
                    alert("添加失败！\n" + data.errmsg);
                }
            }, "json");
        return false;
    });
    $("#addMonitorFormSubmitButton").click(function (event) {  //提交按钮的事件
        event.preventDefault();
        form.submit();
    });
    $('#resetForm').on('click',function(event){  //重置表格按钮的事件
        event.preventDefault();
        $('#nickname').val('');
        $('#weiboUid').val('');
        $('#weiboInterfaceUrl').val('');
    });
}

//给控制panel的按钮注册事件
function registerControlButtonEvent(){
    $('#stopCurrentBackup').on('click',function(event){
        event.preventDefault();
        $.getJSON('./stopBackup.json',function(data){
            if( data.ret === false ){
                alert("发送停止请求失败！\n"+data.errmsg);
                return;
            }
            alert(data.data);
        });
    });
}

$(function () {
    //给Presentation tab设置事件
    registerPresentationTabEvent();

    //给所有监控的人tab设置事件
    registerMonitorTabEvent();
    //给停止监控按钮绑定事件
    registerStopMonitorButtonEvent();
    //给分页绑定事件
    registerPageNaveEvent();

    //给addMonitorForm注册事件
    registerFormSubmitEvent();
    //给控制panel的按钮注册事件
    registerControlButtonEvent();
});