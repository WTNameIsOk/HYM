package com.zhidisoft.servlet.taxPayer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.dao.impl.TaxPayerDaoImpl;

/**
 * taxPayer的删除操作服务器
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/taxPayer/delete.do")
public class DeleteTaxPayerServlet extends HttpServlet {

	/**
	 * 删除操作
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取id参数
		Integer id = Integer.parseInt(req.getParameter("id"));

		// 把id传入执行数据库操作，并返回执行结果
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		// 判断结果，是否返回数据
		if (!dao.delete(id)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

	/**
	 * 判断数据是否占用
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取id参数
		Integer id = Integer.parseInt(req.getParameter("id"));

		// 把id传入执行数据库操作类，并返回占用数据数量
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		Integer count = dao.isBusy(id);
		PrintWriter writer = resp.getWriter();
		writer.print(count);
		writer.flush();
		writer.close();
	}

}
