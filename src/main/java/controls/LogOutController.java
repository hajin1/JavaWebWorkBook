package controls;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class LogOutController implements Controller {
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        HttpSession session = (HttpSession) model.get("session");
        session.invalidate();   //세션 무효화
        return "redirect:login.do";
    }
}
