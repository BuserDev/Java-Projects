//helped with code from stackoverflow.com and  http://algs4.cs.princeton.edu/

public class Quicksort {
	
	 private static long compareCount = 0;

    
    public Quicksort() {
        
    }

    static int[] qs1(int[] a, int lo, int hi) 
    { 
    	if(lo<hi)
        {
            int j = partition(a, lo,hi);
            qs1(a, lo, j-1);
            qs1(a, j+1, hi);
        }
    	return a;
    }

    private static int partition(int [] a, int lo, int hi)
    {
        int x = a[hi];
        int i = lo - 1;
        int temp = 0;
        for ( int j = lo;j < hi-1; j++)
        {
        	compareCount++;
        	if (a[j]<= x)
        	{
        		i++;
        		temp = a[i];
        		a[i] = a[j];
        		a[j] = temp;
        	}
        }
        temp = a[i+1];
        a[i+1] = a[hi];
        a[hi] = temp;
        
        return (i+1);   
     }

	public void reset() 
	{
		compareCount = 0;
		
	}

	public int[] populate(int i) {
		int[] a = new int[i];
		for (int j = 0; j<i; j++)
		{
			a[j] = (int) (Math.random()*10);
		}
		return a;
	}

	public long getPartCount() {
		
		long i = compareCount;
		return i;
	}

	public int select(int[] arr, int a, int b, int c) {
		
		if (arr[a] > arr[b]) 
		{
			  if (arr[b] > arr[c]) 
			  {
			    return b;
			  } 
			  else if (arr[a] > arr[c]) 
			  {
			    return c;
			  } 
			  else 
			  {
			    return a;
			  }
		} 
		else 
		{
			  if (arr[a] > arr[c])
			  {
			    return a;
			  } 
			  else if (arr[b] > arr[c]) 
			  {
			    return c;
			  } 
			  else 
			  {
			    return b;
			  }
		}
	}



}