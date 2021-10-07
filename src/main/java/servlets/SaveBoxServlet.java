package servlets;

import com.google.gson.Gson;
import utils.DBUtil;
import props.SaveBox;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "saveBoxServlet" ,value = { "/savebox-post" ,"/bills-get"})
public class SaveBoxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int return_id =0;
        String data = req.getParameter("obj");
        DBUtil dbUtil = new DBUtil();
        Gson gson = new Gson();
        SaveBox saveBox = gson.fromJson(data,SaveBox.class);
        return_id = dbUtil.saveBox(saveBox);
        resp.setContentType("application/json");
        resp.getWriter().write( "" +return_id );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cu_id = req.getParameter("id");
        DBUtil db = new DBUtil();
        String stJson = db.billList( cu_id );
        System.out.println(stJson);
        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }
}
