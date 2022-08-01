package com.TimerFX.util.helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

final class ImageMapper {

    public ImageMapper(){}

    public javafx.scene.image.Image createImage(java.awt.Image image)  {
        if (!(image instanceof RenderedImage)) {
            int width_height = 300;
            BufferedImage bufferedImage = new BufferedImage(width_height,
                    width_height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();

            image = bufferedImage;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write((RenderedImage) image, "png", out);
            out.flush();
        } catch (IOException e) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return new javafx.scene.image.Image(in);
    }

}
