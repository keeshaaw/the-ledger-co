import service.RequestProcessorService;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length == 0) {
            throw new Exception("The file path is not provided in the arguments");
        }
        String filePath = args[0];
        new RequestProcessorService(filePath).processRequest();
    }
}