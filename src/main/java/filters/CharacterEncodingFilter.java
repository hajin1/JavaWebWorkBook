package filters;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    FilterConfig config;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config=config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter) throws IOException, ServletException {
//       서블릿이 실행되기 전에 해야 할 작업
        request.setCharacterEncoding(config.getInitParameter("encoding"));

        //다음 필터를 호출. 더이상 필터가 없다면 서블릿의 service 메소드가 호출됨
        nextFilter.doFilter(request, response);

        //서블릿을 실행한 후 클라이언트에게 응답하기 전에 해야할 작업
    }

    @Override
    public void destroy() {}
}
