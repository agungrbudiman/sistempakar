public class Driver {
    public static void main(String []args) {
        Engine e = new Engine();
        e.generateRules("data.csv");
        e.inferenceEngine();
    }
}