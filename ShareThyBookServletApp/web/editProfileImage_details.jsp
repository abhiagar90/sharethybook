<%@page import="com.sharethyapp.dbclasses.UserTable"%>
<%@page import="com.sharethyapp.helper.LoginHelper"%>
<article class="hero clearfix">
    <div class="col_100">
        <h1>Change Password</h1>

        <%if (LoginHelper.isLoggedIn(request)) {%>
        <p>
            1. All three password fields should not be empty. <br/> 
            2. New Password and its re-type should match case wise. <br/>
        </p>

        <form action="editImage.do" method="post" class="form" enctype="multipart/form-data">
            <input type="hidden" name="entrynumber" id="entrynumber" value="${sessionScope.entrynumber}" />
            <div class="col_100">

                <h3>Old User</h3>
                <table class="table">
                    
                    <tr>
                    <td><label for="photo">New Image</label><br/></td>
                    <td><input type="file" name="photo" id="photo" /></td>
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

<article class="hero clearfix">
    <div class="col_100">

    </div>
</article>