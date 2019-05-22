package controls;

import dao.MemberDao;
import vo.Member;

import java.util.Map;

public class MemberUpdateController implements Controller {

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        MemberDao memberDao = (MemberDao) model.get("memberDao");
        if (model.get("member") == null) {
            int no = (Integer) model.get("no");
            Member member = memberDao.selectOne(no);
            model.put("member", member);
            return "/WEB-INF/views/MemberUpdateForm.jsp";
        } else {
            Member member = (Member) model.get("member");
            memberDao.update(member);
            return "redirect:../member/list.do";
        }
    }
}
