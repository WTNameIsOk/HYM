package com.zhidisoft.servlet.taxer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.impl.TaxerDaoImpl;

/**
 * taxer��ɾ������������
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/taxer/delete.do")
public class DeleteTaxerServlet extends HttpServlet {

	/**
	 * ɾ������
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��ȡid����
		Integer id = Integer.parseInt(req.getParameter("id"));

		// ��id����ִ�����ݿ������������ִ�н��
		TaxerDaoImpl dao = new TaxerDaoImpl();
		// �жϽ�����Ƿ񷵻�����
		if (!dao.delete(id)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

	/**
	 * �ж������Ƿ�ռ��
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��ȡid����
		Integer id = Integer.parseInt(req.getParameter("id"));

		// ��id����ִ�����ݿ�����࣬������ռ����������
		TaxerDaoImpl dao = new TaxerDaoImpl();
		Integer count = dao.isBusy(id);
		PrintWriter writer = resp.getWriter();
		writer.print(count);
		writer.flush();
		writer.close();
	}

}