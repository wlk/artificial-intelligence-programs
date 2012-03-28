package w;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Reads bmp files and generates network input files, later files must be
 * concatinated into one big input file.
 * 
 * @author w
 * 
 */
public class ExtractFeatures {

	static final int numChars = 36; // allnum
	static final int matrixSize = 5;
	static final String charsPath = "/home/w/Dropbox/studia/siiw/z3/Przykladowe_probki/ext";

	public static void main(String[] args) {

		File folder = new File(charsPath);
		File[] listOfFiles = folder.listFiles();

		System.out.println(numChars + " " + matrixSize * matrixSize);

		for (int i = 0; i < listOfFiles.length; i++) {
			// for (int i = 0; i < 5; i++) {
			if (listOfFiles[i].isFile()) {
				generateFeaturesValues(listOfFiles[i]);
				System.out.println(getCorrectOutput(listOfFiles[i].getName()));

			}
		}
	}

	private static void generateFeaturesValues(File f) {
		// System.err.println(f.getName());
		BufferedImage image = null;
		// read image from file
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int chunkWidth = image.getWidth() / matrixSize;
		int chunkHeight = image.getHeight() / matrixSize;
		int count = 0;
		BufferedImage imgs[] = new BufferedImage[matrixSize * matrixSize];
		for (int x = 0; x < matrixSize; x++) {
			for (int y = 0; y < matrixSize; y++) {
				imgs[count] = new BufferedImage(chunkWidth, chunkHeight,
						image.getType());

				// draws the image chunk
				Graphics2D gr = imgs[count].createGraphics();
				gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth
						* y, chunkHeight * x, chunkWidth * y + chunkWidth,
						chunkHeight * x + chunkHeight, null);
				gr.dispose();

				System.out.print(getCountPercentage(imgs[count++]) + " ");

			}
		}

		// divide image into matrix of 25 fields
		// foreach do:
		// count number of black pixels in each field

		// print out result / (1/25 of total px in image)

	}

	private static double getCountPercentage(BufferedImage img) {
		double count = 0, count2 = 0;
		;
		for (int i = 0; i < img.getHeight(); ++i) {
			for (int j = 0; j < img.getWidth(); ++j) {
				// System.out.println(img.getRGB(j, i));
				if (img.getRGB(j, i) == -1) {
					++count;
				} else {
					++count2;
				}
			}
		}
		return count2 / (count2 + count);
		// return count2 == 0 ? 1.0 : count/count2;
	}

	/**
	 * 
	 * @param character
	 * @return
	 */
	private static String getCorrectOutput(String character) {
		String c = character.substring(0, 1);
		c = c.toLowerCase();
		int whichCharacter = 0;
		if (Character.isDigit(c.toCharArray()[0])) {
			whichCharacter = Integer.parseInt(c);
		} else {
			whichCharacter = c.toCharArray()[0] - 96 + 9;
		}
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < numChars; ++i) {
			if (i == whichCharacter) {
				sb.append("1 ");
			} else {
				sb.append("0 ");
			}
		}

		return sb.toString().substring(0, sb.length());
	}

}
