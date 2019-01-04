package consistenthashing;

/**
 * Physical physicalNode instance.
 */
public class PhysicalNode implements Node {

    private String name;

    public PhysicalNode(String nodeName) {
        this.name = nodeName;
    }

    @Override
    public String getName() {
        return name;
    }
}
