package com.zhidisoft.servlet.taxPayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.impl.TaxPayerDaoImpl;
import com.zhidisoft.entity.TaxPayer;

/**
 * ��������servlet
 * @author ���쳽
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/taxPayer/edit.do")
public class EditTaxPayerServlet extends HttpServlet{

	/**
	 * ����id��ѯ���ݣ�������
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡid����
		Integer id = Integer.parseInt(req.getParameter("id"));
		//����id��ȡ��ѯ����
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		TaxPayer payer = dao.getById(id);
		
		//���������ò���
		req.setAttribute("payer", payer);
	
		//ת��
		req.getRequestDispatcher("/manage/taxPayer/editTaxpayer.jsp").forward(req, resp);
	}

	/**
	 * �������ݿ�
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ�������ϣ���װΪʵ����
		Map<String, String[]> params = req.getParameterMap();
		
		//��ʵ���ഫ��ִ�����ݿ������������ִ�н��
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		//�жϽ�����Ƿ񷵻�����
		if (!dao.update(params)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

}
