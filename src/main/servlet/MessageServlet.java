package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建处理用户留言反馈的Servlet，对用户留言信息进行处理
 * Created by pc on 17-5-10.
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取标题
        String title = req.getParameter("title");
        //获取内容
        String content = req.getParameter("content");
        //将请求到的信息放到request域
        req.setAttribute("title", title);
        req.setAttribute("content", content);
        //转发到result.jsp页面
        req.getRequestDispatcher("index2.jsp").forward(req,resp);
    }
}
