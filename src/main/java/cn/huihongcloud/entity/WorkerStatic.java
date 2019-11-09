package cn.huihongcloud.entity;

public class WorkerStatic implements Comparable<WorkerStatic> {
    private String workerName;
    private int num;
    private String currentDate;
    private int num1;
    private int num2;
    private int num3;
    public String getWorkerName() {
        return workerName;
    }

    public int getNum3() {
        return num3;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public int compareTo(WorkerStatic user) {
        return this.workerName.compareTo(user.workerName);
    }

}
