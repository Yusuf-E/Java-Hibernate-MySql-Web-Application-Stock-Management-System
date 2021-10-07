package servlets;

import utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "payOutServlet",value = { "/payout-post","/payout-get"})
public class PayOutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtil db = new DBUtil();
        String obj = req.getParameter("obj");

        int cid = db.insert_payOut(obj);
        resp.setContentType("application/json");
        resp.getWriter().write( "" +cid );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtil db = new DBUtil();
        String stJson = db.payoutList();

        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }
}
