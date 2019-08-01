package test1;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

class Danmaku{
    int c;
    int d;
}

public class Q2 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int N=sc.nextInt();
            sc.nextLine();
            ArrayList<Danmaku> list = new ArrayList<>();
            for(int i=0;i<N;i++) {
                Danmaku danmaku=new Danmaku();
                String str = sc.nextLine();
                String[] strArr=str.split(" ");
                danmaku.c=Integer.parseInt(strArr[0]);
                danmaku.d=Integer.parseInt(strArr[1]);
                list.add(danmaku);
            }
//            for(int i=0;i<list.size();i++){
//                System.out.println(list.get(i).c+" "+list.get(i).d);
//            }
            solution(list);
        }
    }

    public static void solution(ArrayList<Danmaku> list) {
        //ArrayList<Res> reses = new ArrayList<>();
        HashMap<String,Integer> reses = new HashMap<>();
        for(int i=0;i<list.size();i++){
            String danmaku = list.get(i).c+" "+list.get(i).d;
            reses.put(danmaku,1);
        }
        for(int i=0;i<list.size()-1;i++){
            //判断两个区间是否有交集，若有交集，则记录交集，若无交集，则记录i本身
            for (int j=i+1;j<list.size();j++){
                reses=intersaction(list.get(i),list.get(j),reses);
                //System.out.println(i+" "+j);
            }
        }

        //处理结果
        ArrayList<String> alres = new ArrayList<>();
        int max=0;
        Iterator iter = reses.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String)entry.getKey();
            int val = (Integer)entry.getValue();
            if(val>max){
                alres.clear();
                alres.add(key);
                max=val;
            }else if(val==max){
                alres.add(key);
            }
        }

        //System.out.println(alres);
        for(int i=0;i<alres.size()-1;i++){
            for(int j=i+1;j<alres.size();j++){
                String tmp="";
                String string1=alres.get(i);
                String string2=alres.get(j);
                String[] strings1=string1.split(" ");
                String[] strings2=string2.split(" ");
                int s1=Integer.parseInt(strings1[0]);
                int s2=Integer.parseInt(strings2[0]);
                if(s1>s2){
                    tmp=alres.get(i);
                    alres.set(i,alres.get(j));
                    alres.set(j,tmp);
                }
            }
        }

        for(int i=0;i<alres.size();i++){
            System.out.println(alres.get(i));
        }

//        int[] arr=new int[reses.size()];
//        int count=0;
//        Iterator iter1 = reses.entrySet().iterator();
//        while(iter.hasNext()){
//            Map.Entry entry = (Map.Entry) iter.next();
//            String key = (String)entry.getKey();
//            int val = (Integer)entry.getValue();
//            arr[count]=val;
//            count++;
//            System.out.println(key+" "+val);
//        }
//        int max = max(arr);
    }

    //取交集
    public static HashMap<String,Integer> intersaction(Danmaku d1,Danmaku d2,HashMap<String,Integer> reses){
        String danmaku="";
        int c=max(d1.c,d2.c);
        int d=min(d1.d,d2.d);
        if(c<d){
            danmaku=c+" "+d;
            if(reses.containsKey(danmaku)){
                int count=reses.get(danmaku)+1;
                reses.put(danmaku,count);
            }else {
                reses.put(danmaku,2);
            }
        }
        return reses;
    }
}
