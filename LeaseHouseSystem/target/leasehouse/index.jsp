<!DOCTYPE html>
<%@ page language="java" isThreadSafe="true" pageEncoding="utf8" %>
<%@ page contentType="text/html; charset=utf8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Find Your House</title>
    <script type="text/javascript" src="static/javascript/jquery-3.0.0.min.js"></script>
    <script type="text/javascript" src="static/javascript/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=mjNURF8cCTN37XXffGhY1G8KOGUHG1cA"></script>
    <script type="text/javascript" src="static/javascript/zhaofang.js"></script>
    <%--<link rel="stylesheet" href="static/css/bootstrap.min.css">--%>
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./static/style/zufang.css">
    <%--<style type="text/css">--%>
        <%--body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}--%>
        <%--#l-map{height:300px;width:100%;}--%>
        <%--#r-result{width:100%; font-size:14px;line-height:20px;}--%>
    <%--</style>--%>
</head>
<body>
    <div class="row" id="mainDiv" style="margin: 0">
        <div class="col-md-2 left-search">
            <div class="row">
                <label for="" class="control-label col-md-5">当前访问量：</label>
                <label class="control-label col-md-7"></label>
            </div>
            <form action="">
                <div class="form-group">
                    <div class="row">
                        <label for="data-source" class="control-label col-md-5">数据来源：</label>
                        <select name="" id="data-source" class="form-control col-md-7">
                            <option value="1">58同城房源</option>
                            <option value="2">58同城个人房源</option>
                            <option value="3">安居客房源</option>
                        </select>
                    </div>
                    <div class="row">
                        <label class="control-label col-md-5">价格：</label>
                        <input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="low-price" class="form-control col-md-3">
                        <%--<input type="number" id="low-price" class="form-control col-md-3" />--%>
                        <label>-</label>
                        <input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="high-price" class="form-control col-md-3">
                        <%--<input type="number" id="high-price" class="form-control col-md-3" />--%>
                    </div>
                    <div class="row">
                        <label for="workplace" class="control-label col-md-5">工作地点：</label>
                        <input type="text" class="form-control col-md-7" id="workplace">
                    </div>
                    <div class="row">
                            <button type="submit" class="btn btn-primary col-md-5" id="submit-data">查找</button>
                    </div>
                </div>
            </form>

        </div>
        <div class="col-md-10" style="position: relative">
            <div style="position: absolute; z-index: 100; top:75%; background-color: #46b8da">
                <div>
                    <label for="">0-2000</label>
                    <br>
                    <img src="./static/pic/green_location.png" alt="0-2000">
                </div>
                <br>
                <div>
                    <label for="">2000-4000</label>
                    <br>
                    <img src="./static/pic/blue_location.png" alt="2000-4000">
                </div>
                <br>
                <div>
                    <label for="">4000-6000</label>
                    <br>
                    <img src="./static/pic/yellow_location.png" alt="4000-6000">
                </div>
                <br>
                <div>
                    <label for="">6000-8000</label>
                    <br>
                    <img src="./static/pic/pink_location.png" alt="6000-8000">
                </div>
                <br>
                <div>
                    <label for="">8000+</label>
                    <br>
                    <img src="./static/pic/red_location.png" alt="8000+">
                </div>
            </div>
            <div id="l-map" style="position:absolute;">

            </div>
            <div id="r-result">
                <%--<input type="button" value="批量地址解析" onclick="bdGEO()" />--%>
                <div id="result"></div>
            </div>
        </div>
    </div>
</body>
</html>
<script>
    var map = new BMap.Map("l-map");
    map.centerAndZoom(new BMap.Point(121.473299,31.241243), 19);
    map.enableScrollWheelZoom(true);
    var index = 0;
    var myGeo = new BMap.Geocoder();
    var adds = [
        "包河区金寨路1号（金寨路与望江西路交叉口）",
        "庐阳区凤台路209号（凤台路与蒙城北路交叉口）",
        "蜀山区金寨路217号(近安医附院公交车站)",
        "蜀山区梅山路10号(近安徽饭店) ",
        "蜀山区 长丰南路159号铜锣湾广场312室",
        "合肥市寿春路93号钱柜星乐町KTV（逍遥津公园对面）",
        "庐阳区长江中路177号",
        "新站区胜利路89"
    ];
    function bdGEO(){
        var add = adds[index];
        geocodeSearch(add);
        index++;
    }
    function geocodeSearch(add){
        if(index < adds.length){
            setTimeout(window.bdGEO,400);
        }
        myGeo.getPoint(add, function(point){
            if (point) {
                document.getElementById("result").innerHTML +=  index + "、" + add + ":" + point.lng + "," + point.lat + "</br>";
                var address = new BMap.Point(point.lng, point.lat);
                addMarker(address,new BMap.Label(index+":"+add,{offset:new BMap.Size(20,-10)}));
            }
        }, "合肥市");
    }
    // 编写自定义函数,创建标注
    function addMarker(point,label){
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
        marker.setLabel(label);
    }
</script>