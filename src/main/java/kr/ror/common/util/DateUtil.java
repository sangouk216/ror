/**========================================================
 *@FileName	: DateUtil.java
 *@Date		: 2010. 8. 23. 
 *@작성자   : 정한성
 *@설명		: 공통코드 콤보
 =========================================================*/

package kr.ror.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 
 * <p>Copyright(c) 2010 AgencyPro.
 * All Rights reserved.</p>
 * @version 
 * @author 
 */

public class DateUtil {
	private static final String YYYYMMDD = "yyyyMMdd";
	private static final String HHMMSS = "HHmmss";
	private static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	

    /**
     * 현재 날짜를 java.sql.Date 형으로 가져옴.
     *
     * @return java.sql.Date
     * @throws ProFrameApplicationException 년월일이 없는 경우 발생
     */
    public static java.sql.Date getDate() throws Exception
    {
        return getSystemDate();
    }

    /**
     * 현재 날짜를 java.sql.Timestamp 형으로 가져옴.
     *
     * @return java.sql.Timestamp
     * @throws ProFrameApplicationException 년월일이 없는 경우 발생
     */
    public static java.sql.Timestamp getTimestamp() throws Exception
    {
        return new Timestamp(getSystemDate().getTime());
    }


    /**
     * 현재 날짜를 yyyyMMdd 형식의 문자열로 가져옴.
     *
     * <pre>
     *  사용 예)
     *  DateUtil.getDateString()  =&gt; &quot;20060311&quot; (현재 날짜가 출력됨)
     * </pre>
     *
     * @return yyyyMMdd 형식의 현재 날짜
     */
    public static String toDateString()
    {
    	SimpleDateFormat dateFormatter = new SimpleDateFormat(YYYYMMDD);
        return dateFormatter.format(getSystemDate());
    }

    /**
     * 현재 날짜를 입력 포맷의 문자열로 가져옴.
     *
     * <pre>
     *  사용 예)
     *  DateUtil.getDateString("yyyyMMddHHmmss")  =&gt; &quot;20061017233030&quot; (현재 날짜가 출력됨)
     * </pre>
     * @param datePattern
     *
     * @return yyyyMMdd 형식의 현재 날짜
     */
    public static String toDateString(String datePattern)
    {
    	return new SimpleDateFormat(datePattern).format(getSystemDate());
    }

    /**
     * java.sql.Date 객체를 yyyyMMdd 형식의 문자열로 변환함.
     *
     * @param date
     * @return yyyyMMdd 형식의 입력 날짜
     */
    public static String toDateString(Date date)
    {
        if (date == null)
            return "";
    	SimpleDateFormat dateFormatter = new SimpleDateFormat(YYYYMMDD);
        return dateFormatter.format(date);
    }

    /**
     * java.util.Date 객체를 입력된 형식의 문자열로 변환함.
     *
     * @param date
     * @param datePattern
     * @return yyyyMMdd 형식의 입력 날짜
     */
    public static String toDateString(Date date, String datePattern)
    {
        if (date == null)
            return "";
    	return new SimpleDateFormat(datePattern).format(date);
    }

    /**
     * java.sql.Timestamp 객체를 yyyyMMdd 형식의 문자열로 변환함.
     *
     * @param date
     * @return yyyyMMdd 형식의 입력 날짜
     */
    public static String toDateString(java.sql.Timestamp date)
    {
        if (date == null)
            return "";
    	SimpleDateFormat dateFormatter = new SimpleDateFormat(YYYYMMDD);
        return dateFormatter.format(date);
    }

    /**
     * 현재 날짜를 HHmmss 형식의 문자열로 변환함.
     *
     * @return HHmmss 형식의 현재 시간
     */
    public static String toTimeString()
    {
        SimpleDateFormat timeFormatter = new SimpleDateFormat(HHMMSS);
        return timeFormatter.format(getSystemDate());
    }

    /**
     * java.sql.Date 객체를 HHmmss 형식의 문자열로 변환함.
     *
     * @param date
     * @return HHmmss 형식의 입력 시간
     */
    public static String toTimeString(java.sql.Date date)
    {
        if (date == null)
            return "";
        SimpleDateFormat timeFormatter = new SimpleDateFormat(HHMMSS);
        return timeFormatter.format(date);
    }

    /**
     * java.sql.Timestamp 객체를 HHmmss 형식의 문자열로 변환함.
     *
     * @param date
     * @return HHmmss 형식의 입력 시간
     */
    public static String toTimeString(java.sql.Timestamp date)
    {
        if (date == null)
            return "";
        SimpleDateFormat timeFormatter = new SimpleDateFormat(HHMMSS);
        return timeFormatter.format(date);
    }

    /**
     * 현재 날짜를 yyyyMMddHHmmss 형식의 문자열로 변환함.
     *
     * @return yyyyMMddHHmmss 형식의 현재 날짜+시간
     */
    public static String toDateTimeString()
    {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
        return dateTimeFormatter.format(getSystemDate());
    }

