package in.omerjerk.processingdemo.sketch;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

/**
 * Created by root on 25/6/15.
 */
public class Particles extends PApplet {

    ParticleSystem ps;
    PImage sprite;

    @Override
    public void settings() {
        size(1000, 1000, P2D);
    }

    @Override
    public void setup() {
        sprite = loadImage("sprite.png");
        ps = new ParticleSystem(2000);

        // Writing to the depth buffer is disabled to avoid rendering
        // artifacts due to the fact that the particles are semi-transparent
        // but not z-sorted.
        hint(DISABLE_DEPTH_MASK);
    }

    @Override
    public void draw() {
        background(0);
        ps.update();
        ps.display();

        ps.setEmitter(mouseX,mouseY);

        fill(255);
        textSize(16);
        text("Frame rate: " + PApplet.parseInt(frameRate),10,20);
    }

    class Particle {

        PVector velocity;
        float lifespan = 255;

        PShape part;
        float partSize;

        PVector gravity = new PVector(0,0.1f);


        Particle() {
            partSize = random(10,60);
            part = createShape();
            part.beginShape(QUAD);
            part.noStroke();
            part.texture(sprite);
            part.normal(0, 0, 1);
            part.vertex(-partSize/2, -partSize/2, 0, 0);
            part.vertex(+partSize/2, -partSize/2, sprite.width, 0);
            part.vertex(+partSize/2, +partSize/2, sprite.width, sprite.height);
            part.vertex(-partSize/2, +partSize/2, 0, sprite.height);
            part.endShape();

            rebirth(width/2,height/2);
            lifespan = random(255);
        }

        PShape getShape() {
            return part;
        }

        void rebirth(float x, float y) {
            float a = random(TWO_PI);
            float speed = random(0.5f,4);
            velocity = new PVector(cos(a), sin(a));
            velocity.mult(speed);
            lifespan = 255;
            part.resetMatrix();
            part.translate(x, y);
        }

        boolean isDead() {
            if (lifespan < 0) {
                return true;
            } else {
                return false;
            }
        }


        public void update() {
            lifespan = lifespan - 1;
            velocity.add(gravity);

            part.setTint(color(255,lifespan));
            part.translate(velocity.x, velocity.y);
        }
    }

    class ParticleSystem {
        ArrayList<Particle> particles;

        PShape particleShape;

        ParticleSystem(int n) {
            particles = new ArrayList<Particle>();
            particleShape = createShape(PShape.GROUP);

            for (int i = 0; i < n; i++) {
                Particle p = new Particle();
                particles.add(p);
                particleShape.addChild(p.getShape());
            }
        }

        void update() {
            for (Particle p : particles) {
                p.update();
            }
        }

        void setEmitter(float x, float y) {
            for (Particle p : particles) {
                if (p.isDead()) {
                    p.rebirth(x, y);
                }
            }
        }

        void display() {

            shape(particleShape);
        }
    }
}
