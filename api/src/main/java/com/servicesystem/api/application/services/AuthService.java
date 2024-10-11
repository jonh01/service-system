package com.servicesystem.api.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import com.servicesystem.api.domain.models.users.UserDetailsImpl;
import com.servicesystem.api.domain.utils.CookieUtils;

@Service
public class AuthService {

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    private CookieUtils cookieUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDetailsImpl authentication(String email, String token){

        var authentication = this.authenticationManager
        .authenticate(new PreAuthenticatedAuthenticationToken(email, token));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return (UserDetailsImpl) authentication.getPrincipal();
    }

    public static String userLogged() {
        UserDetailsImpl usu = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usu.getEmail();
    }

    public void userPermission() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
    }
    
    public HttpHeaders addHeaders(UserDetailsImpl userDetails){

        ResponseCookie jwtCookie = cookieUtils.addJwtCookie(userDetails);
        ResponseCookie jwtRefreshCookie = cookieUtils.addRefreshTokenCookie(userDetails.getEmail()); 

        // Criar cabeçalhos e adicionar cookies
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString());

        return headers;
    }

    public HttpHeaders clearToken(){

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

        return headers;

    }
    
    // 	public UserResponse findByEmail (String email){
		
	// 	User user = userRepository.findByEmail(email)
    //     .orElseThrow(() -> new ObjectNotFoundException(
    //         "Usuário não encontrado! Email: "+ email ));

	// 	return modelMapper.map(user, UserResponse.class);
	// }
}
