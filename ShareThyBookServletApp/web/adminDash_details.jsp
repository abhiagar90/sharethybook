<%@page import="com.sharethyapp.helper.WishList"%>
<%@page import="com.sharethyapp.helper.WishListAggregated"%>
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
    
    
    
    <h1>${sessionScope.entrynumber} Dashboard</h1>
    <%
        Object rcvd = request.getAttribute("wish");

        if (rcvd != null) {
    %>
    <h3>Wish List Summary</h3>
    <table class="table">
        <tr>
        <th>ISBN</th>
        <th>Count of wishes!</th>
        </tr>

        <%
            for (WishListAggregated msg : (List<WishListAggregated>) rcvd) {
                pageContext.setAttribute("w", msg);
        %>
        <tr>
        <td>${w.isbn}</td>
        <td>${w.count}</td>
        </tr>

        <%
                } //for each
            } //if condition for each type of view.
        %>
    </table>



    <h3>Wish List ALL</h3>
    <table class="table">
        <tr>
        <th>ISBN</th>
        <th>Entry Number</th>
        <th>Date</th>
        </tr>

        <%
            Object wishallrcvd = request.getAttribute("wishall");

            if (rcvd != null) {
                for (WishList wsg : (List<WishList>) wishallrcvd) {
                    pageContext.setAttribute("w", wsg);
        %>
        <tr>
        <td>${w.isbn}</td>
        <td>${w.entrynumber}</td>
        <td>${w.date}</td>
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