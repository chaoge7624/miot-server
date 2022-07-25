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

		// 思考了许久 任务的开始与结束应该有一个消息表去维护 就没必要在返回值做太多文章了
		// id
		// 任务名称 name
		// 执行开始时间 start_time
		// 执行结束时间 end_time
		// 任务状态(0:执行完成,1:执行失败) status
		// ...

		CompletableFuture.runAsync(() -> {
			try {
				// 记录线程开始时间及结束时间
				// ...
				point.proceed();
				// 这里应该套一层超时 以后再说
				// ...
			} catch (Throwable e) {
				throw new RuntimeException(e);
				// 如果抛出异常应该是任务执行失败
				// ...
			}
		}, ThreadPoolUtils.getThreadPool());

		// 不要返回值了
		return null;
	}
}
