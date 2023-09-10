<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="Main"/>
<%@include file="../common/head.jsp" %>

<script>
    const API_KEY = 'JpQRK9Y1WUvVdXN091jAp6vgQLU3H2k%2B36pjBqn9C7pWPGLtEa57GwDVkUAZbImsMCfQj8IN5XvV9W%2BHjd1%2FFg%3D%3D'

    async function getData() {
        let url = 'http://apis.data.go.kr/1262000/CountryCovid19SafetyServiceNew/getCountrySafetyNewsListNew?numOfRows=10&returnType=JSON&pageNo=1&serviceKey=' + API_KEY
        const response = await fetch(url);

        if (response.ok) {
            const data = await response.json();
            console.log(data)
            // $('.API-content').html(data.data[0].title)
            $('.API-content').html(data.data[0].txt_origin_cn)
        }
    }

    getData();
</script>

<section class="container mx-auto">
    <div class="API-content">

    </div>
</section>
<%@include file="../common/foot.jsp" %>