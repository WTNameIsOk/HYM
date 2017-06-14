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
		//获取id参数
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		TaxerDaoImpl taxerDao = new TaxerDaoImpl();
		//根据id获取查询数据
		Taxer taxer = taxerDao.getById(id);
		//获取上级的数据集合
		List<Taxer> taxers = taxerDao.getAll();
		//获取单位的数据集合
		TaxOrganDaoImpl taxOrganDao = new TaxOrganDaoImpl();
		List<TaxOrgan> organ = taxOrganDao.getAll();
		//把数据设置参数
		req.setAttribute("taxer", taxer);
		req.setAttribute("taxers", taxers);
		req.setAttribute("organs", organ);
		
		req.getRequestDispatcher("/manage/editTaxer.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
