package view.Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import view.DatabaseConnection.DatabaseConnection;

public class Module {
    private static String module_ast;
    private RichOutputLabel it1;
    public String User_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 1 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/User_Management/User_Management.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }

    public String GL_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 2 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/General_Ledger/General_Ledger.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
}
    
    public String Payable_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 4 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/Payable/Payable.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
    
    public String Production_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 5 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/Production/Production.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
    
    public String Asset_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 6 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/Fixed_Assets/Fixed_Assets.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
    
    public String Treasury_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 7 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/Bank_Cash/Bank_Cash.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
    
   

    public String Drawer_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 8 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/Donation/Donation.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
    
    
    public String Payroll_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 9 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/Payroll/Payroll.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
    
    
    public String Report_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 10 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/Reports/Reports.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
    
    public String Receivable_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 11 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/Receivable/Receivable.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
    
    
    
   public String INV_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 12 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/Sale_Purchase/Sale_Purchase.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
   
   
    public String POS_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 13 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/POS/POS.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
   
    public String Sale_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 14 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/Sales/Sales.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
   
    public String School_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 15 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/School_Managment_System/School_Managment_System.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
   
   
    public String CRM_Module() {
        // Add event code here...
        String usid = this.getIt1().getValue().toString();
         System.out.println(" Module is : GL "+usid);
         Connection conn;
          try {
             conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rset =
             stmt.executeQuery("SELECT Tbl_User_D_Modules.id FROM Tbl_User_D_Modules " +
                             "where Tbl_User_D_Modules.Module_ID = 16 and Tbl_User_D_Modules.ast='Y' and user_master_id='" + usid + "'");
        if (rset.next()) {
                 //                conn.close();
                 //getting data against column from table
                 module_ast = (rset.getString("id"));
        //                company_name = (rset.getString("name"));
                 
                System.out.println(".........Module Right is :..." + module_ast + "...");
              
                return "/faces/Main_Pages/Modules/CRM/CRM.jsf?faces-redirect=true";
             } else {
                 showMessage("You Are Unauthorized To Access this Module");
                 conn.close();
                 System.out.println("........You Are Unauthorized To Access this Module........");
                 return "false";
        }
         } catch (SQLException e) {
             System.out.println(e);
         }
        return module_ast;
    }
   
    
    
    
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

    public void setIt1(RichOutputLabel it1) {
        this.it1 = it1;
    }

    public RichOutputLabel getIt1() {
        return it1;
    }


   
}
