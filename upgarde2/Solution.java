package leetcode;

import java.util.*;

class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        //存储结果
        int[] res=new int[2];

        //存储补数和数组中的值
        HashMap<Integer,Integer> hash=new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(hash.containsKey(nums[i])){
                res[0]=hash.get(nums[i]);
                res[1]=i;
                return res;
            }
            hash.put(target-nums[i],i);
        }

        return res;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res=new ListNode(0);
        ListNode cursor=res;
        int tmp=0;
        while(l1!=null||l2!=null||tmp!=0){
            int num1=l1!=null?l1.val:0;
            int num2=l2!=null?l2.val:0;

            int sum=num1+num2+tmp;
            tmp=sum/10;
            cursor.next=new ListNode(sum%10);
            cursor=cursor.next;

            if (l1!=null) l1=l1.next;
            if (l2!=null) l2=l2.next;
        }
        return res.next;
    }

    public int lengthOfLongestSubstring(String s) {
        int res=0;

        char[] chars=s.toCharArray();
        int left=0;
        for(int i=0;i<chars.length;i++){
            for(int j=left;j<i;j++){
                if(chars[j]==chars[i]){
                    res=Math.max(res,i-left);
                    left=j+1;
                    break;
                }
            }
        }
        return res;
        //return Math.max(res,chars.length-left);
        //注：这里有几个特殊情况
        //1."a"
        //2."ab"
        //3."aab"
        //4."aaabc"
        //5."aaabcd"
        //...
        //可以归结为一种，在扫描至数组末尾时，未发现相等数，则少刷新一次。
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double res;
        int len1=nums1.length;
        int len2=nums2.length;

        int target1=(len1+len2+1)/2;
        int target2=(len1+len2+2)/2;

        if(len1==0){
            nums1=new int[]{nums2[len2-1]};
        }else if(len2==0){
            nums2=new int[]{nums1[len1-1]};
        }

        int i=0;
        int j=0;
        int median1=0;
        int median2=0;
        while (target1>0||target2>0){
            //找到较小的数
            int tmp=0;

            if(i==len1){

            }



            System.out.println("i:"+i);
            System.out.println("j:"+j);
            System.out.println(i<len1);
            //System.out.println(nums1[i]<=nums2[j]);
            if(i<len1&&j<len2?nums1[i]<=nums2[j]:true){
                tmp=nums1[i];
                i++;
            }else {
                tmp=nums2[j];
                j++;
            }

            if(target1==1){
                median1=tmp;
            }
            if(target2==1){
                median2=tmp;
            }

            target1--;
            target2--;
        }

        res=(median1+median2)*1.0/2;
        return res;
    }

    public int reverse(int x) {
        int res=0;
        int tmp;

        while(x!=0){
            tmp=x%10;
            if(res>Integer.MAX_VALUE/10||(res==Integer.MAX_VALUE/10&&tmp>Integer.MAX_VALUE%10)){
                return 0;
            }
            if(res<Integer.MIN_VALUE/10||(res==Integer.MIN_VALUE/10&&tmp<Integer.MIN_VALUE%10)){
                return 0;
            }
            res=res*10+tmp;
            x/=10;
        }

        return res;
    }

    public String longestPalindrome(String s) {
        int len=s.length();

        if(len==0){
            return "";
        }

        if(len==1){
            return s;
        }

        char[] chars=s.toCharArray();
        String res="";
        int tmp=0;
        int tmp1=0;
        int tmp2=0;

        for(int i=0;i<len-1;i++){
            if(i==0){
                tmp=1;
                res=chars[0]+"";

                tmp2=expand(chars,i,i+1);
                if(tmp2*2>=tmp){
                    res=s.substring(i+1-tmp2,i+tmp2+1);
                    tmp=tmp2*2;
                }
            }else {
                tmp1=expand(chars,i-1,i+1);
                if(tmp1*2+1>=tmp){
                    res=s.substring(i-tmp1,i+tmp1+1);
                    tmp=tmp1*2+1;
                }

                tmp2=expand(chars,i,i+1);
                if(tmp2*2>=tmp){
                    res=s.substring(i+1-tmp2,i+tmp2+1);
                    tmp=tmp2*2;
                }
            }
        }

        return res;
    }

    public int expand(char[] chars,int left,int right){
        int res=0;
        while(left>=0&&right<=chars.length-1){
            if(chars[left]==chars[right]){
                res+=1;
                left--;
                right++;
            }else {
                break;
            }
        }
        return res;
    }

    public String convert(String s, int numRows) {
        int sLen=s.length();
        char[] chars=s.toCharArray();
        String res="";

        if(numRows==1){
            res=s;
        }else {
            for(int i=0;i<numRows;i++){
                for(int j=i;j<sLen;j+=2*numRows-2){
                    res=res+chars[j];
                    if(i!=0&&i!=numRows-1){
                        if(j+(numRows-i-2)*2+2<sLen){
                            res=res+chars[j+(numRows-i-2)*2+2];
                        }
                    }
                }
            }
        }

        return res;
    }

    public int myAtoi(String str) {
        str=str.trim();
        int num=1;
        try{
            char[] chars=str.toCharArray();
            String res="";
            if(chars.length==0||(chars[0]!='-'&&chars[0]!='+'&&!Character.isDigit(chars[0]))){
                return 0;
            }
            for(int i=0;i<chars.length;i++){
                if(chars[i]=='-'){
                    num=-1;
                }else if(chars[i]=='+'){

                }else if(Character.isDigit(chars[i])){
                    res=res+chars[i];
                }else {
                    break;
                }
            }

            if(res.length()==0){
                return 0;
            }
            return Integer.parseInt(res)*num;
        }catch (Exception e){
            if(num==1){
                return Integer.MAX_VALUE;
            }else {
                return Integer.MIN_VALUE;
            }
        }
    }

    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int tmp=x;
        int res=0;
        while(x>0){
            res=res*10+x%10;
            x/=x;
        }
        return res==tmp;
