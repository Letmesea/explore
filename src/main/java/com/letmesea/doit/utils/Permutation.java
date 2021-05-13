package com.letmesea.doit.utils;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static List<int[]> nchoosek(int[] array, int k){
        int n = array.length;
        if(n > 31){
            throw new IllegalArgumentException("N must be less than or equal to 31");
        }
        checknk(n, k);
        if(k == 0){
            return new ArrayList<>();
        }
        if(n == k){
            List<int[]> combList = new ArrayList<int[]>(1);
            combList.add(java.util.Arrays.copyOf(array, k));
            return combList;
        }
        int combNum = nchoosek(n, (k > (n - k)) ? n - k : k);
        int bits = Integer.MAX_VALUE >> (31 - n);
        List<int[]> combList = new ArrayList<int[]>(combNum);
        for(int i = 0; i < bits; i++){
            if(Integer.bitCount(i) != k){
                continue;
            }
            int[] comb = new int[k];
            int index = 0;
            int iTemp = i;
            while(iTemp != 0){
                int lowest = iTemp & -iTemp;
                comb[index++] = array[(int)(Math.log(lowest) / Math.log(2))];
                iTemp &= iTemp - 1;
            }
            combList.add(comb);
        }
        return combList;
    }
    public static void checknk(int n, int k){
        if(k < 0 || k > n){ // N must be a positive integer.
            throw new IllegalArgumentException("K must be an integer between 0 and N.");
        }
    }
    public static int nchoosek(int n, int k){
        if(n > 31){
            throw new IllegalArgumentException("N must be less than or equal to 31");
        }
        checknk(n, k);
        k = (k > (n - k)) ? n - k : k;
        if(k <= 1){  // C(n, 0) = 1, C(n, 1) = n
            return k == 0 ? 1 : n;
        }
        int limit = Integer.MAX_VALUE >> (31 - n);
        int cnk = 0;
        for(int i = 3; i < limit; i++){
            if(Integer.bitCount(i) == k){
                cnk++;
            }
        }
        return cnk;
    }
}
