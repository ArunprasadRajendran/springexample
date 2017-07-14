/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;


/**
 *
 * @author eswar@vaannila.com
 */
public class LoginAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        LoginForm loginForm = (LoginForm) form;
        JSONArray json = new JSONArray();
        
        request.setCharacterEncoding("UTF-8");
       // System.out.println(" ---------"+loginForm.getCustomer());
      //   System.out.println(" ----hhhh-----"+loginForm.getComment());
         Connection con = null;
          try {
              
                String name = request.getParameter("hiddedcustomer");
                System.out.println("name "+name);
                
                //String unicode=""\\u" + Integer.toHexString('name' | 0x10000).substring(1)";
               // String hexCode = Integer.toHexString(name).toUpperCase();
               
                Class.forName("org.postgresql.Driver");
                con  = DriverManager.getConnection("jdbc:postgresql://192.168.4.243:5432/transalatortest", "postgres","postgres");               
                System.out.println("connection coonected => "+con.isClosed());
                
                    /*  while (name.length() > 0) {
                      for (int index = 0; index < name.length(); index++) {
                          System.out.println("");
      // Convert the integer to a hexadecimal code.
                   String hexCode = Integer.toHexString(name.codePointAt(index)).toUpperCase();

 
      // but the it must be a four number value.
                     String hexCodeWithAllLeadingZeros = "0000" + hexCode;
               String hexCodeWithLeadingZeros = hexCodeWithAllLeadingZeros.substring(hexCodeWithAllLeadingZeros.length()-4);

      System.out.println("\\u" + hexCodeWithLeadingZeros);
    }}*/
                PreparedStatement preparedStatement = con.prepareStatement("insert into customer values (?)");
                preparedStatement.setString(1,name);
                int i =preparedStatement.executeUpdate();
                System.out.println("----> "+ i);
                return mapping.findForward(SUCCESS);
           
        } catch (Exception e) {
            e.getMessage();
          return mapping.findForward(FAILURE);
            
        }finally{
              con.close();
          }
       /* if (loginForm.getUserName().equals(loginForm.getPassword())) {
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(FAILURE);
        }*/
    }
    public String getTamilValue(String val)throws Exception {
        System.out.println("val->"+val);
        
        
        Connection con = null;
        
        String value="";
          try {
        Class.forName("org.postgresql.Driver");
                con  = DriverManager.getConnection("jdbc:postgresql://192.168.4.243:5432/transalatortest", "postgres","postgres");                
                System.out.println("connection coonected => "+con.isClosed());
                
                    /*  while (name.length() > 0) {
                      for (int index = 0; index < name.length(); index++) {
                          System.out.println("");
      // Convert the integer to a hexadecimal code.
                   String hexCode = Integer.toHexString(name.codePointAt(index)).toUpperCase();

 
      // but the it must be a four number value.
                     String hexCodeWithAllLeadingZeros = "0000" + hexCode;
               String hexCodeWithLeadingZeros = hexCodeWithAllLeadingZeros.substring(hexCodeWithAllLeadingZeros.length()-4);

      System.out.println("\\u" + hexCodeWithLeadingZeros);
    }}*/
                PreparedStatement preparedStatement = con.prepareStatement("select customername from customer");
                ResultSet rs =preparedStatement.executeQuery();
                 
                if(rs.next()){
                   value = rs.getString("customername");
                }
                
             /*   preparedStatement.setString(1,val);
                int i =preparedStatement.executeUpdate();
                System.out.println("----> "+ i);*/
   
          } catch (Exception e) {
            e.getMessage();
                     
        }finally{
              con.close();
          }
           return value;
}
}
