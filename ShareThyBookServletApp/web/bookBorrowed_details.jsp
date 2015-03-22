

<%@page import="com.sharethyapp.helper.LoginHelper"%>
<article class="hero clearfix">


    <h1>Contribute a Book! :)</h1>
    <%if (LoginHelper.isLoggedIn(request)) {%>

    <form action="accept.do" method="get" class="form">
        
        
        <p style="color: #6aa12e">
            ${requestScope.Message}
        </p>
        <p class="col_50" style="color: red">
            ${requestScope.Error}
        </p>
        <h3>Confirm Book Lent Details</h3>
        <table class="table">

            <tr>
            <td>Transaction ID</td>
            <td>${tid}<br/></td>
            </tr>


            <tr><td>Condition Of Book</td><td><label for="radio-choice-1">
                <input type="radio" name="cond" id="cond" tabindex="2" value="E" checked="checked"/> Excellent  
            </label>
            <label for="radio-choice-1">
                <input type="radio" name="cond" id="cond" tabindex="2" value="G" /> Good
            </label>
            <label for="radio-choice-1">
                <input type="radio" name="cond" id="cond" tabindex="2" value="O"/> Okay  
            </label>
            <label for="radio-choice-1">
                <input type="radio" name="cond" id="cond" tabindex="2" value="D" /> Damaged
            </label></td></tr>



        </table>
        <br/>
        <div>
            <button type="submit" class="button" onclick="submit">Contribute</button>
        </div>
        <br/>
        <input type="hidden" name="status" id="status" value="${status}"/>    
        <input type="hidden" name="tid" id="tid" value="${tid}"/>
    </form>
    <%} else {%>
    <p class="col_50" style="color: #444">
        Not logged in. Please login.
    </p>
    <br/>
    <%}%>
</article>
