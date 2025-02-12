/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hust.soict.dsai.blockchain.graph.chart.barchart;

import hust.soict.dsai.blockchain.graph.chart.ColumnItem;
import hust.soict.dsai.blockchain.graph.chart.ModelChart;
import hust.soict.dsai.blockchain.graph.chart.ModelLegend;
import hust.soict.dsai.blockchain.graph.chart.blankchart.BlankPlotChart;
import hust.soict.dsai.blockchain.graph.chart.blankchart.BlankPlotChatRender;
import hust.soict.dsai.blockchain.graph.chart.blankchart.SeriesSize;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author 84913
 */
public class BarChart extends javax.swing.JPanel {

    /**
     * Creates new form BarChart
     */
    private ArrayList<ModelLegend> columnList= new ArrayList<ModelLegend>();
    private ArrayList<ModelChart> chartModel= new ArrayList<ModelChart>();
    private final int seriesSize = 12;
    private final int seriesSpace = 12;
    public BarChart() {
        initComponents();
        blankChart.setBlankPlotChatRender(new BlankPlotChatRender(){
            @Override
            public String getLabelText(int index){
                return chartModel.get(index).getLabel();
            }
            @Override
            public void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index){
                double totalSeriesWidth = (seriesSize*columnList.size()) + (seriesSpace*(columnList.size()-1)); 
                double x = (size.getWidth() - totalSeriesWidth)/2;
                for( int i = 0; i < columnList.size(); i++){
                    ModelLegend legend = columnList.get(i);
                    g2.setColor(legend.getColor1());
                    double seriesValues = chart.getSeriesValuesOf(chartModel.get(index).getValues()[i], size.getHeight());
                    g2.fillRect((int) (size.getX() + x), (int) (size.getY() + size.getHeight() - seriesValues), seriesSize, (int) seriesValues);
                    x += seriesSpace + seriesSize;
                }
            }
            

            
        });
    }
    public void addModelLegend(String name, Color color){
        ModelLegend column = new ModelLegend(name, color);
        columnList.add(column);
        BottomBar.add(new ColumnItem(column));
        BottomBar.repaint();
        BottomBar.revalidate();
        
    }
    public void addData(ModelChart data){
        chartModel.add(data);
        blankChart.setLabelCount(chartModel.size());
        double max = data.getMaxValues();
        if( max > blankChart.getMaxValues()){
            blankChart.setMaxValues(max);
        }
    }
    public void clearData(){
        chartModel = new ArrayList<ModelChart>();
        
        
    }
    //public static void main(String[] args){}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        blankChart = new hust.soict.dsai.blockchain.graph.chart.blankchart.BlankPlotChart();
        BottomBar = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        blankChart.setForeground(new java.awt.Color(153, 153, 153));

        BottomBar.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(blankChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BottomBar, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(blankChart, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(BottomBar, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BottomBar;
    private hust.soict.dsai.blockchain.graph.chart.blankchart.BlankPlotChart blankChart;
    // End of variables declaration//GEN-END:variables

}
