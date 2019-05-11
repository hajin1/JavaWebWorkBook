package servlets;

import dao.MemberDao;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            request.setCharacterEncoding("UTF-8");
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            memberDao.delete(Integer.parseInt(request.getParameter("no")));

            response.sendRedirect("list");

            //리프레시 정보를 응답 헤더에 추가
            //response.addHeader("Refresh", "1;url=list");
        } catch (Exception e) {
            try {
                response.sendRedirect("/WEB-INF/views/Error.jsp");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

}
