package org.damsi.todoapplicationfullstack.dtos;

public class AuthResponse {
    private String token;

    public AuthResponse(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}
