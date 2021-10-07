package servlets;

import com.google.gson.Gson;
import entities.Customer;
import entities.Stock;
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


@WebServlet(name = "stockServlet",value = {"/stock-post","/stock-get","/stock-delete"})
public class StockServlet extends HttpServlet {

    SessionFactory sf = HibernateUtil.getSessionFactory();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String dupErr = "";
        int sid = 0;
        try {
            String obj = req.getParameter("obj");
            DBUtil db = new DBUtil();
            sid = db.insert_updateProduct(obj);
            if (sid == -1){
                sid = 0;
                    dupErr = "Duplicate entry";
            }
        }
        catch (Exception e){
            System.err.println("Ürün Ekleme Sırasında Bir Hata Oluştu! : "+e);
        }
        resp.setContentType("application/json");
        resp.getWriter().write(dupErr+""+sid);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        DBUtil db = new DBUtil();
        String stJson = db.productList();
        System.out.println("Here:"+stJson);
        resp.setContentType("application/json");
        resp.getWriter().write(""+stJson);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int return_id = 0;
        String dupErr = "";
        DBUtil dbUtil = new DBUtil();
        try {
            int st_id = Integer.parseInt(req.getParameter("st_id"));
            System.out.println("St Id Burada :"+st_id);
            return_id = dbUtil.deleteProduct(st_id);
            if (return_id == -1){
                dupErr ="Duplicate entry";
            }
        }catch (Exception e){
            System.out.println("Error : "+e);
        }
        resp.setContentType("application/json");
        resp.getWriter().write(dupErr+""+return_id);
    }
}
