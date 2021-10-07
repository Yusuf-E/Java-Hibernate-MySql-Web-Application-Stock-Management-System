package servlets;

import com.google.gson.Gson;
import entities.Customer;
import props.Report;
import utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "checkOutServlet",value =  { "/checkout-payin-get" ,"/checkout-post"})
public class CheckOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtil db = new DBUtil();
        String stJson = db.checkOutPayIn();

        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stJson ="";
        DBUtil db = new DBUtil();
        String obj = req.getParameter("obj");
        Gson gson = new Gson();
        Report report = gson.fromJson(obj, Report.class);
        System.out.println(report);
        if (report.getCtype() == 2){
            // Çıkışları Getir
            stJson = db.filteredPayoutList(report);
        }
        else if (report.getCu_id() == -1){
            // Tüm Girişleri Getir
            stJson = db.filteredPayinList(report);
        }
        else{
            // Müşterinin Girişleri Getir
            stJson = db.filteredCustomerPayin(report);
        }

        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }
}
