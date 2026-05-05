Design Document: Option B(Smart Scheduler)

Goal: Design a system that processes tasks based on urgency rather than arrival time

Data Structure: max binary heap implemented using an array
- This ensures that the tasks with the highest urgency are at the top of the heap.

Time Complexities
- insertion: O(log n)
  - when new tasks are put inside the heap, they are placed at the end of the heap. It must bubble up the heap to be placed in the correct position
  - in the worst case, the new task has the highest priority, which will travel from the leaf to the root, which happens in O(log n) time
- removal: O(log n)
  - when a task is finished, the root is removed, and is replaced by the last element in the heap. It must bubble down to its correct position
  - in the worst case, the new root travels the full length of the tree, resulting in O(log n)
- space complexity: O(n)
  - array with n tasks
 
Trade offs
- In an unsorted list, insertion is O(1), while removal is slow at O(n)
- In a sorted list, insertion is slow at O(n), while removal is O(1)
- for a heap, insertion and removal are O(log n)

