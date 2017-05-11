package servlet;

import org.junit.Test;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * Created by pc on 17-5-10.
 */
@WebServlet("/WordFilter")
public class WordFilter implements Filter {
    //非法字符数组
    private String words[];
    //字符编码
    private String encoding;

    @Test
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取字符编码
        encoding = filterConfig.getInitParameter("encoding");  //filterConfig接口用于获取过滤器初始化期间的参数信息
        words = new String[]{"糟糕", "混蛋"};
    }

    public String filter(String param) {

       /*
       //判断非法字符是否被初始化
        if (words != null && words.length > 0) {
            //循环替换非法字符
            for (int i = 0; i < words.length; i++) {
                //判断是否包含非法字符
                if (param.indexOf(words[i]) == -1) {
                    //将非法字符替换为“****”
                    param = param.replaceAll(words[i], "****");
                }
            }
        }
*/
        try {
            if (words != null && words.length > 0) {// 判断非法文字字符是否被初始化
                for (int i = 0; i < words.length; i++) {// 循环替换非法字符
                    if (param.indexOf(words[i]) != -1) {// 判断是否包含非法字符
                        param = param.replaceAll(words[i], "* * *");// 替换非法文字
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }

    /**
     * 创建内部类Request，重写getParameter()和getParameterValues()方法
     * 并重写这两个方法中实现过滤
     */
 /*   class Request extends HttpServletRequestWrapper {
        //构造方法
        public Request(HttpServletRequest request) {
            super(request);
        }
        //重写getParameter()方法

        @Override
        public String getParameter(String name) {
            return filter(super.getRequest().getParameter(name));
        }

        @Override
        public String[] getParameterValues(String name) {
            //获取所有的参数值
            String values[] = super.getRequest().getParameterValues(name);
            //通过循环对所有的参数值进行过滤
            for (int i = 0; i < values.length; i++) {
                values[i] = filter(values[i]);
            }

            //返回过滤后的参数值
            return values;
        }
    }*/


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //判断字符编码是否有效
        if (encoding != null) {
            //设置servletRequest字符编码
            servletRequest.setCharacterEncoding(encoding);
            //将servletRequest转化为重写后的Request对象
            /*servletRequest = new Request((HttpServletRequest) servletRequest);*/
            //设置response字符编码
            servletResponse.setContentType("text/html;charset=" + encoding);

        }

        String t = servletRequest.getParameter("title");//获取jsp提交的参数“title”的值
        String c = servletRequest.getParameter("content");//获取jsp提交的参数“content”的值
        servletRequest.setAttribute("title", filter(t));//用filter()过滤标题，并放回到request中
        servletRequest.setAttribute("content", filter(c));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        this.words = null;
        this.encoding = null;
    }
}
