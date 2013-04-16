<h1>Java Utility<h1>
Classes and Functionality I find lacking in the Java community

<h2>Justification<h2>
As some of these utilities provide functionality supplied by other more popular libraries
I think it is appropriate I justify why I created them.

<h3>Geometry<h3>
Points and Vectors. Existing libraries tend to fall under two categories:
<ol>
    <li> A General Vector/Point Class. This class represents all dimensions and accepts/returns Vector/Point in its methods. </li>
    <li> Separate VectorXD and PointXD Classes. These classes represent the 2D and 3D vectors and only accept/return similar types in their methods.</li>
</ol>

The General Vector has its advantages; all vectors have the same implementation and therefore the same code base. However, some vector operations
are dimension specific (eg. cross product for 2D and 3D vectors have different return types) and all methods should only work on a vector with the same dimension (eg. 3D vector cannot perform dot product with 2D vector without making assumptions about the default z-component). This leads to either vectors with lower dimensions having their missing components filled in with default values or exceptions being thrown when the dimensions do not match.

The Separate Vectors solve the problem of dimension specific operations but the drawbacks are worse. Most (if not all) libraries that take this approach do not have Vector2D share an interface or parent class with Vector3D (same with Points) and so it is not possible to have a list of any Vector or have a generic class work with 2D or 3D objects depending on how it is initialized.

My Geometry package attempts to take the benefits of both approaches without any of the limitations. There is a single interface (eg Point) that accepts as a generic parameter it dimensionality.

	<code>Point<D2> point = Points.new2DPoint(x, y);</code>

This allows the creation of classes that can accept either 2D or 3D Points and Vectors

	<code>public class MyClass<D extends Dimension> {
		private Point<D> point;
		private Vector<D> vector;

		...
	      }
        </code>
 
Additionally, the interface has been designed so that a Point of type D can only accept other Points of type D

	<code>Vector<D2> other ...
	      Vector<D3> badOther ...
	      Vector<D2> result = vector.dot(other); // syntactically valid
              vector.dot(badOther); // syntacticaly invalid
        </code>

Separate classes exist that conform to these interfaces but also provide dimension specific functionality
	
	<code>Point2D point ...
	      double x = point.getX(); </code>

while utility classes exist to work with the generic Vector and Point classes

	<code>Vector<D2> vector = Vectors.new2DVector(x, y, z);
	      Vector<D2> cross = Vectors.cross(vector, otherVector); </code>
