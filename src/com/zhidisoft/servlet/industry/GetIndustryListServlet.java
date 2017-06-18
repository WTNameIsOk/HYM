package com.zhidisoft.servlet.industry;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.impl.IndustryDaoImpl;
import com.zhidisoft.entity.Industry;

import net.sf.json.JSONArray;

@SuppressWarnings("serial")
@WebServlet("/getIndustry.do")
public class GetIndustryListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取单位的数据集合
		IndustryDaoImpl taxOrganDao = new IndustryDaoImpl();
		List<Industry> industry = taxOrganDao.getAll();
		
		JSONArray array = JSONArray.fromObject(industry);
		PrintWriter writer = resp.getWriter();
		writer.print(array);
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
