package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.TokenController;
import wrappers.TokenWrapper;

@RestController
@RequestMapping(Uris.TOKENS)
public class TokenResource {

    private TokenController tokenController;

    @Autowired
    public void setTokenController(TokenController tokenController) {
        this.tokenController = tokenController;
    }

    @RequestMapping(method = RequestMethod.POST)
    public TokenWrapper login(@AuthenticationPrincipal User activeUser) {
        return tokenController.login(activeUser.getUsername());
    }
}
