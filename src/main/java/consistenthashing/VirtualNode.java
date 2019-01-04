package consistenthashing;

/**
 * Virtual physicalNode containing reference to physical physicalNode.
 */
public class VirtualNode implements Node {

    PhysicalNode physicalNode;
    int replicationIndex;

    public VirtualNode(PhysicalNode physicalNode, int replicationIndex) {
        this.physicalNode = physicalNode;
        this.replicationIndex = replicationIndex;
    }

    public boolean isVirtualNodeOf(PhysicalNode node) {
        return this.physicalNode.getName().equals(node.getName());
    }

    @Override
    public String getName() {
        return physicalNode.getName() + replicationIndex;
    }

    public PhysicalNode getPhysicalNode() {
        return physicalNode;
    }
}
