package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    public void setNext(String next);

    public String getNext();

    public List<String> getHistory();

    public void printCurrent();
}
