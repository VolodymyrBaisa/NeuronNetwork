package model;

public class NeuronImpl implements Neuron {
	private String name;
	private int weight;
	private Pixel[] memory;
	private Pixel[] input;
	private int output;
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public int getWeight() {
		return weight;
		
	}
	
	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public Pixel[] getMemory() {
		return memory;
	}
	
	@Override
	public void setMemory(Pixel[] memory) {
		this.memory = memory;
	}

	@Override
	public Pixel[] getInput() {
		return input;
	}
	
	@Override
	public void setInput(Pixel[] input) {
		this.input = input;
	}

	@Override
	public int getOutput() {
		return output;
	}
	
	@Override
	public void setOutput(int output) {
		this.output = output;		
	}
}
