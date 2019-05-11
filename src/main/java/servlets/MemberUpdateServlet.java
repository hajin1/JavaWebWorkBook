package servlets;

import dao.MemberDao;
import vo.Member;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Integer.parseInt;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        try {
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            Member member = new Member().setEmail(request.getParameter("email"))
                    .setNo(Integer.parseInt(request.getParameter("no")))
                    .setName(request.getParameter("name"));
            memberDao.update(member);
            response.sendRedirect("list");

        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Error.jsp");
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
       }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        response.setContentType("text/html; charset=UTF-8");

        try{
            ServletContext sc = this.getServletContext();
            MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
            int no = parseInt(request.getParameter("no"));

            Member member = memberDao.selectOne(no);
            if(member != null){
                request.setAttribute("member", member);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/MemberUpdateForm.jsp");
            rd.include(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{ if(rs!=null) rs.close(); } catch(Exception e){}
            try{ if(stmt!=null) stmt.close(); } catch(Exception e){}
            try{ if(conn!=null) conn.close(); } catch(Exception e){}
        }
    }
}





