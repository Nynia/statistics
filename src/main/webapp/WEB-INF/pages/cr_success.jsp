<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Ridiculous
  Date: 2016/9/26
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%
    String username = session.getAttribute("username").toString();
    String path = request.getContextPath();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();//获取当前日期
    //cal.add(Calendar.DATE, 8);
    String today = sdf.format(cal.getTime());
    String firstDayofWeek = null;

    if (cal.get(Calendar.DAY_OF_WEEK) <= 5) {
        cal.add(Calendar.DATE, -7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        firstDayofWeek = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 7);
    } else {
        cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        firstDayofWeek = sdf.format(cal.getTime());
    }

    cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
    String firstDayofMonth = sdf.format(cal.getTime());

    cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -31);
    String last30Days = sdf.format(cal.getTime());
%>
<html>
<head>
    <meta http-equiv=”Content-Type” content=”text/html; charset=utf-8″>
    <title>Success</title>
    <link href="<%=path%>/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="<%=path%>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link href="<%=path%>/resources/css/style.css" , rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="form-group">
        <div class="col-md-4">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#weekly" data-toggle="tab">
                        本周
                    </a>
                </li>
                <li>
                    <a href="#monthly" data-toggle="tab">
                        本月
                    </a>
                </li>
                <li>
                    <a id="customa" href="#custom" data-toggle="tab">
                        自定义
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-md-8" id="displsydiv">
            <div class="form-group">
                <div class="fixedheight col-md-1">开始</div>
                <div class="input-group date form_date col-md-4" data-date="" data-date-format="yyyy-MM-dd"
                     data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input id="start_date" class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <div class="fixedheight col-md-1">结束</div>
                <div class="input-group date form_date col-md-4" data-date="" data-date-format="yyyy-MM-dd"
                     data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input id="end_date" class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <div class="col-md-1">
                    <button id="query" class="btn btn-primary">查询</button>
                </div>
            </div>
        </div>
    </div>
    </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="weekly">
            <div style="font-size: medium;font-weight:bold;margin-top:52px;margin-bottom: 10px;"><span
                    id="week_begin_time"><%=firstDayofWeek%></span> — <span id="week_end_time"><%=today%></span></div>
            <table class="table table-bordered">
                <tr>
                    <th>铃音编码</th>
                    <th>名称</th>
                    <th>CP编码</th>
                    <th>CP名称</th>
                    <th>新增数量</th>
                    <th>退订数量</th>
                    <th>到达数</th>
                </tr>
                <c:forEach items="${weeklycrResult}" var="weeklyinfo" begin="0" end="9">
                    <tr>
                        <td id="a${weeklyinfo.ringid}"><a style="text-decoration:none;">${weeklyinfo.ringid}</a></td>
                        <td>${weeklyinfo.ringname}</td>
                        <td>${weeklyinfo.cpid}</td>
                        <td>${weeklyinfo.cpname}</td>
                        <td>${weeklyinfo.weeklyorderamount}</td>
                        <td>${weeklyinfo.weeklycancelamount}</td>
                        <td>${weeklyinfo.totalamount}</td>
                    </tr>
                </c:forEach>
                <td>自营音乐包</td>
                <c:forEach items="${weeklycrResult}" var="weeklyinfo" begin="10">
                    <tr>
                        <td id="a${weeklyinfo.ringid}"><a style="text-decoration:none;">${weeklyinfo.ringid}</a></td>
                        <td>${weeklyinfo.ringname}</td>
                        <td>${weeklyinfo.cpid}</td>
                        <td>${weeklyinfo.cpname}</td>
                        <td>${weeklyinfo.weeklyorderamount}</td>
                        <td>${weeklyinfo.weeklycancelamount}</td>
                        <td>${weeklyinfo.totalamount}</td>
                    </tr>
                </c:forEach>
            </table>
            <table class="table table-bordered" id="weekringdetail">

            </table>
            <hr>
        </div>
        <div class="tab-pane fade" id="monthly">
            <div style="font-size: medium;font-weight:bold;margin-top:52px;margin-bottom: 10px;"><span
                    id="month_begin_time"><%=firstDayofMonth%></span> — <span id="month_end_time"><%=today%></span>
            </div>
            <table class="table table-bordered">
                <tr>
                    <th>铃音编码</th>
                    <th>名称</th>
                    <th>CP编码</th>
                    <th>CP名称</th>
                    <th>新增数量</th>
                    <th>退订数量</th>
                    <th>到达数</th>
                </tr>
                <c:forEach items="${monthlycrResult}" var="monthlyinfo" begin="0" end="9">
                    <tr>
                        <td id="b${monthlyinfo.ringid}"><a style="text-decoration:none;">${monthlyinfo.ringid}</a></td>
                        <td>${monthlyinfo.ringname}</td>
                        <td>${monthlyinfo.cpid}</td>
                        <td>${monthlyinfo.cpname}</td>
                        <td>${monthlyinfo.monthlyorderamount}</td>
                        <td>${monthlyinfo.monthlycancelamount}</td>
                        <td>${monthlyinfo.totalamount}</td>
                    </tr>
                </c:forEach>
                <td>自营音乐包</td>
                <c:forEach items="${monthlycrResult}" var="monthlyinfo" begin="10">
                    <tr>
                        <td id="b${monthlyinfo.ringid}"><a style="text-decoration:none;">${monthlyinfo.ringid}</a></td>
                        <td>${monthlyinfo.ringname}</td>
                        <td>${monthlyinfo.cpid}</td>
                        <td>${monthlyinfo.cpname}</td>
                        <td>${monthlyinfo.monthlyorderamount}</td>
                        <td>${monthlyinfo.monthlycancelamount}</td>
                        <td>${monthlyinfo.totalamount}</td>
                    </tr>
                </c:forEach>
            </table>
            <table class="table table-bordered" id="monthringdetail">

            </table>
        </div>
        <div class="tab-pane fade" id="custom">
            <div id="customdate" style="font-size: medium;font-weight:bold;margin-top:52px;margin-bottom: 10px;"></div>
            <table class="table table-bordered" id="ringtable">

            </table>
            <table class="table table-bordered" id="givenringdetail">

            </table>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript">
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: false
    });

    $(document).ready(function () {
        // Handler for .ready() called.
        $("table tr:gt(0)").each(function (i) {
            //alert("这是第"+i+"行内容");
            $(this).children("td").each(function (i) {
                //alert("第"+i+"个td的内容："+$(this).text());
                if ($(this).is('[id]')) {
                    var id = $(this).attr('id');
                    //alert(id[0]);
                    var begin_time = null;
                    var end_time = null;
                    var tag = null;
                    if (id[0] == 'a') {
                        begin_time = $('#week_begin_time').text() + " 00:00:00";
                        end_time = $('#week_end_time').text() + " 00:00:00";
                        tag = $('#weekringdetail');
                    }
                    else if (id[0] == 'b') {
                        begin_time = $('#month_begin_time').text() + " 00:00:00";
                        end_time = $('#month_end_time').text() + " 00:00:00";
                        tag = $('#monthringdetail');
                    }
                    $(this).click(function () {
                        ringid = $(this).text()
                        $.ajax({
                            type: "GET",
                            url: "ringDetail.do",
                            data: {
                                start_date: begin_time,
                                end_date: end_time,
                                ringid: $(this).text()
                            },
                            success: function (msg) {
                                //alert(msg);
                                tag.empty();
                                tag.append("<caption>" + ringid+"</caption>");
                                tag.append("<tr>" +
                                        "<td>渠道</td>" +
                                        "<td>全省</td>" +
                                        "<td>南京</td>" +
                                        "<td>苏州</td>" +
                                        "<td>无锡</td>" +
                                        "<td>常州</td>" +
                                        "<td>镇江</td>" +
                                        "<td>扬州</td>" +
                                        "<td>南通</td>" +
                                        "<td>泰州</td>" +
                                        "<td>徐州</td>" +
                                        "<td>淮安</td>" +
                                        "<td>盐城</td>" +
                                        "<td>连云港</td>" +
                                        "<td>宿迁</td>" +
                                        "</tr>"
                                );
                                var jsonresult = $.parseJSON(msg);
                                var jsonarraysp = jsonresult.channelinfos;
                                //jsonarrayproduct.sort(function(x, y) {return y['totaluser'] - x['totaluser']})
                                $.each(jsonarraysp, function (idx, item) {
                                    tag.append("<tr>" +
                                            "<td>" + item.channel + "</td>" +
                                            "<td>" + item.total + "</td>" +
                                            "<td>" + item.a1 + "</td>" +
                                            "<td>" + item.a2 + "</td>" +
                                            "<td>" + item.a3 + "</td>" +
                                            "<td>" + item.a4 + "</td>" +
                                            "<td>" + item.a5 + "</td>" +
                                            "<td>" + item.a6 + "</td>" +
                                            "<td>" + item.a7 + "</td>" +
                                            "<td>" + item.a8 + "</td>" +
                                            "<td>" + item.a9 + "</td>" +
                                            "<td>" + item.a10 + "</td>" +
                                            "<td>" + item.a11 + "</td>" +
                                            "<td>" + item.a12 + "</td>" +
                                            "<td>" + item.a13 + "</td>" +
                                            "</tr>"
                                    );
                                });
                                $(document).scrollTop(1000);
                            }
                        });
                    });

                }
            });
        });
    });
    $('#query').click(function () {
        var start_date = $('#start_date').val();
        var end_date = $('#end_date').val();
        if (start_date > end_date) {
            alert("起始日期必须小于等于结束日期");
            return
        }
        $('#customa').click();
        $.ajax({
            type: "GET",
            url: "query.do",
            data: {
                start_date: start_date + " 00:00:00",
                end_date: end_date + " 00:00:00"
            },
            success: function (msg) {
                //alert(msg);
                $('#customdate').text(start_date + '—' + end_date);
                $('#ringtable').empty();
                $('#ringtable').append("<tr>" +
                        "<th>铃音编码</th>" +
                        "<th>名称</th>" +
                        "<th>CP编码</th>" +
                        "<th>CP名称</th>" +
                        "<th>新增数量</th>" +
                        "<th>退订数量</th>" +
                        "<th>到达数</th>" +
                        "</tr>"
                );
                var jsonresult = $.parseJSON(msg);
                var jsonarraysp = jsonresult.ringinfos;
                //jsonarrayproduct.sort(function(x, y) {return y['totaluser'] - x['totaluser']})
                var top = 0;
                $.each(jsonarraysp, function (idx, item) {
                    if (top < 10 && item.ringid.indexOf('810') == 0
                    && item.ringid != '810099991095'
                    && item.ringid != '810099990490')  {
                        $('#ringtable').append("<tr>" +
                                "<td id=\"" + item.ringid + "\">" + "<a style=\"text-decoration:none;\">" + item.ringid + "</td>" +
                                "<td>" + item.ringname + "</td>" +
                                "<td>" + item.cpid + "</td>" +
                                "<td>" + item.cpname + "</td>" +
                                "<td>" + item.givenorderamount + "</td>" +
                                "<td>" + item.givencancelamount + "</td>" +
                                "<td>" + item.totalamount + "</td>" +
                                "</tr>"
                        );
                        top += 1;
                    }
                });
                $('#ringtable').append("<tr><td>自营音乐包</td></tr>");
                $.each(jsonarraysp, function (idx, item) {
                    if (item.ringid.indexOf('820') == 0
                    || item.ringid == '810099991095'
                    || item.ringid == '810099990490') {
                        $('#ringtable').append("<tr>" +
                                "<td id=\"" + item.ringid + "\">" + "<a style=\"text-decoration:none;\">" + item.ringid + "</td>" +
                                "<td>" + item.ringname + "</td>" +
                                "<td>" + item.cpid + "</td>" +
                                "<td>" + item.cpname + "</td>" +
                                "<td>" + item.givenorderamount + "</td>" +
                                "<td>" + item.givencancelamount + "</td>" +
                                "<td>" + item.totalamount + "</td>" +
                                "</tr>"
                        );
                    }
                });
                $("#ringtable td").each(function (){
                    if ($(this).is('[id]')) {
                        $(this).click(function () {
                            ringid = $(this).text();
                            $.ajax({
                                type: "GET",
                                url: "ringDetail.do",
                                data: {
                                    start_date: start_date + " 00:00:00",
                                    end_date: end_date + " 00:00:00",
                                    ringid: $(this).text()
                                },
                                success: function (msg) {
                                    //alert(msg);
                                    $('#givenringdetail').empty();
                                    $('#givenringdetail').append("<caption>" + ringid+"</caption>");
                                    $('#givenringdetail').append("<tr>" +
                                            "<td>渠道</td>" +
                                            "<td>全省</td>" +
                                            "<td>南京</td>" +
                                            "<td>苏州</td>" +
                                            "<td>无锡</td>" +
                                            "<td>常州</td>" +
                                            "<td>镇江</td>" +
                                            "<td>扬州</td>" +
                                            "<td>南通</td>" +
                                            "<td>泰州</td>" +
                                            "<td>徐州</td>" +
                                            "<td>淮安</td>" +
                                            "<td>盐城</td>" +
                                            "<td>连云港</td>" +
                                            "<td>宿迁</td>" +
                                            "</tr>"
                                    );
                                    var jsonresult = $.parseJSON(msg);
                                    var jsonarraysp = jsonresult.channelinfos;
                                    //jsonarrayproduct.sort(function(x, y) {return y['totaluser'] - x['totaluser']})
                                    $.each(jsonarraysp, function (idx, item) {
                                        $('#givenringdetail').append("<tr>" +
                                                "<td>" + item.channel + "</td>" +
                                                "<td>" + item.total + "</td>" +
                                                "<td>" + item.a1 + "</td>" +
                                                "<td>" + item.a2 + "</td>" +
                                                "<td>" + item.a3 + "</td>" +
                                                "<td>" + item.a4 + "</td>" +
                                                "<td>" + item.a5 + "</td>" +
                                                "<td>" + item.a6 + "</td>" +
                                                "<td>" + item.a7 + "</td>" +
                                                "<td>" + item.a8 + "</td>" +
                                                "<td>" + item.a9 + "</td>" +
                                                "<td>" + item.a10 + "</td>" +
                                                "<td>" + item.a11 + "</td>" +
                                                "<td>" + item.a12 + "</td>" +
                                                "<td>" + item.a13 + "</td>" +
                                                "</tr>"
                                        );
                                    });
                                    $(document).scrollTop(1000);
                                }
                            });
                        });
                    }
                });
            }
        });
    });
</script>

</body>
</html>
