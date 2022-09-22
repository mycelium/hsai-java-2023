package com.hsai.algorithms;

public class Merge extends AbstractSort{
    public Merge() {
        super("Merge sort"); 
    };
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    };
    protected void mergeSort(int[] array, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right)/2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    };
    protected void merge(int[] array, int left, int mid, int right) {
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;
    
        int leftArray[] = new int [lengthLeft];
        int rightArray[] = new int [lengthRight];
    
        for (int i = 0; i < lengthLeft; i++) {
            leftArray[i] = array[left+i];
        }
        for (int i = 0; i < lengthRight; i++) {
            rightArray[i] = array[mid+i+1];
        }
    };
}
