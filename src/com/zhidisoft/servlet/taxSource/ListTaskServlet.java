package com.zhidisoft.servlet.taxSource;

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

import com.zhidisoft.dao.impl.TaxSourceDaoImpl;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@WebServlet("/manage/task/list")
public class ListTaskServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//���ղ���map����
		Map<String, String[]> params = req.getParameterMap();
		
		//��ȡ������ݿ��в�ѯ����
		TaxSourceDaoImpl dao = new TaxSourceDaoImpl();
		List<Map<String, String>> listMap = dao.getResultList(params);
		int count = dao.getCount("tax_source");
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
