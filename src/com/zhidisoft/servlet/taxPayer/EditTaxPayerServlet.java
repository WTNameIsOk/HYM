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
 * 更新数据servlet
 * @author 贺天辰
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/taxPayer/edit.do")
public class EditTaxPayerServlet extends HttpServlet{

	/**
	 * 根据id查询数据，并返回
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取id参数
		Integer id = Integer.parseInt(req.getParameter("id"));
		//根据id获取查询数据
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		TaxPayer payer = dao.getById(id);
		
		//把数据设置参数
		req.setAttribute("payer", payer);
	
		//转发
		req.getRequestDispatcher("/manage/taxPayer/editTaxpayer.jsp").forward(req, resp);
	}

	/**
	 * 更新数据库
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数集合，封装为实体类
		Map<String, String[]> params = req.getParameterMap();
		
		//把实体类传入执行数据库操作，并返回执行结果
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		//判断结果，是否返回数据
		if (!dao.update(params)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

}
