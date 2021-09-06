package com.example.transporteescolar;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;   // banco de dados
import android.database.Cursor; 
import android.graphics.Color;

public class chamadaContatocont extends Activity{
	SQLiteDatabase bancodados;
	Cursor cursor;
	ListView MostraDados;
	SimpleCursorAdapter AdapterLista;
	
	 private int _sicao;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chamadacontatocont);
		selecionarDadosTelCont();
		
		Button telefone = (Button) findViewById (R.id.button1);
		Button celular = (Button) findViewById (R.id.button2);
		Button telcont = (Button) findViewById (R.id.Buscar111);
		EditText auto1 = (EditText) findViewById (R.id.autoCompleteTextView1);
		auto1.setTextColor(Color.parseColor("black"));
		ListView MostraDados = (ListView) findViewById (R.id.list);
		Button menu = (Button) findViewById (R.id.button3);

		menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (chamadaContatocont.this, MainActivity.class));
				
			}
		});
	

		telefone.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (chamadaContatocont.this, chamadaContato.class));
				
			}
		});
		
		celular.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (chamadaContatocont.this, chamadaContatocel.class));
				
			}
		});

		telcont.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		
		selecionarDadosTelCont();
	}
});
		

}
	/*@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 //Pronto, você tem o retorno para a sua Activity, ai se você quiser retornar algum valor da outra
 //Activity, isso tambem é possivel
			}

	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{	
	if (keyCode == KeyEvent.KEYCODE_BACK) {	
		startActivity(new Intent (chamadaContatocont.this, MainActivity.class));
		return false;	
		}	
	return super.onKeyDown(keyCode, event);	
	}
	*/
	
	
	public ArrayList<String> selecionarDadosTelCont(){
		cursor = null;
		final ListView MostraDados = (ListView) findViewById (R.id.list);
		AutoCompleteTextView auto = (AutoCompleteTextView) findViewById (R.id.autoCompleteTextView1);
		auto.setTextColor(Color.parseColor("black"));
		bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
		ArrayList<String> listaPessoas = new ArrayList <String>();
		
		try {
			cursor = bancodados.query("dpcliente", // table nome TEXT, rg TEXT, bairro TEXT, cidade TEXT, rua TEXT, num TEXT);
					new String [] {"id","nome","rg","cpf", "NomeR1", "NomeR2", "NomeR3", "rua", "cidade", "tel", "celular", "telcontato","rua2", "cidade2", "pagamento", "preco", "dnome", "sexo", "nascimento", "serie","escola","endereco","professor","periodo", "email", "entrada"}, // columns
					null, //selection
					null, //selectionArgs
					null, //groupBy,
					null, //having,
					"nome ASC", // orderBy,
					null); //  limit
			
			while (cursor.moveToNext()){
				
				String dnome = cursor.getString(cursor.getColumnIndex("nome"));
				String ID = cursor.getString(cursor.getColumnIndex("telcontato"));
				listaPessoas.add( dnome + " - " + ID);
				
				ArrayAdapter<String> adapterPessoas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPessoas);
				MostraDados.setAdapter(adapterPessoas);
				auto.setAdapter(adapterPessoas);
				MostraDados.setOnItemLongClickListener(new OnItemLongClickListener() {

					 

	                  @Override

	                  public boolean onItemLongClick(AdapterView<?> arg0, View arg1,

	                             int posicao, long id) {

	                        _sicao = posicao;

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

      menu.add(0, 0, 0, "Ligar");

    }

	@Override

    public boolean onContextItemSelected(MenuItem item) {

      // TODO Auto-generated method stub

      if(item.getItemId()==0)

      {

            try

            {  

                  //Tipos de chamadas

                  //ACTION_DIAL

                  //ACTION_CALL

            	/*Uri uriLigacontato = Uri.parse("tel:40922048");
            	Intent ILigacontato = new Intent (Intent.ACTION_CALL, ILigacontato);
            	startActivity(ILigacontato);*/
 //pega a posição da pessoa
            	
            	Intent chamadar = new Intent(Intent.ACTION_DIAL);
                  
                  chamadar.setData(Uri.parse("tel:" + selecionarDadosTelCont().get(_sicao)));
                  
                  startActivity(chamadar);
           


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


			(chamadaContatocont.this);
	mensagem.setTitle(titulo);
	mensagem.setMessage(texto);
	mensagem.setNeutralButton("OK", null);
	mensagem.show();
	}
	
}