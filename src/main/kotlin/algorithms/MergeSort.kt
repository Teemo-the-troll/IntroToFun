package algorithms

class MergeSort<T>(val comparator: Comparator<T>) {
    fun mergeSort(list: List<T>): List<T> {
        if (list.size <= 1) {
            return list
        }

        val middle = list.size / 2
        val left = list.subList(0,middle);
        val right = list.subList(middle,list.size);

        return merge(mergeSort(left), mergeSort(right))
    }

    fun merge(left: List<T>, right: List<T>): List<T>  {
        var indexLeft = 0
        var indexRight = 0
        val newList : MutableList<T> = mutableListOf()

        while (indexLeft < left.count() && indexRight < right.count()) {
            if (comparator.compare(left[indexLeft], right[indexRight]) < 0) {
                newList.add(left[indexLeft])
                indexLeft++
            } else {
                newList.add(right[indexRight])
                indexRight++
            }
        }

        while (indexLeft < left.size) {
            newList.add(left[indexLeft])
            indexLeft++
        }

        while (indexRight < right.size) {
            newList.add(right[indexRight])
            indexRight++
        }
        return newList;
    }


}
