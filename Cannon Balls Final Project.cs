using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using OpenTK;
using OpenTK.Graphics.OpenGL;
using OpenTK.Graphics;
using OpenTK.Input;
using OpenGL;
using Tao.FreeGlut;

namespace Cannon.opengl.Components
{
    class Vector3d
    {
        //member variables
        private double x, y, z;

        //constructors
        public Vector3d()
        {
            x = 0;
            y = 0;
            z = 0;
        }

        public Vector3d(double _x, double _y, double _z)
        {
            x = _x;
            y = _y;
            z = _z;
        }
        public Vector3d(Vector3d _v)
        {
            x = _v.x;
            y = _v.y;
            z = _v.z;
        }

        //assignment
        public void setVector3d(double _x, double _y, double _z)
        {
            x = _x;
            y = _y;
            z = _z;
        }

        //access

        //equality
        public static bool operator ==(Vector3d v1, Vector3d v2)
        {
            if ((v1.x == v2.x) && (v1.y == v2.y) && (v1.z == v2.z))
                return true;
            else
                return false;
        }

        //inequality
        public static bool operator !=(Vector3d v1, Vector3d v2)
        {
            if ((v1.x != v2.x) || (v1.y != v2.y) || (v1.z != v2.z))
                return true;
            else
                return false;
        }

        //negation
        public static Vector3d operator -(Vector3d _v)
        {
            _v.x = -_v.x;
            _v.y = -_v.y;
            _v.z = -_v.z;
            return _v;
        }

        /*public static Vector3d operator -()
        {
            this.x = -this.x;
            y = -y;
            z = -z;
            return this;
        } */
        //error

        //addition
        public static Vector3d operator +(Vector3d v1, Vector3d v2)
        {
            Vector3d v3 = new Vector3d();
            v3.x = v1.x + v2.x;
            v3.y = v1.y + v2.y;
            v3.z = v1.z + v2.z;
            return v3;
        }

        //subtraction
        public static Vector3d operator -(Vector3d v1, Vector3d v2)
        {
            Vector3d v3 = new Vector3d();
            v3.x = v1.x - v2.x;
            v3.y = v1.y - v2.y;
            v3.z = v1.z - v2.z;
            return v3;
        }

        //scalar multiply
        public static Vector3d operator *(Vector3d v1, double d)
        {
            Vector3d v2 = new Vector3d();
            v2.x = v1.x * d;
            v2.y = v1.y * d;
            v2.z = v1.z * d;
            return v2;
        }

        //scalar divide
        public static Vector3d operator /(Vector3d v1, double d)
        {
            Vector3d v2 = new Vector3d();
            v2.x = v1.x / d;
            v2.y = v1.y / d;
            v2.z = v1.z / d;
            return v2;
        }

        //component
        public static Vector3d operator ^(Vector3d v1, Vector3d v2)
        {
            Vector3d v3 = new Vector3d();
            v3.x = v1.x * v2.x;
            v3.y = v1.y * v2.y;
            v3.z = v1.z * v2.z;
            return v3;
        }

        //cross product
        public static Vector3d operator %(Vector3d v1, Vector3d v2)
        {
            Vector3d v3 = new Vector3d();
            v3.x = (v1.y * v2.z) - (v1.z * v2.y);
            v3.y = (v1.z * v2.x) - (v1.x * v2.z);
            v3.z = (v1.x * v2.y) - (v1.y * v2.x);
            return v3;
        }

        //dot product
        public static double operator *(Vector3d v1, Vector3d v2)
        {
            return ((v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z));
        }

        //magnitude squared
        public double normsqur()
        {
            return this * this;
        }

        //magnitude 
        public double norm()
        {
            return Math.Sqrt(normsqur());
        }

        //normalized vector
        public Vector3d normalize()
        {
            //if?
            double n = norm();
            return this / n;
        }

        double comp(Vector3d _v)
        {
            return this * _v.normalize();
        }

        //scale vector
        public Vector3d scale(double l)
        {
            double n = norm();
            //if?
            x = x * (l / n);
            y = y * (l / n);
            z = z * (l / n);
            return this;
        }

