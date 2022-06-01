package com.qpros.quanta.exceptions;

@SuppressWarnings("serial")
public class QuantaTestInterruptedException extends RuntimeException {
    public QuantaTestInterruptedException(Throwable t) {
        super(t);
    }

    public QuantaTestInterruptedException(String string) {
        super(string);
    }

    public QuantaTestInterruptedException(String string, Throwable t) {
        super(string, t);
    }   

}
