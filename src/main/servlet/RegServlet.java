package servlet;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pc on 17-5-10.
 */
public class RegServlet implements Filter {
    //数据库连接Connection
    private Connection conn;

    //初始化方法
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.driver");
            //连接数据库的url
            String url = "jdbc:mysql://localhost:3306/jdbcdemo";
            //获取数据库连接
            conn = DriverManager.getConnection(url, "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * init()方法是Servlet的初始化方法，此方法只运行一次，实例中在此方法中加载数据库驱动，并获取数据库
     * 连接对象Connection，在获取数据库连接对象之后，通过doPost()方法处理用户注册请求，
     */

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    public void destroy() {

    }
//初始化方法

}
