package cn.huihongcloud.entity;

public class WorkerStatic implements Comparable<WorkerStatic> {
    private String workerName;
    private int num;
    private String currentDate;

    public String getWorkerName() {
        return workerName;
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
