/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.domain;

/**
 *
 * @author benoni
 */
public class Login {
    
    private final String username;
    private final String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
}
