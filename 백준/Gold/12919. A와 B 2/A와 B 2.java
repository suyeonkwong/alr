import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int result = 0;
    static String copy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        copy = br.readLine();
        StringBuilder str = new StringBuilder(br.readLine());

        Queue<String> queue = new LinkedList<>();
        queue.offer(str.toString());

        while (!queue.isEmpty()) {
            String poll = queue.poll();
//            System.out.println(poll);
            if(poll.equals(copy)) {
                result = 1;
                break;
            }

            if(poll.length() < copy.length()) {
                break;
            }

            /// A면 제거하고
            str.setLength(0);
            str.append(poll);
            if(str.charAt(str.length()-1) == 'A') {
                str.deleteCharAt(str.length()-1);
                queue.offer(str.toString());
            }

            /// 뒤집고 B면 제거한다?
            str.setLength(0);
            str.append(poll);
            str.reverse();
            if(str.charAt(str.length()-1) == 'B') {
                str.deleteCharAt(str.length()-1);
                queue.offer(str.toString());
            }
        }

        System.out.println(result);
    }

}
