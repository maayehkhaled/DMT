package com.qpros.quanta.markuputils;

public class MarkupHelper {
    
    public static Markup createLabel(String text, QuantaColor color) {
        Label l = new Label();
        l.setText(text);
        l.setColor(color);        
        return l;
    }
    
    public static Markup createCodeBlock(String code) {
        CodeBlock cb = new CodeBlock();
        cb.setCodeBlock(code);
        return cb;
    }
    
    public static Markup createCodeBlock(String code, CodeLanguage lang) {
    	CodeBlock cb = new CodeBlock();
        cb.setCodeBlock(code, lang);
        return cb;
    }
    
    public static Markup createTable(String[][] data) {
        Table t = new Table();
        t.setData(data);
        return t;
    }
    
}
