package controls;

import annotation.Component;
import dao.MemberDao;

import java.util.Map;

@Component("/member/list.do")
public class MemberListController implements Controller{
    MemberDao memberDao;

    public MemberListController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        model.put("members", memberDao.selectList());
        return "/WEB-INF/views/MemberList.jsp";
    }
}
