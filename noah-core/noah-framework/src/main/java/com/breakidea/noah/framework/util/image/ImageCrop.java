package com.breakidea.noah.framework.util.image;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.util.Assert;

public class ImageCrop {

	public static BufferedImage dispatchCrop(InputStream fis, Rectangle rectangle) throws IOException {
		Assert.notNull(fis, "FileInputStream must not be null");
		Assert.notNull(rectangle, "Rectangle must not be null");

		ImageReadParam param = null;
		BufferedImage result = null;

		try {

			ImageInputStream iis = ImageIO.createImageInputStream(fis);
			Iterator<ImageReader> it = ImageIO.getImageReaders(iis);
			ImageReader reader = it.next();

			reader.setInput(iis, true);

			param = reader.getDefaultReadParam();
			param.setSourceRegion(rectangle);

			result = reader.read(0, param);
		} catch (IOException ex) {
			throw new IOException("Failed to crop image", ex);
		}

		return result;
	}
}
