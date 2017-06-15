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
 * taxer的新增操作服务器
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
	 * 新增操作
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
		if (!dao.add(taxer)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

}
