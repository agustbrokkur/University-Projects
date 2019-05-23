
attribute vec3 a_position;
attribute vec3 a_normal;

uniform mat4 u_modelMatrix;
uniform mat4 u_viewMatrix;
uniform mat4 u_projectionMatrix;

uniform vec4 u_light_position;

uniform vec4 u_eye_position;

varying vec4 v_n;
varying vec4 v_s;
varying vec4 v_h;

void main()
{
	vec4 position = vec4(a_position.x, a_position.y, a_position.z, 1.0);
	position = u_modelMatrix * position;

	v_n = normalize(u_modelMatrix * vec4(a_normal.x, a_normal.y, a_normal.z, 0.0));
	
	v_s = normalize(u_light_position - position);
	vec4 v = u_eye_position - position;
	v_h = normalize(v_s + v);
	
	position = u_viewMatrix * position;
	//normal = u_viewmatrix * normal;
	
	gl_Position = u_projectionMatrix * position;
}