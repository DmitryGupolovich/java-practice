import model.QueueImpl;

import java.util.function.Predicate;


public class Main {
    public static void main(String[] args) {
        QueueImpl<String> queue = new QueueImpl<>();
        queue.add("First");
        queue.add("Second");
        queue.add("Third");

        QueueImpl<String> queue2 = new QueueImpl<>();
        queue2.add("2First");
        queue2.add("2Second");
        queue2.add("2Third");

        queue.addAll(queue2);

        int indexOf = queue.indexOf(new Predicate<String>() {
           @Override
            public boolean test(String searchFor) {
                if (searchFor.contains("2Thirddddd"))
                return true;
                return false;
            }
        });

        System.out.println(indexOf);
        System.out.println("---------------------------");

        int findIndexx = queue.findWithLoop(new Predicate<String>() {
            @Override
            public boolean test(String searchFor) {
                if (searchFor.contains("2Third"))
                    return true;
                return false;
            }
        });
        System.out.println(findIndexx);

    }
}
