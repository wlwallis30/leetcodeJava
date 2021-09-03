#  LeetCode solution new notes
<!-- ![Big O](pics/118BrutalForceFormula.jpg | width=250) -->
[//]: <another comment>
<!-------- comment here, sample -->

>## @119@ Pascal triangle:
Brutal force big O:   

<img src="pics/118BrutalForceFormula.jpg" width="800" title="BrutalForce Big O">   

>Brutal force code:

```java
private int getNum(int row, int col) {
    if (row == 0 || col == 0 || row == col) {
      return 1;
    }

    return getNum(row - 1, col - 1) + getNum(row - 1, col);
  }

  public List<Integer> getRow(int rowIndex) {
    List<Integer> ans = new ArrayList<>();

    for (int i = 0; i <= rowIndex; i++) {
      ans.add(getNum(rowIndex, i));
    }

    return ans;
  }
```

Explain: 
so for $k$ row, in the loop `for(int i = 0; i <= rowIndex; i++)`, you need to do $k=0-> rownIdx$, that will be:
$$T(k, 0) + T(k, 1) + T(k, 2) + ..+ T(k, k-1) + T(k, k)$$
And each of $T(k,i)$ will compute with 2 items:
$$T(k, i) = T(k-1, i-1) + T(k-1, i)$$
so it will be $T(k-1,i)$ and $T(k-1, i-1)$ will go down to $T(k-2,)$, there are $k$ calls to recursive function till $1$, so roughly it is $2^k$ 

>## @260 Single number III:

1. x & -x keep the right most bit

<img src="pics/260BitMask1.jpg" width="800" title="BrutalForce Big O">

2. bitMaskForBoth

<img src="pics/260BitMask2.jpg" width="800" title="BrutalForce Big O">

3. combined step 1 and 2. 

<img src="pics/260BitMask3.jpg" width="800" title="BrutalForce Big O">

4. having these two will produce x and y, please check code:

```java
// bitmask which will contain only x, other repeated a will be off set by ^ Xor since it appears twice.
    for (int num : nums) if ((num & diffTeller) != 0) x ^= num;
    //now 
    y = bitmaskForBoth ^ x;
```
>## @258 Add digits:
Math solution:

<img src="pics/258DigitalRoot.jpg" width="800" title="BrutalForce Big O">