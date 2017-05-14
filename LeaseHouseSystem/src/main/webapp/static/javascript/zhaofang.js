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
    var location = 0;
    var level = 9;
    var centerPoint;

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
        marker.addEventListener("click", function(){
            this.openInfoWindow(infoWindow);
        });
        map.addOverlay(marker);
    }
    function bdGEO(){
        if(index < adds.length){
            var add = adds[index].houseLocation;
            var link = adds[index].houseLink;
            var message = adds[index].houseMessage;
            var price = adds[index].housePrice;
        }
        if(add !== null || add !== 'undefined')
            geocodeSearch(add, link, message, price);
        index++;
    }
    function geocodeSearch(add, link, message, price){
        if(index < adds.length){
            setTimeout(bdGEO,300);
        }
        myGeo.getPoint(add, function(point){
            if (point) {
                if(workPlace!== null && workPlace!== '' && centerPoint !== null && centerPoint!== 'undefined'){
                    console.log(workPlace);
                    console.log(centerPoint);
                    if(getDistance(centerPoint, point) <= 6000){
                        if(parseInt(price) < 2000){
                            var address = new BMap.Point(point.lng, point.lat);
                            addMarker(address, greenIcon, new BMap.InfoWindow("<a href='"+link+"' target='_blank'>"+message+"</a> <br> <label>"+price+"</label>"));
                        }else if(parseInt(price) < 4000){
                            var address = new BMap.Point(point.lng, point.lat);
                            addMarker(address, blueIcon, new BMap.InfoWindow("<a href='"+link+"' target='_blank'>"+message+"</a> <br> <label>"+price+"</label>"));
                        }else if(parseInt(price) < 6000){
                            var address = new BMap.Point(point.lng, point.lat);
                            addMarker(address, yellowIcon, new BMap.InfoWindow("<a href='"+link+"' target='_blank'>"+message+"</a> <br> <label>"+price+"</label>"));
                        }else if(parseInt(price) < 8000){
                            var address = new BMap.Point(point.lng, point.lat);
                            addMarker(address, pinkIcon, new BMap.InfoWindow("<a href='"+link+"' target='_blank'>"+message+"</a> <br> <label>"+price+"</label>"));
                        }else{
                            var address = new BMap.Point(point.lng, point.lat);
                            addMarker(address, redIcon, new BMap.InfoWindow("<a href='"+link+"' target='_blank'>"+message+"</a> <br> <label>"+price+"</label>"));
                        }
                    }
                }else{
                    if(parseInt(price) < 2000){
                        var address = new BMap.Point(point.lng, point.lat);
                        addMarker(address, greenIcon, new BMap.InfoWindow("<a href='"+link+"' target='_blank'>"+message+"</a> <br> <label>"+price+"</label>"));
                    }else if(parseInt(price) < 4000){
                        var address = new BMap.Point(point.lng, point.lat);
                        addMarker(address, blueIcon, new BMap.InfoWindow("<a href='"+link+"' target='_blank'>"+message+"</a> <br> <label>"+price+"</label>"));
                    }else if(parseInt(price) < 6000){
                        var address = new BMap.Point(point.lng, point.lat);
                        addMarker(address, yellowIcon, new BMap.InfoWindow("<a href='"+link+"' target='_blank'>"+message+"</a> <br> <label>"+price+"</label>"));
                    }else if(parseInt(price) < 8000){
                        var address = new BMap.Point(point.lng, point.lat);
                        addMarker(address, pinkIcon, new BMap.InfoWindow("<a href='"+link+"' target='_blank'>"+message+"</a> <br> <label>"+price+"</label>"));
                    }else{
                        var address = new BMap.Point(point.lng, point.lat);
                        addMarker(address, redIcon, new BMap.InfoWindow("<a href='"+link+"' target='_blank'>"+message+"</a> <br> <label>"+price+"</label>"));
                    }
                }
            }
        }, "上海市");
    }

    //getpoint lng and lat
    function getPoint(add) {
        myGeo.getPoint(add, function (point) {
            centerPoint = point;
        })
    }

    function getDistance(point1, point2) {
        console.log(point1.lng+","+point1.lat);
        console.log(point2.lng+","+point2.lat);
        console.log((map.getDistance(point1, point2)).toFixed(1));
        return (map.getDistance(point1, point2)).toFixed(1);
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
        var level = 1;
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
                adds = data.houseDTOList;
                console.log(level);
                console.log(mapLevel);
                if( mapLevel > level){
                    index = 0;
                    map.clearOverlays();
                    bdGEO();
                }

                level = mapLevel;
            },
            error: function () {
                //请求失败，弹出框
                console.log("error");
            }

        });

    });
    map.addEventListener("zoomend", function(){
        dataSource = $("#data-source").val();
        lowPrice = $('#low-price').val().trim();
        highPrice = $('#high-price').val().trim();
        workPlace = $('#workplace').val().trim();
        mapLevel = map.getZoom();
        console.log("work"+workPlace);
        if(workPlace !== null && workPlace!== ''){
            getPoint(workPlace);
        }
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
        if(level < mapLevel || location !== dataSource){
            console.log(level);
            console.log(mapLevel);
            console.log(location);
            console.log(dataSource);
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
                    adds = data.houseDTOList;
                    index = 0;
                    map.clearOverlays();
                    level = mapLevel;
                    location = dataSource;
                    bdGEO();
                    // alert('成功');
                },
                error: function () {
                    //请求失败，弹出框
                    // alert('失败');
                    console.log("error");
                }

            });
        }
    });
});
