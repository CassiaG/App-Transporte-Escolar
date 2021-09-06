package com.example.transporteescolar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;   // banco de dados
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Consulta extends Activity {
	SQLiteDatabase bancodados;
	Cursor cursor;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consulta);
		AbreouCriabanco ();
		
		Button button1 = (Button) findViewById (R.id.button2);
		Button button2 = (Button) findViewById (R.id.button3);
		Button btprocurar = (Button) findViewById (R.id.button1);
		Button btvai = (Button) findViewById (R.id.button4);
		final EditText codnome = (EditText) findViewById(R.id.editText4);
		
		
		btvai.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mostrarRegistroCod ()){
					
					mostrarDados();
					
				}else
			
		return;
	
				
		}
		});
		
		btprocurar.setOnClickListener (new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (BuscarDados())
				{ 
					mostrarDados();
				}else
					mensagemExibir("Aviso", "Não Possui Registro Gravado");

		return;
	
			
	}
		});
		
		

	
	
	button2.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			mostrarproximoregistro();
			
		}
	});
	
	button1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(cursor.getCount()>=1){
				mostrarRegistroAnterior ();
			}
			
		}
	});

}
	public void AbreouCriabanco () {
		try{
			bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_READABLE, null);
			String sql = "CREATE TABLE IF NOT EXISTS dpcliente"+ "(id INTEGER PRIMARY KEY, nome TEXT, rg TEXT, cpf TEXT, nomeR1 TEXT, nomeR2 TEXT, nomeR3 TEXT, rua TEXT, cidade TEXT, tel TEXT, celular TEXT, telcontato TEXT, rua2 TEXT, "
				+ "cidade2 TEXT, pagamento TEXT, preco TEXT, dnome TEXT, sexo TEXT, nascimento TEXT, serie TEXT, escola TEXT, endereco TEXT, professor TEXT, periodo TEXT, email TEXT, entrada TEXT, bairroe TEXT);"; 
		
		bancodados.execSQL(sql); 
		
		Toast.makeText(Consulta.this, "Banco aberto com sucesso!", Toast.LENGTH_SHORT).show();
		
		}
		catch(Exception erro){
			mensagemExibir("Erro no banco", "Erro ao abrir ou criar o banco" + erro.getMessage());
			
		}
		}
	
	public void fechaBanco (){
		
		try{
			
			bancodados.close(); //fecha banco de dados
			
	} catch (Exception Erro) {
		mensagemExibir ("Erro Banco", "Erro ao fechar banco : "
				 +Erro.getMessage());
	}
		}
	private boolean BuscarDados(){
		
		
		try{
			cursor = bancodados.query("dpcliente", // table nome TEXT, rg TEXT, bairro TEXT, cidade TEXT, rua TEXT, num TEXT);
					new String [] {"id","nome","rg","cpf", "nomeR1", "nomeR2", "nomeR3","rua", "cidade", "tel", "celular", "telcontato","rua2", "cidade2", "pagamento", "preco", "dnome", "sexo", "nascimento", "serie","escola","endereco","professor","periodo", "email", "entrada", "bairroe"}, // columns
			null, //selection
			null, //selectionArgs
			null, //groupBy,
			null, //having,
			null, // orderBy,
			null); //  limit
			
			// "order by nome" ordena por nome, telefone o que quiser
			//getcount mostra o numero de linhas ou de registros do cursor
			cursor.moveToFirst(); 
			mostrarDados();
			
			int numeroRegistros = cursor.getCount();

		if(numeroRegistros != 0)
		{
				cursor.moveToFirst(); 
				mostrarDados();
				
				
				
				return true; 
			
		}else
			return false;
		
	}
		catch (Exception Erro) {
			mensagemExibir ("Erro Banco", "Nenhum dado encontrado "
					 +Erro.getMessage());
		
			return false;
		}
					
}
	
	
	
	public void mostrarDados(){
							
							EditText nome = (EditText) findViewById(R.id.editText10);
							EditText rg = (EditText) findViewById(R.id.editText20);
							EditText cpf = (EditText) findViewById(R.id.editText130);
							EditText nomeR1 = (EditText) findViewById(R.id.editText4);
							EditText nomeR2 = (EditText) findViewById(R.id.editText6);
							EditText nomeR3 = (EditText) findViewById(R.id.editText7);
							EditText rua = (EditText) findViewById(R.id.editText30);
							EditText cidade = (EditText) findViewById(R.id.editText40);
							EditText tel = (EditText) findViewById(R.id.editText50);
							EditText celular = (EditText) findViewById(R.id.editText60);
							EditText telcontato = (EditText) findViewById(R.id.editText70);
							EditText rua2 = (EditText) findViewById(R.id.editText80);
							EditText cidade2 = (EditText) findViewById(R.id.editText90);
							EditText pagamento = (EditText) findViewById(R.id.editText100);
							EditText preco = (EditText) findViewById(R.id.editText110);
							EditText dnome = (EditText) findViewById(R.id.editText120);
							EditText sexo = (EditText) findViewById(R.id.editText140);
							EditText nascimento = (EditText) findViewById(R.id.editText150);
							EditText serie = (EditText) findViewById(R.id.editText160);
							EditText escola = (EditText) findViewById(R.id.editText1);
							EditText endereco = (EditText) findViewById(R.id.editText5);
							EditText professor = (EditText) findViewById(R.id.editText8);
							EditText periodo = (EditText) findViewById(R.id.EditText01);
							EditText email = (EditText) findViewById(R.id.editText2);
							EditText entrada = (EditText) findViewById(R.id.editText3);
							EditText bairroe = (EditText) findViewById(R.id.EditText03);
							

						int cod = cursor.getInt(cursor.getColumnIndex("id"));
							
						nome.setText(cursor.getString(cursor.getColumnIndex("nome")));
						
						rg.setText(cursor.getString(cursor.getColumnIndex("rg")));

						cpf.setText(cursor.getString(cursor.getColumnIndex("cpf")));
						
						nomeR1.setText(cursor.getString(cursor.getColumnIndex("nomeR1")));

						nomeR2.setText(cursor.getString(cursor.getColumnIndex("nomeR2")));

						nomeR3.setText(cursor.getString(cursor.getColumnIndex("nomeR3")));
								
						rua.setText(cursor.getString(cursor.getColumnIndex("rua")));

						cidade.setText(cursor.getString(cursor.getColumnIndex("cidade")));

						tel.setText(cursor.getString(cursor.getColumnIndex("tel")));
						
						celular.setText(cursor.getString(cursor.getColumnIndex("celular")));
						
						telcontato.setText(cursor.getString(cursor.getColumnIndex("telcontato")));

						rua2.setText(cursor.getString(cursor.getColumnIndex("rua2")));
								
						cidade2.setText(cursor.getString(cursor.getColumnIndex("cidade2")));

						pagamento.setText(cursor.getString(cursor.getColumnIndex("pagamento")));
						
						preco.setText(cursor.getString(cursor.getColumnIndex("preco")));
						
						dnome.setText(cursor.getString(cursor.getColumnIndex("dnome")));

						sexo.setText(cursor.getString(cursor.getColumnIndex("sexo")));
								
						nascimento.setText(cursor.getString(cursor.getColumnIndex("nascimento")));

						serie.setText(cursor.getString(cursor.getColumnIndex("serie")));
						
						escola.setText(cursor.getString(cursor.getColumnIndex("escola")));
						
						endereco.setText(cursor.getString(cursor.getColumnIndex("endereco")));

						professor.setText(cursor.getString(cursor.getColumnIndex("professor")));
						
						periodo.setText(cursor.getString(cursor.getColumnIndex("periodo")));
						
						email.setText(cursor.getString(cursor.getColumnIndex("email")));
						
						entrada.setText(cursor.getString(cursor.getColumnIndex("entrada")));

						bairroe.setText(cursor.getString(cursor.getColumnIndex("bairroe")));
						}
	
	
	
	public void mostrarRegistroAnterior ()
	{
					try {
	
		cursor.moveToPrevious();
		mostrarDados();
		}
					
	catch(Exception erro)
	{
		//mensagemExibir ("Erro Banco", "Você já está no primeiro registro do banco: "+erro.getMessage());
		Toast.makeText(Consulta.this, "Você ja está no primeiro Registro!", Toast.LENGTH_SHORT).show();
			}
	}
		//
				//	
	
	
	public boolean mostrarRegistroCod ()
	{	
		
		final EditText codnome = (EditText) findViewById(R.id.EditText02);
		String value= codnome.getText().toString(); 
		int finalValue=Integer.parseInt(value); 
		try
		
		{
			
			cursor = bancodados.query("dpcliente", // table nome TEXT, rg TEXT, bairro TEXT, cidade TEXT, rua TEXT, num TEXT);
			new String [] {"id","nome","rg","cpf", "nomeR1", "nomeR2", "nomeR3","rua", "cidade", "tel", "celular", "telcontato","rua2", "cidade2", "pagamento", "preco", "dnome", "sexo", "nascimento", "serie","escola","endereco","professor","periodo", "email", "entrada", "bairroe"}, // columns
			null, //selection
			null, //selectionArgs
			null, //groupBy,
			null, //having,
			null, // orderBy,
			null); //  limit
	
			
			cursor.move(finalValue); 
			mostrarDados();
			
	int numeroRegistros = cursor.getCount();
	
			if(numeroRegistros == 0)
			{
				
				cursor.move(finalValue); 
			mostrarDados();
		
		
		
		return true; 
	
			}else
				
				return false;

		
		
	
		}
			catch (Exception Erro) {
			//	mensagemExibir ("Erro Banco", "Nenhum dado encontrado "
					//	+Erro.getMessage());
				Toast.makeText(Consulta.this, "Esse codigo não possui nenhum Registro!", Toast.LENGTH_SHORT).show();
				return false;
			}
		
		
			
}
	
	
	
	public void mostrarproximoregistro() {	

	try {
		
	cursor.moveToNext();
	mostrarDados();


			}

	catch(Exception erro)
	{
	//mensagemExibir ("Erro Banco", "Não possui proximo registro no banco: "+erro.getMessage());
		Toast.makeText(Consulta.this, "Você já está no ultimo Registro!", Toast.LENGTH_SHORT).show();
		}
			}
	
	private boolean AlterarRegistro () {
		
		EditText nome = (EditText) findViewById(R.id.editText10);
		EditText rg = (EditText) findViewById(R.id.editText20);
		EditText cpf = (EditText) findViewById(R.id.editText130);
		EditText nomeR1 = (EditText) findViewById(R.id.editText4);
		EditText nomeR2 = (EditText) findViewById(R.id.editText6);
		EditText nomeR3 = (EditText) findViewById(R.id.editText7);
		EditText rua = (EditText) findViewById(R.id.editText30);
		EditText cidade = (EditText) findViewById(R.id.editText40);
		EditText tel = (EditText) findViewById(R.id.editText50);
		EditText celular = (EditText) findViewById(R.id.editText60);
		EditText telcontato = (EditText) findViewById(R.id.editText70);
		EditText rua2 = (EditText) findViewById(R.id.editText80);
		EditText cidade2 = (EditText) findViewById(R.id.editText90);
		EditText pagamento = (EditText) findViewById(R.id.editText100);
		EditText preco = (EditText) findViewById(R.id.editText110);
		EditText dnome = (EditText) findViewById(R.id.editText120);
		EditText sexo = (EditText) findViewById(R.id.editText140);
		EditText nascimento = (EditText) findViewById(R.id.editText150);
		EditText serie = (EditText) findViewById(R.id.editText160);
		EditText escola = (EditText) findViewById(R.id.editText1);
		EditText endereco = (EditText) findViewById(R.id.editText5);
		EditText professor = (EditText) findViewById(R.id.editText8);
		EditText periodo = (EditText) findViewById(R.id.EditText01);
		EditText email = (EditText) findViewById(R.id.editText2);
		EditText entrada = (EditText) findViewById(R.id.editText3);
		EditText bairroe = (EditText) findViewById(R.id.EditText03);

							
							try{
								
								
							bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_WRITEABLE, null);
							int cod = cursor.getInt(cursor.getColumnIndex("id"));
							cursor.moveToPosition(cod);
					
							 ContentValues values = new ContentValues();    
								long rowID = cod;
						    if (rowID >= 0) {    
							        values.put("id", rowID);
							        values.put("nome", nome.getText().toString());
								    values.put("rg", rg.getText().toString());
								    values.put("cpf", cpf.getText().toString());
								    values.put("nomeR1", nomeR1.getText().toString());
								    values.put("nomeR2", nomeR2.getText().toString());
								    values.put("nomeR3", nomeR3.getText().toString());
								    values.put("rua", rua.getText().toString());
								    values.put("cidade", cidade.getText().toString());
								    values.put("tel", tel.getText().toString());
								    values.put("celular", celular.getText().toString());
								    values.put("telcontato", telcontato.getText().toString());
								    values.put("rua2", rua2.getText().toString());
								    values.put("cidade2", cidade2.getText().toString());
								    values.put("pagamento", pagamento.getText().toString());
								    values.put("preco", preco.getText().toString());
								    values.put("dnome", dnome.getText().toString());
								    values.put("sexo", sexo.getText().toString());
								    values.put("nascimento", nascimento.getText().toString());
								    values.put("serie", serie.getText().toString());
							        values.put("escola", escola.getText().toString());
								    values.put("endereco", endereco.getText().toString());
								    values.put("professor", professor.getText().toString());
								    values.put("periodo", periodo.getText().toString());
								    values.put("email", email.getText().toString());
								    values.put("entrada", entrada.getText().toString());
								    values.put("bairroe", bairroe.getText().toString());  
								      
							        String[] args = new String[]{String.valueOf(rowID)};
						        bancodados.update("dpcliente", values, "id = ?", args);
							

							

						        Toast.makeText(Consulta.this, "Dados alterados com sucesso!", Toast.LENGTH_SHORT).show();

							return true;

							}
							}catch (Exception erro){

								mensagemExibir ("Erro Banco", "Dados Não alterados no banco: "+erro.getMessage());

							return false;
							}
							return false;
							
						}
	
				private boolean ExcluirRegistro () {
		
		EditText nome = (EditText) findViewById(R.id.editText10);
		EditText rg = (EditText) findViewById(R.id.editText20);
		EditText cpf = (EditText) findViewById(R.id.editText130);
		EditText nomeR1 = (EditText) findViewById(R.id.editText4);
		EditText nomeR2 = (EditText) findViewById(R.id.editText6);
		EditText nomeR3 = (EditText) findViewById(R.id.editText7);
		EditText rua = (EditText) findViewById(R.id.editText30);
		EditText cidade = (EditText) findViewById(R.id.editText40);
		EditText tel = (EditText) findViewById(R.id.editText50);
		EditText celular = (EditText) findViewById(R.id.editText60);
		EditText telcontato = (EditText) findViewById(R.id.editText70);
		EditText rua2 = (EditText) findViewById(R.id.editText80);
		EditText cidade2 = (EditText) findViewById(R.id.editText90);
		EditText pagamento = (EditText) findViewById(R.id.editText100);
		EditText preco = (EditText) findViewById(R.id.editText110);
		EditText dnome = (EditText) findViewById(R.id.editText120);
		EditText sexo = (EditText) findViewById(R.id.editText140);
		EditText nascimento = (EditText) findViewById(R.id.editText150);
		EditText serie = (EditText) findViewById(R.id.editText160);
		EditText escola = (EditText) findViewById(R.id.editText1);
		EditText endereco = (EditText) findViewById(R.id.editText5);
		EditText professor = (EditText) findViewById(R.id.editText8);
		EditText periodo = (EditText) findViewById(R.id.EditText01);
		EditText email = (EditText) findViewById(R.id.editText2);
		EditText entrada = (EditText) findViewById(R.id.editText3);
		EditText bairroe = (EditText) findViewById(R.id.EditText03);

							
							try{
								
								
							bancodados = openOrCreateDatabase("TransporteEscolar", MODE_WORLD_WRITEABLE, null);
							int cod = cursor.getInt(cursor.getColumnIndex("id"));
							cursor.moveToPosition(cod);
					
							 ContentValues values = new ContentValues();    
								long rowID = cod;
						    if (rowID >= 0) {    
							        values.put("id", rowID);
							        values.put("nome", nome.getText().toString());
								    values.put("rg", rg.getText().toString());
								    values.put("cpf", cpf.getText().toString());
								    values.put("nomeR1", nomeR1.getText().toString());
								    values.put("nomeR2", nomeR2.getText().toString());
								    values.put("nomeR3", nomeR3.getText().toString());
								    values.put("rua", rua.getText().toString());
								    values.put("cidade", cidade.getText().toString());
								    values.put("tel", tel.getText().toString());
								    values.put("celular", celular.getText().toString());
								    values.put("telcontato", telcontato.getText().toString());
								    values.put("rua2", rua2.getText().toString());
								    values.put("cidade2", cidade2.getText().toString());
								    values.put("pagamento", pagamento.getText().toString());
								    values.put("preco", preco.getText().toString());
								    values.put("dnome", dnome.getText().toString());
								    values.put("sexo", sexo.getText().toString());
								    values.put("nascimento", nascimento.getText().toString());
								    values.put("serie", serie.getText().toString());
							        values.put("escola", escola.getText().toString());
								    values.put("endereco", endereco.getText().toString());
								    values.put("professor", professor.getText().toString());
								    values.put("periodo", periodo.getText().toString());
								    values.put("email", email.getText().toString());
								    values.put("entrada", entrada.getText().toString());
								    values.put("bairroe", bairroe.getText().toString());  
								    
							        String[] args = new String[]{String.valueOf(rowID)};
						        bancodados.delete("dpcliente", "id = ?", args);
							

							

						        Toast.makeText(Consulta.this, "Dados excluídos com sucesso!", Toast.LENGTH_SHORT).show();

							return true;

							}
							}catch (Exception erro){

								mensagemExibir ("Erro Banco", "Dados Não alterados no banco: "+erro.getMessage());

							return false;
							}
							return false;
							
						}
				
	
	
	public void mensagemExibir (String titulo, String texto)
	{
	AlertDialog.Builder mensagem = new AlertDialog.Builder 


			(Consulta.this);
	mensagem.setTitle(titulo);
	mensagem.setMessage(texto);
	mensagem.setNeutralButton("OK ", null);
	mensagem.show();
	}
	
	/*public static void showYesNoDialog(String message, Context context,
            OnClickListener yesButton, AlertType alertType) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("Sim", yesButton);
        alertDialog.setNegativeButton("Não", null);
        if (alertType == null) {
            alertDialog.setIcon(alertType.INFO.getDrawable());
            alertDialog.setTitle(alertType.INFO.getTitle());
        } else {
            alertDialog.setIcon(alertType.getDrawable());
            alertDialog.setTitle(alertType.getTitle());
        }
        alertDialog.show();
    }*/
	/*public static void showConfirmDialog(String message, Context context,
            OnClickListener okButton, alertType alertType) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("Ok", okButton);
        alertDialog.setNegativeButton("Cancelar", null);
        if (alertType == null) {
            alertDialog.setIcon(alertType.INFO.getDrawable());
            alertDialog.setTitle(alertType.INFO.getTitle());
        } else {
            alertDialog.setIcon(alertType.getDrawable());
            alertDialog.setTitle(alertType.getTitle());
        }
        alertDialog.show();
    }

}
	*/
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		boolean result = super.onCreateOptionsMenu (menu);
		super.onCreateOptionsMenu (menu);
		menu.add(0, 1, 0, "Chamada"); // aqui add intens no menu
		//menu.add(0, 2, 0, "Cadastro"); // aqui add intens no menu
		menu.add(0, 2, 0, "Menu");
		
		
		
		SubMenu utilitario = menu.addSubMenu ("Controle de Dados");
	utilitario.add(0, 4, 0, "Alterar Dados");
		utilitario.add(0, 5, 0, "Excluir Dados");
		
		
		return result;
		}
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
	switch(item.getItemId())
	{
	case 1 : Intent chamarChamada = new Intent(this, MainActivity.class);
	startActivity(chamarChamada); break;
	//case 2 : Intent chamarcadastro = new Intent(this, DDcliente.class);
	//startActivity(chamarcadastro); break;
	case 2 : Intent chamarMenu = new Intent(this, Cmeni.class);
	startActivity(chamarMenu); break;
	case 4 : AlterarRegistro (); mensagemExibir ("Aviso", "Você irá alterar dados! "); break;
	case 5 : ExcluirRegistro (); mensagemExibir ("Aviso", "Você irá excluir dados! "); break;
	}
	return super.onOptionsItemSelected(item);
	}

	
	}