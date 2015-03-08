<article class="hero clearfix">
    <div class="col_100">
        <h1>${user.entrynumber}</h1>
        <p>
            Firstname: ${user.firstname} <br/>
            Lastname: ${user.lastname} <br/>
            Email-id: ${user.emailId} <br/>
            TypeOfUser: ${user.typeOfUser} <br/>
            <br/>
            Hosteler: ${user.isHosteler} <br/>
            HouseNo: ${user.houseNo} <br/>
            StreetNo/Hostel: ${user.streetNo} <br/>
            City: ${user.city} <br/>
            State ${user.state} <br/>
            Pincode: ${user.pincode} <br/>
            <br/>
            Number of unread msgs: ${user.unreadMsgs} <br/>
            Number of books contributed: ${user.booksContri} <br/>
            Number of books physically having: ${user.booksHave} <br/>
            Image:
            <img width="50" height="50" src="profileimage.do?entrynumber=${user.entrynumber}"></img>

        </p>
    </div>
</article>

<br/>
<br/>