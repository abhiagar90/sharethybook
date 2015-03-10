<article class="hero clearfix">
    <div class="col_100">
        <h1>${user.entrynumber}</h1>
        <div class="img_floatright">
            <img width="60" height="80" src="profileimage.do?entrynumber=${user.entrynumber}"></img>
        </div>
        <p>
            Firstname: ${user.firstname} <br/>
            Lastname: ${user.lastname} <br/>
            Email-id: ${user.emailId} <br/>
            TypeOfUser: ${typeUser} <br/>
            <br/>
            Hosteler: ${user.isHosteler} <br/>
            HouseNo: ${user.houseNo} <br/>
            StreetNo/Hostel: ${user.streetNo} <br/>
            City: ${user.city} <br/>
            State ${user.state} <br/>
            Pincode: ${user.pincode} <br/>
            <br/>
            Number of unread messages: ${user.unreadMsgs} <br/>
            Number of books contributed: ${user.booksContri} <br/>
            Number of books physically having: ${user.booksHave} <br/>
        
        </p>
        <div><a href="AddBook.jsp">Add More Books</a></div>
    </div>
           
</article>

<br/>
<br/>