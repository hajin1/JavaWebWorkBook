package servlets;

import dao.MemberDao;
import vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/MemberForm.jsp");
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            try {
                response.sendRedirect("/WEB-INF/views/Error.jsp");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            request.setCharacterEncoding("UTF-8");
            ServletContext sc = this.getServletContext();
//            conn = (Connection) sc.getAttribute("conn");

            Member member = new Member().setEmail(request.getParameter("email"))
                    .setName(request.getParameter("name")).setPassword(request.getParameter("password"));

            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            memberDao.insert(member);

            response.sendRedirect("list");

            //리프레시 정보를 응답 헤더에 추가
            //response.addHeader("Refresh", "1;url=list");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendRedirect("/WEB-INF/views/Error.jsp");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
