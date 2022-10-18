package com.example.team_jbd;

/**
 * 사용자 계정 정보 모델 클레스
 */

public class activity_UserAcc
{
    private String idToken; // Firebase Uid
    private String emailId; // Firebase 이메일
    private String password; // Firebase 비밀번호

    public activity_UserAcc()
    {

    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
