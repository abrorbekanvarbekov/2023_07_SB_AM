<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="APITest2"/>
<%@include file="../common/head.jsp" %>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=32dc678e0de641535a993b3407a3777d"></script>
    <div id="map" class="mx-auto mt-10" style="width:50%;height:50%;"></div>

    <script>
        let container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
        let options = { //지도를 생성할 때 필요한 기본 옵션
            center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
            level: 3 //지도의 레벨(확대, 축소 정도)
        };

        let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

        // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
        let mapTypeControl = new kakao.maps.MapTypeControl();

        // 지도 타입 컨트롤을 지도에 표시합니다
        map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

        function getInfo() {
            // 지도의 현재 중심좌표를 얻어옵니다
            let center = map.getCenter();

            // 지도의 현재 레벨을 얻어옵니다
            let level = map.getLevel();

            // 지도타입을 얻어옵니다
            let mapTypeId = map.getMapTypeId();

            // 지도의 현재 영역을 얻어옵니다
            let bounds = map.getBounds();

            // 영역의 남서쪽 좌표를 얻어옵니다
            let swLatLng = bounds.getSouthWest();

            // 영역의 북동쪽 좌표를 얻어옵니다
            let neLatLng = bounds.getNorthEast();

            // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
            let boundsStr = bounds.toString();


            let message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
            message += '경도 ' + center.getLng() + ' 이고 <br>';
            message += '지도 레벨은 ' + level + ' 입니다 <br> <br>';
            message += '지도 타입은 ' + mapTypeId + ' 이고 <br> ';
            message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
            message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';
            // 개발자도구를 통해 직접 message 내용을 확인해 보세요.
            console.log(message);
        }
        getInfo();
    </script>


<%@include file="../common/foot.jsp" %>