<%@page import="java.util.List"%>
<%@page import="com.sharethyapp.helper.BookResult"%>

<article class="hero clearfix">
    <div class="col_100">
        <h1>Add Book</h1>
        <p>Keep Adding...Keep Reading</p>
        <p>Some more details</p>

        <form action="addbook.do" method="get" class="form">
            <div class="col_100">
                <h1></h1>
                <br/>
                <label for="name">Search by name</label>
                <input type="text" name="bookName" id="bookName" value="" /><br/>

            </div>
            <div>
                <button type="submit" class="button" onclick="submit">Submit</button>
            </div>
            <%
                List<BookResult> rs = (List<BookResult>) request.getAttribute("listOfBooks");
                if (rs != null) {


            %>
            <table width="100%">
                <tr>
                <th>ISBN</th>
                <th>Title</th>
                <th>Year</th>
                <th>Publisher</th>
                <th>Rating</th>
                </tr>
                <% for (BookResult bt : rs) {
                        pageContext.setAttribute("record", bt);
                %>

                <tr>
                <td>${record.isbn }</td>
                <td>${record.title }</td>
                <td>${record.year }</td>
                <td>${record.publisher }</td>
                <td>${record.rating }</td>

                </tr>
                <%}%>
            </table>
            <%}%>
        </form>
    </div>
</article>

<br/>
<br/>

<article class="hero clearfix">
    <div class="col_100">

    </div>
</article>