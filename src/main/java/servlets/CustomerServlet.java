package servlets;

import com.google.gson.Gson;
import entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.DBUtil;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "customerServlet", value = { "/customer-post", "/customer-delete", "/customer-get" })
public class CustomerServlet extends HttpServlet {

    SessionFactory sf = HibernateUtil.getSessionFactory();

    // /customer-insert
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DBUtil db = new DBUtil();
        String obj = req.getParameter("obj");

        int cid = db.insert_updateCustomer(obj);
        resp.setContentType("application/json");
        resp.getWriter().write( "" +cid );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DBUtil db = new DBUtil();
        String stJson = db.customerList();

        resp.setContentType("application/json");
        resp.getWriter().write( stJson );
    }

    // /customer-remove
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int return_id=0;
        try {
            int cu_id = Integer.parseInt( req.getParameter("cu_id") );
            DBUtil db = new DBUtil();
            return_id = db.deleteCustomer(cu_id);
        }catch (Exception ex) {
            System.err.println("Delete Error : " + ex);
        }
        resp.setContentType("application/json");
        resp.getWriter().write( ""+return_id );
    }


}
