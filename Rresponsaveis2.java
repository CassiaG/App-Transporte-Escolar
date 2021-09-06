package com.example.transporteescolar;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;   // banco de dados
import android.database.Cursor; 
import android.graphics.Color;

public class Rresponsaveis2 extends Activity{
	SQLiteDatabase bancodados;
	Cursor cursor;
	ListView MostraDados;
	SimpleCursorAdapter AdapterLista;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.responsaveis2);
		selecionarDadosManha2();
		
		EditText auto1 = (EditText) findViewById (R.id.autoCompleteTextView1);
		auto1.setTextColor(Color.parseColor("black"));
		Button menu = (Button) findViewById (R.id.button3);

		menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (Rresponsaveis2.this, MainActivity.class));
				
			}
		});

	
		
		
}
	
/*	public ArrayList<String> selecionarREsp(){
		cursor = null;
		final ListView MostraDados = (ListView) findViewById (R.id.list);
		AutoCompleteTextView auto = (AutoCompleteTextView) findViewById (R.id.autoCompleteTextView1);
		auto.setTextColor(Color.parseColor("black"));
		bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
		ArrayList<String> listaPessoas = new ArrayList <String>();
		
		try {
			cursor = bancodados.query("depclientes", // table nome TEXT, rg TEXT, bairro TEXT, cidade TEXT, rua TEXT, num TEXT);
					new String [] {"id","nome","rg","cpf", "NomeR1", "NomeR2", "NomeR3", "rua", "cidade", "tel", "celular", "telcontato","rua2", "cidade2", "pagamento", "preco", "dnome", "sexo", "nascimento", "serie","escola","endereco","professor","periodo", "email", "entrada"}, // columns
					null, //selection
					null, //selectionArgs
					null, //groupBy,
					null, //having,
					"nome ASC", // orderBy,
					null); //  limit
			
			while (cursor.moveToNext()){
				
				String dnome = cursor.getString(cursor.getColumnIndex("dnome"));
				String nome = cursor.getString(cursor.getColumnIndex("nome"));
				String nome1 = cursor.getString(cursor.getColumnIndex("NomeR1"));
				String nome2 = cursor.getString(cursor.getColumnIndex("NomeR2"));
				String nome3 = cursor.getString(cursor.getColumnIndex("NomeR3"));
				listaPessoas.add( dnome + " - " + nome + "," + nome1 + ","+ nome2 + "," + nome3);
				
				ArrayAdapter<String> adapterPessoas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPessoas);
				MostraDados.setAdapter(adapterPessoas);
				auto.setAdapter(adapterPessoas);

				
			}
		}
			catch (Exception Erro) {
				mensagemExibir ("Erro Banco", "Nenhum dado encontrado "
						 +Erro.getMessage());
		}finally{
			bancodados.close();
		}
		return listaPessoas;
		
		}*/
	
	public ArrayList<String> selecionarDadosManha2(){
		cursor = null;
		ListView MostraDados = (ListView) findViewById (R.id.list);
		AutoCompleteTextView auto = (AutoCompleteTextView) findViewById (R.id.autoCompleteTextView1);
		auto.setTextColor(Color.parseColor("black"));
		bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
		ArrayList<String> listaPessoas = new ArrayList <String>();
		
		try {
			cursor = bancodados.query("dpcliente", // table nome TEXT, rg TEXT, bairro TEXT, cidade TEXT, rua TEXT, num TEXT);
					new String [] {"id","nome","rg","cpf", "nomeR1", "nomeR2", "nomeR3", "rua", "cidade", "tel", "celular", "telcontato","rua2", "cidade2", "pagamento", "preco", "dnome", "sexo", "nascimento", "serie","escola","endereco","professor","periodo", "email", "entrada"}, // columns
					null, //selection
					null, //selectionArgs
					null, //groupBy,
					null, //having,
					"dnome ASC", // orderBy,
					null); //  limit
			
			while (cursor.moveToNext()){
				
				String dnome = cursor.getString(cursor.getColumnIndex("dnome"));
				String nome = cursor.getString(cursor.getColumnIndex("nome"));
				String nome1 = cursor.getString(cursor.getColumnIndex("nomeR1"));
				String nome2 = cursor.getString(cursor.getColumnIndex("nomeR2"));
				String nome3 = cursor.getString(cursor.getColumnIndex("nomeR3"));
				listaPessoas.add( dnome + " - " + nome  + ", " +nome1 + ", "+ nome2 + ", " + nome3);
				
				ArrayAdapter<String> adapterPessoas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPessoas);
				MostraDados.setAdapter(adapterPessoas);
				auto.setAdapter(adapterPessoas);
				
			}
		}
			catch (Exception Erro) {
				mensagemExibir ("Erro Banco", "Nenhum dado encontrado "
						 +Erro.getMessage());
		}finally{
			bancodados.close();
		}
		return listaPessoas;
		
		}
	
	
	
	

	public void mensagemExibir (String titulo, String texto)
	{
	AlertDialog.Builder mensagem = new AlertDialog.Builder 


			(Rresponsaveis2.this);
	mensagem.setTitle(titulo);
	mensagem.setMessage(texto);
	mensagem.setNeutralButton("OK", null);
	mensagem.show();
	}
	
}