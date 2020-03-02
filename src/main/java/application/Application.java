package application;

import entity.Calculate;
import entity.CurrentStatus;

import java.util.Scanner;

/**
 * App class to run code
 * tel: 15521394268
 * Created  by  renxingWei  on  2020/2/27
 **/
public class Application {

    public static void main(String[] args) {
        doMain(null);
    }

    public static void doMain(CurrentStatus auto) {
        if (auto != null) {
            Calculate calculate = new Calculate(auto);
            printResult(calculate);
            return;
        }
        Scanner in = new Scanner(System.in);
        String givenDictionary = "{i,like,sam,sung,samsung,mobile,ice,cream,man go}";
        String userDictionary = "{}";
        String alleffect = "";
        String rawSentence = "";
        boolean fist = true;
        while (true) {
            if (fist) {
                fist = false;
                System.out.println("请设置需求条件，即给定的词典，用户的词典以及没有空格的句子。");
                System.out.println("1、请首先输入给定的词典：如{i,love,you}。" + "\n" + "如果你不想输入，请直接按回车进入下一步，系统会默认给定的词典是{i,like,sam,sung,samsung,mobile,ice,cream,man go}");
                givenDictionary = getInfo(in, givenDictionary);
                System.out.println("2、是否需要设置用户词典？如需要请直接输入，如{i,love,you,too},如不需要请回车下一步。");
                userDictionary = getInfo(in, userDictionary);
                if (userDictionary.length() <= 0 || userDictionary.replace(" ", "").equals("{}")) {
                    alleffect = "n";
                } else {
                    System.out.println("3、你输入了你的用户词典，是否设定句子将在两个词典里搜索组成句子的单词？如需要，请输入y回车，否则输入n回车。");
                    alleffect = getInfo(in, alleffect);
                }
            } else {
                System.out.println("上次的给定词典是：" + givenDictionary);
                System.out.println("如需更改，请输入新的给定词典，如{i,love,you}，如不需要请回车跳过。");
                givenDictionary = getInfo(in, givenDictionary);
                System.out.println("上次的用户词典是：" + userDictionary);
                System.out.println("如需更改，请输入新的用户词典，如{i,love,you}，如不需要请回车跳过。");
                userDictionary = getInfo(in, userDictionary);
                if (userDictionary.length() <= 0) {
                    alleffect = "n";
                } else {
                    System.out.println("你的用户词典已改变，是否设定句子将在两个词典里搜索组成句子的单词？如需要，请输入y回车，否则输入n回车。");
                    alleffect = getInfo(in, alleffect);
                }
            }
            System.out.println("最后，请输入本次你需要处理的原始句子（不带空格的句子）");
            rawSentence = getInfo(in, rawSentence);
            while (rawSentence == null || rawSentence.length() <= 0) {
                System.out.println("输入错误请正确输入");
                rawSentence = getInfo(in, rawSentence);
            }
            System.out.println("---------------------数据采集完成----------------------");
            System.out.println("正在计算，请稍后...");
            CurrentStatus currentStatus = new CurrentStatus();
            currentStatus.setRawString(rawSentence);
            currentStatus.setRawGivenDictionaryString(givenDictionary);
            currentStatus.setRawUserDictionaryString(userDictionary);
            currentStatus.setTwoDictionaryEffect((alleffect != null && alleffect.toLowerCase().equals("y")) ? true : false);
            Calculate calculate = new Calculate(currentStatus);
            printResult(calculate);
            System.out.println("------------------------------------------");
            System.out.println("是否继续？继续请输入y，否去将结束程序！");
            if ("y".equals(in.nextLine())) {
                System.out.println("----------------重写设置条件-----------------");
            } else {
                break;
            }
        }
        in.close();
    }

    private static String getInfo(Scanner scanner, String target) {
        String in = scanner.nextLine();
        if (in == null || in.length() <= 0) {
            System.out.println("你选择不输入。");
        } else {
            System.out.println("你设置了：" + in);
            target = in;
        }
        return target;
    }

    public static void printResult(Calculate calculate) {
        System.out.println("（给定词典）The given dictionary is : " + calculate.getCurrentStatus().getRawGivenDictionaryString());
        System.out.println("（用户词典）The user dictionary is : " + calculate.getCurrentStatus().getRawUserDictionaryString());
        System.out.println("（最终在这个词典搜做单词）The work dictionary is : " + calculate.getEndUseDictionary());
        System.out.println("（需要处理的句子是）Your sentence without space is : " + calculate.getCurrentStatus().getRawString());
        System.out.println("(得到)We get " + calculate.getStringResult().size() + " sentence （分别是）:");
        for (String s : calculate.getStringResult()) {
            System.out.println(s);
        }
    }

}
