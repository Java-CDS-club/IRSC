package view.Report;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import view.DatabaseConnection.DatabaseConnection;
import java.sql.CallableStatement;
import java.util.Date;
import java.util.Locale;

import javax.faces.event.ActionEvent;

import view.DatabaseConnection.DatabaseConnection;

public class TrialBalanceReports {
    private RichSelectOneChoice format_type;
    private RichSelectOneChoice report_type;
    private RichInputDate fromDateParam;
    private RichInputDate toDateParam;
    private RichSelectOneChoice projectidparam;
    private RichSelectOneChoice companyidparam;
    private RichSelectOneChoice glL4idparam;

    public TrialBalanceReports() {
    }
    
    private static String selectedReportType = "";
    private static String gotFormat = "";
    private static BigDecimal gotprojectId;
    private static BigDecimal gotcompanyId;
    private static BigDecimal gotGlL4id;

    public String gen_Report() {
        // Add event code here...
        selectedReportType = (String) this.getReport_type().getValue();
        gotFormat = (String) this.getFormat_type().getValue();
        gotprojectId = (BigDecimal) this.getProjectidparam().getValue();
        gotcompanyId = (BigDecimal) this.getCompanyidparam().getValue();
        
        
        
        
    DatabaseConnection dbconnect = new DatabaseConnection();
    OracleReportBean reportBean = new OracleReportBean(dbconnect.getUipReport(), dbconnect.getUportReport(), null);
    String url = "";

    if (getFromDate() != "") {
        reportBean.setReportParameter("P_Fdated", getFromDate());
    }
    if (getToDate() != "") {
        reportBean.setReportParameter("P_Tdated", getToDate());
    }
        if (gotprojectId != null) {
            reportBean.setReportParameter("P_Project_id", gotprojectId.toString());
        }
        if (gotcompanyId != null) {
                    reportBean.setReportParameter("P_Company_id", gotcompanyId.toString());
        }
        
        
    

    if (gotFormat == "") {
        showMessage("Please Select Report Format");
    } else {

        switch (selectedReportType) {

        case "trialReport":

            //working for procedure call//
            
            if (getFromDate() != "" & getToDate() != ""  & gotcompanyId != null) {
                    
                    
                    
                    String pstdt = getFromDate();
                    String pendt = getToDate();
                    BigDecimal P_Company_ID = gotcompanyId;
                    //calling procedure start//
                    Connection conn;
                    ResultSet rs;
                    CallableStatement cstmt = null;
                    try {
                        conn = DatabaseConnection.getConnection();
                        String SQL = "{call P_TB(?,?,?)}";
                        cstmt = conn.prepareCall(SQL);
                        
                       
                        cstmt.setString(1, pstdt );
                        cstmt.setString(2, pendt );
                        cstmt.setBigDecimal(3, P_Company_ID );
                        
                        rs = null;
                        rs = cstmt.executeQuery();
                        
                    
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    
                    reportBean.setReportURLName("userid=irsc/irscir@orcl&domain=classicdomain&report=C:/IRSC_Reports/Trial_Balace_Report&");

                }
             if (getFromDate() != "" & getToDate() != "" & gotprojectId != null & gotcompanyId != null  ) {
                    
                    
                    
                    String pstdt = getFromDate();
                    String pendt = getToDate();
                    BigDecimal p_project_id = gotprojectId;
                    BigDecimal P_Company_ID = gotcompanyId;
                   
                    //calling procedure start//
                    Connection conn;
                    ResultSet rs;
                    CallableStatement cstmt = null;
                    try {
                        conn = DatabaseConnection.getConnection();
                        String SQL = "{call P_TB_PROJECT(?,?,?,?)}";
                        cstmt = conn.prepareCall(SQL);
                        
                       
                        cstmt.setString(1, pstdt );
                        cstmt.setString(2, pendt );
                        cstmt.setBigDecimal(3, p_project_id );
                        cstmt.setBigDecimal(4, P_Company_ID );
                        rs = null;
                        rs = cstmt.executeQuery();
                        
                        
                        
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    
                    reportBean.setReportURLName("userid=irsc/irscir@orcl&domain=classicdomain&report=C:/IRSC_Reports/Trial_Balace_Report&");

                }
            else{
                showMessage("Please Select From Date, Project, Item & Department");
            }
            
            break;
            //calling procedure end//
        default:
            showMessage("Please Select Report Type");
            break;

        }

        reportBean.setReportServerParam(OracleReportBean.RS_PARAM_DESTYPE,
                                        "CACHE"); // which will be one of the [cashe - file - mail - printer]
        reportBean.setReportServerParam(OracleReportBean.RS_PARAM_DESFORMAT,
                                        gotFormat); // Which will be onr of the [HTML - HTML CSS - PDF - SPREADSHEET- RTF].
        reportBean.setReportParameter("paramform", "no");
        if (getFromDate() != "" & getToDate() != ""  ) {
        url = reportBean.getReportServerURL();
        
        System.out.println("Url => " + url);
        reportBean.openUrlInNewWindow(url);
        }
    }
    return null;
    }
    
    public String run_Proce() {
//         Add event code here...
        selectedReportType = (String) this.getReport_type().getValue();
        gotFormat = (String) this.getFormat_type().getValue();
        gotprojectId = (BigDecimal) this.getProjectidparam().getValue();
        gotcompanyId = (BigDecimal) this.getCompanyidparam().getValue();
        
        
        DatabaseConnection dbconnect = new DatabaseConnection();
        OracleReportBean reportBean = new OracleReportBean(dbconnect.getUipReport(), dbconnect.getUportReport(), null);
        String url = "";

        if (getFromDate() != "") {
        reportBean.setReportParameter("P_Fdated", getFromDate());
        }
        if (getToDate() != "") {
        reportBean.setReportParameter("P_Tdated", getToDate());
        }
        if (gotprojectId != null) {
            reportBean.setReportParameter("P_Project_id", gotprojectId.toString());
        }
        if (gotcompanyId != null) {
                    reportBean.setReportParameter("P_Company_id", gotcompanyId.toString());
        }
        

        if (gotFormat == "") {
        showMessage("Please Select Report Format");
        } else {

        switch (selectedReportType) {

        case "trialReport":

            //working for procedure call//
            
            if (getFromDate() != "" & getToDate() != "" ) {
                    
                    
                    
                    String pstdt = getFromDate();
                    String pendt = getToDate();
                   
                    //calling procedure start//
                    Connection conn;
                    ResultSet rs;
                    CallableStatement cstmt = null;
                    try {
                        conn = DatabaseConnection.getConnection();
                        String SQL = "{call P_TB(?,?)}";
                        cstmt = conn.prepareCall(SQL);
                        
                       
                        cstmt.setString(1, pstdt );
                        cstmt.setString(2, pendt );
                        
                        
                        
                        rs = cstmt.executeQuery();
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    
                    reportBean.setReportURLName("userid=irsc/irscir@orcl&domain=classicdomain&report=C:/IRSC_Reports/Trial_Balace_Report&");

                }
            if (getFromDate() != "" & getToDate() != "" & gotprojectId != null   ) {
                    
                    
                    
                    String pstdt = getFromDate();
                    String pendt = getToDate();
                    BigDecimal p_project_id = gotprojectId;
                
                   
                    //calling procedure start//
                    Connection conn;
                    ResultSet rs;
                    CallableStatement cstmt = null;
                    try {
                        conn = DatabaseConnection.getConnection();
                        String SQL = "{call P_TB_PROJECT(?,?,?)}";
                        cstmt = conn.prepareCall(SQL);
                        
                       
                        cstmt.setString(1, pstdt );
                        cstmt.setString(2, pendt );
                        cstmt.setBigDecimal(3, p_project_id );
                        
                        
                        rs = cstmt.executeQuery();
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    
                    reportBean.setReportURLName("userid=irsc/irscir@orcl&domain=classicdomain&report=C:/IRSC_Reports/Trial_Balace_Report&");

                }
            else{
                showMessage("Please Select From Date, Project ");
            }
            
            break;
            //calling procedure end//
        default:
            showMessage("Please Select Report Type");
            break;

        }

        reportBean.setReportServerParam(OracleReportBean.RS_PARAM_DESTYPE,
                                        "CACHE"); // which will be one of the [cashe - file - mail - printer]
        reportBean.setReportServerParam(OracleReportBean.RS_PARAM_DESFORMAT,
                                        gotFormat); // Which will be onr of the [HTML - HTML CSS - PDF - SPREADSHEET- RTF].
        reportBean.setReportParameter("paramform", "no");

//        url = reportBean.getReportServerURL();
//        System.out.println("Url => " + url);
//        reportBean.openUrlInNewWindow(url);

        }
        return null;
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
    
    private String getFromDate() {
        if (fromDateParam.getValue() != null && fromDateParam.getValue() != "") {
            try {
                DateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                Date parsedDate = sdf.parse(fromDateParam.getValue().toString());
                SimpleDateFormat print = new SimpleDateFormat("dd-MMM-yy");
                return (print.format(parsedDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private String getToDate() {
        if (toDateParam.getValue() != null && toDateParam.getValue() != "") {
            try {
                DateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                Date parsedDate = sdf.parse(toDateParam.getValue().toString());
                SimpleDateFormat print = new SimpleDateFormat("dd-MMM-yy");
                return (print.format(parsedDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void setFormat_type(RichSelectOneChoice format_type) {
        this.format_type = format_type;
    }

    public RichSelectOneChoice getFormat_type() {
        return format_type;
    }

    public void setReport_type(RichSelectOneChoice report_type) {
        this.report_type = report_type;
    }

    public RichSelectOneChoice getReport_type() {
        return report_type;
    }

    public void setFromDateParam(RichInputDate fromDateParam) {
        this.fromDateParam = fromDateParam;
    }

    public RichInputDate getFromDateParam() {
        return fromDateParam;
    }

    public void setToDateParam(RichInputDate toDateParam) {
        this.toDateParam = toDateParam;
    }

    public RichInputDate getToDateParam() {
        return toDateParam;
    }


    public void setProjectidparam(RichSelectOneChoice projectidparam) {
        this.projectidparam = projectidparam;
    }

    public RichSelectOneChoice getProjectidparam() {
        return projectidparam;
    }

    public void setCompanyidparam(RichSelectOneChoice companyidparam) {
        this.companyidparam = companyidparam;
    }

    public RichSelectOneChoice getCompanyidparam() {
        return companyidparam;
    }

    public void gen_GL(ActionEvent actionEvent) {
        // Add event code here...
        BigDecimal sendGLID = (BigDecimal) actionEvent.getComponent().getAttributes().get("sendGLID");
//        selectedReportType = (String) this.getReport_type().getValue();
//        gotFormat = (String) this.getFormat_type().getValue();
//        gotGlL4id = (BigDecimal) this.getGlL4idparam().getValue();
        gotprojectId = (BigDecimal) this.getProjectidparam().getValue();
        gotcompanyId = (BigDecimal) this.getCompanyidparam().getValue();
        
        System.out.println(" L4 is : " + sendGLID);
        System.out.println(" Project is : " + gotprojectId);
        System.out.println(" Company is : " + gotcompanyId);
        
        
        DatabaseConnection dbconnect = new DatabaseConnection();
        OracleReportBean reportBean = new OracleReportBean(dbconnect.getUipReport(), dbconnect.getUportReport(), null);
        String url = "";

        if (getFromDate() != "") {
        reportBean.setReportParameter("P_Fdated", getFromDate());
        }
        if (getToDate() != "") {
        reportBean.setReportParameter("P_Tdated", getToDate());
        }
        
            if (sendGLID != null) {
                reportBean.setReportParameter("P_AccID", sendGLID.toString());
            }
            if (gotprojectId != null) {
                reportBean.setReportParameter("P_Project_id", gotprojectId.toString());
            }
            if (gotcompanyId != null) {
                        reportBean.setReportParameter("P_Company_id", gotcompanyId.toString());
            }
            
        

//        
        

       
            
            //working for procedure call//
            
            if (getFromDate() != "" & getToDate() != "" & sendGLID != null & gotcompanyId != null & gotprojectId == null) {
                    
                   
                    BigDecimal P_AccID = sendGLID;
                    String P_Fdate = getFromDate();
                    String P_Tdate = getToDate();
                    BigDecimal P_Company_ID = gotcompanyId;
                    //calling procedure start//
                    Connection conn;
                    ResultSet rs;
                    try {
                        conn = DatabaseConnection.getConnection();

        //first procedure
                        
                                CallableStatement cstmt = null;
                                String SQL = "{call P_GL(?,?,?)}";
                                cstmt = conn.prepareCall(SQL);
                                
                                cstmt.setBigDecimal(1, P_AccID );
                                cstmt.setString(2, P_Fdate );
                        cstmt.setBigDecimal(3, P_Company_ID );
                                rs = null;
                                rs = cstmt.executeQuery();
                            
                        
                        
        //second procedure
                        
                        CallableStatement cstmt2 = null;
                        String SQL2 = "{call P_GL_DP(?,?,?,?)}";
                        cstmt2 = conn.prepareCall(SQL2);
                        
                        cstmt2.setBigDecimal(1, P_AccID );
                        cstmt2.setString(2, P_Fdate );
                        cstmt2.setString(3, P_Tdate );
                        cstmt2.setBigDecimal(4, P_Company_ID );
                        rs = null;
                        rs = cstmt2.executeQuery();
                        
                        
                        
                        
                    
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    
                reportBean.setReportURLName("userid=irsc/irscir@orcl&domain=classicdomain&report=C:/IRSC_Reports/General_Ledger&");

            }
             if (getFromDate() != "" & getToDate() != "" & sendGLID != null & gotcompanyId != null & gotprojectId != null ) {
                    
                   
                    BigDecimal P_AccID = sendGLID;
                    BigDecimal P_PROJECTID = gotprojectId;
                    String P_Fdate = getFromDate();
                    String P_Tdate = getToDate();
                BigDecimal P_Company_ID = gotcompanyId;
                    //calling procedure start//
                    Connection conn;
                    ResultSet rs;
                    try {
                        conn = DatabaseConnection.getConnection();

            //first procedure
                        CallableStatement cstmt = null;
                        String SQL = "{call P_GL_PROJECT(?,?,?,?)}";
                        cstmt = conn.prepareCall(SQL);
                       
                        cstmt.setBigDecimal(1, P_AccID );
                        cstmt.setString(2, P_Fdate );
                        cstmt.setBigDecimal(3, P_PROJECTID );
                        cstmt.setBigDecimal(4, P_Company_ID );
                        rs = null;
                        rs = cstmt.executeQuery();
                        
            //second procedure
                        CallableStatement cstmt2 = null;
                        String SQL2 = "{call P_GL_DP_PROJECT(?,?,?,?,?)}";
                        cstmt2 = conn.prepareCall(SQL2);
                        
                        cstmt2.setBigDecimal(1, P_AccID );
                        cstmt2.setString(2, P_Fdate );
                        cstmt2.setString(3, P_Tdate );
                        cstmt2.setBigDecimal(4, P_PROJECTID );
                        cstmt2.setBigDecimal(5, P_Company_ID );
                        rs = null;
                        rs = cstmt2.executeQuery();
                        
                        
                        
                        
                    
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    
                reportBean.setReportURLName("userid=irsc/irscir@orcl&domain=classicdomain&report=C:/IRSC_Reports/General_Ledger&"); 

            }
            
            
           
            
           
            //calling procedure end//
            reportBean.setReportServerParam(OracleReportBean.RS_PARAM_DESTYPE,
                                            "CACHE"); // which will be one of the [cashe - file - mail - printer]
            reportBean.setReportServerParam(OracleReportBean.RS_PARAM_DESFORMAT,
                                            "PDF"); // Which will be onr of the [HTML - HTML CSS - PDF - SPREADSHEET- RTF].
            reportBean.setReportParameter("paramform", "no");

            url = reportBean.getReportServerURL();
            System.out.println("Url => " + url);
            reportBean.openUrlInNewWindow(url);

        }

        
       
    

    public void setGlL4idparam(RichSelectOneChoice glL4idparam) {
        this.glL4idparam = glL4idparam;
    }

    public RichSelectOneChoice getGlL4idparam() {
        return glL4idparam;
    }
}
