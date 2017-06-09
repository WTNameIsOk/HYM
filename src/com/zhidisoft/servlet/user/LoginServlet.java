package com.zhidisoft.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//验证码验证
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
		String password = req.getParameter("password");
		String rem = req.getParameter("rem");
		
		
		
	}

}
