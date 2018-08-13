package com.wp.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.touna.share.vo.partner.ELendChannel;

import cn.touna.fund.logic.payment.IRoutePayment;
import cn.touna.fund.logic.payment.impl.BaoFooPaymentImpl;

/**
 * 直融放款渠道注解
 * 作者:Share.Mu
 * 日期：2018年4月28日 上午10:55:29
 * 注解功能描述：
 * 	1. 放款渠道ID.
 *  2. 放款渠道名称.
 *  3. 尾款处理类（直融机构的尾款处理默认为：恒赢宝付BaoFooPaymentImpl）.
 *  4. 被注解的类是否是单例的.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoanChannel {
	/** 放款渠道ID **/
	public ELendChannel[] loanChannelId() ;
	/** 放款渠道名称 **/
	public String loanChannelName();
	/**尾款处理类**/
	public Class<? extends IRoutePayment> tailProcess() default BaoFooPaymentImpl.class;
	/**
	 * 是否单实例，默认true
	 * @return
	 */
	public boolean single() default true;
}
