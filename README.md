<h1>Java Utility</h1>
Classes and Functionality I find lacking in the Java community

<h2>Justification</h2>
As some of these utilities provide functionality supplied by other more popular libraries
I think it is appropriate I justify why I created them.

<h3>Geometry</h3>
Points and Vectors. Existing libraries tend to fall under two categories:
<ol>
    <li> A General Vector/Point Class. This class represents all dimensions and accepts/returns Vector/Point in its methods. </li>
    <li> Separate VectorXD and PointXD Classes. These classes represent the 2D and 3D flavours and only accept/return similar types in their methods.</li>
</ol>

The General Vector has its advantages; all vectors have the same implementation and therefore the same code base and interface. However, some vector operations
are dimension specific (eg. cross product for 2D and 3D vectors have different return types) and some methods should only work on a vector with the same dimension (eg. 3D vector cannot perform dot product with 2D vector without making assumptions about the default z-component). This leads to either vectors with lower dimensions having their missing components filled in with default values or exceptions being thrown when the dimensions do not match.

The Separate Vectors solve the problem of dimension specific operations but the drawbacks are worse. Most (if not all) libraries that take this approach do not have Vector2D share an interface or parent class with Vector3D (same with Points) and so it is not possible to have a list of any Vector or have a generic class work with 2D or 3D objects depending on how it is initialized.

My Geometry package attempts to take the benefits of both approaches without any of the limitations. There is a single interface (eg Point) that accepts as a generic parameter its dimensionality.

	Point<D2> point = Points.new2DPoint(x, y);

This allows the creation of classes that can accept a dimension of point or vector specified at instatiation.

	public class MyClass<D extends Dimension> {
		private Point<D> point;
		private Vector<D> vector;
		...
	}
        
 
Additionally, the interface has been designed so that an object of dimension D can only accept other objects of dimension D for most if its methods

	Vector<D2> vector ...
	Vector<D2> other ...
	Vector<D2> result = vector.dot(other); // syntactically valid
	Vector<D3> badOther ...
	vector.dot(badOther); // syntactically invalid
        
The Dimension hierarchy has been designed so that generic bounding (extends and super) can be used to specify dimensions higher than or lower than a specified dimension. <b>super</b> is interpreted as "equal or lower than" and <b>extends</b> is interpreted as "equal or greater than".

	public interface MyInterface<D extends Dimension> {
		// Accept Points with equal or lower dimension to D
		public void doSomething(Point<? super D> point);
	}
	
	MyInterface<D2> inter = ...
	Point<D1> point1D = ...
	inter.doSomething(point1D); // syntactically valid
	Point<D3> point3D = ...
	inter.doSomething(point3D); //syntactically invalid
	
	
Separate classes exist that conform to these interfaces but also provide dimension specific functionality
	
	Vector3D vector = Vector3D.newVector(x, y, z);
	double x = vector.getX();
	Vector3D cross = vector.cross(other3DVector);

while utility classes exist to work with the generic Vector and Point classes

	Vector<D3> vector = Vectors.new3DVector(x, y, z);
	Vector<D3> cross = Vectors.cross(vector, other3DVector);
