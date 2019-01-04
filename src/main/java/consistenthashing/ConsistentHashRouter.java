package consistenthashing;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Consistent Hashing Router.
 */
public class ConsistentHashRouter {

    private SortedMap<Long, VirtualNode> ring;
    private HashFunction hashFunction;

    /**
     * Constructor with list of physical nodes and replication count.
     *
     * @param physicalNodes
     * @param replicationCount
     * @throws NoSuchAlgorithmException
     */
    public ConsistentHashRouter(List<PhysicalNode> physicalNodes, int replicationCount)
            throws NoSuchAlgorithmException {
        ring = new TreeMap<>();
        hashFunction = new MD5Hash();

        for(PhysicalNode node : physicalNodes) {
            addPhysicalNode(node, replicationCount);
        }

    }

    /**
     * Method to add new physical node with given replication count.
     * @param physicalNode
     * @param replicationCount
     */
    public void addPhysicalNode(PhysicalNode physicalNode, int replicationCount) {
        for(int i=0; i<replicationCount; i++) {
            VirtualNode vnode = new VirtualNode(physicalNode, i);
            ring.put(hashFunction.hash(vnode.getName()), vnode);
        }
    }

    /**
     * Method to remove given physical physicalNode. It iterates over all virtual physicalNode
     * and removes all virtual nodes belonging to given physical physicalNode.
     * @param node
     */
    public void removePhysicalNode(PhysicalNode node) {
        ring.entrySet().removeIf(entry -> entry.getValue().isVirtualNodeOf(node));
    }


    /**
     * Method to get physical node for given data.
     * @param data
     * @return
     */
    public PhysicalNode get(String data) {
        long hash = hashFunction.hash(data);

        SortedMap<Long, VirtualNode> tailMap = ring.tailMap(hash);
        Long key = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        return ring.get(key).getPhysicalNode();
    }

}
