package servlets;

import utils.DBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "rememberServlet",value = "/remember")
public class RememberServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        System.out.println(email);
        DBUtil db = new DBUtil();
        String stJson = db.rememberMe(email);
        resp.setContentType("application/json");
        resp.getWriter().write("" + stJson );
    }
}
