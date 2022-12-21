package lesson1.homework.src.com.hsai.sorts;

public class Merge {
    public static void merge(int[] array, int left, int right) {
        if (right <= left) return;
        int mid = (left+right)/2;
        merge(array, left, mid);
        merge(array, mid+1, right);
        help_for_merge(array, left, mid, right);
    }
    static void help_for_merge(int[] array, int left, int mid, int right) {
        int left_ind = 0;
        int right_ind = 0;
        int length_left = mid - left + 1;
        int length_right = right - mid;
        int[] array_for_left = new int [length_left];
        int[] array_for_right = new int [length_right];
        System.arraycopy(array, left, array_for_left, 0, length_left);
        for (int i = 0; i < length_right; i++)
            array_for_right[i] = array[mid+i+1];
        for (int i = left; i < right + 1; i++) {
            if (left_ind < length_left && right_ind < length_right) {
                if (array_for_left[left_ind] < array_for_right[right_ind]) {
                    array[i] = array_for_left[left_ind];
                    left_ind++;
                }
                else {
                    array[i] = array_for_right[right_ind];
                    right_ind++;
                }
            }
            else if (left_ind < length_left) {
                array[i] = array_for_left[left_ind];
                left_ind++;
            }
            else if (right_ind < length_right) {
                array[i] = array_for_right[right_ind];
                right_ind++;
            }
        }
    }
}