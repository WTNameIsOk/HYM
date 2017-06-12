package com.zhidisoft.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhidisoft.dao.impl.UserDaoImpl;
import com.zhidisoft.entity.User;
import com.zhidisoft.util.EncryptUtil;

@SuppressWarnings("serial")
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 验证码验证
		String iptCode = req.getParameter("code");
		String Code = (String) req.getSession().getAttribute("KAPTCHA_SESSION_KEY");

		PrintWriter writer = resp.getWriter();
		if (Code.equalsIgnoreCase(iptCode)) {
			writer.print(true);
		}
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = EncryptUtil.encryptMD5(req.getParameter("password"));
		Boolean rem = Boolean.parseBoolean(req.getParameter("rem"));

		// 设置cookie
		if (rem) {
			Cookie cookie = new Cookie("username", username);
			resp.addCookie(cookie);
		}
		
		//验证用户
		HttpSession session = req.getSession();
		UserDaoImpl dao = new UserDaoImpl();
		User user = dao.login(username, password);
		PrintWriter writer = resp.getWriter();
		
		if (user != null) {
			session.setAttribute("user", user);
		} else {
			writer.println("登陆失败");
		}
		
		writer.flush();
		writer.close();
	}

}
