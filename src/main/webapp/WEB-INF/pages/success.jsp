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

    cal.add(Calendar.DATE, -7);
    cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
    firstDayofWeek = sdf.format(cal.getTime());
    cal.add(Calendar.DATE, 7);

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
    <link href="<%=path%>/resources/css/bootstrap-select.min.css" rel="stylesheet" media="screen">
    <link href="<%=path%>/resources/css/style.css" , rel="stylesheet">
</head>
<body>
<script>
    var curDate = new Date();
    var hours = curDate.getHours();
    var miniutes = curDate.getMinutes();
    if (Number(hours) == 9 && Number(miniutes) < 20 ) {
       alert('后台正在同步数据，请15分钟之后再查询');
        window.history.back(-1);
    }
</script>
<div class="container">
    <div class="form-group">
        <div class="col-md-5">
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
                <li>
                    <a href="#rank" data-toggle="tab">
                        排行
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-md-7" id="displsydiv">
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
            <div style="font-size: medium;font-weight:bold;margin-top:52px;margin-bottom: 10px;"><%=firstDayofWeek%>
                — <%=today%>
            </div>
            <table class="table table-bordered">
                <tr>
                    <th>SP名称</th>
                    <th>点播新增</th>
                    <th>包月新增</th>
                    <th>包月退订</th>
                </tr>
                <c:forEach items="${spinfos}" var="spinfo">
                    <tr>
                        <td>${spinfo.spname}</td>
                        <td>${spinfo.weeklyfeeamount}</td>
                        <td>${spinfo.weeklyorderamount}</td>
                        <td>${spinfo.weeklycancelamount}</td>
                    </tr>
                </c:forEach>
            </table>
            <hr>
            <div style="width: 200px">
                <select class="selectpicker show-tick form-control">
                    <option>全部</option>
                    <c:forEach items="${spinfos}" var="spinfo">
                        <option>${spinfo.spname}</option>
                    </c:forEach>
                </select>
            </div>
            <table class="table table-bordered">
                <tr>
                    <th>产品名称</th>
                    <th>类型</th>
                    <th>SP名称</th>
                    <th>到达数</th>
                    <th>点播新增</th>
                    <th>包月新增</th>
                    <th>包月退订</th>
                </tr>
                <c:forEach items="${weekproductinfos}" var="productinfo">
                    <tr>
                        <td><a href="productDetail/${productinfo.productId}.do">${productinfo.productName}</a></td>
                        <td>${productinfo.orderFlag}</td>
                        <td>${productinfo.spName}</td>
                        <td>${productinfo.totalUserAmount}</td>
                        <td>${productinfo.weekFeeAmount}</td>
                        <td>${productinfo.weekOrderAmount}</td>
                        <td>${productinfo.weekCancelAmount}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="tab-pane fade" id="monthly">
            <div style="font-size: medium;font-weight:bold;margin-top:52px;margin-bottom: 10px;"><%=firstDayofMonth%>
                — <%=today%>
            </div>
            <table class="table table-bordered">
                <tr>
                    <th>SP名称</th>
                    <th>点播新增</th>
                    <th>包月新增</th>
                    <th>包月退订</th>
                </tr>
                <c:forEach items="${spinfos}" var="spinfo">
                    <tr>
                        <td>${spinfo.spname}</td>
                        <td>${spinfo.monthlyfeeamount}</td>
                        <td>${spinfo.monthlyorderamount}</td>
                        <td>${spinfo.monthlycancelamount}</td>
                    </tr>
                </c:forEach>
            </table>
            <hr>
            <div style="width: 200px">
                <select class="selectpicker show-tick form-control">
                    <option>全部</option>
                    <c:forEach items="${spinfos}" var="spinfo">
                        <option>${spinfo.spname}</option>
                    </c:forEach>
                </select>
            </div>
            <table class="table table-bordered">
                <tr>
                    <th>产品名称</th>
                    <th>类型</th>
                    <th>SP名称</th>
                    <th>到达数</th>
                    <th>点播新增</th>
                    <th>包月新增</th>
                    <th>包月退订</th>
                </tr>
                <c:forEach items="${monthproductinfos}" var="productinfo">
                    <tr>
                        <td><a href="productDetail/${productinfo.productId}.do">${productinfo.productName}</a></td>
                        <td>${productinfo.orderFlag}</td>
                        <td>${productinfo.spName}</td>
                        <td>${productinfo.totalUserAmount}</td>
                        <td>${productinfo.monthFeeAmount}</td>
                        <td>${productinfo.monthOrderAmount}</td>
                        <td>${productinfo.monthCancelAmount}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="tab-pane fade" id="custom">
            <div id="customdate" style="font-size: medium;font-weight:bold;margin-top:52px;margin-bottom: 10px;"></div>
            <table class="table table-bordered" id="sptable">

            </table>
            <div style="width: 200px">
                <select id='basic' class="selectpicker show-tick form-control">
                    <option>全部</option>
                    <c:forEach items="${spinfos}" var="spinfo">
                        <option>${spinfo.spname}</option>
                    </c:forEach>
                </select>
            </div>
            <table class="table table-bordered" id="producttable">

            </table>
        </div>
        <c:if test = "${not empty weeklytopinfos}">
            <div class="tab-pane fade" id="rank">
                <table class="table table-bordered" id="ranktable1">
                    <caption>周排行</caption>
                    <tr>
                        <th>排行</th>
                        <th>SP</th>
                        <th>SP名称</th>
                        <th>新增数</th>
                    </tr>
                    <c:forEach items="${weeklytopinfos}" var="topinfo" begin="0" end="9" varStatus="xh">
                        <tr>
                            <td>${xh.count}</td>
                            <td>${topinfo.spid}</td>
                            <td>${topinfo.spname}</td>
                            <td>${topinfo.count}</td>
                        </tr>
                    </c:forEach>
                </table>
                <table class="table table-bordered" id="ranktable2">
                    <caption>月排行</caption>
                    <tr>
                        <th>排行</th>
                        <th>SP</th>
                        <th>SP名称</th>
                        <th>新增数</th>
                    </tr>
                    <c:forEach items="${monthlytopinfos}" var="topinfo" begin="0" end="9" varStatus="xh">
                        <tr>
                            <td>${xh.count}</td>
                            <td>${topinfo.spid}</td>
                            <td>${topinfo.spname}</td>
                            <td>${topinfo.count}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>

    </div>
