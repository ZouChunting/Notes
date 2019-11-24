package leetcode;

import java.util.*;

public class Solution2 {
    //33.搜索旋转排序数组
    public int search(int[] nums, int target) {
        int res=bSearch(nums,target,0,nums.length-1);
        System.out.println(res);
        return res;
    }
    //nums = [4,5,6,7,0,1,2], target = 0
    public int bSearch(int[] nums,int target,int low,int high){
        if(low>high){
            return -1;
        }
        int mid=low+(high-low)/2;
        System.out.println(mid);
        if(target==nums[mid]){
            return mid;
        }
        if(nums[low]<=nums[mid]){
            //左边有序
            if(target<nums[low]||target>nums[mid]){
                return bSearch(nums,target,mid+1,high);
            }else {
                return bSearch(nums,target,low,mid-1);
            }
        }else {
            //右边有序
            if(target<nums[mid]||target>nums[high]){
                return bSearch(nums,target,low,mid-1);
            }else {

                return bSearch(nums,target,mid+1,high);
            }
        }
    }

    //34. 在排序数组中查找元素的第一个和最后一个位置
    //nums = [5,7,7,8,8,10], target = 8
    public int[] searchRange(int[] nums, int target) {
        int[] res={-1,-1};
        int low=0;
        int high=nums.length-1;
        int index=-1;
        while (low<=high){
            int mid=low+(high-low)/2;
            if(target<nums[mid]){
                high=mid-1;
            }else if(target>nums[mid]){
                low=mid+1;
            }else {
                index=mid;
                break;
            }
        }
        if(index!=-1){
            int i=index;
            while (i>=0&&nums[i]==target){
                i--;
            }
            int j=index;
            while (j<nums.length&&nums[j]==target){
                j++;
            }
            res[0]=i+1;
            res[1]=j-1;
        }

        return res;
    }

    //30. 串联所有单词的子串
    //barfoothefoobarman
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res=new ArrayList<>();
        if(s==null||s.length()==0||words.length==0||words[0].length()==0) return res;

        int word_len=words[0].length();
        int word_num=words.length;

        //存储数组中所有单词
        HashMap<String,Integer> all_words=new HashMap<>();
        for(int i=0;i<word_num;i++){
            if(all_words.containsKey(words[i])){
                all_words.put(words[i],all_words.get(words[i])+1);
            }else {
                all_words.put(words[i],1);
            }
        }

        //存储子字符串包含单词
        HashMap<String,Integer> s_words=new HashMap<>();

