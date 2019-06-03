package controls;

import annotation.Component;
import bind.DataBinding;
import dao.MemberDao;

import java.util.Map;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
    MemberDao memberDao;

    public MemberDeleteController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        int no = (Integer) model.get("no");
        memberDao.delete(no);

        return "redirect:list.do";
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[] {
                "no", Integer.class,
        };
    }
}
