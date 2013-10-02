import grails.plugins.rest.client.RestBuilder
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.web.util.UriUtils

class HomeController {
    GrailsApplication grailsApplication

    def index() {
        String googleApiUrl = grailsApplication.config.google.authUrl.toString()
        String redirectUri = UriUtils.encodeHost(grailsApplication.config.google.redirectUri.toString(), "UTF8")
        String encodedScope = UriUtils.encodeHost(grailsApplication.config.google.scope.toString(), "UTF8")
        String clientId = grailsApplication.config.google.clientId.toString()
        String authUrl = "${googleApiUrl}scope=${encodedScope}&redirect_uri=${redirectUri}&client_id=${clientId}&response_type=code"
        [authUrl: authUrl]
    }

    def signInCallback() {
        def authorizationCode = params.code
        RestBuilder restBuilder = new RestBuilder()
        def resp = restBuilder.post("https://accounts.google.com/o/oauth2/token"){
            contentType "application/x-www-form-urlencoded"
            setProperty "code", authorizationCode
            setProperty "client_id", grailsApplication.config.google.clientId.toString()
            setProperty "client_secret", grailsApplication.config.google.clientSecret.toString()
            setProperty "redirect_uri", grailsApplication.config.google.redirectUri.toString()
            setProperty "grant_type", grailsApplication.config.google.grantType.toString()
        }
        render resp.json
    }
}
