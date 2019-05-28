package controls;

import bind.DataBinding;
import dao.MemberDao;
import vo.Member;

import java.util.Map;

public class MemberAddController implements Controller, DataBinding {
    MemberDao memberDao;

    public MemberAddController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("member");
        if (member.getEmail() == null) { // 입력폼을 요청할 때
            return "/WEB-INF/views/MemberForm.jsp";
        } else { // 회원 등록을 요청할 때
            memberDao.insert(member);
            return "redirect:list.do";
        }
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
          "member", vo.Member.class
        };
    }
}
