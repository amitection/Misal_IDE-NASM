/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package features.search;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author amit
 */
public class WordSearcher {
    
    protected JTextComponent comp;
    protected Highlighter.HighlightPainter painter;

    public WordSearcher(JTextComponent comp) {
        this.comp = comp;
        this.painter = new UnderlineHighlighter.UnderlineHighlightPainter(
                Color.yellow);
    }
    
    public WordSearcher(JTextPane comp, String word)
    {
        this.comp = comp;
        this.painter = new UnderlineHighlighter.UnderlineHighlightPainter(Color.yellow);
        search(word);
    }

    // Search for a word and return the offset of the
    // first occurrence. Highlights are added for all
    // occurrences found.
    public int search(String word) {
        int firstOffset = -1;
        Highlighter highlighter = comp.getHighlighter();
        // Remove any existing highlights for last word
        Highlighter.Highlight[] highlights = highlighter.getHighlights();
       
        for (int i = 0; i < highlights.length; i++) {
            Highlighter.Highlight h = highlights[i];
            if (h.getPainter() instanceof UnderlineHighlighter.UnderlineHighlightPainter) {
                highlighter.removeHighlight(h);
            }
        }
        if (word == null || word.equals("")) {
            return -1;
        }
        // Look for the word we are given - insensitive search
        String content = null;
        try {
            Document d = comp.getDocument();
            content = d.getText(0, d.getLength()).toLowerCase();
        } catch (BadLocationException e) {
            // Cannot happen
            return -1;
        }
        word = word.toLowerCase();
        int lastIndex = 0;
        int wordSize = word.length();
        
        //content.indexOf returns the index or position 'first' occurence(match)
        //word = substring to be searched in content
        //lastIndex = index(position) from which to start the search
        while ((lastIndex = content.indexOf(word, lastIndex)) != -1) {
            int endIndex = lastIndex + wordSize;
            //if match occurs next position to start the search will be
            // = postion till the search already been done + the size of the
            //                                  of the word to be searched
            try {
                highlighter.addHighlight(lastIndex, endIndex, painter);
            } catch (BadLocationException e) {
                // Nothing to do
            }
            if (firstOffset == -1) {
                firstOffset = lastIndex;
            }
            lastIndex = endIndex;
        }
        return firstOffset;
    }
    
}

