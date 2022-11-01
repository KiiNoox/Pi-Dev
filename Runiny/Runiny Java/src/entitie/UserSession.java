/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitie;

/**
 *
 * @author ACER EXTENSA 15
 */
public final class UserSession {

    private static UserSession instance;
    public int employeeId;
    public String privileges;

    
    private UserSession(int employeeId) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.employeeId = employeeId;
    }



    public static UserSession getInstance(int employeeId) {
         if (instance == null) {
            instance = new UserSession(employeeId);
        }
        return instance;
    }
    
    @Override
    public String toString() {
        return "UserSession{" + "employeeId=" + employeeId + '}';
    }
    

}
