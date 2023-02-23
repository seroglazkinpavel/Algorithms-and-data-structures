import java.util.Random;

public class Seminar_2 {
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] arr = ArrayUtils.prepareArray();
        ArrayUtils.printArray(arr);
        HeapSort.sort(arr);
        ArrayUtils.printArray(arr);

        int[] testArray = ArrayUtils.prepareArray(100000);

        long startTime = System.currentTimeMillis();
        HeapSort.sort(testArray.clone());
        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;
        System.out.printf("Время работы пирамидальной сортировки: %d мс.\n", processingTime);
    }
    public static class ArrayUtils {

        static int[] prepareArray() {
            int[] arr = new int[random.nextInt(16) + 5];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(100);
            }
            return arr;
        }

        static int[] prepareArray(int length) {
            int[] arr = new int[length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(100);
            }
            return arr;
        }

        static void printArray(int[] arr) {
            for (int element : arr)
                System.out.printf("%d\t", element);
            System.out.println();
        }

    }

    /**
     * Класс для сортировки массива целых чисел с помощью кучи.
     * Методы в классе написаны в порядке их использования. Для сортировки
     * вызывается статический метод sort(int[] a)
     */
    class HeapSort {
        /**
         * Размер кучи. Изначально равен размеру сортируемого массива
         */
        private static int heapSize;

        /**
         * Сортировка с помощью кучи.
         * Сначала формируется куча:
         * @see HeapSort#buildHeap(int[])
         * Теперь максимальный элемент массива находится в корне кучи. Его нужно
         * поменять местами с последним элементом и убрать из кучи (уменьшить
         * размер кучи на 1). Теперь в корне кучи находится элемент, который раньше
         * был последним в массиве. Нужно переупорядочить кучу так, чтобы
         * выполнялось основное условие кучи - a[parent]>=a[child]:
         * @see #heapify(int[], int)
         * После этого в корне окажется максимальный из оставшихся элементов.
         * Повторить до тех пор, пока в куче не останется 1 элемент
         *
         * @param a сортируемый массив
         */
        public static void sort(int[] a) {
            buildHeap(a);
            while (heapSize > 1) {
                swap(a, 0, heapSize - 1);
                heapSize--;
                heapify(a, 0);
            }
        }

        /**
         * Построение кучи. Поскольку элементы с номерами начиная с a.length / 2 + 1
         * это листья, то нужно переупорядочить поддеревья с корнями в индексах
         * от 0 до a.length / 2 (метод heapify вызывать в порядке убывания индексов)
         *
         * @param a - массив, из которого формируется куча
         */
        private static void buildHeap(int[] a) {
            heapSize = a.length;
            for (int i = a.length / 2; i >= 0; i--) {
                heapify(a, i);
            }
        }

        /**
         * Переупорядочивает поддерево кучи начиная с узла i так, чтобы выполнялось
         * основное свойство кучи - a[parent] >= a[child].
         *
         * @param a - массив, из которого сформирована куча
         * @param i - корень поддерева, для которого происходит переупорядосивание
         */
        private static void heapify(int[] a, int i) {
            int l = left(i);
            int r = right(i);
            int largest = i;
            if (l < heapSize && a[i] < a[l]) {
                largest = l;
            }
            if (r < heapSize && a[largest] < a[r]) {
                largest = r;
            }
            if (i != largest) {
                swap(a, i, largest);
                heapify(a, largest);
            }
        }

        /**
         * Возвращает индекс правого потомка текущего узла
         *
         * @param i индекс текущего узла кучи
         * @return индекс правого потомка
         */
        private static int right(int i) {
            return 2 * i + 1;
        }

        /**
         * Возвращает индекс левого потомка текущего узла
         *
         * @param i индекс текущего узла кучи
         * @return индекс левого потомка
         */
        private static int left(int i) {
            return 2 * i + 2;
        }

        /**
         * Меняет местами два элемента в массиве
         *
         * @param a массив
         * @param i индекс первого элемента
         * @param j индекс второго элемента
         */
        private static void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

    }
}
