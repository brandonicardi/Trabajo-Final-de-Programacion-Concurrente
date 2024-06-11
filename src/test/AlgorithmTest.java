package test;

import data.ConcurrentQuickSort;
import data.SequentialQuicksort;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class AlgorithmTest 
{
    public static void main(String[] args) 
    {
        int n = 100000000;
    
        if(n <= 1) 
        {
            System.out.println("El array no contiene elementos suficientes para ser ordenado.");
            return;
        }
    
        int[] arr = new int[n];
        Random random = new Random();
    
        for(int i = 0; i < n; i++) 
        {
            arr[i] = random.nextInt(n + 1);
        }     
    
        
        int arrayOrdenarSecuencialmente[] = Arrays.copyOf(arr, n);
        int arrayOrdenarConcurrententemente[] = Arrays.copyOf(arr, n);
        
        SequentialQuicksort qss = new SequentialQuicksort();
        
        long tiempoInicialSecuencial = System.nanoTime();
        
        qss.quickSort(arrayOrdenarSecuencialmente, 0, n - 1);
        
        long tiempoFinalSecuencial = System.nanoTime() - tiempoInicialSecuencial;
        
        
        ForkJoinPool pool = new ForkJoinPool();
        
        ConcurrentQuickSort qsc = new ConcurrentQuickSort(arrayOrdenarConcurrententemente, 0, n - 1); // Modificado
        
        long tiempoInicialConcurrente = System.nanoTime();
        
        pool.invoke(qsc);
        
        long tiempoFinalConcurrente = System.nanoTime() - tiempoInicialConcurrente;
      
        System.out.println("Para ordenar " + n + " elementos:");
        System.out.println("El algoritmo QuickSort SECUENCIAL tardó: " + tiempoFinalSecuencial / 1000000 + " ms.");
        System.out.println("El algoritmo QuickSort CONCURRENTE tardó: " + tiempoFinalConcurrente / 1000000 + " ms.");
    }
}