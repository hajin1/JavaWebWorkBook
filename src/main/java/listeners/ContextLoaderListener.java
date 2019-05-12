package listeners;

import dao.MemberDao;
import util.DBConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    DBConnectionPool connPool;

    @Override
    public void contextInitialized(ServletContextEvent event){
        try {
            ServletContext sc = event.getServletContext();
            connPool = new DBConnectionPool(
                    sc.getInitParameter("driver"),
                    sc.getInitParameter("url"),
                    sc.getInitParameter("username"),
                    sc.getInitParameter("password"));
            MemberDao memberDao = new MemberDao();
            memberDao.setDbConnectionPool(connPool);

            sc.setAttribute("memberDao", memberDao);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        connPool.closeAll();
    }
}
