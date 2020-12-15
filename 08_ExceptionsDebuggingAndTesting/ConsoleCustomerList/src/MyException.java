public class MyException extends Exception{

    private int length;

    public int getLength()
    {
        return length;
    }

    public MyException (String message, int number)
    {
        super(message);
        length = number;
    }
}
