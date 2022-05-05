import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple skiplist implementation which realises a IDictionary interface.
 * The skiplist stores key-values pairs with Integer keys and any comparable
 * type for the value.
 */
// COMPILES AND EXECUTES CORRECTLY: 15 MARKS
//SKIPLIST CLASS TOTAL: 25 MARKS
public class SkipList<K, V> implements IDictionary<K ,V> {

    /** The top left node **/
    protected SLNode<SkipEntry<K,V>> start;
    /** The number of items in the skip list **/
    protected int size;
    /** The height of the tallest tower in the list **/
    protected int height;
    /** A comparator to compare Integer keys **/
    protected Comparator<K> comp;
    /** A comparator to compare values **/
    protected Comparator<V> compValue;
    /** A random object for the coinflip **/
    protected Random rand;

    /** Maximum key **/
    protected final static int MAX = 2147483647;
    /** Minimum key **/
    protected final static int MIN = -1;

    /**
     * DEfault comparator, used to compare keys/values
     * @param <T> The type for the comparator
     */
    protected static class DefaultComparator<T> implements Comparator<T> {
        public DefaultComparator() { /* default constructor */ }

        public int compare(T a, T b) throws ClassCastException {
            if (a == null || b == null) {
                return -1;
            }
            return ((Comparable) a).compareTo(b); // use the natural order for a
        }
    }

    /**
     * An Inner Class to represent entries in the skiplist, Integer keys are used
     * @param <V> The type of the value
     */
    protected class SkipEntry<K, V> implements IEntry<K, V> {
        private K key;
        private V value;
        private Integer sentinel = 0;

        /**
         * Default Constructor
         * @param key the key
         * @param value the value
         */
        public SkipEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public SkipEntry(K key, V value, Integer sentinel) {
            this.key = key;
            this.value = value;
            this.sentinel = sentinel;
        }

        public Integer getSentinel() {
            return sentinel;
        }

        public void setSentinel(Integer sentinel) {
            this.sentinel = sentinel;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }

    /**
     * Default constructor
     */
    public SkipList() {
        start = new SLNode<SkipEntry<K,V>>(null, null, null, null, new SkipEntry<K, V>(null, null, MIN));
        SLNode end = insertAfterAbove(start, null, new SkipEntry<K,V>(null, null, MAX));
        size = 0;
        height = 0;
        comp = new DefaultComparator<K>();
        compValue = new DefaultComparator<V>();
        rand = new Random();
    }

    /**
     * Return the size of the skiplist
     * @return the size of the skiplist
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the skiplist is empty
     * @return true is the skiplist is empty
     */
    public boolean isEmpty() {
        return (size == 0);
    }

   /**
    * Search the Skiplist for a key
    * @param key the key to search for
    * @return the position containting the key
    */
    protected SLNode<SkipEntry<K,V>> searchSkip(K key) {
        SLNode<SkipEntry<K,V>> pos = start;
	//TODO: COMPLETE - 5 MARKS       
    }

    /**
     * Find the entry with the specified key
     * @param key the key to locate
     * @return the entry containing the key
     * @throws InvalidKeyException if the key is invalid
     */
    public IEntry<K, V> find(K key) throws InvalidKeyException {
        SkipEntry<K, V> entry = searchSkip(key).element();
        if (entry == null)
            return null;
        if (comp.compare(entry.getKey(), key) != 0) {
            return null;
        }
        return entry;
    }

    /**
     * Returns an interable list of all the entries containing the specified key
     * @param key the key to locate
     * @return an iterable list containing the keys
     * @throws InvalidKeyException if the key is invalid
     */
    public Iterable<IEntry<K, V>> findAll(K key) throws InvalidKeyException {
        ArrayList<IEntry<K, V>> list = new ArrayList<IEntry<K, V>>();

        SLNode<SkipEntry<K,V>> found = searchSkip(key);
        SkipEntry<K,V> foundElement = found.element();

        if (found == null) {
           //we did not find the key
            return null;
        }
        if (comp.compare(foundElement.getKey(), key) != 0) {
            //we did not find a node containing the key
            return null;
        }

        //found key - add it to the list
        list.add(foundElement);

        //search backwards to find duplicate keys
        SLNode<SkipEntry<K,V>> prev = found.getPrev();
        while (prev != null) {
            SkipEntry<K,V> prevEntry = prev.element();
            if (comp.compare(prevEntry.getKey(), key) == 0) {
                list.add(prevEntry);
                prev = prev.getPrev();
            } else {
                break;
            }
        }

        SLNode<SkipEntry<K,V>> next = found.getNext();
        while (next != null) {
            SkipEntry<K,V> nextEntry = next.element();
            if (comp.compare(nextEntry.getKey(), key) == 0) {
                list.add(nextEntry);
                next = next.getNext();
            } else {
             break;
            }
        }

        return list;
    }

