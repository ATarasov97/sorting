# sorting
Sorting benchmark

Every sort work with N=1000(Counting with N = 1000000) elements in array. TimeUmit is MICROSECONDS

|Sort   |NarrowRange   |LongString   |ReverseHeap   |Sorted        |ReverseSorted   |RandInt   |RandString   |
|-------|--------------|-------------|--------------|--------------|----------------|----------|-------------|
|Counting|50098,495|-|121555,090|15177,598|18720,171|129316,723|-|
|Heap|91,950|200,325|101,267|98,847|96,795|100,476|167,900|
|LSD|28,026|17310,133|61,987|58,448|59,622|58,687|795,099|
|Merge|67,061|79,842|62,712|58,524|62,237|62,103|74,178|
|QuickSortInsert|28,350|65,334|25,986|33,283|22,526|24,568|69,388|
|QuickSortThreeWay|17,364|184,881|100,361|102,787|101,752|102,136|174,391|