//        StringBuilder sb=new StringBuilder(x+"");
//        return sb.toString().equals(sb.reverse().toString());
    }

    public String intToRoman(int num) {
        String res="";

        int[] nums=new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] strings=new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        for(int i=0;i<13;i++){
            while (num>=nums[i]){
                res=res+strings[i];
                num-=nums[i];
            }
        }

        return res;
    }

    public int romanToInt(String s) {
        int res;

        HashMap<Character,Integer> hashMap=new HashMap<>();
        hashMap.put('M',1000);
        hashMap.put('D',500);
        hashMap.put('C',100);
        hashMap.put('L',50);
        hashMap.put('X',10);
        hashMap.put('V',5);
        hashMap.put('I',1);
        //MCMXCIV
        char[] sChars=s.toCharArray();
        char tmp=sChars[sChars.length-1];
        res=hashMap.get(sChars[sChars.length-1]);
        for(int i=sChars.length-2;i>=0;i--){
            if(hashMap.get(sChars[i])<hashMap.get(tmp)){
                res=res-hashMap.get(sChars[i]);
            }else {
                res=res+hashMap.get(sChars[i]);
                tmp=sChars[i];
            }
        }

        return res;
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0){
            return "";
        }
        String res=strs[0];
        for(int i=1;i<strs.length;i++){
            while(strs[i].indexOf(res)!=0){
                res=res.substring(0,res.length()-1);
            }
        }

        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);//-4,-1,-1,0,1,2
        List<List<Integer>> res=new ArrayList<>();

        int left=0;
        int right=0;
        int sum=0;
        for(int i=0;i<nums.length-2;i++){
            if(i==0||(i>0&&nums[i]!=nums[i-1])){
                left=i+1;
                right=nums.length-1;
                sum=0-nums[i];
                while (left<right){
                    if(nums[left]+nums[right]==sum){
                        res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                        while(left<right&&nums[left]==nums[left+1]) left++;
                        while (left<right&&nums[right]==nums[right-1]) right--;
                        left++;
                        right--;
                    }else if(nums[left]+nums[right]<sum){
                        while(left<right&&nums[left]==nums[left+1]) left++;
                        left++;
                    }else {
                        while (left<right&&nums[right]==nums[right-1]) right--;
                        right--;
                    }
                }
            }
        }
        return res;
    }

    public List<String> letterCombinations(String digits) {
        List<String> res=new ArrayList<>();

        if(digits.length()==0){
            return res;
        }

        HashMap<Character,String> hashMap=new HashMap<>();
        hashMap.put('2',"abc");
        hashMap.put('3',"def");
        hashMap.put('4',"ghi");
        hashMap.put('5',"jkl");
        hashMap.put('6',"mno");
        hashMap.put('7',"pqrs");
        hashMap.put('8',"tuv");
        hashMap.put('9',"wxyz");

        List<String> tmp=new ArrayList<>();
        for(int i=0;i<digits.length();i++){
            tmp.add(hashMap.get(digits.charAt(i)));
        }
        recursionForLetterCombinations(tmp,res,"");

        return res;
    }

    public void recursionForLetterCombinations(List<String> tmp,List<String> res,String string){
        if(string.length()==tmp.size()){
            res.add(string);
        }else {
            String stmp=string;
            for(int i=0;i<tmp.get(string.length()).length();i++){
                recursionForLetterCombinations(tmp,res,stmp+tmp.get(string.length()).charAt(i));
            }
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left=head;
        ListNode right=head;
        for(int i=0;i<n;i++){
            right=right.next;
        }

        if(right==null){
            return left.next;
        }

        while(right.next!=null){
            left=left.next;
            right=right.next;
        }
        ListNode node=left.next.next;
        left.next=node;
        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res=new ListNode(0);
        ListNode head=res;
        while(l1!=null||l2!=null){
            if(l2==null||l1!=null&&l2!=null&&l1.val<=l2.val){
                head.next=l1;
                l1=l1.next;
            }else{
                head.next=l2;
                l2=l2.next;
            }
            System.out.println(head.val);
            head=head.next;
        }
        return res.next;
    }

    //1->2->3->4
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode next=head.next;
        head.next=swapPairs(next.next);
        next.next=head;

        return next;
    }

    public int threeSumClosest(int[] nums, int target) {
        //排序
        Arrays.sort(nums);
        int res=nums[0]+nums[1]+nums[2];
        int left=0;
        int right=0;
        int sum=0;
        for(int i=0;i<nums.length-2;i++){
            if(i==0||(i>0&&nums[i]!=nums[i-1])){
                left=i+1;
                right=nums.length-1;
                while(left<right){
                    sum=nums[i]+nums[left]+nums[right];
                    if(Math.abs(target-sum)<Math.abs(target-res)){
                        res=sum;
                    }
                    if(sum==target){
                        return target;
                    }else if(sum<target){
                        while (left<right&&nums[left]==nums[left+1]) left++;
                        left++;
                    }else {
                        while(left<right&&nums[right]==nums[right-1]) right--;
                        right--;
                    }
                }
            }
        }
        return res;
    }

    public boolean isValid(String s) {
//        while(s.contains("()")||s.contains("[]")||s.contains("{}")){
//            s=s.replace("()","");
//            s=s.replace("[]","");
//            s=s.replace("{}","");
//        }
//        return s.equals("");

        Stack<Character> stack=new Stack<>();
        char[] chars=s.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='('||chars[i]=='['||chars[i]=='{'){
                stack.push(chars[i]);
            }else {
                if(stack.empty()){
                    System.out.println("1");
                    return false;
                }else{
                    char tmp=stack.pop();
                    System.out.println(chars[i]);
                    System.out.println(tmp);
                    if(!((chars[i]==')'&&tmp=='(')||(chars[i]==']'&&tmp=='[')||(chars[i]=='}'&&tmp=='{'))){
                        System.out.println("2");
                        return false;
                    }
                }
            }
        }

        if(!stack.empty()){
            return false;
        }

        return true;
    }

    public List<String> generateParenthesis(int n) {
        List<String> res=new ArrayList<>();
        generateParenthesisRecursion("",n,n,res);

        return res;
    }

    public void generateParenthesisRecursion(String string,int left,int right,List<String> res){
        if(left==0&&right==0){
            res.add(string);
        }else if(left==0){
            generateParenthesisRecursion(string+")",left,right-1,res);
        }else {
            if(left<right){
                generateParenthesisRecursion(string+"(",left-1,right,res);
                generateParenthesisRecursion(string+")",left,right-1,res);
            }else {
                generateParenthesisRecursion(string+"(",left-1,right,res);
            }
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res=new ListNode(0);
        ListNode temp=res;

        mergeKListsRecursion(lists,temp);

        return res.next;
    }

    public void mergeKListsRecursion(ListNode[] lists,ListNode temp){
        int tmp=-1;
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null){
                if(tmp==-1){
                    tmp=i;
                }else if(lists[i].val<lists[tmp].val){
                    tmp=i;
                }
            }
        }
        if(tmp!=-1){
            temp.next=lists[tmp];
            System.out.println(temp.val);
            temp=temp.next;
            lists[tmp]=lists[tmp].next;
            mergeKListsRecursion(lists,temp);
        }
    }

    //1->2->3->4->5/2 1 3 4 5/3 2 1 4 5
    public ListNode reverseKGroup(ListNode head, int k) {
        //1.head 2.end 3.current
        ListNode res=head; //最后作为头节点的节点
        ListNode end=head; //已翻转链表部分的最末节点，这个值基本不变
        ListNode current=head.next;  //将要被置顶的节点
        while (k>1){
            System.out.println("pre:"+res.val);
            end.next=current.next;
            current.next=res;

            //刷新上述3个值
            res=current;
            current=end.next;

            System.out.println("after:"+res.val);

            k--;
        }

        while(res!=null){
            System.out.println(res.val);
            res=res.next;
        }

        return res;
    }

    //10 3
    public int divide(int dividend, int divisor) {
        int res=0;
        if(dividend==0){
            return res;
        }
        boolean flag=true;
        if((dividend>0&&divisor<0)||(dividend<0&&divisor>0)){
            flag=false;
        }

        dividend=dividend<0?dividend:0-dividend;
        divisor=divisor<0?divisor:0-divisor;
        int count=0;
        while(dividend<divisor){
            divisor=divisor<<1;
            count++;
        }
        System.out.println(dividend);
        System.out.println(divisor);
        while(count>=0){
            if(dividend<=divisor){
                res=res+1<<count;
//                if(res<0){
//                    return Integer.MAX_VALUE;
//                }
                dividend-=divisor;
            }
            count--;
            divisor=divisor>>1;
        }

        return flag?res:0-res;
    }

    public static void main(String args[]){
        Solution solution=new Solution();
        ListNode a1=new ListNode(1);
        ListNode a2=new ListNode(2);
        ListNode a3=new ListNode(4);
        a1.next=a2;a2.next=a3;

        ListNode b1=new ListNode(1);
        ListNode b2=new ListNode(3);
        ListNode b3=new ListNode(4);
        b1.next=b2;b2.next=b3;
//        //System.out.println(solution.addTwoNumbers(a1,b1).val);
//        System.out.println(solution.lengthOfLongestSubstring("aaabcdabcdefg"));

//        int[] num1=new int[]{1,2};
//        int[] num2=new int[]{3,4};
//        System.out.println(solution.findMedianSortedArrays(num1,num2));
//        System.out.println(solution.reverse(-1463847412));
//        System.out.println(solution.longestPalindrome("cbbd"));
//        String string="babad";
//        System.out.println(solution.expand(string.toCharArray(),));
//        System.out.println(solution.convert("ABCD",2));
//        System.out.println(solution.myAtoi("+-2"));
//        System.out.println(solution.isPalindrome(-121));
//        System.out.println(solution.intToRoman(1994));
//        System.out.println(solution.romanToInt("MCMXCIV"));
//        System.out.println(solution.letterCombinations("23"));
//        ListNode a=new ListNode(1);
//        solution.removeNthFromEnd(a,1);
//        solution.mergeTwoLists(a1,b1);
//        int[] nums=new int[]{1,2,4,8,16,32,64,128};
//        solution.threeSum(nums);
//        System.out.println(solution.threeSumClosest(nums,82));
//        System.out.println(solution.isValid("["));
//        System.out.println(solution.generateParenthesis(3));
//        ListNode[] listNodes=new ListNode[]{a1,b1};
//        solution.mergeKLists(listNodes);
//        ListNode n1=new ListNode(1);
//        ListNode n2=new ListNode(2);
//        ListNode n3=new ListNode(3);
//        ListNode n4=new ListNode(4);
//        ListNode n5=new ListNode(5);
//        n1.next=n2;n2.next=n3;n3.next=n4;n4.next=n5;
//        solution.reverseKGroup(n1,2);
        System.out.println(solution.divide(2147483647,1));
    }
}




