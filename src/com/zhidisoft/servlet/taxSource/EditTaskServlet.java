package com.zhidisoft.servlet.taxSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.impl.TaxPayerDaoImpl;
import com.zhidisoft.dao.impl.TaxSourceDaoImpl;
import com.zhidisoft.entity.TaxSource;

/**
 * ��������servlet
 * @author ���쳽
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/task/edit.do")
public class EditTaskServlet extends HttpServlet{

	/**
	 * ����id��ѯ�Ӽ�¼����������������
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡid����
		Integer id = Integer.parseInt(req.getParameter("id"));
		//����id��ȡ��ѯ����
		TaxSourceDaoImpl dao = new TaxSourceDaoImpl();
		TaxSource payer = dao.getById(id);
		
		//���������ò���
		req.setAttribute("payer", payer);
		
		PrintWriter writer = resp.getWriter();
		writer.print(payer);
		writer.flush();
		writer.close();
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
