package mealordering.utils;

import dkbreeze.annotation.NotTested;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 支付的工具类
 * TODO 充分理解
 */
@NotTested
public class PaymentUtils {

	private static String encodingCharset = "UTF-8";

	/**
	 * 生成hmac（哈希信息验证码）
	 * @param p0_Cmd 业务类型
	 * @param p1_MerId 商户编号
	 * @param p2_Order 商户订单号
	 * @param p3_Amt 支付金额
	 * @param p4_Cur 交易币种
	 * @param p5_PId 商品名称
	 * @param p6_PCat 商品种类
	 * @param p7_PDesc 商品描述
	 * @param p8_Url 商户接收支付成功数据的地址
	 * @param p9_SAF 送货地址
	 * @param pa_MP 商户扩展信息
	 * @param pd_FrpId 银行编码
	 * @param pr_NeedResponse 应答机制
	 * @param keyValue 商户密钥
	 */
	public static String buildHmac(String p0_Cmd, String p1_MerId, String p2_Order, String p3_Amt, String p4_Cur,
			String p5_PId, String p6_PCat, String p7_PDesc, String p8_Url, String p9_SAF, String pa_MP, String pd_FrpId,
			String pr_NeedResponse, String keyValue) {

		String sValue = p0_Cmd + p1_MerId + p2_Order + p3_Amt + p4_Cur + p5_PId + p6_PCat + p7_PDesc + p8_Url + p9_SAF + pa_MP + pd_FrpId + pr_NeedResponse;
		return PaymentUtils.hmacSign(sValue, keyValue);
	}

	/**
	 * 校验hmac（哈希信息验证码）
	 * @param hmac 支付网关发来的加密验证码
	 * @param p1_MerId 商户编号
	 * @param r0_Cmd 业务类型
	 * @param r1_Code 支付结果
	 * @param r2_TrxId 易宝支付交易流水号
	 * @param r3_Amt 支付金额
	 * @param r4_Cur 交易币种
	 * @param r5_Pid 商品名称
	 * @param r6_Order 商户订单号
	 * @param r7_Uid 易宝支付会员ID
	 * @param r8_MP 商户扩展信息
	 * @param r9_BType 交易结果返回类型
	 * @param keyValue 商户密钥
	 */
	public static boolean verifyHmac(String hmac, String p1_MerId, String r0_Cmd, String r1_Code, String r2_TrxId,
			String r3_Amt, String r4_Cur, String r5_Pid, String r6_Order, String r7_Uid, String r8_MP, String r9_BType,
			String keyValue) {
		String sValue = p1_MerId + r0_Cmd + r1_Code + r2_TrxId + r3_Amt + r4_Cur + r5_Pid + r6_Order + r7_Uid + r8_MP + r9_BType;
		String str = PaymentUtils.hmacSign(sValue, keyValue);
		if(str == null)
			return false;
		return str.equals(hmac);
	}

	/**
	 * 根据指定的键和参数，得到MD5摘要，并转化成16进制，以获得hmac（哈希信息验证码）。
	 * @param key 键
	 * @param args 参数
	 */
	public static String getHmac(@NotNull String key, @NotNull String[] args) {
		if(args.length == 0)
			return null;

		StringBuilder str = new StringBuilder();
		for(String arg : args) {
			str.append(arg);
		}
		return (hmacSign(key, str.toString()));
	}

	/**
	 * 根据指定的键和值，得到MD5摘要，并转化成16进制，以获得hmac（哈希信息验证码）。
	 */
	public static String hmacSign(@NotNull String key, @NotNull String value) {
		byte[] k_ipad = new byte[64];
		byte[] k_opad = new byte[64];
		byte[] key1;
		byte[] value1;
		try {
			value1 = value.getBytes(encodingCharset);
			key1 = key.getBytes(encodingCharset);
		} catch(UnsupportedEncodingException e) {
			value1 = value.getBytes();
			key1 = key.getBytes();
		}

		Arrays.fill(k_ipad, key1.length, 64, (byte) 54);
		Arrays.fill(k_opad, key1.length, 64, (byte) 92);
		for(int i = 0; i < key1.length; i++) {
			k_ipad[i] = (byte) (key1[i] ^ 0x36);
			k_opad[i] = (byte) (key1[i] ^ 0x5c);
		}

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch(NoSuchAlgorithmException e) {
			return null;
		}
		md.update(k_ipad);
		md.update(value1);
		byte[] dg = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 16);
		dg = md.digest();
		return toHex(dg);
	}

	/**
	 * 得到指定字符串的SHA摘要，并转化成16进制。
	 */
	public static String digest(@NotNull String aValue) {
		aValue = aValue.trim();
		byte[] value;
		try {
			value = aValue.getBytes(encodingCharset);
		} catch(UnsupportedEncodingException e) {
			value = aValue.getBytes();
		}
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return toHex(md.digest(value));
	}

	/**
	 * 将指定字符集转化成16进制。
	 */
	public static String toHex(@NotNull byte[] input) {
		StringBuilder output = new StringBuilder(input.length * 2);
		for(byte anInput : input) {
			int current = anInput & 0xff;
			if(current < 16)
				output.append("0");
			output.append(Integer.toString(current, 16));
		}

		return output.toString();
	}

}
