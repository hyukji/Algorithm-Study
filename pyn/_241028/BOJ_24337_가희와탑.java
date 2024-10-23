import java.io.*;
import java.util.*;

public class BOJ_24337_가희와탑 {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        StringBuilder sb=new StringBuilder();

        int N=Integer.parseInt(st.nextToken());
        int a=Integer.parseInt(st.nextToken());
        int b=Integer.parseInt(st.nextToken());
        int[] building=new int[N];
        for(int i=0; i<N; i++) building[i]=1;
        if(a+b>N+1) sb.append(-1);
        else if(a+b==N+1) {
            if(a<b) {
                while(a>0) building[--a]=a+1;
                while(b>0) building[N-b]=b--;
            } else {
                while(b>0) building[N-b]=b--;
                while(a>0) building[--a]=a+1;
            }
            //System.out.println(Arrays.toString(building));
            for(int i=0; i<N; i++) sb.append(building[i]).append(" ");
        } else {
            int high=Math.max(a, b);
            if(a==1) {
                int base=1;
                building[0]=high;
                while(base<b) building[N-base]=base++;
            } else if(b==1) {
                int base=1;
                int index=a;
                building[N-1]=high;
                while(base<high) building[N-index--]=base++;
            } else {
                building[N-b]=high;
                int last=1;
                for(int i=N-1; i>N-b; i--) building[i]=last++;
                while(a>1) building[N-b++-1]=a---1;
            }
            //System.out.println(Arrays.toString(building));
            for(int i=0; i<N; i++) sb.append(building[i]).append(" ");
        }
        System.out.println(sb);
        br.close();
    }
}