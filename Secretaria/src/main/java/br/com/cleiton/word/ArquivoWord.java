package br.com.cleiton.word;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import br.com.cleiton.modelo.Equipe;
import br.com.cleiton.modelo.Partipacao;
import br.com.cleiton.modelo.Pessoa;


/**
 * Hello world!
 * 
 */
public class ArquivoWord {
	private XWPFDocument document;
	private String fonte = "Times New Roman";

	public static void main(String[] args) throws IOException {
		ArquivoWord app = new ArquivoWord();
		app.carregarArquivo("C:/Users/Cleiton/EJC/Quadrante/src/arquivos/QuadranteTemplate.docx");
			for (int i = 0; i < 10; i++) {
				app.criarNomeEquipe("Correios");
				app.criarPapelServos("Jovens Dirigentes");
				List<String> telefones=new ArrayList<String>();
				telefones.add("(89)9462-0776");
				telefones.add("(89)9462-0776");
				app.inserirQuebraDePagina();
				
			}
		app.finalizarArquivo();
	}
	
	private void criarEquipe(Equipe equipe){
		criarNomeEquipe(equipe.getName());
		//Inserir papel na equipe
		List<Partipacao> partipacao = equipe.getPartipacao();
		inserirQuebraDePagina();
	}

	/**
	 * Cria um paragrafo com todas as informações de um jovem
	 */
	public void criarJoven(Pessoa primo) {
	
		XWPFParagraph paragrafo = document.createParagraph();
		
		paragrafo.setSpacingAfter(0);
		formatarNome(paragrafo, primo.getNome());
		formatarPadraoOutrosDadosServo(paragrafo, "Endereço: "+primo.getEndereco());
		formatarPadraoOutrosDadosServo(paragrafo, "Bairro: "+primo.getBairro());
		formatarPadraoOutrosDadosServo(paragrafo, "Fones: "+primo.getFonesTemplate());
		formatarPadraoOutrosDadosServo(paragrafo, "E-mail: "+primo.getEmail());
		formatarPadraoOutrosDadosServo(paragrafo,primo.getTipoPessoa().getData()+ ": "+primo.getDataNascimento());
	}

	public void carregarArquivo(String path) throws IOException {
		document = new XWPFDocument(new FileInputStream(new File(path)));
	
		}


	public void finalizarArquivo() throws FileNotFoundException, IOException {
		File file = new File("C:/Users/Cleiton/Desktop/poi.docx");
		
		document.write(new FileOutputStream(file));
Desktop.getDesktop().open(file);
	}

	public void criarNomeEquipe(String nameEquipe) {
		nameEquipe = nameEquipe.toUpperCase();
		XWPFParagraph novo = document.createParagraph();
		novo.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun createRun = novo.createRun();
		createRun.setFontSize(16);
		createRun.setBold(true);
		createRun.setUnderline(UnderlinePatterns.SINGLE);
		createRun.setFontFamily("Times New Roman");
		createRun.setText(nameEquipe);
	}

	/**
	 * Agrupa os jovens pelos papeis exercidos no encontro
	 * 
	 * @param papel
	 */
	public void criarPapelServos(String papel) {
		XWPFParagraph novo = document.createParagraph();
		novo.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun createRun = novo.createRun();
		createRun.setFontSize(12);
		createRun.setBold(true);
		createRun.setItalic(true);
		createRun.setFontFamily("Times New Roman");
		createRun.setText(papel + ":");
	}

	private void formatarNome(XWPFParagraph paragrafo, String nome) {
		nome = "Nome: " + nome;
		XWPFRun formatarTexto = formatarTexto(paragrafo, nome,true,true,12,ParagraphAlignment.CENTER);
		formatarTexto.addBreak();
	}
	private void formatarPadraoOutrosDadosServo(XWPFParagraph paragrafo,String text) {
		XWPFRun formatarTexto = formatarTexto(paragrafo, text,false,false,12,ParagraphAlignment.CENTER);
		formatarTexto.addBreak();
	}

	private XWPFRun formatarTexto(XWPFParagraph paragrafo, String text,boolean isItalic,boolean isBold,int size,ParagraphAlignment paragraphAlignment) {
		paragrafo.setAlignment(paragraphAlignment);
		XWPFRun createRun = paragrafo.createRun();
		createRun.setFontSize(size);
		createRun.setBold(isBold);
		createRun.setItalic(isItalic);
		createRun.setFontFamily(fonte);
		createRun.setText(text);
		return createRun;

	}

	public void inserirLinhaVazia() {
		XWPFParagraph paragraph = document.createParagraph();
		inserirLinhaVazia(paragraph);
	}

	public void inserirLinhaVazia(XWPFParagraph paragraph) {
		XWPFRun createRun = paragraph.createRun();
		createRun.addBreak();
	}
	
	public void inserirQuebraDePagina() {
		 document.createParagraph().createRun().addBreak(BreakType.PAGE);
	}
	
		public XWPFDocument getDocument() {
		return document;
	}

	public void setDocument(XWPFDocument document) {
		this.document = document;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}
	
}