        /*public Vector3d selfScale(double l)
        {

        }*/

        //rotate vector
        public Vector3d rotateX(double _rad)
        {
            Vector3d v1 = new Vector3d();
            double c = Math.Cos(_rad), s = Math.Sin(_rad);
            v1.x = x;
            v1.y = y * c - z * s;
            v1.z = y * s + z * c;
            return v1;
        }

        public Vector3d rotateXd(double _deg)
        {
            double rad = Math.PI / 180 * _deg;
            return rotateX(rad);
        }

        public Vector3d rotateY(double _rad)
        {
            Vector3d v1 = new Vector3d();
            double c = Math.Cos(_rad), s = Math.Sin(_rad);
            v1.x = x * c + z * s;
            v1.y = y;
            v1.z = -x * s + z * c;
            return v1;
        }

        public Vector3d rotateYd(double _deg)
        {
            double rad = Math.PI / 180 * _deg;
            return rotateY(rad);
        }

        public Vector3d rotateZ(double _rad)
        {
            Vector3d v1 = new Vector3d();
            double c = Math.Cos(_rad), s = Math.Sin(_rad);
            v1.x = x * c - y * s;
            v1.y = x * s + y * c;
            v1.z = z;
            return v1;
        }

        public Vector3d rotateZd(double _deg)
        {
            double rad = Math.PI / 180 * _deg;
            return rotateZ(rad);
        }

        //reset
        void reset()
        {
            x = 0;
            y = 0;
            z = 0;
        }

        public double GetX()
        {
            return x;
        }

        public double GetY()
        {
            return y;
        }

        public double GetZ()
        {
            return z;
        }

        public void SetX(double d)
        {
            x = d;
        }

        public void SetY(double d)
        {
            y = d;
        }

        public void SetZ(double d)
        {
            z = d;
        }

        public void SetAll(double d1, double d2, double d3)
        {
            x = d1;
            y = d2;
            z = d3;
        }

        public void print()
        {
            x = GetX();
            y = GetY();
            z = GetZ();
            Console.WriteLine("X = {0}, Y = {1} , Z = {2} ", x, y, z);


        }



        public class Ball
        {
            double damping = 0.95;
            double collisionDamping = 0.3;

            public class Node
            {
                public Node next;
                public Ball ball;
            }
            public class LinkedList
            {
                public Node head;
            }


            Vector3d center;
            Vector3d velocity;
            Vector3d oldVelocity;
            Vector3d accel;

            Vector3d color;

            double radius;

            //double yPlane;
            //double bbx;               //ref?????????
            public double xMin, xMax, yMin, yMax, zMin, zMax;
            //              [  0 , 1  ,  2  , 3  ,  4  , 5  ]
            // bbx contains [xMin,xMax, yMin,yMax, zMin,zMax]

            public Ball()
            {
                center.SetAll(0, 0, 0);
                velocity.SetAll(0, 0, 0);
                accel.SetAll(0, 0, 0);
                color.SetAll(0.3, 0.2, 0.8);
            }

            public void SetValues(double _radius, Vector3d _center, Vector3d _vel, Vector3d _accel, /*double _bbx*/ double _xMin, double _xMax, double _yMin, double _yMax, double _zMin, double _zMax)
            {
                radius = _radius;
                center = _center;
                velocity = _vel;
                accel = _accel;
                //bbx = _bbx;
                xMin = _xMin;
                xMax = _xMax;
                yMin = _yMin;
                yMax = _yMax;
                zMin = _zMin;
                zMax = _zMax;

            }

            public double myRand()
            {
                Random rand = new Random();
                return (double)((double)(rand.Next()) / Int32.MaxValue);      //RAND_MAX???
                //return rand.Next(0, 255);
            }

            public void SetRandomColor()
            {

                color.SetX(myRand());
                color.SetY(myRand());
                color.SetZ(myRand());
            }

            public bool IsMoving()
            {
                if (velocity.norm() < 0.2) return true;
                else return false;
            }

