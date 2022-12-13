/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasitokosembako;

/**
 *
 * @author akbaroke
 */
public class Session {
    private String userId = null;
    
    public static Session session = new Session();
    public void setSession(String x){
        this.userId = x;
    }
    
    public String getSession(){
        return this.userId;
    }
}
