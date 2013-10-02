# Google Auth With Grails.

Create a __google-config.groovy__ file under ~/.grails with the following :

```
google.authUrl = "https://accounts.google.com/o/oauth2/auth?"
google.scope = "https://www.googleapis.com/auth/userinfo.email"
google.redirectUri = "http://localhost:8090/google-auth/home/signInCallback"
google.clientId = "GetYourOwn-83vvodt.apps.googleusercontent.com"
```

You need to get your own client id from google. Follow the directions [here](https://developers.google.com/accounts/docs/OAuth2WebServer)
to get a client id.

You will need to register an app where __web origin__ is "http://localhost:8090", and where __Redirect Uri__ is the same
as __google.redirectUri__ in the config file.

Then start grails in port 8090:
```
grails run-app -Dserver.port=8090
```