    /**
     * java.sql.Date 객체를 yyyyMMddHHmmss 형식의 문자열로 변환함.
     *
     * @param date
     * @return yyyyMMddHHmmss 형식의 입력 날짜+시간
     */
    public static String toDateTimeString(java.sql.Date date)
    {
        if (date == null)
            return "";
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
        return dateTimeFormatter.format(date);
    }

    /**
     * java.sql.Timestamp 객체를 yyyyMMddHHmmss 형식의 문자열로 변환함.
     *
     * @param date
     * @return yyyyMMddHHmmss 형식의 입력 날짜+시간
     */
    public static String toDateTimeString(java.sql.Timestamp date)
    {
        if (date == null)
            return "";
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
        return dateTimeFormatter.format(date);
    }

    /**
     * java.sql.Date 객체를 java.sql.Timestamp 형으로 변환함.
     *
     * @param date java.sql.Date 객체
     * @return java.sql.Timestamp
     */
    public static Timestamp toTimestamp(Date date)
    {
        if (date == null)
            return null;
        return new Timestamp(date.getTime());
    }


    /**
     * 일자간의 일수 차이를 구한다.
     *
     * @param beginDate
     * @param endDate
     * @return 두 일자간의 일수 차이
     */
    public static long getDaysDiff(java.sql.Date beginDate, java.sql.Date endDate)
    {
        if (endDate.after(beginDate))
        {
            return doGetDaysDiff(beginDate, endDate);
        } else
        {
            return -doGetDaysDiff(endDate, beginDate);
        }
    }

    private static long doGetDaysDiff(java.sql.Date beginDate, java.sql.Date endDate)
    {
        long diff = endDate.getTime() - beginDate.getTime();

        return (long) diff / (24 * 3600 * 1000);
    }


    /**
     * 일자간의 일수 차이를 구한다. 1일을 넘어서면 2일로 계산한다.
     *
     * @param beginDate
     * @param endDate
     * @return 두 일자간의 일수 차이
     */
    public static long getDaysDiffAbove(java.sql.Date beginDate, java.sql.Date endDate)
    {
        if (endDate.after(beginDate))
        {
            return doGetDaysDiffAbove(beginDate, endDate);
        } else
        {
            return -doGetDaysDiffAbove(endDate, beginDate);
        }
    }

    private static long doGetDaysDiffAbove(java.sql.Date beginDate, java.sql.Date endDate)
    {
        long diff = endDate.getTime() - beginDate.getTime();

        double dayDiff = diff / (24 * 3600 * 1000.0);

        return (long) Math.ceil(dayDiff);
    }

    
    /**
     * java.util.Date 객체를 java.sql.Date 객체로 변환함.
     *
     * @param utilDate java.util.Date
     * @return java.sql.Date
     */
    public static java.sql.Date toSqlType(java.util.Date utilDate)
    {   
        if (utilDate == null)
            return null;
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }

    /**
     * 입력일자의 분기를 구한다.
     *
     * @param strDate yyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
     * @return 입력일자의 분기(int)
     */
    public static int getQuater(String strDate)
    {

        String[] pd = parseDate(strDate);
        if (pd == null)
            return -1;

        int month = Integer.parseInt(pd[1]);

        return (month - 1) / 3 + 1;
    }


    /**
     * 현재월의 첫번쨰날자를 구한다
     *
     * @param datePattern 리턴 할 데이트 페턴.
     * @return 입력한 datePattern 형식의 날자
     */
    public static String getFirstDay(String datePattern)
    {
    	Calendar cal = Calendar.getInstance();
    	cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    	return new SimpleDateFormat(datePattern).format(cal.getTime());
    }

    /**
     * 현재월의 첫번쨰날자를 구한다
     *
     * @return yyyyMMdd 형식의 날자
     */
    public static String getFirstDay()
    {
        return getFirstDay(YYYYMMDD);
    }


    /**
     * 현재월의 마지막날자를 구한다
     *
     * @param datePattern 리턴 할 데이트 페턴
     * @return 입력한 datePattern 형식의 날자
     */
    public static String getLastDay(String datePattern)
    {
    	Calendar cal = Calendar.getInstance();
    	cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    	return new SimpleDateFormat(datePattern).format(cal.getTime());
    }

    /**
     * 현재월의 마지막날자를 구한다
     *
     * @return yyyyMMdd 형식의 날자
     */
    public static String getLastDay()
    {
        return getLastDay(YYYYMMDD);
    }


