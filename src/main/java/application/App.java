package application;

import entity.Calculate;
import entity.CurrentStatus;

import java.util.Scanner;

/**
 * App class to run code
 * tel: 15521394268
 * Created  by  renxingWei  on  2020/2/27
 **/
public class App {

    public static void main(String[] args) {
        //CurrentStatus currentStatus = new CurrentStatus();
        CurrentStatus currentStatus = new CurrentStatus(
                "{i,like,sam,sung,samsung,mobile,ice,cream,man go}",
                "{i,like,sam,sung,mobile,icecream,man go,mango}",
                true);
        //String rawString = "ilikeicecreamandmango";
        String rawString = "ilikesamsungmobile";
        currentStatus.setRawString(rawString);
        Calculate calculate = new Calculate(currentStatus);
        printResult(calculate);
    }

    public static void printResult(Calculate calculate){
        System.out.println("The given dictionary is : " + calculate.getCurrentStatus().getRawGivenDictionaryString());
        System.out.println("The user dictionary is : " + calculate.getCurrentStatus().getRawUserDictionaryString());
        System.out.println("The work dictionary is : " + calculate.getEndUseDictionary() );
        System.out.println("Your sentence without space is : " + calculate.getCurrentStatus().getRawString() );
        System.out.println("we get " + calculate.getStringResult().size()+" sentence :");
        for (String s : calculate.getStringResult()) {
            System.out.println(s);
        }
    }

}
