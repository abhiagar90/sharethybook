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

        <form action="changePassword.do" method="post" class="form">
            <input type="hidden" name="EntryNumber" id="EntryNumber" value="${sessionScope.entrynumber}" />
            <div class="col_100">

                <h3>Old User</h3>
                <table class="table">
                    
                    <tr>
                    <td><label for="oldpass">Old Password*</label><br/></td>
                    <td><input type="password" name="oldpass" id="oldpass"/></td>
                    </tr>
                    <tr>
                    <td><label for="newpass">New Password*</label><br/></td>
                    <td><input type="password" name="newpass" id="newpass"/></td>
                    </tr>
                    <tr>
                    <td><label for="repass">Re-type New Password*</label><br/></td>
                    <td><input type="password" name="repass" id="repass"/></td>
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