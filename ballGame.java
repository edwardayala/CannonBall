import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.FloatBuffer;
//import java.util.LinkedList;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.*;


public class ballGame implements GLEventListener, KeyListener {
    // create a linked-list
//    LinkedList<Ball> Node = new LinkedList<>();

    // a singular target
    private Target target;

    // cannon position
    private Vector3d cannon;

    // A singular ball
    private Ball ball;

    // ball radius
    private double curRadius = 0.6;

    // cannon length
    private double cannonL = 0.5;

    // angle1 for rotating cannon
    private float angle1 = 45.0f;
    private float angle2 = 165.0f;

    private double [] bbx = new double[]{-20,20,-4,20,-100,100};

    private ballGame() {
        target = new Target();
        ball = new Ball();
        cannon = new Vector3d(0,-1.8,0);
    }

    private static final int GLUT_KEY_LEFT = 37;
    private static final int GLUT_KEY_UP = 38;
    private static final int GLUT_KEY_RIGHT = 39;
    private static final int GLUT_KEY_DOWN = 40;

    public void init(GLAutoDrawable drawable){
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

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    private void changeSize(int w, int h, GLAutoDrawable drawable){
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

        // get back to the model view
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
    }

    private void drawScene(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        gl.glTranslatef(0f, 0f, -2.5f);

        // enable lighting
        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glEnable(GL_COLOR_MATERIAL);

        // RIGHT WALL
        gl.glNormal3d(0,-1,0);
        gl.glColor3d(0.0,0.0,1.0);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3d(bbx[1],bbx[2],bbx[4]);      // TOP RIGHT
        gl.glVertex3d(bbx[1],bbx[3],bbx[4]);      // TOP LEFT
        gl.glVertex3d(bbx[1],bbx[3],bbx[5]);      // BOTTOM LEFT
        gl.glVertex3d(bbx[1],bbx[2],bbx[5]);      // BOTTOM RIGHT
        gl.glEnd();                               // THEN THEY ALL CONNECT

        // LEFT WALL
        gl.glNormal3d(0,-1,0);
        gl.glColor3d(1.0,0.0,0.0);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3d(bbx[0],bbx[2],bbx[4]);      // TOP RIGHT
        gl.glVertex3d(bbx[0],bbx[3],bbx[4]);      // TOP LEFT
        gl.glVertex3d(bbx[0],bbx[3],bbx[5]);      // BOTTOM LEFT
        gl.glVertex3d(bbx[0],bbx[2],bbx[5]);      // BOTTOM RIGHT
        gl.glEnd();                               // THEN THEY ALL CONNECT

        // TOP WALL
        gl.glNormal3d(0,-1,0);
        gl.glColor3d(0.0,0.8,0.2);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3d(bbx[0],bbx[3],bbx[4]);       // TOP RIGHT
        gl.glVertex3d(bbx[0],bbx[3],bbx[5]);       // TOP LEFT
        gl.glVertex3d(bbx[1],bbx[3],bbx[5]);       // BOTTOM LEFT
        gl.glVertex3d(bbx[1],bbx[3],bbx[4]);       // BOTTOM RIGHT
        gl.glEnd();                                // THEN THEY ALL CONNECT

        // BACK WALL
        gl.glNormal3d(0,-1,0);
        gl.glColor3d(0.0,0.8,0.8);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3d(bbx[0],bbx[2],bbx[4]);       // TOP RIGHT
        gl.glVertex3d(bbx[0],bbx[3],bbx[4]);       // TOP LEFT
        gl.glVertex3d(bbx[1],bbx[3],bbx[4]);       // BOTTOM LEFT
        gl.glVertex3d(bbx[1],bbx[2],bbx[4]);       // BOTTOM RIGHT
        gl.glEnd();                                // THEN THEY ALL CONNECT

        // BOTTOM
        gl.glNormal3d(0,-1,0);
        gl.glColor3d(0.8,0.8,0.8);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3d(bbx[0],bbx[2],bbx[4]);       // TOP RIGHT
        gl.glVertex3d(bbx[0],bbx[2],bbx[5]);       // TOP LEFT
        gl.glVertex3d(bbx[1],bbx[2],bbx[5]);       // BOTTOM LEFT
        gl.glVertex3d(bbx[1],bbx[2],bbx[4]);       // BOTTOM RIGHT
        gl.glEnd();                                // THEN THEY ALL CONNECT
    }

    void getCannonEndPts(double ang1, double cX, double cY){
        cX = cannon.getX()+cannonL * Basic.cosd(ang1);
        cY = cannon.getY()+cannonL * Basic.sind(ang1);
    }

    private double [] getCannonEndPts3D(double ang1, double ang2, double cX, double cY, double cZ){
        cY = cannon.getY()+ (cannonL * Basic.sind(ang1));
        double l2 = cannonL * Basic.cosd(ang1);
        cX = cannon.getX()+ (cannonL * Basic.sind(ang2));
        cZ = cannon.getZ()+ (cannonL * Basic.cosd(ang2));
        double [] tmp = {cX, cY, cZ};
        return tmp;
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
            // time step
            double dt = 0.02;
            tmp.ball.update(dt);
            tmp.ball = tmp.Node.get(i+1);
        }
    }

