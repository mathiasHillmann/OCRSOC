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
/**
 *
 * @author Mathias Hillmann
 */
import java.io.BufferedReader;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.Tesseract;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import net.sourceforge.tess4j.util.LoadLibs;

public class RealizarOCR {
    public static String RealizarOCR(String caminho) throws TesseractException, IOException {
        String sequencial = "";
        //OCR para pegar sequencial da ficha
        final File image = new File(caminho);
        final Tesseract tessInst = new Tesseract();
        tessInst.setTessVariable("user_defined_dpi", "270");
        tessInst.setLanguage("eng");
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        tessInst.setDatapath(tessDataFolder.getAbsolutePath());
            String result = tessInst.doOCR(image);
            BufferedReader br = new BufferedReader(new StringReader(result));
            String namerequired;
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                if (counter == 6) {
                    namerequired = line;
                    sequencial = namerequired.substring(namerequired.length()-9); 
                } 
                counter++;
            }       
        return sequencial;
    }
}
