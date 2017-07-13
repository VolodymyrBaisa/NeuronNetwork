package model;

public interface Neuron {
	String getName();
	void setName(String name);
	
	int getWeight();
	void setWeight(int weight);
	
	Pixel[] getMemory();
	void setMemory(Pixel[] memory);
	
	Pixel[] getInput();
	void setInput(Pixel[] input);
	
	int getOutput();
	void setOutput(int output);
}