    private void addBall(double _r, Vector3d stPt, Vector3d vel, Vector3d accelVec){
//        Node newBall = new Node();
//        newBall.ball.setValues(curRadius,stPt,vel,accelVec,bbx);
//        newBall.ball.setRandomColor();
//        newBall.next = head;
//        head = newBall;

    }

    private void removeAllNonMoving(){
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

    private void renderScene(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        final GLU glu = new GLU();
//        final GLUT glut = new GLUT();

        gl.glEnable(GL_DEPTH_TEST);

        // clear color and depth buffers
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0.5f,0.5f,0.5f,1.0f);

        // reset transformations
        gl.glLoadIdentity();

        // set the camera
        glu.gluLookAt(0.0f, 0.0f, 2.5f,
                     0.0f, 0.0f,  0.0f,
                     0.0f, 1.0f,  0.0f);

        // enable lighting
        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glEnable(GL_COLOR_MATERIAL);

        // draw bbx
        drawScene(drawable);

        // draw yPlane
        gl.glLineWidth(2);
        gl.glColor3f(0,1f,1f);
        gl.glBegin(GL_LINES);
        float yPlane = -2;
        gl.glVertex3f(-2.1f, yPlane,0.0f);
        gl.glVertex3f(2.1f, yPlane,0.0f);
        gl.glEnd();

        // draw the ball(s)
//        drawAllBalls(drawable);
        target.draw(drawable);

        // draw the cannon
        double cX = 0.0, cY = -2, cZ = 0.0;
        getCannonEndPts3D(angle1, angle2, cX,cY,cZ);
        gl.glColor3f(0,0,1);
        gl.glLineWidth(2);
        gl.glBegin(GL2.GL_LINES);
//        gl.glVertex3d(cannon.getX(), cannon.getY(), cannon.getZ());
//        gl.glVertex3d(cannon.getX(), cannon.getY(), cannon.getZ());
        gl.glVertex3d(cX, cY, cZ);
        gl.glVertex3d(getCannonEndPts3D(angle1, angle2, cX, cY, cZ)[0], getCannonEndPts3D(angle1, angle2, cX, cY, cZ)[1], getCannonEndPts3D(angle1, angle2, cX, cY, cZ)[2]);
        gl.glEnd();

//        draw the ball(s)
//        drawAllBalls(drawable);
//        target.draw(drawable);

//        updateAllBalls();
//        target.update(Node.getFirst());

//        glutSwapBuffers();
    }

    private void processNormalKeys(char key){
        double maxCannonL = 1.5;
        if (key == 27 || key == 'q')
            java.lang.System.exit(0);
        else if (key == 's'){
            double cX = 0.0, cY = 0.0, cZ = 0.0;
            getCannonEndPts3D(angle1, angle2, cX, cY, cZ);
            // maximum acceleration
            double maxAccel = 850;
            double accel = maxAccel * (cannonL/ maxCannonL);
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
            double minCannonL = 0.25;
            if (cannonL< minCannonL)
                cannonL = minCannonL;
        }
        else if(key=='2') {
            cannonL += 0.02;
            if(cannonL> maxCannonL) cannonL = maxCannonL;
        }
        else if(key=='9') {
            curRadius -= 0.2;
            double minRadius = 0.2;
            if(curRadius< minRadius) curRadius = minRadius;
        }
        else if(key=='0') {
            curRadius += 0.2;
            double maxRadius = 1.4;
            if(curRadius> maxRadius) curRadius = maxRadius;
        }
    }

    private void processSpecialKeys(int key){
        switch(key) {
            case GLUT_KEY_UP :
                angle1 += 1;
                if(angle1>=100) angle1 = 100;
                System.out.println("Cannon move");
                break;
            case GLUT_KEY_DOWN :
                angle1 -= 1;
                if(angle1<=0) angle1 = 0;
                break;
            case GLUT_KEY_LEFT :
                angle2 += 1;
                if(angle2>=270) angle2 = 270;
                break;
            case GLUT_KEY_RIGHT :
                angle2 -= 1;
                if(angle2<=90) angle2 = 90;
        }
    }

    public void display(GLAutoDrawable drawable){
        init(drawable);
        renderScene(drawable);
//        drawScene(drawable);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        changeSize(width,height,drawable);
    }

    public static void main(String[] args){
        // getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // canvas
        final GLCanvas glCanvas = new GLCanvas(capabilities);
        ballGame b = new ballGame();
        glCanvas.addGLEventListener(b);
        glCanvas.addKeyListener(b);
        glCanvas.setSize(400, 400);

        // frame
        final JFrame frame = new JFrame("ShootPts");

        // adding canvas to frame
        frame.getContentPane().add(glCanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed: " + e.getKeyChar() + " - " + e.getKeyCode());
        processSpecialKeys(e.getKeyCode());
        processNormalKeys(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
