import model.QueueImpl;

import java.util.function.Predicate;


public class Main {
    public static void main(String[] args) {
        QueueImpl<String> queue = new QueueImpl<String>();
        queue.add("First");
        queue.add("Second");
        queue.add("Third");

        QueueImpl<String> queue2 = new QueueImpl<String>();
        queue2.add("2First");
        queue2.add("2Second");
        queue2.add("2Third");

        queue.addAll(queue2);

        String indexOf = queue.indexOf(new Predicate<String>() {
            public boolean test(String searchFor) {
                if (searchFor.contains("lol"))
                return true;
                return false;
            }
        });
        System.out.println(indexOf);
        System.out.println("---------------------------");

        while (!queue.isEmpty()){
            String str = queue.pop();
            System.out.print(str);
            System.out.print(" ");
        }




        System.out.println("\nafter pop "+queue.size());


    }

}
