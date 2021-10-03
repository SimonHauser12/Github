def bubbleSort(list):
    n = len(list)
    for i in range(n):
        for j in range(0, n-i-1):
            if list[j] > list[j+1] :
                list[j], list[j+1] = list[j+1], list[j]
    return list

def insertionSort(list):
    j = -1
    key = list[0]
    for i in range(1, len(list)):
        key = list[i]
        j = i-1
        while(j >= 0 and list[j] > key):
            list[j+1] = list[j]
            j -= 1
        list[j+1] = key
    return list

def selectionSort(list):
    for i in range(len(list)):
        min_idx = i
        for j in range(i+1, len(list)):
            if list[min_idx] > list[j]:
                min_idx = j
      
        list[i], list[min_idx] = list[min_idx], list[i]
    return list
    
if __name__ == "__main__":
    unsortedList = [2,5,8,6,4,8,9,10]

    print(unsortedList)

    bubbleList = bubbleSort(unsortedList)
    print(bubbleList)
    unsortedList = [2,5,8,6,4,8,9,10]
    insertionList = insertionSort(unsortedList)
    print(insertionList)
    unsortedList = [2,5,8,6,4,8,9,10]
    selectionList = selectionSort(unsortedList)
    print(selectionList)

    
    
    
    