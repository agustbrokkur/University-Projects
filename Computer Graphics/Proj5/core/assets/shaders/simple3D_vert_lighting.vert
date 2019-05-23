
attribute vec3 a_position;
attribute vec3 a_normal;

uniform mat4 u_modelMatrix;
uniform mat4 u_viewMatrix;
uniform mat4 u_projectionMatrix;

uniform vec4 u_material_diffuse;
uniform vec4 u_material_specular;
uniform float u_material_shininess;

uniform vec4 u_light_position;
uniform vec4 u_light_color;

uniform vec4 u_eye_position;

varying vec4 v_color;

void main()
{
	vec4 position = vec4(a_position.x, a_position.y, a_position.z, 1.0);
	position = u_modelMatrix * position;

	vec4 normal = vec4(a_normal.x, a_normal.y, a_normal.z, 0.0);
	normal = u_modelMatrix * normal;
	
	vec4 s = u_light_position - position;
	vec4 v = u_eye_position - position;
	vec4 h = s + v;
	
	float n_length = length(normal);
	
	float lambert = max(0.0, dot(normal, s) / (n_length * length(s)));
	float phong = max(0.0, dot(normal, h) / (n_length * length(h)));
	
	v_color = lambert * u_light_color * u_material_diffuse 
			+ pow(phong, u_material_shininess) * u_light_color * u_material_specular;
	
	position = u_viewMatrix * position;
	//normal = u_viewmatrix * normal;
	
	gl_Position = u_projectionMatrix * position;
}