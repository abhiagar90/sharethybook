<%@page import="com.sharethyapp.helper.UtilitiesHelper"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.sharethyapp.helper.WishList"%>
<%@page import="com.sharethyapp.helper.TransactionHistory"%>
<%@page import="com.sharethyapp.dbclasses.UserTable"%>
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
        <%
            String sessionEntry = (String) request.getSession().getAttribute("entrynumber");
            UserTable usertable = (UserTable) request.getAttribute("user");
            String userEntry = usertable.getEntrynumber();
            if (!sessionEntry.equals(userEntry)) {
        %>
        <div class="img_floatright">
            <%
                if (UtilitiesHelper.getUserType(request) == 1) {
            %>
            <a href="changetype.do?entrynumber=${user.entrynumber}&oldtype=${typeUser}">Elevate/De-Elevate</a>
            <%}%>
            <p><a href="#msg">Send a message</a></p>
        </div>
        <%}%>

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
            <th>Revoke Book</th>
            </tr>

            <%
                Object ownListTemp = request.getAttribute("ownlist");
                if (ownListTemp != null)
                    for (PhysicalBooks tempbook : (List<PhysicalBooks>) request.getAttribute("ownlist")) {
                        pageContext.setAttribute("tempbook", tempbook);
            %>
            <tr>
            <td>${tempbook.bookidPhysical}</td>
            <td><a href="viewbook.do?isbn=${tempbook.isbn}">${tempbook.isbn}</a></td>
            <td><a href="profile.do?entrynumber=${tempbook.holderid}">${tempbook.holderid}</a></td>
            <td>${tempbook.holdingdate}</td>
            <td>${tempbook.lastCondition}</td>
            <td>Revoke</td>
            </tr>
            <%
                }
            %>

        </table>
        <br/>

        <h3>Books Having Physically</h3>

        <table class="table" width="100%">

            <tr>
            <th>BookID</th>
            <th>ISBN</th>
            <th>OwnerID</th>
            <th>HoldingDate</th>
            <th>Last Condition</th>
            </tr>

            <%
                Object phyListTemp = request.getAttribute("havinglist");
                if (phyListTemp != null)
                    for (PhysicalBooks tempbook : (List<PhysicalBooks>) request.getAttribute("havinglist")) {
                        pageContext.setAttribute("tempbook", tempbook);
            %>
            <tr>
            <td>${tempbook.bookidPhysical}</td>
            <td><a href="viewbook.do?isbn=${tempbook.isbn}">${tempbook.isbn}</a></td>
            <td><a href="profile.do?entrynumber=${tempbook.ownerid}">${tempbook.ownerid}</a></td>
            <td>${tempbook.holdingdate}</td>
            <td>${tempbook.lastCondition}</td>
            </tr>
            <%
                }
            %>



        </table>
        <%
            if (sessionEntry.equals(userEntry)) {
        %>

        <h3>Books Requested By ${user.firstname}</h3>

        <table class="table" width="100%">

            <tr>
            <th>TransactionID</th>
            <th>Requested From</th>
            <th>BookID</th>
            <th>Start Date</th>
            <th>Last Update</th>
            <th>Status</th>            
            <th>Action</th>
            </tr>

            <%
                Object reqBooks = request.getAttribute("booksRequested");
                if (reqBooks != null)
                    for (TransactionHistory tempbook : (List<TransactionHistory>) reqBooks) {
                        pageContext.setAttribute("tempbook", tempbook);
            %>
            <tr>
            <td>${tempbook.getTransactionID()}</td>
            <td><a href="profile.do?entrynumber=${tempbook.getToID()}">${tempbook.getToID()}</a></td>
            <td>${tempbook.getBookID()}</td>
            <td>${tempbook.getTransStartDate()}</td>
            <td>${tempbook.getLastUpdate()}</td>
            <td>${tempbook.getStatus()}</td>           
            <td>
                <%if (tempbook.getStatus().equals("R")) {%>
                Waiting
                <%} else if (tempbook.getStatus().equals("C")) { %>
                Canceled
                <%} else if (tempbook.getStatus().equals("T")) {%>
                In Transit
                <%} else if (tempbook.getStatus().equals("H")) { %>
                <a href="accept.do?tid=${tempbook.getTransactionID()}&status=E">I have Borrowed</a>
                <% } else if (tempbook.getStatus().equals("E")) { %>
                Ended
                <% } %>
            </td>
            </tr>
            <%
                }
            %>



        </table>



        <h3>Books Requested From ${user.firstname}</h3>

        <table class="table" width="100%">

            <tr>
            <th>TransactionID</th>
            <th>Requested By</th>
            <th>BookID</th>
            <th>Start Date</th>
            <th>Last Update</th>
            <th>Status</th>            
            <th>Action</th>
            </tr>

            <%
                Object reqBooksPending = request.getAttribute("booksReqPending");
                if (reqBooksPending != null)
                    for (TransactionHistory tempbook : (List<TransactionHistory>) reqBooksPending) {
                        pageContext.setAttribute("tempbook", tempbook);
            %>
            <tr>
            <td>${tempbook.getTransactionID()}</td>
            <td><a href="profile.do?entrynumber=${tempbook.getFromID()}">${tempbook.getFromID()}</a></td>
            <td>${tempbook.getBookID()}</td>
            <td>${tempbook.getTransStartDate()}</td>
            <td>${tempbook.getLastUpdate()}</td>
            <td>${tempbook.getStatus()}</td>           
            <td>
                <%if (tempbook.getStatus().equals("R")) {%>
                <a href="accept.do?tid=${tempbook.getTransactionID()}&status=T">Accept</a> 

                <%
                    boolean rejectable = true;
                    if (phyListTemp != null) {
                        PhysicalBooks phyBooktemp = null;
                        for (PhysicalBooks temp : (List<PhysicalBooks>) phyListTemp) {
                            if (temp.getBookidPhysical().equals(tempbook.getBookID() + "")) {
                                phyBooktemp = temp;
                                break;
                            }
                        }
                        if (phyBooktemp != null) {
                            Calendar calendar = Calendar.getInstance();
                            java.util.Date today = calendar.getTime();

                            System.out.println("AAAAAAAAAAA " + today);
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            String dateInString = phyBooktemp.getHoldingdate();
                            java.util.Date thatday = formatter.parse(dateInString);

                            System.out.println("AAAAAAAAAAAA " + thatday);

                            long duration = today.getTime() - thatday.getTime();
                            long days = TimeUnit.MILLISECONDS.toDays(duration);
                            if (days > 20) {
                                rejectable = false;
                            }

                            System.out.println("AAAAAAAAAAAAAA" + days);
                        }
                    }
                    if (rejectable) {
                %>
                <a href="accept.do?tid=${tempbook.getTransactionID()}&status=C">Reject</a>
                <%}%>
                <%} else if (tempbook.getStatus().equals("C")) { %>
                Canceled
                <%} else if (tempbook.getStatus().equals("T")) {%>
                <a href="accept.do?tid=${tempbook.getTransactionID()}&status=H">I have Lent</a>
                <%} else if (tempbook.getStatus().equals("H")) { %>
                Waiting
                <% } else {%>
                Ended
                <%}%>
            </td>
            </tr>
            <%
                }
            %>



        </table>
        <h3>${user.firstname}'s Wish-list!</h3>

        <table class="table" width="100%">

            <tr>
            <th>ISBN</th>
            <th>Title</th>
            <th>Date</th>
            </tr>

            <%
                Object booksWished = request.getAttribute("booksWished");
                if (booksWished != null)
                    for (WishList tempbook : (List<WishList>) booksWished) {
                        pageContext.setAttribute("tempbook", tempbook);
            %>
            <tr>
            <td>${tempbook.getIsbn()}</td>
            <td>${tempbook.getTitle()}</td>
            <td>${tempbook.getDate()}</td>
            </tr>
            <%
                }
            %>



        </table>

        <h3>Settings and features</h3>
        <div>
            <a href="addbook.jsp">Contribute a book</a> <br/>
            <a href="beforeedit.do?entrynumber=${user.entrynumber}">Edit Profile Details</a> <br/>
            <a href="changePassword.jsp">Change Password </a> <br/>
            <a href="editProfileImage.jsp">Edit Profile Image</a> <br/>
            <a href="allmsgsview.do">View messages</a> <br/>
        </div>


        <!-- ADMIN WORK -->
        <% if (usertable.getTypeOfUser() == 1) {%>
        <h3>ADMIN Stuff</h3>
        <a href="admin.do?type=W">See all wishlist!</a> <br/>
        <a href="admin.do?type=C">See all canceled transactions</a> <br/>
        <a href="admin.do?type=E">See all completed transactions</a> <br/>
        <a href="admin.do?type=P">See all pending transactions</a> <br/>
        <%}%>



        <%} else { %>




        <a name="msg"></a>
        <h3>Say Hi to ${user.firstname}</h3>

        <!-- Form for message here!! Phew -->
        <form action="message.do" method="post" class="form">

            <input type="hidden" name="fromid" id="fromid" value="${sessionScope.entrynumber}" />
            <input type="hidden" name="toid" id="toid" value="${user.entrynumber}"/>

            <div class="col_100">

                <table class="table">
                    <tr></tr>

                    <tr>
                    <td><label for="msg">Type Message Here</label><br/></td>
                    <td>
                        <textarea name="msg" id="msg" rows="4" cols="50" value=""></textarea>               
                    </td>                
                    </tr>

                </table><br/>




            </div>
            <div>
                <button type="submit" class="button" onclick="submit">Submit</button>
            </div>

            <br/>

            <br/>
        </form>
        <%}%>
        <p class="col_50" style="color: red">
            ${requestScope.error}
        </p>
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