package com.TimerFX.util.helpers;

import java.awt.*;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import static com.TimerFX.util.helpers.ImageGetter.Extension.JPG_EXTENSION;
import static com.TimerFX.util.helpers.ImageGetter.Extension.PNG_EXTENSION;

public class ImageGetter {
    public static final String PACKAGE_LOCATION = "/com/TimerFX/images/";
    public static final Optional EMPTY_OPTIONAL = Optional.empty();

    private static ImageGetter admin;

    private final ImageMapper imageMapper;

    private final Toolkit toolkit;

    public enum Extension {

        PNG_EXTENSION(".png"),
        JPG_EXTENSION(".jpg");

        public final String value;

        Extension(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return String.join(" - ", this.name(), this.getValue());
        }
    }

    private ImageGetter() {
        this.toolkit = Toolkit.getDefaultToolkit();
        this.imageMapper = new ImageMapper();
    }

    public static ImageGetter getInstance() {
        if (admin == null) {
            admin = new ImageGetter();

        }

        return admin;
    }

    /**
     * Get the image with the specific extension in Resources, inside a <i>{@link Optional}</i>.
     */
    public Optional<Image> getImage(String imageName, Extension imageExtension) {
        switch (imageExtension) {

            case JPG_EXTENSION:
                return admin.getJpgImage(imageName);

            case PNG_EXTENSION:
                return admin.getPngImage(imageName);

            default:
                return EMPTY_OPTIONAL;
        }
    }

    /**
     * Get an image saved inside the resource folder as a JPG image.<br/>
     * * It can return empty images.
     */
    private Optional<Image> getJpgImage(String imageName) {
        return getOptionalImage(imageName, JPG_EXTENSION);
    }

    /**
     * Get an image saved inside the resource folder as a PNG image.<br/>
     * It can return empty images.
     */
    private Optional<Image> getPngImage(String imageName) {
        return getOptionalImage(imageName, PNG_EXTENSION);
    }

    /**
     * Get the URL of the image
     */
    private Optional<URL> getImageLocation(String imageName, Extension extension) {
        final String imageLocation = String.join("", PACKAGE_LOCATION, imageName, extension.getValue());

        return Optional.ofNullable(getClass().getResource(imageLocation));
    }

    /**
     * Return a {@link Optional} Object with a {@link Image} inside, if it exists in Resources.
     *
     * @apiNote Use Optional.isEmpty or Optional.isPresent to check the content.
     */
    private Optional<Image> getOptionalImage(String imageName, Extension extension) {
        Image image;
        if (imageName.isBlank()) {
            return EMPTY_OPTIONAL;
        }

        Optional<URL> imageLocation = admin.getImageLocation(imageName, extension);

        if (imageLocation.isEmpty()) {
            return EMPTY_OPTIONAL;
        }

        image = admin.toolkit.createImage(imageLocation.get());

        return Optional.of(image);
    }

    //    TODO : Check if it null
    public Optional<javafx.scene.image.Image> getImageFX(String imageName, Extension imageExtension) {
        Optional<Image> optionalImage = admin.getImage(imageName, imageExtension);

        return Optional.ofNullable(createImageFX(optionalImage.get()));
    }

    private static javafx.scene.image.Image createImageFX(Image optionalImage) {
        if (Objects.isNull(optionalImage)) {
            return null;
        }
        return admin.imageMapper.createImage(optionalImage);
    }
}
