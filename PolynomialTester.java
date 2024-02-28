
public class PolynomialTester {
    public static void main(String[] args) {

        double[] coeficients1 = {3,4,5,2};
        Polynomial p1 = new Polynomial(coeficients1);
        double[] coeficients2 = {2,4,1};
        Polynomial p2 = new Polynomial(coeficients2);


        System.out.println("Result of add operation: " + p1.add(p2));
        System.out.println("Result of substract operation: " + p1.sub(p2));
        System.out.println("Result of multuplication operation: " + p1.mul(p2));

        double[] coefficients3 = {3,4,1};
        Polynomial p3 = new Polynomial(coefficients3);
        double[] coefficients4 = {2,1};
        Polynomial p4 = new Polynomial(coefficients4);

        System.out.println("Result of compose operation: " + p3.compose(p4));

        double[] coefficients5 = {3,4,1,3,0,2};
        Polynomial p5 = new Polynomial(coefficients5);

        System.out.println("Result of divison operation: " + p5.div(p4));

        double[] coefficients6 = {10};
        Polynomial p6 = new Polynomial(coefficients6);
        double[] coefficients7 = {2,1};
        Polynomial p7 = new Polynomial(coefficients7);

        int[] arr = p6.findEqual(p7);
        System.out.print("This is equal list of numbers: ");
        for (int i : arr) {
            System.out.print(i + " \n");
        }
    }
}