    /**
     * Insert a new node after the before node and above the below node. The
     * pointers for before and below must be adjusted accordingly
     * @param before the node to insert after
     * @param below the node to insert above
     * @param element the element to insert
     * @return a SLNOde<SkipEntry<K,V>> containting the new element
     */
    protected SLNode<SkipEntry<K,V>> insertAfterAbove(SLNode<SkipEntry<K,V>> before,
        SLNode<SkipEntry<K,V>> below, SkipEntry<K,V> element) {

        //TODO: COMPLETE - 5 MARKS
    }

    /**
     * Insert a new node into the skiplist
     * @param key the key of the new item
     * @param value the value of the new item
     * @return The entry for the new item in the skiplist
     * @throws InvalidKeyException is not thrown
     */
    public IEntry<K, V> insert(K key, V value) throws InvalidKeyException {
		//TODO: COMPLETE - 10 MARKS

	return newElem;
    }

    /**
     * Removes a node from the skip list
     * @param key the key to remove
     * @param value the value to remove
     * @return the item to remove
     */
    public IEntry<K, V> remove(K key, V value) {
        return remove(new SkipEntry<K,V>(key,value));
    }

    /**
     *  Removes a node from the skiplist
     * @param e An Entry object containing the key and value of the object to remove
     * @return The removed object
     * @throws InvalidEntryException is not thrown
     */
    public IEntry<K, V> remove(IEntry<K, V> e) throws InvalidEntryException {
      SLNode<SkipEntry<K,V>> p = searchSkip(e.getKey());
        SkipEntry<K,V> elem = p.element();

        //key in found object is less then the specified key
        if (e == null || (comp.compare(e.getKey(), elem.getKey())<0)) {
            return null;
        }

        //key is greater then or equal
        // search backwards in list to locate key
        if (comp.compare(e.getKey(), elem.getKey()) > 0) {
            SLNode<SkipEntry<K,V>> prev = p.getPrev();
            while (prev != null) {
                SkipEntry<K,V> prevEntry = prev.element();
                if (comp.compare(e.getKey(), prevEntry.getKey()) == 0) {
                    if (compValue.compare(e.getValue(), prevEntry.getValue()) == 0) {
                        p = prev;
                        break;
                    }
                    prev = prev.getPrev();
                } else {
                    break;
                }
            }
        }

        //search forward
        SLNode<SkipEntry<K,V>> next = p.getNext();
        while (next != null) {
            SkipEntry<K,V> nextEntry = next.element();
            if (comp.compare(nextEntry.getKey(), e.getKey()) == 0) {
                if (compValue.compare(nextEntry.getValue(), e.getValue()) == 0) {
                    p = next;
                    break;
                }
                next = next.getNext();
            } else {
                break;
            }
        }

        //remove the entry located at p and the tower
        SkipEntry<K,V> toRet = p.element();
        size--;
        removeAbove(p);
        return toRet;
    }

    /**
     * Removes the node and the associated tower
     * @param p the node to remove
     */
    private void removeAbove(SLNode<SkipEntry<K,V>> p) {
        SLNode<SkipEntry<K,V>> nodeToRemove = p;
        if (nodeToRemove != null) {
            removeAbove(nodeToRemove.getAbove());

            SLNode<SkipEntry<K,V>> below = p;
            SLNode<SkipEntry<K,V>> above = nodeToRemove.getAbove();
            SLNode<SkipEntry<K,V>> before = nodeToRemove.getPrev();
            SLNode<SkipEntry<K,V>> after = nodeToRemove.getNext();

            if (below != null) {
                below.setAbove(above);
            }
            if (above != null) {
                above.setBelow(below);
            }
            if (before != null) {
                before.setNext(after);
            }
            if (after != null) {
                after.setPrev(before);
            }

        }

    }

    /**
     * Return an iterable list of all the items in the SkipList
     * @return an Iterator<IEntry<K,V>> of all the items in the skip list
     */
    public Iterator<IEntry<K, V>> entries() {
        SLNode<SkipEntry<K,V>> current = start;

       	// TODO: COMPLETE - 5 MARKS
    }

}
