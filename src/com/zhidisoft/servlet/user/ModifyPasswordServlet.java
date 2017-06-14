package com.zhidisoft.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhidisoft.dao.impl.UserDaoImpl;
import com.zhidisoft.entity.User;
import com.zhidisoft.util.EncryptUtil;

@SuppressWarnings("serial")
@WebServlet("/modifyPassword")
public class ModifyPasswordServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//MD5加密密码
		String oldPassword = EncryptUtil.encryptMD5(req.getParameter("oldPassword"));
		String newPassword = EncryptUtil.encryptMD5(req.getParameter("newPassword"));
		
		//获取session的user
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		PrintWriter writer = resp.getWriter();
		//验证原密码
		if (user.getPassword().equals(oldPassword)) {
			//执行密码修改
			user.setPassword(newPassword);
			UserDaoImpl dao = new UserDaoImpl();
			dao.update(user);
		} else {
			//密码错误，返回数据
			writer.println("密码错误");
		}
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
