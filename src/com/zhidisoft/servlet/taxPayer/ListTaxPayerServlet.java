package com.zhidisoft.servlet.taxPayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.impl.TaxPayerDaoImpl;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@WebServlet("/manage/taxPayer/list")
public class ListTaxPayerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取分页查询参数
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		String payerCode = req.getParameter("payerCode");
		String payerName = req.getParameter("name");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("payerName", payerName);
		map.put("payerCode", payerCode);
		
		//获取后端数据库中查询数据
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		String tables = "tb_tax_payer ttp LEFT JOIN tb_tax_organ tto ON ttp.taxOrganId=tto.id LEFT JOIN tb_industry ti ON ttp.industryId=ti.id";
		List<Map<String, String>> listMap = dao.getResultList(tables, page, rows, map);
		int count = dao.getCount("tax_payer");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", count);
		data.put("rows", listMap);
		
		//把查询数据封装为json数据，将数据传到前端
		JSONObject object = JSONObject.fromObject(data);
		PrintWriter writer = resp.getWriter();
		writer.print(object);
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
