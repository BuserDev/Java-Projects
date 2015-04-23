import org.junit.Test;

/*
   Class: cs146-01
   Semester: Fall 2011
   Project: #1 - Quicksort
   sample JUnit tests for qs1, qs2, qs3, running time,
   counter in partition, median with select
 */
import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * The main JUnit Test class to test each method in Quicksort
 * 
 *
 */
public class QuicksortTest
{

   private Quicksort QS;

   @Before
   public void setUp() throws Exception
   {
      QS = new Quicksort();
      QS.reset();
   } // setUp()

   /* Sorting an empty List should produce an empty list:
    */
   @Test
   public void testEmpty() {
             int[] array1 = new int[0];
             int[]  array2= new int[0]; //correct sorted array
             
             array1 = QS.qs1(array1, 0, array1.length - 1);
             assertArrayEquals(array1,array2);
            // array1 = QS.qs2(array1, 0, array1.length - 1);
             //assertArrayEquals(array1,array2);
             //array1 = QS.qs3(array1, 0, array1.length - 1);
             //assertArrayEquals(array1,array2);
   }   
  
   /* Sorting an already sorted list:
    */
   @Test
   public void testSorted() {
        int[] array1 = new int[20];
        int[] array2 = new int[20];
        int[] array3 = new int[20];
        for (int i = 0; i < 10; i++) {
             array1[i] = i*2;
             array2[i] = i*2;
             array3[i] = i*2;
        }
        Arrays.sort(array3);
         
        // run QS1()
        array1 = QS.qs1(array1, 0, array1.length - 1);
        assertArrayEquals(array1,array3);
        
        // run QS2()
        //array1 = QS.qs2(array2, 0, array2.length - 1);
       // assertArrayEquals(array1,array3);
        
        // run QS3()
       // array1 = QS.qs3(array2, 0, array2.length - 1);
        //assertArrayEquals(array1,array3);
   }
   
   /* Sorting a reverse sorted list:
    */
   @Test
   public void testReverseSorted() {
        int[] array1 = new int[10];
        int[] array2 = new int[10];
        
        int[] array3 = new int[10];
        
        for (int i = 0; i < 10; i++) {
             array1[i] = (100-i);
             array2[i] = (100-i);
             array3[i] = (100-i);
        }
        //sort array3 
        Arrays.sort(array3); 
        // run QS1()
        array1 = QS.qs1(array1, 0, array1.length - 1);
        assertArrayEquals(array1,array3);
        
        // run QS2()
        //array1 = QS.qs2(array2, 0, array2.length - 1);
        //assertArrayEquals(array1,array3);
        
        // run QS3()
        //array1 = QS.qs3(array2, 0, array2.length - 1);
        //assertArrayEquals(array1,array3);
   }
   
