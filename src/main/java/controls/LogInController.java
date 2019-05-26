package controls;

import dao.MemberDao;
import vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class LogInController implements Controller {
    MemberDao memberDao;

    public LogInController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if (model.get("email") != null) {
            Member member = memberDao.exist((String) model.get("email"), (String) model.get("password"));
            if (member != null) {
                HttpSession session = (HttpSession)model.get("session");
                session.setAttribute("member", member);
                return "redirect:../member/list.do";
            } else {
                return "/WEB-INF/views/auth/LogInFail.jsp";
            }
        }
         return "/WEB-INF/views/auth/LogInForm.jsp";
    }
}