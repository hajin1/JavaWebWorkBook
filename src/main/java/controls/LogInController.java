package controls;

import bind.DataBinding;
import dao.MemberDao;
import vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class LogInController implements Controller, DataBinding {
    MemberDao memberDao;

    public LogInController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member loginInfo = (Member) model.get("loginInfo");
        if (loginInfo.getEmail() == null) { // 입력폼을 요청할 때
            return "/WEB-INF/views/auth/LogInForm.jsp";

        } else { // 회원 등록을 요청할 때
            Member member = memberDao.exist(loginInfo.getEmail(), loginInfo.getPassword());

            if (member != null) {
                HttpSession session = (HttpSession) model.get("session");
                session.setAttribute("member", member);
                return "redirect:../member/list.do";
            } else {
                return "/WEB-INF/views/auth/LogInFail.jsp";
            }
        }
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "loginInfo", vo.Member.class
        };
    }
}