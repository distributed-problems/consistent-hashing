package consistenthashing;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Demo class which utilizes consistent hash map to test data.
 */
public class Demo {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Map<String, Long> countMap = new HashMap<>();

        PhysicalNode A = new PhysicalNode("A");
        PhysicalNode B = new PhysicalNode("B");
        PhysicalNode C = new PhysicalNode("C");
        PhysicalNode D = new PhysicalNode("D");

        ConsistentHashRouter router = new ConsistentHashRouter(Arrays.asList(A, B, C, D), 100000);

        for(int i=0; i<10000; i++) {
            String data = UUID.randomUUID().toString();
            PhysicalNode pNode = router.get(data);
            countMap.putIfAbsent(pNode.getName(), 0L);
            countMap.put(pNode.getName(), countMap.get(pNode.getName()) + 1);
            System.out.println(String.format("%s is in Server %s", data, pNode.getName()));
        }

        countMap.entrySet().forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
    }
}
