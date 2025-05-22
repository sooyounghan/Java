package collection.map.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionaryTest {
    public static void main(String[] args) {
        Map<String, String> dictionary = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("== 단어 입력 단계 ==");
        while(true) {
            System.out.println("영어 단어를 입력하세요. (종료는 'q') :");
            String searchWordWord = scanner.nextLine();
            if(searchWordWord.equals("q")) {
                break;
            }

            System.out.println("한글 뜻을 입력하세요. : ");
            String koreanMeaning = scanner.nextLine();

            dictionary.put(searchWordWord, koreanMeaning);
        }

        System.out.println("== 단어 검색 단계 ==");
        while(true) {
            System.out.println("찾을 영어 단어를 입력하세요. (종료는 'q') :");
            String searchWord = scanner.nextLine();
            if(searchWord.equals("q")) {
                break;
            }

            if(dictionary.containsKey(searchWord)) {
                System.out.println(searchWord + "의 뜻 : " + dictionary.get(searchWord));
            } else {
                System.out.println(searchWord + "은(는) 사전에 없는 단어입니다.");
            }
        }
    }
}
