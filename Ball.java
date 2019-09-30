import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.gl2.GLUT;

import java.util.LinkedList;


public class Ball {
    // Properties/Attributes
    Vector3d center = new Vector3d();       // Default constructor initialized with (0,0,0)
    Vector3d velocity = new Vector3d();
    Vector3d oldVelocity = new Vector3d();
    Vector3d accel = new Vector3d();
    Vector3d color = new Vector3d(0.3,0.2,0.8);
    double radius;
    double [] bbx;

    double damping = 0.95;
    double collisionDamping = 0.3;

    LinkedList<Ball> Node;

    Ball(){}

    public Ball(Vector3d center, Vector3d velocity, Vector3d oldVelocity, Vector3d accel, Vector3d color, double radius, double [] bbx) {
        this.center = center;
        this.velocity = velocity;
        this.oldVelocity = oldVelocity;
        this.accel = accel;
        this.color = color;
        this.radius = radius;
        this.bbx = bbx;
    }

    // Methods
    void setValues(double _radius, Vector3d _center, Vector3d _vel, Vector3d _accel, double [] _bbx){
        this.radius = _radius;
        this.center = _center;
        this.velocity = _vel;
        this.accel = _accel;
        this.bbx = _bbx;
    }
    void setRandomColor(){
        color.setX(Math.random());
        color.setY(Math.random());
        color.setZ(Math.random());
    }
    boolean isMoving(){
        return velocity.norm() < 0.2;
    }
    void update(double dt){
        // center = center + velocity * dt
        center.assignOp(center.addition(velocity.scalarMultiplication(dt)));
        // oldVelocity = velocity
        oldVelocity.assignOp(velocity);
        // velocity = velocity + accel * dt
        velocity.assignOp(velocity.addition(accel.scalarMultiplication(dt)));

        double oldAccelY = accel.getY();
        oldAccelY -= 9.8;
        accel.setY(oldAccelY);
        // accel *= damping
        accel.scalarMultiplication(damping);

        resolveCollision();

        if (accel.norm() < 0.5)
            accel.setAll(0,0,0);
        if (velocity.norm() < 0.5)
            velocity.setAll(0,0,0);
    }

    //              [  0 ,  1  ,  2  ,  3  ,  4  ,  5  ]
    // bbx contains [xMin, xMax, yMin, yMax, zMin, zMax]
    void resolveCollision(){
        // resolve X component
        if (center.getX()-radius < bbx[0]){
            double oldCenterX = center.getX();
            center.setX(bbx[0]+radius);
            velocity.setX(-1*velocity.getX());
            velocity.scalarMultiplication(collisionDamping);
            accel.scalarMultiplication(collisionDamping);
        }
        if (center.getX()+radius > bbx[1]){
            double oldCenterX = center.getX();
            center.setX(bbx[1]-radius);
            velocity.setX(-1*velocity.getX());
            velocity.scalarMultiplication(collisionDamping);
            accel.scalarMultiplication(collisionDamping);
        }
        // resolve Y component
        if (center.getY()-radius < bbx[2]){
            double oldCenterY = center.getY();
            center.setY(bbx[2]+radius);
            velocity.setY(-1*velocity.getY());
            velocity.scalarMultiplication(collisionDamping);
            accel.scalarMultiplication(collisionDamping);
        }
        if (center.getY()+radius > bbx[3]){
            double oldCenterY = center.getY();
            center.setY(bbx[3]-radius);
            velocity.setY(-1*velocity.getY());
            velocity.scalarMultiplication(collisionDamping);
            accel.scalarMultiplication(collisionDamping);
        }
        // resolve Z component
        if (center.getZ()-radius < bbx[4]){
            double oldCenterZ = center.getZ();
            center.setZ(bbx[4]+radius);
            velocity.setZ(-1*velocity.getZ());
            velocity.scalarMultiplication(collisionDamping);
            accel.scalarMultiplication(collisionDamping);
        }
        if (center.getZ()+radius > bbx[5]){
            double oldCenterZ = center.getZ();
            center.setZ(bbx[5]-radius);
            velocity.setZ(-1*velocity.getZ());
            velocity.scalarMultiplication(collisionDamping);
            accel.scalarMultiplication(collisionDamping);
        }
    }
    Vector3d getCenter(){
        return center;
    }

    void draw(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        GLUT glut = new GLUT();
        gl.glColor3d(color.getX(),color.getY(),color.getZ());
        gl.glPushMatrix();
        gl.glTranslated(center.m_v[0],center.m_v[1],center.m_v[2]);
        glut.glutSolidSphere(radius,10,10);
        gl.glPopMatrix();
    }
}