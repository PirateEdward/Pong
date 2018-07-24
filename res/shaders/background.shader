#vertex
#version 330 core

layout(location = 0) in vec4 position;

uniform mat4 pr_matrix;
uniform mat4 ml_matrix;

out vec4 p;

void main()
{
	p = ml_matrix * position;
	gl_Position = pr_matrix * p;
}

#fragment
#version 330 core

layout (location = 0) out vec4 color;

in vec4 p;

uniform vec2 ball;
uniform vec3 ball_color;

void main()
{
	//color = vec4(0.1, 0.1, 0.25, 1.0);
	color = vec4(ball_color, 1.0);
	color *= 8.0 / (length(8 * (ball - vec2(p))) + 4.0) + 0.2;
}