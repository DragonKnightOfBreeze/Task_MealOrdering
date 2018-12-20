/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.account;


import mealordering.utils.PaymentUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * 在线支付的Servlet
 * TODO 正确的对接
 */
@WebServlet(name = "OnlinePayServlet", urlPatterns = {"/mealordering/onlinePlay"})
public class OnlinePayServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到在线支付所需的参数
		String pd_FrpId = request.getParameter("bank"); //银行编码
		String p0_Cmd = "Buy"; //业务类型
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId"); //商户编号
		String p2_Order = request.getParameter("orderId"); //商户订单号
		String p3_Amt = request.getParameter("money"); //支付金额
		String p4_Cur = "CNY"; //交易币种
		String p5_PId = ""; //商品名称
		String p6_PCat = ""; //商品种类
		String p7_PDesc = ""; //商品描述
		String p8_Url = ResourceBundle.getBundle("props/merchantInfo").getString("responseURL"); //商户接收支付成功数据的地址
		String p9_SAF = ""; //送货地址
		String pa_MP = ""; //商户拓展信息
		String pr_NeedResponse = "1"; //应答机制

		//得到商户密钥
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
		//得到hmac（哈希信息验证码）
		String hmac = PaymentUtils.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_PId, p6_PCat, p7_PDesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

		//2.将数据提交到指定的路径
		//response.sendRedirect("https://www.yeepay
		//.com/app-merchant-proxy/node?p0_Cmd="+p0_Cmd+"&p1_MerId="+p1_MerId);

		//设置相关特性
		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_PId", p5_PId);
		request.setAttribute("p6_PCat", p6_PCat);
		request.setAttribute("p7_PDesc", p7_PDesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("hmac", hmac);
		//跳转到支付确认页面
		request.getRequestDispatcher("/client/confirm.jsp").forward(request, response);
	}
}
