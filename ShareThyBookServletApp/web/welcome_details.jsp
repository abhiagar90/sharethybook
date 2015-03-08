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
        <p> 
            <%if (!LoginHelper.isLoggedIn(request)) {%>
                <a href="signup.jsp">Sign up here</a> 
            <%}%>
        </p>
    </div>
</article>


