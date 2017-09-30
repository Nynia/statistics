<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page
        import="java.util.List,service.cpuserService,service.outputlistService"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", -10);
%>
<html>
<base href="<%=basePath%>">
<head>
    <meta http-equiv=”Content-Type” content=”text/html; charset=utf-8″>
    <title>Home</title>
    <link href="<%=path%>/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="<%=path%>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link href="<%=path%>/resources/css/style.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <div>
        <div class="form-group">
            <div class="fixedheight col-md-2">开始日期</div>
            <div class="input-group date form_date col-md-3" data-date="" data-date-format="yyyy-MM-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                <input id="start_date" class="form-control" size="16" type="text" value="" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
            <div class="fixedheight col-md-2">结束日期</div>
            <div class="input-group date form_date col-md-3" data-date="" data-date-format="yyyy-MM-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                <input id="end_date" class="form-control" size="16" type="text" value="" readonly>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
            <div class="col-md-1"><button id="query" class="btn btn-primary">查询</button></div>
        </div>

    </div>
    <div id="qureycontent">
        <table id="table" class="table">
            <tr>
                <td>CP名称</td>
                <td>点播订购量</td>
                <td>包月订购量</td>
                <td>包月退订量</td>
            </tr>
        </table>
    </div>

</div>

<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: false
    });
    $('#query').click(function(){
        var start_date = $('#start_date').val();
        var end_date = $('#end_date').val();
        if (start_date > end_date) {
            alert("起始日期必须小于等于结束日期");
            return
        }
        $.ajax({
            type: "GET",
            url: "query.do",
            data: {
                start_date:start_date+" 00:00:00",
                end_date:end_date+" 00:00:00"
            },
            success: function (msg) {
                $('#table').empty();
                $('#table').append("<tr>" +
                        "<td>CP名称</td>" +
                        "<td>点播订购量</td>" +
                        "<td>包月订购量</td>" +
                        "<td>包月退订量</td>" +
                        "</tr>"
                );
                var jsonarray= $.parseJSON(msg);
                var total1 = 0;
                var total2 = 0;
                var total3 = 0;
                $.each(jsonarray, function(i, val) {
                    //alert(val.id);
                    $('#table').append("<tr>" +
                            "<td>" + val.name + "</td>" +
                            "<td>" + val.c1 + "</td>" +
                            "<td>" + val.c2 + "</td>" +
                            "<td>" + val.c3 + "</td>" +
                            "</tr>"
                    );
                    total1 += parseInt(val.c1);
                    total2 += parseInt(val.c2);
                    total3 += parseInt(val.c3);
                });
                $('#table').append("<tr>" +
                        "<td>总量</td>" +
                        "<td>" + total1 + "</td>" +
                        "<td>" + total2 + "</td>" +
                        "<td>" + total3 + "</td>" +
                        "</tr>"
                );
            }
        });
    });
</script>

</body>
</html>
