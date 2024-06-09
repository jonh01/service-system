package com.servicesystem.api.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.servicesystem.api.application.payload.LoginGoogle;
import com.servicesystem.api.application.payload.response.GoogleTokenErrorResponse;
import com.servicesystem.api.application.payload.response.GoogleTokenResponse;
import com.servicesystem.api.application.services.RefreshTokenService;
import com.servicesystem.api.application.services.UserDetailsServiceImpl;
import com.servicesystem.api.application.services.UserService;
import com.servicesystem.api.domain.models.users.UserDetailsImpl;
import com.servicesystem.api.domain.utils.CookieUtils;
import com.servicesystem.api.domain.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl uServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CookieUtils cookieUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginGoogle loginGoogle) {

        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + loginGoogle.getGoogleToken();

        try {
            // GoogleTokenResponse tokenInfo = restTemplate.getForObject(url,
            // GoogleTokenResponse.class); // descomentar função do google

            GoogleTokenResponse tokenResponse = new GoogleTokenResponse(); // setando um email só para usar a função do
                                                                           // google (apagar linha quando utilizar a
                                                                           // função do google)

            if (!loginGoogle.getGoogleToken().equals("123456")) { // função temporária para verificar o erro (apagar
                                                                  // futuramente)
                return ResponseEntity.badRequest().body("token do google incorreto!");
            }

            tokenResponse.setEmail(loginGoogle.getUser().getEmail()); // apagar linha quando utilizar a função do google

            if (!userService.existsByEmail(tokenResponse.getEmail())) {
                userService.create(loginGoogle.getUser());
            }

            var authentication = this.authenticationManager
                    .authenticate(new PreAuthenticatedAuthenticationToken(tokenResponse.getEmail(),
                            loginGoogle.getGoogleToken()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            ResponseCookie jwtCookie = cookieUtils.addJwtCookie(userDetails);
            refreshTokenService.createRefreshToken(userDetails.getEmail()); // cria o token no banco de dados
            ResponseCookie jwtRefreshCookie = cookieUtils.addRefreshTokenCookie(userDetails.getEmail());

            // Criar cabeçalhos e adicionar cookies
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
            headers.add(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(userService.findByEmail(userDetails.getEmail()));

        } catch (HttpClientErrorException e) {
            GoogleTokenErrorResponse tokenError = restTemplate.getForObject(url, GoogleTokenErrorResponse.class);
            return ResponseEntity.status(e.getStatusCode()).body(tokenError);
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!"anonymousUser".equals(principle.toString())) {
            String email = ((UserDetailsImpl) principle).getEmail();
            refreshTokenService.deleteByEmail(email);
        }

        ResponseCookie jwtCookie = cookieUtils.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = cookieUtils.getCleanJwtRefreshCookie();

        // Criar cabeçalhos e adicionar cookies
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString());

        return ResponseEntity.ok()
                .headers(headers)
                .body("Você foi deslogado!");
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        String refreshToken = cookieUtils.getJwtRefreshFromCookies(request);

        if ((refreshToken != null) && (refreshToken.length() > 0)) {

            var resultToken = refreshTokenService.findByToken(refreshToken);

            if (jwtUtils.validateToken(refreshToken).equals("-1")) { // -1 diz que o token não é valido

                refreshTokenService.deleteByEmail(resultToken.getUserEmail());
                ResponseCookie jwtCookie = cookieUtils.getCleanJwtCookie();
                ResponseCookie jwtRefreshCookie = cookieUtils.getCleanJwtRefreshCookie();

                // Criar cabeçalhos e adicionar cookies
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
                headers.add(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString());

                return ResponseEntity.ok()
                        .headers(headers)
                        .body("Faça login novamente!");

            } else {
                ResponseCookie jwtCookie = cookieUtils
                        .addJwtCookie(uServiceImpl.loadUserByUsername(resultToken.getUserEmail()));

                return ResponseEntity.ok()
                        .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                        .body("Token atualizado!");
            }
        }
        return ResponseEntity.badRequest().body("Refresh Token está em branco!");
    }

}