</div>
<script type="text/javascript" src="<%=path%>/resources/js/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bootstrap-select.min.js"></script>
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
                start_date: start_date + "000000",
                end_date: end_date + "000000"
            },
            success: function (msg) {
                //alert(msg);
                $('#customdate').text(start_date + '—' + end_date);
                $('#sptable').empty();
                $('#producttable').empty();
                $('#sptable').append("<tr>" +
                        "<th>SP名称</th>" +
                        "<th>点播新增</th>" +
                        "<th>包月新增</th>" +
                        "<th>包月退订</th>" +
                        "</tr>"
                );
                $('#producttable').append("<tr>" +
                        "<th>产品名称</th>" +
                        "<th>类型</th>" +
                        "<th>SP名称</th>" +
                        "<th>到达数</th>" +
                        "<th>点播新增</th>" +
                        "<th>包月新增</th>" +
                        "<th>包月退订</th>" +
                        "</tr>"
                );
                var jsonresult = $.parseJSON(msg);
                var jsonarraysp = jsonresult.spinfos;
                var jsonarrayproduct = jsonresult.productinfos;
                jsonarrayproduct.sort(function (x, y) {
                    if (y['type'] == '点播' && x['type'] == '点播')
                        return y['feeamount'] - x['feeamount']
                    else if (y['type'] == '包月' && x['type'] == '包月') {
                        if (y['orderamount'] == x['orderamount']) {
                            return y['totaluser'] - x['totaluser']
                        }
                        else return y['orderamount'] - x['orderamount']
                    }
                    else if ((y['type'] == '点播' && x['type'] == '包月'))
                        return -1;
                    else
                        return 1;
                })
                $.each(jsonarraysp, function (idx, item) {
                    $('#sptable').append("<tr>" +
                            "<td>" + item.spname + "</td>" +
                            "<td>" + item.feeamount + "</td>" +
                            "<td>" + item.orderamount + "</td>" +
                            "<td>" + item.cancelamount + "</td>" +
                            "</tr>"
                    );
                });

                $.each(jsonarrayproduct, function (idx, item) {
                    $('#producttable').append("<tr>" +
                            "<td><a href=\"productDetail/" + item.productid + ".do\">" + item.productname + "</a></td>" +
                            "<td>" + item.type + "</td>" +
                            "<td>" + item.spname + "</td>" +
                            "<td>" + item.totaluser + "</td>" +
                            "<td>" + item.feeamount + "</td>" +
                            "<td>" + item.orderamount + "</td>" +
                            "<td>" + item.cancelamount + "</td>" +
                            "</tr>"
                    );
                });
                var trs = $('#producttable').find('tr:gt(0)');
                var table = $('#producttable');
                $('#basic').on('change',
                        function () {
                            var selected = $(this).find("option:selected").text();
                            //alert(selected);
                            table.find('tr:gt(0)').remove();
                            if (selected == '全部') {
                                trs.each(function (i, item) {
                                    table.append($(this));
                                })
                            }
                            else {
                                trs.each(function (i, item) {
                                    var spname = $(this).children(":eq(2)").text();
                                    if (spname == selected) {
                                        //alert(spname);
                                        table.append($(this));
                                    }
                                })
                            }
                        });
            }
        });
    });
    //获取所有tr
    //var trs = $("table:eq(1) tr:gt(0)");
    $('.selectpicker').each(function () {
        var trs = $(this).parent().next('table').find('tr:gt(0)');
        var table = $(this).parent().next('table')
        $(this).on('change',
                function () {
                    var selected = $(this).find("option:selected").text();
                    //alert(selected);
                    table.find('tr:gt(0)').remove();
                    if (selected == '全部') {
                        trs.each(function (i, item) {
                            table.append($(this));
                        })
                    }
                    else {
                        trs.each(function (i, item) {
                            var spname = $(this).children(":eq(2)").text();
                            if (spname == selected) {
                                //alert(spname);
                                table.append($(this));
                            }
                        })
                    }
                });
    });
    //再运行 selectpicker
    $('.selectpicker').selectpicker();
</script>

</body>
</html>
