import Parser.Parser;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

  public static void main(String[] args)
    throws NumberFormatException, Exception {
    String[] fileContent = Files.readString(Path.of(args[0])).split(" ");
    fileContent = Parser.Num_To_Dig(fileContent);
    String outputString = new String(
      Parser.To_Byte_Arr(fileContent, Integer.parseInt(args[1])),
      Parser.CharSet(args[2])
    );
    System.out.print(outputString);
  }
}
