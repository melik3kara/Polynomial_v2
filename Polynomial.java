import java.util.*;
/**
 * Polynomial
 * @author Melike Kara
 * 22203094
 */
public class Polynomial {

    //instance variables

    double[] coefficients;
    int[] degree;

    //constructor methods

    public Polynomial(int d, double c){

        this.coefficients = new double[d+1];
        this.degree =  new int[d+1];
        for (int i = 0; i < d; i++) {
            this.coefficients[i] = 0;
            this.degree[i] = i;
        }
        this.coefficients[d] = c;
        this.degree[d] = d;
    }

    public Polynomial(){

        this.coefficients = new double[1];
        this.degree =  new int[1];
        this.coefficients[0] = 0;
        this.degree[0] = 0;
    }

    public Polynomial(double[] coefficient){

        int length = coefficient.length;
        this.coefficients = new double[length];
        this.degree =  new int[length];

        this.coefficients = Arrays.copyOf(coefficient, length);

        for (int i = 0; i < coefficient.length; i++) {
            this.degree[i] = i;;
        }
    }

    //getter methods

    public double getCoefficient(int degreeOfPoly){

        int index = 0;

        for (int i = 0; i < degree.length; i++) {
            if(degree[i] == degreeOfPoly){
                index = i;
            }
        }
        return coefficients[index];
    }

    public int getDegree(){

        int index = this.degree.length;
        return index-1;
    }

    //toString method
    public String toString(){

        String polynomialStr = "";
        double[] sortedCoefficients = sortingArrayDouble(coefficients);
        int[] sortedDegree = sortingArrInteger(degree);
        if(sortedDegree.length == 1){
            polynomialStr = "0";
        }
        else{
            for (int i = 0; i < sortedDegree.length; i++) {

                if(sortedCoefficients[i] != 0.0){
    
                    if(sortedDegree[i] != 0){
                        if(!polynomialStr.equals("")){
                            polynomialStr += " + " + sortedCoefficients[i] + "x";
                            polynomialStr += "^" + sortedDegree[i];
                        }
                        else{
                            polynomialStr += sortedCoefficients[i] + "x";
                            polynomialStr += "^" + sortedDegree[i];
                        }
                    }
                    else if(sortedDegree[i] == 0){
                        polynomialStr += sortedCoefficients[i];
                    }
                }
            }
        }
        
        return polynomialStr;
    }

    //methods for evaluation

    public double eval(double x){

        double result = 0.0;

        for (int i = 0; i < this.coefficients.length; i++) {
            double coef = this.coefficients[i];
            double deg = this.degree[i];
            result += (Math.pow(x, deg) * coef);
        }
        
        return result;
    }

    public double eval2(double x){

        int length = coefficients.length;
        double[]sortedCoefficients = sortingArrayDouble(coefficients);
        double result = coefficients[length - 1];

        for (int i = length - 2; i >= 0; i--) {

            result = (result * x) + sortedCoefficients[i];
        }

        return result;
    }

    //other necessary methods

    private static int[] sortingArrInteger(int[] arr ){

        int temp = 0;
        int length = arr.length;

        for (int i = 0; i < length-1; i++) {
            for (int j = 0; j < arr.length-length-1; j++){
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    private static double[] sortingArrayDouble(double[] arr ){

        double temp = 0;
        int length = arr.length;

        for (int i = 0; i < length-1; i++) {
            for (int j = 0; j < arr.length-length-1; j++){
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        return arr;
    }

    // second lab's assigments methods

    public Polynomial add(Polynomial p2){

        double coefficients[];
        int count;
        int maksCount;
        Polynomial maksPolynomial;

        if(this.getDegree() < p2.getDegree()){
            count = this.getDegree() +1;
            maksCount = p2.getDegree() +1;
            maksPolynomial = p2;
        }
        else{
            count = p2.getDegree() +1;
            maksCount = this.getDegree() +1;
            maksPolynomial = this;
        }

        coefficients = new double[maksCount];

        for (int i = 0; i < count; i++) {
            coefficients[i] = this.coefficients[i] + p2.coefficients[i];
        }

        for (int i = 0; i < maksCount-count; i++) {
            coefficients[count + i] = maksPolynomial.coefficients[count + i];
        }
        Polynomial poly = new Polynomial(coefficients);
        return poly;
    }

    public Polynomial sub(Polynomial p2){

        double coefficients[];
        int count;
        int maksCount;
        Polynomial maksPolynomial;

        if(this.getDegree()+1 < p2.getDegree()+1){
            count = this.getDegree()+1;
            maksCount = p2.getDegree()+1;
            maksPolynomial = p2;
        }
        else{
            count = p2.getDegree()+1;
            maksCount = this.getDegree()+1;
            maksPolynomial = this;
        }


        coefficients = new double[maksCount];

        for (int i = 0; i < count; i++) {
            coefficients[i] = this.coefficients[i] - p2.coefficients[i];
        }

        for (int i = 0; i < maksCount-count; i++) {
            coefficients[count + i] = maksPolynomial.coefficients[count + i];
        }

        Polynomial poly = new Polynomial(coefficients);
        return poly;
    }

    public Polynomial mul(Polynomial p2){

        double coefficients[];
        coefficients = new double[this.getDegree() + p2.getDegree() +1];
        for (int i = 0; i < this.getDegree() +1; i++) {
            for (int j = 0; j < p2.getDegree() +1; j++) {

                coefficients[i + j] += this.getCoefficient(i) * p2.getCoefficient(j);
            }
        }
        Polynomial poly = new Polynomial(coefficients);
        return poly;
    }

    public Polynomial compose(Polynomial p2){

        Polynomial pol = new Polynomial(0,0);
        for (int i = this.coefficients.length-1; i > 0 ; i--) {
            if(this.coefficients[i] != 0){
                Polynomial coefPol = new Polynomial(0,this.coefficients[i]);
                pol = pol.add(coefPol);
                pol = pol.mul(p2);
            }
        }
        Polynomial coefPol = new Polynomial(0,this.coefficients[0]);
        pol = pol.add(coefPol);
        return pol;
    }

    public Polynomial div(Polynomial p2){

        Polynomial p = this;
        Polynomial result = new Polynomial();
        for (int i = 0; i < this.coefficients.length-1; i++) {

            double coef1 = lead(p).coefficients[lead(p).coefficients.length-1];
            double coef2 = lead(p2).coefficients[lead(p2).coefficients.length-1];
            double coef = (coef1 / coef2);

            int deg1 = lead(p).getDegree();
            int deg2 = lead(p2).getDegree();
            int degree = deg1 - deg2;

            Polynomial poly = new Polynomial(degree, coef);
            result = result.add(poly);
            p = p.sub(poly.mul(p2));
        }

        return result;
    }

    public int[] findEqual(Polynomial p2){

        ArrayList<Integer> equalList = new ArrayList<Integer>();

        for (int i = 0 ; i < 200; i++) {
            if(this.eval2(i + 1) == p2.eval(i + 1)){
                equalList.add(i + 1);
            }
        }

        int counter = 0;
        int[] equalArr = new int[equalList.size()];
        for (int i : equalList) {
            equalArr[counter] = i;
            counter ++;
        }
        return equalArr;
    }

    //other necessary methods vol2

    public Polynomial lead(Polynomial poly){

        boolean control = true;
        int index = poly.getDegree();
        while ( control ) {
            
            if(poly.coefficients[index] != 0){control = false;}
            else {index--;}
        }
        double leadNum = poly.coefficients[index];
        Polynomial poly2 = new Polynomial(index, leadNum);
        return poly2;
    }
}