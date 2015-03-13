
<%@page import="com.sharethyapp.helper.LoginHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.sharethyapp.helper.BookResult"%>

<article class="hero clearfix">
    <div class="col_100">
        <h1>Contribute a Book</h1>
        <%if (LoginHelper.isLoggedIn(request)) {%>
        <p>Keep Adding...Keep Reading</p>
        <p>Some more details</p>

        <form action="addbook.do" method="get" class="form">

            <table border="2" width="100%">
                <tr>
                <td align="center"><label for="name">Search by ISBN</label></td>
                <td align="center"><input type="text" name="ISBN" id="ISBN" value="" /></td></tr>
                <tr>
                <td align="center"><label for="name">Search by book name</label></td>
                <td align="center"><input type="text" name="bookName" id="bookName" value="" /></td></tr>
                <tr>
                <td align="center"><label for="name">Search by author name</label></td>
                <td align="center"><input type="text" name="authorName" id="authorName" value="" /></td></tr>
                <tr>
                <td align="center"><label for="name">Search by publisher name</label></td>
                <td align="center"><input type="text" name="publisherName" id="publisherName" value="" /></td></tr>
                <tr>
                <td align="center"><label for="name">Search by year</label></td>
                <td align="center"> <label for="name">From</label><input type="text" name="fromYear" id="fromYear" value="" /><label for="name">To</label><input type="text" name="toYear" id="toYear" value="" /></td></tr>
            </table>
            <br/>
            <div>
                <button type="submit" class="button" onclick="submit">Submit</button>
            </div>

            <br/>
            <%
                List<BookResult> rs = (List<BookResult>) request.getAttribute("listOfBooks");
                if (rs != null) {
                    if (rs.isEmpty()) {

            %>
            <div>No Result Fetched.</div> 
            <%} else { %>
            <table width="100%" class="table">
                <tr>
                <th><a href="addbook.do?Order=ISBN">ISBN</a></th>
                <th><a href="addbook.do?Order=Title">Title</a></th>
                <th><a href="addbook.do?Order=AuthorName">Authors</a></th>
                <th><a href="addbook.do?Order=Publisher">Publisher</a></th>
                <th><a href="addbook.do?Order=Year">Year</a></th>
                <th><a href="addbook.do?Order=Rating">Rating</a></th>
                <th>Add Book</th>
                </tr>
                <% for (BookResult bt : rs) {
                        pageContext.setAttribute("record", bt);
                %>

                <tr>
                <td align="center"><a href="viewbook.do?isbn=${record.isbn}">${record.isbn }</a></td>
                <td align="center">${record.title }</td>
                <td align="center">${record.authors }</td>
                <td align="center">${record.publisher }</td>
                <td align="center">${record.year }</td>
                <td align="center">${record.rating }</td>
                <td><a href="addphysicalbook.do?ISBN=${record.isbn}">Contribute</a></td>
                </tr>
                <%}%>
            </table>
            <%}%>
            <%}%>
            <br/>
            <br/>
            <div>
                <label for="name">In case you don't find book to be added in our master repository. <a href="suggestBook.jsp">Suggest us </a>the book and our moderator will verify it. </label>
            </div>

        </form>
    </div>

    <p class="col_50" style="color: red">
        ${requestScope.error}
    </p>
    <%} else {%>
    <p class="col_50" style="color: #444">
        Not logged in. Please login.
    </p>
    <br/>
    <%}%>
</article>

<br/>
<br/>

<article class="hero clearfix">
    <div class="col_100">

    </div>
</article>