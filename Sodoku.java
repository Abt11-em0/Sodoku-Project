import java.util.ArrayList;
public class Sodoku
{
    public static void main(String[] args)
    {
        double start = System.currentTimeMillis();
        int[][][][] c = new int[3][3][3][3];
        c = fill(c);
        while(check(c)==-1) c = fill(c);
        for(int i = 0; i<c.length; i++){
            ArrayList<String> out = new ArrayList<String>();
            out = add(add(wrap(c[i][0]),wrap(c[i][1])),wrap(c[i][2]));
            for(int j = 0; j<out.size(); j++){
                System.out.println(out.get(j));
            }
        }
        double end = System.currentTimeMillis();
        double total = (end-start)/1000;
        System.out.println(total+ " seconds taken");
        
    }
    
    public static ArrayList<String> wrap(int[][] c){
        ArrayList<String> out = new ArrayList<String>();
        out.add(" ----------- ");
        for(int i = 0; i<c.length; i++){
            String temp = "|";
            for(int j = 0; j<c[i].length; j++){
                temp += " " + c[i][j]+" |"; 
            }
            out.add(temp);
            out.add(" ----------- ");
        }
        return out;
        
    }
    public static ArrayList<String> add(ArrayList<String> a, ArrayList<String> b){
        ArrayList<String> c = new ArrayList<String>();
        for(int i=0; i<a.size(); i++){
            String temp = a.get(i)+b.get(i);
            c.add(temp);
        }
        return c;
    }
    
    public static int[][][][] fill(int[][][][] a){
        for(int y1 = 0; y1<a.length; y1++){
            for(int x1 = 0; x1<a[y1].length;x1++){
                a = clear(a, y1, x1);
                int restart = 0; 
                int abort = 0;
                for(int y2 = 0; y2<a[y1][x1].length; y2++){
                    in:
                    for(int x2 = 0; x2<a[y1][x1][y2].length; x2++){
                        int test = getValidNum(a,y1,x1,y2,x2);
                        while(test<1){
                            y2 = -1; 
                            x2 = -1;
                            a = clear(a, y1, x1);
                            restart++;
                            if(restart>5){
                                abort ++;
                                if(abort>10){
                                    return a;
                                }
                                change(a,y1,x1);
                                restart = 0;
                            }
                            break in;
                         
                        }
                        a[y1][x1][y2][x2] = test;
                    }
                }
            }
        }
        return a;
    }
    
    public static int getValidNum(int[][][][] a, int y1, int x1, int y2, int x2){
        ArrayList<Integer> available = new ArrayList<Integer>();
        for(int i=1; i<10; i++) available.add(i);
        for(int j1 = 0; j1<a[y1][x1].length; j1++){
            for(int j2 = 0; j2<a[y1][x1][j1].length;j2++){
                int j3 = available.indexOf(a[y1][x1][j1][j2]);
                if(j3>=0){
                    available.remove(j3);
                }
            }
        }
        for(int k1 = 0; k1<a[y1].length;k1++){
            for(int k2 = 0; k2<a[y1][x1][y2].length;k2++){
                int k3 = available.indexOf(a[y1][k1][y2][k2]);
                if(k3>=0){
                    available.remove(k3);
                }
            }
        }
        
        for(int l1 = 0; l1<a.length;l1++){
            for(int l2 = 0; l2<a[y1][x1].length;l2++){
                int l3 = available.indexOf(a[l1][x1][l2][x2]);
                if(l3>=0){
                    available.remove(l3);
                }
            }
        }
        if(available.size()>0)
            return available.get((int)(Math.random()*available.size()));
        else
            return -1;
    }
    
    public static int[][][][] change(int[][][][] a, int y1, int x1){
        clear(a,y1,x1);
        ArrayList<Integer[]> temp = new ArrayList<Integer[]>();
        if(y1>0){
            Integer[] yUp = {y1-1, x1};
            temp.add(yUp);
        }
        if(y1<a.length-1){
            Integer[] yDown = {y1+1, x1};
            temp.add(yDown);
        }
        if(x1>0){
            Integer[] xUp = {y1, x1-1};
            temp.add(xUp);
        }
        if(x1<a[y1].length-1){
            Integer[] xDown = {y1, x1+1};
            temp.add(xDown);
        }
        
        Integer[] b = temp.get((int)(Math.random()*temp.size()));
        int restart = 0;
        int abort = 0;
        for(int i = 0; i<a[b[0]][b[1]].length; i++){
            out:
            for(int j = 0; j<a[b[0]][b[1]][i].length; j++){
                int test = getValidNum(a,y1,x1,i,j);
                while(test<1){
                    a = clear(a, y1, x1);
                    i =-1;
                    j = -1;
                    restart++;
                    if(restart>10){
                        abort++;
                        if(abort>10){
                            return a;
                        }
                        change(a,b[0],b[1]);
                        restart = 0;
                    }
                    break out;
                }
            }
        }
        return a;
    }
    
    public static int[][][][] clear(int [][][][] a, int y1, int x1){
        for(int i = 0; i<a[y1][x1].length; i++){
            for(int j = 0; j<a[y1][x1][i].length; j++){
                a[y1][x1][i][j] = -1;
            }
        }
        return a;
    }
    
    public static int check(int[][][][] a){
        for(int i = 0; i<a.length; i++)
            for(int j = 0; j<a[i].length; j++)
                for(int k = 0; k<a[i][j].length; k++)
                    for(int l = 0; l<a[i][j][k].length; l++)
                        if(a[i][j][k][l]<1)
                            return -1;    
                  
        return 0;
    }
}
