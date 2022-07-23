package org.springblade.common.tool;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTime {

	/**
	 * 获取过去或者未来 任意天内的日期数组
	 * @param intervals intervals天内
	 * @return 日期数组
	 */
	public static ArrayList<String> pastDayss(String strData, int intervals, int k) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = 0; i < intervals; i++) {
			if (i % k == 0) {
				pastDaysList.add(getPastDates(strData, i));
			}
		}
		System.out.println(pastDaysList);
		return pastDaysList;
	}

	/**
	 * 获取未来第几天的日期
	 * @param past
	 * @param
	 * @return
	 */
	public static String getPastDates(String strData, int past) {
		String preDate = "";
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(strData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day1 = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day1 + past);
		preDate = sdf.format(c.getTime());
		return preDate;
	}

	/**
	 * 获取过去或者未来 任意天内的日期数组
	 * @param intervals intervals天内
	 * @return 日期数组
	 */
	public static ArrayList<String> pastMonth(String strData, int intervals, int k) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = 0; i < intervals; i++) {
			if (i % k == 0) {
				pastDaysList.add(getPastMonth2(strData, i));
			}
		}
		System.out.println(pastDaysList);
		return pastDaysList;
	}

	/**
	 * 获取未来第几月的日期
	 * @param past
	 * @return
	 */
	public static String getPastMonth(String strData, int past) {
		String preDate = "";
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		Date date = null;
		try {
			date = sdf.parse(strData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day1 = c.get(Calendar.MONTH);
		c.set(Calendar.MONTH, day1 - past);
		preDate = sdf.format(c.getTime());
		return preDate;
	}

	public static String getPastMonth2(String strData, int past) {
		String preDate = "";
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(strData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day1 = c.get(Calendar.MONTH);
		c.set(Calendar.MONTH, day1 - past);
		preDate = sdf.format(c.getTime());
		return preDate;
	}


	/**
	 * 获取过去或者未来 任意天内的日期数组
	 * @param intervals intervals天内
	 * @return 日期数组
	 */
	public static ArrayList<String> pastDays(int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = 0; i < intervals; i++) {
			pastDaysList.add(getPastDate(i));
		}
		System.out.println(pastDaysList);
		return pastDaysList;
	}

	/**
	 * 获取过去第几天的日期
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * @Author：
	 * @Description：更加输入日期，获取输入日期的前一天
	 * @Date：
	 * @strData：参数格式：yyyy-MM-dd
	 * @return：返回格式：yyyy-MM-dd
	 */
	public static String getPreDateByDate(String strData) {
		String preDate = "";
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(strData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day1 = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day1 - 1);
		preDate = sdf.format(c.getTime());
		return preDate;
	}

	/**
	 * date 需要计算的日期
	 * dateAmount 相差数据
	 * calendar 年月日
	 * return 根据传入时间获取对于相差时间
	 */
	public static String datedif(Date date, int dateAmount, int calendar) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendar, -dateAmount);
		Date d = c.getTime();
		String pastdate = format.format(d);
		return pastdate;
	}

	/**
	 * 获取两个日期相差时间
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int difDay(String beginDate, String endDate) {
		int days = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date beginDateD = format.parse(beginDate);
			Date endDateD = format.parse(endDate);
			days = (int) ((endDateD.getTime() - beginDateD.getTime()) / (1000 * 3600 * 24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

	/**
	 * 判断两个时间相差多少小时
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static int hour(String beginTime, String endTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		long st = 0;
		long en = 0;
		try {
			st = df.parse(beginTime).getTime();
			en = df.parse(endTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int h = (int) (((en - st) % 86400000) / 3600000);
		return h;
	}

	/**
	 * 判断两个时间相差多少分钟
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static int minute(String beginTime, String endTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		long st = 0;
		long en = 0;
		try {
			st = df.parse(beginTime).getTime();
			en = df.parse(endTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int min = (int) (((en - st) % 86400000) % 3600000 / (1000 * 60));
		return min;
	}

	/**
	 * 获取未来 第 past 天的日期
	 * @param past
	 * @return
	 */
	public static String getFetureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 判断时间是否在时间段内
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean belongCalendar(Date nowTime, Date beginTime,
										 Date endTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取前24小时的整点时间
	 * @param day
	 * @param dateFormat
	 * @return
	 */
	public static ArrayList<String> twHour(Date day, String dateFormat) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		String s = df.format(day);
		Date date = null;
		try {
			date = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<String> dates = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if (i != 0) {
				cal.add(Calendar.HOUR, -1);
			}
			date = cal.getTime();
			String s1 = df.format(date);
			dates.add(s1);
		}
		Collections.reverse(dates);
		return dates;
	}

	/**
	 * 获取前24小时的半小时时间
	 * @param day
	 * @return
	 */
	public static ArrayList<String> twMinit(Date day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		String s = df.format(day);
		Date date = null;
		try {
			date = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<String> dates = new ArrayList<String>();
		for (int i = 0; i < 48; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MINUTE, -30);
			date = cal.getTime();
			String s1 = format.format(date);
			dates.add(s1);
		}
		Collections.reverse(dates);
		return dates;
	}

	/**
	 * 获取前6小时的整点时间
	 * @param day
	 * @return
	 */
	public static ArrayList<String> sixHour(Date day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		String s = df.format(day);
		Date date = null;
		try {
			date = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<String> dates = new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if (i != 0) {
				cal.add(Calendar.HOUR, -1);
			}
			date = cal.getTime();
			String s1 = df.format(date);
			dates.add(s1);
		}
		Collections.reverse(dates);
		return dates;
	}

	/**
	 * 获取前几个月时间
	 * @param date
	 * @return
	 */
	public static String yesMonth(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		// 过去一月
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);
		System.out.println("过去一个月：" + mon);
		return mon;
	}

	/**
	 * 获取前几个月时间
	 * @param date
	 * @param amount
	 * @param format
	 * @return
	 */
	public static String yesMonth2(Date date, int amount, SimpleDateFormat format) {
		Calendar c = Calendar.getInstance();
		// 过去一月
		c.setTime(date);
		c.add(Calendar.MONTH, amount);
		Date m = c.getTime();
		String mon = format.format(m);
		System.out.println("第" + amount + "个月：" + mon);
		return mon;
	}

	/**
	 * 根据当前时间判断数据库里是否一个小时前已经存储数据
	 * @param dateN
	 * @param oldTime
	 * @return
	 */
	public static boolean selectExist(String dateN, String oldTime) {

		boolean reExist;
		if (oldTime != null && !"".equals(oldTime)) {
			int h = DateTime.hour(oldTime, dateN);
			if (h > 0) {
				reExist = true;
			} else {
				reExist = false;
			}
		} else {
			reExist = true;
		}
		return reExist;
	}

	/**
	 * 根据当前时间判断数据库里是否半小时前已经存储数据
	 * @param dateN
	 * @param oldTime
	 * @return
	 */
	public static boolean selectExistMinute(String dateN, String oldTime) {

		boolean reExist;
		if (oldTime != null && !"".equals(oldTime)) {
			int min = DateTime.minute(oldTime, dateN);
			if (min > 30) {
				reExist = true;
			} else {
				reExist = false;
			}
		} else {
			reExist = true;
		}
		return reExist;
	}

	/**
	 * 判断当前token 是否在时效内
	 * @param getTokenTime
	 * @param expires_in
	 * @return
	 */
	public static boolean comperToken(Long getTokenTime, Long expires_in) {
		Long nowTime = System.currentTimeMillis();
		if ((getTokenTime + (expires_in * 1000)) > nowTime) {
			return true;
		}
		return false;
	}

	/**
	 * 获取当前时间（年月日时分秒）， 当前时间（年月日）， 当月开始时间
	 * @param data
	 * @return
	 */
	public static Map<String, Object> getNowBeginTime(Date data) {
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat dfH = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dfbeg = new SimpleDateFormat("yyyy-MM-01 00:00:00");//设置日期格式
		String todayTime = dfH.format(data);
		String today = todayTime.substring(0, 10);
		String beginTime = dfbeg.format(data);
		map.put("todayTime", todayTime);
		map.put("today", today);
		map.put("beginTime", beginTime);
		return map;
	}

	/**
	 * 获取当前时间前一个小时时间
	 * @param data
	 * @return
	 */
	public static String getpastMinit(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 30);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}

	/**
	 * 获取当前时间前一个小时时间
	 * @param data
	 * @return
	 */
	public static String getpastHour(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 1);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}

	public static String getpastMinit(String startTime, int minit) {
		Date data = null;
		try {
			data = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - minit);
		String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(calendar.getTime());
		System.out.println(endTime);
		return endTime;
	}

	/**
	 * 根据年 月 获取对应的月份 天数
	 */

	public static int getDaysByYearMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;

	}

}
