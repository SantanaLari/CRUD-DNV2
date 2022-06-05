package application.controller;

import java.sql.SQLException;
import java.util.List;

import application.model.Compra;
import application.persistence.CompraDao;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CompraController implements ICompraController {

	private TextField txtCodigoCompra;
	private TextField txtNomeComprador;
	private TextField txtEmailCompra;
	private TextField txtCodigoEvento_compra;
	private TextField txtCodigoIngresso_compra;
	private TextField txtCodigoCaravana_compra;
	private TextArea taCompra;
	
	public CompraController(TextField txtCodigoCompra, TextField txtNomeComprador, TextField txtEmailCompra,
			TextField txtCodigoEvento_compra, TextField txtCodigoIngresso_compra, TextField txtCodigoCaravana_compra,
			TextArea taCompra) {
		this.txtCodigoCompra = txtCodigoCompra;
		this.txtNomeComprador = txtNomeComprador;
		this.txtEmailCompra = txtEmailCompra;
		this.txtCodigoEvento_compra = txtCodigoEvento_compra;
		this.txtCodigoIngresso_compra = txtCodigoIngresso_compra;
		this.txtCodigoCaravana_compra = txtCodigoCaravana_compra;
		this.taCompra = taCompra;
	}

	@Override
	public void inserirCompra(Compra cp) throws ClassNotFoundException, SQLException {
		CompraDao cpDao = new CompraDao();
		cpDao.insereCompra(cp);
		limpaCamposCompra();
	//	buscarCompras();
	}

	@Override
	public void atualizarCompra(Compra cp) throws ClassNotFoundException, SQLException {
		CompraDao cpDao = new CompraDao();
		cpDao.atualizaCompra(cp);
		limpaCamposCompra();
		buscarCompras();
	}

	@Override
	public void excluirCompra(Compra cp) throws ClassNotFoundException, SQLException {
		CompraDao cpDao = new CompraDao();
		cpDao.excluiCompra(cp);
		limpaCamposCompra();
		buscarCompras();
	}

	@Override
	public void listaFiltrar() throws ClassNotFoundException, SQLException {
		limpaCamposCompra();
		taCompra.setText("");
		
		CompraDao cDao = new CompraDao();
		List<Compra> listaFiltragem = cDao.listagemFiltrada();
		
	/*	StringBuffer buffer = new StringBuffer("Comprador\t\t\tEvento\t\t\tCaravana\t\t\tValor");
		for(Compra cp : listaFiltragem) {
			buffer.append(cp.getNome()+"\t\t\t"+cp.getEvento().getNome()+"\t\t\t"
		   +cp.getCaravana().getNome()+"\t\t\t"+cp.getValor());
		}*/
		
		StringBuffer buffer = new StringBuffer("Comprador\t\t\tCaravana");
		for(Compra cp : listaFiltragem) {
			buffer.append(cp.getNome() +"\t\t\t" + cp.getCaravana().getNome());
		
		taCompra.setText(buffer.toString());
		}
	}

	@Override
	public void buscarCompras() throws ClassNotFoundException, SQLException {
		limpaCamposCompra();
		taCompra.setText("");
		
		CompraDao cDao = new CompraDao();
		List<Compra> buscaCompleta = cDao.buscaCompras();
		
	/*	StringBuffer buffer = new StringBuffer("Evento\t\tDia\tHora\tRua\t\t\tNumero\ttipoIngresso\tPreco\tCaravana\tPreco\tCliente\t\temail");
		for(Compra cp : buscaCompleta) {
			buffer.append(cp.getEvento().getNome()+"\t\t" + cp.getEvento().getData() + "\t" + cp.getEvento().getHora() + "\t\t" + cp.getEvento().getRua()+ 
					cp.getEvento().getNumero() + "\t" + cp.getIngresso().getTipo() + "\t" + cp.getIngresso().getPreco() + "\t" + cp.getCaravana().getNome()+
					"\t" + cp.getCaravana().getPreco() + "\t" + cp.getNome() + "\t\t" + cp.getEmail());
		}*/
	//	taCompra.setText(buffer.toString());
		
		StringBuffer buffer = new StringBuffer("Caravana\tPreco\tCliente\t\temail");
		for(Compra cp : buscaCompleta) {
			buffer.append(cp.getCaravana().getNome() + "\t" + cp.getCaravana().getPreco() + "\t" + cp.getNome() + "\t\t" + cp.getEmail());
		}
		taCompra.setText(buffer.toString());

	}
	
	@Override
	public void visualizarOpcao() throws ClassNotFoundException, SQLException {
		limpaCamposCompra();
		taCompra.setText("");
		
		CompraDao cDao = new CompraDao();
		List<Compra> listaFiltragem = cDao.visualizaOpcao();
		
		/*StringBuffer buffer = new StringBuffer("CodigoEvento\tNomeEvento\t\tDia\tCodigoIngresso\tPreco\tCodigoCaravana\tPrecoCaravana");
		for(Compra cp : listaFiltragem) {
		/*	buffer.append(cp.getEvento().getCodigo()+"\t"+cp.getEvento().getNome()+
					"\t\t"+cp.getEvento().getData()+"\t"+cp.getIngresso().getCodigo() + "\t" + cp.getIngresso().getPreco()+
					"\t"+cp.getCaravana().getCodigo()+"\t"+cp.getCaravana().getPreco());
		}*/
		
		StringBuffer buffer = new StringBuffer("Codigo\t\t\tPreco\n");
		for(Compra cp : listaFiltragem) {
			buffer.append(cp.getCaravana().getCodigo() + "\t\t\t\t" + cp.getCaravana().getPreco()+"\n");
		}
		
		taCompra.setText(buffer.toString());
	}
	
	public void limpaCamposCompra() throws ClassNotFoundException, SQLException {
		txtCodigoCompra.setText("");
		txtNomeComprador.setText("");
		txtEmailCompra.setText("");
//		txtCodigoEvento_compra.setText("");
//		txtCodigoIngresso_compra.setText("");
		txtCodigoCaravana_compra.setText("");
	}

}
