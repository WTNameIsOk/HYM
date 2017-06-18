package com.zhidisoft.servlet.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhidisoft.dao.impl.UserDaoImpl;
import com.zhidisoft.entity.User;

/**
 * 获取用户信息servlet
 * @author 贺天辰
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/userMsg")
public class UserMsgServlet extends HttpServlet{

	/**
	 * 根据id查询数据，并返回
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		//根据id获取查询数据
		UserDaoImpl dao = new UserDaoImpl();
		Map<String, String> map = dao.getMsgById(user.getId());
		//把数据设置参数
		req.setAttribute("msg", map);
		//转发
		req.getRequestDispatcher("/manage/userMsg.jsp").forward(req, resp);
	}

	/**
	 * 更新数据库
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
