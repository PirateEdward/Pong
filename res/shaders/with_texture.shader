#vertex
#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 texCoords;

uniform mat4 pr_matrix;
uniform mat4 ml_matrix = mat4(1.0);

out DATA
{
	vec2 tc;
} vs_out;

void main()
{
	gl_Position = pr_matrix * ml_matrix * position;
	vs_out.tc = texCoords;
}

#fragment
#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
} fs_in;

uniform sampler2D tex;

void main()
{
	color = texture(tex, fs_in.tc);
}