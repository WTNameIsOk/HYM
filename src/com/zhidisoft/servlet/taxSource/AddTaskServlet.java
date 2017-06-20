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

import net.sf.json.JSONObject;

/**
 * taxPayer����������������
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/task/add.do")
public class AddTaskServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡid����
		String payerCode = req.getParameter("payerCode");
		//����id��ȡ��ѯ����
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		Map<String, String> payer = dao.getPayerByCode(payerCode);
		
		//�ж��������������ͬ����
		if ("ajax".equals(req.getParameter("method"))) {
			JSONObject jsonObject = JSONObject.fromObject(payer);
			PrintWriter writer = resp.getWriter();
			writer.print(jsonObject);
			writer.flush();
			writer.close();
		}else {
			//���������ò���
			req.setAttribute("payer", payer);
			//ת��
			req.getRequestDispatcher("/manage/tesk/addTask.jsp?").forward(req, resp);
		}
	}

	/**
	 * ��������
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ��������
		Map<String, String[]> params = req.getParameterMap();
		
		//�Ѳ������ϴ���ִ�����ݿ������������ִ�н��
		TaxSourceDaoImpl dao = new TaxSourceDaoImpl();
		
		//�жϽ�����Ƿ񷵻�����
		if (!dao.add(params)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

}
