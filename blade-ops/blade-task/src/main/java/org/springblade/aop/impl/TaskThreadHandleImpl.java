package org.springblade.aop.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springblade.common.tool.ThreadPoolUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * 定时任务线程切面处理
 * @author 李家民
 */
@Component
@Aspect
public class TaskThreadHandleImpl {

	/**
	 * 异步线程池处理 防止因为线程卡顿
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(org.springblade.aop.TaskThreadHandle)")
	public Object asyncThreadExecute(ProceedingJoinPoint point) throws Throwable {
		CompletableFuture.runAsync(() -> {
			try {
				point.proceed();
				// 这里应该套一层超时 以后再说
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		}, ThreadPoolUtils.getThreadPool());
		// void 也不要返回值了
		return null;
	}
}
