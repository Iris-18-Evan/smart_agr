/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// model/AuthResult.java
package model;

public final class AuthResult {
    public enum Status { SUCCESS, INVALID_CREDENTIALS, INACTIVE, NOT_FOUND, ERROR }

    private final Status status;
    private final User user;
    private final String message;

    private AuthResult(Status status, User user, String message) {
        this.status = status; this.user = user; this.message = message;
    }

    public static AuthResult success(User u)          { return new AuthResult(Status.SUCCESS, u, null); }
    public static AuthResult fail(Status s, String m) { return new AuthResult(s, null, m); }

    public Status getStatus() { return status; }
    public User getUser()     { return user; }
    public String getMessage(){ return message; }
    public boolean isSuccess(){ return status == Status.SUCCESS; }
}

