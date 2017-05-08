/**
 * Created by laowang on 17-5-1.
 */
$(document).ready(function () {
    //baidu map
    var map = new BMap.Map("l-map");
    map.centerAndZoom(new BMap.Point(121.473299,31.841243), 10);
    map.enableScrollWheelZoom(true);

    var mapHeight = $(document.body).height();
    $("#l-map").css("height",mapHeight);
    var dataSource = $("#data-source").val();
    var lowPrice = $('#low-price').val().trim();
    var highPrice = $('#high-price').val().trim();
    var workPlace = $('#workplace').val().trim();
    var mapLevel = map.getZoom();
    var isAnjuke = false;
    var isPersonal = false;
    if(dataSource === '2'){
        isAnjuke = false;
        isPersonal = true;
    }else if(dataSource === '3'){
        isAnjuke = true;
        isPersonal = false;
    }

    $.ajax({
        url:'/zhaofang',
        type :'POST',
        data: {
            'dataSource' : dataSource,
            'lowPrice' : lowPrice,
            'highPrice' : highPrice,
            'workPlace' : workPlace,
            'mapLevel' : mapLevel,
            'isAnjuke' : isAnjuke,
            'isPersonal' : isPersonal
        },
        success: function (data) {
            //请求成功，对数据进行处理
            console.log(data);
            alert('成功');
        },
        error: function () {
            //请求失败，弹出框
            alert('失败');
        }

    });
    $("#submit-data").on("click", function () {
        dataSource = $("#data-source").val();
        lowPrice = $('#low-price').val().trim();
        highPrice = $('#high-price').val().trim();
        workPlace = $('#workplace').val().trim();
        mapLevel = map.getZoom();
        if(dataSource === '1'){
            isAnjuke = false;
            isPersonal = false;
        }else if(dataSource === '2'){
            isAnjuke = false;
            isPersonal = true;
        }else if(dataSource === '3'){
            isAnjuke = true;
            isPersonal = false;
        }
        //ajax
        $.ajax({
            url:'/zhaofang',
            type :'POST',
            data: {
                'dataSource' : dataSource,
                'lowPrice' : lowPrice,
                'highPrice' : highPrice,
                'workPlace' : workPlace,
                'mapLevel' : mapLevel,
                'isAnjuke' : isAnjuke,
                'isPersonal' : isPersonal
            },
            success: function (data) {
                //请求成功，对数据进行处理
                alert('成功');
            },
            error: function () {
                //请求失败，弹出框
                alert('失败');
            }

        });

    });
    /*baidu map */
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
    map.addEventListener("zoomend", function(){
        dataSource = $("#data-source").val();
        lowPrice = $('#low-price').val().trim();
        highPrice = $('#high-price').val().trim();
        workPlace = $('#workplace').val().trim();
        mapLevel = map.getZoom();
        if(dataSource === '1'){
            isAnjuke = false;
            isPersonal = false;
        }else if(dataSource === '2'){
            isAnjuke = false;
            isPersonal = true;
        }else if(dataSource === '3'){
            isAnjuke = true;
            isPersonal = false;
        }
        //发送ajax 请求
        $.ajax({
            url:'/zhaofang',
            type :'POST',
            data: {
                'dataSource' : dataSource,
                'lowPrice' : lowPrice,
                'highPrice' : highPrice,
                'workPlace' : workPlace,
                'mapLevel' : mapLevel,
                'isAnjuke' : isAnjuke,
                'isPersonal' : isPersonal
            },
            success: function (data) {
                //请求成功，对数据进行处理
                console.log(data);
                alert('成功');
            },
            error: function () {
                //请求失败，弹出框
                alert('失败');
            }

        });

    });
});
