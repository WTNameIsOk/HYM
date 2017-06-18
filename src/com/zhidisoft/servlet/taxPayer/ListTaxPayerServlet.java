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
		String payerName = req.getParameter("payerName");
		//定义精确查询参数
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("payerCode", payerCode);
		//定义模糊查询参数
		HashMap<String, String> fuzzySearch = new HashMap<String, String>();
		fuzzySearch.put("payerName", payerName);
		
		//获取后端数据库中查询数据
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		String tables = "tb_tax_payer ttp LEFT JOIN tb_tax_organ tto ON ttp.taxOrganId=tto.id LEFT JOIN tb_industry ti ON ttp.industryId=ti.id";
		List<Map<String, String>> listMap = dao.getResultList(tables, page, rows, map, fuzzySearch);
		int count = dao.getCount("tax_payer");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", count);
		data.put("rows", listMap);
		
		//把数据总条数设置为会话参数，方便payerCode的生成
		req.getSession().setAttribute("taxPayerSize", count);
		
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