            public void Update(double dt)
            {
                //Console.WriteLine();
                center = center + velocity * dt;
                oldVelocity = velocity;
                velocity = velocity + accel * dt;
                double oldAccelY = accel.GetY();
                oldAccelY -= 9.8;
                accel.SetY(oldAccelY);
                accel *= damping;

                ResolveCollision(); //this should fix things in the case that it goes out of bounds

                if (accel.norm() < 0.5) accel.SetAll(0, 0, 0);
                if (velocity.norm() < 0.5) velocity.SetAll(0, 0, 0);
            }

            public void ResolveCollision()
            {
                //resolve X component
                if (center.GetX() - radius < xMin)
                {
                    double oldCenterX = center.GetX();
                    //make sure center doesn't cross plane
                    center.SetX(xMin + radius);
                    //reflect velocity - just change y direction
                    velocity.SetX(-1 * velocity.GetX());
                    velocity *= collisionDamping;
                    accel *= collisionDamping;
                }
                if (center.GetX() + radius > xMax)
                {
                    double oldCenterX = center.GetX();
                    //make sure center doesn't cross plane
                    center.SetX(xMax - radius);
                    //reflect velocity - just change y direction
                    velocity.SetX(-1 * velocity.GetX());
                    velocity *= collisionDamping;
                    accel *= collisionDamping;
                }
                //resolve Y component
                if (center.GetY() - radius < yMin)
                {
                    double oldCenterY = center.GetY();
                    //make sure center doesn't cross plane
                    center.SetY(yMin + radius);
                    //reflect velocity - just change y direction
                    velocity.SetY(-1 * velocity.GetY());
                    //accel.SetY( -1*accel.GetY() );
                    velocity *= collisionDamping;
                    accel *= collisionDamping;
                }
                if (center.GetY() + radius > yMax)
                {
                    double oldCenterY = center.GetY();
                    //make sure center doesn't cross plane
                    center.SetY(yMax - radius);
                    //reflect velocity - just change y direction
                    velocity.SetY(-1 * velocity.GetY());
                    velocity *= collisionDamping;
                    accel *= collisionDamping;
                }
                //resolve Z component
                if (center.GetZ() - radius < zMin)
                {
                    double oldCenterZ = center.GetZ();
                    //make sure center doesn't cross plane
                    center.SetZ(zMin + radius);
                    //reflect velocity - just change y direction
                    velocity.SetZ(-1 * velocity.GetZ());
                    velocity *= collisionDamping;
                    accel *= collisionDamping;
                }
                if (center.GetZ() + radius > zMax)
                {
                    double oldCenterZ = center.GetZ();
                    //make sure center doesn't cross plane
                    center.SetZ(zMax - radius);
                    //reflect velocity - just change y direction
                    velocity.SetZ(-1 * velocity.GetZ());
                    velocity *= collisionDamping;
                    accel *= collisionDamping;
                }
            }

            private void openGLControl_OpenGLDraw(object sender, FrameEventArgs e)
            {
                //OpenGL gl = openGLControl.OpenGL;
                GL.Color3(1, 0, 0);

                GL.Translate(0, 1, 2);
                GL.SolidSphere(radius, 10, 10);
                GL.PopMatrix();
            }


        }
        public class Target
        {

            Vector3d center;
            Vector3d color;
            //double bbx;              //ref????????
            double xMin, xMax, yMin, yMax, zMin, zMax;
            double innerRadius, outerRadius;
            bool isMoving;
            double deltaX;

            public Target()
            {
                center.SetAll(0, 10, -80);
                color.SetAll(0.8, 0.7, 0.8);
                innerRadius = 0.5;
                outerRadius = 4;
                isMoving = false;
                deltaX = 0.02;
            }

            public void SetBBX(double _xMin, double _xMax, double _yMin, double _yMax, double _zMin, double _zMax)
            {
                //bbx = _bbx;
                xMin = _xMin;
                xMax = _xMax;
                yMin = _yMin;
                yMax = _yMax;
                zMin = _zMin;
                zMax = _zMax;
            }

            public void Update(Ball ball)
            {
                if (isMoving)
                {
                    center.SetX(center.GetX() + deltaX);
                    if (center.GetX() < xMin)
                    {
                        center.SetX(xMin);
                        deltaX *= -1;
                    }
                    if (center.GetX() > xMax)
                    {
                        center.SetX(xMax);
                        deltaX *= -1;
                    }
                }

            }
        }

