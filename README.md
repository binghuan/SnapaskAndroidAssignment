# SnapaskAndroidAssignment

<img src="Icon.png" width="auto" height="240"><br/>


## Design. 
<img src="images/Slide1.jpg" width="auto" height="480"><br/>


## Demo. 
<br/><a href="https://youtu.be/qAy0zt2r7kU" target="_blank"><img src="www/images/Screen Shot 2016-03-30 at 19.53.38.png"><br/>Watch the video on YouTube</a>

## Task 1: How to check the funtinality of data cache.
<ul>
<li>Launch app</li>
<li>Press button "QUESTION"</li>
<li>Check the responding time</li>
<li>Back to the main page</li>
<li>Press button "QUESTION"</li>
<li>Check the responding time</li>
</ul>
You will see the difference. 


## Task 2: How to test app deep link
Get into the folder "www", and host a local web server. <br/>
Then, open the page "index.html" on device. <br/>
<img src="images/Screen Shot 2016-03-30 at 14.46.06.png" width="auto" height="auto"><br/>

Or just use adb command to test. <br/>
<p>
adb shell am start<br/>
        -W -a android.intent.action.VIEW<br/>
        -d URI PACKAGE<br/>
        </p>

## Task 3: How to run unit test.
In project folder, execute following command
<p style="color=blue;">./gradlew test</p>

### Then, you will see the result.

<img src="images/Screen Shot 2016-03-30 at 14.44.16.png" width="auto" height="auto">


## Reference:

<h4>Enabling Deep Links for App Content</h4>
<a href="http://developer.android.com/intl/zh-tw/training/app-indexing/deep-linking.html">http://developer.android.com/intl/zh-tw/training/app-indexing/deep-linking.html</a>

<br/><br/>
Coding quest from "Snapask"
