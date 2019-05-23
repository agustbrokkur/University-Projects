
uniform vec4 u_material_diffuse;
uniform vec4 u_material_specular;
uniform float u_material_shininess;

uniform vec4 u_light_ambient;
uniform vec4 u_light_color;

varying vec4 v_n;
varying vec4 v_s;
varying vec4 v_h;

void main()
{
	
	float n_length = length(v_n);
	
	float lambert = max(0.0, dot(v_n, v_s) / (n_length * length(v_s)));
	float phong = max(0.0, dot(v_n, v_h) / (n_length * length(v_h)));
	
	gl_FragColor = u_light_ambient * u_material_diffuse
			+ lambert * u_light_color * u_material_diffuse 
			+ pow(phong, u_material_shininess) * u_light_color * u_material_specular;
	
}