package br.com.cleiton.word;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class MontarRelogio {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		XWPFDocument document = new XWPFDocument();
		XWPFParagraph novo = document.createParagraph();
		novo.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun createRun = novo.createRun();
		createRun.setFontSize(16);
		createRun.setBold(true);
		createRun.setUnderline(UnderlinePatterns.SINGLE);
		createRun.setFontFamily("Times New Roman");
		createRun.setText("Cleiton dos Santos");
		File file = new File("C:/Users/clebert/Documents/Cleiton/eclipse/poi.docx");
		document.write(new FileOutputStream(file));
		Desktop.getDesktop().open(file);
	}
}
