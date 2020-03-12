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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Mathias Hillmann
 */
public class DadosPorSequencial {
    public static String DadosPorSequencial(String sequencial) throws MalformedURLException, IOException {
        String urlString = "https://ws1.soc.com.br/WSSoc/services/ExportaDadosWs?wsdl";
        URL urlForInfWebSvc = new URL(urlString);
        URLConnection UrlConnInfWebSvc = urlForInfWebSvc.openConnection();
        HttpURLConnection httpUrlConnInfWebSvc = (HttpURLConnection) UrlConnInfWebSvc;
        httpUrlConnInfWebSvc.setDoOutput(true);
        httpUrlConnInfWebSvc.setDoInput(true);
        httpUrlConnInfWebSvc.setAllowUserInteraction(true);
        httpUrlConnInfWebSvc.setRequestMethod("GET");
        httpUrlConnInfWebSvc.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
        OutputStreamWriter infWebSvcReqWriter = new OutputStreamWriter(httpUrlConnInfWebSvc.getOutputStream());
        String infWebSvcRequestMessage = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.soc.age.com/\"> <soapenv:Header/> <soapenv:Body> <ser:exportaDadosWs> <!--Optional:--> <arg0> <!--Optional:--> <arquivo>cid:619844239465</arquivo> <!--Optional:--> <campoLivre1>?</campoLivre1> <!--Optional:--> <campoLivre2>?</campoLivre2> <!--Optional:--> <campoLivre3>?</campoLivre3> <!--Optional:--> <campoLivre4>?</campoLivre4> <!--Optional:--> <campoLivre5>?</campoLivre5> <erro>?</erro> <!--Optional:--> <mensagemErro>?</mensagemErro> <!--Optional:--> <parametros>{'empresa':'62168','codigo':'9393','chave':'80e832c7afb77571d678','tipoSaida':'xml','sequencialFicha':'"+sequencial+"'}</parametros> <!--Optional:--> <retorno>xml</retorno> <!--Optional:--> <tipoArquivoRetorno>xml</tipoArquivoRetorno> </arg0> </ser:exportaDadosWs> </soapenv:Body> </soapenv:Envelope>";
        infWebSvcReqWriter.write(infWebSvcRequestMessage);
        infWebSvcReqWriter.flush();
        BufferedReader infWebSvcReplyReader = new BufferedReader(new InputStreamReader(httpUrlConnInfWebSvc.getInputStream()));
        String line;
        String RetornoWS = "";
        while ((line = infWebSvcReplyReader.readLine()) != null) {
            RetornoWS = RetornoWS.concat(line);
            }
        infWebSvcReqWriter.close();
        infWebSvcReplyReader.close();
        httpUrlConnInfWebSvc.disconnect();
        return RetornoWS;
    }
}
