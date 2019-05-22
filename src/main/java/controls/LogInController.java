package controls;

import dao.MemberDao;
import vo.Member;

import java.util.Map;

public class LogInController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if (model.get("email") != null) {
            MemberDao memberDao = (MemberDao) model.get("memberDao");
            Member member = memberDao.exist((String) model.get("email"), (String) model.get("password"));
            if (member != null) {
                model.put("member", member);
                return "redirect:../member/list.do";
            } else {
                return "/WEB-INF/views/auth/LogInFail.jsp";
            }
        }
         return "/WEB-INF/views/auth/LogInForm.jsp";
    }
}