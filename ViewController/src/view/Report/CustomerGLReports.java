package view.Report;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import view.DatabaseConnection.DatabaseConnection;

public class CustomerGLReports {
    private RichSelectOneChoice report_type;
    private RichSelectOneChoice format_type;
    private RichInputDate fromDateParam;
    private RichInputDate toDateParam;
    private RichSelectOneChoice rblCustidparam;
    private RichSelectOneChoice projectidparam;
    private RichSelectOneChoice companyidparam;

    public CustomerGLReports() {
    }

    private static String selectedReportType = "";
    private static String gotFormat = "";
    private static BigDecimal gotrblCustid;
    private static BigDecimal gotprojectId;
    private static BigDecimal gotcompanyId;

    public String gen_Report() {
        // Add event code here...
        selectedReportType = (String) this.getReport_type().getValue();
        gotFormat = (String) this.getFormat_type().getValue();
        gotrblCustid = (BigDecimal) this. getRblCustidparam().getValue();
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
            if (gotrblCustid != null) {
                reportBean.setReportParameter("P_AccID", gotrblCustid.toString());
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

        case "customerLedger":

            //working for procedure call//
            
            if (getFromDate() != "" & getToDate() != "" & gotrblCustid != null ) {
                    
                   
                    BigDecimal P_AccID = gotrblCustid;
                    String P_Fdate = getFromDate();
                    String P_Tdate = getToDate();
                   
                    //calling procedure start//
                    Connection conn;
                    ResultSet rs;
                    try {
                        conn = DatabaseConnection.getConnection();

        //first procedure
                        CallableStatement cstmt = null;
                        String SQL = "{call P_RBL_GL(?,?)}";
                        cstmt = conn.prepareCall(SQL);
                       
                        cstmt.setBigDecimal(1, P_AccID );
                        cstmt.setString(2, P_Fdate );
                        rs = null;
                        rs = cstmt.executeQuery();
                        
        //second procedure
                        CallableStatement cstmt2 = null;
                        String SQL2 = "{call P_RBL_GL_DP(?,?,?)}";
                        cstmt2 = conn.prepareCall(SQL2);
                        
                        cstmt2.setBigDecimal(1, P_AccID );
                        cstmt2.setString(2, P_Fdate );
                        cstmt2.setString(3, P_Tdate );
                        
                        
                        rs = null;
                        rs = cstmt2.executeQuery();
                        
                        
                        
                    
                    
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    
                    reportBean.setReportURLName("userid=irsc/irscir@orcl&domain=classicdomain&report=C:/IRSC_Reports/Customer_Ledger&");

                }
            
            if (getFromDate() != "" & getToDate() != "" & gotrblCustid != null &  gotprojectId != null ) {
                    
                   
                    BigDecimal P_AccID = gotrblCustid;
                    BigDecimal P_Project_ID = gotprojectId;
                    String P_Fdate = getFromDate();
                    String P_Tdate = getToDate();
                   
                    //calling procedure start//
                    Connection conn;
                    ResultSet rs;
                    try {
                        conn = DatabaseConnection.getConnection();

            //first procedure
                        CallableStatement cstmt = null;
                        String SQL = "{call P_RBL_GL_PROJECT(?,?,?)}";
                        cstmt = conn.prepareCall(SQL);
                       
                        cstmt.setBigDecimal(1, P_AccID );
                        cstmt.setString(2, P_Fdate );
                        cstmt.setBigDecimal(3, P_Project_ID );
                        
                        rs = null;
                        rs = cstmt.executeQuery();
                        
            //second procedure
                        CallableStatement cstmt2 = null;
                        String SQL2 = "{call P_RBL_GL_DP_PROJECT(?,?,?,?)}";
                        cstmt2 = conn.prepareCall(SQL2);
                        
                        cstmt2.setBigDecimal(1, P_AccID );
                        cstmt2.setString(2, P_Fdate );
                        cstmt2.setString(3, P_Tdate );
                        cstmt2.setBigDecimal(4, P_Project_ID );
                        
                        rs = null;
                        rs = cstmt2.executeQuery();
                        
                        
                        
                    
                    
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    
                    reportBean.setReportURLName("userid=irsc/irscir@orcl&domain=classicdomain&report=C:/IRSC_Reports/Customer_Ledger&");

                }
            else{
                showMessage("Please Select From Date,TO Date, Customer Name ");
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

            if (getFromDate() != "" & getToDate() != "" & gotrblCustid != null ) {
        url = reportBean.getReportServerURL();
        System.out.println("Url => " + url);
        reportBean.openUrlInNewWindow(url);
            }
        }
        return null;
    }
    
    public String run_Proce() {
        // Add event code here...
        selectedReportType = (String) this.getReport_type().getValue();
        gotFormat = (String) this.getFormat_type().getValue();
        gotrblCustid = (BigDecimal) this. getRblCustidparam().getValue();
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
            if (gotrblCustid != null) {
                reportBean.setReportParameter("P_AccID", gotrblCustid.toString());
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

        case "customerLedger":

            //working for procedure call//
            
            if (getFromDate() != "" & getToDate() != "" & gotrblCustid != null ) {
                    
                   
                    BigDecimal P_AccID = gotrblCustid;
                    String P_Fdate = getFromDate();
                    String P_Tdate = getToDate();
                   
                    //calling procedure start//
                    Connection conn;
                    ResultSet rs;
                    try {
                        conn = DatabaseConnection.getConnection();

        //first procedure
                        CallableStatement cstmt = null;
                        String SQL = "{call P_RBL_GL(?,?)}";
                        cstmt = conn.prepareCall(SQL);
                       
                        cstmt.setBigDecimal(1, P_AccID );
                        cstmt.setString(2, P_Fdate );
                        
                        rs = cstmt.executeQuery();
                        
        //second procedure
                        CallableStatement cstmt2 = null;
                        String SQL2 = "{call P_RBL_GL_DP(?,?,?)}";
                        cstmt2 = conn.prepareCall(SQL2);
                        
                        cstmt2.setBigDecimal(1, P_AccID );
                        cstmt2.setString(2, P_Fdate );
                        cstmt2.setString(3, P_Tdate );
                        rs = cstmt2.executeQuery();
                        
                        
                        
                    
                    
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    
                    reportBean.setReportURLName("userid=irsc/irscir@orcl&domain=classicdomain&report=C:/IRSC_Reports/Customer_Ledger&");

                }
            
            if (getFromDate() != "" & getToDate() != "" & gotrblCustid != null &  gotprojectId != null ) {
                    
                   
                    BigDecimal P_AccID = gotrblCustid;
                    BigDecimal P_Project_ID = gotprojectId;
                    String P_Fdate = getFromDate();
                    String P_Tdate = getToDate();
                   
                    //calling procedure start//
                    Connection conn;
                    ResultSet rs;
                    try {
                        conn = DatabaseConnection.getConnection();

            //first procedure
                        CallableStatement cstmt = null;
                        String SQL = "{call P_RBL_GL_PROJECT(?,?,?)}";
                        cstmt = conn.prepareCall(SQL);
                       
                        cstmt.setBigDecimal(1, P_AccID );
                        cstmt.setString(2, P_Fdate );
                        cstmt.setBigDecimal(3, P_Project_ID );
                        
                        rs = cstmt.executeQuery();
                        
            //second procedure
                        CallableStatement cstmt2 = null;
                        String SQL2 = "{call P_RBL_GL_DP_PROJECT(?,?,?,?)}";
                        cstmt2 = conn.prepareCall(SQL2);
                        
                        cstmt2.setBigDecimal(1, P_AccID );
                        cstmt2.setString(2, P_Fdate );
                        cstmt2.setString(3, P_Tdate );
                        cstmt2.setBigDecimal(4, P_Project_ID );
                        rs = cstmt2.executeQuery();
                        
                        
                        
                    
                    
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    
                    reportBean.setReportURLName("userid=irsc/irscir@orcl&domain=classicdomain&report=C:/IRSC_Reports/Customer_Ledger&");

                }
            else{
                showMessage("Please Select From Date, Project, Item ");
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

    public void setReport_type(RichSelectOneChoice report_type) {
        this.report_type = report_type;
    }

    public RichSelectOneChoice getReport_type() {
        return report_type;
    }

    public void setFormat_type(RichSelectOneChoice format_type) {
        this.format_type = format_type;
    }

    public RichSelectOneChoice getFormat_type() {
        return format_type;
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

    public void setRblCustidparam(RichSelectOneChoice rblCustidparam) {
        this.rblCustidparam = rblCustidparam;
    }

    public RichSelectOneChoice getRblCustidparam() {
        return rblCustidparam;
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
}
