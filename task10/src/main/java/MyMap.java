/**
 * Created by User on 02.06.2019.
 */

import one.util.streamex.EntryStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MyMap {

    public static <K,V> Map<? extends V, ? extends List<? extends K>> inverte(Map<? extends K,? extends V> map){
                return EntryStream.of(map)
                        .invert()
                        .grouping();
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
        map.put(6, "Anna");

        // print map details
        System.out.println("Map: " + map);
         System.out.println(inverte(map));

    }

}