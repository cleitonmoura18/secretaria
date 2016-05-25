package br.com.cleiton.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;

import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.cleiton.modelo.Encontro;
import br.com.cleiton.modelo.Equipe;
import br.com.cleiton.modelo.PapelNaEquipe;
import br.com.cleiton.modelo.Participacao;
import br.com.cleiton.modelo.Pessoa;
import br.com.cleiton.modelo.TipoPessoa;
import br.com.cleiton.repositorio.PessoaRepository;
import br.com.cleiton.repositorio.PessoaRepositoryImpl;

public class MontarQuadrante {
	
	private PessoaRepository pessoaRepository;
	
	
	public MontarQuadrante(PessoaRepository pessoaRepository) {
		super();
		this.pessoaRepository = pessoaRepository;
	}

	public Download criarEncontro(File file, Encontro encontro) throws Exception {
		List<LinhaArquivoXLSX> linhasDoArquivo = lerarquivo(file);
		List<Equipe> equipesDoEncontro = encontro.getEquipes();
		List<PapelNaEquipe> papeisNaEquipe = encontro.getPapeisNaEquipe();
		Map<String, PapelNaEquipe> mapPapeisNaEquipe = MapMe(PapelNaEquipe.class, papeisNaEquipe, "getNameUpercase");
		Map<String, Equipe> mapEquipe = MapMe(Equipe.class, equipesDoEncontro, "getNameUpercase");
		validarNomeDasEquipes(mapEquipe, linhasDoArquivo);
		validarPapeisDasEquipes(mapPapeisNaEquipe, linhasDoArquivo);
		montarEncontro( linhasDoArquivo, mapPapeisNaEquipe, mapEquipe);
		ArquivoWord arquivoWord = new ArquivoWord();
		return arquivoWord.criarQuadrante(encontro);
	}

	private  void montarEncontro( List<LinhaArquivoXLSX> linhasDoArquivo,
			Map<String, PapelNaEquipe> mapPapeisNaEquipe, Map<String, Equipe> mapEquipe) {
		for (LinhaArquivoXLSX linhaArquivoXLSX : linhasDoArquivo) {
			String nomeEquipe = linhaArquivoXLSX.getEquipe().toUpperCase();
			Equipe equipe = mapEquipe.get(nomeEquipe);
			String nomePapelNaEquipe = linhaArquivoXLSX.getPapel().toUpperCase();
			PapelNaEquipe papelNaEquipe = mapPapeisNaEquipe.get(nomePapelNaEquipe);
			
			Participacao participacao = new Participacao();
			participacao.setEquipe(equipe);
			Pessoa pessoa = null;
			if(linhaArquivoXLSX.getIdEncontrista() != null){
				pessoa = pessoaRepository.find(linhaArquivoXLSX.getIdEncontrista());
			}else{
				pessoa = new Pessoa();
				pessoa.setNome(linhaArquivoXLSX.getNome());
				if(linhaArquivoXLSX.getTipo().equalsIgnoreCase("Casal")){
					pessoa.setTipoPessoa(TipoPessoa.CASAL);
					pessoa.setNomeConjugue(linhaArquivoXLSX.getNomeConjugue());
				}else{
					if(linhaArquivoXLSX.getTipo().equalsIgnoreCase("Jovem")){
						pessoa.setTipoPessoa(TipoPessoa.JOVEM);
					}
				}
				
				
			}
			participacao.setPessoa(pessoa);
			participacao.setPapelNaEquipe(papelNaEquipe);
			equipe.getPartipacao().add(participacao);
		}
	}

