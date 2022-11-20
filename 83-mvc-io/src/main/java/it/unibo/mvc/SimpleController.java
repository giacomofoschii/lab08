package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {
    private String nextPrint = new String();
    private final List<String> historyList = new LinkedList<>();

    @Override
    public void setNext(final String next) {
        if(next!=null) {
            this.nextPrint = next;
        } else {
            throw new IllegalArgumentException("Required non-null input");
        }
    }

    @Override
    public String getNext() {
        return this.nextPrint;
    }

    @Override
    public List<String> getHistory() {
        return this.historyList;
    }

    @Override
    public void printCurrent() {
        if(this.nextPrint!=null){
            historyList.add(this.nextPrint);
            System.out.println(this.nextPrint);
        } else {
            throw new IllegalArgumentException("The current string to print is unset");
        }
    }

}
