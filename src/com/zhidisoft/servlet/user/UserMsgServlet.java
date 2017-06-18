package com.zhidisoft.servlet.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhidisoft.dao.impl.UserDaoImpl;
import com.zhidisoft.entity.User;

/**
 * ��ȡ�û���Ϣservlet
 * @author ���쳽
 *
 */
@SuppressWarnings("serial")
@WebServlet("/manage/userMsg")
public class UserMsgServlet extends HttpServlet{

	/**
	 * ����id��ѯ���ݣ�������
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		//����id��ȡ��ѯ����
		UserDaoImpl dao = new UserDaoImpl();
		Map<String, String> map = dao.getMsgById(user.getId());
		//���������ò���
		req.setAttribute("msg", map);
		//ת��
		req.getRequestDispatcher("/manage/userMsg.jsp").forward(req, resp);
	}

	/**
	 * �������ݿ�
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
