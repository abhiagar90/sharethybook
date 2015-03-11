<%@page import="com.sharethyapp.helper.LoginHelper"%>
<article class="hero clearfix">
    <div class="col_100">
        <p style="color: #6aa12e">
            ${infoMsg}
        </p>
        <%if (LoginHelper.isLoggedIn(request)) {%>
        <h1>${masterbook.title}</h1>

        <!--
        TODO: Image work
        <div class="img_floatright">    
            <img width="60" height="80" src="profileimage.do?entrynumber=user.entrynumber"></img>
        </div>
        -->

        <p>
            ISBN: ${masterbook.isbn} <br/>
            Title: ${masterbook.title} <br/>
            Year Of Publication: ${masterbook.year} <br/>
            Publisher: ${masterbook.publisher} <br/>
            Overall Rating: ${masterbook.rating} <br/>
            Authors: ${masterbook.authorList} <br/>
            Reviews: ${masterbook.reviewList}
        </p>
        <!--
        <div>
            <a href="addbook.jsp">Contribute a book</a> <br/>
            <a href="beforeedit.do?entrynumber=${user.entrynumber}">Edit Profile Details</a> <br/>
            <a href="changePassword.jsp">Change Password <a/> <br/>
                <a href="editProfileImage.jsp">Edit Profile Image</a> <br/>
        </div>
        -->
        <%} else {%>
        <p class="col_50" style="color: #444">
            Not logged in. Please login.
        </p>
        <br/>
        <%}%>
    </div>

</article>

<br/>
<br/>