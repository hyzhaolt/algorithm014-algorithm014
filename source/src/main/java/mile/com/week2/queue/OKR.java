package mile.com.week2.queue;

/**
 * Created by zhaofengying on 2020/8/13.
 */
public class OKR implements Comparable<OKR>{
    /**
     * 年份
     */
    private String year;
    /**
     * 类型 0:年度  1:季度  2:月度
     */
    private Integer type;
    /**
     * 固定类型内部排序 从1开始
     */
    private Integer innerOrder;
    /**
     * 当前用户名
     */
    private String userName;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getInnerOrder() {
        return innerOrder;
    }

    public void setInnerOrder(Integer innerOrder) {
        this.innerOrder = innerOrder;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int compareTo(OKR o){
        if(null == o){
            o = new OKR();
        }

        String currentValue = this.getValue();
        String targetValue = o.getValue();

        return currentValue.compareToIgnoreCase(targetValue);
    }

    private String getValue(){

        String currValue = userName
                +"_" + type
                + "_" + year
                + "_" + innerOrder;

        return currValue;
    }

    @Override
    public String toString(){
        return this.getValue();
    }

}
