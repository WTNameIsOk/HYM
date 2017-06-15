package com.zhidisoft.servlet.taxOrgan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.impl.TaxOrganDaoImpl;
import com.zhidisoft.entity.TaxOrgan;

import net.sf.json.JSONArray;

@SuppressWarnings("serial")
@WebServlet("/getOrgans.do")
public class GetOrganListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取单位的数据集合
		TaxOrganDaoImpl taxOrganDao = new TaxOrganDaoImpl();
		List<TaxOrgan> organ = taxOrganDao.getAll();
		
		JSONArray array = JSONArray.fromObject(organ);
		PrintWriter writer = resp.getWriter();
		writer.print(array);
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
