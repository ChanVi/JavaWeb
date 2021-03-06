package com.chenv.controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chenv.dao.AccountsMapper;
import com.chenv.pojo.Accounts;
import com.chenv.service.CcbService;



@Controller
@RequestMapping("/ccb")
public class CcbController {
  @Resource
  private CcbService ccbService;
  
  @RequestMapping("/login")
  public String toLogin(HttpServletRequest request,Model model){
	  
	  //从request获取session
	  HttpSession session = request.getSession();
	  
	  //利用业务层进行登录验证，如果登录成功则返回用户信息
	  Accounts loginAccount =  this.ccbService.login(request);
	  
	  //将用户信息放进session中
    if (loginAccount != null) {
    	session.setAttribute("loginAccount", loginAccount);
    	 return "main";
	} else {
		return "error";
	}
   
  }
  
  
  @RequestMapping("/todo")
  public String toDo(HttpServletRequest request,Model model){
	  String aService = request.getParameter("service");
	  if(aService!=null) return aService;
	  if(request.getParameter("cancel")!=null) return "main";
	  if(request.getParameter("sure")!=null) return "success";
	  return "main";
  }
  
  @RequestMapping("/balance")
  public String balance(HttpServletRequest request,Model model){
	  //直接从session中获取用户
	  return "balance";
  }
  
  @RequestMapping("/fetch")
  public String fetch(HttpServletRequest request,Model model){
	  if(request.getParameter("cancel")!=null) return "main";
	  if(request.getParameter("sure")!=null) {
		  boolean isFetch = this.ccbService.fetch(Double.parseDouble(request.getParameter("money")));
		  if (isFetch) {
			  model.addAttribute("message", "请取钞！！！");
			  return "success";
		}else {
			model.addAttribute("errorMsg","余额不足！！！");
			return "main";
		}
	  }
	  return "main";
  }
  
  
  @RequestMapping("/deposit")
  public String deposit(HttpServletRequest request,Model model){
	  if(request.getParameter("cancel")!=null) return "main";
	  if(request.getParameter("sure")!=null) {
		  this.ccbService.deposit(Double.parseDouble(request.getParameter("money")));
		  model.addAttribute("message", "存款成功！！！");
		  return "success";
	  }
	  return "main";
  }
  
  
  @RequestMapping("/changePwd")
  public String changePwd(HttpServletRequest request,Model model){
	  if(request.getParameter("cancel")!=null) return "main";
	  if(request.getParameter("sure")!=null) {
		  
		  String pwd = request.getParameter("pwd");
		  String newPwd1 = request.getParameter("newPwd1");
		  String newPwd2 = request.getParameter("newPwd2");
		  if (newPwd1.equals(newPwd2)) {
			  if (this.ccbService.changePwd(pwd,newPwd1)) {
				  model.addAttribute("message", "修改密码成功！！！");
				  return "success";
			} else {
				model.addAttribute("errorMeg", "原密码错误，请重新输入");
				model.addAttribute("redirect", "./changePwd");
				return "error";
			}
		} else {
			model.addAttribute("errorMeg", "新密码输入不一致，请重新输入");
			model.addAttribute("redirect", "./changePwd");
			return "error";
		}
		  
		  
	  }
	  return "main";
  }
  
  
  @RequestMapping("/exit")
  public String exit(HttpServletRequest request,Model model) throws ServletException{
	  if (this.ccbService.exit()) {
		  model.addAttribute("message", "安全退出！！！");
		  return "exitsuccess";
	}
	  model.addAttribute("errorMeg", "退出失败！！！");
	  return "error";
  }
}