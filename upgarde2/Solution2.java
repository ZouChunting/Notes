package leetcode;

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


    public static void main(String args[]){
        Solution2 solution2=new Solution2();

        int[] nums={5,7,7,8,8,10};
//        solution2.search(nums,1);
        solution2.searchRange(nums,8);
    }
}
