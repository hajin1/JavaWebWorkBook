package servlets;

import dao.MemberDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 프런트 컨트롤러 적용
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 	throws ServletException {
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			request.setAttribute("members", memberDao.selectList());
			request.setAttribute("viewUrl", "/WEB-INF/views/MemberList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
