package com.zhidisoft.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.impl.UserDaoImpl;
import com.zhidisoft.entity.User;

import net.sf.json.JSONArray;

@SuppressWarnings("serial")
@WebServlet("/getUsers.do")
public class GetUserListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取办税专员的数据集合
		UserDaoImpl dao = new UserDaoImpl();
		List<User> Users = dao.getAll();
		
		JSONArray array = JSONArray.fromObject(Users);
		PrintWriter writer = resp.getWriter();
		writer.print(array);
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
