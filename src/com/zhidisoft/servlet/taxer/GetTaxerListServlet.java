package com.zhidisoft.servlet.taxer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.impl.TaxerDaoImpl;
import com.zhidisoft.entity.Taxer;

import net.sf.json.JSONArray;

@SuppressWarnings("serial")
@WebServlet("/getTaxerServlet.do")
public class GetTaxerListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取办税专员的数据集合
		TaxerDaoImpl dao = new TaxerDaoImpl();
		List<Taxer> taxer = dao.getAll();
		
		JSONArray array = JSONArray.fromObject(taxer);
		PrintWriter writer = resp.getWriter();
		writer.print(array);
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
