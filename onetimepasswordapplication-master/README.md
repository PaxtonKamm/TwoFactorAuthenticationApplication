This onetime password application has example code to allows a user of the Ytel API 
to send a one time password SMS to one of their users. This app will use two of Ytel's end points. The first end point 
will create an authorization token for the user. Once the authorization token is
created, it will be stored in memory and then used for authentication with the one time password endpoint.
The onetime password endpoint will send an SMS to the provided phone number and return back to the caller the OTP sent to the user.

The application requires gradle 5.5 and java 8. First, build the 
application with gradle.build. Once it has successfully built, type in the 
following: ./gradlew run --args='ytelusername password phonenumber'

Note: Numbers must be in E164 format (ex: +17145551234).