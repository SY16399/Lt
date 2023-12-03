package org.example.WUYONG;

import java.util.*;

public class 了28 {
    /*static class Solution {

        public int longestConsecutive(int[] nums) {
            //Set<Integer> nums_set = new HashSet<Integer>();
            HashMap<Integer,Integer> nums_map = new HashMap<Integer,Integer>();
            for(var num: nums){
                nums_map.put(num,1);

            }
            int maxlongSize = 0;
            for(var num:nums){
                if(!nums_map.containsKey(num-1)){
                    int currNum = num;
                    int currstack = 1;
                    while(nums_map.containsKey(currNum+1)){
                        currNum++;
                        currstack++;
                    }
                    maxlongSize = Integer.max(maxlongSize,currstack);
                }
            }
            return maxlongSize;

        }

        public static void main(String[] args) {
            String a = new String();
            a.length();

        }

    }*/

     /*   public static void main(String[] args){
            List<Integer> list = findAnagrams("baa","aa");
            System.out.println(list.toString());
        }*/
     static class Solution {
         /*static class All{
             public int all = 0;

         }
         public static int subarraySum(int[] nums, int k) {
             int len = nums.length;
             All all = new All();
             dfs(nums,0,k,0,all);
             return all.all;
         }
         public static void dfs(int[] nums,int i,int k,int sum,All all){
             if (i < 0){
                 return;
             }
             if(sum == k){
                 all.all= all.all+1;
                 return;
             }
             else if(sum < k ){
                 for(int j = i ;j<nums.length;++j){
                     dfs(nums,j--,k,sum+nums[j],all);
                 }
              }else{
                 return;
             }
         }

         public static void main(String[] args) {
             System.out.println(subarraySum(new int[]{1,1,1},2));
         }*/


         public static void setZeroes(int[][] matrix) {
             LinkedList<int[]> list = new LinkedList<int[]>();
             int m = matrix.length;
             int n = matrix[0].length;
             for(int i = 0; i< m;i++){
                 for(int j = 0 ;j<n;j++){
                     if(matrix[i][j]==0){
                         int[] arr = new int[]{i,j};
                         list.offer(arr);
                     }
                 }
             }
             while(!list.isEmpty()){
                 int[] arr = list.pop();
                 System.out.println(Arrays.toString(arr));
                 int line = arr[0];
                 int row = arr[1];
                 for(int i = 0;i<n;i++){
                     matrix[line][i] = 0;
                 }
                 for(int i = 0;i<m;i++){
                     matrix[i][row] = 0;
                 }
             }

         }
         public static void main(String[] args){
             int[][] arr = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
             setZeroes(arr);
             for (int i = 0; i < arr.length; i++) {
                 System.out.println(Arrays.toString(arr[i]));
             }

         }



     }

    }


