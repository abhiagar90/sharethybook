

<%@page import="com.sharethyapp.helper.LoginHelper"%>
<article class="hero clearfix">

    <%if (LoginHelper.isLoggedIn(request)) {%>
    <h1>${masterbook.title}</h1>


<form action="InsertBookDetails.do" method="post" class="form">
        <p style="color: #6aa12e">
            ${requestScope.Message}
        </p>
        <p class="col_50" style="color: red">
            ${requestScope.Error}
        </p>
    <h3>Title Details</h3>
    <table class="table">

        <tr>
        <td>ISBN</td>
        <td>${masterbook.isbn}<br/></td>
        </tr>

        <tr>
        <td>Title</td>
        <td>${masterbook.title}</td>
        </tr>

        <tr>
        <td>Year Of Publication</td>
        <td>${masterbook.year}</td>
        </tr>

        <tr>
        <td>Publisher</td>
        <td>${masterbook.publisher}</td>
        </tr>

        <tr>
        <td>Overall Rating</td>
        <td>${masterbook.rating}  computed by ${masterbook.numOfRatings} ratings</td>
        </tr>

        <tr>
        <td>Authors</td>
        <td>${masterbook.authorList}</td>
        </tr>
        <tr><td>Condition Of Book</td><td><label for="radio-choice-1">
                                <input type="radio" name="Condition" id="Condition" tabindex="2" value="E" checked="checked"/> Excellent  
                            </label>
                            <label for="radio-choice-1">
                                <input type="radio" name="Condition" id="Condition" tabindex="2" value="G" /> Good
                            </label>
                            <label for="radio-choice-1">
                                <input type="radio" name="Condition" id="Condition" tabindex="2" value="O"/> Okay  
                            </label>
                            <label for="radio-choice-1">
                                <input type="radio" name="Condition" id="Condition" tabindex="2" value="D" /> Damaged
                            </label></td></tr>
    </table>
    <br/>
        <div>
                <button type="submit" class="button" onclick="submit">Contribute</button>
            </div>
    <br/>
    <input type="hidden" name="ISBN" id="ISBN" value="${masterbook.isbn}"/>
</form>
    <%}%>
    </article>
