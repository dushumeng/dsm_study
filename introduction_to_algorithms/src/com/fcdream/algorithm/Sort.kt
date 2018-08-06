package com.fcdream.algorithm

fun main(args: Array<String>) {
    val list: ArrayList<Int> = arrayListOf()
    for (i in 0 until 49) {
        list.add((Math.random() * 100 + 1).toInt())
    }
    printList(list, "原始数组")
    insertionSort(list.copy())
    mergeSort(list.copy())
}

fun ArrayList<Int>.copy(): ArrayList<Int> {
    val newList = arrayListOf<Int>()
    newList.addAll(this)
    return newList
}

/**
 * 插入排序
 */
fun insertionSort(list: ArrayList<Int>) {
    val startTime = System.nanoTime()
    for (index in 1 until list.size) {
        var mIndex = index
        if (mIndex - 1 >= 0 && list[mIndex] < list[mIndex - 1]) {
            var temp = list[mIndex]
            var newIndex = mIndex - 1
            while (newIndex >= 0 && list[newIndex] > temp) {
                list[newIndex + 1] = list[newIndex]
                newIndex -= 1
            }
            list[newIndex + 1] = temp
        }
    }
    val endTime = System.nanoTime()
    printList(list, "插入排序，耗时：${endTime - startTime}")
}

/**
 * 归并排序
 */
fun mergeSort(list: ArrayList<Int>) {
    val startTime = System.nanoTime()
    mergeSort_sort(list, 0, list.size - 1, list.copy())
    val endTime = System.nanoTime()
    printList(list, "合并排序，耗时：${endTime - startTime}")
}

fun mergeSort_sort(list: ArrayList<Int>, left: Int, right: Int, midList: ArrayList<Int>) {
    if (left < right) {
        val mid = (left + right) / 2
        mergeSort_sort(list, left, mid, midList)
        mergeSort_sort(list, mid + 1, right, midList)
        mergeSort_merge(list, left, mid, right, midList)
    }
}

fun mergeSort_merge(list: ArrayList<Int>, left: Int, mid: Int, right: Int, midList: ArrayList<Int>) {
    var i = left
    var j = mid + 1
    var k = 0
    while (i <= mid && j <= right) {
        if (list[i] <= list[j]) {
            midList[k++] = list[i++]
        } else {
            midList[k++] = list[j++]
        }
    }
    while (i <= mid) {
        midList[k++] = list[i++]
    }
    while (j <= mid) {
        midList[k++] = list[j++]
    }
    for (i in 0 until k) {
        list[left + i] = midList[i]
    }
}

fun printList(list: ArrayList<Int>, tag: String) {
    if (list.isEmpty()) {
        return
    }
    println("------$tag------")
    for (i in 0 until list.size) {
        when (i) {
            0 -> print("[${list[i]}")
            list.size - 1 -> print(",${list[i]}]")
            else -> print(",${list[i]}")
        }
    }
    println()
}