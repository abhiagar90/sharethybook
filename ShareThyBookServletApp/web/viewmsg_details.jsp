<%@page import="com.sharethyapp.dbclasses.UserTable"%>
<%@page import="com.sharethyapp.dbclasses.UserTableDB"%>
<%@page import="com.sharethyapp.helper.UtilitiesHelper"%>
<%@page import="com.sharethyapp.helper.RateAndReview"%>
<%@page import="com.sharethyapp.helper.PhysicalBooks"%>
<%@page import="java.util.List"%>
<%@page import="com.sharethyapp.helper.BookResult"%>
<%@page import="com.sharethyapp.helper.LoginHelper"%>

<article class="hero clearfix">

    <p style="color: #6aa12e">
        ${infoMsg}
    </p>
    <p style="color: red">
        ${error}
    </p>
    <%if (LoginHelper.isLoggedIn(request)) {%>
    <h1>Specific Message</h1>

    <!--
    TODO: Image work
    <div class="img_floatright">    
        <img width="60" height="80" src="profileimage.do?entrynumber=user.entrynumber"></img>
    </div>
    -->


    <h3>Message Details</h3>
    <table class="table">

        <tr>
        <td>Message ID</td>
        <td>${msg.messageid} <br/></td>
        </tr>

        <tr>
        <td>From</td>
        <td><a href="profile.do?entrynumber=${msg.fromid}">${msg.fromid}</a></td>
        </tr>

        <tr>
        <td>To</td>
        <td><a href="profile.do?entrynumber=${msg.toid}">${msg.toid}</a></td>
        </tr>

        <tr>
        <td>Date</td>
        <td>${msg.date}</td>
        </tr>

        <tr>
        <td>Read by receiver now</td>
        <td>${msg.status}</td>
        </tr>

        <tr>
        <td>Message Content</td>
        <td>${msg.message}</td>
        </tr>

        <%if (UtilitiesHelper.getUserType(request) == 1) {
                Object modlist = new UserTableDB().getModerators();
                if (modlist != null) {

        %>
        <tr>
        <td>Forward To Moderator</td>
        <td>
            <form action="fwd.do" method="post">
                <input type="hidden" name="mid" id="mid" value="${msg.messageid}"/>   
                <input type="hidden" name="content" id="content" value="${msg.message}"/>
                <select name="modid">
                    <%for (UserTable mod : (List<UserTable>) modlist) {
                            pageContext.setAttribute("mod", mod);
                    %>
                    <option value="<%out.println(mod.getEntrynumber());%>"><%out.println(mod.getEntrynumber());%></option>
                    <%}%>
                </select>

                <input type="submit" value="Forward Now!"/>
            </form>
        </td>
        </tr>
        <%}
            }%>

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