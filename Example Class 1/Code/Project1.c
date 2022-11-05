#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int *generate_random_array(int min, int max, int number_of_elements);
int is_sorted_array(int arr[], int l, int r);
void merge_sort(int arr[], int l, int r, long *comparisonCount);
void merge(int arr[], int l, int m, int r, long *comparisonCount);
void insertion_sort(int arr[], int l, int r, long *comparisonCount);
void hybrid_sort(int arr[], int l, int r, int S, long *comparisonCount);
void swap(int *ptr1, int *ptr2);

int main()
{
    long comparisonCount = 0;
    long comparisonCount2 = 0;
    int lower_bound = 1;
    int upper_bound = 1000;
    int size = 10000000;

    int *arr = generate_random_array(lower_bound, upper_bound, size);

    // copy the same array for another sort algorithm
    int *arr2 = (int *)malloc(size * sizeof(int));
    for (int g = 0; g < size; g++)
    {
        arr2[g] = arr[g];
    }

    // carry out mergesort

    clock_t t = clock();

    merge_sort(arr, 0, size - 1, &comparisonCount);

    t = clock() - t;

    double time_taken = ((double)t) / CLOCKS_PER_SEC;

    printf("\nThe elapsed time for merge_sort is: %6.3f seconds", time_taken);

    printf("\nNumber of comparisons for merge_sort is: %lld", comparisonCount);

    printf("\nSorted: %d", is_sorted_array(arr, 0, size - 1));

    free(arr);

    clock_t h = clock();

    // carry out hybrid sort

    hybrid_sort(arr2, 0, size - 1, 4, &comparisonCount2);

    h = clock() - h;

    double time_taken2 = ((double)h) / CLOCKS_PER_SEC;

    printf("\n\nThe elapsed time for hybrid_sort is: %6.3f seconds", time_taken2);

    printf("\nNumber of comparisons for hybrid_sort is: %lld", comparisonCount2);

    printf("\nSorted: %d", is_sorted_array(arr2, 0, size - 1));

    free(arr2);

    return 0;
}

int *generate_random_array(int min, int max, int number_of_elements)
{

    int *arr = (int *)malloc(number_of_elements * sizeof(int));

    srand(time(NULL));

    for (int i = 0; i < number_of_elements; i++)
        arr[i] = rand() % (max - min) + min;
    return arr;
}

int is_sorted_array(int arr[], int l, int r)
{
    int isIncreasing = 1;
    for (int i = l; i < r; i++)
    {
        if (arr[i + 1] < arr[i])
        {
            isIncreasing = 0;
        }
    }
    return isIncreasing;
}

void merge_sort(int arr[], int l, int r, long *comparisonCount)
{
    int mid = (l + r) / 2;

    if (r - l <= 0)
        return;
    else
    {
        merge_sort(arr, l, mid, comparisonCount);
        merge_sort(arr, mid + 1, r, comparisonCount);
    }
    merge(arr, l, mid, r, comparisonCount);
}

void merge(int arr[], int l, int mid, int r, long *comparisonCount)
{
    int num1 = mid - l + 1;
    int num2 = r - mid;

    // create temp arrays for left and right sub array
    int *L = (int *)malloc(num1 * sizeof(int));
    int *R = (int *)malloc(num2 * sizeof(int));

    // initialize values for left and right sub array
    for (int i = 0; i < num1; i++)
        L[i] = arr[l + i];
    for (int j = 0; j < num2; j++)
        R[j] = arr[mid + 1 + j];

    int i = 0, j = 0, k = l;

    while (i < num1 && j < num2) // while either half is not empty
    {
        (*comparisonCount)++;

        if (L[i] <= R[j])    // 1st element of 1st half is <=
            arr[k] = L[i++]; // 1st element of 1st half joins end of merged list

        else                 // 1st element of 1st half is greater
            arr[k] = R[j++]; // 1st element of 2nd half joins end of merged list

        k++;
    }

    // copy remaining elements of left to end of merged list
    while (i < num1)
        arr[k++] = L[i++];

    // copy remaining elements of right to end of merged list
    while (j < num2)
        arr[k++] = R[j++];

    free(L);
    free(R);
}

void swap(int *ptr1, int *ptr2)
{
    int temp;

    temp = *ptr1;

    *ptr1 = *ptr2;

    *ptr2 = temp;
}

void insertion_sort(int arr[], int l, int r, long *comparisonCount)
{
    if (l >= r) // if array length is <=1
        return;

    int key;

    for (int i = l; i <= r; i++)
    {
        key = arr[i];
        int j = i - 1;

        while (j >= l && arr[j] > key)
        {
            (*comparisonCount)++;
            arr[j + 1] = arr[j];
            j = j - 1;
        }

        arr[j + 1] = key;
    }
}

void hybrid_sort(int arr[], int l, int r, int S, long *comparisonCount)
{

    if (r - l + 1 <= S)
    { // if size of sub array is <= S, perform insertion sort
        insertion_sort(arr, l, r, comparisonCount);
    }

    else
    { // if size of array > S, perform merge sort
        int mid = (l + r) / 2;
        hybrid_sort(arr, l, mid, S, comparisonCount);
        hybrid_sort(arr, mid + 1, r, S, comparisonCount);
        merge(arr, l, mid, r, comparisonCount);
    }
}
