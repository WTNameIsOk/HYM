package com.zhidisoft.servlet.taxSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.impl.TaxSourceDaoImpl;

/**
 * ��������servlet
 * @author ���쳽
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/task/edit.do")
public class EditTaskServlet extends HttpServlet{

	/**
	 * ����id��ѯ���ݣ�������
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡid����
		String id = req.getParameter("id");
		Map<String, String> map = new HashMap<String, String>();
		map.put("tts.id", id);
		//����id��ȡ��ѯ����
		TaxSourceDaoImpl dao = new TaxSourceDaoImpl();
		String tables = "tb_tax_source tts left join tb_tax_payer ttp on payerId=ttp.id left JOIN tb_tax_organ tto ON ttp.taxOrganId=tto.id LEFT JOIN tb_industry ti ON ttp.industryId=ti.id LEFT JOIN tb_user tu ON tu.id=ttp.userId";
		Map<String, String> payer = dao.getResultList(tables, "1", "1", map, null).get(0);
		
		//���������ò���
		req.setAttribute("payer", payer);

		//ת��
		req.getRequestDispatcher("/manage/tesk/editTask.jsp").forward(req, resp);
	}

	/**
	 * �������ݿ�
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ�������ϣ���װΪʵ����
		Map<String, String[]> params = req.getParameterMap();
		
		//��ʵ���ഫ��ִ�����ݿ������������ִ�н��
		TaxSourceDaoImpl dao = new TaxSourceDaoImpl();
		//�жϽ�����Ƿ񷵻�����
		if (!dao.update(params)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

}
