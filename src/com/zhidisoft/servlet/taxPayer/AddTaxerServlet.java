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

/**
 * taxer的新增操作服务器
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/taxPayer/add.do")
public class AddTaxerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/**
	 * 新增操作
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数集合
		Map<String, String[]> params = req.getParameterMap();
		
		//把参数集合传入执行数据库操作，并返回执行结果
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		//判断结果，是否返回数据
		if (!dao.add(params)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

}
