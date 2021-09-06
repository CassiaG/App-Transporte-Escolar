package com.example.transporteescolar;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;   // banco de dados
import android.database.Cursor; 

public class MainActivity extends Activity {
	SQLiteDatabase bancodados;
	Cursor cursor;
	ListView MostraDados;
	SimpleCursorAdapter AdapterLista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btadm = (Button) findViewById (R.id.button3);
		Button manha = (Button) findViewById (R.id.button1);
		Button tarde = (Button) findViewById (R.id.button2);
		ListView MostraDados = (ListView) findViewById (R.id.list);
		
		
		tarde.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				selecionarDadosTarde();
				selecionarDadosTarde2();
		
			}
		});

			manha.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				selecionarDadosManha();
				selecionarDadosManha2();
				
			}
		});
		
		
	
			btadm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (MainActivity.this, Bblogin.class));
				
			}
		});
	
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 //Pronto, você tem o retorno para a sua Activity, ai se você quiser retornar algum valor da outra
 //Activity, isso tambem é possivel
			}

	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{	
	if (keyCode == KeyEvent.KEYCODE_BACK) {	
		startActivity(new Intent (MainActivity.this, Bblogin.class));
		return false;	
		}	
	return super.onKeyDown(keyCode, event);	
	} 
	
	public ArrayList<String> selecionarDadosManha2(){
		cursor = null;
		ListView MostraDados = (ListView) findViewById (R.id.list);
		bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
		ArrayList<String> listaPessoas = new ArrayList <String>();
		
		try {
			cursor = bancodados.query("dpcliente WHERE periodo = 'm' ", // table nome TEXT, rg TEXT, bairro TEXT, cidade TEXT, rua TEXT, num TEXT);
					new String [] {"id","nome","rg","cpf", "rua", "cidade", "tel", "celular", "telcontato","rua2", "cidade2", "pagamento", "preco", "dnome", "sexo", "nascimento", "serie","escola","endereco","professor","periodo", "email", "entrada"}, // columns
					null, //selection
					null, //selectionArgs
					null, //groupBy,
					null, //having,
					"dnome ASC", // orderBy,
					null); //  limit
			
			while (cursor.moveToNext()){
				
				String dnome = cursor.getString(cursor.getColumnIndex("dnome"));
				String ID = cursor.getString(cursor.getColumnIndex("id"));
				listaPessoas.add( ID + " - " + dnome);
				
				ArrayAdapter<String> adapterPessoas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPessoas);
				MostraDados.setAdapter(adapterPessoas);

				
			}
		}
			catch (Exception Erro) {
			/*	mensagemExibir ("Erro Banco", "Nenhum dado encontrado "
						 +Erro.getMessage());*/
				  Toast.makeText(MainActivity.this, "Não possui nenhum Registro!", Toast.LENGTH_SHORT).show();
						
		}finally{
			bancodados.close();
		}
		return listaPessoas;
		
		}
	
	public ArrayList<String> selecionarDadosManha(){
		cursor = null;
		ListView MostraDados = (ListView) findViewById (R.id.list);
		bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
		ArrayList<String> listaPessoas = new ArrayList <String>();
		
		try {
			cursor = bancodados.query("dpcliente WHERE periodo = 'M' ", // table nome TEXT, rg TEXT, bairro TEXT, cidade TEXT, rua TEXT, num TEXT);
					new String [] {"id","nome","rg","cpf", "rua", "cidade", "tel", "celular", "telcontato","rua2", "cidade2", "pagamento", "preco", "dnome", "sexo", "nascimento", "serie","escola","endereco","professor","periodo", "email", "entrada"}, // columns
					null, //selection
					null, //selectionArgs
					null, //groupBy,
					null, //having,
					"dnome ASC", // orderBy,
					null); //  limit
			
			while (cursor.moveToNext()){
				
				String dnome = cursor.getString(cursor.getColumnIndex("dnome"));
				String ID = cursor.getString(cursor.getColumnIndex("id"));
				listaPessoas.add( ID + " - " + dnome);
				
				ArrayAdapter<String> adapterPessoas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPessoas);
				MostraDados.setAdapter(adapterPessoas);

				
			}
		}
			catch (Exception Erro) {
			/*	mensagemExibir ("Erro Banco", "Nenhum dado encontrado "
						 +Erro.getMessage());*/
				 Toast.makeText(MainActivity.this, "Não possui nenhum Registro!", Toast.LENGTH_SHORT).show();
		}finally{
			bancodados.close();
		}
		return listaPessoas;
		
		}
	
	
	public ArrayList<String> selecionarDadosTarde(){
		cursor = null;
		ListView MostraDados = (ListView) findViewById (R.id.list);
		bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
		ArrayList<String> listaPessoas = new ArrayList <String>();
		
		try {
			cursor = bancodados.query("dpcliente WHERE periodo = 'T' ", // table nome TEXT, rg TEXT, bairro TEXT, cidade TEXT, rua TEXT, num TEXT);
					new String [] {"id","nome","rg","cpf", "rua", "cidade", "tel", "celular", "telcontato","rua2", "cidade2", "pagamento", "preco", "dnome", "sexo", "nascimento", "serie","escola","endereco","professor","periodo", "email", "entrada"}, // columns
					null, //selection
					null, //selectionArgs
					null, //groupBy,
					null, //having,
					"dnome ASC", // orderBy,
					null); //  limit
			
			while (cursor.moveToNext()){
				
				String dnome = cursor.getString(cursor.getColumnIndex("dnome"));
				String ID = cursor.getString(cursor.getColumnIndex("id"));
				listaPessoas.add( ID + " - " + dnome);
				
				ArrayAdapter<String> adapterPessoas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPessoas);
				MostraDados.setAdapter(adapterPessoas);

				
			}
		}
			catch (Exception Erro) {
				/*mensagemExibir ("Erro Banco", "Nenhum dado encontrado "
						 +Erro.getMessage());*/
				 Toast.makeText(MainActivity.this, "Não possui nenhum Registro!", Toast.LENGTH_SHORT).show();
		}finally{
			bancodados.close();
		}
		return listaPessoas;
		
		}
	
	public ArrayList<String> selecionarDadosTarde2(){
		cursor = null;
		ListView MostraDados = (ListView) findViewById (R.id.list);
		bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
		ArrayList<String> listaPessoas = new ArrayList <String>();
		
		try {
			cursor = bancodados.query("dpcliente WHERE periodo = 't' ", // table nome TEXT, rg TEXT, bairro TEXT, cidade TEXT, rua TEXT, num TEXT);
					new String [] {"id","nome","rg","cpf", "rua", "cidade", "tel", "celular", "telcontato","rua2", "cidade2", "pagamento", "preco", "dnome", "sexo", "nascimento", "serie","escola","endereco","professor","periodo", "email", "entrada"}, // columns
					null, //selection
					null, //selectionArgs
					null, //groupBy,
					null, //having,
					"dnome ASC", // orderBy,
					null); //  limit
			
			while (cursor.moveToNext()){
				
				String dnome = cursor.getString(cursor.getColumnIndex("dnome"));
				String ID = cursor.getString(cursor.getColumnIndex("id"));
				listaPessoas.add( ID + " - " + dnome);
				
				ArrayAdapter<String> adapterPessoas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPessoas);
				MostraDados.setAdapter(adapterPessoas);

				
			}
		}
			catch (Exception Erro) {
			/*	mensagemExibir ("Erro Banco", "Nenhum dado encontrado "
						 +Erro.getMessage());*/
				 Toast.makeText(MainActivity.this, "Não possui nenhum Registro!", Toast.LENGTH_SHORT).show();
		}finally{
			bancodados.close();
		}
		return listaPessoas;
		
		}
	
	
	
	public void mensagemExibir (String titulo, String texto)
	{
	AlertDialog.Builder mensagem = new AlertDialog.Builder 


			(MainActivity.this);
	mensagem.setTitle(titulo);
	mensagem.setMessage(texto);
	mensagem.setNeutralButton("OK", null);
	mensagem.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		boolean result = super.onCreateOptionsMenu (menu);
		super.onCreateOptionsMenu (menu);
		menu.add(0, 1, 0, "Endereços"); // aqui add intens no menu
		menu.add(0, 2, 0, "Lista de Responsáveis pela criança"); // aqui add intens no menu
		menu.add(0, 3, 0, "Contatos"); // aqui add intens no menu
		
	
		
		
		return result;
		}
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
	switch(item.getItemId())
	{
	case 1 : Intent chamarChamada = new Intent(this, Enderecos3.class);
	startActivity(chamarChamada); break;
	case 2 : Intent chamarlista = new Intent(this, Rresponsaveis2.class);
	startActivity(chamarlista); break;
case 3 : Intent chamarcadastro = new Intent(this, chamadaContato.class);
	startActivity(chamarcadastro); break;
	}
	return super.onOptionsItemSelected(item);
	}

	
	}
	