   /*
    * randomness to the tests:
    */
    @Test
     public void testRandom() {
       int[] array1 = new int[10];
       
       for (int i = 0; i < 10; i++) {
            array1[i] = (int) (Math.random()*10);
       }
       //copy arrays
       int[] array2 = Arrays.copyOf(array1, array1.length);  
       int[] array3 = Arrays.copyOf(array1, array1.length); // correct sorted array 
        Arrays.sort(array3);
 
        // run QS1()
       array1 = QS.qs1(array1, 0, array1.length - 1);
       assertArrayEquals(array1,array3);
       
       // run QS2()
       //array1 = QS.qs2(array2, 0, array2.length - 1);
      // assertArrayEquals(array1,array3);
       
       // run QS3()
       array1 = QS.qs1(array2, 0, array2.length - 1);
       assertArrayEquals(array1,array3);
     }
   @Test
   public void testQS1Timing()
   {
      // create an array and a sorted backup
      int[] array1 = QS.populate(10000);
      int[] array2 = QS.populate(1000000);
      int[] array3 = QS.populate(10000000);
      
      
      long start = System.currentTimeMillis();
      array1 = QS.qs1(array1, 0, array1.length - 1);
      long end = System.currentTimeMillis();
      long elapsed = end - start;
      System.out.println("QS1 time to sort 10000 elements in ms:"+ elapsed);

      start = System.currentTimeMillis();
      array2 = QS.qs1(array2, 0, array2.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS1 time to sort 1000000 elements in ms:"+ elapsed);
      
      start = System.currentTimeMillis();
      array3 = QS.qs1(array3, 0, array3.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS1 time to sort 100000000 elements in ms:"+ elapsed);
   }

/*  
   @Test
   public void testQS2Timing()
   {
      // create an array and a sorted backup
      int[] array1 = QS.populate(10000);
      int[] array2 = QS.populate(1000000);
      int[] array3 = QS.populate(10000000);
      
      
      long start = System.currentTimeMillis();
      array1 = QS.qs2(array1, 0, array1.length - 1);
      long end = System.currentTimeMillis();
      long elapsed = end - start;
      System.out.println("QS2 time to sort 10000 elements in ms:"+ elapsed);

      start = System.currentTimeMillis();
      array2 = QS.qs2(array2, 0, array2.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS2 time to sort 1000000 elements in ms:"+ elapsed);
      
      start = System.currentTimeMillis();
      array3 = QS.qs2(array3, 0, array3.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS2 time to sort 100000000 elements in ms:"+ elapsed);
   } 

   @Test
   public void testQS3Timing()
   {
      // create an array and a sorted backup
      int[] array1 = QS.populate(10000);
      int[] array2 = QS.populate(1000000);
      int[] array3 = QS.populate(10000000);
      
      
      long start = System.currentTimeMillis();
      array1 = QS.qs3(array1, 0, array1.length - 1);
      long end = System.currentTimeMillis();
      long elapsed = end - start;
      System.out.println("QS3 time to sort 10000 elements in ms:"+ elapsed);

      start = System.currentTimeMillis();
      array2 = QS.qs3(array2, 0, array2.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS3 time to sort 1000000 elements in ms:"+ elapsed);
      
      start = System.currentTimeMillis();
      array3 = QS.qs3(array3, 0, array3.length - 1);
      end = System.currentTimeMillis();
      elapsed = end - start;
      System.out.println("QS3 time to sort 100000000 elements in ms:"+ elapsed);
   } 
*/
   @Test
   //in a reverse sorted array of 10 numbers the number of comparisons is 45 
   public void testgetPartCount()
   {
      int[] array1 = new int[10];
      
      for (int i = 0; i < 10; i++) {
           array1[i] = (100-i);
      }
      
      array1 = QS.qs1(array1, 0, array1.length - 1);
      System.out.println("comparisons in reverse sorted:"+ QS.getPartCount());
      long compare=QS.getPartCount();
      assertEquals(compare, 45);
         
   } // getPartCount()

   @Test
   //in a sorted array of 10 numbers the number of comparisons is 45 
   public void testgetPartCountA()
   {
      int[] array1 = new int[10];
      
    //  int[] result1 = new int[10];
      
      for (int i = 0; i < 10; i++) {
           array1[i] = i*20;
      }
      
      array1 = QS.qs1(array1, 0, array1.length - 1);
     System.out.println("comparisons in already sorted:"+ QS.getPartCount());
      long compare=QS.getPartCount();
      System.out.println(compare);
      assertEquals(compare, 45);
   }
 
   /*
    * test select
    
    @Test
     public void testSelect() {
       int[] array1 = new int[100];
       
       for (int i = 0; i < 100; i++) {
            array1[i] = i;
       }
       // median is 50
       int median=QS.select(array1,0, array1.length-1, array1.length/2);
       System.out.println("median:"+ QS.select(array1,0, array1.length-1,
                array1.length/2));
       assertEquals(median, 50);
       
    }*/
 
   
  } // class QuicksortTest