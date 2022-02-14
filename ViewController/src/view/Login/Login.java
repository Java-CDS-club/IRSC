package view.Login;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import oracle.adf.view.rich.component.rich.input.RichInputText;
//import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
//import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.nav.RichLink;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;



import view.DatabaseConnection.DatabaseConnection;

public class Login {

    // generating static variables to use in different scopes
    private static String role_master_id;
    private static String user_master_id;
    private static String user_master_name;
    private static String user_detail_id;
    private static String company_id;
    private static String company_name;
    private static String sessUName;
    private static String sessCName;
//    private static Date COStart_Date;
//    private static String COEnd_Date;
//    private static String abc_Date;
    
    private RichInputText it1;
    private RichInputText it2;
    private RichLink l1;
    private RichInputText it3;
    private RichSelectOneChoice it4;
    //    private RichButton b1;
//    private RichPanelGroupLayout pgl2;
//    private RichButton b2;


    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }
     

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }
    
    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }
    
    public void setIt4(RichSelectOneChoice it4) {
        this.it4 = it4;
    }

    public RichSelectOneChoice getIt4() {
        return it4;
    }
    

    public void setL1(RichLink l1) {
        this.l1 = l1;
    }

    public RichLink getL1() {
        return l1;
    }

    ///////////////////////////////////////////

    public String showMessage(String msgs) {
        String messageText = msgs;
        FacesMessage fm = new FacesMessage(messageText);
        /**
             * set the type of the message.
             * Valid types: error, fatal,info,warning
             */
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
    }
    // session value storing function
    public static void storeOnSession(String key, Object object) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map sessionState = ctx.getExternalContext().getSessionMap();
        sessionState.put(key, object);
    }



    //user logging in
    public String login_action() {
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        
        // Add event code here...
        String username = this.getIt1().getValue().toString();
        String email = this.getIt3().getValue().toString();
        String password = this.getIt2().getValue().toString();
        String company = this.getIt4().getValue().toString();

        sessUName = username;
        storeOnSession("sessUName", sessUName);
        System.out.println("value for session..............." + sessUName);
        
        System.out.println("Entered username is : " + username + "....and email is : " + email);
        System.out.println(".......................................................................");
        System.out.println(".......................................................................");
        System.out.println("Entered username is : " + username + "....and password is : " + password);
        System.out.println(".......................................................................");
        System.out.println(".......................................................................");
        System.out.println("Entered username is : " + username + "....and company is : " + company);
        System.out.println(dateFormat.format(date));
        Connection conn;
        

        try {
            conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rset =
            stmt.executeQuery("SELECT tbl_user_master.role_master_id,tbl_user_master.user_master_id,tbl_user_detail.user_detail_id,tbl_user_detail.company_id,col_code,Tbl_Company.NAME FROM tbl_user_master,tbl_user_detail,Tbl_Company " +
                            "where tbl_user_detail.user_m_id=tbl_user_master.user_master_id  And tbl_user_detail.company_id=Tbl_Company.ID And user_master_name = '" + username + "'and user_master_email='"+ email+"' and user_master_pwd = '" + password + "' and company_id = '" + company + "'");

            if (rset.next()) {
                //                conn.close();
                //getting data against column from table
                role_master_id = (rset.getString("role_master_id")).toString();
                user_master_id = (rset.getString("user_master_id")).toString();
                user_detail_id = (rset.getString("user_detail_id")).toString();
                company_id = (rset.getString("company_id"));
                company_name = (rset.getString("name"));
                
//                COStart_Date = (rset.getDate("Start_Date"));
//                COEnd_Date = (rset.getString("End_Date")).toString();
                
                
//                if (rset.wasNull()) {
//                    project_id = ""; // set it to empty string as you desire.
//                }
//                
                
                if(rset.getString("company_id") != null)
                {
                    company_id = rset.getString("company_id").toString();
                }
                else
                {
                    company_id = "";
                }
                
                
                //Storing value in session username from input text field and role_master_id from DB

                System.out.println(".........User Name stored in session is :..." + username + "...");
//                System.out.println(".........User Password stored in session is :..." + password + "...");
                System.out.println(".........User Role stored in session is :..." + role_master_id + "...");
                System.out.println(".........User Master ID stored in session is :..." + user_master_id + "...");
                System.out.println(".........User detail ID stored in session is :..." + user_detail_id + "...");
                System.out.println(".........Company ID stored in session is :..." + company_id + "...");
                System.out.println(".........Company Name stored in session is :..." + company_name + "...");
                
//                System.out.println(".........Company Start Date in session is :..." + COStart_Date + "...");
                
                storeOnSession("sessRMID", role_master_id);                
                storeOnSession("sessUMID", user_master_id); 
                storeOnSession("sessUDID", user_detail_id);
                storeOnSession("sessCoID", company_id);
                storeOnSession("sessCoName", company_name);
               
                
                conn.close();
                
//                if(dateFormat.format(date) > COStart_Date){
                    
                return "/faces/Main_Pages/Dashboard.jsf?faces-redirect=true";
//                }
            } else {
                showMessage("Wrong Login Credentials");
                conn.close();
                System.out.println("........wrong login credentials........");
                //return "/faces/Main_Pages/Login.jsf?faces-redirect=true";
                return "false";

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return role_master_id;
    }

    //Logout > session cleared and session variable cleared
    public String logout_action() {
        System.out.println("logout called");
        sessUName = "";
        role_master_id = "";
        storeOnSession("sessUName", "");
        storeOnSession("sessUID", "");
        storeOnSession("sessCoID", "");
        storeOnSession("sessRID", "");
        //        return "good";
      
        return "/faces/Main_Pages/Login.jsf?faces-redirect=true";
    }
    
//    public String Company_Out() {
//        // Add event code here...
//        System.out.println("Company out called");
//        sessUName = "";
//        role_master_id = "";
//        storeOnSession("sessUName", "");
//        storeOnSession("sessUID", "");
//        storeOnSession("sessCoID", "");
//        storeOnSession("sessRID", "");
//        //        return "good";
//        
//        return "/faces/Main_Pages/Company.jsf?faces-redirect=true";
//    }

   
    public String login_Session() { 
        // Add event code here...
        {
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //get current date time with Date()
                Date date = new Date();
                System.out.println(dateFormat.format(date));
                
                // Add event code here...
//                String username = this.getIt1().getValue().toString();
                String email = this.getIt3().getValue().toString();
                String password = this.getIt2().getValue().toString();
//                String company = this.getIt4().getValue().toString();

                sessUName = user_master_name;
                storeOnSession("sessUName", sessUName);
                System.out.println("value for session..............." + sessUName);
                
                System.out.println("Entered username is : " + user_master_name + "....and email is : " + email);
                System.out.println(".......................................................................");
                System.out.println(".......................................................................");
                System.out.println("Entered username is : " + user_master_name + "....and password is : " + password);
                System.out.println(".......................................................................");
                System.out.println(".......................................................................");
//                System.out.println("Entered username is : " + username + "....and company is : " + company);
                System.out.println(dateFormat.format(date));
                Connection conn;
                

                try {
                    conn = DatabaseConnection.getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rset =
                    stmt.executeQuery("SELECT tbl_user_master.role_master_id,tbl_user_master.user_master_id,tbl_user_master.user_master_name,tbl_user_master.user_master_Name FROM tbl_user_master " +
                                    "where  user_master_email='"+ email+"' and user_master_pwd = '" + password +  "'");

                    if (rset.next()) {
                        //                conn.close();
                        //getting data against column from table
                        role_master_id = (rset.getString("role_master_id")).toString();
                        user_master_id = (rset.getString("user_master_id")).toString();
                        user_master_name = (rset.getString("user_master_name")).toString();
//                        user_detail_id = (rset.getString("user_detail_id")).toString();
//                        company_id = (rset.getString("company_id"));
//                        company_name = (rset.getString("name"));
                        
        //                COStart_Date = (rset.getDate("Start_Date"));
        //                COEnd_Date = (rset.getString("End_Date")).toString();
                        
                        
        //                if (rset.wasNull()) {
        //                    project_id = ""; // set it to empty string as you desire.
        //                }
        //                
                        
//                        if(rset.getString("company_id") != null)
//                        {
//                            company_id = rset.getString("company_id").toString();
//                        }
//                        else
//                        {
//                            company_id = "";
//                        }
                        
                        
                        //Storing value in session username from input text field and role_master_id from DB

                        System.out.println(".........User Name stored in session is :..." + user_master_name + "...");
        //                System.out.println(".........User Password stored in session is :..." + password + "...");
                        System.out.println(".........User Role stored in session is :..." + role_master_id + "...");
                        System.out.println(".........User Master ID stored in session is :..." + user_master_id + "...");
                        System.out.println(".........User Master Org name stored in session is :..." + user_master_name + "...");
//                        System.out.println(".........User detail ID stored in session is :..." + user_detail_id + "...");
//                        System.out.println(".........Company ID stored in session is :..." + company_id + "...");
//                        System.out.println(".........Company Name stored in session is :..." + company_name + "...");
                        
        //                System.out.println(".........Company Start Date in session is :..." + COStart_Date + "...");
                        
                        storeOnSession("sessRMID", role_master_id);                
                        storeOnSession("sessUMID", user_master_id); 
                        storeOnSession("sessUName", user_master_name); 
//                        storeOnSession("sessUDID", user_detail_id);
//                        storeOnSession("sessCoID", company_id);
//                        storeOnSession("sessCoName", company_name);
                       
                        
                        conn.close();
                        
        //                if(dateFormat.format(date) > COStart_Date){
                            
                        return "/faces/Main_Pages/Company.jsf?faces-redirect=true";

//                        return "true";
        //                }
                    } else {
                        showMessage("Wrong Login Credentials");
                        conn.close();
                        System.out.println("........wrong login credentials........");
                        //return "/faces/Main_Pages/Login.jsf?faces-redirect=true";
                        return "false";

                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }

                return role_master_id;
            }
    }

    public String login_company(){
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        
        // Add event code here...
       
        
        String company = this.getIt4().getValue().toString();
        
//        sessUName = company;
//        storeOnSession("sessUName", sessUName);
//        System.out.println("value for session..............." + sessUName);
        
        
        System.out.println(".......................................................................");
        System.out.println(".......................................................................");
        System.out.println(" company is : " + company);
        System.out.println(dateFormat.format(date));
        Connection conn;
        

        try {
            conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rset =
            stmt.executeQuery("SELECT Tbl_Company.id,Tbl_Company.NAME FROM Tbl_Company " +
                            "where Tbl_Company.id = '" + company + "'");

            if (rset.next()) {
                //                conn.close();
                //getting data against column from table
                
               
                company_id = (rset.getString("id"));
                company_name = (rset.getString("name"));
                
//                COStart_Date = (rset.getDate("Start_Date"));
//                COEnd_Date = (rset.getString("End_Date")).toString();
                
                
//                if (rset.wasNull()) {
//                    project_id = ""; // set it to empty string as you desire.
//                }
//                
                
                if(rset.getString("id") != null)
                {
                    company_id = rset.getString("id").toString();
                }
                else
                {
                    company_id = "";
                }
                
                
                //Storing value in session username from input text field and role_master_id from DB

//                System.out.println(".........User Name stored in session is :..." + username + "...");
//                
                System.out.println(".........Company ID stored in session is :..." + company_id + "...");
                System.out.println(".........Company Name stored in session is :..." + company_name + "...");
                
//                System.out.println(".........Company Start Date in session is :..." + COStart_Date + "...");
                
                
                storeOnSession("sessCoID", company_id);
                storeOnSession("sessCoName", company_name);
               
                
                conn.close();
                
//                if(dateFormat.format(date) > COStart_Date){
                    
                return "/faces/Main_Pages/Dashboard.jsf?faces-redirect=true";
//                }
            } else {
                showMessage("Wrong Login Credentials");
                conn.close();
                System.out.println("........wrong login credentials........");
                //return "/faces/Main_Pages/Login.jsf?faces-redirect=true";
                return "false";

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return company_id;
    }

    
}
