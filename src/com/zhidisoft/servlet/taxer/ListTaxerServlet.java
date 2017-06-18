package com.zhidisoft.servlet.taxer;

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

import com.zhidisoft.dao.impl.TaxerDaoImpl;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@WebServlet("/manage/taxer/list")
public class ListTaxerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ��ҳ��ѯ����
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		String taxerName = req.getParameter("name");
		
		//��ȡ������ݿ��в�ѯ����
		TaxerDaoImpl dao = new TaxerDaoImpl();
		List<Map<String, String>> listMap = dao.getListByMap(page, rows, taxerName);
		int count = dao.getCount();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", count);
		data.put("rows", listMap);
		
		//����������������Ϊ�Ự����������taxerCode������
		req.getSession().setAttribute("taxerSize", count);
		
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
