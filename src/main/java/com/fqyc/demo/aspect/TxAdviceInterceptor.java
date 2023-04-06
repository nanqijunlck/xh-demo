package com.fqyc.demo.aspect;///**
// *
// */
//package com.fqyc.quality.aspect;
//
//
//import org.springframework.aop.Advisor;
//import org.springframework.aop.aspectj.AspectJExpressionPointcut;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.interceptor.*;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
///**
//* @Description:
//* @author: frank
//* @date: 2019年5月31日 下午4:31:16
//*/
//
//@Configuration
//public class TxAdviceInterceptor {
//
//	private static final int TX_METHOD_TIMEOUT = 600;
//    private static final String AOP_POINTCUT_EXPRESSION = "execution (* cn.hydee.ewx.honey.service.impl.*.*(..))";
//
//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//
//    @Bean
//    public TransactionInterceptor txAdvice() {
//        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
//        /*只读事务，不做更新操作*/
//        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
//        readOnlyTx.setReadOnly(true);
//        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
//        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
//        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
//        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
//        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
//        Map<String, TransactionAttribute> txMap = new HashMap<>();
//        txMap.put("*", requiredTx);
//        txMap.put("add*", requiredTx);
//        txMap.put("save*", requiredTx);
//        txMap.put("insert*", requiredTx);
//        txMap.put("update*", requiredTx);
//        txMap.put("delete*", requiredTx);
//        txMap.put("get*", readOnlyTx);
//		txMap.put("list*", readOnlyTx);
//        txMap.put("query*", readOnlyTx);
//        source.setNameMap(txMap);
//        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
//        return txAdvice;
//    }
//
//    @Bean
//    public Advisor txAdviceAdvisor() {
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
//        return new DefaultPointcutAdvisor(pointcut, txAdvice());
//    }
//}
