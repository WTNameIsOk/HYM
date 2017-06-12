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
		String oldPassword = EncryptUtil.encryptMD5(req.getParameter("oldPassword"));
		String newPassword = EncryptUtil.encryptMD5(req.getParameter("newPassword"));
		
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		PrintWriter writer = resp.getWriter();
		if (user.getPassword().equals(oldPassword)) {
			user.setPassword(newPassword);
			UserDaoImpl dao = new UserDaoImpl();
			dao.update(user);
		} else {
			writer.println(false);
		}
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
