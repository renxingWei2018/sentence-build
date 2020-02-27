import application.App;
import entity.Calculate;
import entity.CurrentStatus;

/**
 * Junit test class
 * 测试类
 *
 * Created  by  renxingWei  on  2020/2/26 0026
 **/

public class Test {


    // testNoUserDictionary
    // 测试没有用户输入字典情况
    @org.junit.Test
    public void testNoUserDictionary() {
        // raw data given
        String givenDictionary = "{i,like,sam,sung,mobile,samsung}";
        String userDictionary = null;
        boolean allDictionarWork = false;
        String yourSentenceWithoutSpace = "ilikesamsungmobile";
        App.printResult(getCalculateObj(givenDictionary,userDictionary,allDictionarWork,yourSentenceWithoutSpace));
    }

    @org.junit.Test
    // testWhithAllDictionary
    // 测试有用户输入字典且两个字典都使用的情况
    public void testWhithUserDictionary() {
        // raw data given
        String givenDictionary = "{i,like,sam,sung,mobile,samsung}";
        String userDictionary = "{i,like,sam,sung,man go,samsung}";
        boolean allDictionarWork = true;
        String yourSentenceWithoutSpace = "ilikesamsungmobile";
        App.printResult(getCalculateObj(givenDictionary,userDictionary,allDictionarWork,yourSentenceWithoutSpace));
    }

    @org.junit.Test
    // testWhithUserDictionary
    // 测试有用户输入字典且只是用用户字典情况
    public void aVoid() {
        // raw data given
        String givenDictionary = "{i,like,sam,sung,mobile,samsung}";
        String userDictionary = "{i,like,sam,sung,mobile,}";
        boolean allDictionarWork = false;
        String yourSentenceWithoutSpace = "ilikesamsungmobile";
        App.printResult(getCalculateObj(givenDictionary,userDictionary,allDictionarWork,yourSentenceWithoutSpace));
    }


    private Calculate getCalculateObj(String givenDictionary,String userDictionary,boolean allDictionarWork,String yourSentenceWithoutSpace){
        CurrentStatus currentStatus = new CurrentStatus(givenDictionary,userDictionary,allDictionarWork);
        currentStatus.setRawString(yourSentenceWithoutSpace);
        return new Calculate(currentStatus);
    }

}
