package servlets;

import com.google.gson.Gson;
import props.Box;
import utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "boxActionServlet",value = {"/boxaction-get","/boxaction-post","/boxaction-delete"})
public class BoxActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String obj = req.getParameter("id");
        int id = Integer.parseInt(obj);
        System.out.println("Data : "+ obj);
        DBUtil dbUtil = new DBUtil();
        String stJson = dbUtil.getOrders(id);

        resp.setContentType("application/json");
        resp.getWriter().write(stJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int return_id = 0;

        DBUtil dbUtil = new DBUtil();
        String data = req.getParameter("obj");
        Gson gson = new Gson();
        Box box = gson.fromJson(data,Box.class);
        System.out.println(data);
        if (box.getUpdate_id() == 1){
            return_id = dbUtil.addBox( box );
        }
        resp.setContentType("application/json");
        resp.getWriter().write( "" +return_id );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int return_id=0;
        try {
            int box_bid = Integer.parseInt( req.getParameter("box_bid") );
            DBUtil db = new DBUtil();
            return_id = db.deleteProductList(box_bid);
        }catch (Exception ex) {
            System.err.println("Delete Error : " + ex);
        }
        resp.setContentType("application/json");
        resp.getWriter().write( ""+return_id );
    }
}
