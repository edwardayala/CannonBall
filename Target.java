import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;

public class Target {
    // Properties / Attributes
    Vector3d center;
    Vector3d color;
    double [] bbx;
    double innerRadius, outerRadius;
    boolean isMoving;
    double deltaX;

    // Constructor
    Target(){
        center.setAll(0,10,-80);
        color.setAll(0.8,0.7,0.8);
        innerRadius = 0.5;
        outerRadius = 4;
        isMoving = false;
        deltaX = 0.02;
    }

    // Methods
    void update(Ball ballList){
        if (isMoving){
            center.setX(center.getX() + deltaX);
            if (center.getX() < bbx[0]){
                center.setX(bbx[0]);
                deltaX *= -1;
            }
            if (center.getX() > bbx[1]){
                center.setX(bbx[1]);
                deltaX *= -1;
            }
        }

        boolean hit = false;
        boolean isLast = false;
        Ball curBall = ballList;
        while (curBall != null){
            Ball b = new Node().ball;
            Vector3d ballCenter = b.getCenter();
            if ((ballCenter.subtraction(center)).norm() < outerRadius){
                if (Math.abs(ballCenter.getZ()-center.getZ())< 1.0){
                    hit = true;
                    break;
                }
            }
//            curBall = curBall.next;
        }
        if (hit){
            //start isMoving
            isMoving = true;
            // change color
            color.setX(Math.random());
            color.setY(Math.random());
            color.setZ(Math.random());
            deltaX *= 1.05;
        }
    }
    void draw(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        GLUT glut = new GLUT();

        gl.glColor3d(color.getX(),color.getY(),color.getZ());
        gl.glPushMatrix();
        gl.glTranslated(center.m_v[0],center.m_v[1],center.m_v[2]);
        glut.glutSolidTorus(innerRadius, outerRadius, 20, 20);
        gl.glPopMatrix();
    }
    void setBbx(double [] _bbx){
        this.bbx = _bbx;
    }
    Vector3d getCenter(){return center;}

}