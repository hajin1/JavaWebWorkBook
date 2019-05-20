package servlets;

import dao.MemberDao;
import vo.Member;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("viewUrl", "/WEB-INF/views/MemberForm.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");

            Member member = (Member)request.getAttribute("member");
            memberDao.insert(member);
            request.setAttribute("viewUrl", "redirect:list.do");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

}
