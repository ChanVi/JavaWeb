package com.chenv.javaweb.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chenv.javaweb.dao.factory.CustomerDAOFactory;


/**
 * Servlet implementation class InirServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		CustomerDAOFactory.getInstace().setType("jdbc");
		
		//��ȡ��·���µ�switch�ļ�
		InputStream in = getServletContext().getResourceAsStream("/WEB-INF/calsses/switch.properties");
		
		Properties properties = new Properties();
		try {
			properties.load(in);
			//��ȡtype������
			String type = properties.getProperty("type");
			//��factory type����
			CustomerDAOFactory.getInstace().setType(type);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