    /**
     * 날자를 Calendar 형식으로 변환한다
     *
     * @param date yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
     * @return Calendar Object
     */
    private static Calendar toCalendar(String date) {
        String[] pd = parseDate(date);
        if (pd == null)
            return null;

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(pd[0]), Integer.parseInt(pd[1])-1, Integer.parseInt(pd[2]));
        return cal;
    }

    
    /**
     * 입력일자기준으로 날자를 더한다
     *
     * @param date yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
     * @param day  더할 날자
     * @param datePattern 리턴할 날자의 datePattern
     * @return 입력일자에 월도를 더한 datePattern 형식의 문자열
     */
    public static String addDay(String date, int day, String datePattern)
    {
        Calendar cal = toCalendar(date);
        if(cal == null) 
            return null;
        cal.add(Calendar.DATE, day);
        return new SimpleDateFormat(datePattern).format(cal.getTime());
    }

    /**
     * 입력일자기준으로 날자를 더한다
     *
     * @param date yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
     * @param day  더할 날자
     * @return 입력일자에 날자를 더한 yyyyMMdd 형식의 문자열
     */
    public static String addDay(String date, int day)
    {
        return addDay(date, day, YYYYMMDD);
    }

    /**
     * 입력일자기준으로 날자를 더한다
     *
     * @param date yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
     * @param day  더할 날자
     * @return 입력일자에 날자를 더한 yyyyMMdd 형식의 문자열
     */
    public static String addDay(java.sql.Date date, int day)
    {
        return addDay(toDateString(date), day, YYYYMMDD);
    }

    /**
     * 오늘날자 기준으로 날자를 더한다
     *
     * @param day  더할 날자
     * @param datePattern 리턴할 날자의 datePattern
     * @return 오늘날자에 날자를 더한 datePattern 형식의 문자열
     */
    public static String addDay(int day, String datePattern)
    {
        return addDay(toDateString(), day, datePattern);
    }

    /**
     * 오늘날자 기준으로 날자를 더한다
     *
     * @param day  더할 날자
     * @return 오늘날자에 날자를 더한 yyyyMMdd 형식의 문자열
     */
    public static String addDay(int day)
    {
        return addDay(toDateString(), day, YYYYMMDD);
    }


    /**
     * 입력일자기준으로 월도를 더한다
     *
     * @param date yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
     * @param month 더할 월도
     * @param datePattern 리턴할 날자의 datePattern
     * @return 입력일자에 월도를 더한 datePattern 형식의 문자열
     */
    public static String addMonth(String date, int month, String datePattern)
    {
        Calendar cal = toCalendar(date);
        if(cal == null) return null;
        cal.add(Calendar.MONTH, month);
        return new SimpleDateFormat(datePattern).format(cal.getTime());
    }


    /**
     * 입력일자기준으로 월도를 더한다
     *
     * @param date yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
     * @param month 더할 월도
     * @return 입력일자에 월도를 더한 yyyyMMdd 형식의 문자열
     */
    public static String addMonth(String date, int month)
    {
        return addMonth(toDateString(date), month, YYYYMMDD);
    }


    /**
     * 입력일자기준으로 월도를 더한다
     *
     * @param date yyyyMMdd 형식의 문자열, 구분자가 있어도 상관없음.
     * @param month 더할 월도
     * @return 입력일자에 월도를 더한 yyyyMMdd 형식의 문자열
     */
    public static String addMonth(java.sql.Date date, int month)
    {
        return addMonth(toDateString(date), month, YYYYMMDD);
    }

    /**
     * 오늘날자 기준으로 월도를 더한다
     *
     * @param month 더할 월도
     * @param datePattern 리턴할 날자의 datePattern
     * @return 오늘날자에 월도를 더한 datePattern 형식의 문자열
     */
    public static String addMonth(int month, String datePattern)
    {
        return addMonth(toDateString(), month, datePattern);
    }

    /**
     * 오늘날자 기준으로 월도를 더한다
     *
     * @param month 더할 월도
     * @return 오늘날자에 월도를 더한 yyyyMMdd 형식의 문자열
     */
    public static String addMonth(int month)
    {
        return addMonth(toDateString(), month);
    }
    

    /**
     * 시스템 날짜를 가져옴.
     *
     * @return Date
     */
    private static java.sql.Date getSystemDate()
    {
        return new java.sql.Date(System.currentTimeMillis());
    }

    private static String[] parseDate(String strDate)
    {
        if (strDate == null)
            return null;

        strDate = strDate.replaceAll("^\\D+$", "");

        if (strDate.length() >= 10)
        {
            return new String[] { strDate.substring(0, 4), strDate.substring(4, 6), strDate.substring(6, 8), strDate.substring(8, 10) };
        } else if (strDate.length() >= 8)
        {
            return new String[] { strDate.substring(0, 4), strDate.substring(4, 6), strDate.substring(6, 8), "00" };
        } else if (strDate.length() >= 6)
        {
            return new String[] { strDate.substring(0, 4), strDate.substring(4, 6), "00", "00" };
        } else if (strDate.length() >= 4)
        {
            return new String[] { strDate.substring(0, 4), "00", "00", "00" };
        }
        return null;
    }
}
