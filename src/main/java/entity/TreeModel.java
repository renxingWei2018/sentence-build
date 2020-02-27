package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeModel class to Simulation results
 * 利用树形结构模拟结果的所有情况
 * jdk1.8
 * Created  by  renxingWei  on  2020/2/27
 **/
public class TreeModel {

    private String currentRawString;
    private String currentEndString;
    private List<TreeModel> next = new ArrayList<>();

    public String getCurrentRawString() {
        return currentRawString;
    }

    public void setCurrentRawString(String currentRawString) {
        this.currentRawString = currentRawString;
    }

    public String getCurrentEndString() {
        return currentEndString;
    }

    public void setCurrentEndString(String currentEndString) {
        this.currentEndString = currentEndString;
    }

    public List<TreeModel> getNext() {
        return next;
    }

    public void setNext(List<TreeModel> next) {
        this.next = next;
    }
}