	private List<LinhaArquivoXLSX> lerarquivo(File file) throws FileNotFoundException, IOException {
		FileInputStream arquivo = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(arquivo);

		XSSFSheet primeiraPlanilha = workbook.getSheetAt(0);
		Iterator<Row> linhas = primeiraPlanilha.iterator();
		linhas.next();
		HashSet<String> papeis = new HashSet<String>();
		
		List<LinhaArquivoXLSX> linhasDoArquivo = new ArrayList<LinhaArquivoXLSX>();
		while (linhas.hasNext()) {
			Row row = linhas.next();
			Cell cellParticipacao = row.getCell(5);
			if (cellParticipacao != null) {
				Cell cellIdEncontrista = row.getCell(0);
				Cell cellPapel = row.getCell(6);
				Cell cellNome = row.getCell(1);
				Cell cellNomeConjuque = row.getCell(2);
				Cell cellTipo = row.getCell(3);
				String equipe = cellParticipacao.getStringCellValue();
				Long idEncontrista = null;
				if (cellIdEncontrista != null) {
					Double numericCellValue = cellIdEncontrista.getNumericCellValue();
					idEncontrista = numericCellValue.longValue();
				}
				String papel = cellPapel.getStringCellValue();
				papeis.add(papel.toUpperCase());
				
				LinhaArquivoXLSX linhaDoArquivo = new LinhaArquivoXLSX();
				linhaDoArquivo.setEquipe(equipe);
				linhaDoArquivo.setPapel(papel);
				linhaDoArquivo.setIdEncontrista(idEncontrista);
				linhasDoArquivo.add(linhaDoArquivo);
				linhaDoArquivo.setNome(cellNome.getStringCellValue());
				if(cellNomeConjuque!=null)
					linhaDoArquivo.setNomeConjugue(cellNomeConjuque.getStringCellValue());
				linhaDoArquivo.setTipo(cellTipo.getStringCellValue());
			}

		}
		arquivo.close();
		return linhasDoArquivo;
	}

	private void validarPapeisDasEquipes(Map<String, PapelNaEquipe> mapMe, List<LinhaArquivoXLSX> linhasDoArquivo) throws Exception {
		HashSet<String> equipes = new HashSet<String>();
		for (LinhaArquivoXLSX linhaArquivoXLSX : linhasDoArquivo) {
			equipes.add(linhaArquivoXLSX.getPapel().toUpperCase());
		}
		HashSet<String> nomesInvalidos = new HashSet<String>();
		
		for (String string : equipes) {
			if (!mapMe.containsKey(string)) {
				nomesInvalidos.add(string);
			}
		}
		if (!nomesInvalidos.isEmpty()) {
			String message = "O nome dos papeis das equipes do arquivo estão diferentes.\n" + "Nomes de papeis Invalidos: "
					+ nomesInvalidos + ".\n" + "Substituir por estes:" + mapMe.keySet();
			throw new Exception(message);

		}
		
	}

	private  void validarNomeDasEquipes(Map<String, Equipe> mapEquipe, List<LinhaArquivoXLSX> linhasDoArquivo)
			throws Exception {
		HashSet<String> equipes = new HashSet<String>();
		for (LinhaArquivoXLSX linhaArquivoXLSX : linhasDoArquivo) {
			equipes.add(linhaArquivoXLSX.getEquipe().toUpperCase());
		}
		HashSet<String> nomesInvalidos = new HashSet<String>();
		
		for (String string : equipes) {
			if (!mapEquipe.containsKey(string)) {
				nomesInvalidos.add(string);
			}
		}
		if (!nomesInvalidos.isEmpty()) {
			String message = "O nome das equipes do arquivo estão diferentes.\n" + "Nomes de Equipes Invalidos: "
					+ nomesInvalidos + ".\n" + "Substituir por estes:" + mapEquipe.keySet();
			throw new Exception(message);

		}
	}


	public static void main(String[] args) throws Exception {
//		Session session = CriadorDeSession.getSession();
//		Encontro encontro = (Encontro) session.get(Encontro.class, 3l);
//		File file = new File("C:\\Users\\clebert\\Desktop\\EJC\\Participantes EJC.xlsx");
//		PessoaRepositoryImpl  pessoaRepositoryImpl = new PessoaRepositoryImpl(session);
//		MontarQuadrante montarQuadrante = new MontarQuadrante(pessoaRepositoryImpl);
//		montarQuadrante.criarEncontro(file, encontro);
	}

	public static <T> Map<String, T> MapMe(Class<T> clz, Collection<T> list, String methodName) throws Exception {
		Map<String, T> map = new HashMap<String, T>();
		Method method = clz.getMethod(methodName);
		for (T el : list) {
			map.put((String) method.invoke(el), el);
		}
		return map;
	}
	
	
}
