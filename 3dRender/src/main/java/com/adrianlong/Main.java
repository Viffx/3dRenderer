package com.adrianlong;

import com.adrianlong.display.Display;
import com.adrianlong.display.Polygon;
import com.adrianlong.utils.Vector2;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Display d = new Display("display",Toolkit.getDefaultToolkit().getScreenSize());
        Render render = new Render();
        render.readFile(
                "C:\\Users\\adria\\Documents\\Java Projects\\3dRender\\src\\main\\resources\\Fighter_ship.obj"
        ).parse();

//        render.model.get(4).x -= 300;
        d.shapes.add(render);
//        render.rotateModelY(1.5708);
//        render.rotateModelX(1.5708);
        render.cameraPos.y-= 10;
        render.rotateModelX(Math.toRadians(90));
        render.rotateModelY(Math.toRadians(20));
        while (true) {
            render.run(d.getSize(),300);
            d.repaint();
            render.rotateModelZ(0.05);
            Thread.sleep(20);
        }
    }
}