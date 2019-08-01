package test1;

import java.util.*;

class Branch{
    int left;
    int right;
    boolean isVisited=false;

    @Override
    public String toString() {
        return "("+left+","+right+","+isVisited+")";
    }
}

public class Q3 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int N=sc.nextInt();
            sc.nextLine();
            ArrayList<Branch> branches=new ArrayList<>();
            for(int i=0;i<N;i++){
                String str=sc.nextLine();
                String []strs=str.split(",");
                Branch branch=new Branch();
                branch.left=Integer.parseInt(strs[0]);
                branch.right=Integer.parseInt(strs[1]);
                branches.add(branch);
            }
            //System.out.println(branches);
            solution(branches);
        }
    }
    public static void solution(ArrayList<Branch> branches){
        //广度优先遍历
        Queue<Integer> vQueue=new LinkedList<>();
        //找到头节点
        int head=0;

        ArrayList<Integer> lefts=new ArrayList<>();
        ArrayList<Integer> rights=new ArrayList<>();
        Set<Integer> all=new TreeSet<>();
        for(Branch branch:branches){
            lefts.add(branch.left);
            rights.add(branch.right);
            all.add(branch.left);
            all.add(branch.right);
        }

        Set<Integer> set=new TreeSet<>();
        for(int i=0;i<lefts.size();i++){
            if(!rights.contains(lefts.get(i))){
                set.add(lefts.get(i));
            }
        }
        if(set.size()>1){
            System.out.println("Not a tree");
        }else{
            head=set.iterator().next();
            vQueue.add(head);
//            int count=0;
            ArrayList<Integer> res=BFS(vQueue,branches);
//            System.out.println(res.size());
//            System.out.println(res);
            //System.out.println(branches);

            if(res.size()!=all.size()){
                System.out.println("Not a tree");
            }else {
                String resS="";
                for(int i=0;i<res.size();i++){
                    if(i==res.size()-1){
                        resS+=res.get(i).toString();
                    }else {
                        resS+=res.get(i).toString()+",";
                    }
                }
                System.out.println(resS);
            }
        }
    }

    public static ArrayList<Integer> BFS(Queue<Integer> vQueue,ArrayList<Branch> branches){
        ArrayList<Integer> res = new ArrayList<>();
        while(!vQueue.isEmpty()){
            int head = vQueue.remove();
            res.add(head);
            for(Branch branch:branches){
                //遍历边集合
                if(branch.isVisited==false&&branch.left==head){
                    vQueue.add(branch.right);
                    branch.isVisited=true;
                    //System.out.println("plus:"+branch.right);
                }
            }
        }
        return res;
    }
}
