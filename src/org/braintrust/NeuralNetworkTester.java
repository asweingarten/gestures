package org.braintrust;

import java.util.Arrays;

public class NeuralNetworkTester {
  
  public static final int TRAINING_SAMPLES = 15000;
  public static final int[] TOPOLOGY = new int[]{GestureStore.INPUT_NEURONS, 150, 150, 50, 50, GestureStore.OUTPUT_NEURONS};
  public static final double LEARNING_RATE = 0.7;

  public static void main(String[] args) {

    NeuralNetwork n = new NeuralNetwork(LEARNING_RATE, TOPOLOGY);

    System.out.println("LEARNING RATE = " + LEARNING_RATE);
    System.out.println("TOPOLOGY = " + Arrays.toString(TOPOLOGY));
    System.out.println("TRAINING SAMPLES = " + TRAINING_SAMPLES);
    System.out.println("\n\n");
    
    for (int i = 0; i < TRAINING_SAMPLES; i++) {
      Gesture gesture = GestureStore.instance.getRandomTrainingGesture();
      n.train(gesture.input, gesture.targetOutput);
    }
    
    int classifyTotal = 0;
    int classifyError = 0;
    int classifyTime = 0;
    
    for (Gesture gesture : GestureStore.instance.getAllTestingGestures()) {
      long timeStart = System.nanoTime();
      double[] outputActual = n.classify(gesture.input);
      long timeEnd = System.nanoTime();
      classifyTime += timeEnd - timeStart;
      
      GestureType typeActual = GestureType.fromOutput(outputActual);
      GestureType typeTarget = gesture.type;
      
      classifyTotal++;
      if (typeActual == typeTarget) {
        System.out.println("CORRECTLY CLASSIFIED " + typeActual);
      } else {
        System.out.println("MISTOOK " + typeTarget + " FOR A " + typeActual);
        classifyError++;
      }
    }
    
    System.out.println("\n");
    System.out.println("ERROR RATE " + classifyError / (double) classifyTotal);
    System.out.println("AVERAGE CLASSIFY TIME (NS) " + classifyTime / (double) classifyTotal);
    System.out.println("\n");
  }
}