package listeners;

import controls.*;
import dao.MySqlMemberDao;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event){
        try {
            ServletContext sc = event.getServletContext();

            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/studydb");

            MySqlMemberDao memberDao = new MySqlMemberDao();
            memberDao.setDataSource(ds);

            sc.setAttribute("/auth/login.do", new LogInController().setMemberDao(memberDao));
            sc.setAttribute("/auth/logout.do", new LogOutController());
            sc.setAttribute("/member/list.do", new MemberListController().setMemberDao(memberDao));
            sc.setAttribute("/member/update.do", new MemberUpdateController().setMemberDao(memberDao));
            sc.setAttribute("/member/delete.do", new MemberDeleteController().setMemberDao(memberDao));
            sc.setAttribute("/member/add.do", new MemberAddController().setMemberDao(memberDao));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) { }
}
