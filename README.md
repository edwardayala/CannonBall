# Cannon Ball - Java - OpenGL
This is a Java OpenGL project to recreate a C++ cannon ball game in which a ball is shot through a target.
![Screenshot](https://raw.githubusercontent.com/edwardayala/OpenGL_Project/master/ScreenShot.png)
# Files

The project contains various class files that each serve their own purpose. Each splits the different parts of the Cannon Ball game into easy to understand classes.

 - **Vector3d**: Contains Vector operations
 - **Basic**: Contains trigonometric functions and has conversions between degrees to radians and radians to degrees.
 - **Target**: contains a constructor with initialize values so that it could imitate the shapes and volume of a torus.
 - **Ball**: Contains functions made to set and get the radius,center, velocity, acceleration, and color. The Ball uses the Vector3d class to implement the ball properties.
 - **ballGame**: The "*main*" class, contains all constructors and initializes all objects of the Cannon Ball program.

## Code Highlights
Vector3d:

    class Vector3d {

    double [] m_v = new double[3];
    
    // Constructors
    
    Vector3d(){
    
    m_v[0] = 0;
    
    m_v[1] = 0;
    
    m_v[2] = 0;
    
    }
    
    Vector3d(double _x, double _y, double _z){
    
    m_v[0] = _x;
    
    m_v[1] = _y;
    
    m_v[2] = _z;
    
    }
    
    Vector3d(Vector3d _v){
    
    m_v[0] = _v.m_v[0];
    
    m_v[1] = _v.m_v[1];
    
    m_v[2] = _v.m_v[2];
    
    }
    
    Vector3d(double[] _t){
    
    m_v[0] = _t[0];
    
    m_v[1] = _t[1];
    
    m_v[2] = _t[2];
    
    }
    .
    .
    .
    // addition
	
	Vector3d addition(Vector3d _v){

	return new Vector3d(m_v[0] + _v.m_v[0], m_v[1] + _v.m_v[1], m_v[2] + _v.m_v[2]);

	}

	// subtraction

	Vector3d subtraction(Vector3d _v){

	return new Vector3d(m_v[0] - _v.m_v[0], m_v[1] - _v.m_v[1], m_v[2] - _v.m_v[2]);

	}

	// scalar multiplication

	Vector3d scalarMultiplication(double _d){

	return new Vector3d(m_v[0] * _d, m_v[1] * _d, m_v[2] * _d);

	}

	// scalar division

	Vector3d scalarDivision(double _d){

	return new Vector3d(m_v[0] / _d, m_v[1] / _d, m_v[2] / _d);

	}
Basic:

    public class Basic {
    
    // return square
	int sqr(int a){return a*a;}

	// return sign of x

	int sign(double x){return x >=0 ? 1: -1;}

	// angle conversion

	private static double degToRad(double x){return x*Math.PI/180;}

	private static double radToDeg(double x){return x*180/Math.PI;}

	// degree trig functions

	static double sind(double x){return Math.sin(degToRad(x));}

	static double cosd(double x){return Math.cos(degToRad(x));}

	static double tand(double x){return Math.tan(degToRad(x));}

	static double asind(double x){return radToDeg(Math.asin(x));}

	static double acosd(double x){return radToDeg(Math.acos(x));}

	static double atand(double x){return radToDeg(Math.atan(x));}

	static double atan2d(double x, double y){return radToDeg(Math.atan2(x,y));}

	// myRand()

	static double myRand(){

	return Math.random();

	}

	}

## UML Class Diagram
![UML](https://raw.githubusercontent.com/edwardayala/OpenGL_Project/master/UMLClassDiagram.png)
## Cluster Diagram
![Cluster](https://raw.githubusercontent.com/edwardayala/OpenGL_Project/master/Cluster.png)


# OpenGL Dependencies:

gluten-rt.jar

jogl-all.jar


Create a new library in intelliJ and add these files to your project to enable the use of OpenGL in the project.
