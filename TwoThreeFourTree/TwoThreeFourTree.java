/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Buser
 */
import java.util.ArrayList;
import java.util.Collections;

public class TwoThreeFourTree 
{
    public Node root;
    
    public TwoThreeFourTree()
    {
        root = new Node();
        root.isRoot = true;
    }
    
    public void insert(int k)
    {
       root.insert(k); 
    }
    
    class Node
    {
        public ArrayList <Integer> keys;
        public ArrayList <Node> children;
        Boolean leaf = true;
        int n = keys.size();
        Boolean isRoot = false;
        int promotion;
        
        public Node(){}
        
        private Node insert(int k)
        {
            if(leaf && n<3)
            {
                keys.add(k);
                Collections.sort(keys);
                return this;
            }
            if(n==3&&leaf)
            {
                Node temp = this.split();
                if(temp.isRoot)
                {
                    temp.insert(k);
                    return temp;
                }
                else
                {
                    return temp;
                }
                
  
            }
            if(!leaf)
            {
                if(this.keys.contains(k))
                {
                    return  this;
                }
                int temp = 0;
                for(int i=0; i<this.n;i++)
                {
                    if(k<this.keys.get(i))
                    {
                        this.children.get(temp).insert(k);
                    }
                    temp++;
                }
                Node promo = this.children.get(temp).insert(k);
                this.keys.add(promo.promotion);
                this.children.add(promo);
                        
                    
            }
            return null;
        }
        
        private Node split()
        {
            if(this.isRoot && this.children.isEmpty())
            {
                Node l = new Node();
                Node r = new Node();
                l.keys.add(this.keys.remove(0));
                r.keys.add(this.keys.remove(2));
                this.children.add(l);
                this.children.add(l);
                this.leaf = false;
                return this;
            }
            else
            {
                Node temp = new Node();
                temp.promotion = this.keys.remove(1);
                temp.keys.add(this.keys.remove(2));
                return temp;
            }
            
            //return null;
            
        }
        
    }
}
