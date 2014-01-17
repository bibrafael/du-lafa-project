package lanchonetedulafa.utils;

import javax.swing.text.*;

public class FixedLengthDocument extends PlainDocument {

    private int iMaxLength;

    public FixedLengthDocument(int maxlen) {
        super();
        iMaxLength = maxlen;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str == null) {
            return;
        }

        if (iMaxLength <= 0) {
            super.insertString(offset, str, attr);
            return;
        }

        int ilen = (getLength() + str.length());
        if (ilen <= iMaxLength) // se o comprimento final for menor...
        {
            super.insertString(offset, str, attr);   // ...aceita str
        }
    }
}
