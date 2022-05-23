package com.qpros.quanta.markuputils;

class Label implements Markup {

    private static final long serialVersionUID = 548763908072445261L;
    
    private String text = "";
    private QuantaColor color = QuantaColor.TRANSPARENT;
    
    public void setText(String text) {
        this.text = text;
    }
    public String getText() { return text; }
    
    public void setColor(QuantaColor color) { 
        this.color = color; 
    }
    public QuantaColor getColor() { return color; }

    public String getMarkup() {
        String textColor = color != QuantaColor.WHITE ? "white-text" : "black-text";
        String lhs = "<span class='badge " + textColor + " " + String.valueOf(color).toLowerCase() + "'>";
        String rhs = "</span>";
        
        return lhs + text + rhs;
    }
}
