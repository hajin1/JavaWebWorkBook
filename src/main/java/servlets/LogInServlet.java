package servlets;

import dao.MemberDao;
import vo.Member;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("viewUrl", "/WEB-INF/views/auth/LogInForm.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext sc = this.getServletContext();
        MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
        Member member = memberDao.exist(request.getParameter("email"), request.getParameter("password"));

        if(member != null){
            request.setAttribute("member", member);
            request.setAttribute("viewUrl", "redirect:list.do");
        } else {
            request.setAttribute("viewUrl", "/WEB-INF/views/auth/LogInFail.jsp");
        }
    }
}
