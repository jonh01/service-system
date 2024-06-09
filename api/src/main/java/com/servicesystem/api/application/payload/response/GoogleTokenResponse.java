package com.servicesystem.api.application.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleTokenResponse {
    
    private String iss;
    private String azp;
    private String aud;
    private String sub;
    private String email;
    private boolean email_verified;
    private String at_hash;
    private String name;
    private String picture;
    private String given_name;
    private String family_name;
    private String locale;
    private long iat;
    private long exp;
    private String jti;
}
