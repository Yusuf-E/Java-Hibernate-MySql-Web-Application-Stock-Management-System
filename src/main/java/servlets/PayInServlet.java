package servlets;

import com.google.gson.Gson;
import utils.DBUtil;
import props.Payin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "payInServlet",value = { "/payin-post","/payin-get"})
public class PayInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int return_id = 0;

        DBUtil dbUtil = new DBUtil();
        String data = req.getParameter("obj");
        Gson gson = new Gson();
        Payin payin = gson.fromJson(data, Payin.class);
        return_id = dbUtil.addPayin(payin);
        resp.setContentType("application/json");
        resp.getWriter().write( "" +return_id );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtil db = new DBUtil();
        String stJson = db.payinList();

        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }
}
