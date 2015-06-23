package in.omerjerk.processingdemo.sketch;

import android.app.Activity;

import in.omerjerk.processingdemo.MainActivity;
import processing.core.PApplet;

/**
 * Created by root on 23/6/15.
 */
public class Directional extends PApplet {

    @Override
    public void setup() {
//        fullScreen(P3D);
        noStroke();
        fill(204);
    }

    @Override
    public void draw() {
        noStroke();
        background(0);
        float dirY = (mouseY / PApplet.parseFloat(height) - 0.5f) * 2;
        float dirX = (mouseX / PApplet.parseFloat(width) - 0.5f) * 2;
        directionalLight(204, 204, 204, -dirX, -dirY, -1);
        translate(width/2 - 100, height/2, 0);
        sphere(80);
        translate(200, 0, 0);
        sphere(80);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(0);
    }

    @Override
    public void settings() {
        size(1000, 1000, P3D);
    }
}
