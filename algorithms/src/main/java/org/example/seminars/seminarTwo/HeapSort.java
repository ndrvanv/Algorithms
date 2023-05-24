package org.example.seminars.seminarTwo;


public class HeapSort {

    public static void main(String[] args) {
        int[] array = {45,78,69,2,123,789,5,101,85,32};
        heapSort(array);
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
    // Реализовать алгоритм пирамидальной сортировки (сортировка кучей)
    private static void heapSort(int[] array){
        int size = array.length;
        for(int i = size / 2 - 1; i >= 0; i--){
            heapify(array, i, size);
        }
        

        for(int i = size - 1; i >= 0; i--){
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;

            heapify(array, 0, i);
        }
    }

    private static void heapify(int[] array, int i, int size) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size && array[left] > array[largest]){
            largest = left;
        }
        if (right < size && array[right] > array[largest]){
            largest = right;
        }
        if(i != largest) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
        
            heapify(array, largest, size);
        }
}
}