/**
 * Created by User on 02.06.2019.
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class MyMap {

    /*
    * без метода compute хотел попробовать. Перерыл интернет и видимо не понимаю, почему нельзя сделать
    * метод так, как сейчас у меня.
    * Если не дойду мыслями то сделаю как писали на занятии, создавая свой собственный ArrayList.
    * */
    public static <K,V> HashMap<V, Collection<K>> inverte(Map<? extends K,? extends V> map){

        HashMap<V, Collection<K>> output =
                map.entrySet().stream().collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.groupingBy(Map.Entry::getKey,
                                Collectors.toList())
                        )
                );
        return output;
    }

    public static void main(String[] args) {
        // {1=Dima, 2=Kate, 3=Dima, 4=Anna, 5=Stas}
        // Create a Map and add some values
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Dima");
        map.put(2, "Kate");
        map.put(3, "Dima");
        map.put(4, "Anna");
        map.put(5, "Stas");

        // print map details
        System.out.println("Map: " + map);
      //  System.out.println(inverte(map));

    }

}