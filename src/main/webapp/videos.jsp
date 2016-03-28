<%--
  Created by IntelliJ IDEA.
  User: shen
  Date: 2016/3/11
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>delete</title>
    <link href="<%=request.getContextPath()%>/css/vendor/bootstrap.min.css" rel='stylesheet' type='text/css' />
    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/css/vendor/style.css" rel='stylesheet' type='text/css' />
    <!-- Graph CSS -->
    <link href="<%=request.getContextPath()%>/css/vendor/player.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/css/vendor/lines.css" rel='stylesheet' type='text/css' />
    <link href="<%=request.getContextPath()%>/css/vendor/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/vendor/bootstrap.css">
    <link href="<%=request.getContextPath()%>/css/vendor/custom.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/js/html5shiv.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/respond.min.js"></script>


</head>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="top1 navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">TJUSTV</a>
        </div>
        <!-- /.navbar-header -->
        <form class="navbar-form navbar-right">
            <input type="text" class="form-control" value="Search..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search...';}">
        </form>
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse" style="height: 550px">
                <ul class="nav" id="side-menu">

                    <li>
                        <a href="delete.jsp">删除视频</a>
                    </li>
                    <li>
                        <a href="add.jsp">上传视频</a>
                    </li>

                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>
    <div id="page-wrapper">
    <table id="my_table" class="table table-hover table-bordered table-striped table-striped col-md-10">
    <thead>
    <tr>
    <th></th>
    <th>缩略图</th>
    <th>视频名称</th>
    <th>视频描述</th>
    <th>系列</th>
    <th>主演</th>
    <th>时长</th>
    <th>大小</th>
    <th>访问次数</th>
    <th>创建时间</th>
    <th>操作</th>

    </tr>
    </thead>
    <tbody>


    </tbody>
    </table>

    </div>

</div>
    <%@include file="footer.jsp" %>
    <script>
//    var rootUrl = "http://121.42.217.4:8080/";
    var rootUrl = "http://localhost:8080/";
    $(document).ready(function() {
    loadTable();
    });
    var table;
    function loadTable(){
        $.ajax({
        url: rootUrl+'studentTV/video',
        type: 'get',
        dataType: 'json'
        })
        .success(function(data){
                table = $('#my_table').DataTable({
                "bProcessing": true,
                "bDestroy": true,
                "sPaginationType" : "full_numbers",
                "oLanguage" : {
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    "sInfoEmpty": "没有数据",
                    "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                    "sZeroRecords": "没有检索到数据",
                    "sSearch": "搜索: &nbsp",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"
                }
        },
        "data":data.data.videos,
        "columns": [
            { "data": 'name' } ,
            { "data":'path'} ,
            { "data": 'name' },
            { "data": 'description' },
            { "data": 'album' },
            { "data": 'performer' },
            { "data": 'duration' },
            { "data": 'size' },
            { "data": 'visitedTimes' },
            { "data": 'createtime' },
            { "data": 'id' }
        ],
        "columnDefs": [
                {
                    "targets": 0,
                     "render": function (data, type, row, table) {
                        return '<input type="checkbox" class="checkbox">';
                      }
                },
                {
                    "targets": 1,
                    "render": function (data, type, row, table) {
                        var imageUrl = data.substring(0,data.lastIndexOf("."))+".jpg";
                        console.log("数据是2：" + data + '  ;' + imageUrl);
                        return '<div class="thumb-area"> <li class="thumb-item video-item" video-path="' + data + '"> <img src="' + imageUrl + '" alt="" />' + '</li> </div>';
                     }

                },
                {
                    "targets": 10,
                    "render": function (data, type, row, table) {
                        return '<a href="" onclick="deleteVideo('+data+')">删除</a>';
                    }
                }]
      });
    });
    }

     function deleteVideo(id){
         $.ajax({
             url: rootUrl+'studentTV/video/delete/'+id,
//             contentType:"application/json",
             type: 'get'
//             dataType: 'json',
//             data:JSON.stringify(newApply)
         }).success(function(data){
             loadTable();
         }).error(function(){
             alert("删除失败");
         });
     }
    </script>
    </body>
    </html>

