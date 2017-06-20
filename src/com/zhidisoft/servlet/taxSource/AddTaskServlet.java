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
 * taxPayer的新增操作服务器
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/task/add.do")
public class AddTaskServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取id参数
		String payerCode = req.getParameter("payerCode");
		//根据id获取查询数据
		TaxPayerDaoImpl dao = new TaxPayerDaoImpl();
		Map<String, String> payer = dao.getPayerByCode(payerCode);
		
		//判断请求参数，做不同操作
		if ("ajax".equals(req.getParameter("method"))) {
			JSONObject jsonObject = JSONObject.fromObject(payer);
			PrintWriter writer = resp.getWriter();
			writer.print(jsonObject);
			writer.flush();
			writer.close();
		}else {
			//把数据设置参数
			req.setAttribute("payer", payer);
			//转发
			req.getRequestDispatcher("/manage/tesk/addTask.jsp?").forward(req, resp);
		}
	}

	/**
	 * 新增操作
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数集合
		Map<String, String[]> params = req.getParameterMap();
		
		//把参数集合传入执行数据库操作，并返回执行结果
		TaxSourceDaoImpl dao = new TaxSourceDaoImpl();
		
		//判断结果，是否返回数据
		if (!dao.add(params)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

}
