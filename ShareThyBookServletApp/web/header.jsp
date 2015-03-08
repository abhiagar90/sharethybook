
<%@page import="com.sharethyapp.helper.LoginHelper"%>
<header class="header clearfix">
    <div class="logo">Share Thy Book - A crowd-sourced library for IITD students!</div>

    <nav class="menu_main">
        <ul>

           <%if (LoginHelper.isLoggedIn(request)) {%>
                    <li class="active"><a href="profile.do?entrynumber=${entrynumber}">${sessionScope.entrynumber}</a></li>
                    <li class="active"><a href="logout.do">Logout</a></li>
            <%} else {%>
                    <li class="active"><a href="login.jsp">Login</a></li>
            <%}%>
            
            <li class="active"><a href="welcome.jsp">Home</a></li>
            <li class="active"><a href="">Search books</a></li>
            <li class="active"><a href="">About us</a></li>
        </ul>
    </nav>
</header>