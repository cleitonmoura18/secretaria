package br.com.cleiton.components;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.swing.text.MaskFormatter;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;

public class Utils {
	public static String DELIMITADOR = ";";

	public static String format(String pattern, Object value) {
		MaskFormatter mask;
		try {
			mask = new MaskFormatter(pattern);
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(value);
		} catch (ParseException e) {
			System.out.println(pattern + " - " + value);
			throw new RuntimeException(e);
		}
	}

	public static Download createXLSX(List<String> arquivo, String nomearquivo)
			throws IOException {
		String contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("servos");

		int rowNum = 0;
		for (String objects : arquivo) {
			String[] split = objects.split(DELIMITADOR);
			Row createRow = sheet.createRow(rowNum);
			for (int i = 0; i < split.length; i++) {
				String string = split[i];
				createRow.createCell(i).setCellValue(string);
			}
			rowNum++;

		}

		ByteArrayOutputStream bufferedOutputStream = new ByteArrayOutputStream();
		wb.write(bufferedOutputStream);
		return new ByteArrayDownload(bufferedOutputStream.toByteArray(),
				contentType, nomearquivo + ".xlsx");
	}

	public static void main(String[] args) throws IOException {
		System.out.println(format("####-####",
				"9866 0806".replaceAll("[^\\d.]", "")));
	}

	public static String padronizarNomes(String string) {
		if (string == null)
			return null;
		String[] wordArray = string.toLowerCase().replaceAll("\\s+", " ")
				.split(" "); // Split string to analyze word by
								// word.
		int i = 0;
		lowercase: for (String word : wordArray) {
			if (word != wordArray[0]) { // First word always in capital
				String[] lowercaseWords = { "da", "de", "do", "dos","e" };
				for (String word2 : lowercaseWords) {
					if (word.equals(word2)) {
						wordArray[i] = word;
						i++;
						continue lowercase;
					}
				}
			}
			char[] characterArray = word.toCharArray();
			characterArray[0] = Character.toTitleCase(characterArray[0]);
			wordArray[i] = new String(characterArray);
			i++;
		}
		return StringUtils.join(wordArray, " "); 
	}
}
