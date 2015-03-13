<%@page import="com.sharethyapp.helper.LoginHelper"%>
<article class="hero clearfix">
    <div class="col_100">
        <h1>Suggest Book</h1>
        <%if (LoginHelper.isLoggedIn(request)) {%>

        <form action="suggestbookservlet.do" method="post" class="form">
            <p style="color: #6aa12e">
                ${requestScope.Message}
            </p>
            <div class="col_100">

                <table class="table">
                    <tr></tr>
                    <tr>
                    <td><label for="name">ISBN</label><br/></td>
                    <td><input type="text" name="ISBN" id="ISBN" value="" /></td>
                    </tr>
                    <tr>
                    <td><label for="name">Title</label><br/></td>
                    <td><input type="text" name="Title" id="Title" value="" /></td>
                    </tr>
                    <tr>
                    <td><label for="name">Year</label><br/></td>
                    <td><input type="text" name="Year" id="Year" value="" /></td>
                    </tr>
                    <tr>
                    <td><label for="name">Author</label><br/></td>
                    <td><input type="text" name="Author" id="Author" value="" /></td>
                    </tr>
                    <tr>
                    <td><label for="name">Publisher</label><br/></td>
                    <td><input type="text" name="Publisher" id="Publisher" value="" /></td>
                    </tr>

                </table><br/>


            </div>
            <div>
                <button type="submit" class="button" onclick="submit">Submit</button>
            </div>
            <br/>
            <p class="col_50" style="color: red">
                ${requestScope.error}
            </p>
            <br/>
        </form>
        <%}%>

    </div>
</article>
