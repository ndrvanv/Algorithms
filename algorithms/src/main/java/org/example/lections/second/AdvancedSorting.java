package org.example.lections.second;

public class AdvancedSorting {
    //Быстрая сортировка
    //Пирамидальная сортировка или сортировка кучей
    public static void main(String[] args) {
        int[] array = new int[] {2,1,3,5,8,6,9,4};
        sort(array);
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }
    public static void sort(int[]array, int startPosition, int endPosition) {
        int leftPosition = startPosition;
        int rightPosition = endPosition;
        int pivot = array[(startPosition + endPosition) / 2];
        do{
            while (array[leftPosition] < pivot) {
                leftPosition++;
            }
            while (array[rightPosition] > pivot) {
                rightPosition++;
            }
            if(leftPosition <= rightPosition) {
                if(leftPosition < rightPosition) {
                    int temp = array[leftPosition];
                    array[leftPosition] = array[rightPosition];
                    array[rightPosition] = temp;
                }
                leftPosition++;
                rightPosition--;
            }
        } while (leftPosition <= rightPosition);
        if(leftPosition < endPosition) {
            sort(array, leftPosition, rightPosition);
        }
        if(startPosition < rightPosition) {
            sort(array, startPosition, rightPosition);
        }
    }

    //Бинарный поиск
    public static void sort(int[] array) {
        // Построение кучи (перегруппируем массив)
        for(int i = array.length / 2 - 1; i >=0; i--)
            heapify(array, array.length, i);

        //Один за другим извлекаем элементы из кучи
        for (int i = array.length - 1; i >= 0; i--) {
            // Перемещаем текущий корень в конец
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Вызываем процедуру heapify на уменьшеной куче
        }

    }
    private static void heapify(int[]array, int heapSize, int rootIndex) {
        int largest = rootIndex; // Инициализируем наибольший элемент как корень
        int leftChild = 2 * rootIndex + 1;
        int rightChild = 2 * rootIndex + 2;
        //Если левый дочерний элемень больше корня
        if(leftChild < heapSize && array[leftChild] > array[largest])
            largest = leftChild;
        //Если первый дочерний элемент больше, чем самый большой элемент на данный момент
        if(rightChild < heapSize && array[rightChild] > array[largest])
            largest = rightChild;
        // Если самый большой элемень не корень
        if(largest != rootIndex) {
            int temp = array[rootIndex];
            array[rootIndex] = array[largest];
            array[largest] = temp;

            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(array, heapSize, largest);
        }
    }

}
