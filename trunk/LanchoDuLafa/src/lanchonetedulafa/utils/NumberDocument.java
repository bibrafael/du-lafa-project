/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonetedulafa.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumberDocument extends PlainDocument {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NumberDocument() {
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {

        if (str == null) {
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) == false && str.charAt(i) != '.' && str.charAt(i) != ',') {
                return;
            } else {
                super.insertString(offs, str, a);   // ...aceita str
            }
        }
    }
}