        class Basic
        {
            const double PI = 3.1415926535897;
            const double TWOPI = 6.2831853071794;

            //return the square of a.
            public T sqr<T>(ref T a)
            {
                dynamic da = a;
                return da * da;
            }

            //return sign of x
            public int sign(double x)
            {
                return x >= 0 ? 1 : -1;
            }

            //Angle conversions
            public double degToRad(double x)
            {
                return x * PI / 180;
            }

            public double radToDeg(double x)
            {
                return x * 180 / PI;
            }

            //Degree Trig functions
            public double sind(double x)
            {
                return Math.Sin(degToRad(x));                //?std::???
            }

            public double cosd(double x)
            {
                return Math.Cos(degToRad(x));
            }

            public double tand(double x)
            {
                return Math.Tan(degToRad(x));
            }

            public double asind(double x)
            {
                return radToDeg(Math.Asin(x));          //std::???
            }

            public double acosd(double x)
            {
                return radToDeg(Math.Acos(x));
            }

            public double atand(double x)
            {
                return radToDeg(Math.Atan(x));
            }

            public double atan2d(double x, double y)
            {
                return radToDeg(Math.Atan2(x, y));
            }

            /*public double myRand()
            {
                Random rand = new Random();
                //return (double)((double)(rand.Next()) / Int32.MaxValue);      //RAND_MAX???
                return rand.Next(0, 255);
            }*/
        }

        public class tester
        {
            public class Node
            {
                public Node next;
                public Ball ball;
                public Node head;
            }
            /*
            public class LinkedList
            {
                public Node head;
            }*/
            // all variables initialized to 1.0, meaning
            // the triangle will initially be white
            float red = 1.0f, blue = 1.0f, green = 1.0f;

            // time step
            double dt = 0.001;
            // yPlane
            float yPlane = -4;
            /*unsafe struct Node
            {
                Ball ball;
           

            };*/

            /*public Node head;
        public Node current;
        current =0;
            head = null;
             // create a linked-list
           NodeArr[0] == null;*/

            // a singular ball
            Ball ball;
            // a singular target
            Target target;
            // cannon position
            Vector3d cannon;
            // ball radius
            double curRadius = 0.6;
            double minRadius = 0.2;
            double maxRadius = 1.4;
            // maximum acceleration
            double maxAccel = 6850;
            // cannon length
            double cannonL = 0.5;
            double maxCannonL = 1.5;
            double minCannonL = 0.25;
            // angle1 for rotating cannon
            float angle1 = 45.0f;
            float angle2 = 165.0f;
            double xMin = -20;
            double xMax = 20, yMin = -4, yMax = 20, zMin = -100, zMax = 100;



            //double bbx[] = {-20, 20, -4, 20, -100, 100 };



            public void changeSize(int w, int h)
            {

                // Prevent a divide by zero, when window is too short
                // (you cant make a window of zero width).
                if (h == 0)
                    h = 1;
                float ratio = (float)(w * 1.0 / h);

                // Use the Projection Matrix
                GL.MatrixMode(GL_PROJECTION);

                // Reset Matrix
                GL.LoadIdentity();

                // Set the viewport to be the entire window
                GL.Viewport(0, 0, w, h);

                // Set the correct perspective.
                GL.pPerspective(45.0f, ratio, 0.1f, 1000.0f);

                // Get Back to the Modelview
                GL.MatrixMode(GL_MODELVIEW);

                /*
                glEnable(GL_LIGHTING);
                glEnable(GL_LIGHT0);
                GLfloat lightpos[] = {.5, 1., 1., 0.};
                glLightfv(GL_LIGHT0, GL_POSITION, lightpos);
                */
            }

