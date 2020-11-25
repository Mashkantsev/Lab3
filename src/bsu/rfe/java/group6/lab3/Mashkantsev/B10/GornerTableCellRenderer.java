package bsu.rfe.java.group6.lab3.Mashkantsev.B10;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JPanel;
import javax.swing.JLabel;




public class GornerTableCellRenderer implements TableCellRenderer {
    private JPanel panel= new JPanel();
    private JLabel label= new JLabel();
    // Ищем ячейки, строковое представление которых равно needle(иголке)
    // Применяется аналогия поиска иголки в сене, в роли сена -таблица
    private String needle= "0";
    private DecimalFormat formatter= (DecimalFormat)NumberFormat.getInstance();

    public GornerTableCellRenderer() {
        // Показывать только 5 знаков после запятой
        formatter.setMaximumFractionDigits(5);
        // Не использовать группировку (т.е. не отделять тысячи //
        // ни запятыми, ни пробелами), т.е. показывать число как "1000", //
        // а не"1 000" или"1,000"
        formatter.setGroupingUsed(false);
        // Установить в качестве разделителя дробной части точку, а не // запятую.
        // По умолчанию, в региональных настройках
        // Россия/Беларусь дробная часть отделяется запято
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        // Разместить надпись внутри панели
        panel.add(label);
        // Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        // Преобразовать числов строку с помощью форматировщика
        String formattedDouble = formatter.format(value);
        // Установить текст надписи равным строковому представлению числа
        label.setText(formattedDouble);
        if(col==1 && needle.equals(formattedDouble)) {
            // Номер столбца = 1 (т.е. второй столбец)//
            // + иголка не null(т.е. мы что-то ищем)//
            // + значение иголки совпадает со значением ячейки таблицы -//
            // окрасить задний фон панели в красный цвет
            panel.setBackground(Color.YELLOW);
        }
        else if (col==1 || col==0){
            String str = formattedDouble;
            Double temp = Double.parseDouble (str);
            System.out.println(temp);
            int i  = str.length()-1;
            int Sum=0;
            for (;str.charAt(i) != '.' && str.charAt(i)!=',' && temp%1 > 0.0000000001;i--)
            {
                Sum += str.charAt(i)-48;
            }
            if(Sum % 10 ==0){
                panel.setBackground(Color.GREEN);
            }
            else panel.setBackground(Color.WHITE);
        }
        else{
            panel.setBackground(Color.WHITE);
        }
        return  panel;
    }
    public void setNeedle(String needle) {

        this.needle= needle;
    }
}

