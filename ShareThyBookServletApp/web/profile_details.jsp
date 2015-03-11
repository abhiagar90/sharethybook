<%@page import="com.sharethyapp.helper.LoginHelper"%>
<%@page import="java.util.List"%>
<%@page import="com.sharethyapp.helper.PhysicalBooks"%>
<article class="hero clearfix">
    <div class="col_100">
        <p style="color: #6aa12e">
            ${infoMsg}
        </p>
        <%if (LoginHelper.isLoggedIn(request)) {%>
        <h1>${user.entrynumber}</h1>
        <div class="img_floatright">
            <img width="60" height="80" src="profileimage.do?entrynumber=${user.entrynumber}"></img>
        </div>
        <p>

        <h3>Profile Details</h3>
        <table class="table">

            <tr>
            <td>First Name</td>
            <td>${user.firstname}</td>
            </tr>

            <tr>
            <td>Lastname </td> 
            <td>${user.lastname}</td>
            </tr>

            <tr>
            <td>Email-id</td> 
            <td>${user.emailId}</td>
            </tr>

            <tr>
            <td>TypeOfUser</td> 
            <td>${typeUser}</td>
            </tr>

            <tr>
            <td>Hosteler</td>
            <td>${user.isHosteler} </td>
            </tr>

            <tr>
            <td>HouseNo</td>
            <td>${user.houseNo}</td>
            </tr>


            <tr>
            <td>StreetNo/Hostel</td> 
            <td>${user.streetNo}</td>
            </tr>

            <tr>
            <td>City</td>
            <td>${user.city}</td>
            </tr>

            <tr>
            <td>State</td>
            <td>${user.state}</td>
            </tr>

            <tr>
            <td>Pincode</td>
            <td>${user.pincode}</td>
            </tr>

            <tr>
            <td>Number of unread messages</td>
            <td>${user.unreadMsgs}</td>
            </tr>

            <tr>
            <td>Number of books contributed</td> 
            <td>${user.booksContri}</td>
            </tr>

            <tr>
            <td>Number of books physically having</td>
            <td>${user.booksHave}</td>
            </tr>
        </table>
        <br/>

        <h3>Books Contributed</h3>

        <table class="table">

            <tr>
            <th>BookID</th>
            <th>ISBN</th>
            <th>HolderID</th>
            <th>HoldingDate</th>
            <th>Last Condition</th>
            <th>Request from Holder</th>
            </tr>

            <%
                for (PhysicalBooks tempbook : (List<PhysicalBooks>) request.getAttribute("ownlist")) {
                    pageContext.setAttribute("tempbook", tempbook);
            %>
            <tr>
            <td>${tempbook.bookidPhysical}</td>
            <td><a href="viewbook.do?isbn=${tempbook.isbn}">${tempbook.isbn}</a></td>
            <td><a href="profile.do?entrynumber=${tempbook.holderid}">${tempbook.holderid}</a></td>
            <td>${tempbook.holdingdate}</td>
            <td>${tempbook.lastCondition}</td>
            <td>Request</td>
            </tr>
            <%
                }
            %>

        </table>
        <br/>

        <h3>Books Having Physically</h3>

        <table class="table">

            <tr>
            <th>BookID</th>
            <th>ISBN</th>
            <th>OwnerID</th>
            <th>HoldingDate</th>
            <th>Last Condition</th>
            <th>Request from Holder</th>
            </tr>

            <%
                for (PhysicalBooks tempbook : (List<PhysicalBooks>) request.getAttribute("havinglist")) {
                    pageContext.setAttribute("tempbook", tempbook);
            %>
            <tr>
            <td>${tempbook.bookidPhysical}</td>
            <td><a href="viewbook.do?isbn=${tempbook.isbn}">${tempbook.isbn}</a></td>
            <td><a href="profile.do?entrynumber=${tempbook.ownerid}">${tempbook.ownerid}</a></td>
            <td>${tempbook.holdingdate}</td>
            <td>${tempbook.lastCondition}</td>
            <td>Request</td>
            </tr>
            <%
                }
            %>



        </table>

        <h3>Books Requested By You</h3>
        <h3>Books Requested From You</h3>

        <div>
            <a href="addbook.jsp">Contribute a book</a> <br/>
            <a href="beforeedit.do?entrynumber=${user.entrynumber}">Edit Profile Details</a> <br/>
            <a href="changePassword.jsp">Change Password <a/> <br/>
                <a href="editProfileImage.jsp">Edit Profile Image</a> <br/>
        </div>
    </div>
            <%} else {%>
    <p class="col_50" style="color: #444">
        Not logged in. Please login.
    </p>
    <br/>
    <%}%>

</article>

<br/>
<br/>