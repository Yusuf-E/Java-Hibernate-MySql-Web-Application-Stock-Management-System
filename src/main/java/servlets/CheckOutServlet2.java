package servlets;

import utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "checkOutServlet2",value =  { "/checkout-payment-get" })
public class CheckOutServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtil db = new DBUtil();
        String stJson = db.checkOutPayOut();

        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }
}

