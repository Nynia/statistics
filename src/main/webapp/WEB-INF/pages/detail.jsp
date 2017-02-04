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
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Detail</title>
    <link href="<%=path%>/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="<%=path%>/resources/css/style.css" , rel="stylesheet">
    <script type="text/javascript" src="<%=path%>/resources/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/bootstrap.min.js"></script>
</head>
<body>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript">
    function getDateStr(AddDayCount) {
        var dd = new Date();
        dd.setDate(dd.getDate() + AddDayCount);//获取AddDayCount天后的日期
        var y = dd.getFullYear();
        var m = dd.getMonth()+1;//获取当前月份的日期
        var d = dd.getDate();
        return m+"-"+d;
    }
    $().ready(function () {
        var orderflag = ${orderflag};
        var productname = '${productname}';
        var totaluser = ${totaluser};
        var lastthirty = new Array();

        for (var i=0; i<3; i++) {
            lastthirty[i] = new Array();
        }
        <c:forEach items="${result}" var="item" varStatus="status">
        var outindex = ${status.index};
        <c:forEach items="${item}" var="num" varStatus="innerstatus">
        var index = ${innerstatus.index};
        lastthirty[index][outindex] = ${num};
        </c:forEach>
        </c:forEach>

        if (orderflag == 3) {
            //包月
            var data_30 = new Array();
            var x_30 = new Array();
            var user_30 = new Array();

            for (var i=0; i<2; i++) {
                data_30[i] = new Array();
            }
            for (var i=29; i>=0; i--) {
                data_30[0][i] = lastthirty[0][i];
                data_30[1][i] = lastthirty[1][i];
                x_30[i] = getDateStr(i-30);
                if (i==29) {
                    user_30[i] = totaluser;
                }
                else {
                    user_30[i]  = user_30[i+1] - (data_30[0][i+1] - data_30[1][i+1]);
                }
            }

            var dom = document.getElementById("chart1");
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            option = {
                title : {
                    text: productname,
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['订购','退订']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : x_30
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        axisLabel : {
                            formatter: '{value}'
                        }
                    }
                ],
                series : [
                    {
                        name:'订购',
                        type:'line',
                        data:data_30[0],
                    },
                    {
                        name:'退订',
                        type:'line',
                        data:data_30[1],
                    }
                ]
            };
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }

            var dom = document.getElementById("chart2");
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            option = {
                title : {
                    text: productname,
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['用户总数']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : x_30
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        axisLabel : {
                            formatter: '{value}'
                        }
                    }
                ],
                series : [
                    {
                        name:'用户总数',
                        type:'line',
                        data:user_30,
                    },
                ]
            };
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        }
        else {
            //点播
            var data_30 = new Array();
            var x_30 = new Array();

            for (var i=29; i>=0; i--) {
                data_30[i] = lastthirty[2][i];
                x_30[i] = getDateStr(i-30);
            }

            var dom = document.getElementById("chart1");
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            option = {
                title : {
                    text: productname,
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['点播']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : x_30
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        axisLabel : {
                            formatter: '{value}'
                        }
                    }
                ],
                series : [
                    {
                        name:'点播',
                        type:'line',
                        data:data_30,
                    },
                ]
            };
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        }
    });
</script>
<div id="chart1" style="width: 100%;height:300px;"></div>

<div id="chart2" style="width: 100%;height:300px;"></div>

</body>
</html>
