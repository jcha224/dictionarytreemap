package treemap;

import java.util.ArrayList;
import java.util.List;

public class TrieTree<AnyKey extends Comparable<? super AnyKey>, 
					AnyValue extends Comparable<? super AnyValue>> {
	
    private TrieNode<AnyKey, AnyValue> root;
 
    public TrieTree() {
        root = new TrieNode<AnyKey, AnyValue>(null, null);
    }
 
    // Inserts key-value into the trie.
    public void put(AnyKey[] keys, AnyValue value) {
    	TrieNode<AnyKey, AnyValue> t = searchNode(keys);
    	if(t != null) 
    		t.setEntry(value);
    	else {
	    	TrieNode<AnyKey, AnyValue> temp = root;
	    	for(int i = 0; i < keys.length; i++) {
	    		if(temp.containsKey(keys[i]) == null) {
    				temp.append(new TrieNode<AnyKey, AnyValue>(keys[i], null));
	    		}if(i == keys.length-1)
	    			temp.setEntry(value);
	    	}
    	}
    }
 
    // Returns if the key is in the trie.
    public AnyValue get(AnyKey[] akey) {
        TrieNode<AnyKey, AnyValue> t = searchNode(akey);
 
        if (t != null && t.isEntry) 
            return t.v;
        else
            return null;
    }
    
    // Returns the key value if it exists.
//    @SuppressWarnings("unchecked")
//	public AnyValue get(AnyKey key) {
//    	if(key == null) {
//    		return null;
//    	}
//    	int size = (key.toString()).length();
//    	AnyKey[] array = (AnyKey[]) new Character[size];
//    	for (int i = 0; i < size; i++) {
//    		array[i] = (AnyKey) new Character((key.toString()).charAt(i));
//    	}
//    	return get(array);
//    }
    
    // inserts key-value into the trie
    @SuppressWarnings("unchecked")
	public void put(AnyKey key, AnyValue value) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	int size = ((String)key).length();
    	AnyKey[] array = (AnyKey[]) new Character[size];
    	for (int i = 0; i < size; i++) {
    		array[i] = (AnyKey) new Character(((String)key).charAt(i));
    	}
    	put(array, value);
    }
    
    // Removes the node.
    @SuppressWarnings("unchecked")
	public void remove(AnyKey x) {
    	if(x == null) {
    		throw new IllegalArgumentException();
    	}
    	int size = ((String)x).length();
    	AnyKey[] array = (AnyKey[]) new Character[size];
    	for (int i = 0; i < size; i++) {
    		array[i] = (AnyKey) new Character(((String)x).charAt(i));
    	}
    	remove(array);
    }
    
    // Helper method to remove
    public void remove(AnyKey[] keys) {
    	if(searchNode(keys) == null) {
    		throw new IllegalArgumentException();
    	}
    	searchNode(keys).isEntry = false;
    }
 
    // returns the address of the node containing the end of the entry
    // null if entry is not in the trie
    private TrieNode<AnyKey, AnyValue> searchNode(AnyKey[] akey){
        
        TrieNode<AnyKey, AnyValue> t, node;
        t = node = root;
        AnyKey key;
        for(int i = 0; i < akey.length; i++){
            key = akey[i];
            t = node.containsKey(key);
            if (t == null)
            	return null;
            node = t;
        } 
        if (t.isEntry)
        	return t;
        else
        	return null;
    }
    
    // replace with toString method
    public String toString() {
        TrieNode<AnyKey, AnyValue> p = root;
         
        StringBuffer sb = new StringBuffer();
        printTrieHelper(p, sb);
        return sb.toString();
    }
     

    private void printTrieHelper(TrieNode<AnyKey, AnyValue> p, StringBuffer sb) {
        if (p == null) {
            return;
        }
         
        if (p.isEntry) {
            System.out.println(sb.toString() + p.v);
        }
         
        for (int i = 0; i < p.children.size(); i++) {
            if (p.children.get(i) != null) {
                char c = (char) p.children.get(i).k;
                sb.append(c);
                printTrieHelper(p.children.get(i), sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
    @SuppressWarnings("hiding")
	private class TrieNode<AnyKey, AnyValue> {
        AnyKey k;
        AnyValue v;
        boolean isEntry;
        List<TrieNode<AnyKey, AnyValue>> children;
     
        public TrieNode(AnyKey key, AnyValue val){
            this.k = key;
            this.v = val;
            this.isEntry = false;
            children = new ArrayList<TrieNode<AnyKey, AnyValue>>();
        }
        
        // checks if any of the children contain the key; returns the address of the child
        // if found, null otherwise
        public TrieNode<AnyKey, AnyValue> containsKey(AnyKey key) {
        	for (int i = 0; i < children.size(); i++)
        		if (children.get(i).k.equals(key))
        			return children.get(i);
        	return null;
        }
        
        public void append(TrieNode<AnyKey, AnyValue> newNode) {
        	children.add(newNode);
        }
        
        public void setEntry(AnyValue val) {
        	isEntry = true;
        	v = val;
        }
    }
}