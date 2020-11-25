package bsu.rfe.java.group6.lab3.Mashkantsev.B10;

import javax.swing.table.AbstractTableModel;
import java.lang.String;

public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from= from;
        this.to= to;
        this.step= step;
        this.coefficients= coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }
    @Override
    public Object getValueAt(int row, int col) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from+ step*row;
        Double result = 0.0;
        boolean z = false;
        for (int i = 0; i < coefficients.length; i++) {
            result += Math.pow(x, coefficients.length - 1 - i) * coefficients[i];
        }
        if(col==0) {
            return x;
        }
        else if (col == 1){
            return result;

        }
        else {
            if (result%1<0.000000001) {
                if (result%10<0.00000001) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                if (Math.abs(result%10-result%10%1-result*10%10+result*10%10%1)<0.000000001) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }

    }
    @Override
    public String getColumnName(int col) {
        switch(col) {
            case 0:return"Значение X";
            case 1 :return"Значение многочлена";
            default:return "Ограниченная симметрия";

        }

    }
    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 0 || col == 1){
            return Double.class;
        }
        else return Boolean.class;

    }
}