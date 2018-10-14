package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

public class DateTime {
	private long advance;
	private long time;

	public DateTime() {
		time = System.currentTimeMillis();
		// ��õ�����1970-1-01 00:00:00.000 ����ǰʱ�̵�ʱ����룬����
	}

	public DateTime(int setClockForwardInDays) {
		advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;
		// ��ת�ɺ���
		time = System.currentTimeMillis() + advance;
		// ���ڵ�δ��ĳ��ĺ���
	}
	
	public DateTime(String s) throws Exception {
		String[] a;
		a = s.split("/");
		setDate(Integer.parseInt(a[0]),Integer.parseInt(a[1]),Integer.parseInt(a[2]));
		
	}
	public DateTime(DateTime startDate, int setClockForwardInDays) {
		advance = ((setClockForwardInDays * 24L + 0) * 60L) * 60000L;
		time = startDate.getTime() + advance;
		// ĳһ�쵽δ��ĳ��ĺ���
	}

	public DateTime(int day, int month, int year) {
		setDate(day, month, year);
		// ĳ��ĳ��ĳ��ת��Ϊ�������time
	}

	public long getTime() {
		return time;
	}

	public String toString() {
		// long currentTime = getTime();
		// Date gct = new Date(currentTime);
		// return gct.toString();
		return getFormattedDate();
		// ת��Ϊ ��/��/��
	}

	public static String getCurrentTime() {
		Date date = new Date(System.currentTimeMillis());
		// ���ϵͳ��ǰʱ����룬����Date��������Date
		return date.toString();
		// ���ش�ʱ���String��ʽ��ת��Ϊ ��/��/��
	}

	public String getFormattedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");// ָ��
		long currentTime = getTime();// ����
		Date gct = new Date(currentTime);// ��-��-��

		return sdf.format(gct);// ��/��/��
	}

	public String getEightDigitDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		long currentTime = getTime();
		Date gct = new Date(currentTime);

		return sdf.format(gct);
		// ������
	}

	// returns difference in days
	public static int diffDays(DateTime endDate, DateTime startDate) {
		final long HOURS_IN_DAY = 24L;
		final int MINUTES_IN_HOUR = 60;
		final int SECONDS_IN_MINUTES = 60;
		final int MILLISECONDS_IN_SECOND = 1000;
		long convertToDays = HOURS_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTES * MILLISECONDS_IN_SECOND;
		long hirePeriod = endDate.getTime() - startDate.getTime(); // ���������
		double difference = (double) hirePeriod / (double) convertToDays; // ת��double��
		int round = (int) Math.round(difference);// ��������int�������ȡ��
		return round;
		// ����date�������int
	}

	private void setDate(int day, int month, int year) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, 0, 0);

		java.util.Date date = calendar.getTime();
		// �õ���ǰ��������ʱ�� �磺Mon Aug 06 00:00:31 AEST 2018

		time = date.getTime();
		// ת��Ϊ����
	}

	// Advances date/time by specified days, hours and mins for testing purposes
	public void setAdvance(int days, int hours, int mins) {
		advance = ((days * 24L + hours) * 60L) * 60000L;
	}
}
