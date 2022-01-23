package injectmocks;

public class Computer {

    Processor processor = new Processor();

    public String getProcessorName(){
        return processor.getName();
    }

}
