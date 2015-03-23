<%@page import="com.sharethyapp.dbclasses.UserTable"%>
<%@page import="com.sharethyapp.dbclasses.UserTableDB"%>
<%@page import="com.sharethyapp.dbclasses.SearchBooks"%>
<%@page import="com.sharethyapp.helper.BookResult"%>
<%@page import="java.util.List"%>
<%@page import="com.sharethyapp.helper.LoginHelper"%>
<article class="hero clearfix">

    <p style="color: red">
        ${errorMsg}
    </p>
    <p style="color: #6aa12e">
        ${infoMsg}
    </p>
    <div class="col_100">
        <img width="100%" height="600" src="book.jpg"/>
    </div>
</article>

<article class="hero clearfix">
    <div class="col_100">
        <h1>Welcome</h1>
        <p>Blah Blah Blah</p>
        <p>Its free, crowd-sourced, blah, blah!!</p>

        <%if (!LoginHelper.isLoggedIn(request)) {%>
        <a href="signup.jsp">Sign up here</a> 
        <%} else {%>

        <%
            Object topbooks = new SearchBooks().getTop10Books();
            if (topbooks != null) {

        %>
        <h3>Top Rated Books</h3>
        <table width="100%" class="table">
            <tr>
            <th align="center">ISBN</th>
            <th align="center">Title</th>
            <th align="center">Rating</th>
            </tr>
            <% for (BookResult bt : (List<BookResult>) topbooks) {
                    pageContext.setAttribute("record", bt);
            %>

            <tr>
            <td align="center"><a href="viewbook.do?isbn=${record.isbn}">${record.isbn }</a></td>
            <td align="center">${record.title }</td>
            <td align="center">${record.rating }</td>
            </tr>
            <%}%>
        </table>
        <%}%>
        <br/>


        <%
            Object topcontries = new UserTableDB().getTopContributors();
            if (topcontries != null) {

        %>
        <h3>Top Contributors</h3>
        <table width="100%" class="table">
            <tr>
            <th align="center">Entrynumber</th>
            <th align="center">No. of contributions</th>
            </tr>
            <% for (UserTable bt : (List<UserTable>) topcontries) {
                    pageContext.setAttribute("record", bt);
            %>

            <tr>
            <td align="center"><a href="profile.do?entrynumber=${record.entrynumber}">${record.entrynumber}</a></td>
            <td align="center">${record.booksContri}</td>
            </tr>
            <%}%>
            
        </table>
        <%}%>
        <br/>





        <%}%>
    </div>
</article>


