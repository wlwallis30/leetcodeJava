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
			if(!res.isEmpty() || val != 0) res = res + Integer.toString(val);
		}
		return res.isEmpty() ? "0": res;
    }
	
	public int getSum_371(int a, int b) {
		if (b == 0) return a;
        int sum = a ^ b;
        int carry = (a & b & 0x7fffffff) << 1;
        return getSum(sum, carry);    
        //  only one line solution
        //return b == 0 ? a : getSum(a ^ b, (a & b & 0x7fffffff) << 1);
    }
}