            public void drawBBX()
            {

                GL.CreateShader(GL_SMOOTH);
                GL.Normal3(1, 0, 0);
                GL.Color3(1, 0, 0);
                GL.Begin(GL_QUADS); //left side
                GL.Vertex3(-20, -4, -100);
                GL.Vertex3(-20, 20, -100);
                GL.Vertex3(-20, 20, 100);
                GL.Vertex3(-20, -4, 10);
                GL.End();

                GL.Normal3(-1, 0, 0);
                GL.Color3(0, 0, 1);
                GL.Begin(GL_QUADS); //right side
                GL.Vertex3(20, -4, -100);
                GL.Vertex3(20, 20, -100);
                GL.Vertex3(20, 20, 100);
                GL.Vertex3(20, -4, 100);
                GL.End();

                GL.Normal3(0, 1, 0);
                GL.Color3(0.8, 0.8, 0.8);
                GL.Begin(GL_QUADS); //bottom side
                GL.Vertex3(-20, -4, -100);
                GL.Vertex3(-20, -4, 100);
                GL.Vertex3(20, -4, 100);
                GL.Vertex3(20, -4, -100);
                GL.End();

                GL.Normal3(0, -1, 0);
                GL.Color3(0.0, 0.8, 0.2);
                GL.Begin(GL_QUADS); //top side
                GL.Vertex3(20, 20, 20);
                GL.Vertex3(20, 20, 100);
                GL.Vertex3(20, 20, 100);
                GL.Vertex3(20, 20, -100);
                GL.End();

                //back
                GL.Normal3(0, 0, -1);
                GL.Color3(0.0, 0.8, 0.8);
                GL.Begin(GL_QUADS); //back side
                GL.Vertex3(-20, -4, -100);
                GL.Vertex3(-20, 20, -100);
                GL.Vertex3(20, 20, -100);
                GL.Vertex3(20, -4, -100);
                GL.End();
                //front
                GL.Normal3(0, 0, 1);
                GL.Color3(0.0, 0.8, 0.8);
                GL.Begin(GL_QUADS); //front side
                GL.Vertex3(-20, -4, 100);
                GL.Vertex3(-20, 20, 100);
                GL.Vertex3(20, 20, 100);
                GL.Vertex3(20, -4, 100);
                GL.End();
            }

            public void getCannonEndPts(double ang1, double cX, double cY)
            {
                cX = cannon.GetX() + cannonL * Math.Cos(ang1);
                cY = cannon.GetY() + cannonL * Math.Sin(ang1);
            }
            public void getCannonEndPts3D(double ang1, double ang2, double cX, double cY, double cZ)
            {
                cY = cannon.GetY() + (cannonL * Math.Sin(ang1));
                double l2 = cannonL * Math.Cos(ang1);
                cX = cannon.GetX() + (cannonL * Math.Sin(ang2));
                cZ = cannon.GetZ() + (cannonL * Math.Cos(ang2));
            }

            public void DrawAllBalls()
            {
                Node head = new Node();
                Node tmp = new Node();
                tmp = head;
                while (tmp != null)
                {
                    tmp.ball.DrawALLBalls();
                    tmp = tmp.next;
                }
            }
            public void UpdateAllBalls()
            {
                Node head = new Node();
                Node tmp = head;
                while (tmp != null)
                {
                    tmp.ball.Update(dt);
                    tmp = tmp.next;
                }
            }
            public void AddBall(double _r, Vector3d stPt, Vector3d vel, Vector3d accelVec)
            {
                Node head = new Node();
                Node newBall = new Node();//double _radius, Vector3d _center, Vector3d _vel, Vector3d _accel, /*double _bbx*/ double _xMin, double _xMax, double _yMin, double _yMax, double _zMin, double _zMax)
                newBall.ball.SetValues(curRadius, stPt, vel, accelVec, xMin, xMax, yMin, yMax, zMin, zMax);
                newBall.ball.SetRandomColor();
                newBall.next = head;
                head = newBall;
            }


            public void RemoveAllNonMoving()
            {
                Node head = new Node();
                Node predLoc = null;
                Node location = head;
                bool isLast = false;
                int numRemoved = 0;
                int numBallsRemaining = 0;
                int counter = 0;
                while (location != null)
                {
                    if (location.next == null) isLast = true;
                    if (location.ball.IsMoving())
                    {
                        //increment
                        predLoc = location;
                        location = location.next;
                        numBallsRemaining++;
                    }
                    else
                    { //not moving remove
                        Console.WriteLine("counter = ", counter);
                        numRemoved++;
                        Node tmp = location; //tmp points to what should be removed
                        location = location.next; //move location over
                        if (predLoc != null)
                            predLoc.next = location;
                        else
                            head = location;
                        tmp = null;
                    }
                    counter++;
                }//endwhile
                Console.WriteLine("numBallsRemaining: ", numBallsRemaining, " numRemoved: ", numRemoved);
            }

