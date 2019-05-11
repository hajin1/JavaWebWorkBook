package servlets;

import dao.MemberDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 	throws ServletException {
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");

			request.setAttribute("members", memberDao.selectList());
			response.setContentType("text/html; charset=UTF-8");

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/MemberList.jsp");
			rd.include(request, response);
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Error.jsp");
			try {
				rd.forward(request, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
