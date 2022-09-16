package com.example.dcmanagesystem.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Integer code;
    private String msg;
    private Object data;

    public Response(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static Response invalidParamResp(String paramName){
        return new Response(412, paramName+"is invalid");
    }

    public static Response authFailedResp() {
        return new Response(302, "username or password incorrect");
    }
}
