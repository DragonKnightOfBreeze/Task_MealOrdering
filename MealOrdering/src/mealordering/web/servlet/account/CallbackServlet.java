/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.account;

import dk_breeze.utils.ext.StringExt;
import mealordering.service.OrderService;
import mealordering.utils.PaymentUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * 数据回调的Servlet，用于支付
 * TODO 正确的对接
 * TODO 重定向连接
 */
@WebServlet(name = "CallbackServlet", urlPatterns = {"/mealordering/callback"})
public class CallbackServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得所有的回调数据
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_TrxTime = request.getParameter("ru_TrxTime");

		//身份校验，判断是不是支付公司通知你
		//哈希信息验证码
		String hmac = request.getParameter("hmac");
		//商户密钥
		String keyValue = ResourceBundle.getBundle("props/merchantInfo").getString("keyValue");

		//自己对上面数据进行加密，比较支付公司发过来hmac
		boolean isValid = PaymentUtils.verifyHmac(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		if (isValid) {
			if (StringExt.equals(r9_BType, "1") || StringExt.equals(r9_BType, "2")) { //判断正确支付.
				//响应数据有效，完成修改订单状态操作
				OrderService service = new OrderService();
				try {
					//根据订单号修改订单状态
					service.updatePayState(r6_Order);
					response.getWriter().println("信息：修改订单状态成功！");
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().println("警告：修改订单状态失败！");
				}
			}
		} else {
			response.getWriter().println("警告：数据被篡改！");
		}
	}
}
