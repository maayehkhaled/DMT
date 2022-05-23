package com.qpros.quanta.markuputils;

class Table implements Markup {

    private static final long serialVersionUID = 7780527162407673294L;
    
    private String[][] data;
    
    public void setData(String[][] data) {
        this.data = data;
    }
    public String[][] getData() { return data; }    
    
    @Override
    public String getMarkup() {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class='runtime-table table-striped table'>");
        for(int row = 0; row < data.length; row++){
            sb.append("<tr>");
            for(int col = 0; col < data[row].length; col++){
                sb.append("<td>" + data[row][col] + "</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

}
