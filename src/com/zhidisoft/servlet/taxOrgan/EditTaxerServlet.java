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
import com.zhidisoft.dao.impl.TaxerDaoImpl;
import com.zhidisoft.entity.TaxOrgan;
import com.zhidisoft.entity.Taxer;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@WebServlet("/getOrganServlet.do")
public class EditTaxerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ��λ�����ݼ���
		TaxOrganDaoImpl taxOrganDao = new TaxOrganDaoImpl();
		List<TaxOrgan> organ = taxOrganDao.getAll();
		
		JSONObject jsonObject = JSONObject.fromObject(organ);
		PrintWriter writer = resp.getWriter();
		writer.print(jsonObject);
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
