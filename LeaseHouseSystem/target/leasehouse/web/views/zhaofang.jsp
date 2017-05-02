<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>找房</title>
    <%--<script type="text/javascript" src="../../static/js/jquery-3.0.0.min.js"></script>--%>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <%--<script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>--%>
    <%--<script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>--%>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
    <script src="http://webapi.amap.com/maps?v=1.3&key=8e7ebee8249f55f52524981be89ec9e6"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    <%--<link rel="stylesheet" href="../../static/css/bootstrap.min.css">--%>
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../../static/css/zufang.css">

</head>
<body>
    <div class="row mainDiv">
        <div class="col-md-4">
            1121122
        </div>
        <div class="col-md-8 mapDiv" id="container">

        </div>
    </div>
<script type="text/javascript">
    var map = new AMap.Map('container', {
        resizeEnable: true,
        zoom:11,
        center: [116.397428, 39.90923]
    });
</script>

</body>



</html>
