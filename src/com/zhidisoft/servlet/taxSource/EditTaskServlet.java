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
 * 更新数据servlet
 * @author 贺天辰
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/task/edit.do")
public class EditTaskServlet extends HttpServlet{

	/**
	 * 根据id查询数据，并返回
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取id参数
		String id = req.getParameter("id");
		Map<String, String> map = new HashMap<String, String>();
		map.put("tts.id", id);
		//根据id获取查询数据
		TaxSourceDaoImpl dao = new TaxSourceDaoImpl();
		String tables = "tb_tax_source tts left join tb_tax_payer ttp on payerId=ttp.id left JOIN tb_tax_organ tto ON ttp.taxOrganId=tto.id LEFT JOIN tb_industry ti ON ttp.industryId=ti.id LEFT JOIN tb_user tu ON tu.id=ttp.userId";
		Map<String, String> payer = dao.getResultList(tables, "1", "1", map, null).get(0);
		
		//把数据设置参数
		req.setAttribute("payer", payer);

		//转发
		req.getRequestDispatcher("/manage/tesk/editTask.jsp").forward(req, resp);
	}

	/**
	 * 更新数据库
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数集合，封装为实体类
		Map<String, String[]> params = req.getParameterMap();
		
		//把实体类传入执行数据库操作，并返回执行结果
		TaxSourceDaoImpl dao = new TaxSourceDaoImpl();
		//判断结果，是否返回数据
		if (!dao.update(params)) {
			PrintWriter writer = resp.getWriter();
			writer.print(false);
			writer.flush();
			writer.close();
		}
	}

}