            public void renderScene()
            {
                GL.Enable(GL_DEPTH_TEST);

                // Clear Color and Depth Buffers
                GL.Clear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                GL.ClearColor(0.5, 0.5, 0.5, 1.0);           // background is gray | ONLY takes 2 Overloads

                // Reset transformations
                GL.LoadIdentity();

                // Set the camera
                gluLookAt(0.0f, 0.0f, 10.0f,
                        0.0f, 0.0f, 0.0f,
                        0.0f, 1.0f, 0.0f);

                // Enable lighting
                GL.Enable(GL_LIGHTING);
                GL.Enable(GL_LIGHT0);
                GL.Enable(GL_COLOR_MATERIAL);

                // draw bbx
                drawBBX();

                // draw yPlane
                GL.LineWidth(2);
                GL.Begin(GL_LINES);  //draw the yPlane
                GL.Vertex3(-4.0f, yPlane, 0.0f);
                GL.Vertex3(4.0f, yPlane, 0.0f);
                GL.End();

                //draw the ball(s)
                //ball.Draw();
                DrawAllBalls();
                Target.DrawALLBalls();

                //draw the cannon
                //double cX; //<<next 3 lines, for 2d
                //double cY;
                //getCannonEndPts(angle1,cX,cY);
                double cX;
                double cY;
                double cZ;
                getCannonEndPts3D(angle1, angle2, cX, cY, cZ);
                GL.Color3(0, 0, 1);
                GL.LineWidth(2);
                GL.Begin(GL_LINES);  //draw the yPlane
                GL.Vertex3(cannon.GetX(), cannon.GetY(), cannon.GetZ());
                GL.Vertex3(cX, cY, cZ);
                GL.End();

                //ball.Update(dt);
                UpdateAllBalls();
                Target.Update(head);

                GLUT.glutSwapBuffers();
            }

            public void processNormalKeys(char key, int x, int y)
            {
                Vector3d vel = new Vector3d(0, 0, 0);
                Vector3d accelVec = new Vector3d(cX - cannon.GetX(), cY - cannon.GetY(), cZ - cannon.GetZ());
                Vector3d stPt = new Vector3d(cX, cY + curRadius, cZ);

                if (key == 27 || key == 'q')  //quit
                    exit(0);
                else if (key == 's')
                { //shoot
                    double cX, cY, cZ;
                    getCannonEndPts3D(angle1, angle2, cX, cY, cZ);
                    double accel = maxAccel * (cannonL / maxCannonL);
                    Vector3d vel = new Vector3d(0, 0, 0);
                    Vector3d accelVec = new Vector3d(cX - cannon.GetX(), cY - cannon.GetY(), cZ - cannon.GetZ());
                    Vector3d stPt = new Vector3d(cX, cY + curRadius, cZ);
                    //Vector3d stPt(cX, cY+curRadius,cZ);
                    //Vector3d vel(0,0,0);
                    //Vector3d accelVec(cX-cannon.GetX(),cY - cannon.GetY(),cZ - cannon.GetZ());
                    accelVec.selfNormalize();
                    accelVec.selfScale(accel);
                    //ball.SetValues(curRadius,stPt,vel,accelVec,yPlane);
                    AddBall(curRadius, stPt, vel, accelVec);
                }
                else if (key == 'd')
                {
                    RemoveAllNonMoving();
                }
                else if (key == '1')
                {
                    cannonL -= 0.02;
                    if (cannonL < minCannonL) cannonL = minCannonL;
                }
                else if (key == '2')
                {
                    cannonL += 0.02;
                    if (cannonL > maxCannonL) cannonL = maxCannonL;
                }
                else if (key == '9')
                {
                    curRadius -= 0.2;
                    if (curRadius < minRadius) curRadius = minRadius;
                }
                else if (key == '0')
                {
                    curRadius += 0.2;
                    if (curRadius > maxRadius) curRadius = maxRadius;
                }
            }

