package org.springblade;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

/**
 * 静态任务执行框架
 * https://qqe2.com/cron/index
 * 在线cron表达式生成
 * @author lijiamin
 */
@BladeCloudApplication
public class TaskApplication {
	public static void main(String[] args) {
		BladeApplication.run("blade-task", TaskApplication.class, args);
	}
}
