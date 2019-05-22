package controls;

import dao.MemberDao;

import java.util.Map;

public class MemberDeleteController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        MemberDao memberDao = (MemberDao) model.get("memberDao");
        int no = (Integer) model.get("no");
        memberDao.delete(no);

        return "redirect:list.do";
    }
}
