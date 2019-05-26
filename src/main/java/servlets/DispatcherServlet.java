package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controls.*;
import vo.Member;

@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String servletPath = request.getServletPath();
        try {
            ServletContext sc = this.getServletContext();
            HashMap<String, Object> model = new HashMap<String, Object>();
            model.put("session", request.getSession());
            Controller pageController = (Controller) sc.getAttribute(servletPath);

            if ("/member/add.do".equals(servletPath)) {
                if (request.getParameter("email") != null) {
                    model.put("member", new Member()
                    .setEmail(request.getParameter("email"))
                    .setName(request.getParameter("name"))
                    .setPassword(request.getParameter("password")));
                }
            } else if ("/member/update.do".equals(servletPath)) {
                if (request.getParameter("email") == null) {
                    model.put("no", Integer.parseInt(request.getParameter("no")));
                } else {
                    model.put("member", new Member().setEmail(request.getParameter("email"))
                    .setName(request.getParameter("name"))
                    .setNo(Integer.parseInt(request.getParameter("no")))
                    .setPassword(request.getParameter("password")));
                }
            } else if ("/member/delete.do".equals(servletPath)) {
                model.put("no", Integer.parseInt(request.getParameter("no")));
            } else if ("/auth/login.do".equals(servletPath)) {
                if (request.getParameter("email") != null && request.getParameter("password") != null) {
                    model.put("email", String.valueOf(request.getParameter("email")));
                    model.put("password", String.valueOf((String)request.getParameter("password")));
                }
            }
            String viewUrl = pageController.execute(model);

            for (String key : model.keySet()) {
                request.setAttribute(key, model.get(key));
            }

            if (viewUrl.startsWith("redirect:")) {
                response.sendRedirect(viewUrl.substring(9));
                return;
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
                rd.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
            rd.forward(request, response);
        }
    }
}
