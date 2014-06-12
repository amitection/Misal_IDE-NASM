/*
 * Copyright (C) 2014 Amit & Ravi
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package features;

/**
 *
 * @author amit
 */
import java.awt.Color;
import java.util.List;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author amit
 */
public class KeyWordCheck {

    List section;
    JTextPane textArea;

    String red = "section";
    String blue = ".bss|.data|.text";
    

    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

    
    public KeyWordCheck() {

        final StyleContext cont = StyleContext.getDefaultStyleContext();

        final AttributeSet attrRed = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.RED);

        final AttributeSet attrWhite = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);

        final AttributeSet attrBlue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLUE);

        DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if (text.substring(wordL, wordR).matches("(\\W)*"+red)) {
                            setCharacterAttributes(wordL, wordR - wordL, attrRed, false);
                        }
                        else if (text.substring(wordL, wordR).matches("(\\W)*"+blue)) {
                            setCharacterAttributes(wordL, wordR - wordL, attrBlue, false);
                        }
                        else {
                            setCharacterAttributes(wordL, wordR - wordL, attrWhite, false);
                        }
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());

                int before = findLastNonWordChar(text, offs);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offs);

                if (text.substring(before, after).matches(red)) {
                    setCharacterAttributes(before, after - before, attrRed, false);
                }
                 else if (text.substring(before, after).matches(blue)) {
                    setCharacterAttributes(before, after - before, attrBlue, false);
                } else {
                    setCharacterAttributes(before, after - before, attrWhite, false);
                }
            }

        }; 
        textArea = new JTextPane(doc);
        
    }

    public JTextPane addColorProperties() {
        return textArea;
    }
}
