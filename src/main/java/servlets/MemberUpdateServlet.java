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

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            Member member = (Member)request.getAttribute("member");
            memberDao.update(member);
            request.setAttribute("viewUrl", "redirect:list.do");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

            Member member = memberDao.selectOne(Integer.parseInt(request.getParameter("no")));
            request.setAttribute("member", member);
            request.setAttribute("viewUrl", "/WEB-INF/views/MemberUpdateForm.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}





