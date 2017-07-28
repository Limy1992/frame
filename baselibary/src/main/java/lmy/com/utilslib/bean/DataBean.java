package lmy.com.utilslib.bean;

/**
 * Created by lmy on 2017/7/6
 */

public class DataBean {

    /**
     * date : 2017-07-06
     * exceptionNums : 4
     * list : [{"attendance_date":"2017-07-01","signOut_address":"","sign_address":"","sign_endTime":"","sign_startTime":"","sign_status":"3"},{"attendance_date":"2017-07-02","signOut_address":"","sign_address":"","sign_endTime":"","sign_startTime":"","sign_status":"3"},{"attendance_date":"2017-07-03","signOut_address":"","sign_address":"","sign_endTime":"","sign_startTime":"","sign_status":"2"},{"attendance_date":"2017-07-04","signOut_address":"","sign_address":"","sign_endTime":"","sign_startTime":"","sign_status":"2"},{"attendance_date":"2017-07-05","signOut_address":"","sign_address":"上海市黄浦区新永安路29号","sign_endTime":"","sign_startTime":"2017-07-05 14:12:10","sign_status":"2"},{"attendance_date":"2017-07-06","signOut_address":"","sign_address":"","sign_endTime":"","sign_startTime":"","sign_status":"2"}]
     * message : 查询成功
     * normalDays : 0
     * rcode : 0
     * unAppealNums : 1
     */

    public String date;
    public int exceptionNums;
    public String message;
    public int normalDays;
    public String rcode;
    public int unAppealNums;


    public static class ListBean {
        /**
         * attendance_date : 2017-07-01
         * signOut_address :
         * sign_address :
         * sign_endTime :
         * sign_startTime :
         * sign_status : 3
         */

        public String attendance_date;
        public String signOut_address;
        public String sign_address;
        public String sign_endTime;
        public String sign_startTime;
        public String sign_status;

        @Override
        public String toString() {
            return "ListBean{" +
                    "attendance_date='" + attendance_date + '\'' +
                    ", signOut_address='" + signOut_address + '\'' +
                    ", sign_address='" + sign_address + '\'' +
                    ", sign_endTime='" + sign_endTime + '\'' +
                    ", sign_startTime='" + sign_startTime + '\'' +
                    ", sign_status='" + sign_status + '\'' +
                    '}';
        }
    }
}
