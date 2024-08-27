package com.servicesystem.api.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.servicesystem.api.application.payload.LoginGoogle;
import com.servicesystem.api.application.payload.response.GoogleTokenErrorResponse;
import com.servicesystem.api.application.payload.response.GoogleTokenResponse;
import com.servicesystem.api.application.services.AuthService;
import com.servicesystem.api.application.services.RefreshTokenService;
import com.servicesystem.api.application.services.UserDetailsServiceImpl;
import com.servicesystem.api.application.services.UserService;
import com.servicesystem.api.domain.models.users.UserDetailsImpl;
import com.servicesystem.api.domain.utils.CookieUtils;
import com.servicesystem.api.domain.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl uServiceImpl;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CookieUtils cookieUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam @NotBlank String google_token) {
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + google_token;

        try {
            // GoogleTokenResponse tokenInfo = restTemplate.getForObject(url,
            // GoogleTokenResponse.class); // descomentar função do google

            GoogleTokenResponse tokenResponse = new GoogleTokenResponse(); // setando um email só para usar a função do
                                                                           // google (apagar linha quando utilizar a
                                                                           // função do google)

            if (!google_token.equals("123456")) { // função temporária para verificar o erro (apagar
                                                  // futuramente)
                return ResponseEntity.badRequest().body("token do google incorreto!");
            }

            tokenResponse.setEmail("user1@example.com"); // apagar linha quando utilizar a função do google

            if (!userService.existsByEmail(tokenResponse.getEmail())) {
                return ResponseEntity.badRequest().body("E-mail não cadastrado! Por favor, crie uma conta");
            }

            UserDetailsImpl userDetails = authService.authentication(tokenResponse.getEmail(), google_token);

            // Criar cabeçalhos e adicionar cookies
            HttpHeaders headers = authService.addHeaders(userDetails);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(userService.findByEmail(userDetails.getEmail()));

        } catch (HttpClientErrorException e) {
            GoogleTokenErrorResponse tokenError = restTemplate.getForObject(url, GoogleTokenErrorResponse.class);
            return ResponseEntity.status(e.getStatusCode()).body(tokenError);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody LoginGoogle loginGoogle) {

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

            if (userService.existsByEmail(tokenResponse.getEmail())) {
                return ResponseEntity.badRequest().body("E-mail já cadastrado! Por favor, faça login na sua conta");
            }

            userService.create(loginGoogle.getUser()); // criar usuário

            UserDetailsImpl userDetails = authService.authentication(tokenResponse.getEmail(), loginGoogle.getGoogleToken());

            // Criar cabeçalhos e adicionar cookies
            HttpHeaders headers = authService.addHeaders(userDetails);

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

        return ResponseEntity.ok()
                .headers(authService.clearToken())
                .body("Você foi deslogado!");
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        String refreshToken = cookieUtils.getJwtRefreshFromCookies(request);

        if ((refreshToken != null) && (refreshToken.length() > 0)) {

            var resultToken = refreshTokenService.findByToken(refreshToken);

            try {

                String email = jwtUtils.validateToken(resultToken.getToken());

                if (!email.contains("Exception")) {

                    ResponseCookie jwtCookie = cookieUtils
                            .addJwtCookie(uServiceImpl.loadUserByUsername(email));

                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                            .body("Token atualizado!");
                }

            } catch (JWTVerificationException ex) {

                refreshTokenService.deleteByEmail(resultToken.getUserEmail());
                ResponseCookie jwtCookie = cookieUtils.getCleanJwtCookie();
                ResponseCookie jwtRefreshCookie = cookieUtils.getCleanJwtRefreshCookie();

                // Criar cabeçalhos e adicionar cookies vazios
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
                headers.add(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString());

                return ResponseEntity.badRequest()
                        .headers(headers)
                        .body("Refresh Token inválido: " + ex + "\n Faça login novamente!");

            }
        }
        return ResponseEntity.badRequest().body("Refresh Token inválido! Faça login novamente!");
    }

}
