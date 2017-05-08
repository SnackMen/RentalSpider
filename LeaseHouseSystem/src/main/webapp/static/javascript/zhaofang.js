/**
 * Created by laowang on 17-5-1.
 */
$(document).ready(function () {
    //baidu map
    var map = new BMap.Map("l-map");
    map.centerAndZoom(new BMap.Point(121.473299,31.841243), 10);
    map.enableScrollWheelZoom(true);
    var myGeo = new BMap.Geocoder();
    var adds;
    var index = 0;
    var iconColor;

    /*show icon*/
    var blueIcon = new BMap.Icon('../../static/pic/blue_location.png', new BMap.Size(24,36),{
        anchor : new BMap.Size(10,30),
        infoWindowAnchor: new BMap.Size(10, 0)
    });
    var greenIcon = new BMap.Icon('../../static/pic/green_location.png', new BMap.Size(24,36),{
        anchor : new BMap.Size(10,30),
        infoWindowAnchor: new BMap.Size(10, 0)
    });
    var pinkIcon = new BMap.Icon('../../static/pic/pink_location.png', new BMap.Size(24,36),{
        anchor : new BMap.Size(10,30),
        infoWindowAnchor: new BMap.Size(10, 0)
    });
    var redIcon = new BMap.Icon('../../static/pic/red_location.png', new BMap.Size(24,36),{
        anchor : new BMap.Size(10,30),
        infoWindowAnchor: new BMap.Size(10, 0)
    });
    var yellowIcon = new BMap.Icon('../../static/pic/yellow_location.png', new BMap.Size(24,36),{
        anchor : new BMap.Size(10,30),
        infoWindowAnchor: new BMap.Size(10, 0)
    });

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
    function addMarker(point, icon, infoWindow) {
        var marker = new BMap.Marker(point, {
            icon : icon
        });
        marker.addEventListener("mouseover", function(){
            this.openInfoWindow(infoWindow);
        });
        map.addOverlay(marker);
    }
    function bdGEO(){
        var add = adds[index].houseLocation;
        var price = adds[index].housePrice;
        if(parseInt(price) < 2000){
            iconColor = greenIcon;
        }
        else if(parseInt(price) < 4000){
            iconColor = blueIcon;
        }
        else if(parseInt(price) < 6000){
            iconColor = yellowIcon;
        }
        else if(parseInt(price) < 8000){
            iconColor = pinkIcon;
        }
        else
            iconColor = redIcon;
        geocodeSearch(add);
        index++;
    }
    function geocodeSearch(add){
        if(index < adds.length){
            setTimeout(bdGEO,400);
        }
        myGeo.getPoint(add, function(point){
            if (point) {
                var address = new BMap.Point(point.lng, point.lat);
                addMarker(address, iconColor, new BMap.InfoWindow("<a href='https://www.baidu.com/'>显示内容</a>"));
            }
        }, "上海市");
    }
    
    //init
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
            // var index = 0;
            adds = data.houseDTOList;
            bdGEO();
            // var mkr = new BMap.Marker(new BMap.Point(121.473299,31.841243), {
            //     icon: blueIcon
            // });
            // // var opts = {title : '<span style="font-size:14px;color:#0A8021">this is blue</span>'};
            // var infoWindow =new BMap.InfoWindow("<a href='https://www.baidu.com/'>显示内容</a>");  // 创建信息窗口对象，引号里可以书写任意的html语句。
            // mkr.addEventListener("mouseover", function(){
            //     this.openInfoWindow(infoWindow);
            // });
            // map.addOverlay(mkr);
            // alert('成功');
        },
        error: function () {
            //请求失败，弹出框
            console.log("error");
            // alert('失败');
        }

    });
    //submit
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
                // alert('成功');
                console.log(data);
            },
            error: function () {
                //请求失败，弹出框
                console.log("error");
            }

        });

    });
    /*baidu map */
    // var index = 0;
    // var myGeo = new BMap.Geocoder();
    // var adds = [
    //     "包河区金寨路1号（金寨路与望江西路交叉口）",
    //     "庐阳区凤台路209号（凤台路与蒙城北路交叉口）",
    //     "蜀山区金寨路217号(近安医附院公交车站)",
    //     "蜀山区梅山路10号(近安徽饭店) ",
    //     "蜀山区 长丰南路159号铜锣湾广场312室",
    //     "合肥市寿春路93号钱柜星乐町KTV（逍遥津公园对面）",
    //     "庐阳区长江中路177号",
    //     "新站区胜利路89"
    // ];
    // function bdGEO(){
    //     var add = adds[index];
    //     geocodeSearch(add);
    //     index++;
    // }
    // function geocodeSearch(add){
    //     if(index < adds.length){
    //         setTimeout(window.bdGEO,400);
    //     }
    //     myGeo.getPoint(add, function(point){
    //         if (point) {
    //             document.getElementById("result").innerHTML +=  index + "、" + add + ":" + point.lng + "," + point.lat + "</br>";
    //             var address = new BMap.Point(point.lng, point.lat);
    //             addMarker(address,new BMap.Label(index+":"+add,{offset:new BMap.Size(20,-10)}));
    //         }
    //     }, "合肥市");
    // }
    // 编写自定义函数,创建标注
    // function addMarker(point,label){
    //     var marker = new BMap.Marker(point);
    //     map.addOverlay(marker);
    //     marker.setLabel(label);
    // }
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
                // alert('成功');
            },
            error: function () {
                //请求失败，弹出框
                // alert('失败');
                console.log("error");
            }

        });

    });
});
