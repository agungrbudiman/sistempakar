public class Driver {
    public static void main(String []args) {
        Engine.generateRules("data.csv");
        Engine.inferenceEngine();
    }
}