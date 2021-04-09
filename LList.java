public class LList<K, V> {

	private Node<K, V> head;
	private int size;
	public LList() {
		head = null;
		size = 0;
	}
    public void put(K key, V value){
    	Node<K, V>cur = head;
    	while(cur != null) {
    		// if key already exists replace value
    		if(cur.key.equals(key)) {
    			cur.value = value;
    			return;
    		}
    		cur = cur.next;
    	}
    	// key is not on list - create it
    	size++;
    	if(head == null) {
    		head = new Node<K, V>(key, value, null);
    	} else {
    		head = new Node<K, V>(key, value, head);
    	}
    }
    public V get(K key){
    	Node<K, V>cur = head;
    	while(cur != null) {
    		if(cur.key.equals(key)) {
    			return cur.value;
    		}
    		cur = cur.next;
    	}
    	return null;
    }
    
    public K get(int i){
    	if(size==0) return null;
    	int curCnt = 0;
    	Node<K, V>cur = head;
    	while(cur != null) {
    		if(curCnt == i) return cur.key;
    		curCnt++;
    		cur=cur.next;
    	}
    	return null;
    }
    
    public int size(){
    	return size;
    }
    
    public Node<K, V>getHead(){
    	return head;
    }
}
