package servlets;

import dao.MemberDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try{
            request.setCharacterEncoding("UTF-8");
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            memberDao.delete(Integer.parseInt(request.getParameter("no")));
            request.setAttribute("viewUrl", "redirect:list.do");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
