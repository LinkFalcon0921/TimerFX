package com.TimerFX.util.helpers;

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.*;
import java.util.Optional;

public class ImageGetterTest {

    public static final String NOT_EXISTS_THIS_IMAGE_IN_RESOURCES = "Not exists this image in resources";
    public static final String IMAGE_FOUND_IN_RESOURCES = "Not exists this image in resources";
    private ImageGetter imageGetter;

    @Test
    public void image_JPG_not_Expected() {
        final String imageName = "pixel_Art_0291313";
        imageGetter = ImageGetter.getInstance();

        Optional<Image> image;

        image = this.imageGetter.getImage(imageName, ImageGetter.Extension.JPG_EXTENSION);

        assertTrue(IMAGE_FOUND_IN_RESOURCES,image.isEmpty());

    }

    @Test
    public void getPngImage() {
        final String imageName = "time_out";
        imageGetter = ImageGetter.getInstance();

        Optional<Image> image;

        image = this.imageGetter.getImage(imageName, ImageGetter.Extension.PNG_EXTENSION);

        assertFalse(image.isEmpty());

        assertNotNull(NOT_EXISTS_THIS_IMAGE_IN_RESOURCES,image.get());

    }

    @Test
    public void image_PNG_not_Expected() {
        final String imageName = "pixel_Art_0291313";
        imageGetter = ImageGetter.getInstance();

        Optional<Image> image;

        image = this.imageGetter.getImage(imageName,ImageGetter.Extension.PNG_EXTENSION);

        assertTrue(IMAGE_FOUND_IN_RESOURCES,image.isEmpty());

    }
}