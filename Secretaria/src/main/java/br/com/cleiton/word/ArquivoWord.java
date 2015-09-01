package br.com.cleiton.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.cleiton.components.Utils;
import br.com.cleiton.modelo.Encontro;
import br.com.cleiton.modelo.Equipe;
import br.com.cleiton.modelo.PapelNaEquipe;
import br.com.cleiton.modelo.Participacao;
import br.com.cleiton.modelo.Pessoa;
import br.com.cleiton.modelo.TipoPessoa;

/**
 * Hello world!
 * 
 */
public class ArquivoWord {
	public static final String CONTENT_TYPE_DOCX=""
			+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	private XWPFDocument document;
	private String fonte = "Times New Roman";
	private String nomeArquivo;
	private final String path = "/arquivos/Modelo 2 Colunas.docx";
	
	public Download criarQuadrante(Encontro encontro) throws IOException, URISyntaxException {
		carregarArquivo(path);
		criarEncontro(encontro);
		nomeArquivo = "Quadrante " + encontro.getTema();
		return finalizarArquivo();
	}
	public Download criarEquipe(Encontro encontro, Equipe equipe) throws IOException, URISyntaxException {
		carregarArquivo(path);
		Collections.sort(encontro.getPapeisNaEquipe());
		criarEquipe(equipe, encontro.getPapeisNaEquipe());
		nomeArquivo = "Quadrante " + encontro.getTema()+ " "+ equipe.getName();
		return finalizarArquivo();
	}

	private void criarEncontro(Encontro encontro) {
		Collections.sort(encontro.getEquipes());
		Collections.sort(encontro.getPapeisNaEquipe());
		List<PapelNaEquipe> papeisNaEquipe = encontro.getPapeisNaEquipe();
		int quantidadeEquipes = encontro.getEquipes().size();
		for (int i = 0; i < encontro.getEquipes().size(); i++) {
			criarEquipe(encontro.getEquipes().get(i), papeisNaEquipe);
			if (quantidadeEquipes - 1 != i)
				inserirQuebraDePagina();
		}
	}
	
	private void criarEquipe(Equipe equipe, List<PapelNaEquipe> papelNaEquipes) {
		String nomeEquipe ="";
		if(equipe.isCirculo()){
			nomeEquipe ="Círculo "+ equipe.getName();
		}else{
			nomeEquipe = equipe.getName();
		}
		criarNomeEquipe(nomeEquipe);
		if(equipe.isPrecisaPapelNaEquipe()){
		// Inserir papel na equipe
			System.out.println(equipe.getName());
		for (PapelNaEquipe papelNaEquipe : papelNaEquipes) {
			List<Participacao> partipacao = equipe
					.getPartipacaoPorPapel(papelNaEquipe);
			if (!partipacao.isEmpty()) {
				criarPapelServos(papelNaEquipe.getNome());
				for (Participacao participacao : partipacao) {
					Pessoa pessoa = participacao.getPessoa();
					criarParticipante(pessoa,equipe);
				}
			}

		}
		}else{
			List<Participacao> partipacao = equipe.getPartipacao();
			for (Participacao participacao : partipacao) {
				Pessoa pessoa = participacao.getPessoa();
				criarParticipante(pessoa,equipe);
			}
		}
	}

	
	public void criarParticipante(Pessoa pessoa,Equipe equipe) {
		if(equipe.getId().equals(21l)){
			pessoa.setTipoPessoa(TipoPessoa.CASAL);
		}
		XWPFParagraph paragrafo = document.createParagraph();

		paragrafo.setSpacingAfter(0);
		if(pessoa.getTipoPessoa().equals(TipoPessoa.CASAL)) {
			formatarNome(paragrafo,	"Tio " + Utils.padronizarNomes(pessoa.getNome() + " e Tia "+ pessoa.getNomeConjugue()));
		} else {
			formatarNome(paragrafo, Utils.padronizarNomes(pessoa.getNome()));
		}
		
		if (pessoa.getCirculo() != null) {
			formatarPadraoOutrosDadosServo(paragrafo, "Círculo: "+ pessoa.getCirculo().getName());
		}
		else{
			if(pessoa.getTipoPessoa().equals(TipoPessoa.JOVEM) && !equipe.isCirculo()){
				formatarPadraoOutrosDadosServo(paragrafo, "Círculo: ");
			}
		}
		String endereco="";
		String bairro ="";

		if(pessoa.getEndereco() != null){
			endereco = pessoa.getEndereco();
		}
		if(pessoa.getBairro() != null){
			bairro =  pessoa.getBairro();
		}

		formatarPadraoOutrosDadosServo(paragrafo,"Endereço: " + endereco);
		formatarPadraoOutrosDadosServo(paragrafo,"Bairro: " + bairro);
		formatarPadraoOutrosDadosServo(paragrafo,"Fones: " + pessoa.getFonesTemplate());
		if (StringUtils.isNotBlank(pessoa.getEmail())) {
			formatarPadraoOutrosDadosServo(paragrafo,"E-mail: " + pessoa.getEmail());
		}
		String dataFormatada = "";
		if (pessoa.getDataNascimento() != null)
			dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(pessoa
					.getDataNascimento());
		formatarPadraoOutrosDadosServo(paragrafo, pessoa.getTipoPessoa()
				.getData() + ": " + dataFormatada);
	}

	public void carregarArquivo(String path) throws IOException, URISyntaxException {
		URL resource = getClass().getResource(path);
		System.out.println(resource);
		document = new XWPFDocument(new FileInputStream(new File(resource.toURI())));

	}

	public Download finalizarArquivo() throws FileNotFoundException, IOException {
		ByteArrayOutputStream bufferedOutputStream = new ByteArrayOutputStream();
		document.write(bufferedOutputStream);
		return  new ByteArrayDownload(bufferedOutputStream.toByteArray(),CONTENT_TYPE_DOCX, this.nomeArquivo + ".docx");
		 
	
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
