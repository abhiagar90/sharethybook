<%@page import="com.sharethyapp.helper.TransactionHistory"%>
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
    <br/>



    <%
        Object wishallrcvd = request.getAttribute("wishall");

        if (wishallrcvd != null) {
    %>
    <h3>Wish List ALL</h3>
    <table class="table">
        <tr>
        <th>ISBN</th>
        <th>Entry Number</th>
        <th>Date</th>
        </tr>

        <%
            for (WishList wsg : (List<WishList>) wishallrcvd) {
                pageContext.setAttribute("w", wsg);
        %>
        <tr>
        <td>${w.isbn}</td>
        <td>${w.entrynumber}</td>
        <td>${w.date}</td>
        </tr>

        <%
                } //for each
            } //if condition
        %>
    </table>
    <br/>


    <%
        Object canceled = request.getAttribute("canceled");
        if (canceled != null) {
    %>
    <h3>Canceled Transactions</h3>
    <table class="table" width="100%">

        <tr>
        <th>TransactionID</th>
        <th>From ID</th>
        <th>To ID</th>
        <th>BookID</th>
        <th>Start Date</th>
        <th>Last Update</th>
        <th>Status</th>            
        </tr>

        <%
            for (TransactionHistory tempbook : (List<TransactionHistory>) canceled) {
                pageContext.setAttribute("tempbook", tempbook);
        %>
        <tr>
        <td>${tempbook.getTransactionID()}</td>
        <td><a href="profile.do?entrynumber=${tempbook.getFromID()}">${tempbook.getFromID()}</a></td>
        <td><a href="profile.do?entrynumber=${tempbook.getToID()}">${tempbook.getToID()}</a></td>
        <td>${tempbook.getBookID()}</td>
        <td>${tempbook.getTransStartDate()}</td>
        <td>${tempbook.getLastUpdate()}</td>
        <td>${tempbook.getStatus()}</td>           
        </tr>
        <%
                }
            }
        %>



    </table>
    <br/>
    
    
    
    <%
        Object ended = request.getAttribute("ended");
        if (ended != null) {
    %>
    <h3>Completed Transactions</h3>
    <table class="table" width="100%">

        <tr>
        <th>TransactionID</th>
        <th>From ID</th>
        <th>To ID</th>
        <th>BookID</th>
        <th>Start Date</th>
        <th>Last Update</th>
        <th>Status</th>            
        </tr>

        <%
            for (TransactionHistory tempbook : (List<TransactionHistory>) ended) {
                pageContext.setAttribute("tempbook", tempbook);
        %>
        <tr>
        <td>${tempbook.getTransactionID()}</td>
        <td><a href="profile.do?entrynumber=${tempbook.getFromID()}">${tempbook.getFromID()}</a></td>
        <td><a href="profile.do?entrynumber=${tempbook.getToID()}">${tempbook.getToID()}</a></td>
        <td>${tempbook.getBookID()}</td>
        <td>${tempbook.getTransStartDate()}</td>
        <td>${tempbook.getLastUpdate()}</td>
        <td>${tempbook.getStatus()}</td>           
        </tr>
        <%
                }
            }
        %>
    </table>
    <br/>
    
    <%
        Object pending = request.getAttribute("pending");
        if (pending != null) {
    %>
    <h3>Pending Transactions</h3>
    <table class="table" width="100%">

        <tr>
        <th>TransactionID</th>
        <th>From ID</th>
        <th>To ID</th>
        <th>BookID</th>
        <th>Start Date</th>
        <th>Last Update</th>
        <th>Status</th>            
        </tr>

        <%
            for (TransactionHistory tempbook : (List<TransactionHistory>) pending) {
                pageContext.setAttribute("tempbook", tempbook);
        %>
        <tr>
        <td>${tempbook.getTransactionID()}</td>
        <td><a href="profile.do?entrynumber=${tempbook.getFromID()}">${tempbook.getFromID()}</a></td>
        <td><a href="profile.do?entrynumber=${tempbook.getToID()}">${tempbook.getToID()}</a></td>
        <td>${tempbook.getBookID()}</td>
        <td>${tempbook.getTransStartDate()}</td>
        <td>${tempbook.getLastUpdate()}</td>
        <td>${tempbook.getStatus()}</td>           
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