
package com.test.practiceframework.pojos.responsePOJO.restfulbooker;

import com.google.gson.annotations.Expose;
import com.test.practiceframework.pojos.requestPOJO.restfulbooker.Auth;


public class TokenResponse {

    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
