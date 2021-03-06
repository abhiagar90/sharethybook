<%@page import="com.sharethyapp.dbclasses.UserTable"%>
<%@page import="com.sharethyapp.dbclasses.UserTableDB"%>
<%@page import="com.sharethyapp.helper.RateAndReview"%>
<%@page import="com.sharethyapp.helper.PhysicalBooks"%>
<%@page import="java.util.List"%>
<%@page import="com.sharethyapp.helper.BookResult"%>
<%@page import="com.sharethyapp.helper.LoginHelper"%>

<article class="hero clearfix">

    <p style="color: #6aa12e">
        ${infoMsg}
    </p>
    <%if (LoginHelper.isLoggedIn(request)) {%>
    <h1>${masterbook.title}</h1>
    <div class="img_floatright">
        <img width="60" height="80" src="bookimage.do?isbn=${masterbook.isbn}"></img>
    </div>

    <h5 style="float: right"><a style="" href="addphysicalbook.do?ISBN=${masterbook.isbn}">Contribute this book</a></h5>
    <!--
    TODO: Image work
    <div class="img_floatright">    
        <img width="60" height="80" src="profileimage.do?entrynumber=user.entrynumber"></img>
    </div>
    -->

    <h3>Title Details</h3>
    <table class="table">

        <tr>
        <td>ISBN</td>
        <td>${masterbook.isbn} <br/></td>
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

    </table>
    <br/>



    <h3>Ratings & Reviews</h3>
    <table class="table">

        <tr>
        <th>User ID</th>
        <th>Rating Given</th>
        <th>Review</th>
        </tr>

        <%
            BookResult newTemp = ((BookResult) request.getAttribute("masterbook"));
            if (newTemp.getRateReviewList() != null) {
                for (RateAndReview review : newTemp.getRateReviewList()) {
                    pageContext.setAttribute("review", review);
        %>
        <tr>
        <td><a href="profile.do?entrynumber=${review.entrynumber}">${review.entrynumber}</td>
        <td>${review.rating}</td>
        <td>${review.review}</td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <br/>

    <h3>Physical Copies of this title</h3>
    <table class="table">
        <tr>
        <th>BookID</th>
        <th>ISBN</th>
        <th>OwnerID</th>
        <th>HolderID</th>
        <th>HoldingDate</th>
        <th>Last Condition</th>
        <th>Request from holder</th>
        </tr>

        <%
            Object phylist = request.getAttribute("physicalList");

            if (phylist != null && !((List<PhysicalBooks>) phylist).isEmpty()) {
                for (PhysicalBooks physical : (List<PhysicalBooks>) phylist) {
                    pageContext.setAttribute("tempbook", physical);
        %>
        <tr>
        <td>${tempbook.bookidPhysical}</td>
        <td><a href="viewbook.do?isbn=${tempbook.isbn}">${tempbook.isbn}</a></td>
        <td><a href="profile.do?entrynumber=${tempbook.ownerid}">${tempbook.ownerid}</a></td>
        <td><a href="profile.do?entrynumber=${tempbook.holderid}">${tempbook.holderid}</a></td>
        <td>${tempbook.holdingdate}</td>
        <td>${tempbook.lastCondition}</td>
        <td>
            <% if (!physical.getHolderid().equals((String) request.getSession().getAttribute("entrynumber"))) {%>
            <%
                //get books contributed!!
                UserTable user = new UserTableDB().getDetailsfromEntryNum((String) request.getSession().getAttribute("entrynumber"));
                if (user.getBooksContri() >= 2) {
            %>
            <a href="requestbook.do?bookID=${tempbook.bookidPhysical}&&ISBN=${tempbook.isbn}&&oid=${tempbook.ownerid}&&hid=${tempbook.holderid}"> Request</a>
            <%} else {%>
            Contribute two books first!
            <%}
            } else {%>
            You hold this!
            <%}%>
        </td>

        </tr>
        <%
            }
        } else {%>
        <tr>
        <td>
            <a href="addwishlist.do?ISBN=${masterbook.isbn}">Add to wish-list</a>
        </td>
        </tr>
        <%}%>
    </table>
    <br/>

    <h3>Rate and review!</h3>
    <form action="rate.do" method="post" class="form">

        <input type="hidden" name="entrynumber" id="entrynumber" value="${sessionScope.entrynumber}" />
        <input type="hidden" name="isbn" id="isbn" value="${masterbook.isbn}"/>

        <div class="col_100">

            <table class="table">
                <tr></tr>

                <tr>
                <td><label for="rate">Rate</label><br/></td>
                <td><select name="rate" id="rate">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </td>
                </tr>

                <tr>
                <td><label for="review">Review</label><br/></td>
                <td><textarea name="review" id="review" rows="4" cols="50" ></textarea></td>
                </tr>


            </table><br/>




        </div>
        <div>
            <button type="submit" class="button" onclick="submit">Submit</button>
        </div>

        <br/>

        <br/>

    </form>
    <!--
    <div>
        <a href="addbook.jsp">Contribute a book</a> <br/>
        <a href="beforeedit.do?entrynumber=${user.entrynumber}">Edit Profile Details</a> <br/>
        <a href="changePassword.jsp">Change Password <a/> <br/>
            <a href="editProfileImage.jsp">Edit Profile Image</a> <br/>
    </div>
    -->
    <%} else {%>
    <p class="col_50" style="color: #444">
        Not logged in. Please login.
    </p>
    <br/>
    <%}%>


</article>

<br/>
<br/>