package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by pc on 17-5-10.
 */
@WebServlet(name = "RegServlet1", urlPatterns = "/RegServlet1")
public class RegServlet1 extends HttpServlet {
    //数据库连接Connection
    private java.sql.Connection conn;

    //初始化方法

    public void init() throws ServletException {
        super.init();
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //连接数据库的url
            String url = "jdbc:mysql://localhost:3306/jdbcdemo?useUnicode=true&characterEncoding=UTF-8";
            //获取数据库连接
            conn =  DriverManager.getConnection(url, "root", "root");
            if (conn != null) {
                System.out.println("数据库链接成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * init()方法是Servlet的初始化方法，此方法只运行一次，实例中在此方法中加载数据库驱动，并获取数据库
     * 连接对象Connection，在获取数据库连接对象之后，通过doPost()方法处理用户注册请求，
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置request response编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取表单中的属性值
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String question = req.getParameter("question");
        String answer = req.getParameter("answer");
        String email = req.getParameter("email");
        //判断数据库是否连接成功
        if (conn != null) {
            try {
                //插入注册信息的sql语句
                String sql = "insert into tb_user(username,password,sex,question,answer,email)" + "values(?,?,?,?,?,?)";
                //创建PreparedStatement对象
                PreparedStatement ps = conn.prepareStatement(sql);
                //对sql语句中的参数动态赋值
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, sex);
                ps.setString(4, question);
                ps.setString(5, answer);
                ps.setString(6, email);
                //执行更新操作
                ps.executeUpdate();
                //获取PrintWriter对象
                PrintWriter out = resp.getWriter();
                //输出注册信息结果
                out.print("<h1 align=center");
                out.print(username + "注册成功！");
                out.print("</h1>");
                out.flush();
                out.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //发送数据库连接错误信息
            resp.sendError(500, "数据库连接错误！");
        }
    }
}
