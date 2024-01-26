import java.util.*;
import java.io.*;
import java.util.concurrent.*;
import java.lang.*;

public class Primes 
{
	static final int MAX = (int)Math.pow(10, 8); // 10^8
	
	public static void main(String [] args) throws FileNotFoundException
	{
		long startTime = System.nanoTime();
		Resources r = new Resources();
		
		// creat and run threads
		Thread threads [] = new Thread[8];

		for (int i = 0; i < 8; i++)
		{
			threads[i] = new PrimesThread(r, 2*i+1); // start at the first 8 odd numbers
			threads[i].start();
		}
		
		for (Thread thread : threads)
		{
			try
			{
				thread.join();
			}
			catch (Exception e) {}
		}
		
		long endTime = System.nanoTime();
		
		
		// format and print output
		PrintWriter out = new PrintWriter("primes.txt");
		
		out.printf("%.4f seconds |", (endTime-startTime)/Math.pow(10,9));
		out.printf("%d primes found |", r.size());
		out.printf("%d \n", r.sum());
		
		Deque<Integer> bigten = new ArrayDeque<Integer>(); // print out biggest ten from small to largest
		for (int i = 0; i < 10; i++)
			bigten.add(r.pop());
		for (int num : bigten)
			out.printf("%d \n", num);
			
		out.close();
	}
}

// helper class to have a shared heap to keep primes in
public class Resources
{
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
	
	public synchronized void push(int n)
	{
		maxHeap.add(n);
	}
	
	public synchronized int pop()
	{
		return maxHeap.poll();
	}
	
	public synchronized int size()
	{
		return maxHeap.size();
	}
	
	public synchronized long sum()
	{
		long sum = 0;
		for (int num : maxHeap)
		{
			sum += num;
		}
		return sum;
	}
}

// thread class that runs threaded algorithm
public class PrimesThread extends Thread
{	
	static final int MAX = (int)Math.pow(10, 8); // 10^8
	Resources r;
	int n;
	
	PrimesThread(Resources r, int start) // constructor
	{
		super();
		this.r = r;
		this.n = start;
	}
	

	public void run()
	{
		if (n == 1) // if starting value is 1 also add 2 to prime list
			if (isprime(n+1))
				r.push(n+1);
				
		for (int i = n; i < MAX; i += 16) // skips to every 8th odd number from start to check
		{
			if (isprime(i))
				r.push(i);
		}
	}
	
	// helper function to optimally check if number is prime
	public Boolean isprime(int n)
	{
		if (n <= 1)
			return false;
		
		if (n == 2 || n == 3)
			return true;
			
		if (n % 2 == 0 || n % 3 == 0)
			return false;
	
		for (int i=5; i <= Math.sqrt(n); i += 6)
		{
			if (n % i == 0 || n % (i + 2) == 0)
				return false;
		}
	
		return true;
	}
}
