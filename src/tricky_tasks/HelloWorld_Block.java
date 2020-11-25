package tricky_tasks;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class HelloWorld_Block {
    public static  void main(String args[])
    {
        System.out.println("Good Bye, console!");
        System.setOut(new PrintStream(new OutputStream() {public void write(int b) throws IOException { }}));
        HelloWorld message = new HelloWorld();
        message.main();
    }
}
