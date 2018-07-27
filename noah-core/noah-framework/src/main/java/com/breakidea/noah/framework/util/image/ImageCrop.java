package com.breakidea.noah.framework.util.image;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageCrop {

	/**
	 * @param imageReader
	 * @param rectangle
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage cutCataRectangle(ImageReader imageReader, Rectangle rectangle) throws IOException {
		try {
			ImageReadParam param = imageReader.getDefaultReadParam();

			param.setSourceRegion(rectangle);
			BufferedImage bi = imageReader.read(0, param);

			return bi;
		} catch (IOException ex) {
			throw new IOException("failed cut image", ex);
		}
	}

	/**
	 * @param is
	 * @param out
	 * @param rectangle
	 * @return
	 * @throws IOException
	 */
	public static boolean dispatchCrop(InputStream is, OutputStream out, Rectangle rectangle) throws IOException {
		try {
			ImageReader imageReader = getImageReader(is);
			BufferedImage bufferedImage = cutCataRectangle(imageReader, rectangle);

			String formatName = imageReader.getFormatName();
			ImageIO.write(bufferedImage, formatName, out);

			return true;
		} catch (Exception ex) {
			throw new IOException("failed dispatch crop image", ex);
		}
	}

	/**
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static ImageReader getImageReader(InputStream is) throws IOException {
		try {
			ImageInputStream iis = ImageIO.createImageInputStream(is);
			Iterator<ImageReader> it = ImageIO.getImageReaders(iis);
			ImageReader reader = it.next();

			reader.setInput(iis, true);
			return reader;
		} catch (IOException ex) {
			throw new IOException("failed convert ImageReader", ex);
		}
	}
}