            public void ProcessSpecialKeys(int key, int x, int y)
            {

                switch (key)
                {
                    case GLUT_KEY_UP:
                        angle1 += 1;
                        if (angle1 >= 100) angle1 = 100;
                        break;
                    case GLUT_KEY_DOWN:
                        angle1 -= 1;
                        if (angle1 <= 0) angle1 = 0;
                        break;
                    case GLUT_KEY_LEFT:
                        angle2 += 1;
                        if (angle2 >= 270) angle2 = 270;
                        break;
                    case GLUT_KEY_RIGHT:
                        angle2 -= 1;
                        if (angle2 <= 90) angle2 = 90;
                }
            }

            /*int main(int argc, char** argv)
            {
                //cannon.SetAll(-4,yPlane,0);
                cannon.SetAll(0, yPlane, 0);
                //set the target bbx
                target.SetBBX(bbx);

                // init GLUT and create window
                glutInit(&argc, argv);
                glutInitDisplayMode(GLUT_DEPTH | GLUT_DOUBLE | GLUT_RGBA);
                glutInitWindowPosition(100, 100);
                glutInitWindowSize(320, 320);
                glutCreateWindow("ShootPts");

                init();
                // register callbacks
                glutDisplayFunc(renderScene);
                glutReshapeFunc(changeSize);
                glutIdleFunc(renderScene);

                // here are the new entries
                glutKeyboardFunc(processNormalKeys);
                glutSpecialFunc(processSpecialKeys);

                // enter GLUT event processing cycle
                glutMainLoop();

                return 1;
            }*/


            public void init()
                {
                    

                    GL.mat_specular[] = { 1.0, 1.0, 1.0, 0.0 };
                    GL.mfloat mat_shininess[] = { 10.0 };
                    GL.float light_position[] = { 1.0, 1.0, 1.0, 0.0 };
                    GL.float light_ambient[] = { 0.8, 0.8, 0.8, 1.0 };
                    GL.float light_diffuse[] = { 1.0, 1.0, 1.0, 1.0 };
                    GL.float light_specular[] = { 0.8, 0.8, 0.8, 1.0 };
                    GL.ClearColor(0.0, 0.0, 0.0, 0.0);  //ONLY takes 2 overloads
                    GL.ShadeModel(GL_SMOOTH);

                    GL.Materialfv(GL_FRONT_AND_BACK, GL_SPECULAR, mat_specular);
                    GL.Materialfv(GL_FRONT_AND_BACK, GL_SHININESS, mat_shininess);


                    gl.Light(GL_LIGHT0, GL_AMBIENT, light_ambient);
                    glLightfv(GL_LIGHT0, GL_DIFFUSE, light_diffuse);
                    glLightfv(GL_LIGHT0, GL_SPECULAR, light_specular);
                    glLightfv(GL_LIGHT0, GL_POSITION, light_position);

                    glEnable(GL_LIGHTING);
                    glEnable(GL_LIGHT0);
                    glEnable(GL_DEPTH_TEST);
                }
            public void Main(string[] args)
            { //cannon.SetAll(-4,yPlane,0);
                cannon.SetAll(0, yPlane, 0);
                //set the target bbx
                target.SetBBX(bbx);

                // init GLUT and create window
                glutInit(&argc, argv);
                glutInitDisplayMode(GLUT_DEPTH | GLUT_DOUBLE | GLUT_RGBA);
                glutInitWindowPosition(100, 100);
                glutInitWindowSize(320, 320);
                glutCreateWindow("ShootPts");

                init();
                // register callbacks
                glutDisplayFunc(renderScene);
                glutReshapeFunc(changeSize);
                glutIdleFunc(renderScene);

                // here are the new entries
                glutKeyboardFunc(processNormalKeys);
                glutSpecialFunc(processSpecialKeys);

                // enter GLUT event processing cycle
                glutMainLoop();

                return 1;

            }

        }//end of tester / main
    }
}





