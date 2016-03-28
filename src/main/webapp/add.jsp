<%--
  Created by IntelliJ IDEA.
  User: shen
  Date: 2016/3/11
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addvideo</title>
    <link href="<%=request.getContextPath()%>/css/vendor/bootstrap.min.css" rel='stylesheet' type='text/css' />
    <!-- Custom CSS -->
    <link href="<%=request.getContextPath()%>/css/vendor/style.css" rel='stylesheet' type='text/css' />
    <!-- Graph CSS -->
    <link href="<%=request.getContextPath()%>/css/vendor/lines.css" rel='stylesheet' type='text/css' />
    <link href="<%=request.getContextPath()%>/css/vendor/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/vendor/custom.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/vendor/dropzone.css" />
    <%--<link href="<%=request.getContextPath()%>/css/vendor/bootstrap.css" rel="stylesheet">--%>
    <%--<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="<%=request.getContextPath()%>/css/vendor/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script src="js/fileinput.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/fileinput_locale_zh.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dropzone.js"></script>
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
                    <a href="videos.jsp">删除视频</a>
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
<div class="container kv-main" style="width: 800px ; height: 582px">
    <form enctype="multipart/form-data">
        <div class="form-group" style="height: 300px">
            <%--<input id="file-5" name="file" class="file" type="file"  multiple data-preview-file-type="any" data-upload-url="http://121.42.217.4:8080/studentTV/video/upload">--%>
            <input id="file-5" name="file" class="file" type="file"  multiple data-preview-file-type="any" data-upload-url="http://localhost:8080/studentTV/video/upload">
        </div>
    </form>
</div>
</body>
<script>
    var rootUrl = "http://121.42.217.4:8080/";
    $('#file-fr').fileinput({
//        language: 'zh',

    });
</script>
</html>
