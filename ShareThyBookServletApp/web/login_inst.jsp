<article class="hero clearfix">
    <div class="col_100">
        <h1>Login</h1>
        <%if (!(request.getSession().getAttribute("entrynumber") != null
                        && !request.getSession().getAttribute("entrynumber").equals("NA"))) {%>
                   
        <form action="login.do" method="post" class="form">
        
            <p class="col_50">
              <label for="entrynumber">Entry Number:</label><br/>
              <input type="text" name="entrynumber" id="name" value="" />
              <br/>
              <br/>
              <label for="passwd">Password:</label><br/>
              <input type="password" name="passwd" id="email" value="" />
            </p>
            
            <div class="clearfix"></div>

            <br/>
            <br/>

            <div>
                <button type="submit" class="button" onclick="submit">Submit</button>
            </div>
          </form>
        <br/>
        <br/>
        <p class="col_50" style="color: red">
                ${requestScope.error}
            </p>                 
        <%}else{%>
        <p class="col_50" style="color: #444">
                Already logged in. Please logout.
            </p>
         <br/>
         <br/>
        <%}%>
        
    </div>
</article>

<br/>
<br/>