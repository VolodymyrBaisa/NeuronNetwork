package brain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.w3c.dom.css.RGBColor;

import model.Neuron;
import model.NeuronImpl;
import model.Pixel;

public class NeuronNetwork {
	private Neuron[] neuronsArray;
	private static final String MEMORY_PATH = "../NeuronNetwork/memory/";
	private static final String FILE_EXTENSION = ".bmp";
	private int maxNeuronWeight;
	private int maxNeuron;

	public NeuronNetwork(int neurons) {
		neuronsArray = new Neuron[neurons];
		initNeuron(neurons);
		loadMemory(neurons);
	}

	private void initNeuron(int neurons) {
		for (int i = 0; i < neurons; i++) {
			Neuron neuron = new NeuronImpl();
			neuron.setName(String.valueOf('A' + i));
			neuronsArray[i] = neuron;
		}
	}

	private void loadMemory(int neurons) {
		for (int i = 0; i < neurons; i++) {
			Neuron neuron = neuronsArray[i];
			File file = new File(MEMORY_PATH.concat(neuron.getName()).concat(FILE_EXTENSION));
			Pixel[] pixels = loadImage(neuron, file);
			if (pixels != null) {
				neuronsArray[i].setMemory(pixels);
			}
		}
	}

	private Pixel[] loadImage(Neuron neuron, File file) {
		if (file.exists()) {

			BufferedImage image = null;
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (image != null) {
				Pixel[] pixels = new Pixel[image.getWidth() * image.getWidth()];

				for (int width = 0; width < image.getWidth(); width++) {
					for (int height = 0; height < image.getHeight(); height++) {

						int rgb = image.getRGB(width, height);
						int blue = rgb & 0xFF;
						int green = (rgb >> 8) & 0xFF;
						int red = (rgb >> 16) & 0xFF;

						pixels[width * height] = new Pixel(width, height, Math.round((blue + green + red) / 3));
					}
				}
				return pixels;
			}
		} else {
			BufferedImage bi = new BufferedImage(30, 30, BufferedImage.TYPE_BYTE_GRAY);
			Graphics gc = bi.getGraphics();
			gc.setColor(Color.white);
			gc.fillRect(0, 0, 30, 30);

			try {
				ImageIO.write(bi, "BMP", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void savePixelInMemory(Neuron neuron, int x, int y, int rgb) {
		File file = new File(MEMORY_PATH.concat(neuron.getName()).concat(FILE_EXTENSION));
		if (file.exists()) {

			try {
				BufferedImage image = ImageIO.read(file);
				image.setRGB(x, y, rgb);

				ImageIO.write(image, "BMP", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void inputImage(File file) {
		if (file.exists()) {
			for (int i = 0; i < neuronsArray.length; i++) {
				Pixel[] pixels = loadImage(neuronsArray[i], file);
				neuronsArray[i].setInput(pixels);
			}
		}
	}

	public void recognize() {
		for (int i = 0; i < neuronsArray.length; i++) {
			Neuron neuron = neuronsArray[i];
			
			Pixel[] inputs =neuron.getInput();
			Pixel[] memorys = neuron.getMemory();

			for (int width = 0; width < 29; width++) {
				for (int height = 0; height < 29; height++) {
					
					Pixel inputPixel = inputs[width*height];
					int inputRGB = inputPixel.getRGB();
					
					Pixel memoryPixel = memorys[width*height];
					int memoryRGB = memoryPixel.getRGB();
				
					if(Math.abs(inputRGB - memoryRGB) < 120){
						if(inputRGB < 250){
							neuron.setWeight(neuron.getWeight() + 1);
						}
						
						if(inputRGB != 0){
							if(inputRGB < 250){
								memoryPixel.setRGB(Math.round((memoryRGB+(memoryRGB+inputRGB)/2)/2));
							} else if(memoryRGB != 0){
								if(inputRGB < 250){
									memoryPixel.setRGB(Math.round((memoryRGB+(memoryRGB+inputRGB)/2)/2));
								}
							}
						}
					}
				}
			}

			if(neuron.getWeight() > maxNeuronWeight){
				maxNeuronWeight = neuron.getWeight();
				maxNeuron = i;
			}
		}
	}

	public void out(){
		System.out.println(maxNeuronWeight + " " + (char)Integer.parseInt(neuronsArray[maxNeuron].getName()));
	}
}
