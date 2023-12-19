package hello.springtx.order;

public class NotEnoughMoneyException extends Exception {
    //Exception 상속 받았으므로 체크예외가 된다.

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
