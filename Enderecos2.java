package com.example.transporteescolar;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemLongClickListener;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;   // banco de dados
import android.database.Cursor; 
import android.graphics.Color;

public class Enderecos2 extends Activity{
	SQLiteDatabase bancodados;
	Cursor cursor;
	ListView MostraDados;
	SimpleCursorAdapter AdapterLista;
	private int _posicao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enderecos2);
		selecionarDados2();

		// #00ff00
		
		AutoCompleteTextView auto = (AutoCompleteTextView) findViewById (R.id.autoCompleteTextView1);
		auto.setTextColor(Color.parseColor("black"));
		
		ListView MostraDados = (ListView) findViewById (R.id.lista);
		Button endereco2 = (Button) findViewById (R.id.Buscar111);
		Button endereco = (Button) findViewById (R.id.button1);
Button volt = (Button) findViewById (R.id.button2);
		
		
		volt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (Enderecos2.this, Cmeni.class));
				
			}
		});
		
		
		endereco.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (Enderecos2.this, Enderecos.class));
				
			}
		});
		
		
		endereco2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				selecionarDados2();
				
			}
		});
		

}
	
	public ArrayList<String> selecionarDados2(){
		cursor = null;
		final ListView MostraDados = (ListView) findViewById (R.id.lista);
		AutoCompleteTextView auto = (AutoCompleteTextView) findViewById (R.id.autoCompleteTextView1);
		auto.setTextColor(Color.parseColor("black"));
		bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
		ArrayList<String> listaPessoas = new ArrayList <String>();
		
		try {
			cursor = bancodados.query("dpcliente", // table nome TEXT, rg TEXT, bairro TEXT, cidade TEXT, rua TEXT, num TEXT);
					new String [] {"id","nome","rg","cpf","NomeR1", "NomeR2", "NomeR3", "rua", "cidade", "tel", "celular", "telcontato","rua2", "cidade2", "pagamento", "preco", "dnome", "sexo", "nascimento", "serie","escola","endereco","professor","periodo", "email", "entrada"}, // columns
					null, //selection
					null, //selectionArgs
					null, //groupBy,
					null, //having,
					"dnome ASC", // orderBy,
					null); //  limit
			
			while (cursor.moveToNext()){
				
				String dnome = cursor.getString(cursor.getColumnIndex("dnome"));
				String ID = cursor.getString(cursor.getColumnIndex("rua2"));
				String v = cursor.getString(cursor.getColumnIndex("cidade2"));
				listaPessoas.add( dnome + " - "+ID + " / " + v);
				
				ArrayAdapter<String> adapterPessoas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPessoas);
				MostraDados.setAdapter(adapterPessoas);
				auto.setAdapter(adapterPessoas);
/*
				
			}
		}
			catch (Exception Erro) {
				mensagemExibir ("Erro Banco", "Nenhum dado encontrado "
						 +Erro.getMessage());
		}finally{
			bancodados.close();
		}
		return listaPessoas;
		
		*/
				MostraDados.setOnItemLongClickListener(new OnItemLongClickListener() {

					 

	                  @Override

	                  public boolean onItemLongClick(AdapterView<?> arg0, View arg1,

	                             int posicao, long id) {

	                        _posicao = posicao;

	                        // TODO Auto-generated method stub

	                        //registra o menu após o click

	                        registerForContextMenu(MostraDados);

	                        return false;

	                  }

	        });

				
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
		
	

	@Override

  public void onCreateContextMenu(ContextMenu menu, View v,

          ContextMenuInfo menuInfo) {

    // TODO Auto-generated method stub

    super.onCreateContextMenu(menu, v, menuInfo);

     //cria o menu para fazer a ligação     

    menu.add(0, 0, 0, "Abrir Google Maps");

  }

	@Override

  public boolean onContextItemSelected(MenuItem item) {

    // TODO Auto-generated method stub

    if(item.getItemId()==0)

    {

          try

          {  

//pega a posição da pessoa
          	
        	  
           Uri urimaps = Uri.parse("https://www.google.com.br/maps/preview#!data=!1m4!1m3!1d2982299!2d-48.6355141!3d-22.5460521/‎‎"+ selecionarDados2().get(_posicao));
				Intent IPagWeb = new Intent (Intent.ACTION_VIEW, urimaps);
				startActivity(IPagWeb);

          }

          catch(ActivityNotFoundException act)

          {

                Log.e("Exemplo de chamada", "falha", act);

          }

    }

   

    return super.onContextItemSelected(item);



  }
	
	
	
	
	
	public void mensagemExibir (String titulo, String texto)
	{
	AlertDialog.Builder mensagem = new AlertDialog.Builder 


			(Enderecos2.this);
	mensagem.setTitle(titulo);
	mensagem.setMessage(texto);
	mensagem.setNeutralButton("OK", null);
	mensagem.show();
	}
}