package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static void main(String args[]){
        Solution2 solution2=new Solution2();

//        int[] nums={5,7,7,8,8,10};
//        solution2.search(nums,1);
//        solution2.searchRange(nums,8);

//        String[] words = {"ab","ba","ab","ba"};
//        solution2.findSubstring("abaababbaba",words);

        int[] nums={1,3,5,6};
        solution2.searchInsert(nums,2);
    }
}
