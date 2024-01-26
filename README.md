# Multithreaded-Primes-Finder
Description and Summary of Program:
This program will find all primes between 0 and 10^8 by spawning 8 threads to run concurrently and collect all of the primes.
It splits the work across the threads by giving each thread a starting number, that is an odd number from 1 to 17, checking if it is prime and then skips over the next 7 odd numbers to find the next value to check if prime.
The numbers it skips over should be covered by the other 7 threads, and this how the program splits the work of checking primes, so that each thread is doing a roughly the same amount of calculation, as in a somewhat even amount of easy and hard to calculate prime checks for each thread.
The prime checking algorithm is optimized by looping through to the square root of the given number to find possible divisors that may divide the given number.
The program uses a max heap to store the primes into and get easy access to the biggest prime numbers found.
The program will print out in "primes.txt" the time it takes to run, the total number of primes, the sum of all primes, and the ten biggest primes found.

Compile and Run:
This program is written in java and will need JDK downloaded and installed
Open a terminal and navigate to the directory with the 'primes.java' file in it
To Compile enter into the command line: 'javac primes.java'
To Run after compiling enter: 'java primes'
The ouput will be stored in file 'primes.txt' in the same directory
