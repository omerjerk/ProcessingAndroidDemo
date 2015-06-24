package in.omerjerk.processingdemo.sketch;

import processing.core.PApplet;

/**
 * Created by root on 24/6/15.
 */
public class Reflection extends PApplet {

    @Override
    public void settings() {
        size(1000, 1000, P3D);
    }

    @Override
    public void setup() {
        noStroke();
        colorMode(RGB, 1);
        fill(0.4f);
    }

    @Override
    public void draw() {
        background(0);
        translate(width / 2, height / 2);
        // Set the specular color of lights that follow
        lightSpecular(1, 1, 1);
        directionalLight(0.8f, 0.8f, 0.8f, 0, 0, -1);
        float s = mouseX / PApplet.parseFloat(width);
        specular(s, s, s);
        sphere(120);
    }
}
