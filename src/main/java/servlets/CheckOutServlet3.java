package servlets;

import utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "checkOutServlet3",value = {"/checkout-amount-get","/allbills-post"})
public class CheckOutServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtil db = new DBUtil();
        String stJson = db.checkOutAmount();

        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtil db = new DBUtil();
        String stJson = db.allBills();

        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }
}
