<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>未完结项目列表</title>
    <script type="text/javascript" src="${ctx}/js/jquery-1.10.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.jedate.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.jedate.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/skin/jedate.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/mobiscroll_date.css"/>
    <script src="${ctx}/js/mobiscroll_date.js" type="text/javascript" charset="utf-8"></script>
    <style>
        .show_div_bg {
            display: none;
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            background: #000;
            opacity: .3;
            z-index: 8
        }

        #show_div {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: #fff;
            z-index: 9;
            max-height: 400px;
            overflow: auto
        }

        #show_div table {
            width: 700px;
        }

        #show_div table tbody, #show_div table tbody tr {
            height: 50px;
        }
    </style>
</head>
<body style="text-align: center">


<c:if test="${code == 200}">

    <h2>未完结项目列表</h2>


    <div style="text-align: center">
        <table border="1" cellpadding="0" cellspacing="0" width="90%" style="text-align: center;margin-left: 5%;">
            <thead>

            <tr bgcolor="#DAF3F5" style="height: 33px;">
                <td>序号</td>
                <td>项目号</td>
                <td>项目名称</td>
                <td>项目状态</td>
                <td>操作</td>
            </tr>

            </thead>


            <tbody>
            <c:forEach items="${list}" var="pr" varStatus="index">
                <tr>


                    <td>${index.index + 1}</td>
                    <td>${pr.projectNo}</td>
                    <td>${pr.projectName}</td>
                        <%--//项目状态  0:新立项项目 1：正常进行项目 2：大货完结 4:项目暂停 5：取消项目 6：样品完结 --%>
                        <%--<td>${pr.projectStatus}</td>--%>
                    <td>${pr.projectStatusDesc}</td>
                    <td>
                        <button onclick="qualityReportList('${pr.projectNo}')">设置完结</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <div class="show_div_bg"></div>
    <div id="show_div" style="display: none;text-align: center">
        <table border="1" cellpadding="0" cellspacing="0" width="90%" style="text-align: center;">
            <caption>项目号:<input id="save_project_no" value="" disabled="disabled"/></caption>
            <thead>
            <tr>
                <td>质检分类</td>
                <td>质检状态</td>
                <td>质检人</td>
                <td>操作时间</td>
            </tr>
            </thead>
            <tbody id="show_div_body">
            </tbody>
            <tfoot id="show_div_tfoot">
            <tr>
                <td>操作:</td>
                <td colspan="3" style="text-align: center;">完结时间:
                    <input class="datainp wicon" id="finish_time" type="text" value="" placeholder="请选择筛选时间" readonly>
                    &nbsp;&nbsp;
                    <select id="save_type">
                        <option value="2">大货完结</option>
                        <option value="6">样品完结</option>
                    </select>
                    &nbsp; &nbsp;&nbsp;
                    <button onclick="finishStatus()">保存</button>
                    &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button onclick="closeDiv()">取消</button>
                </td>
            </tr>
            </tfoot>
        </table>
        <br>
        <p></p>
    </div>
</c:if>

<c:if test="${code == 404}">
    <h3>${msg}</h3>
</c:if>

</body>
</html>

<script type="text/javascript">
    function qualityReportList(project_no) {
        $.ajax({
            type: "post",
            url: "${ctx}/project/qualityReportList",
            data: {
                project_no: project_no
            },
            success: function (json) {
// 				      var json = eval("(" + data +")");
                if (json.ok) {
                    $("#save_project_no").val(project_no);
                    $("#show_div,.show_div_bg").show();
                    $("#show_div_body").empty();
                    var data = json.data;
                    var content = '';
                    for (var i = 0; i < data.length; i++) {
                        var qr = data[i];
                        content += '<tr>';
                        content += '<td>' + qr.typeDesc + '</td>';
                        content += '<td>' + qr.statusDesc + '</td>';
                        content += '<td>' + qr.user + '</td>';
                        content += '<td>' + qr.createtimeStr + '</td>';
                        content += '</tr>';
                    }
                    $("#show_div_body").append(content);
                } else {
                    alert(json.message);
                }
            },
            error: function () {
                alert("请检查网络");
            }
        });
    }

    function finishStatus() {
        var project_no = $("#save_project_no").val();
        var finish_time = $("#finish_time").val();
        var save_type = $("#save_type").val();

        $.ajax({
            type: "post",
            url: "${ctx}/project/finishStatus",
            data: {
                project_no: project_no,
                finish_time: finish_time,
                save_type: save_type
            },
            success: function (json) {
                if (json.ok) {
                    window.location.reload();
                } else {
                    alert(json.message);
                }
            },
            error: function () {
                alert("请检查网络");
            }
        });
    }

    function closeDiv() {
        $("#save_project_no").val("");
        $("#show_div,.show_div_bg").hide();
        $("#show_div_body").empty();
    }
</script>


<script type="text/javascript">
    var cancel = {
        minDate: '2015-06-16 23:59:59',
        maxDate: '2017-02-16 23:59:59'
    }
    var opts = $.extend({
        type: "je",
        minDate: undefined,
        maxDate: undefined
    }, cancel);
    if (opts.type == "je") {
        $("#optsdate").jeDate({
            isinitVal: true,
            festival: true,
            ishmsVal: false,
            minDate: opts.minDate,
            maxDate: opts.maxDate,
            format: "YYYY-MM-DD hh:mm",
            zIndex: 3000
        })
    }

    var start = {
        format: 'YYYY-MM-DD hh:mm:ss',
        minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期
        festival: true,
        //isinitVal:true,
        maxDate: $.nowDate(0), //最大日期
        choosefun: function (elem, datas) {
            end.minDate = datas; //开始日选好后，重置结束日的最小日期
        },
        okfun: function (elem, datas) {
            alert(datas)
        }
    };
    var end = {
        format: 'YYYY年MM月DD日 hh:mm:ss',
        minDate: $.nowDate(0), //设定最小日期为当前日期
        festival: true,
        //isinitVal:true,
        maxDate: '2099-06-16 23:59:59', //最大日期
        choosefun: function (elem, datas) {
            start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
        }
    };
    $("#inpstart").jeDate(start);
    $("#inpend").jeDate(end);

    $("#finish_time").jeDate({
        isinitVal: true,
        festival: true,
        ishmsVal: false,
        minDate: '2016-06-16 23:59:59',
        maxDate: $.nowDate(0),
        format: "YYYY-MM-DD",
        zIndex: 3000,
    })

</script>
