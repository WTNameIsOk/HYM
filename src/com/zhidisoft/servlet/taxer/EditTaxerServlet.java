package com.zhidisoft.servlet.taxer;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.dao.impl.TaxerDaoImpl;
import com.zhidisoft.entity.Taxer;

/**
 * ��������servlet
 * @author ���쳽
 *
 */
@SuppressWarnings("serial")
@WebServlet("/editTaxer.do")
public class EditTaxerServlet extends HttpServlet{

	/**
	 * ����id��ѯ���ݣ�������
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡid����
		Integer id = Integer.parseInt(req.getParameter("id"));
		//����id��ȡ��ѯ����
		TaxerDaoImpl dao = new TaxerDaoImpl();
		Taxer taxer = dao.getById(id);
		//���������ò���
		req.setAttribute("taxer", taxer);
		//ת��
		req.getRequestDispatcher("/manage/editTaxer.jsp").forward(req, resp);
	}

	/**
	 * �������ݿ�
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ�������ϣ���װΪʵ����
		Map<String, String[]> params = req.getParameterMap();
		Taxer taxer = new Taxer();
		try {
			BeanUtils.populate(taxer, params);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//��ʵ���ഫ��ִ�����ݿ������������ִ�н��
		TaxerDaoImpl dao = new TaxerDaoImpl();
		//�жϽ�����Ƿ񷵻�����
		if (!dao.update(taxer)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

}
