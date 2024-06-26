package SortingAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Algorithms {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        int [] arr = {40,18,5,33,23,55,12,60};
        int algoritmos;
        System.out.println("Algoritmos de ordenamiento: " +
                "\n1.Bubble Sort" +
                "\n2.Selection Sort" +
                "\n3.Insertion Sort" +
                "\n4.Heap Sort" +
                "\n5.Counting Sort" +
                "\n6.Marge Sort" +
                "\n7.Quick Sort" +
                "\n8.Radix Sort" +
                "\n9.Bucket Sort");
        System.out.println("\nIngrese la opcion que desee: ");
        algoritmos = scanner.nextInt();

        switch (algoritmos){
            case 1:
                bubbleSort(arr);
                break;
            case 2:
                selectionSort(arr);
                break;
            case 3:
                insertionSort(arr);
                break;
            case 4:
                heapSort(arr);
                break;
            case 5:
                countingSort(arr);
                break;
            case 6:
                mergeSort(arr, 0, arr.length - 1);
                System.out.println("\nEl arreglo ordenado es: ");
                for (int l : arr){
                    System.out.print(l + " ");
                }
                break;
            case 7:
                quickSort(arr, 0, arr.length - 1);
                System.out.println("\nEl arreglo ordenado es: ");
                for (int j : arr){
                    System.out.print(j + " ");
                }
                break;
            case 8:
                radixSort(arr);
                break;
            case 9:
                bucketSort(arr, arr.length);
                System.out.println("\nEl arreglo ordenado es: ");
                for (int j : arr){
                    System.out.print(j + " ");
                }
                break;
            default:
                System.out.println("Se ingreso una opcion incorrecta");
                break;
        }

    }

    public static void bubbleSort(int[] arr){
        int aux;

        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length - 1; j++){
                if(arr[j] > arr[j + 1]){
                    aux = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = aux;
                }
            }
        }
        System.out.println("\nEl arreglo ordenado es: ");
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }

    public static void selectionSort(int[] arr){
        int index, aux;

        for (int i = 0; i < arr.length - 1; i++){
            //Encontrar el indice del elemento minomo en el resto del arreglo
            index = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[index]){
                    index = j;
                }
            }

            //Intercambio de los elementos
            aux = arr[index];
            arr[index] = arr[i];
            arr[i] = aux;
        }
        System.out.println("\nEl arreglo ordenado es: ");
        for (int j : arr){
            System.out.print(j + " ");
        }
    }

    public static void insertionSort(int[] arr){
        int index, aux;

        for (int i = 0; i < arr.length; i++){
            index = i;
            aux = arr[i];

            while ((index > 0) && (arr[index - 1] > aux)){
                arr[index] = arr[index - 1];
                index--;
            }

            arr[index] = aux;
        }
        System.out.println("\nEl arreglo ordenado es: ");
        for (int j : arr){
            System.out.print(j + " ");
        }
    }

    public static void heapSort(int[] arr){
        int aux;
        int n = arr.length;

        //Construir un monton maximo
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        //Extraer los elementos del monton uno por uno
        for (int i = n - 1; i > 0; i--){
            //Mover la raiz actual al final del arreglo
            aux = arr[0];
            arr[0] = arr[i];
            arr[i] = aux;

            heapify(arr, i, 0);
        }
        System.out.println("\nEl arreglo ordenado es: ");
        for (int j : arr){
            System.out.print(j + " ");
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        //Si el hijo izquierdo es mas grande que la raiz
        if ((left < n) && (arr[left] > arr[largest]))
            largest = left;

        //Si el hijo derecho es mas grande que el mas grande hasta ahora
        if ((right < n) && (arr[right] > arr[largest]))
            largest = right;

        //Si el mas largo no es la raiz
        if (largest != i){
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            //Recursivamente heapify el sub arbol afectado
            heapify(arr, n, largest);
        }
    }

    public static void countingSort(int [] arr){
        int max;
        int n = arr.length;

        //Encontrar el valor mamximo del arreglo
        max = arr[0];
        for (int i = 1; i < n; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }

        //Arreglo de conteo
        int [] count = new int[max + 1];

        //Inicializar el arreglo en cero
        for (int i = 0; i <= max; i++){
            count[i] = 0;
        }

        //Contar la ocurrencia de cada elemento del arreglo
        for (int j : arr){
            count[j]++;
        }

        //Modificar el arreglo de conteo para almacenar las posiciones reales de los elementos
        for(int i = 1; i <= max; i++){
            count[i] += count[i - 1];
        }

        //Arreglo de salida
        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--){
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        //Copiar los elementos ordenados del arreglo de salida al arreglo original
        System.arraycopy(output, 0, arr, 0, n);

        System.out.println("\nEl arreglo ordenado es: ");
        for (int j : arr){
            System.out.print(j + " ");
        }
    }

    public static void mergeSort(int[] arr, int left, int right){

        if (left < right){
            //Encontrar el punto medio
            int middle = left + (right - left) /2;
            //Ordenar la primera mitad
            mergeSort(arr, left, middle);
            //Ordenar la segunda mitad
            mergeSort(arr, middle + 1, right);

            merge(arr, left, middle, right);
        }
    }

    private static void merge(int[] arr, int left, int middle, int right){
        //Tamaño de las sublistas
        int n1 = middle - left + 1;
        int n2 = right - middle;

        //Arreglos temporales para las sublistas
        int[] L = new int[n1];
        int[] R = new int[n2];

        //Copia los elementos a los arreglos temporales
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr,middle + 1, R, 0, n2);

        //Indice para las sublistas
        int i = 0, j = 0;
        //Indice para el arreglo combinado
        int k = left;

        //Combina las sublistas en orden ascendente
        while ((i < n1) && (j < n2)){
            if (L[i] <= R[j]){
                arr[k] = L[i];
                i++;
            }else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        //Copia elementos restantes de L
        while (i < n1){
            arr[k] = L[i];
            i++;
            k++;
        }
        //Copia elementos restantes de R
        while (j < n2){
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void quickSort(int[] arr, int start, int end){
        if (start < end){
            //Encuentra el indice de la particion
            int index = partition(arr, start, end);

            //Ordena recursivamente los elementos antes y despues de la particion
            quickSort(arr, start, index - 1);
            quickSort(arr, index + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end){
        int pivot = arr[end]; //El ultimo elemento se elige como pivote
        int i = start - 1; //Indice del elemento mas pequeño
        int aux;

        for (int j = start; j < end; j++){
            //Si el elemento actual es menor o igual al pivote
            if (arr[j] <= pivot){
                i++;
                //Se intercambia arreglo [i] y arreglo [j]
                aux = arr[i];
                arr[i] = arr[j];
                arr[j] = aux;
            }
        }

        //Intercambia arreglo [i + 1] y arreglo [fin] o pivote
        aux = arr[i+ 1];
        arr[i + 1] = arr[end];
        arr[end] = aux;

        return i + 1;
    }

    public static void radixSort(int[] arr){
        //Encontrar el valor maximo
        int max = getMax(arr);

        //Realizar counting sort para cada digito, enlugar de pasar el numero del digito
        for (int exp = 1; max / exp > 0; exp *= 10){
            countingSortByDigit(arr, exp);
        }

        System.out.println("\nEl arreglo ordenado es: ");
        for (int j : arr){
            System.out.print(j + " ");
        }
    }

    private static int getMax(int [] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }

    private static void countingSortByDigit(int[] arr, int exp){
        int digit;
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        //Inicializa el areglo de conteo
        Arrays.fill(count, 0);

        //Almacena el conteo de ocurrencias en el arreglo de conteo
        for (int j : arr){
            count[(j / exp) % 10]++;
        }

        //Modificar el arreglo de conteo para que cada posicion tenga su valor real
        for (int i = 1; i <10; i++){
            count[i] += count[i - 1];
        }

        //Arreglo de salida
        for (int i = n - 1; i >= 0; i--){
            digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        //Copiar los elementos del arreglo de salida al arreglo original
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void bucketSort(int[] arr, int n){
        if (n <= 0){
            return;
        }

        //Encuentra el valor maximo del arreglo
        int max = arr[0];
        for (int i = 0; i < n; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }

        //Crea una lista de cubetas vacias
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] buckets = new ArrayList[n];

        //Inicializar
        for (int i = 0; i < n; i++){
            buckets[i] = new ArrayList<>();
        }

        //Distribuye los elementos en cubetas correspondientes
        for (int i = 0; i < n; i++){
            int bucketIndex = (arr[i] * n) / (max + 1);
            buckets[bucketIndex].add(arr[i]);
        }

        //Ordena los elementos dentro de cada cubeta usando collections
        for (int i = 0; i < n; i++){
            Collections.sort(buckets[i]);
        }

        //Concatena las cubetas para formar el arreglo ordenado
        int index = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < buckets[i].size(); j++){
                arr[index++] = buckets[i].get(j);
            }
        }
    }
}
