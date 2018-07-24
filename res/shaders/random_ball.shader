#vertex
#version 330 core

layout(location = 0) in vec4 position;

uniform mat4 pr_matrix;
uniform mat4 ml_matrix;

void main()
{
	gl_Position = pr_matrix * ml_matrix * position;
}

#fragment
#version 330 core

layout (location = 0) out vec4 color;

uniform vec3 c;

void main()
{	
	color = vec4(c, 1.0);
}