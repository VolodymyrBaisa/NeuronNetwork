import java.io.File;

import brain.NeuronNetwork;

public class Interface {

	public static void main(String[] args) {
		new Interface().init();

	}

	private void init() {
		NeuronNetwork neuronNetwork = new NeuronNetwork(30);
		neuronNetwork.inputImage(new File("C:/Users/Bios/workspace/NeuronNetwork/123.bmp"));
		neuronNetwork.recognize();
		neuronNetwork.out();
	}

}
