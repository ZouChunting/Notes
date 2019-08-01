package test1;

import java.util.ArrayList;
import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			String str = sc.nextLine();
			String[] strArr=str.split(" ");
			int n=Integer.parseInt(strArr[0]);
			int m=Integer.parseInt(strArr[1]);
			ArrayList<Integer> nlist=new ArrayList<Integer>();
			for(int i=0;i<n;i++) {
				nlist.add(i+1);
			}
			ArrayList<Integer> res=solution(nlist,m,1);
			System.out.println(res.get(0));
		}
	}
	
	//ตÝน้
	public static ArrayList<Integer> solution(ArrayList<Integer> nlist,int m,int t) {
		int size=nlist.size();
		while (size>1) {
			t=(m+t)%size-1;
			nlist.remove(t);
			m=m*m;
			return solution(nlist, m, t);
		}
		return nlist;
	}

}
