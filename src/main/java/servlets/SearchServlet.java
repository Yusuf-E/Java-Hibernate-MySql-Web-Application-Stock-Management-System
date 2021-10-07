package servlets;

import com.google.gson.Gson;
import entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "searchServlet",value = "/search-customer")
public class SearchServlet extends HttpServlet {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        Gson gson = new Gson();
        String q =req.getParameter("data");
        q =q.trim().replaceAll("\\s+ " ,"*");
        System.out.println(q);
        List<Customer> cusSearches = sesi.createSQLQuery("CALL fullTextSearch(?)")
                .addEntity(Customer.class)
                .setParameter(1,q)
                .getResultList();


        String stJson = gson.toJson(cusSearches);
        System.out.println(stJson);
        resp.setContentType("application/json");
        resp.getWriter().write( stJson );


    }
    }
