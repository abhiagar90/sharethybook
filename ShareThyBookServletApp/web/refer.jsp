<!doctype html>
<html lang="en">
    <jsp:include page="head.jsp"/>

<body>
  <div class="container">

      <jsp:include page="header.jsp"/>


    <div class="info">
        <jsp:include page="welcomeArticle.jsp"/>

      <br/>
      <br/>


      <article class="article clearfix">
        
        <div class="col_33">
          <h2>Clean code</h2>
          <p>HTML5 and CSS3 made live of web developers easier than ever. Welcome to the world where less code and less files required. ?Simpliste? has different skins and all of them are created with no images for styling at all.</p>
          <p>Template contains CSS-reset based on the reset file from <a href="http://html5boilerplate.com/" target="_blank">HTML5 boilerplate</a> which makes appearens of ?Simpliste? skins consistent in different browsers.</p>
          <p>Print styles and styles for mobile devices are already included in the stylesheet.</p>
        </div>

        <div class="col_33">
          <h2>Responsive markup</h2>
          <p>You know that now it's time to think more about your users with mobile devices. This template will make your site respond to your client's browser with no effort on your part.</p>
          <p>Multi-column layout becomes one column for viewers with tablets, navigation elements become bigger for users with smartphones. And your desktop browser users will see just a normal web site.</p>
          <p>Try changing the width of your browser window and you'll see how ?Simpliste? works.</p>
        </div>

        <div class="col_33">
          <h2>Easy to use</h2>
          <p>?Simpliste? is not a template for a CMS. You can use its code right away after downloading without reading any documentation. Place your content, make customisations and voil� the site is ready to upload to the server.</p>
          <p>All content management can be done by using existing sample blocks and styles. Almost every template style is represented among <a href="#samples">samples</a> on this page. Off course you can create your own styles, which is easy as well.</p>
        </div>
        
        <div class="clearfix"></div>

        
        <h1>?Simpliste? in use</h1>
        
        <div class="col_50">
          <h2>Sample content</h2>
          
          <h3>Principles behind ?Simpliste?</h3>
          <ul>
             <li>Really simple</li>
             <li>Has ready to use set of simple designs</li>
             <li>It's written using HTML5 and CSS3</li>
             <li>It responds to mobile devices</li>
             <li>No CMS</li>
             <li>Free</li>
          </ul>
          
          <h3>How to use?</h3>
          <ol>
             <li>Choose one skin from the list above</li>
             <li>Click download button</li>
             <li>Unpack files</li>
             <li>Make any customisation you need</li>
             <li>Upload your new site to the server</li>
          </ol>
        </div>        

        <div class="col_50">
          <form action="#" method="post" class="form">
            <h2>Sample form</h2>

            <p class="col_50">
              <label for="name">Simple name:</label><br/>
              <input type="text" name="name" id="name" value="" />
            </p>
            <p class="col_50">
              <label for="email">Simple e-mail:</label><br/>
              <input type="text" name="email" id="email" value="" />
            </p>
            <div class="clearfix"></div>

            <h3>Your favorite number</h3>
            <p>
              <div class="col_33">
                <label for="radio-choice-1"><input type="radio" name="radio-choice-1" id="radio-choice-1" tabindex="2" value="choice-1" /> One</label><br/>
                <label for="radio-choice-2"><input type="radio" name="radio-choice-1" id="radio-choice-2" tabindex="3" value="choice-2" /> Two</label><br/>
                <label for="radio-choice-3"><input type="radio" name="radio-choice-1" id="radio-choice-3" tabindex="4" value="choice-3" /> Three</label>
              </div>

              <div class="col_33">
                <label for="radio-choice-4"><input type="radio" name="radio-choice-1" id="radio-choice-4" tabindex="2" value="choice-1" /> Four</label><br/>
                <label for="radio-choice-5"><input type="radio" name="radio-choice-1" id="radio-choice-5" tabindex="3" value="choice-2" /> Five</label><br/>
                <label for="radio-choice-6"><input type="radio" name="radio-choice-1" id="radio-choice-6" tabindex="4" value="choice-3" /> Six</label>
              </div>

              <div class="col_33">
                <label for="radio-choice-7"><input type="radio" name="radio-choice-1" id="radio-choice-7" tabindex="2" value="choice-1" /> Seven</label><br/>
                <label for="radio-choice-8"><input type="radio" name="radio-choice-1" id="radio-choice-8" tabindex="3" value="choice-2" /> Eight</label><br/>
                <label for="radio-choice-9"><input type="radio" name="radio-choice-1" id="radio-choice-9" tabindex="3" value="choice-2" /> Niine</label>
              </div>

            <div class="clearfix"></div>
            </p>

            <p>
              <label for="select-choice">Simple city:</label>
              <select name="select-choice" id="select-choice">
                <option value="Choice 1">London</option>
                <option value="Choice 2">Paris</option>
                <option value="Choice 3">Rome</option>
              </select>
            </p>

            <p>
              <label for="textarea">Simple testimonial:</label><br/>
              <textarea cols="40" rows="8" name="textarea" id="textarea"></textarea>
            </p>

            <p>
              <label for="checkbox"><input type="checkbox" name="checkbox" id="checkbox" /> Simple agreement</label><br/>
            </p>

            <div>
                <button type="button" class="button">Submit</button>
            </div>
          </form>
        </div>

        <div class="clearfix"></div>


        <div class="col_33">
          <h2>More elements</h2>

          <p>Use <code>strong</code> tag for information with <strong>strong importance</strong>. Use <code>em</code> tag to <em>stress emphasis</em> on a word or phrase.</p>

          <p class="warning">Sample <code>.warning</code></p>
          <p class="success">Sample <code>.success</code></p>
          <p class="message">Sample <code>.message</code></p>
        </div>

        <div class="col_66">
          <h2>CSS classes table</h2>

          <table class="table">
            <tr>
              <th>Class</th>
              <th>Description</th>
            </tr>

            <tr>
              <td><code>.col_33</code></td>
              <td>Column with 33% width</td>
            </tr>
            <tr>
              <td><code>.col_50</code></td>
              <td>Column with 50% width</td>
            </tr>
            <tr>
              <td><code>.col_66</code></td>
              <td>Column with 66% width</td>
            </tr>
            <tr>
              <td><code>.col_100</code></td>
              <td>Full width column with proper margins</td>
            </tr>
            <tr>
              <td><code>.clearfix</code></td>
              <td>Use after or wrap a block of floated columns</td>
            </tr>
            <tr>
              <td><code>.left</code></td>
              <td>Left text alignment</td>
            </tr>
            <tr>
              <td><code>.right</code></td>
              <td>Right text alignment</td>
            </tr>
            <tr>
              <td><code>.center</code></td>
              <td>Centered text alignment</td>
            </tr>
            <tr>
              <td><code>.img_floatleft</code></td>
              <td>Left alignment for images in content</td>
            </tr>
            <tr>
              <td><code>.img_floatright</code></td>
              <td>Right alignment for images in content</td>
            </tr>
            <tr>
              <td><code>.img</code></td>
              <td>Makes image change its width when browser window width is changed</td>
            </tr>
          </table>
        </div>

        <div class="clearfix"></div>
      
      </article>
    </div>
    
      <jsp:include page="footer.jsp"/>

  </div>
</body>
</html>
