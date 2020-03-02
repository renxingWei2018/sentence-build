package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CurrentStatus class for a sure environment,the status of the application
 * 程序的状态类
 * jdk1.8
 * tel:15521394268
 * Created  by  renxingWei  on  2020/2/27 0027
 **/
public class CurrentStatus {

    // 原始句子，没有空格的句子
    // Sentences without Spaces
    private String rawString;

    // 给定词典
    // Given dictionary
    private String rawGivenDictionaryString;

    // 用户附加的词典
    // User dictionary
    private String rawUserDictionaryString;

    // 是否全部起效,即是否在两个词典里匹配句子，默认是从给定的词典找
    // Is two Dictionary all work
    private boolean twoDictionaryEffect = false;

    public CurrentStatus() {
        this("{i,like,sam,sung,samsung,mobile,ice,cream,man go}");
    }

    public CurrentStatus(String rawGivenDictionaryString) {
        this(rawGivenDictionaryString, null);
    }

    public CurrentStatus(String rawGivenDictionaryString, String rawUserDictionaryString) {
        this(rawGivenDictionaryString, rawUserDictionaryString, false);
    }

    public CurrentStatus(String rawGivenDictionaryString, String rawUserDictionaryString, boolean twoDictionaryEffect) {
        this.rawUserDictionaryString = rawUserDictionaryString;
        this.rawGivenDictionaryString = rawGivenDictionaryString;
        this.twoDictionaryEffect = twoDictionaryEffect;
    }

    public String getRawString() {
        return rawString;
    }

    public void setRawString(String rawString) {
        this.rawString = rawString;
    }

    public List<String> getRawGivenDictionaryStringAsList() {
        String rawGivenDictionaryString = this.rawGivenDictionaryString.replace("{", "");
        rawGivenDictionaryString = rawGivenDictionaryString.replace("}", "");

        return  rawGivenDictionaryString.equals("")?
                new ArrayList<>():
                Arrays.asList(rawGivenDictionaryString.split(","));
    }

    public void setRawGivenDictionaryString(String rawGivenDictionaryString) {
        this.rawGivenDictionaryString = rawGivenDictionaryString;
    }

    public List<String> getRawUserDictionaryStringAsList() {
        if (this.rawUserDictionaryString == null)
            return  new ArrayList<>();
        String rawUserDictionaryString = this.rawUserDictionaryString.replace("{", "");
        rawUserDictionaryString = rawUserDictionaryString.replace("}", "");
        return rawUserDictionaryString.equals("")?
                new ArrayList<>():
                Arrays.asList(rawUserDictionaryString.split(","));
    }

    public void setRawUserDictionaryString(String rawUserDictionaryString) {
        this.rawUserDictionaryString = rawUserDictionaryString;
    }

    public boolean isTwoDictionaryEffect() {
        return twoDictionaryEffect;
    }

    public void setTwoDictionaryEffect(boolean twoDictionaryEffect) {
        this.twoDictionaryEffect = twoDictionaryEffect;
    }

    public String getRawGivenDictionaryString() {
        return rawGivenDictionaryString;
    }

    public String getRawUserDictionaryString() {
        return rawUserDictionaryString;
    }
}
