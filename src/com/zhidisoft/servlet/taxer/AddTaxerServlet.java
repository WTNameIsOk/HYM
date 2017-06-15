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
 * taxer����������������
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@WebServlet("/addTaxer.do")
public class AddTaxerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * ��������
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
		if (!dao.add(taxer)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

}
