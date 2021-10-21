package com.wlwallis30.leetcode;

import java.lang.reflect.Array;
import java.util.*;

public class AddTwoNums {
	
	public ListNode addTwoSums_2(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(-1);
		int carryBit = 0;
		ListNode curNode = dummyHead;
		
		while(l1 != null || l2 != null) {
			int digit1 = l1 == null ? 0 : l1.val;
			int digit2 = l2 == null ? 0 : l2.val;
			
			int sum = digit1 + digit2 + carryBit;
			carryBit = sum >= 10 ? 1 : 0;
			
			curNode.next = new ListNode(sum % 10);
			curNode = curNode.next;
			
			if(l1 != null) l1 = l1.next;
			if(l2 != null) l2 = l2.next;
			
		}
		
		if(carryBit == 1) curNode.next = new ListNode(1);
		
		return dummyHead.next;
	}
	
	public String addBinary_67(String strA, String strB) {
		String res = "";
		int m = strA.length()-1, n = strB.length()-1;
		int carry = 0;
		while(m >= 0 || n >=0) {
			int bitA = m >= 0 ? strA.charAt(m--) - '0' : 0;
			int bitB = n >= 0 ? strB.charAt(n--) - '0' : 0;

			int sum = bitA + bitB + carry;
			res = Integer.toString(sum % 2) + res;
			
			carry = sum / 2;
		}
		
		res = carry == 1 ? "1" + res : res;
		
		return res;
	}
	
	public String multiply_43(String num1, String num2) {
		String res = "";
		int m = num1.length(), n = num2.length();
		int[] vals = new int[m+n];
		//Arrays.fill(vals, 100);
		//Array.setInt(vals, 0, 1000); 

    //result are stored in the right order in vals, e.g. 213 is sotre in [2, 1, 3]
		// so m-1, n-1 will be pushed to m-1 + n-1 +1 = m+n-1 position in array, which is the last idx
		for(int i=m-1; i>=0; i--) {
			for(int j=n-1; j>=0; j--) {
				int mul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
				int highIdx = i + j, lowIdx = i + j+ 1;
				int sum = vals[lowIdx] + mul;
				vals[highIdx] += sum / 10;
				vals[lowIdx] = sum % 10;
			}
		}
		
		for(int val: vals) {
		  //the highest num is store at 0 idx, it might be 0, e.g. [0, 2, 1, 3], so you need to skip 0
			if(!res.isEmpty() || val != 0) res = res + Integer.toString(val);
		}
		return res.isEmpty() ? "0": res;
    }
	
	public int getSum_371(int a, int b) {
		if (b == 0) return a;
        int sum = a ^ b;
        int carry = (a & b & 0x7fffffff) << 1;
        return getSum_371(sum, carry);    
        //  only one line solution
        //return b == 0 ? a : getSum(a ^ b, (a & b & 0x7fffffff) << 1);
    }

	public int[] plusOne_66(int[] digits) {
		int len = digits.length;
		for(int i = digits.length-1; i >= 0; i--) {
			if(digits[i] < 9) {
				++digits[i];
				return digits;
			}
			digits[i] = 0;
		}
		int[] res = new int[len+1];
		res[0] = 1;
		return res;
	}

	public ListNode plusOne_369(ListNode head) {
	  ListNode cur = head;
	  // the first non 9 number from the right
	  ListNode theRightNot9 = null;
	  // find the first non 9 number along the list
	  while(cur != null) {
	    // this can also jump the middle 9s as long as it can find an non 9 number b4 right most;
	    if(cur.val != 9) theRightNot9 = cur;
	    cur = cur.next;
    }

    // handle addition
    // the case of all 9 in the list
    if (theRightNot9 == null) {
	    theRightNot9 = new ListNode(0);
	    theRightNot9.next = head;
	    head = theRightNot9;
    }
    ++theRightNot9.val;
	  // the case of all 9 in the list, now setting all to 0 except the head
    cur = theRightNot9.next;
    while(cur != null) {
      cur.val = 0;
      cur = cur.next;
    }
	  return head;
	}

	public String addStrings415(String num1, String num2) {
		StringBuilder res = new StringBuilder();

		int carry = 0;
		int idx1 = num1.length() - 1;
		int idx2 = num2.length() - 1;
		while (idx1 >= 0 || idx2 >= 0) {
			int x1 = idx1 >= 0 ? num1.charAt(idx1) - '0' : 0;
			int x2 = idx2 >= 0 ? num2.charAt(idx2) - '0' : 0;
			int value = (x1 + x2 + carry) % 10;
			carry = (x1 + x2 + carry) / 10;
			res.append(value);
			idx1--;
			idx2--;
		}

		if (carry != 0)
			res.append(carry);

		return res.reverse().toString();
	}
}
