/*package com.wp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.ws.util.StringUtils;
import com.touna.share.vo.partner.ELendChannel;

import cn.touna.fund.api.PartnerRepayService;
import cn.touna.fund.bean.annatations.LoanChannel;
import cn.touna.fund.logic.external.IExternal;
import cn.touna.fund.logic.external.impl.MethodInterceptorImplExt;
import cn.touna.fund.logic.payment.IResultQuery;
import cn.touna.fund.logic.payment.IRoutePayment;
import cn.touna.fund.logic.request.IHttpRequestHandler;
import cn.touna.fund.logic.request.IRequestDataBuilder;
import cn.touna.fund.share.IoWrap;
import cn.touna.fund.share.s360.S360LoanCallBackRequest;
import cn.touna.fund.share.s360.S360QueryMoneyResponse;
import net.sf.cglib.proxy.Enhancer;

*//**
 * 本加载器仅用于LoanChannel注解的IRoutePayment或IResultQuery子类
 * 作者:Share.Mu
 * 日期：2018年4月25日 下午4:52:01
 * 描述：放款渠道对象加载.
 *//*
public class LendChanelManager {
    private static final HashMap<Integer, IRoutePayment> routePaymentInstance = new HashMap<>();
    private static final HashMap<Integer, Class> routePaymentClassCache = new HashMap<>();
    private static final HashMap<Integer, IResultQuery> resultQueryInstance = new HashMap<>();
    private static final HashMap<Integer, Class> resultQueryClassCache = new HashMap<>();
    private static final HashMap<Integer, Class> tailClassCache = new HashMap<>();
    private static final HashMap<Integer, IHttpRequestHandler> iRequestHandlerInstance = new HashMap<>();
    private static final HashMap<Integer, Class> iRequestHandlerClassCache = new HashMap<>();
    private static final HashMap<Integer, Class> extnernalClassCache = new HashMap<>();
    private static final HashMap<Integer, Class> requestDataBuilderClassCache = new HashMap<>();
    private static final HashMap<Integer, IRequestDataBuilder> requestDataBuilderInstance = new HashMap<>();
    private static final HashMap<Integer, PartnerRepayService> partnerRepayInstance = new HashMap<>();
    private static final HashMap<Integer, Class> partnerRepayClassCache = new HashMap<>();

    private static boolean loaded = false;

    public static synchronized boolean load() {
        try {
            if (loaded) {
                return true;
            }
            Set<Class<?>> allClasses = ClassUtil.getClasses("cn.touna.fund.logic.payment.impl");
            allClasses.addAll(ClassUtil.getClasses("cn.touna.fund.logic.request"));
            allClasses.addAll(ClassUtil.getClasses("cn.touna.fund.logic.external.impl"));
            allClasses.addAll(ClassUtil.getClasses("cn.touna.fund.logic.repay"));
            for (Class<?> clazz : allClasses) {
                try {
                    LoanChannel routeTypeAnn = clazz.getAnnotation(LoanChannel.class);
                    if (routeTypeAnn != null) {
                        if (routeTypeAnn.loanChannelId() == null || StringUtils.isEmpty(routeTypeAnn.loanChannelId())) {
                            throw new RuntimeException("放款渠道加载失败," + clazz.getName() + ",放款渠道ID不能为空. 放款渠道名称:" + routeTypeAnn.loanChannelName());
                        }
                        ELendChannel[] loanChannelIds = routeTypeAnn.loanChannelId();//放款渠道ID，允许多个，用逗号分隔.
                        for (ELendChannel lendChannel : loanChannelIds) {
                            Integer loanChannelId = lendChannel.getChannel();

                            if (IRoutePayment.class.isAssignableFrom(clazz)) {
                                if (routePaymentInstance.get(loanChannelId) != null) {
                                    throw new RuntimeException("放款渠道加载失败:放款渠道ID重复:" + lendChannel.getChannel() + " 放款渠道名称:" + routeTypeAnn.loanChannelName());
                                }
                                logger.info("放款渠道加载--->>>放款渠道ID:{" + lendChannel.getChannel() + "} 放款渠道名称:{" + routeTypeAnn.loanChannelName() + "}");
                                if (routeTypeAnn.single()) {
                                    routePaymentInstance.put(loanChannelId, (IRoutePayment) clazz.newInstance());
                                } else {
                                    routePaymentClassCache.put(loanChannelId, clazz);
                                }
                                if (routeTypeAnn.tailProcess() != null) {
                                    tailClassCache.put(loanChannelId, routeTypeAnn.tailProcess());
                                }
                            }

                            if (IResultQuery.class.isAssignableFrom(clazz)) {
                                if (resultQueryInstance.get(loanChannelId) != null) {
                                    throw new RuntimeException("放款渠道结果查询加载失败:放款渠道ID重复:" + loanChannelId + " 放款渠道名称:" + routeTypeAnn.loanChannelName());
                                }
                                logger.info("放款渠道结果查询加载--->>>放款渠道结果查询ID:{" + loanChannelId + "} 放款渠道名称:{" + routeTypeAnn.loanChannelName() + "}");
                                if (routeTypeAnn.single()) {
                                    resultQueryInstance.put(loanChannelId, (IResultQuery) clazz.newInstance());
                                } else {
                                    resultQueryClassCache.put(loanChannelId, clazz);
                                }
                            }

                            if (IHttpRequestHandler.class.isAssignableFrom(clazz)) {
                                if (iRequestHandlerInstance.get(loanChannelId) != null) {
                                    throw new RuntimeException("放款渠道请求处理类,ID重复:" + loanChannelId + " 放款渠道名称:" + routeTypeAnn.loanChannelName());
                                }
                                logger.info("放款渠道请求处理类加载--->>>放款渠道请求处理类ID:{" + loanChannelId + "} 放款渠道名称:{" + routeTypeAnn.loanChannelName() + "}");
                                if (routeTypeAnn.single()) {
                                    iRequestHandlerInstance.put(loanChannelId, (IHttpRequestHandler) clazz.newInstance());
                                } else {
                                    iRequestHandlerClassCache.put(loanChannelId, clazz);
                                }
                            }

                            if (IExternal.class.isAssignableFrom(clazz)) {
                                if (extnernalClassCache.get(loanChannelId) != null) {
                                    throw new RuntimeException("放款渠道External,ID重复:" + loanChannelId + " 放款渠道名称:" + routeTypeAnn.loanChannelName());
                                }
                                logger.info("放款渠道请求处理类加载--->>>放款渠道请求处理类ID:{" + loanChannelId + "} 放款渠道名称:{" + routeTypeAnn.loanChannelName() + "}");
                                extnernalClassCache.put(loanChannelId, clazz);
                            }

                            if (IRequestDataBuilder.class.isAssignableFrom(clazz)) {
                                if (requestDataBuilderClassCache.get(loanChannelId) != null) {
                                    throw new RuntimeException("放款渠道External,ID重复:" + loanChannelId + " 放款渠道名称:" + routeTypeAnn.loanChannelName());
                                }
                                logger.info("放款渠道请求处理类加载--->>>放款渠道请求处理类ID:{" + loanChannelId + "} 放款渠道名称:{" + routeTypeAnn.loanChannelName() + "}");
                                requestDataBuilderClassCache.put(loanChannelId, clazz);

                                if (routeTypeAnn.single()) {
                                    requestDataBuilderInstance.put(loanChannelId, (IRequestDataBuilder) clazz.newInstance());
                                } else {
                                    requestDataBuilderClassCache.put(loanChannelId, clazz);
                                }
                            }

                            if (PartnerRepayService.class.isAssignableFrom(clazz)) {
                                if (partnerRepayInstance.get(loanChannelId) != null) {
                                    throw new RuntimeException("放款渠道加载失败:放款渠道ID重复:" + lendChannel.getChannel() + " 放款渠道名称:" + routeTypeAnn.loanChannelName());
                                }
                                if (routeTypeAnn.single()) {
                                    partnerRepayInstance.put(loanChannelId, (PartnerRepayService) clazz.newInstance());
                                } else {
                                    partnerRepayClassCache.put(loanChannelId, clazz);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error("放款渠道加载，异常>>>>>>", e);
                    e.printStackTrace();
                    System.exit(0);
                }
                loaded = true;
            }
            logger.info("放款渠道加载完成>>>>>>");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    public static IResultQuery getResultQuery(Integer loanChannelId) {
        return (IResultQuery) getInstanceFromMap(loanChannelId, resultQueryInstance, resultQueryClassCache);
    }


    public static IRoutePayment getLoanChannel(Integer loanChannelId) {
        return (IRoutePayment) getInstanceFromMap(loanChannelId, routePaymentInstance, routePaymentClassCache);
    }

    public static IRoutePayment getTailProcessor(Integer loanChannelId) {
        return (IRoutePayment) getInstanceFromMap(loanChannelId, null, tailClassCache);
    }

    public static IRequestDataBuilder getRequestDataBuilder(Integer loanChannelId) {
        return (IRequestDataBuilder) getInstanceFromMap(loanChannelId, requestDataBuilderInstance, requestDataBuilderClassCache);
    }

    public static IHttpRequestHandler getRequestHandler(Integer loanChannelId) {
        return (IHttpRequestHandler) getInstanceFromMap(loanChannelId, iRequestHandlerInstance, iRequestHandlerClassCache);
    }

    public static PartnerRepayService getRepayChannel(Integer loanChannelId) {
        return (PartnerRepayService) getInstanceFromMap(loanChannelId, partnerRepayInstance, partnerRepayClassCache);
    }

    private static Object instanceObj(Class clazz) {
        if (clazz != null) {
            try {
                return clazz.newInstance();
            } catch (InstantiationException e) {
                logger.error("放款渠道类:{" + clazz.getCanonicalName() + "} 实例化出错.", e);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                logger.error("放款渠道类:{" + clazz.getCanonicalName() + "} 实例化出错.", e);
                e.printStackTrace();
            }
        }
        return null;
    }

    private static Object getInstanceFromMap(Integer loanChannelId, HashMap instanceMap, HashMap<Integer, Class> classCacheMap) {
        if (!loaded) {
            load();
        }
        if (instanceMap != null && instanceMap.get(loanChannelId) != null) {
            return instanceMap.get(loanChannelId);
        }
        Class clazz = (Class) classCacheMap.get(loanChannelId);
        return instanceObj(clazz);
    }


    private static HashMap<String, Object> beanInstaceCache = new HashMap<>();

    public static <T> T getExternalInstance(Integer channelId) {
        if (!loaded) {
            load();
        }
        Class superclass = extnernalClassCache.get(channelId);
        if(superclass==null)
        {
        	return null;
        }
        Object obj = beanInstaceCache.get(superclass.getCanonicalName());
        if (obj != null) {
            return (T) obj;
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(superclass);
        enhancer.setCallback(new MethodInterceptorImplExt());
        Object obj2 = enhancer.create();
        beanInstaceCache.put(superclass.getCanonicalName(), obj2);
        return (T) obj2;
    }


    public static void main(String[] args) {
        IExternal s360 = LendChanelManager.getExternalInstance(ELendChannel.S360.getChannel());
//		S360LoanApplyRequest req = new S360LoanApplyRequest();
//		req.setLoanApplyNo("100011444");
//		req.setThisAmount(11144);
//		IoWrap io=s360.preLoanApply2("360",req,S360LoanApplyResponse.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String queryTime = sdf.format(new Date());
        S360LoanCallBackRequest reqq = new S360LoanCallBackRequest();
        reqq.setLoanApplyNo("1212");
        IoWrap io = s360.loanResultCallback(ELendChannel.S360.getChannel(), reqq, S360QueryMoneyResponse.class);
        System.out.println("返回结果:" + JSON.toJSONString(io.getOutResp()));
    }


}
*/