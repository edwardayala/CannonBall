import com.jogamp.opengl.*;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.ListIterator;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.*;


public class ballGame {

    // all variables initialized to 1.0, meaning
    // the triangle will initially be white
    float red=1.0f, blue=1.0f, green=1.0f;

    // time step
    double dt = 0.02;

    //yPlane
    float yPlane = -4;

    // create a linked-list
    // ... no
    LinkedList<Ball> Node = new LinkedList<>();

    // A singular ball
    Ball ball = new Ball();

    // a singular target
    Target target = new Target();

    // cannon position
    Vector3d cannon = new Vector3d();

    // ball radius
    double curRadius = 0.6;
    double minRadius = 0.2;
    double maxRadius = 1.4;

    // maximum acceleration
    double maxAccel = 850;

    // cannon length
    double cannonL = 0.5;
    double maxCannonL = 1.5;
    double minCannonL = 0.25;

    // angle1 for rotating cannon
    float angle1 = 45.0f;
    float angle2 = 165.0f;

    double [] bbx = new double[]{-20,20,-4,20,-100,100};


    void init(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();

        // GLFloat == float [] - https://jogamp.org/deployment/jogamp-next/javadoc/jogl/javadoc/
        float [] mat_specular = {1.0f,1.0f,1.0f,0.0f};
        float [] mat_shininess = {10.0f};
        float [] light_position = { 1.0f, 1.0f, 1.0f, 0.0f };
        float [] light_ambient = { 0.8f, 0.8f, 0.8f, 1.0f };
        float [] light_diffuse = {1.0f,1.0f,1.0f,1.0f};
        float [] light_specular = { 0.8f, 0.8f, 0.8f, 1.0f };

        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH);

        gl.glMaterialfv(GL_FRONT_AND_BACK, GLLightingFunc.GL_SPECULAR, FloatBuffer.wrap(mat_specular));
        gl.glMaterialfv(GL_FRONT_AND_BACK, GLLightingFunc.GL_SHININESS, FloatBuffer.wrap(mat_shininess));

        gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_AMBIENT, FloatBuffer.wrap(light_ambient));
        gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_DIFFUSE, FloatBuffer.wrap(light_diffuse));
        gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_SPECULAR, FloatBuffer.wrap(light_specular));
        gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_POSITION, FloatBuffer.wrap(light_position));

        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glEnable(GL_DEPTH_TEST);
    }

    void changeSize(int w, int h, GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        final GLU glu = new GLU();


        // Prevent a divide by zero, when window is too short
        // (you cant make a window of zero width).
        if (h == 0)
            h = 1;
        float ratio =  w * 1.0f / h;

        // use the projection matrix
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);

        // reset matrix
        gl.glLoadIdentity();

        // set viewport to be entire window
        gl.glViewport(0,0,w,h);

        // set correct perspective
        glu.gluPerspective(45.0f,ratio,0.1f,1000.0f);

        // get back to the modelview
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
    }

    void drawBBX(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();

        gl.glShadeModel(GL_SMOOTH);
        gl.glNormal3d(1,0,0);
        gl.glColor3f(1,0,0);
        gl.glBegin(GL_QUADS); // left side
        gl.glVertex3d(bbx[0],bbx[2],bbx[4]);
        gl.glVertex3d(bbx[0],bbx[3],bbx[4]);
        gl.glVertex3d(bbx[0],bbx[3],bbx[5]);
        gl.glVertex3d(bbx[0],bbx[2],bbx[5]);
        gl.glEnd();

        gl.glNormal3d(-1,0,0);
        gl.glColor3f(0,0,1);
        gl.glBegin(GL_QUADS); //right side
        gl.glVertex3d(bbx[1],bbx[2],bbx[4]);
        gl.glVertex3d(bbx[1],bbx[3],bbx[4]);
        gl.glVertex3d(bbx[1],bbx[3],bbx[5]);
        gl.glVertex3d(bbx[1],bbx[2],bbx[5]);
        gl.glEnd();

        gl.glNormal3d(0,1,0);
        gl.glColor3f(0.8f,0.8f,0.8f);
        gl.glBegin(GL_QUADS); //bottom side
        gl.glVertex3d(bbx[0],bbx[2],bbx[4]);
        gl.glVertex3d(bbx[0],bbx[2],bbx[5]);
        gl.glVertex3d(bbx[1],bbx[2],bbx[5]);
        gl.glVertex3d(bbx[1],bbx[2],bbx[4]);
        gl.glEnd();

        gl.glNormal3d(0,-1,0);
        gl.glColor3f(0.0f,0.8f,0.2f);
        gl.glBegin(GL_QUADS); //top side
        gl.glVertex3d(bbx[0],bbx[3],bbx[4]);
        gl.glVertex3d(bbx[0],bbx[3],bbx[5]);
        gl.glVertex3d(bbx[1],bbx[3],bbx[5]);
        gl.glVertex3d(bbx[1],bbx[3],bbx[4]);
        gl.glEnd();

        //back
        gl.glNormal3d(0,0,-1);
        gl.glColor3f(0.0f,0.8f,0.8f);
        gl.glBegin(GL_QUADS); //back side
        gl.glVertex3d(bbx[0],bbx[2],bbx[4]);
        gl.glVertex3d(bbx[0],bbx[3],bbx[4]);
        gl.glVertex3d(bbx[1],bbx[3],bbx[4]);
        gl.glVertex3d(bbx[1],bbx[2],bbx[4]);
        gl.glEnd();
        //front
        gl.glNormal3d(0,0,1);
        gl.glColor3f(0.0f,0.8f,0.8f);
        gl.glBegin(GL_QUADS); //front side
        gl.glVertex3d(bbx[0],bbx[2],bbx[5]);
        gl.glVertex3d(bbx[0],bbx[3],bbx[5]);
        gl.glVertex3d(bbx[1],bbx[3],bbx[5]);
        gl.glVertex3d(bbx[1],bbx[2],bbx[5]);
        gl.glEnd();
    }

    void getCannonEndPts(double ang1, double cX, double cY){
        cX = cannon.getX()+cannonL * Basic.cosd(ang1);
        cY = cannon.getY()+cannonL * Basic.sind(ang1);
    }

    void getCannonEndPts3D(double ang1, double ang2, double cX, double cY, double cZ){
        cY = cannon.getY()+ (cannonL * Basic.sind(ang1));
        double l2 = cannonL * Basic.cosd(ang1);
        cX = cannon.getX()+ (cannonL * Basic.sind(ang2));
        cZ = cannon.getZ()+ (cannonL * Basic.cosd(ang2));
    }

    void drawAllBalls(GLAutoDrawable drawable){
//        Node tmp = new Node(); // Node* tmp = head;
//        while(tmp != null){
//            tmp.ball.draw();
//            tmp = tmp.next;


        Node tmp = new Node();
        int i = 0;

        while (tmp != null){
            tmp.ball.draw(drawable);
            tmp.ball = tmp.Node.get(i+1);
        }
    }

    void updateAllBalls(){
//        Node tmp = head;
//        while(tmp!=NULL) {
//            tmp->ball.Update(dt);
//            tmp = tmp->next;
//        }

        Node tmp = new Node();
        tmp.ball = tmp.Node.get(0);
        int i = 0;

        while (tmp != null){
            tmp.ball.update(dt);
            tmp.ball = tmp.Node.get(i+1);
        }
    }

    void addBall(double _r, Vector3d stPt, Vector3d vel, Vector3d accelVec){
        Node newBall = new Node();
        newBall.ball.setValues(curRadius,stPt,vel,accelVec,bbx);
        newBall.ball.setRandomColor();
//        newBall.next = head;
//        head = newBall;
    }

    void removeAllNonMoving(){
        Node predLoc = null;
        Node location = new Node(); // Node location = head;
        boolean isLast = false;
        int numRemoved = 0;
        int numBallsRemaining = 0;
        int counter = 0;
        int i = 0;
        while (location != null){
            if (location.Node.get(i+1) == null)
                isLast = true;
            if (location.ball.isMoving()){
                predLoc = location;
                location.ball = location.Node.get(i+1);
                numBallsRemaining++;
            }
            else {
                System.out.println("counter = " + counter);
                numRemoved++;
                Node temp = location;
                location.ball = location.Node.get(i+1);
                if (predLoc != null)
                    predLoc.Node.set(i+1, location);
                else
                    location.Node.set(0, location);
                temp.Node.clear();  // delete temp;
            }
            counter++;
        }
        System.out.println("numBallsRemaining: " + numBallsRemaining + " numBallsRemoved: " + numRemoved);
    }

    void renderScene(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        final GLU glu = new GLU();
        final GLUT glut = new GLUT();

        gl.glEnable(GL_DEPTH_TEST);

        // clear color and depth buffers
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0.5f,0.5f,0.5f,1.0f);

        // reset transformations
        gl.glLoadIdentity();

        // set the camera
        glu.gluLookAt(0.0f, 0.0f, 10.0f,
                     0.0f, 0.0f,  0.0f,
                     0.0f, 1.0f,  0.0f);

        // enable lighting
        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glEnable(GL_COLOR_MATERIAL);

        // draw bbx
        drawBBX(drawable);

        // draw yPlane
        gl.glLineWidth(2);
        gl.glBegin(GL_LINES);
        gl.glVertex3f(-4.0f,yPlane,0.0f);
        gl.glVertex3f(4.0f,yPlane,0.0f);
        gl.glEnd();

        // draw the ball(s)
        drawAllBalls(drawable);
        target.draw(drawable);

        // draw the cannon
        double cX = 0.0, cY = 0.0, cZ = 0.0;
        getCannonEndPts3D(angle1, angle2, cX,cY,cZ);
        gl.glColor3f(0,0,1);
        gl.glLineWidth(2);
        gl.glBegin(GL_LINES);
        gl.glVertex3d(cannon.getX(), cannon.getY(), cannon.getZ());
        gl.glVertex3d(cX, cY, cZ);
        gl.glEnd();

        updateAllBalls();
        target.update(Node.getFirst());

