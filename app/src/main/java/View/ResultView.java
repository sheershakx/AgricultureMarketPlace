package View;

import java.util.List;

import model.Result;
/** This interface fetch the Response json from any get post operations and call the override method whereever used **/
public interface ResultView {
    void responseReady (Result result);
}
