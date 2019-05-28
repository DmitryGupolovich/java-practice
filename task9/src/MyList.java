/*
Напишите метод, который добавляет 1000000 элементов в ArrayList и LinkedList.
Далее напишите метод, который выбирает из заполненного списка элемент наугад 100000 раз.
Замерьте время, которое потрачено на это. Сравните результаты и выведите.
*/

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyList {
    private List<Integer> list;

    public MyList(List<Integer> list) {
        this.list = list;
    }

    public void addList() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            list.add((int) (Math.random() * 2000000));
        }
        showInformation(System.nanoTime() - startTime, "insert");
    }

    public void findIndex() {
        int value = (int) (Math.random() * 2000000);
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            if ((list.get(i)) == value) {
                break;
            }
        }
        showInformation(System.nanoTime() - startTime, "search");
    }

    private void showInformation(long estimatedTime, @NotNull String whatPrint) {
        long finalTime = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS);
        String simpleName = list.getClass().getSimpleName();
        switch (whatPrint) {
            case "search": {
                System.out.println("Время поиска " + simpleName + " составило " + finalTime + "мс.");
                break;
            }
            case "insert": {
                System.out.println("Время заполнения " + simpleName + " составило " + finalTime + "мс.");
                break;
            }

        }
    }

    public static void main(String[] args) {
        MyList myList1 = new MyList(new ArrayList<>());
        myList1.addList();
        myList1.findIndex();

        MyList myList2 = new MyList(new LinkedList<>());
        myList2.addList();
        myList2.findIndex();

        System.gc();

    }
}