//        glutSwapBuffers();
    }

    void processNormalKeys(char key, int x, int y){
        if (key == 27 || key == 'q')
            java.lang.System.exit(0);
        else if (key == 's'){
            double cX = 0.0, cY = 0.0, cZ = 0.0;
            getCannonEndPts3D(angle1, angle2, cX, cY, cZ);
            double accel = maxAccel * (cannonL/maxCannonL);
            Vector3d stPt = new Vector3d(cX, cY+curRadius, cZ);
            Vector3d vel = new Vector3d(0,0,0);
            Vector3d accelVec = new Vector3d(cX-cannon.getX(), cY-cannon.getY(),cZ-cannon.getZ());
            accelVec.selfNormalize();
            accelVec.selfScale(accel);

            addBall(curRadius,stPt,vel,accelVec);
        }
        else if (key=='d'){
            removeAllNonMoving();
        }
        else if (key=='1'){
            cannonL -= 0.02;
            if (cannonL<minCannonL)
                cannonL = minCannonL;
        }
        else if(key=='2') {
            cannonL += 0.02;
            if(cannonL>maxCannonL) cannonL = maxCannonL;
        }
        else if(key=='9') {
            curRadius -= 0.2;
            if(curRadius<minRadius) curRadius = minRadius;
        }
        else if(key=='0') {
            curRadius += 0.2;
            if(curRadius>maxRadius) curRadius = maxRadius;
        }
    }

    void processSpecialKeys(int key, int x, int y, GLAutoDrawable drawable){
        GLUT glut = new GLUT();
        final GL2 gl = drawable.getGL().getGL2();

        GLU glu = new GLU();



//
//        switch(key) {
//            case GLUT_KEY_UP :
//                angle1 += 1;
//                if(angle1>=100) angle1 = 100;
//                break;
//            case GLUT_KEY_DOWN :
//                angle1 -= 1;
//                if(angle1<=0) angle1 = 0;
//                break;
//            case GLUT_KEY_LEFT :
//                angle2 += 1;
//                if(angle2>=270) angle2 = 270;
//                break;
//            case GLUT_KEY_RIGHT :
//                angle2 -= 1;
//                if(angle2<=90) angle2 = 90;
//        }
    }

    public void main(int argc, char [][] argv, GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        GLUT glut = new GLUT();
        Frame frame = new Frame("ShootPts");


        cannon.setAll(0 ,yPlane, 0);
        target.setBbx(bbx);


    }
}
