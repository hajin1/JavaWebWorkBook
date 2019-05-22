package controls;

import dao.MemberDao;
import vo.Member;

import java.util.Map;

public class MemberAddController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        if (model.get("member") == null) { // 입력폼을 요청할 때
            return "/WEB-INF/views/MemberForm.jsp";
        } else { // 회원 등록을 요청할 때
            MemberDao memberDao = (MemberDao) model.get("memberDao");
            Member member = (Member) model.get("member");
            memberDao.insert(member);
            return "redirect:list.do";
        }
    }
}
