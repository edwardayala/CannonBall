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
        System.out.println("Vector v3(x,y,z): " + v3.m_v[0] + " | " + v3.m_v[1] + " | " + v3.m_v[2] + "\n");

        //                              ----------OPERATION TESTS----------

        // Addition operator test
        System.out.println("Addition results 1: " + v1.addition(v2).m_v[0] + " | " + v1.addition(v2).m_v[1] + " | " + v1.addition(v2).m_v[2]);
        System.out.println("Addition results 2: " + v2.addition(v3).m_v[0] + " | " + v2.addition(v3).m_v[1] + " | " + v2.addition(v3).m_v[2]);
        System.out.println("Addition results 3: " + v3.addition(v3).m_v[0] + " | " + v3.addition(v3).m_v[1] + " | " + v3.addition(v3).m_v[2] + "\n");

        // Subtraction operator test
        System.out.println("Subtraction results 1: " + v1.subtraction(v2).m_v[0] + " | " +  v1.subtraction(v2).m_v[1] + " | " +  v1.subtraction(v2).m_v[2]);
        System.out.println("Subtraction results 2: " + v2.subtraction(v3).m_v[0] + " | " +  v2.subtraction(v3).m_v[1] + " | " +  v2.subtraction(v3).m_v[2]);
        System.out.println("Subtraction results 3: " + v3.subtraction(v3).m_v[0] + " | " +  v3.subtraction(v3).m_v[1] + " | " +  v3.subtraction(v3).m_v[2] + "\n");

        // Multiplication operator test
        System.out.println("Multiplication results 1: " + v1.scalarMultiplication(x).m_v[0] + " | " +  v1.scalarMultiplication(x).m_v[1] + " | " +  v1.scalarMultiplication(x).m_v[2]);
        System.out.println("Multiplication results 2: " + v2.scalarMultiplication(y).m_v[0] + " | " +  v2.scalarMultiplication(y).m_v[1] + " | " +  v2.scalarMultiplication(y).m_v[2]);
        System.out.println("Multiplication results 3: " + v3.scalarMultiplication(z).m_v[0] + " | " +  v3.scalarMultiplication(z).m_v[1] + " | " +  v3.scalarMultiplication(z).m_v[2] + "\n");

        // Component operator test
        System.out.println("Component * result 1: " + v1.component(v2).m_v[0] +  " | " + v1.component(v2).m_v[1] +  " | " + v1.component(v2).m_v[2]);
        System.out.println("Component * result 2: " + v2.component(v3).m_v[0] +  " | " + v2.component(v3).m_v[1] +  " | " + v2.component(v3).m_v[2]);
        System.out.println("Component * result 3: " + v3.component(v1).m_v[0] +  " | " + v3.component(v1).m_v[1] +  " | " + v3.component(v1).m_v[2] + "\n");

        // Negate operator test
        System.out.println("Negate Vector 1: " + v1.negate().m_v[0] +  " | " + v1.negate().m_v[1] +  " | " + v1.negate().m_v[2]);
        System.out.println("Negate Vector 2: " + v2.negate().m_v[0] +  " | " + v2.negate().m_v[1] +  " | " + v2.negate().m_v[2]);
        System.out.println("Negate Vector 3: " + v3.negate().m_v[0] +  " | " + v3.negate().m_v[1] +  " | " + v3.negate().m_v[2] + "\n");

        // Dot-product operator test
        System.out.println("Dot-Product result 1: " + v1.dotProd(v2));
        System.out.println("Dot-Product result 2: " + v2.dotProd(v3));
        System.out.println("Dot-Product result 3: " + v3.dotProd(v3) + "\n");

        // Cross-Product operator test
        System.out.println("Cross-Product result 1: " + v1.crossProd(v2).m_v[0] +  " | " + v1.crossProd(v2).m_v[1] +  " | " + v1.crossProd(v2).m_v[2]);
        System.out.println("Cross-Product result 2: " + v2.crossProd(v3).m_v[0] +  " | " + v2.crossProd(v3).m_v[1] +  " | " + v2.crossProd(v3).m_v[2]);
        System.out.println("Cross-Product result 3: " + v3.crossProd(v1).m_v[0] +  " | " + v3.crossProd(v1).m_v[1] +  " | " + v3.crossProd(v1).m_v[2] + "\n");

        //                      -----------Self manipulating operations change contents of original vectors-----------

        // Self Multiplication operator test
        System.out.println("Self Multiplication 1: " + v1.selfMult(v1).m_v[0] + " | " + v1.selfMult(v1).m_v[1] + " | " + v1.selfMult(v1).m_v[2]);
        System.out.println("Self Multiplication 2: " + v2.selfMult(v2).m_v[0] + " | " + v2.selfMult(v2).m_v[1] + " | " + v2.selfMult(v2).m_v[2]);
        System.out.println("Self Multiplication 3: " + v3.selfMult(v3).m_v[0] + " | " + v3.selfMult(v3).m_v[1] + " | " + v3.selfMult(v3).m_v[2] + "\n");

        // Divide operator test
        System.out.println("Division Result 1: " + v1.scalarDivision(x).m_v[0] + " | " + v1.scalarDivision(x).m_v[1] + " | " + v1.scalarDivision(x).m_v[2]);
        System.out.println("Division Result 2: " + v1.scalarDivision(y).m_v[0] + " | " + v1.scalarDivision(y).m_v[1] + " | " + v1.scalarDivision(y).m_v[2]);
        System.out.println("Division Result 3: " + v1.scalarDivision(z).m_v[0] + " | " + v1.scalarDivision(z).m_v[1] + " | " + v1.scalarDivision(z).m_v[2] + "\n");

        // Self Division operator test
        System.out.println("Self Division 1: " + v1.selfScalDiv(x).m_v[0] + " | " + v1.selfScalDiv(x).m_v[1] + " | " + v1.selfScalDiv(x).m_v[2]);
        System.out.println("Self Division 2: " + v2.selfScalDiv(y).m_v[0] + " | " + v2.selfScalDiv(y).m_v[1] + " | " + v2.selfScalDiv(y).m_v[2]);
        System.out.println("Self Division 3: " + v3.selfScalDiv(z).m_v[0] + " | " + v3.selfScalDiv(z).m_v[1] + " | " + v3.selfScalDiv(z).m_v[2] + "\n");




    }
}
