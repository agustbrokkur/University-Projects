package com.ru.tgra.shapes;

public class Point3D {

	public float x;
	public float y;
	public float z;

	public Point3D()
	{
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Point3D(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void add(Vector3D v)
	{
		x += v.x;
		y += v.y;
		z += v.z;
	}

	public Point3D sum(Point3D p)
	{
		return new Point3D(x + p.x, y + p.y, z + p.z);
	}
	
	public Point3D multiply(float t) {
		float x1 = x * t;
		float y1 = y * t;
		float z1 = z * t;

		return new Point3D(x1, y1, z1);
	}
	
	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float distance(Point3D point) {
		float x1 = (float)Math.pow((this.x - point.x), 2);
		float y1 = (float)Math.pow((this.y - point.y), 2);
		float z1 = (float)Math.pow((this.z - point.z), 2);
		
		float result = (float)Math.sqrt(x1 + y1 + z1);
		
		return result;
	}
	/*
	private Point3D lerp(Point3D a, Point3D b, float t) {
		if(t >= 1) {
			t = 1;
		}
		Point3D newPoint1 = a.multiply((1 - t));
		Point3D newPoint2 = b.multiply(t);
		
		Point3D result = newPoint1.sum(newPoint2);
		
		return result;
	}*/
	
	public void lerp4(Point3D start, Point3D mid1, Point3D mid2, Point3D end, 
								float currentTime, float startTime, float endTime) {
		float t = (currentTime - startTime) / ( endTime - startTime);
		//P = (1-t)^3 * P1 + 3(1-t)^2 * t * P2 + 3(1-t) * t^2 * P3 + t^3 * P4
		if(t > 1) {
			t = 1;
		}
		
		
		//Version 1
		// Straight
		/*
		Point3D p1 = lerp(start, mid1, t);
		Point3D p2 = lerp(mid1, mid2, t);
		Point3D p3 = lerp(mid2, end, t);
		
		Point3D p4 = lerp(p1, p2, t);
		Point3D p5 = lerp(p2, p3, t);
		
		Point3D p = lerp(p4, p5, t);
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
		*/
		
		// Version 2
		// Curved
		
		Point3D P1 = start.multiply((float)Math.pow((1 - t), 3));
		Point3D P2 = mid1.multiply((float)Math.pow(3*(1-t), 2) * t);
		Point3D P3 = mid2.multiply(3* (1-t) * (float)Math.pow(t, 2));
		Point3D P4 = end.multiply((float)Math.pow(t, 3));
		
		Point3D P = new Point3D();
		P = P.sum(P1.sum(P2.sum(P3.sum(P4))));
		this.x = P.x;
		this.y = P.y;
		this.z = P.z;
		
	}
}
