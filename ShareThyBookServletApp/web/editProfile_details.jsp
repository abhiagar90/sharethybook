<%@page import="com.sharethyapp.dbclasses.UserTable"%>
<%@page import="com.sharethyapp.helper.LoginHelper"%>
<article class="hero clearfix">
    <div class="col_100">
        <h1>Edit Profile Details</h1>
        
        <%if (LoginHelper.isLoggedIn(request)) {%>
        <p>
            <!-- 1. Entrynumber should not be empty. <br/>  -->
            <!-- 2. Password should not be empty. Both passwords should match case wise. <br/> -->
            1. Firstname cannot be null. <br/>
            2. Hosteler field has to be selected. <br/>
            3. EmailId cannot be null and should be of iitd.ac.in domain. <br/>
            4. Pincode can be empty or should follow normal picode formats. <br/>
            
           
        </p>

        <form action="signup.do" method="post" class="form">
            <div class="col_100">
              
                <h3>Old User</h3>
                <table class="table">
                    <tr></tr>
                    <tr>
                        <td><label for="name">First name*</label><br/></td>
                        <td><input type="text" name="firstName" id="firstName" value="${newUser.firstname}" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">Last name</label><br/></td>
                        <td><input type="text" name="lastName" id="lastName" value="${newUser.lastname}" /></td>
                    </tr>
                    <!-- Removed Entry Number -->
                    <!-- Password Change another servlet task -->
                    
                    <% 
                        UserTable nayaUser = (UserTable)request.getAttribute("newUser"); 
                        if(nayaUser == null   || !nayaUser.isIsHosteler()) {
                    %>
                    <tr>
                        <td><label for="name">Hosteler*</label><br/></td>
                        <td><label for="radio-choice-1">
                                <input type="radio" name="Hosteler" id="Hosteler" tabindex="2" value="choice-1" /> Yes  
                            </label>
                            <label for="radio-choice-1">
                                <input type="radio" name="Hosteler" id="Hosteler" tabindex="2" value="choice-2" checked="checked"/> No
                            </label>
                        </td>
                    </tr>
                    <%} else {%>
                    <tr>
                        <td><label for="name">Hosteler*</label><br/></td>
                        <td><label for="radio-choice-1">
                                <input type="radio" name="Hosteler" id="Hosteler" tabindex="2" value="choice-1" checked="checked"/> Yes  
                            </label>
                            <label for="radio-choice-1">
                                <input type="radio" name="Hosteler" id="Hosteler" tabindex="2" value="choice-2" /> No
                            </label>
                        </td>
                    </tr>
                    
                    <%}%>
                    
                    <tr>
                        <td><label for="name">House Number</label><br/></td>
                        <td><input type="text" name="HouseNo" id="HouseNo" value="${newUser.houseNo}" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">Street Number</label><br/></td>
                        <td><input type="text" name="StreetNo" id="StreetNo" value="${newUser.streetNo}" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">City</label><br/></td>
                        <td><input type="text" name="City" id="City" value="${newUser.city}" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">State</label><br/></td>
                        <td><input type="text" name="State" id="State" value="${newUser.state}" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">Pin Code</label><br/></td>
                        <td><input type="text" name="PinCode" id="PinCode" value="${newUser.pincode}" /></td>
                    </tr>
                    <tr>
                        <td><label for="name">Email ID*</label><br/></td>
                        <td><input type="text" name="EmailID" id="EmailID" value="${newUser.emailId}" /></td>
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