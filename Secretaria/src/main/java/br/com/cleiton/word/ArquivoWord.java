package br.com.cleiton.word;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import br.com.cleiton.modelo.Encontro;
import br.com.cleiton.modelo.Equipe;
import br.com.cleiton.modelo.PapelNaEquipe;
import br.com.cleiton.modelo.Participacao;
import br.com.cleiton.modelo.Pessoa;

/**
 * Hello world!
 * 
 */
public class ArquivoWord {
	private XWPFDocument document;
	private String fonte = "Times New Roman";


	public void criarQuadrante(Encontro encontro) throws IOException {
		carregarArquivo("C:/Users/Cleiton/EJC/Quadrante/src/arquivos/QuadranteTemplate.docx");
		criarEncontro(encontro);
		finalizarArquivo();
	}

	private void criarEncontro(Encontro encontro) {
		Collections.sort(encontro.getEquipes());
		Collections.sort(encontro.getPapeisNaEquipe());
		List<PapelNaEquipe> papeisNaEquipe = encontro.getPapeisNaEquipe();
		int quantidadeEquipes=encontro.getEquipes().size();
		for (int i = 0; i < encontro.getEquipes().size(); i++) {
			criarEquipe(encontro.getEquipes().get(i), papeisNaEquipe);
			if(quantidadeEquipes-1!=i)
			inserirQuebraDePagina();
		}
	}

	private void criarEquipe(Equipe equipe, List<PapelNaEquipe> papelNaEquipes) {
		criarNomeEquipe(equipe.getName());
		// Inserir papel na equipe
		for (PapelNaEquipe papelNaEquipe : papelNaEquipes) {
			List<Participacao> partipacao = equipe
					.getPartipacaoPorPapel(papelNaEquipe);
			if (!partipacao.isEmpty()) {
				criarPapelServos(papelNaEquipe.getNome());
				for (Participacao participacao : partipacao) {
					Pessoa pessoa = participacao.getPessoa();
					criarJovem(pessoa);
				}
			}

		}
	}

	/**
	 * Cria um paragrafo com todas as informações de um jovem
	 */
	public void criarJovem(Pessoa primo) {

		XWPFParagraph paragrafo = document.createParagraph();

		paragrafo.setSpacingAfter(0);
		formatarNome(paragrafo, primo.getNome());
		formatarPadraoOutrosDadosServo(paragrafo,
				"Endereço: " + primo.getEndereco());
		formatarPadraoOutrosDadosServo(paragrafo,
				"Bairro: " + primo.getBairro());
		formatarPadraoOutrosDadosServo(paragrafo,
				"Fones: " + primo.getFonesTemplate());
		formatarPadraoOutrosDadosServo(paragrafo, "E-mail: " + primo.getEmail());
		formatarPadraoOutrosDadosServo(paragrafo, primo.getTipoPessoa()
				.getData() + ": " + new SimpleDateFormat("dd/MM/yyyy").format(primo.getDataNascimento()));
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
		XWPFRun formatarTexto = formatarTexto(paragrafo, nome, true, true, 12,
				ParagraphAlignment.CENTER);
		formatarTexto.addBreak();
	}

	private void formatarPadraoOutrosDadosServo(XWPFParagraph paragrafo,
			String text) {
		XWPFRun formatarTexto = formatarTexto(paragrafo, text, false, false,
				12, ParagraphAlignment.CENTER);
		formatarTexto.addBreak();
	}

	private XWPFRun formatarTexto(XWPFParagraph paragrafo, String text,
			boolean isItalic, boolean isBold, int size,
			ParagraphAlignment paragraphAlignment) {
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
