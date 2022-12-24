package lesson8.json;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
enum TokenType {
    CURLY_OPEN,
    CURLY_CLOSE,
    COLON,
    STRING,
    NUMBER,
    ARRAY_OPEN,
    ARRAY_CLOSE,
    COMMA,
    BOOLEAN,
    NULL_TYPE
}
record Token(TokenType type, String value){}
public class JSONTokenizer {
    int prevPos;

    int fileSize;

    ByteBuffer buf;

    JSONTokenizer(String fileName) throws IOException {

        FileChannel fileChannel = FileChannel.open(Path.of(fileName));

        this.fileSize = (int)fileChannel.size();

        this.buf = ByteBuffer.allocate(fileSize);

        fileChannel.read(buf);

        buf.flip();

        fileChannel.close();
    }
    char getWithoutWhiteSpace()
    {
        char c = ' ';
        while ((c == ' ' || c == '\n'|| c == '\r') && buf.hasRemaining())
        {
            c = (char)buf.get();
        }
        return c;
    }
    Token getToken()
    {
        char c;
        if (!buf.hasRemaining())
        {
            System.out.println("No more tokens!");
        }
        prevPos = buf.position();
        c = getWithoutWhiteSpace();

        TokenType currentType = null;

        StringBuilder value = null;

        if (c == '"')
        {
            currentType = TokenType.STRING;
            value = new StringBuilder();
            c = (char)buf.get();
            while ( c != '"')
            {
                value.append(c);
                c = (char)buf.get();
            }
        }
        else if (c == '{')
        {
            currentType = TokenType.CURLY_OPEN;
        }
        else if (c == '}')
        {
            currentType = TokenType.CURLY_CLOSE;
        }
        else if (c=='-' || (c >= '0' && c <= '9'))
        {
            currentType = TokenType.NUMBER;
            value = new StringBuilder();
            while(buf.hasRemaining()&&c!=' '&&c!='\n'&&c!='\r'&&c!=',')
            {
                value.append(c);
                c = (char)buf.get();
            }
            if(c==','){
                buf.position(buf.position()-1);
            }
        }
        else if(c=='f'){
            currentType = TokenType.BOOLEAN;
            value = new StringBuilder("False");
            buf.position(buf.position()+4);
        }
        else if(c=='t'){
            currentType = TokenType.BOOLEAN;
            value = new StringBuilder("True");
            buf.position(buf.position()+3);
        }
        else if(c=='n'){
            currentType = TokenType.NULL_TYPE;
            buf.position(buf.position()+3);
        }
        else if (c == '[')
        {
            currentType = TokenType.ARRAY_OPEN;
        }
        else if (c == ']')
        {
            currentType = TokenType.ARRAY_CLOSE;
        }
        else if (c == ':')
        {
            currentType = TokenType.COLON;
        }
        else if (c == ',')
        {
            currentType = TokenType.COMMA;
        }

        return new Token(currentType, value==null?null:value.toString());
    }

    boolean hasMoreTokens()
    {
        return buf.hasRemaining();
    }
}
