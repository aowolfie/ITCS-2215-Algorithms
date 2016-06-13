/**
 * Created by brandonbeckwith on 6/3/16.
 *
 * First it encodes a file, then it decodes the file
 */
public class Main {

    private static final String FILE_NAME = "input"; //This is just some randomly generated text.

    public static void main(String[] args){

        //This encodes the file, and prints the number of bytes saved
        HEncode.handleFile(FILE_NAME + ".txt");

        //This unencodes the encoded file
        HEncode.handleFile(FILE_NAME + ".hec");
    }
}
