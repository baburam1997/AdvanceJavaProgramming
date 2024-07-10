

import javax.swing.*;
import javax.swing.table.*;

public class MVCExample {
    public static void main(String[] args) {
        String[] columnNames = { "Name", "Age" };
        Object[][] data = {
                { "John", 25 },
                { "Anna", 30 },
                { "Paul", 28 }
        };
        TableModel model = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("MVC Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }
}
