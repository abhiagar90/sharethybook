<article class="hero clearfix">
    <div class="col_100">
        <h1>Sign up</h1>
        <p>Details here. Write out here.</p>
        <p>Some more details</p>
        
        <form action="signup.do" method="post" class="form">
        <div class="col_100">
            <h1>New User!!</h1>
            <table class="table">
                <tr></tr>
                <tr>
                <td><label for="name">First name</label><br/></td>
                <td><input type="text" name="firstName" id="firstName" value="" /></td>
                </tr>
                 <tr>
                <td><label for="name">Last name</label><br/></td>
                <td><input type="text" name="lastName" id="lastName" value="" /></td>
                </tr>
                 <tr>
                <td><label for="name">Entry Number</label><br/></td>
                <td><input type="text" name="EntryNumber" id="EntryNumber" value="" /></td>
                </tr>
                <tr>
                <td><label for="name">Password</label><br/></td>
                <td><input type="password" name="Password" id="Password" value="" /></td>
                </tr>
                <tr>
                <td><label for="name">Retype Password</label><br/></td>
                <td><input type="password" name="rePassword" id="rePassword" value="" /></td>
                </tr>
                <tr>
                <td><label for="name">Hosteler</label><br/></td>
                <td><label for="radio-choice-1"><input type="radio" name="Hosteler" id="Hosteler" tabindex="2" value="choice-1" /> Yes  </label><label for="radio-choice-1"><input type="radio" name="Hosteler" id="Hosteler" tabindex="2" value="choice-2" /> No</label></td>
                </tr>
                <tr>
                <td><label for="name">House Number</label><br/></td>
                <td><input type="text" name="HouseNo" id="HouseNo" value="" /></td>
                </tr>
               <tr>
                <td><label for="name">Street Number</label><br/></td>
                <td><input type="text" name="StreetNo" id="StreetNo" value="" /></td>
                </tr>
                <tr>
                <td><label for="name">City</label><br/></td>
                <td><input type="text" name="City" id="City" value="" /></td>
                </tr>
                <tr>
                <td><label for="name">State</label><br/></td>
                <td><input type="text" name="State" id="State" value="" /></td>
                </tr>
                <tr>
                <td><label for="name">Pin Code</label><br/></td>
                <td><input type="text" name="PinCode" id="PinCode" value="" /></td>
                </tr>
                <tr>
                <td><label for="name">Email ID</label><br/></td>
                <td><input type="text" name="EmailID" id="EmailID" value="" /></td>
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
    </div>
</article>

<br/>
<br/>

<article class="hero clearfix">
    <div class="col_100">
                
    </div>
</article>