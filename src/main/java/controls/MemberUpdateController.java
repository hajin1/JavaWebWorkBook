package controls;

import bind.DataBinding;
import dao.MemberDao;
import vo.Member;

import java.util.Map;

public class MemberUpdateController implements Controller, DataBinding {
    MemberDao memberDao;

    public MemberUpdateController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("member");
        if (member.getEmail() == null) { // 업데이트할 멤버 정보 조회
            int no = (Integer) model.get("no");
            member = memberDao.selectOne(no);
            model.put("member", member);
            return "/WEB-INF/views/MemberUpdateForm.jsp";
        } else { // 멤버 정보 업데이트
            memberDao.update(member);
            return "redirect:../member/list.do";
        }
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class,
                "member", vo.Member.class
        };
    }
}
