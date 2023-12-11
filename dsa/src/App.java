import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        var list = new LinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.reverse();
        var array = list.toArray();
        System.out.println(Arrays.toString(array));

    }
}
