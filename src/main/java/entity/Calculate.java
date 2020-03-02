package entity;

import java.util.*;

/**
 * Calculate class for getting the result
 * 计算类,用于推算出所有的句子
 * jdk1.8
 * tel: 15521394268
 * Created  by  renxingWei  on  2020/2/27   15521394268
 **/
public class Calculate {

    // dataSource , given string for calculating
    // 原始数据，即给定的词典，用户词典，以及原始没有空格的句子
    private CurrentStatus currentStatus;
    private Set<String> rawGivenDictionary = new HashSet<>();
    private Set<String> rawUserDictionary = new HashSet<>();

    // data use to calculate
    // 初步处理的原始数据
    private Set<String> endUseDictionary = new HashSet<>();
    private TreeModel modelResult = new TreeModel();

    // the result of sentences
    // 最终结果的集合
    private List<String> stringResult = new ArrayList<>();

    // the public interface to get the result
    // 封装后向外提供的获取最终结果的方法
    public List<String> getStringResult() {
        return stringResult;
    }


    // 获取结果
    private void fillResult() {
        List<TreeModel> models = new ArrayList<>();
        models.add(modelResult);
        this.getSentence(models);
    }

    // 递归获取句子
    //Recursively analyze the data and get the sentences
    private void getSentence(List<TreeModel> treeModels) {
        for (TreeModel treeModel : treeModels) {
            if (treeModel.getNext().size() > 0) {
                // 递归查找
                getSentence(treeModel.getNext());
            } else {
                // 添加一条结果
                // add one to result
                stringResult.add(treeModel.getCurrentEndString());
            }
        }
    }

    // 递归分析数据并得出树形结果
    //Recursively analyze the data and get the tree results
    private void fillTreeResult(List<TreeModel> next) {
        for (TreeModel treeModel : next) {
            String innerCurrentRawString = String.valueOf(treeModel.getCurrentRawString());
            String innerCurrentEndString = String.valueOf(treeModel.getCurrentEndString());
            // 只想核心判断
            List<TreeModel> nextTree = this.nextTree(innerCurrentRawString, innerCurrentEndString);
            if (nextTree.size() > 0) {
                // 递归处
                fillTreeResult(nextTree);
            }
            treeModel.setNext(nextTree);
        }
    }

    // the core code to calculate
    private List<TreeModel> nextTree(String currentRawString, String currentEndString) {
        List<TreeModel> result = new ArrayList<>();
        if (currentRawString.length() < 1) {
            // 已经到尾
            return result;
        }

        // 记录词典在句子出现的位置以及单词可能的组合情况
        Map<Integer, Set<String>> mik = new HashMap<>();
        for (String s : this.endUseDictionary) {
            if (currentRawString.contains(s.replace(" ", ""))) {
                // Find the index position
                // 找到句子中的对应下标并记录
                int index = currentRawString.indexOf(s.replace(" ", ""));
                Set<String> mse = mik.get(index);
                if (mse == null) {
                    mse = new HashSet<>();
                }
                boolean ifContains = false;
                for (String s1 : mse) {
                    if (s1.contains(s)) {
                        ifContains = true;
                    }
                }
                // 如果此处已经包含有其他情况，但本次的是包含已存在的情况，如samsung包含sam
                if (!ifContains) {
                    Set<String> copy = new HashSet<>();
                    for (String s1 : mse) {
                        if (s.contains(s1)) {
                            copy.add(s1);
                        }
                    }
                    Set<String> copy2 = new HashSet<>();
                    for (String s1 : copy) {
                        // 如果能找到另外一个组合和本子的词语可以组成不同情形
                        for (String s2 : endUseDictionary) {
                            if (!s2.equals(s) && s.contains((s1 + s2))) {
                                copy2.add(s1);
                            }
                        }
                    }
                    copy.removeAll(copy2);
                    mse.removeAll(copy);
                    mse.add(s);
                } else {
                    boolean stop = false;
                    //String target = null;
                    for (String s1 : endUseDictionary) {
                        if (stop) {
                            break;
                        }
                        for (String s2 : mse) {
                            if (stop) {
                                break;
                            }
                            if (s2.contains(s + s1)) {
                                //target = s;
                                stop = true;
                            }
                        }
                    }
                    if (stop) {
                        mse.add(s);
                    }

                }
                mik.put(index, mse);
            }
        }
        String subRawString = String.valueOf(currentRawString);
        String subEndString = String.valueOf(currentEndString);
        if (mik.size() < 1) {
            // 如果没有找到，将后续的视为多余的整体
            TreeModel t = new TreeModel();
            t.setCurrentRawString("");
            t.setCurrentEndString(subEndString + " " + subRawString);
            result.add(t);
            return result;
        }
        // 找出最小,最靠近前面的下标
        // find out the min
        int min = currentRawString.length();
        for (Integer integer : mik.keySet()) {
            if (integer < min) {
                min = integer;
            }
        }
        if (min > 0) {
            // 前面有找不到的
            subEndString = subEndString + " " + subRawString.substring(0, min);
        }
        for (String strings : mik.get(min)) {
            TreeModel treeModel = new TreeModel();
            treeModel.setCurrentEndString(subEndString + " " + strings);
            treeModel.setCurrentRawString(subRawString.substring(min + (strings.replace(" ", "")).length(), subRawString.length()));
            result.add(treeModel);
        }
        return result;
    }

    public Calculate(CurrentStatus currentStatus) {
        this.currentStatus = currentStatus;
        this.createEndUseDictionary();
    }

    // init data
    // 设置初始化数据，组装容易处理的数据
    private void createEndUseDictionary() {
        this.rawGivenDictionary.addAll(this.currentStatus.getRawGivenDictionaryStringAsList());
        this.rawUserDictionary.addAll(this.currentStatus.getRawUserDictionaryStringAsList());
        if (this.rawGivenDictionary == null) {
            throw new RuntimeException("the givenDictionary is null!");
        }

        // Choose work dictionary
        //确定哪个词典有效
        if (this.currentStatus.isTwoDictionaryEffect()) {
            this.endUseDictionary.addAll(this.rawUserDictionary);
            this.endUseDictionary.addAll(this.rawGivenDictionary);
        } else {
            if (this.rawUserDictionary.size() == 0) {
                this.endUseDictionary.addAll(this.rawGivenDictionary);
            } else {
                this.endUseDictionary.addAll(this.rawUserDictionary);
            }
        }
        Set<String> sentenceContains = new HashSet<>();
        for (String s : this.endUseDictionary) {
            if (this.currentStatus.getRawString().contains(s.replace(" ", ""))) {
                sentenceContains.add(s);
            }
        }
        // 初始化树根节点
        this.endUseDictionary = sentenceContains;
        this.modelResult.setCurrentEndString("");
        this.modelResult.setCurrentRawString(this.currentStatus.getRawString());
        List<TreeModel> root = new ArrayList<>();
        root.add(this.modelResult);
        // core code
        // 核心步骤
        this.fillTreeResult(root);
        this.fillResult();
    }

    public CurrentStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(CurrentStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Set<String> getEndUseDictionary() {
        return endUseDictionary;
    }

    public void setEndUseDictionary(Set<String> endUseDictionary) {
        this.endUseDictionary = endUseDictionary;
    }
}
