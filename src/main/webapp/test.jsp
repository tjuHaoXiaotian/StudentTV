<%--
  Created by IntelliJ IDEA.
  User: shen
  Date: 2016/3/13
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/vendor/bootstrap2.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/vendor/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <script src="js/jquery.min.js"></script>
    <script src="js/fileinput.js" type="text/javascript"></script>
    <script src="js/fileinput_locale_zh.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<div class="container kv-main">
    <form enctype="multipart/form-data">
        <div class="form-group">
            <input id="file-5" class="file" type="file"  multiple data-preview-file-type="any" data-upload-url="#">
        </div>
    </form>

</div>
</body>
<script>
    $('#file-fr').fileinput({
//        language: 'zh',

    });
</script>
</html>
