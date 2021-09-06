package com.example.transporteescolar;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.database.sqlite.SQLiteDatabase;   // banco de dados
import android.database.Cursor;

public class Relatorio2 extends Activity {
	SQLiteDatabase bancodados;
	Cursor cursor;
	ListView MostraDados;
	SimpleCursorAdapter AdapterLista;
	private int _posicao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relatorio2);
	selecionarDados();
		
		AutoCompleteTextView auto = (AutoCompleteTextView) findViewById (R.id.autoCompleteTextView1);
		auto.setTextColor(Color.parseColor("black"));
		
		ListView MostraDados = (ListView) findViewById (R.id.listView1);

}
	public ArrayList<String> selecionarDados(){
		cursor = null;
		final ListView MostraDados = (ListView) findViewById (R.id.listView1);
		AutoCompleteTextView auto = (AutoCompleteTextView) findViewById (R.id.autoCompleteTextView1);
		auto.setTextColor(Color.parseColor("black"));
		bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
		ArrayList<String> listaPessoas = new ArrayList <String>();
		
		try {
			cursor = bancodados.query("relatorio", // table nome TEXT, rg TEXT, bairro TEXT, cidade TEXT, rua TEXT, num TEXT);
					new String [] {"id", "ano", "combustivel", "gastos_v", "gastos_o", "total_d", "lucro_g", "lucro_l"},
					null, //selection
					null, //selectionArgs
					null, //groupBy,
					null, //having,
					null, // orderBy,
					null); //  limitsimple_expandable_list_item_1
			
			while (cursor.moveToNext()){
				
				String dnome = cursor.getString(cursor.getColumnIndex("ano"));
				String ID = cursor.getString(cursor.getColumnIndex("total_d"));
				String I = cursor.getString(cursor.getColumnIndex("lucro_g"));
				String v = cursor.getString(cursor.getColumnIndex("lucro_l"));
				listaPessoas.add( dnome + " - "+ ID + " / " + I +" / " + v );
				
				ArrayAdapter<String> adapterPessoas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaPessoas);
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


			(Relatorio2.this);
	mensagem.setTitle(titulo);
	mensagem.setMessage(texto);
	mensagem.setNeutralButton("OK", null);
	mensagem.show();
	}
}
