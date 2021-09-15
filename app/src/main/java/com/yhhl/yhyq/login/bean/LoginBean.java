package com.yhhl.yhyq.login.bean;

import com.google.gson.Gson;
import com.yhhl.yhyq.base.BaseEntity;

import java.util.List;

public class LoginBean extends BaseEntity {

    /**
     * access_token : eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxMDAwMCwidXNlcl9uYW1lIjoi5LuY57qi56eR5oqA5pyJ6ZmQ5YWs5Y-4566h55CG5ZGYIiwic2NvcGUiOlsidGVzdCJdLCJkb21haW5zIjpbIjEwMDAxLjEwMDAwIl0sImV4cCI6MTYyNjQ0Mzc2MSwiYXV0aG9yaXRpZXMiOlsiMzk0MjI0IiwiMTMxMzEyIiwiMTE3OTg4OCIsIjI2MjI1NiIsIjkxNzc0NCIsIjY1NTYwMCIsIjE0NDIwMzIiLCIzNjcwMTkyIiwiMzUzOTE4NCIsIjUyNDc4NCIsIjc4NjY3MiIsIjEzMTEyMTYiLCIxMDUwNjA4Il0sImp0aSI6ImM3ZTZjYjgxLWY2NDgtNGJlMy05ZGIyLTdkYWFlODhiZmMxYyIsImNsaWVudF9pZCI6ImFwcF8xIn0.bQJsk0Wa1QWTj43e97skFzdSj3o2MGsPltb4ORwuaf_FMsX5ZbHPKys7g89LKGoICeIOH5tcayqSd83n7gd7UsVlUmFER1nzYyU-IK1BI6KLomK9rSe5VtkYcQasgVkyQsHYrxE3ZAP2UmGFWfR98D6hqrdoo6HgScR6xcmGdI42BhB0ZhSBxwYaPslEsFt5Sy7iSanE4SpgO4nC_X0qnX1m7VhCrB4pxZI8Ginx2tG7NUtEH2XJ7XgkjI6KEqAD7B-7YS2QDE8KZi4FXPQVl19TuNvzkJFsvWNFOWYCjp4p8K7ShXvmxgwf6QK7NgP0kPdMovrJtvzNogYjn90kCA
     * token_type : bearer
     * refresh_token : eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxMDAwMCwidXNlcl9uYW1lIjoi5LuY57qi56eR5oqA5pyJ6ZmQ5YWs5Y-4566h55CG5ZGYIiwic2NvcGUiOlsidGVzdCJdLCJhdGkiOiJjN2U2Y2I4MS1mNjQ4LTRiZTMtOWRiMi03ZGFhZTg4YmZjMWMiLCJkb21haW5zIjpbIjEwMDAxLjEwMDAwIl0sImV4cCI6MTYyNzAwNTM2MSwiYXV0aG9yaXRpZXMiOlsiMzk0MjI0IiwiMTMxMzEyIiwiMTE3OTg4OCIsIjI2MjI1NiIsIjkxNzc0NCIsIjY1NTYwMCIsIjE0NDIwMzIiLCIzNjcwMTkyIiwiMzUzOTE4NCIsIjUyNDc4NCIsIjc4NjY3MiIsIjEzMTEyMTYiLCIxMDUwNjA4Il0sImp0aSI6ImFiMTQ2MDc2LWM4NTEtNDJlZi1iN2M5LTQ4MTJhZTQ4MjEzMiIsImNsaWVudF9pZCI6ImFwcF8xIn0.aaa7gEK3A805qLl97xnFlwsWPcTULYgDC0cKLGPw_KtItHXVb1PHUH5-iGjypFXTXSIw4KSwLqAdTE8YF-e0Ea541t0vcN5jNm6cEc2V4wBmAyNpFjrSKoE0sr3GoBSjSn4R_8eX7h2ZiGHqjdU3rvepw2qCVM2sOK-Ho7M3qT5DeopZ2D0EuF61QZk7Q1sh28-q4A7zGRhSYorip4CbUGO77jCtla7giJC5Rpb-dECch3Om5bFjuM6rkc6CKLD1vKqG81LynTIZEwPa-xlNYz9ZF_n9TPfuAxCPL5gjP_NnO2-23phQY5DaYqKUdlnaMD4tVDHEXrrvV0ci_aXm7g
     * expires_in : 43199
     * scope : test
     * user_id : 10000
     * user_name : 付红科技有限公司管理员
     * domains : ["10001.10000"]
     * jti : c7e6cb81-f648-4be3-9db2-7daae88bfc1c
     */

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int user_id;
    private String user_name;
    private String jti;
    private List<String> domains;

    public static LoginBean objectFromData(String str) {

        return new Gson().fromJson(str, LoginBean.class);
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }
}
