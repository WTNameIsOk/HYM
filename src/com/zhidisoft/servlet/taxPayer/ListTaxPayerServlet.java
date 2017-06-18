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
		//��ȡ��ҳ��ѯ����
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		String payerCode = req.getParameter("payerCode");
		String payerName = req.getParameter("payerName");
		//���徫ȷ��ѯ����
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("payerCode", payerCode);
		//����ģ����ѯ����
		HashMap<String, String> fuzzySearch = new HashMap<String, String>();
		fuzzySearch.put("payerName", payerName);
		
		//��ȡ������ݿ��в�ѯ����
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		String tables = "tb_tax_payer ttp LEFT JOIN tb_tax_organ tto ON ttp.taxOrganId=tto.id LEFT JOIN tb_industry ti ON ttp.industryId=ti.id";
		List<Map<String, String>> listMap = dao.getResultList(tables, page, rows, map, fuzzySearch);
		int count = dao.getCount("tax_payer");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", count);
		data.put("rows", listMap);
		
		//����������������Ϊ�Ự����������payerCode������
		req.getSession().setAttribute("taxPayerSize", count);
		
		//�Ѳ�ѯ���ݷ�װΪjson���ݣ������ݴ���ǰ��
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