        int left=0;  //左指针
        int index=0;  //子字符串起始位置
        int right=word_len-1;  //右指针
        int num=0;  //已包含单词数
        while(right<s.length()){
            String tmp=s.substring(left,right+1);
            if(all_words.containsKey(tmp)){
                if(num==0){
                    index=left;
                }
                if(s_words.containsKey(tmp)){
                    if(all_words.get(tmp)>s_words.get(tmp)){
                        s_words.put(tmp,s_words.get(tmp)+1);
                        num++;
                        left+=word_len;
                        right+=word_len;
                    }else {
                        s_words.clear();
                        num=0;
                        left=index+1;
                        right=left+word_len-1;
                    }
                }else {
                    s_words.put(tmp,1);
                    num++;
                    left+=word_len;
                    right+=word_len;
                }
            }else {
                s_words.clear();
                num=0;
                left=++index;
                right=left+word_len-1;
            }
            if(num==word_num){
                res.add(index);
                s_words.clear();
                num=0;
                left=index+1;
                right=left+word_len-1;
            }
        }
        System.out.println(res);
        return res;
    }

    //35. 搜索插入位置
    public int searchInsert(int[] nums, int target) {
        int res=bSearchForSearchInsert(nums,target,0,nums.length-1);
        System.out.println(res);
        return res;
    }

    //二分查找
    public int bSearchForSearchInsert(int[] nums,int target,int low,int high){
        if(low>=high){
            if(target>nums[low]) return low+1;
            return low;
        }
        int mid=low+(high-low)/2;
        if(target<nums[mid]){
            return bSearchForSearchInsert(nums,target,low,mid-1);
        }else if(target>nums[mid]){
            return bSearchForSearchInsert(nums,target,mid+1,high);
        }else{
            return mid;
        }
    }

    //31. 下一个排列
    public void nextPermutation(int[] nums) {
        int index=nums.length-2;
        while (index>=0&&nums[index]>=nums[index+1]){
            index--;
        }
        if(index>=0){
            int j=nums.length-1;
            while (j>=0&&nums[j]<=nums[index]){
                j--;
            }
            swap(nums,index,j);
        }
        //翻转数组
        reverse(nums,index+1,nums.length-1);
    }
    public void swap(int[] nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
    public void reverse(int[] nums,int i,int j){
        while (i<j){
            swap(nums,i,j);
            i++;
            j--;
        }
    }

    //36. 有效的数独
    public boolean isValidSudoku(char[][] board) {

//        HashMap<Integer,Integer>[] row=new HashMap[9];
//        HashMap<Integer,Integer>[] column=new HashMap[9];
//        HashMap<Integer,Integer>[] box=new HashMap[9];

        HashSet[] row=new HashSet[9];
        HashSet[] column=new HashSet[9];
        HashSet[] box=new HashSet[9];
        for(int i=0;i<9;i++){
            row[i]=new HashSet<>();
            column[i]=new HashSet<>();
            box[i]=new HashSet<>();
        }

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                char num=board[i][j];
                if(num!='.'){
                    if(row[i].contains(num)||column[j].contains(num)||box[i/3*3+j/3].contains(num)){
                        return false;
                    }
                    row[i].add(num);
                    column[j].add(num);
                    box[i/3*3+j/3].add(num);
                }
            }
        }

        return true;
    }

    //42. 接雨水
    //[0,1,0,2,1,0,1,3,2,1,2,1]
    public int trap(int[] height) {
        int res=0;
        int left=1;
        int right=height.length-2;
        int left_max=0;
        int right_max=0;
        while (left<=right){
            left_max=Math.max(left_max,height[left-1]);
            right_max=Math.max(right_max,height[right+1]);
            if(left_max<right_max){
                if(height[left]<left_max){
                    res=res+(left_max-height[left]);
                }
                left++;
            }else {
                if(height[right]<right_max){
                    res=res+(right_max-height[right]);
                }
                right--;
            }
        }
        System.out.println(res);
        return res;
    }

    //38. 报数
    public String countAndSay(int n) {
        //StringBuilder res=new StringBuilder("1");
        String res="1";
        for(int i=2;i<n+1;i++){
            StringBuilder tmp=new StringBuilder();
            int count=0;
            for(int j=0;j<res.length();j++){
                if(j==0||res.charAt(j)==res.charAt(j-1)){
                    count++;
                }else {
                    tmp.append(count).append(res.charAt(j-1));
                    count=1;
                }
            }
            tmp.append(count).append(res.charAt(res.length()-1));
            res=tmp.toString();
        }
        System.out.println(res);
        return res;
    }

    //39. 组合总和
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Stack<Integer> tmp=new Stack<>();
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(candidates);
        recursionForCombinationSum(candidates,target,0,tmp,res);

        return res;
    }

    public void recursionForCombinationSum(int[] candidates, int target,int start, Stack<Integer> tmp, List<List<Integer>> res){
        if(target==0){
            res.add(new ArrayList<>(tmp));
            return;
        }
        for(int i=start;i<candidates.length&&target-candidates[i]>=0;i++){
            tmp.add(candidates[i]);
            recursionForCombinationSum(candidates,target-candidates[i],i,tmp,res);
            tmp.pop();
        }

    }

    //40. 组合总和 II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Stack<Integer> tmp=new Stack<>();
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(candidates);
        recursionCombinationSum2(candidates,target,0,tmp,res);

        return res;
    }

    public void recursionCombinationSum2(int[] candidates,int target,int start,Stack<Integer> tmp,List<List<Integer>> res){
        if(target==0){
            res.add(new ArrayList<>(tmp));
            return;
        }
        for(int i=start;i<candidates.length&&target-candidates[i]>=0;i++){
            if(!(i>start&&candidates[i]==candidates[i-1])){
                tmp.add(candidates[i]);
                recursionCombinationSum2(candidates,target-candidates[i],i+1,tmp,res);
                tmp.pop();
            }
        }
    }

    public static void main(String args[]){
        Solution2 solution2=new Solution2();

//        int[] nums={5,7,7,8,8,10};
//        solution2.search(nums,1);
//        solution2.searchRange(nums,8);

//        String[] words = {"ab","ba","ab","ba"};
//        solution2.findSubstring("abaababbaba",words);

//        int[] nums={1,2,3};
//        solution2.searchInsert(nums,2);
//        solution2.nextPermutation(nums);
//        for(int i=0;i<nums.length;i++){
//            System.out.println(nums[i]);
//        }
//        char[][] board=new char[9][];
//        solution2.isValidSudoku(board);
//        int[] nums={0,1,0,2,1,0,1,3,2,1,2,1};
//        solution2.trap(nums);
//        solution2.countAndSay(5);

        int[] candidates={2,3,6,7};
        solution2.combinationSum2(candidates,8);
    }
}
