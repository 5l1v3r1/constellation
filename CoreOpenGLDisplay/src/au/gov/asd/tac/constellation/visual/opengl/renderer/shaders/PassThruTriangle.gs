#version 330 core

layout(triangles) in;
layout(triangle_strip, max_vertices=3) out;

in vec4 gColor[];
out vec4 fColor;

void main() {
    fColor = gColor[0];
    gl_Position = gl_in[0].gl_Position;
    EmitVertex();

    fColor = gColor[1];
    gl_Position = gl_in[1].gl_Position;
    EmitVertex();

    fColor = gColor[2];
    gl_Position = gl_in[2].gl_Position;
    EmitVertex();

    EndPrimitive();
}
