package controls;

import dao.MemberDao;

import java.util.Map;

public class MemberListController implements Controller{
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        MemberDao memberDao = (MemberDao) model.get("memberDao");
        model.put("members", memberDao.selectList());
        return "/WEB-INF/views/MemberList.jsp";
    }
}
