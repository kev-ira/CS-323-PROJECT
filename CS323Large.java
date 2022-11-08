public class main {

static int QScompCount = 0;
static int QSswapCount = 0;
static int HScompCount = 0;
static int HSswapCount = 0;
static int MScompCount = 0;
static int MSswapCount = 0;
static int MStempArrCount =0;
static int ISswapCount = 0;
static int IScompCount =0;

public static void insertionSort(int[] A) {
  int arraySize = A.length;
  for (int k=1; k<arraySize; k++) {
    int c = A[k];
    int i= k-1;
    //Each loop of while increments both counters
    while((i>=0) && (A[i]>c)) {
      ISswapCount++;
      IScompCount++;
      A[i+1]=A[i];
      i--;
    }
    A[i+1]=c;
  }
//Add one more compare for the comparison that happened but
//failed, so it didn't increment the counter
  IScompCount++;
}
  
public static void heapSort(int[]A, int aSize) {
  //Heapify given integer array
  for (int i= aSize/2 -1; i>=0; i--) {
    maxHeapPerDown(i,A,aSize);
  }
  for(int i=aSize-1; i>0; i--) {
    int temp = A[0];
    A[0]=A[i];
    A[i]=temp;
    HSswapCount++;
    maxHeapPerDown(0, A, i);
  }
}
  
public static void maxHeapPerDown(int index, int[] A, int aSize) {
  //Inserts an element as the root of the
  //"heap" and then lets it come down if necessary
  int childIndex = 2* index +1;
  int value = A[index];
  while (childIndex< aSize) {
    int maxVal = value;
    int maxIndex = -1;
    for(int i = 0;i<2 && i+childIndex < aSize; i++) {
      HScompCount = HScompCount + 2;
      if(A[i+childIndex]> maxVal) {
        maxVal = A[i+childIndex];
        maxIndex = i+ childIndex;
      }
    }
    if(maxVal == value)return;
    else {
      int temp = A[index];
      A[index]= A[maxIndex];
      A[maxIndex]= temp;
      HSswapCount++;
      index = maxIndex;
      childIndex = 2 * index +1;
    }
  }
}
  
public static void mergeSort(int[]A, int first, int last) {
  int mid=0;
  if(first < last) {
    mid= (last+first)/2;
    //Break up the list into halves until only one
    //element is left in each array
    mergeSort(A, first, mid);
    mergeSort(A, mid+1, last);
    //Merge these arrays back in correct order
    merge(A, first, mid, last);
  }
}
  
public static void merge(int []A, int first, int mid, int last) {
  int mergedSize = last-first +1;
  int mergedPos=0;
  //Here is where the temp array is created so we increment the counter
  //Through this we use a lot of memory, which in some cases where
  // you might not have a lot of space, the other algorithms might be preferred
  int [] mergedA= new int [mergedSize];
  MStempArrCount++;
  int leftPos= first;
  int rightPos=mid+1;
  while(leftPos <= mid && rightPos <= last) {
    MScompCount = MScompCount +3;
    //If left side is bigger add it to the temp arr
    if(A[leftPos]< A[rightPos]) {
      mergedA[mergedPos] = A[leftPos];
      leftPos++;
      MSswapCount++;
    }
    //else add the right side
    else {
      mergedA[mergedPos] = A[rightPos];
      rightPos++;
      MSswapCount++;
    }
    mergedPos++;
  }
  //If there are any remaining elements add them to the temp arr 
  while(leftPos <= mid) {
    mergedA[mergedPos]= A[leftPos];
    leftPos++;
    mergedPos++;
    MSswapCount++;
  }
  while(rightPos <= last) {
    mergedA[mergedPos]= A[rightPos];
    rightPos++;
    mergedPos++;
    MSswapCount++;
  }
  //Copy the temp array back into the original one
  for(mergedPos = 0; mergedPos<mergedSize; mergedPos++) {
    A[first+mergedPos]=mergedA[mergedPos];
  }
}
  
static void quickSort(int first, int last, int [] A) {
  if(first < last) {
    //Pick a pivot element
    int pivot = partition(first, last, A);
    //Recursively sort the two arrays made by
    //breaking the original array up around the pivot
    quickSort(first, pivot-1, A);
    quickSort(pivot+1, last, A);
  }
}
  
static int partition(int first, int last, int [] A) {
  //Pick last element as pivot
  int pivElt = A[last];
  int sortPos = first - 1;
  for(int k = first; k < last; k++) {
    QScompCount++;
    //If the element in the array is less than
    //the pivot, increment the counter
    if (A[k]<=pivElt) {
      sortPos++;
      int temp = A[sortPos];
      A[sortPos]= A[k];
      A[k]= temp;
      QSswapCount++;
    }
  }
  int temp = A[sortPos+1];
  A[sortPos+1]= A[last];
  A[last]= temp;
  QSswapCount++;
  return sortPos +1 ;
  //Returns the final sorted position of the pivot
}
  
public static void main(String[] args) {
  //Creates the int array that stores 10000 elements
  int [] ra = new int[10000];
  int min = 0;
  int max = 1000000;
  //For each index in the array, fill it with a random value
  //that is at least 0 and at most 1000000
  for(int i = 0; i<10000; i++){
    int random = ThreadLocalRandom.current().nextInt(min, max+1);
    ra[i] = random;
  }
  //insertion sort
  //Copies the random array created so that
  //each sorting algorithm receives the same array
  int[] B = new int[10000];
  for(int i = 0; i<10000; i++){
    B[i]= ra[i];
  }
  //notes the current time before and after IS is done
  // in order to find the difference
  System.out.println(System.currentTimeMillis());
  long IStime1 = System.currentTimeMillis();
  insertionSort(B);
  long IStime2 = System.currentTimeMillis();
  System.out.println(System.currentTimeMillis());
  long ISdifference = IStime2- IStime1;
  System.out.println("Insertion Sort Sorted");
  System.out.println("Comparisons needed: " + IScompCount );
  System.out.println("Swaps needed: " + ISswapCount);
  System.out.println("Time needed for Insertion Sort: " + ISdifference);
  System.out.println();
  //heap sort
  //Copies the random array
  int[] C = new int[10000];
  for(int i = 0; i<10000; i++){
    C[i]= ra[i];
  }
  //notes the current time before and after HS is done
  // in order to find the difference
  System.out.println(System.currentTimeMillis());
  long HStime1 = System.currentTimeMillis();
  heapSort(C, 10000);
  long HStime2 = System.currentTimeMillis();
  System.out.println(System.currentTimeMillis());
  System.out.println("Heap Sort Sorted");
  System.out.println("Comparisons needed: " + HScompCount );
  System.out.println("Swaps needed: " + HSswapCount);
  long HSdifference = HStime2- HStime1;
  System.out.println("Time needed for Heap Sort: " + HSdifference);
  System.out.println();
  //merge sort
  //Copies the random array
  int[] D = new int[10000];
  for(int i = 0; i<10000; i++){
    D[i]= ra[i];
  }
  //notes the current time before and after MS is done
  // in order to find the difference
  System.out.println(System.currentTimeMillis());
  long MStime1 = System.currentTimeMillis();
  mergeSort(D, 0,9999);
  long MStime2 = System.currentTimeMillis();
  System.out.println(System.currentTimeMillis());
  System.out.println("Merge Sort Sorted");
  long MSdifference = MStime2- MStime1;
  System.out.println("Comparisons needed: " + MScompCount );
  System.out.println("Swaps needed: " + MSswapCount);
  System.out.println("Temporary arrays needed: " + MStempArrCount++);
  System.out.println("Time needed for Merge Sort: " + MSdifference);
  System.out.println();
  //quick sort
  //Copies the randm array
  int[] E = new int[10000];
  for(int i = 0; i<10000; i++){
    E[i]= ra[i];
  }
  //notes the current time before and after QS is done
  // in order to find the difference
  System.out.println(System.currentTimeMillis());
  long QStime1 = System.currentTimeMillis();
  quickSort(0, 9999, E);
  long QStime2 = System.currentTimeMillis();
  System.out.println(System.currentTimeMillis());
  System.out.println("Quick Sort Sorted");
  System.out.println("Comparisons needed: " + QScompCount );
  System.out.println("Swaps needed: " + QSswapCount);
  long QSdifference = QStime2- QStime1;
  System.out.println("Time needed for Quick Sort: " + QSdifference);
  System.out.println();
  }
}
//Averages run time of the sorting algorithms
// IS : 40.19
//HS: 3.83
//MS: 2.54
//QS: 1.9
