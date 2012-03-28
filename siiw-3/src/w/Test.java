package w;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	static int numIterations = 40; // how many times repeat tests (all of them)
	static int numInputLines = 100; // numTests in input file
	static final int numInputFeatures = 25;
	static int numHiddenLayer = 30; // 25+36 = 61, hidden: {30, 31}
	static final int numOutputLayer = 36; // allnum
	static double learnRate = 0.2;
	static double mommentum = 0.8;
	static double errorThreshold = 0.90;

	DataInputStream in;
	BufferedReader br;
	FileInputStream fstream = null;
	Network network;
	double globalLearnInput[][];
	double globalIdeal[][];

	public static void main(String[] args) {
		if (args[0] != null)
			numInputLines = Integer.parseInt(args[0]);
		if (args[1] != null)
			numHiddenLayer = Integer.parseInt(args[1]);
		if (args[2] != null)
			errorThreshold = Double.parseDouble(args[2]);
		if (args[3] != null)
			numIterations = Integer.parseInt(args[3]);
		if (args[4] != null)
			learnRate = Double.parseDouble(args[4]);
		if (args[5] != null)
			mommentum = Double.parseDouble(args[5]);

		Test ln = new Test();

		ln.readFile("/home/w/Dropbox/studia/siiw/z3/std-cechy.txt");

		// for(int a = 0; a < ln.globalIdeal.length; ++a){
		// for(int b = 0; b < ln.globalIdeal[a].length; ++b){
		// System.out.print(ln.globalIdeal[a][b] + " ");
		// }
		// System.out.println();
		// }

		for (int iter = 0; iter < numIterations; ++iter) {
			for (int i = 0; i < numInputLines - 1; ++i) {
				ln.learn(i);
			}
			//System.out.print("iter: " + iter + ", ");
			// ln.printError();
			// if (ln.getError() < errorThreshold) {
			// break;
			// }
			if (iter > numIterations - 2) {
				ln.printError();
			}
			ln.getError();
		}

		ln.test("/home/w/Dropbox/studia/siiw/z3/ext-cechy.txt");
	}

	public Test() {
		network = new Network(numInputFeatures, numHiddenLayer, numOutputLayer,
				learnRate, mommentum);
		globalLearnInput = new double[numInputLines][numInputFeatures];
		globalIdeal = new double[numInputLines][numOutputLayer];
	}

	private void printError() {
		System.out.println(network.getGlobalErrorNoReset(numInputLines
				* numOutputLayer));
	}

	private double getError() {
		return network.getGlobalError(numInputLines * numOutputLayer);
	}

	private void readFile(String file) {

		try {
			fstream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		in = new DataInputStream(fstream);
		br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		int trail = 0;

		double learnInput[];
		double idealOutput[];
		String[] tokens;
		try {
			strLine = br.readLine();
			while ((strLine = br.readLine()) != null
					&& trail < numInputLines - 1) {
				tokens = strLine.split(" ");
				// System.err.println(tokens.length);
				learnInput = new double[numInputFeatures];
				idealOutput = new double[numOutputLayer];

				for (int i = 0; i < tokens.length; ++i) {
					if (i < numInputFeatures) {// output layer
						learnInput[i] = Double.parseDouble(tokens[i]);
					} else {// ideal
						idealOutput[i - numInputFeatures] = Double
								.parseDouble(tokens[i]);
					}
				}
				++trail;
				globalLearnInput[trail] = learnInput;
				globalIdeal[trail] = idealOutput;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void test(String file) {
		double testInput[][] = new double[numInputLines][numInputFeatures];
		double testIdeal[][] = new double[numInputLines][numOutputLayer];
		try {
			fstream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		in = new DataInputStream(fstream);
		br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		int trail = 0;

		double learnInput[];
		double idealOutput[];
		String[] tokens;
		try {
			while ((strLine = br.readLine()) != null
					&& trail < numInputLines - 1) {
				tokens = strLine.split(" ");

				learnInput = new double[numInputFeatures];
				idealOutput = new double[numOutputLayer];

				for (int i = 0; i < tokens.length; ++i) {
					if (i < numInputFeatures) {// output layer
						learnInput[i] = Double.parseDouble(tokens[i]);
					} else {// ideal
						idealOutput[i - numInputFeatures] = Double
								.parseDouble(tokens[i]);
					}
				}
				++trail;
				testInput[trail] = learnInput;
				testIdeal[trail] = idealOutput;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// int results[] = new int[numOutputLayer];
		int resultOK = 0;

		for (int i = 0; i < testInput.length; i++) {
			StringBuilder key = new StringBuilder();
			StringBuilder val = new StringBuilder();

			double out[] = network.getOutputs(testInput[i]);
			double max_val = out[0];
			int max_j = 0;
			for (int j = 0; j < out.length; ++j) {
				// select option with maximum output
				if (out[j] > max_val) {
					max_val = out[j];
					max_j = j;
				}
			}

			double out2[] = new double[out.length];
			out2[max_j] = 1.0;

			for (int j = 0; j < testIdeal[i].length; ++j) {
				key.append(testIdeal[i][j]);
			}
			for (int j = 0; j < out2.length; ++j) {
				val.append(out2[j]);
			}

			String k = key.toString();
			String v = val.toString();
			// System.out.println(k + ", " + v);
			if (k.equals(v)) {
				++resultOK;
			}
		}

		System.out.println(resultOK + "/" + testInput.length);

	}

	private void learn(int indexInInputFile) {
		// System.err.println("learning");
		network.getOutputs(globalLearnInput[indexInInputFile]);
		network.learn(globalIdeal[indexInInputFile]);
		// for (int i = 0; i < out.length; ++i) {
		// System.out.print(globalIdeal[indexInInputFile][i] + " " + out[i]
		// + ",");
		// }
		// System.out.println();

	}
}
