// Edward Ayala, Melissa Rivera, Veronika Lysenko
// operators ( +, -, * ) , scalar, dot-product, and cross-product and more...
public class Test {
    public static void main(String [] args){

        //                          ----------SETUP VECTORS AND VALUES----------
        // Create test vectors
        final Vector3d v1 = new Vector3d( 5,3,1.5);
        final Vector3d v2 = new Vector3d(7,3.4,1.2);
        final Vector3d v3 = new Vector3d(6,4.5,3.2);

        // Scalar values
        double x = 4.3, y = 2.3, z = 0.8;

        // Print vectors
        System.out.println("\nVector v1(x,y,z): " + v1.m_v[0] + " | " + v1.m_v[1] + " | " + v1.m_v[2]);
        System.out.println("Vector v2(x,y,z): " + v2.m_v[0] + " | " + v2.m_v[1] + " | " + v2.m_v[2]);
        System.out.println("Vector v3(x,y,z): " + v3.m_v[0] + " | " + v3.m_v[1] + " | " + v3.m_v[2]);

        //                              ----------OPERATION TESTS----------

        // Addition operator test
        Vector3d add1 = v1.addition(v2);
        Vector3d add2 = v2.addition(v3);
        Vector3d add3 = v3.addition(v1);

        System.out.println("\nAddition results 1: " + add1.m_v[0] + " | " + add1.m_v[1] + " | " + add1.m_v[2]);
        System.out.println("Addition results 2: " + add2.m_v[0] + " | " + add2.m_v[1] + " | " + add2.m_v[2]);
        System.out.println("Addition results 3: " + add3.m_v[0] + " | " + add3.m_v[1] + " | " + add3.m_v[2]);

        // Subtraction operator test
        Vector3d sub1 = v1.subtraction(v2);
        Vector3d sub2 = v2.subtraction(v3);
        Vector3d sub3 = v3.subtraction(v1);

        System.out.println("\nSubtraction results 1: " + sub1.m_v[0] + " | " +  sub1.m_v[1] + " | " +  sub1.m_v[2]);
        System.out.println("Subtraction results 2: " + sub2.m_v[0] + " | " +  sub2.m_v[1] + " | " +  sub2.m_v[2]);
        System.out.println("Subtraction results 3: " + sub3.m_v[0] + " | " +  sub3.m_v[1] + " | " +  sub3.m_v[2]);

        // Multiplication operator test
        Vector3d mult1 = v1.scalarMultiplication(x);
        Vector3d mult2 = v2.scalarMultiplication(y);
        Vector3d mult3 = v3.scalarMultiplication(z);

        System.out.println("\nMultiplication results 1: " + mult1.m_v[0] + " | " +  mult1.m_v[1] + " | " +  mult1.m_v[2]);
        System.out.println("Multiplication results 2: " + mult2.m_v[0] + " | " +  mult2.m_v[1] + " | " +  mult2.m_v[2]);
        System.out.println("Multiplication results 3: " + mult3.m_v[0] + " | " +  mult3.m_v[1] + " | " +  mult3.m_v[2]);

        // Divide operator test
        Vector3d div1 = v1.scalarDivision(x);
        Vector3d div2 = v2.scalarDivision(y);
        Vector3d div3 = v3.scalarDivision(z);

        System.out.println("\nDivision Result 1: " + div1.m_v[0] + " | " + div1.m_v[1] + " | " + div1.m_v[2]);
        System.out.println("Division Result 2: " + div2.m_v[0] + " | " + div2.m_v[1] + " | " + div2.m_v[2]);
        System.out.println("Division Result 3: " + div3.m_v[0] + " | " + div3.m_v[1] + " | " + div3.m_v[2]);

        // Component operator test
        Vector3d comp1 = v1.component(v2);
        Vector3d comp2 = v2.component(v3);
        Vector3d comp3 = v3.component(v1);

        System.out.println("\nComponent * result 1: " + comp1.m_v[0] +  " | " + comp1.m_v[1] +  " | " + comp1.m_v[2]);
        System.out.println("Component * result 2: " + comp2.m_v[0] +  " | " + comp2.m_v[1] +  " | " + comp2.m_v[2]);
        System.out.println("Component * result 3: " + comp3.m_v[0] +  " | " + comp3.m_v[1] +  " | " + comp3.m_v[2]);

        // Negate operator test
        Vector3d neg1 = v1.negate();
        Vector3d neg2 = v2.negate();
        Vector3d neg3 = v3.negate();

        System.out.println("\nNegate Vector 1: " + neg1.m_v[0] +  " | " + neg1.m_v[1] +  " | " + neg1.m_v[2]);
        System.out.println("Negate Vector 2: " + neg2.m_v[0] +  " | " + neg2.m_v[1] +  " | " + neg2.m_v[2]);
        System.out.println("Negate Vector 3: " + neg3.m_v[0] +  " | " + neg3.m_v[1] +  " | " + neg3.m_v[2]);

        // Dot-product operator test
        double dot1 = v1.dotProd(v2);
        double dot2 = v2.dotProd(v3);
        double dot3 = v3.dotProd(v1);

        System.out.println("\nDot-Product result 1: " + dot1);
        System.out.println("Dot-Product result 2: " + dot2);
        System.out.println("Dot-Product result 3: " + dot3);

        // Cross-Product operator test
        Vector3d cross1 = v1.crossProd(v2);
        Vector3d cross2 = v2.crossProd(v3);
        Vector3d cross3 = v3.crossProd(v1);

        System.out.println("\nCross-Product result 1: " + cross1.m_v[0] +  " | " + cross1.m_v[1] +  " | " + cross1.m_v[2]);
        System.out.println("Cross-Product result 2: " + cross2.m_v[0] +  " | " + cross2.m_v[1] +  " | " + cross2.m_v[2]);
        System.out.println("Cross-Product result 3: " + cross3.m_v[0] +  " | " + cross3.m_v[1] +  " | " + cross3.m_v[2]);

        //                      -----------Self manipulating operations change contents of original vectors-----------

        // Self Multiplication operator test
        Vector3d sMult1 = v1.selfMult(v1);
        Vector3d sMult2 = v2.selfMult(v2);
        Vector3d sMult3 = v3.selfMult(v3);

        System.out.println("\nSelf Multiplication 1: " + sMult1.m_v[0] + " | " + sMult1.m_v[1] + " | " + sMult1.m_v[2]);
        System.out.println("Self Multiplication 2: " + sMult2.m_v[0] + " | " + sMult2.m_v[1] + " | " + sMult2.m_v[2]);
        System.out.println("Self Multiplication 3: " + sMult3.m_v[0] + " | " + sMult3.m_v[1] + " | " + sMult3.m_v[2]);

        // Self Division operator test
        Vector3d sDiv1 = v1.selfScalDiv(x);
        Vector3d sDiv2 = v2.selfScalDiv(y);
        Vector3d sDiv3 = v3.selfScalDiv(z);

        System.out.println("\nSelf Division 1: " + sDiv1.m_v[0] + " | " + sDiv1.m_v[1] + " | " + sDiv1.m_v[2]);
        System.out.println("Self Division 2: " + sDiv2.m_v[0] + " | " + sDiv2.m_v[1] + " | " + sDiv2.m_v[2]);
        System.out.println("Self Division 3: " + sDiv3.m_v[0] + " | " + sDiv3.m_v[1] + " | " + sDiv3.m_v[2]);
    }
}
