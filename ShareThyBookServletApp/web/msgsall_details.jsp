<%@page import="com.sharethyapp.helper.Messages"%>
<%@page import="com.sharethyapp.dbclasses.UserTable"%>
<%@page import="com.sharethyapp.helper.RateAndReview"%>
<%@page import="com.sharethyapp.helper.PhysicalBooks"%>
<%@page import="java.util.List"%>
<%@page import="com.sharethyapp.helper.BookResult"%>
<%@page import="com.sharethyapp.helper.LoginHelper"%>

<article class="hero clearfix">

    <p style="color: #6aa12e">
        ${infoMsg}
    </p>
    <%if (LoginHelper.isLoggedIn(request)) {%>
    <h1>Message View : ${sessionScope.entrynumber}</h1>

    <h3>Received Messages</h3>
    <table class="table">
        <tr>
        <th>Message ID</th>
        <th>From</th>
        <th>Date</th>
        <th>Read by you</th>
        </tr>

        <%
            Object rcvd = request.getAttribute("rcvd");

            if (rcvd != null) {
                for (Messages msg : (List<Messages>) rcvd) {
                    pageContext.setAttribute("msg", msg);
        %>
        <tr>
        <td><a href="readmsg.do?mid=${msg.messageid}">${msg.messageid}</a></td>
        <td><a href="profile.do?entrynumber=${msg.fromid}">${msg.fromid}</a></td>
        <td>${msg.date}</td>

        <%if (!msg.isStatus()) {%>
        <td><b>Not Read</b></td>
        <%} else {%>
        <td>Read</td>
        <%}%>
        </tr>

        <%
                }
            }
        %>
    </table>
    <br/>

    <h3>Sent Messages</h3>
    <table class="table">
        <tr>
        <th>Message ID</th>
        <th>To</th>
        <th>Date</th>
        <th>Read by receiver</th>
        </tr>

        <%
            Object sent = request.getAttribute("sent");

            if (sent != null) {
                for (Messages msg : (List<Messages>) sent) {
                    pageContext.setAttribute("msg", msg);
        %>
        <tr>
        <td><a href="readmsg.do?mid=${msg.messageid}">${msg.messageid}</a></td>
        <td><a href="profile.do?entrynumber=${msg.toid}">${msg.toid}</a></td>
        <td>${msg.date}</td>

        <%if (!msg.isStatus()) {%>
        <td><b>Not Read</b></td>
        <%} else {%>
        <td>Read</td>
        <%}%>

        </tr>
        <%
                }
            }
        %>
    </table>
    <br/>

    <%} else {%>
    <p class="col_50" style="color: #444">
        Not logged in. Please login.
    </p>
    <br/>
    <%}%>


</article>

<br/>
<br/>