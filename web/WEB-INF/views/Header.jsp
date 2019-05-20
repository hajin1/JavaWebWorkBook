<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:useBean id="member" scope="session" class="vo.Member" />
<div style="background-color: #00008b;color: #ffffff; height: 20px; padding: 5px;">
    SPMS(Simple Project Management System)
    <span style="float:right";>
        <%=member.getName()%>
        ${sessionScope.member.name}
        <a style="color:white;" href="<%=request.getContextPath()%>/auth/logout.do">로그아웃</a>
    </span>
</div>
