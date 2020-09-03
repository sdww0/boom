public class test {

    public static void main(String[] args){
        int[] a = {1,2,3,4};
        int[] b = null;
        int[] c = {6,5,32,3};
        b = a;
        b[1] = 1;
        c = b;
        c[2] = 1;
        System.out.printf("%d %d %d",a[0],a[1],a[2]);
    }


}
