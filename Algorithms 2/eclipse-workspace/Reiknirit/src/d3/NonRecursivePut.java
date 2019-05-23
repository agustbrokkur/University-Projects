package d3;

import d3.BST.Node;
import edu.princeton.cs.algs4.StdOut;

public class NonRecursivePut {

	//Færa þetta yfir í BST.java skrá og yfirskrifa gamla recursive put() fallið---
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calledput() with a null key");
        if (val == null) {
            delete(key);
            return;
        }
        
        //Ef rótin er núll fer þetta put() gildi bent í rótina
        Node x = root;
        if(x == null) {
        	root = new Node(key, val, 1);
        	return;
        }
        
        //Þar sem x getur endað á null nóðu þarf að geyma eina nóðu
        //sem geymir gildið sem x var á, áður en farið var úr lykkjuni
        Node temp = null;
        while(x != null) {
        	temp = x;
            //temp.size = 1 + size(temp.left) + size(temp.right);
            x.size++;
        	int cmp = key.compareTo(x.key);
        	//Næsta umferð þá fer x í gildið til vinstri
            if      (cmp < 0) {
            	x = x.left;
            }
        	//Næsta umferð þá fer x í gildið til hægri
            else if (cmp > 0) {
            	x = x.right;
            }
        	//cmp er 0, þar að leiðandi erum við á sama key
            //Þá þarf bara að yfirksrifa val hjá x í nýja val
            else {
            	x.val = val;
				return;
            }
        }

        //Gáum í nóðuni áður en x var null
        //finnum svo út ef á að gera nýju nóðuna vinstra eða hægra meginn
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
