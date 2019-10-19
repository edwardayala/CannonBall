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
