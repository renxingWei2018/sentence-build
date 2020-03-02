package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeModel class to Simulation results
 * 利用树形结构模拟结果的所有情况，结果保存在所有的树叶里
 * jdk1.8
 * tel: 15521394268
 * Created  by  renxingWei  on  2020/2/27
 **/
public class TreeModel {

    // 本节点未排序的剩余句子
    // The remaining unsorted sentences on this node
    private String currentRawString;
    // 本节点已经排序好的句子情况
    // This node has ordered the sentence situation
    private String currentEndString;
    // 子节点，树枝部分
    // Child nodes, branches
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
