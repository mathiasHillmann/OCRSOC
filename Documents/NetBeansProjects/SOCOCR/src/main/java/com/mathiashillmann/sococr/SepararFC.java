/*
*   MIT License
*
*   Copyright (c) 2020 Mathias Hillmann
*
*   Permission is hereby granted, free of charge, to any person obtaining a copy
*   of this software and associated documentation files (the "Software"), to deal
*   in the Software without restriction, including without limitation the rights
*   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*   copies of the Software, and to permit persons to whom the Software is
*   furnished to do so, subject to the following conditions:
*
*   The above copyright notice and this permission notice shall be included in all
*   copies or substantial portions of the Software.
*
*   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*   SOFTWARE.
*/
package com.mathiashillmann.sococr;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author Mathias Hillmann
 */
public class SepararFC {
    public static boolean SepararFC(String sequencial, String caminho, String destino) {
        //Separar ficha clinica do pdf inicial
        try (PDDocument document = PDDocument.load(new File(caminho))) {
            int noOfPages= document.getNumberOfPages();
            document.removePage(0);
            document.save(new File(destino, sequencial + "_fc.pdf"));
            document.close();
        } 
        catch (IOException e){
            System.err.println(e);
        }
        return true;
    }
}
