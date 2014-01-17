/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lanchonetedulafa.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumberFixedLengthDocument extends PlainDocument {

    /**
     *
     */
    private int iMaxLength;
    private static final long serialVersionUID = 1L;

    public NumberFixedLengthDocument(int maxLength) {
        iMaxLength = maxLength;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {

        if (str == null) {
            return;
        }
        if (iMaxLength <= 0) {
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i)) == false) {
                    return;
                }
            }
        }
        int ilen = (getLength() + str.length());
        if (ilen <= iMaxLength) // se o comprimento final for menor...
        {
            for (int i = 0; i < str.length(); i++) {
                if (Character.isDigit(str.charAt(i)) == false) {
                    return;
                } else {
                    super.insertString(offs, str, a);   // ...aceita str
                }
            }
        }
    }
}
