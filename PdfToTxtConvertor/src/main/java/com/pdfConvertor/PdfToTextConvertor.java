/*
 * SriramNarayanan
 */
package com.pdfConvertor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfToTextConvertor {

	private PDDocument getPdfFromSystem(String pdfLocation, String backUpLocation) {

		PDDocument inputPdf = null;
		File input = new File(pdfLocation);
		try {
			inputPdf = PDDocument.load(input);
			inputPdf.save(backUpLocation + input.getName() + "backUp.pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputPdf;
	}

	public static void main(String[] args) {
		PdfToTextConvertor pdfConvertor = new PdfToTextConvertor();
		BufferedWriter bufferedWriter;
		String backUpLocation = "";
		String inputPdfFileLocation = "";
		String outputFileLocation = "";
		Boolean fetchOnlyText = true;
		try {
			PDDocument sourcePdfFile = pdfConvertor.getPdfFromSystem(inputPdfFileLocation, backUpLocation);
			PDFTextStripper txtStripper = new PDFTextStripper();
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileLocation)));
			txtStripper.writeText(sourcePdfFile, bufferedWriter);
			if (fetchOnlyText) {
				String textFromPdf = txtStripper.getText(sourcePdfFile);
				System.out.println(textFromPdf);
			}

			if (sourcePdfFile != null) {
				sourcePdfFile.close();
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
