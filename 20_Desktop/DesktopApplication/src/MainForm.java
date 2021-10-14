import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

public class MainForm {
    private JPanel mainPanel;
    private JButton button;
    private JPanel Down;
    private JPanel Center;
    private JPanel Up;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField patronymicTextField;
    private JTextArea output;

    public MainForm() {
        output.setVisible(false);
        button.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                if (button.getText().equals("Collapse")) {
                    String[] values = getFieldsValues();
                    if (values[0].equals("") || values[1].equals("")) {
                        JOptionPane.showMessageDialog(mainPanel, "Вы не ввели имя или фамилию. Введите их и попробуйте снова.");
                    } else {
                        if (values[2].equals("")) {
                            output.setText(values[0] + " " + values[1]);
                        } else {
                            output.setText(values[0] + " " + values[1] + " " + values[2]);
                        }
                        output.setVisible(true);
                        setVisible(false);
                        button.setText("Expand");
                    }
                } else {
                    if (getOutputValuesAndSetTextFields()) {
                        output.setVisible(false);
                        setVisible(true);
                        button.setText("Collapse");
                    }
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    public String[] getFieldsValues() {
        String[] fields = new String[3];
        fields[0] = nameTextField.getText();
        fields[1] = surnameTextField.getText();
        fields[2] = patronymicTextField.getText();
        return fields;
    }

    public void setVisible(Boolean visible) {
        if (visible) {
            nameTextField.setVisible(true);
            surnameTextField.setVisible(true);
            patronymicTextField.setVisible(true);
        } else {
            nameTextField.setVisible(false);
            surnameTextField.setVisible(false);
            patronymicTextField.setVisible(false);
        }
    }


    public boolean getOutputValuesAndSetTextFields() {
        String[] lines = output.getText().split(" +");
        if (lines.length > 1) {
            nameTextField.setText(lines[0]);
            surnameTextField.setText(lines[1]);
            if (lines.length == 3) patronymicTextField.setText(lines[2]);
            return true;
        } else {
            JOptionPane.showMessageDialog(mainPanel, "Вы не ввели имя или фамилию или ввели их не через \"-\". Попробуйте снова");
            return false;
        }
    }

}
