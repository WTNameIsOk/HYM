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
 * 更新数据servlet
 * @author 贺天辰
 *
 */
@SuppressWarnings("serial")
@WebServlet("/editTaxer.do")
public class EditTaxerServlet extends HttpServlet{

	/**
	 * 根据id查询数据，并返回
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取id参数
		Integer id = Integer.parseInt(req.getParameter("id"));
		//根据id获取查询数据
		TaxerDaoImpl dao = new TaxerDaoImpl();
		Taxer taxer = dao.getById(id);
		//把数据设置参数
		req.setAttribute("taxer", taxer);
		//转发
		req.getRequestDispatcher("/manage/editTaxer.jsp").forward(req, resp);
	}

	/**
	 * 更新数据库
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数集合，封装为实体类
		Map<String, String[]> params = req.getParameterMap();
		Taxer taxer = new Taxer();
		try {
			BeanUtils.populate(taxer, params);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//把实体类传入执行数据库操作，并返回执行结果
		TaxerDaoImpl dao = new TaxerDaoImpl();
		//判断结果，是否返回数据
		if (!dao.update(taxer)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

}
