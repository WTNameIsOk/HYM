package com.zhidisoft.servlet.taxer;

import java.io.IOException;
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

@SuppressWarnings("serial")
@WebServlet("/editTaxer")
public class EditTaxerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡid����
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		TaxerDaoImpl taxerDao = new TaxerDaoImpl();
		//����id��ȡ��ѯ����
		Taxer taxer = taxerDao.getById(id);
		//��ȡ�ϼ������ݼ���
		List<Taxer> taxers = taxerDao.getAll();
		//��ȡ��λ�����ݼ���
		TaxOrganDaoImpl taxOrganDao = new TaxOrganDaoImpl();
		List<TaxOrgan> organ = taxOrganDao.getAll();
		//���������ò���
		req.setAttribute("taxer", taxer);
		req.setAttribute("taxers", taxers);
		req.setAttribute("organs", organ);
		
		req.getRequestDispatcher("/manage/editTaxer.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
