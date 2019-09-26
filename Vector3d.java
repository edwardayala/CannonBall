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
    // template?

    // Assignment
    Vector3d operator(double _x, double _y, double _z){   // operator()
        m_v[0] = _x;
        m_v[1] = _y;
        m_v[2] = _z;
        return this;
    }
    Vector3d assignOp(Vector3d _v){     // operator=
        m_v[0] = _v.m_v[0];
        m_v[1] = _v.m_v[1];
        m_v[2] = _v.m_v[2];
        return this;
    }
    Vector3d assignOp(double[] _d){   // operator=
        m_v[0] = _d[0];
        m_v[1] = _d[1];
        m_v[2] = _d[2];
        return this;
    }

    // Access
    double accessOp(int idx){   // double operator[](int idx)
        return m_v[idx];
    }
    // already declared ^
//    final double operator(int idx){
//        return m_v[idx];
//    }

    // already declared
//    final double[] begin(){
//        return m_v;
//    }
//
//    final double[] end(){ // ?
//        return m_v;
//    }

    double[] begin(){
        return m_v;
    }

    double [] end(){     // ?
        return m_v;
    }


    // Operators

    // equality
    boolean equalOp(Vector3d _v){
        return m_v[0] == _v.m_v[0] && m_v[1] == _v.m_v[1] && m_v[2] == _v.m_v[2];
    }

    // inequality
    boolean ineqlOp(Vector3d _v){
        return !(this == _v);
    }

    // self addition
    Vector3d selfAdd(Vector3d _v){
        m_v[0] += _v.m_v[0];
        m_v[1] += _v.m_v[1];
        m_v[2] += _v.m_v[2];
        return this;
    }

    // self subtraction
    Vector3d selfSub(Vector3d _v){
        m_v[0] -= _v.m_v[0];
        m_v[1] -= _v.m_v[1];
        m_v[2] -= _v.m_v[2];
        return this;
    }

    // self scalar multiply
    Vector3d selfScalMult(double _d){
        m_v[0] *= _d;
        m_v[1] *= _d;
        m_v[2] *= _d;
        return this;
    }

    // self scalar divide
    Vector3d selfScalDiv(double _d){
        m_v[0] /= _d;
        m_v[1] /= _d;
        m_v[2] /= _d;
        return this;
    }

    // self mult
    Vector3d selfMult(Vector3d _v){
        m_v[0] *= _v.m_v[0];
        m_v[1] *= _v.m_v[1];
        m_v[2] *= _v.m_v[2];
        return this;
    }

    // self cross product
    Vector3d selfCross(Vector3d _v){
        double v0 = m_v[0], v1 = m_v[1], v2 = m_v[2];
        m_v[0] = v1 * _v.m_v[2] - v2 * _v.m_v[1];
        m_v[1] = v2 * _v.m_v[0] - v0 * _v.m_v[2];
        m_v[2] = v0 * _v.m_v[1] - v1 * _v.m_v[0];
        return this;
    }

    // negation
    Vector3d negate(){
        return new Vector3d(-m_v[0], -m_v[1], -m_v[2]);
    }

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

    // component *
    Vector3d component(Vector3d _v){
        return new Vector3d(m_v[0] * _v.m_v[0], m_v[1] * _v.m_v[1], m_v[2] * _v.m_v[2]);
    }

    // cross product
    Vector3d crossProd(Vector3d _v){
        return new Vector3d(m_v[0] %= _v.m_v[0], m_v[1] %= _v.m_v[1], m_v[2] %= _v.m_v[2]);
    }

    // dot product
    double dotProd(Vector3d _v){
        return m_v[0] * _v.m_v[0] + m_v[1] * _v.m_v[1] + m_v[2] * _v.m_v[2];
    }

    // magnitude
    double norm(){
        return Math.sqrt(normsqr());
    }

    // magnitude squared
    double normsqr(){
        // norm = sqrt(abs(x)^2 + abs(y)^2 + abs(z)^2)
        double x = Math.abs(Math.pow(m_v[0],2));    // abs(x)^2
        double y = Math.abs(Math.pow(m_v[1],2));    // abs(y)^2
        double z = Math.abs(Math.pow(m_v[2],2));    // abs(z)^2
        return x+y+z; // norm function returns the sqrt(x+y+z)
    }

    // normalized Vector3d
    Vector3d selfNormalize(){
        double n = norm();
        if (n < Double.MAX_VALUE){
            return this;
        }
        return new Vector3d(m_v[0]/n,m_v[1]/n,m_v[2]/n);
    }

    // normalize
    Vector3d normalize(){
        double n = norm();
        if (n < Double.MAX_VALUE)
            return this;
        return new Vector3d(m_v[0]/n,m_v[1]/n,m_v[2]/n);
    }

    // I dont know the difference between normalize() and selfNormalize()

    // Projections
    // find |component| of this along _v's direction
    double comp(Vector3d _v){
        return (this.m_v[0] * _v.normalize().m_v[0] + this.m_v[1] * _v.normalize().m_v[1] + this.m_v[2] * _v.normalize().m_v[2]);
    }

    // scale Vector3d
    // selfScale
    Vector3d selfScale(double _l){
        double n = norm();
        if (n < Double.MAX_VALUE)
            return new Vector3d(this);
        return new Vector3d(m_v[0] *_l/n, m_v[1] *_l/n, m_v[2] *_l/n);
    }

    // scale
    Vector3d scale(double _l){
        double n = norm();
        if (n < Double.MAX_VALUE)
            return this;
        return new Vector3d(m_v[0] *_l/n, m_v[1] *_l/n, m_v[2] *_l/n);
    }

    // rotate Vector3d
    Vector3d rotateX(double _rad){
        double c = Math.cos(_rad), s = Math.sin(_rad);
        return operator(m_v[0], m_v[1]*c - m_v[2]*s, m_v[1]*s + m_v[2]*c);
    }
    Vector3d rotateXd(double _deg){
        return rotateX(Math.toRadians(_deg));
    }
    Vector3d rotateY(double _rad){
        double c = Math.cos(_rad), s = Math.sin(_rad);
        return operator(m_v[0]*c + m_v[2]*s, m_v[1], -m_v[0]*s + m_v[2]*c);
    }
    Vector3d rotateYd(double _deg){
        return rotateY(Math.toRadians(_deg));
    }
    Vector3d rotateZ(double _rad){
        double c = Math.cos(_rad), s = Math.sin(_rad);
        return operator(m_v[0]*c - m_v[1]*s, m_v[0]*s + m_v[1]*c, m_v[2]);
    }
    Vector3d rotateZd(double _deg){
        return rotateZ(Math.toRadians(_deg));
    }

    // reset
    void reset(){
        m_v[0] = 0;
        m_v[1] = 0;
        m_v[2] = 0;
    }

    // Set & Get
    double getX(){
        return m_v[0];
    }
    double getY(){
        return m_v[1];
    }
    double getZ(){
        return m_v[2];
    }

    void setX(double d){
        m_v[0] = d;
    }
    void setY(double d){
        m_v[1] = d;
    }
    void setZ(double d){
        m_v[2] = d;
    }
    void setAll(double dX, double dY, double dZ){
        m_v[0] = dX;
        m_v[1] = dY;
        m_v[2] = dZ;
    }
}