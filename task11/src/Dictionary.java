/**
 * Created by User on 02.06.2019.
 */

import java.util.HashMap;

public class Dictionary {
    public static boolean checkSymbol(char outerchar) {
        if (((outerchar >= 'a') && (outerchar <= 'z')) || ((outerchar >= 'A') && (outerchar <= 'Z'))) {
            // символ принадлежит к английскому алфавиту
            return true;
        }
        return false;
    }

    public static StringBuilder StringBuilderLowerCase(StringBuilder pText) {
        StringBuilder pTextLower = new StringBuilder(pText);
        for (int idx = 0; idx < pText.length(); idx++) {
            char c = pText.charAt(idx);
            if (c >= 65 && c <= 65 + 27) {
                pTextLower.setCharAt(idx, (char) ((int) (pText.charAt(idx)) | 32));
            }
        }
        return pTextLower;
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("The BthlaugranA");// +  board, however,
//                                                        " are not willing to rush a decision, while another candidate for the dugout," +
//                                                        " Ronald Koeman, didn't rule out the possibility of coaching Barcelona." +
//                                                    "Правление Blaugrana, однако," +
//                                                    " не желает торопиться с решением, в то время как другой кандидат на блиндаж," +
//                                                    " Рональд Куман, не исключил возможности тренировать «Барселону».");
        sb = StringBuilderLowerCase(sb);
        System.out.println(sb);
        System.out.println(" ");
        HashMap<String, Integer> hashMap = new HashMap<>();

        int index = sb.length() - 1;

        while (index >= 0) {
            int frequence = 0;
            System.out.println(sb.toString());
            char innerchar = sb.charAt(index);
            if (checkSymbol(innerchar)) { // Если символ, который мы ищем соответствует условию...
                for (int i = index; i >= 0; i--) {
                    char outerchar = sb.charAt(i);
                    if (checkSymbol(outerchar)) {
                        if (innerchar == outerchar) {
                            sb.setCharAt(i, ' ');
                            frequence++;
                        }
                    }
                }
                hashMap.put(Character.toString(innerchar), frequence);
            }
            sb.deleteCharAt(index);
            --index;
        }
        System.out.println(hashMap);


    }
}
