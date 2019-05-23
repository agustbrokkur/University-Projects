package d3;

import d3.BST.Node;
import edu.princeton.cs.algs4.StdOut;

public class NonRecursivePut {

	//F�ra �etta yfir � BST.java skr� og yfirskrifa gamla recursive put() falli�---
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calledput() with a null key");
        if (val == null) {
            delete(key);
            return;
        }
        
        //Ef r�tin er n�ll fer �etta put() gildi bent � r�tina
        Node x = root;
        if(x == null) {
        	root = new Node(key, val, 1);
        	return;
        }
        
        //�ar sem x getur enda� � null n��u �arf a� geyma eina n��u
        //sem geymir gildi� sem x var �, ��ur en fari� var �r lykkjuni
        Node temp = null;
        while(x != null) {
        	temp = x;
            //temp.size = 1 + size(temp.left) + size(temp.right);
            x.size++;
        	int cmp = key.compareTo(x.key);
        	//N�sta umfer� �� fer x � gildi� til vinstri
            if      (cmp < 0) {
            	x = x.left;
            }
        	//N�sta umfer� �� fer x � gildi� til h�gri
            else if (cmp > 0) {
            	x = x.right;
            }
        	//cmp er 0, �ar a� lei�andi erum vi� � sama key
            //�� �arf bara a� yfirksrifa val hj� x � n�ja val
            else {
            	x.val = val;
				return;
            }
        }

        //G�um � n��uni ��ur en x var null
        //finnum svo �t ef � a� gera n�ju n��una vinstra e�a h�gra meginn
    	int cmp = key.compareTo(temp.key);
        if      (cmp < 0) temp.left = new Node(key, val, 1);
        else			  temp.right = new Node(key, val, 1);
        
        assert check();
    }
    //---
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST<String, Integer> thing = new BST<String, Integer>();
		thing.put("First", 1);
		thing.put("Second", 2);
		thing.put("Third", 3);
		thing.put("Fourth", 4);
		thing.put("Fifth", 5);
		thing.put("Sixth", 6);
		thing.put("Seventh", 7);
		thing.put("Eight", 8);
		thing.put("Nineth", 9);
		StdOut.println(thing.size());
		for (String word : thing.keys()) {
			StdOut.println(word);
		}
	}

}
