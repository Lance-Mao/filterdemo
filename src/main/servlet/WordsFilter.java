package servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by pc on 17-5-11.
 */
public class WordsFilter implements Filter {
    //非法字符数组
    private String words[];
    //字符编码
    private String encoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        //获取初始化字符编码
        encoding = filterConfig.getInitParameter("encoding");
        words = new String[]{"fg", "ss"};
    }

    //循环非法字符，对非法字符进行过滤
    public String filter(String param) {
        if (words != null && words.length > 0) {
            for (int i = 0; i < words.length; i++) {
                if (param.indexOf(words[i])!=-1) {
                    param = param.replaceAll(words[i], "***");
                }
            }
        }
        return param;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setContentType("text/html;charset=" + encoding);

        String t = servletRequest.getParameter("title");
        String c = servletRequest.getParameter("content");
        servletRequest.setAttribute("title", filter(t));
        servletRequest.setAttribute("content", filter(c));

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        this.encoding = null;
        this.words = null;
    }
}
