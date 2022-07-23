package org.springblade.business;

import org.springblade.aop.TaskThreadHandle;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @Component 要用就放下去咯
 * @EnableScheduling 要用就放下去咯
 * 定时任务示例Demo
 */
public class DemoBusiness {

	/** 上一次执行完毕时间后执行下一轮 --- 每次5秒 缺点：任务之间会发生线程阻塞 所以那个@TaskThreadHandle注解要套上 */
	@Scheduled(cron = "0/5 * * * * *")
	@TaskThreadHandle
	public void demoRunSync() throws InterruptedException {
		long startTime = System.currentTimeMillis();
		Thread.sleep(10000);
		System.out.println("=====>>>>>使用cron demoRunSync() {}");
		long lastTime = System.currentTimeMillis();
		System.out.println("demoRunSync()执行时长 = " + (lastTime - startTime) / 1000 + " 秒");
	}

	/**
	 * 会议状态/会议室状态定时刷新
	 * 每小时的x分执行一次
	 */
	@Scheduled(cron = "0 5,20,50 * * * ?")
	@TaskThreadHandle
	public void meetingStatusUpdate() {
		System.out.println("会议状态/会议室状态定时刷新 = " + LocalDateTime.now());
	}

	/**
	 * 访客状态定时刷新
	 * 每天凌晨1点刷新
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	@TaskThreadHandle
	public void visitStatusUpdate() {
		System.out.println("访客状态定时刷新 = " + LocalDateTime.now());
	}

}
